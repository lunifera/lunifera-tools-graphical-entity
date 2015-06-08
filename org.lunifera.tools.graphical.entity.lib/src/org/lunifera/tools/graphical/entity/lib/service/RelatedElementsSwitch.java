/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES and Others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.lunifera.tools.graphical.entity.lib.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.lunifera.dsl.semantic.common.types.LEnum;
import org.lunifera.dsl.semantic.common.types.LTypedPackage;
import org.lunifera.dsl.semantic.entity.LBean;
import org.lunifera.dsl.semantic.entity.LBeanAttribute;
import org.lunifera.dsl.semantic.entity.LBeanReference;
import org.lunifera.dsl.semantic.entity.LEntity;
import org.lunifera.dsl.semantic.entity.LEntityAttribute;
import org.lunifera.dsl.semantic.entity.LEntityReference;
import org.lunifera.dsl.semantic.entity.util.LunEntitySwitch;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;

/**
 * A switch implementation retrieving all the elements which might be related to
 * a single one.
 */
public class RelatedElementsSwitch extends LunEntitySwitch<List<EObject>> {

	private Set<EObject> relateds;

	private Collection<Setting> xRefs;

	private ECrossReferenceAdapter referencer;

	public RelatedElementsSwitch() {

	}

	public RelatedElementsSwitch(ECrossReferenceAdapter xRef) {
		this.referencer = xRef;
	}

	public List<EObject> getRelatedElements(EObject ctx) {
		Session sess = SessionManager.INSTANCE.getSession(ctx);
		relateds = Sets.newLinkedHashSet();
		if (sess != null) {
			xRefs = sess.getSemanticCrossReferencer().getInverseReferences(ctx);
		} else if (referencer != null) {
			xRefs = referencer.getInverseReferences(ctx);
		}
		doSwitch(ctx);
		relateds.remove(ctx);
		// hack to prevent some null element in relateds for a unknown reason.
		relateds.remove(null);
		return ImmutableList.copyOf(relateds);
	}

	@Override
	public List<EObject> caseLEntity(LEntity object) {
		relateds.add(object.getSuperType());

		for (LEntityReference ref : object.getReferences()) {
			if (ref.getType() != null) {
				caseLEntityReference(ref);
			}
		}

		for (LEntityAttribute ref : object.getAttributes()) {
			if (ref.getType() instanceof LBean) {
				caseLEntityAttribute(ref);
			}
		}

		return super.caseLEntity(object);
	}

	@Override
	public List<EObject> caseLBean(LBean object) {
		for (LBeanReference ref : object.getReferences()) {
			if (ref.getType() != null) {
				caseLBeanReference(ref);
			}
		}

		for (LBeanAttribute ref : object.getAttributes()) {
			caseLBeanAttribute(ref);
		}

		return super.caseLBean(object);
	}

	@Override
	public List<EObject> caseLBeanReference(LBeanReference object) {
		relateds.add(object.getType());
		return super.caseLReference(object);
	}

	@Override
	public List<EObject> caseLEntityReference(LEntityReference object) {
		relateds.add(object.getType());
		return super.caseLEntityReference(object);
	}

	@Override
	public List<EObject> caseLBeanAttribute(LBeanAttribute object) {
		if (object.getType() instanceof LBean
				|| object.getType() instanceof LEnum) {
			relateds.add(object.getType());
		}
		return super.caseLBeanAttribute(object);
	}

	@Override
	public List<EObject> caseLEntityAttribute(LEntityAttribute object) {
		if (object.getType() instanceof LBean
				|| object.getType() instanceof LEnum) {
			relateds.add(object.getType());
		}
		return super.caseLEntityAttribute(object);
	}

	@Override
	public List<EObject> defaultCase(EObject object) {
		if (object instanceof LTypedPackage) {
			relateds.addAll(((LTypedPackage) object).getTypes());
		}
		return super.defaultCase(object);
	}
}
