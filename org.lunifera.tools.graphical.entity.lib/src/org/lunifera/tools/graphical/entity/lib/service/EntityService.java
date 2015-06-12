package org.lunifera.tools.graphical.entity.lib.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.business.internal.helper.task.operations.CreateViewTask;
import org.eclipse.sirius.diagram.business.internal.metamodel.spec.DNodeContainerSpec;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.tool.CreateView;
import org.eclipse.sirius.diagram.description.tool.ToolFactory;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.lunifera.dsl.semantic.common.helper.Bounds;
import org.lunifera.dsl.semantic.common.types.LAttribute;
import org.lunifera.dsl.semantic.common.types.LDataType;
import org.lunifera.dsl.semantic.common.types.LEnum;
import org.lunifera.dsl.semantic.common.types.LFeature;
import org.lunifera.dsl.semantic.common.types.LImport;
import org.lunifera.dsl.semantic.common.types.LMultiplicity;
import org.lunifera.dsl.semantic.common.types.LOperation;
import org.lunifera.dsl.semantic.common.types.LPackage;
import org.lunifera.dsl.semantic.common.types.LReference;
import org.lunifera.dsl.semantic.common.types.LType;
import org.lunifera.dsl.semantic.common.types.LTypedPackage;
import org.lunifera.dsl.semantic.common.types.LunTypesFactory;
import org.lunifera.dsl.semantic.entity.LBean;
import org.lunifera.dsl.semantic.entity.LBeanAttribute;
import org.lunifera.dsl.semantic.entity.LBeanReference;
import org.lunifera.dsl.semantic.entity.LEntity;
import org.lunifera.dsl.semantic.entity.LEntityAttribute;
import org.lunifera.dsl.semantic.entity.LEntityModel;
import org.lunifera.dsl.semantic.entity.LEntityReference;
import org.lunifera.dsl.semantic.entity.LunEntityFactory;
import org.lunifera.dsl.semantic.entity.LunEntityPackage;
import org.lunifera.dsl.semantic.entity.util.LunEntitySwitch;
import org.lunifera.runtime.common.metric.TimeLogger;

import com.google.common.base.Ascii;
import com.google.common.base.CharMatcher;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;

public class EntityService {

	public Boolean hasError(EObject eObj) {
		if (eObj instanceof LType || eObj instanceof LFeature) {
			Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eObj);
			DiagnosticAttachment.getOrCreate(eObj, diagnostic);
			return diagnostic.getSeverity() == Diagnostic.ERROR;
		}
		return false;
	}

	public List<EObject> getValidsForDiagram(final EObject element,
			final DSemanticDecorator containerView) {
		Predicate<EObject> validForClassDiagram = new Predicate<EObject>() {
			public boolean apply(EObject input) {
				return input instanceof LTypedPackage || input instanceof LType;
			}
		};
		return allValidSessionElements(element, validForClassDiagram);
	}

	public LEntityModel getEntityModel(LTypedPackage pkg) {
		return (LEntityModel) pkg.eContainer();
	}

	/**
	 * Returns all the root objects of all the resources in the same
	 * resource-set as the specified object.
	 * 
	 * @param any
	 *            an EObject.
	 * @return all the root objects in the same resource-set as <code>any</code>
	 *         or an empty collection if <code>any</code> is not inside a
	 *         resource-set.
	 */
	public Collection<EObject> allRoots(EObject any) {
		Resource res = any.eResource();
		if (res != null && res.getResourceSet() != null) {
			Collection<EObject> roots = new ArrayList<EObject>();
			for (Resource childRes : res.getResourceSet().getResources()) {
				roots.addAll(childRes.getContents());
			}
			return roots;
		} else {
			return Collections.emptySet();
		}
	}

	public Collection<LType> rootElements(EObject any) {
		return Sets.newLinkedHashSet(Iterables.filter(allRoots(any),
				LType.class));
	}

	public Collection<LFeature> children(LType lType) {
		return Sets.newLinkedHashSet(Iterables.filter(allRoots(lType),
				LFeature.class));
	}

	private List<EObject> allValidSessionElements(EObject cur,
			Predicate<EObject> validForClassDiagram) {
		Session found = SessionManager.INSTANCE.getSession(cur);
		List<EObject> result = Lists.newArrayList();
		if (found != null) {
			for (Resource res : found.getSemanticResources()) {
				if (res.getURI().isPlatformResource()
						|| res.getURI().isPlatformPlugin()) {
					Iterators.addAll(result, Iterators.filter(
							res.getAllContents(), validForClassDiagram));
				}
			}
		}
		return result;
	}

	/**
	 * Gets the containing resource name, or null.
	 * 
	 * @param current
	 *            is the object
	 * @return the resource
	 */
	public String eResourceName(final EObject current) {
		if (current != null && current.eResource() != null) {
			return current.eResource().getURI().lastSegment();
		} else {
			return null;
		}
	}

	/**
	 * Performs a "direct edit" operation on an LAttribute.
	 */
	// TODO siehe DesignServices
	public LAttribute performEdit(LAttribute attr, String editString) {
		String[] tokens = editString.split(":");
		attr.setName(tokens[0].trim());
		return attr;
	}

	/**
	 * Performs a "direct edit" operation on an LReference.
	 */
	// TODO siehe DesignServices
	public LReference performEdit(LReference attr, String editString) {
		attr.setName(editString);
		return attr;
	}

	public LTypedPackage performEdit(LTypedPackage pkg, String editString) {
		pkg.setName(editString);
		return pkg;
	}

	public String render(LAttribute attr) {
		StringBuilder b = new StringBuilder();
		b.append(attr.getName());
		b.append(" : ");
		b.append(render(attr.getType()));

		return b.toString();
	}

	public String render(LType type) {
		return type != null ? type.getName() : "";
	}

	public String renderTooltip(EObject current) {
		String result = "";
		Optional<Diagnostic> diag = DiagnosticAttachment.get(current);
		if (diag.isPresent()) {
			result += prettyMessage(diag.get());
		}
		return result;
	}

	public String prettyMessage(Diagnostic diag) {
		String result = "";
		for (Diagnostic child : diag.getChildren()) {
			result += "\n" + severityLabel(child.getSeverity()) + " : "
					+ child.getMessage();
			result += prettyMessage(child);
		}
		return result;
	}

	public String eKeysLabel(LAttribute att) {
		LMultiplicity mult = att.getMultiplicity();
		if (mult == null) {
			mult = LunTypesFactory.eINSTANCE.createLMultiplicity();
			Bounds zeroToOne = Bounds.createZeroToOne();
			mult.setLower(zeroToOne.getLower());
			mult.setUpper(zeroToOne.getUpper());
		}
		return mult.getToMultiplicityString();
	}

	public String eKeysLabel(LReference ref) {
		LMultiplicity mult = ref.getMultiplicity();
		if (mult == null) {
			mult = LunTypesFactory.eINSTANCE.createLMultiplicity();
			Bounds zeroToOne = Bounds.createZeroToOne();
			mult.setLower(zeroToOne.getLower());
			mult.setUpper(zeroToOne.getUpper());
		}
		return mult.getToMultiplicityString();
	}

	public EEnumLiteral arrowsFillDiamond(EObject any) {
		return DiagramPackage.eINSTANCE.getEdgeArrows().getEEnumLiteral(
				"FillDiamond");
	}

	public EEnumLiteral fontFormatBold(EObject any) {
		return ViewpointPackage.eINSTANCE.getFontFormat().getEEnumLiteral(
				"bold");
	}

	public boolean isRequired(LFeature att) {
		return Bounds.createFor(att).isRequired();
	}

	public boolean isCascading(LFeature att) {
		if (att instanceof LReference) {
			return ((LReference) att).isCascading();
		}
		return false;
	}

	public boolean isContainer(LFeature att) {
		return false;
	}

	public boolean isOperation(LFeature feature) {
		return feature instanceof LOperation;
	}

	public String severityLabel(int severity) {
		switch (severity) {
		case Diagnostic.ERROR:
			return "ERROR";
		case Diagnostic.CANCEL:
			return "CANCEL";
		case Diagnostic.INFO:
			return "INFO";
		case Diagnostic.WARNING:
			return "WARNING";
		case Diagnostic.OK:
			return "OK";

		}
		return "UNKNOWN";
	}

	/**
	 * Replace spaces by camel case value.
	 * 
	 * @param any
	 * @param from
	 * @return
	 */
	public String toCamelCase(EObject any, String from) {
		if (from != null) {
			StringBuffer buffer = new StringBuffer(from.length());
			for (String word : Splitter.on(CharMatcher.WHITESPACE)
					.trimResults().split(from)) {
				buffer.append(toU1Case(word));
			}
			return buffer.toString();
		}
		return from;
	}

	private String toU1Case(String word) {
		if (word != null && word.length() > 0) {
			return new StringBuilder(word.length())
					.append(Ascii.toUpperCase(word.charAt(0)))
					.append(word.substring(1)).toString();
		}
		return word;
	}

	public EObject markForAutosize(EObject any) {
		if (any != null) {
			any.eAdapters().add(AutosizeTrigger.AUTO_SIZE_MARKER);
		}
		return any;
	}

	public boolean isEntity(EObject any) {
		return any instanceof LEntity;
	}

	public boolean isBean(EObject any) {
		return any instanceof LBean;
	}

	public boolean isEnum(EObject any) {
		return any instanceof LEnum;
	}

	public boolean isPackage(EObject any) {
		return any instanceof LPackage;
	}

	public List<EObject> children(final LTypedPackage pkg) {
		final String pkgName = pkg.getName();
		Iterable<LTypedPackage> iterable = Iterables.filter(allPackages(pkg),
				new Predicate<LTypedPackage>() {
					@Override
					public boolean apply(LTypedPackage input) {
						return !input.getName().equals(pkgName);
					}
				});

		return Lists.<EObject> newLinkedList(iterable);
	}

	public Boolean viewContainerNotSemanticContainer(EObject self,
			DSemanticDiagram diag, DSemanticDecorator containerView) {
		EObject target = containerView.getTarget();
		return target != self.eContainer();
	}

	public Collection<EObject> getRelated(EObject firstView,
			List<EObject> allSelectedViews, DDiagram diag) {
		Set<EObject> relateds = Sets.newLinkedHashSet();
		for (DSemanticDecorator decorator : Iterables.filter(allSelectedViews,
				DSemanticDecorator.class)) {
			relateds.addAll(new RelatedElementsSwitch()
					.getRelatedElements(decorator.getTarget()));
		}
		return relateds;
	}

	public void openClassDiagramContextHelp(EObject any) {
	}

	public Collection<LTypedPackage> allPackages(EObject any) {
		Resource res = any.eResource();
		if (res != null && res.getResourceSet() != null) {
			Collection<LTypedPackage> roots = new ArrayList<LTypedPackage>();
			for (Resource childRes : res.getResourceSet().getResources()) {
				if (childRes.getContents().isEmpty()) {
					continue;
				}
				EObject element = childRes.getContents().get(0);
				if (element instanceof LEntityModel) {
					roots.addAll(((LEntityModel) element).getPackages());
				}
			}
			return roots;
		} else {
			return Collections.emptySet();
		}
	}

	public boolean hasOpposite(EReference ref) {
		return getOppositeReferences(ref) != null;
	}

	public List<LFeature> getOppositeReferences(EObject ref) {
		if (ref instanceof LReference) {
			return getOppositeSemanticElements((LReference) ref);
		} else {
			return Collections.emptyList();
		}
	}

	public List<LFeature> getOppositeSemanticElements(LReference ref) {
		Set<LFeature> allRefs = Sets.newLinkedHashSet();

		LFeature opposite = getOpposite(ref);
		allRefs.add(ref);
		if (opposite != null) {
			allRefs.add(opposite);
		}
		return Ordering.natural().onResultOf(new Function<LFeature, String>() {
			public String apply(LFeature input) {
				return input.getName();
			}
		}).sortedCopy(allRefs);
	}

	public String renderOpposite(LReference ref) {
		LFeature opposite = getOpposite(ref);

		if (opposite == null) {
			return "";
		}

		StringBuilder b = new StringBuilder();
		b.append("[");
		b.append(opposite.getMultiplicity().getToMultiplicityString());
		b.append("]");
		b.append(" ");
		b.append(opposite.getName());

		return b.toString();
	}

	public String render(LReference ref) {
		if (ref == null) {
			return "";
		}

		StringBuilder b = new StringBuilder();
		b.append("[");
		b.append(ref.getMultiplicity().getToMultiplicityString());
		b.append("]");
		b.append(" ");
		b.append(ref.getName());

		return b.toString();
	}

	public boolean noOpposite(LReference ref) {
		return getOpposite(ref) == null;
	}

	// public Collection<LType> samePackage(EObject eObject,
	// DSemanticDiagram containerView) {
	// return true;
	// // return types;
	// }

	private LFeature getOpposite(LReference ref) {
		if (ref instanceof LEntityReference) {
			return ((LEntityReference) ref).getOpposite();
		} else if (ref instanceof LBeanReference) {
			((LBeanReference) ref).getOpposite();
		}
		return null;
	}

	/**
	 * Paste a semantic element and create the corresponding view in the given
	 * container
	 * 
	 * @param container
	 *            Semantic container
	 * @param semanticElement
	 *            Element to paste
	 * @param containerView
	 *            Container view
	 */
	public void paste(final EObject container, final EObject semanticElement,
			final DSemanticDecorator elementView,
			final DSemanticDecorator containerView) {
		// Paste the semantic element from the clipboard to the selected
		// container
		final Session session = SessionManager.INSTANCE.getSession(container);
		TransactionalEditingDomain domain = session
				.getTransactionalEditingDomain();

		Command cmd = null;
		if (container instanceof LEntity
				&& semanticElement instanceof LEntityAttribute) {
			cmd = AddCommand.create(domain, container,
					LunEntityPackage.Literals.LENTITY__FEATURES,
					semanticElement);
		} else if (container instanceof LBean
				&& semanticElement instanceof LBeanAttribute) {
			cmd = AddCommand.create(domain, container,
					LunEntityPackage.Literals.LBEAN__FEATURES, semanticElement);
		} else if (container instanceof LEntity
				&& semanticElement instanceof LBeanAttribute) {

			LBeanAttribute beanAtt = EcoreUtil
					.copy((LBeanAttribute) semanticElement);
			cmd = AddCommand.create(domain, container,
					LunEntityPackage.Literals.LENTITY__FEATURES,
					createEntityAttribute(beanAtt));
		} else if (container instanceof LBean
				&& semanticElement instanceof LEntityAttribute) {
			LEntityAttribute entityAtt = EcoreUtil
					.copy((LEntityAttribute) semanticElement);
			cmd = AddCommand.create(domain, container,
					LunEntityPackage.Literals.LBEAN__FEATURES,
					createBeanAttribute(entityAtt));
		} else {
			cmd = AddCommand.create(domain, container, null, semanticElement);
		}

		// The feature is set to null because the domain will deduce it

		if (cmd.canExecute()) {
			cmd.execute();
		}
		// Create the view for the pasted element
		createView(semanticElement, containerView, session, "containerView");
	}

	private LEntityAttribute createEntityAttribute(LBeanAttribute beanAtt) {
		LEntityAttribute entityAtt = LunEntityFactory.eINSTANCE
				.createLEntityAttribute();

		entityAtt.setAnnotationInfo(beanAtt.getAnnotationInfo());
		entityAtt.setCascading(beanAtt.isCascading());
		entityAtt.setDerived(beanAtt.isDerived());
		entityAtt.setDirty(beanAtt.isDirty());
		entityAtt.setDerivedGetterExpression(beanAtt
				.getDerivedGetterExpression());
		entityAtt.setDomainDescription(beanAtt.isDomainDescription());
		entityAtt.setDomainKey(beanAtt.isDomainKey());
		entityAtt.setId(beanAtt.isId());
		entityAtt.setLazy(beanAtt.isLazy());
		entityAtt.setMultiplicity(beanAtt.getMultiplicity());
		entityAtt.setName(beanAtt.getName());
		entityAtt.setTransient(beanAtt.isTransient());
		entityAtt.setType(beanAtt.getType());
		entityAtt.setTypeJvm(beanAtt.getTypeJvm());
		entityAtt.setUuid(beanAtt.isUuid());
		entityAtt.setVersion(beanAtt.isVersion());

		entityAtt.getAnnotations().addAll(beanAtt.getAnnotations());
		entityAtt.getProperties().addAll(beanAtt.getProperties());
		entityAtt.getResolvedAnnotations().addAll(
				beanAtt.getResolvedAnnotations());
		return entityAtt;
	}

	private LBeanAttribute createBeanAttribute(LEntityAttribute entityAtt) {
		LBeanAttribute beanAtt = LunEntityFactory.eINSTANCE
				.createLBeanAttribute();

		beanAtt.setAnnotationInfo(entityAtt.getAnnotationInfo());
		beanAtt.setCascading(entityAtt.isCascading());
		beanAtt.setDerived(entityAtt.isDerived());
		beanAtt.setDirty(entityAtt.isDirty());
		beanAtt.setDerivedGetterExpression(entityAtt
				.getDerivedGetterExpression());
		beanAtt.setDomainDescription(entityAtt.isDomainDescription());
		beanAtt.setDomainKey(entityAtt.isDomainKey());
		beanAtt.setId(entityAtt.isId());
		beanAtt.setLazy(entityAtt.isLazy());
		beanAtt.setMultiplicity(entityAtt.getMultiplicity());
		beanAtt.setName(entityAtt.getName());
		beanAtt.setTransient(entityAtt.isTransient());
		beanAtt.setType(entityAtt.getType());
		beanAtt.setTypeJvm(entityAtt.getTypeJvm());
		beanAtt.setUuid(entityAtt.isUuid());
		beanAtt.setVersion(entityAtt.isVersion());

		beanAtt.getAnnotations().addAll(entityAtt.getAnnotations());
		beanAtt.getProperties().addAll(entityAtt.getProperties());
		beanAtt.getResolvedAnnotations().addAll(
				entityAtt.getResolvedAnnotations());
		return beanAtt;
	}

	/**
	 * Create view.
	 * 
	 * @param semanticElement
	 *            Semantic element
	 * @param containerView
	 *            Container view
	 * @param session
	 *            Session
	 * @param containerViewVariable
	 *            Name of the container view variable
	 */
	private void createView(final EObject semanticElement,
			final DSemanticDecorator containerView, final Session session,
			final String containerViewVariable) {
		// Get all available mappings applicable for the copiedElement in the
		// current container
		List<DiagramElementMapping> semanticElementMappings = getMappings(
				semanticElement, containerView, session);

		// Build a createView tool
		final CreateView createViewOp = ToolFactory.eINSTANCE
				.createCreateView();
		for (DiagramElementMapping copiedElementMapping : semanticElementMappings) {
			final DiagramElementMapping tmpCopiedElementMapping = copiedElementMapping;
			createViewOp.setMapping(tmpCopiedElementMapping);
			final String containerViewExpression = "var:"
					+ containerViewVariable;
			createViewOp.setContainerViewExpression(containerViewExpression);

			session.getTransactionalEditingDomain()
					.getCommandStack()
					.execute(
							new RecordingCommand(session
									.getTransactionalEditingDomain()) {

								@SuppressWarnings("restriction")
								@Override
								protected void doExecute() {
									try {
										// Get the command context
										DRepresentation representation = null;
										if (containerView instanceof DRepresentation) {
											representation = (DRepresentation) containerView;
										} else if (containerView instanceof DDiagramElement) {
											representation = ((DDiagramElement) containerView)
													.getParentDiagram();
										}

										final CommandContext context = new CommandContext(
												semanticElement, representation);

										// Execute the create view task
										new CreateViewTask(context, session
												.getModelAccessor(),
												createViewOp, session
														.getInterpreter())
												.execute();
									} catch (MetaClassNotFoundException e) {
									} catch (FeatureNotFoundException e) {
									}
								}
							});
		}
	}

	/**
	 * Get mappings available for a semantic element and a given container view.
	 * 
	 * @param semanticElement
	 *            Semantic element
	 * @param containerView
	 *            Container view
	 * @param session
	 *            Session
	 * @return List of mappings which could not be null
	 */
	@SuppressWarnings("restriction")
	private List<DiagramElementMapping> getMappings(
			final EObject semanticElement,
			final DSemanticDecorator containerView, Session session) {
		ModelAccessor modelAccessor = session.getModelAccessor();
		List<DiagramElementMapping> mappings = new ArrayList<DiagramElementMapping>();

		if (containerView instanceof DSemanticDiagram) {
			for (DiagramElementMapping mapping : (((DSemanticDiagram) containerView)
					.getDescription().getAllContainerMappings())) {
				String domainClass = ((AbstractNodeMapping) mapping)
						.getDomainClass();
				if (modelAccessor.eInstanceOf(semanticElement, domainClass)
						&& !mapping.isCreateElements()) {
					mappings.add(mapping);
				}
			}
			for (DiagramElementMapping mapping : (((DSemanticDiagram) containerView)
					.getDescription().getAllNodeMappings())) {
				String domainClass = ((AbstractNodeMapping) mapping)
						.getDomainClass();
				if (modelAccessor.eInstanceOf(semanticElement, domainClass)
						&& !mapping.isCreateElements()) {
					mappings.add(mapping);
				}
			}
		} else if (containerView instanceof DNodeContainerSpec) {
			for (DiagramElementMapping mapping : (((DNodeContainerSpec) containerView)
					.getActualMapping().getAllContainerMappings())) {
				String domainClass = ((AbstractNodeMapping) mapping)
						.getDomainClass();
				if (modelAccessor.eInstanceOf(semanticElement, domainClass)
						&& !mapping.isCreateElements()) {
					mappings.add(mapping);
				}
			}
			for (DiagramElementMapping mapping : (((DNodeContainerSpec) containerView)
					.getActualMapping().getAllNodeMappings())) {
				String domainClass = ((AbstractNodeMapping) mapping)
						.getDomainClass();
				if (modelAccessor.eInstanceOf(semanticElement, domainClass)
						&& !mapping.isCreateElements()) {
					mappings.add(mapping);
				}
			}
		}
		return mappings;
	}

	public LTypedPackage createNewPackage(LTypedPackage container) {
		LEntityModel model = (LEntityModel) container.eContainer();
		LTypedPackage typedPackage = LunTypesFactory.eINSTANCE
				.createLTypedPackage();
		typedPackage.setName(container.getName() + "." + "n"
				+ model.getPackages().size());
		model.getPackages().add(typedPackage);

		return typedPackage;
	}

	public void reconnectReference(EObject element, DEdge edgeAfterReconnect) {

		if (edgeAfterReconnect.getSourceNode() instanceof DSemanticDecorator
				&& edgeAfterReconnect.getTargetNode() instanceof DSemanticDecorator) {
			if (element instanceof LEntityReference) {
				LEntityReference entityRef = (LEntityReference) element;

				LEntity newSource = (LEntity) ((DSemanticDecorator) edgeAfterReconnect
						.getSourceNode()).getTarget();
				LEntity newTarget = (LEntity) ((DSemanticDecorator) edgeAfterReconnect
						.getTargetNode()).getTarget();
				LEntity oldSource = entityRef.getEntity();
				LEntity oldTarget = entityRef.getType();

				// reconnect the source
				if (oldSource != newSource) {
					oldSource.getReferences().remove(entityRef);
					newSource.getReferences().add(entityRef);
				}

				// reconnect the target
				if (oldTarget != newTarget) {
					entityRef.setType(newTarget);
				}
			} else if (element instanceof LBeanReference) {
				LBeanReference beanRef = (LBeanReference) element;

				LBean newSource = (LBean) ((DSemanticDecorator) edgeAfterReconnect
						.getSourceNode()).getTarget();
				LType newTarget = (LEntity) ((DSemanticDecorator) edgeAfterReconnect
						.getTargetNode()).getTarget();
				LBean oldSource = beanRef.getBean();
				LType oldTarget = beanRef.getType();

				// reconnect the source
				if (oldSource != newSource) {
					oldSource.getReferences().remove(beanRef);
					newSource.getReferences().add(beanRef);
				}

				// reconnect the target
				if (oldTarget != newTarget) {
					beanRef.setType(newTarget);
				}
			}
		}
	}

	public void reconnectSuperType(EObject element, DEdge edgeAfterReconnect) {

		if (edgeAfterReconnect.getSourceNode() instanceof DSemanticDecorator
				&& edgeAfterReconnect.getTargetNode() instanceof DSemanticDecorator) {
			if (element instanceof LEntity) {
				LEntity entity = (LEntity) element;

				LEntity newSource = (LEntity) ((DSemanticDecorator) edgeAfterReconnect
						.getSourceNode()).getTarget();
				LEntity newTarget = (LEntity) ((DSemanticDecorator) edgeAfterReconnect
						.getTargetNode()).getTarget();

				entity.setSuperType(null);
				newSource.setSuperType(newTarget);

			} else if (element instanceof LBean) {
				LBean bean = (LBean) element;

				LBean newSource = (LBean) ((DSemanticDecorator) edgeAfterReconnect
						.getSourceNode()).getTarget();
				LBean newTarget = (LBean) ((DSemanticDecorator) edgeAfterReconnect
						.getTargetNode()).getTarget();

				bean.setSuperType(null);
				newSource.setSuperType(newTarget);

			}
		}
	}

	public boolean dropType(EObject var, LType type,
			LTypedPackage newSemanticContainer,
			LTypedPackage oldSemanticContainer) {

		Set<LTypedPackage> related = getRelatedPackages(type);
		related.add(oldSemanticContainer);
		related.add(newSemanticContainer);

		// do the move
		oldSemanticContainer.getTypes().remove(type);
		newSemanticContainer.getTypes().add(type);

		// fix imports
		//
		TimeLogger logger = TimeLogger.start(getClass());
		for (LTypedPackage pkg : related) {
			fixImports(pkg);
		}
		logger.stop("Fixing imports for dropped " + type.getName());

		return true;
	}

	protected Set<LTypedPackage> getRelatedPackages(LType type) {
		RelatedElementsSwitch rS = new RelatedElementsSwitch();
		List<EObject> related = rS.getRelatedElements(type);

		Set<LTypedPackage> result = new HashSet<LTypedPackage>();
		for (EObject r : related) {
			if (r instanceof LType) {
				LTypedPackage pkg = (LTypedPackage) r.eContainer();
				result.add(pkg);
			} else if (r instanceof LReference) {
				// r.type == given type && r is reference from somewhere to type
				LType lType = (LType) r.eContainer();
				LTypedPackage pkg = (LTypedPackage) lType.eContainer();
				result.add(pkg);
			}
		}

		return result;
	}

	public boolean removeReference(LEntityReference ref) {

		LEntity source = ref.getEntity();
		LEntity target = ref.getType();

		source.getFeatures().remove(ref);
		ref.setType(null);

		fixImports(source, source);
		fixImports(target, target);

		return true;
	}

	public boolean removeReference(LBeanReference ref) {

		LBean source = ref.getBean();
		LType target = ref.getType();

		source.getFeatures().remove(ref);
		ref.setType(null);

		fixImports(source, source);
		fixImports(target, target);

		return true;
	}

	public boolean fixImports(EObject var, LTypedPackage newSemanticContainer,
			LTypedPackage oldSemanticContainer) {
		fixImports(oldSemanticContainer);
		fixImports(newSemanticContainer);

		return true;
	}

	public boolean fixImports(EObject context, LType type) {
		if (type instanceof LEntity) {
			fixImports(context, (LEntity) type);
		} else if (type instanceof LBean) {
			fixImports(context, (LBean) type);
		}

		return true;
	}

	public boolean fixImports(EObject context, LBean subtype) {
		LBean supertype = subtype.getSuperType();
		fixImports((LTypedPackage) subtype.eContainer());
		if (supertype != null && supertype.eContainer() != subtype.eContainer()) {
			fixImports((LTypedPackage) supertype.eContainer());
		}

		return true;
	}

	public boolean fixImports(EObject context, LEntity subtype) {
		LEntity supertype = subtype.getSuperType();
		fixImports((LTypedPackage) subtype.eContainer());
		if (supertype != null && supertype.eContainer() != subtype.eContainer()) {
			fixImports((LTypedPackage) supertype.eContainer());
		}

		return true;
	}

	public boolean fixImports(LTypedPackage lPkg) {

		// remove old imports
		lPkg.getImports().clear();

		ImportCollectionSwitch s = new ImportCollectionSwitch();
		s.doSwitch(lPkg);

		appendImports(lPkg, s.imports);

		return true;
	}

	private void appendImports(LTypedPackage lPkg, Set<String> imports) {
		for (String imp : imports) {
			if (!lPkg.getName().equals(imp)) {
				LImport lImp = LunTypesFactory.eINSTANCE.createLImport();
				lImp.setImportedNamespace(imp + ".*");
				lPkg.getImports().add(lImp);
			}
		}
	}
	
	public EObject openTextEditor(EObject any) {
		if (any != null && any.eResource() instanceof XtextResource
				&& any.eResource().getURI() != null) {

			String fileURI = any.eResource().getURI().toPlatformString(true);
			IFile workspaceFile = ResourcesPlugin.getWorkspace().getRoot()
					.getFile(new Path(fileURI));
			if (workspaceFile != null) {
				IWorkbenchPage page = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage();
				try {
					IEditorPart openEditor = IDE.openEditor(page,
							workspaceFile,
							"org.lunifera.dsl.entity.xtext.EntityGrammar",
							true);
					if (openEditor instanceof AbstractTextEditor) {
						ICompositeNode node = NodeModelUtils
								.findActualNodeFor(any);
						if (node != null) {
							int offset = node.getOffset();
							int length = node.getTotalEndOffset() - offset;
							((AbstractTextEditor) openEditor).selectAndReveal(
									offset, length);
						}
					}
					// editorInput.
				} catch (PartInitException e) {
					// Put your exception handler here if you wish to.
				}
			}
		}
		return any;
	}

	private class ImportCollectionSwitch extends LunEntitySwitch<Boolean> {

		private Set<String> imports = new HashSet<String>();
		private boolean isInType;

		@Override
		public Boolean caseLBean(LBean object) {

			addImport(object);

			if (isInType) {
				return true;
			}

			isInType = true;

			if (object.getSuperType() != null) {
				doSwitch(object.getSuperType());
			}

			for (LBeanAttribute att : object.getAttributes()) {
				doSwitch(att);
			}

			for (LBeanReference ref : object.getReferences()) {
				doSwitch(ref);
			}

			isInType = false;

			return super.caseLBean(object);
		}

		@Override
		public Boolean caseLEntity(LEntity object) {

			addImport(object);

			if (isInType) {
				return true;
			}

			isInType = true;

			if (object.getSuperType() != null) {
				doSwitch(object.getSuperType());
			}

			for (LEntityAttribute att : object.getAttributes()) {
				doSwitch(att);
			}

			for (LEntityReference ref : object.getReferences()) {
				doSwitch(ref);
			}

			isInType = false;

			return super.caseLEntity(object);
		}

		@Override
		public Boolean caseLEntityAttribute(LEntityAttribute object) {
			if (object.getType() instanceof LBean) {
				doSwitch(object.getType());
			} else if (object.getType() instanceof LDataType) {
				doSwitch(object.getType());
			} else if (object.getType() instanceof LEnum) {
				doSwitch(object.getType());
			}

			return super.caseLEntityAttribute(object);
		}

		@Override
		public Boolean caseLEntityReference(LEntityReference object) {

			if (object.getType() != null) {
				doSwitch(object.getType());
			}

			return super.caseLEntityReference(object);
		}

		@Override
		public Boolean caseLBeanAttribute(LBeanAttribute object) {
			if (object.getType() instanceof LBean) {
				doSwitch(object.getType());
			} else if (object.getType() instanceof LDataType) {
				doSwitch(object.getType());
			} else if (object.getType() instanceof LEnum) {
				doSwitch(object.getType());
			}

			return super.caseLBeanAttribute(object);
		}

		@Override
		public Boolean caseLBeanReference(LBeanReference object) {
			if (object.getType() != null) {
				doSwitch(object.getType());
			}
			return super.caseLBeanReference(object);
		}

		@Override
		public Boolean defaultCase(EObject object) {
			if (object instanceof LTypedPackage) {
				for (LType type : ((LTypedPackage) object).getTypes()) {
					doSwitch(type);
				}
			} else if (object instanceof LDataType) {
				addImport((LType) object);
			} else if (object instanceof LEnum) {
				addImport((LType) object);
			}
			return super.defaultCase(object);
		}

		private void addImport(LType type) {
			imports.add(calcImport(type));
		}

		private String calcImport(LType type) {
			if (type == null) {
				return "";
			}

			LTypedPackage pkg = (LTypedPackage) type.eContainer();
			return pkg.getName();
		}

	}

}
