package org.lunifera.tools.graphical.entity.lib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.lunifera.dsl.semantic.common.types.LImport;
import org.lunifera.dsl.semantic.common.types.LTypedPackage;
import org.lunifera.dsl.semantic.common.types.LunTypesFactory;
import org.lunifera.dsl.semantic.entity.LBean;
import org.lunifera.dsl.semantic.entity.LBeanFeature;
import org.lunifera.dsl.semantic.entity.LBeanReference;

public class LBeanReferencePackageImportToMany implements IExternalJavaAction {
	private List<LBeanReference> oldReferences;

	public LBeanReferencePackageImportToMany() {
		oldReferences = new ArrayList<LBeanReference>();
	}

	@Override
	public void execute(Collection<? extends EObject> selections,
			Map<String, Object> parameters) {
		LBeanReference newRef = getNewReference(selections);
		importIfNecessary(newRef);
		LBeanReference opposite = (LBeanReference) getNewReference(selections)
				.getOpposite();
		importIfNecessary(opposite);

	}

	private void importIfNecessary(LBeanReference newRef) {
		LTypedPackage sourcePackage = (LTypedPackage) newRef.getBean()
				.eContainer();
		LTypedPackage targetPackage = (LTypedPackage) newRef.getType()
				.eContainer();

		if (targetPackage == sourcePackage) {
			return;
		} else {
			LImport newImport = LunTypesFactory.eINSTANCE.createLImport();
			newImport.setImportedNamespace(targetPackage.getName() + ".*");
			boolean alreadyimported = false;
			for (LImport imp : sourcePackage.getImports()) {
				if (newImport.getImportedNamespace().equals(
						imp.getImportedNamespace())) {
					alreadyimported = true;
				}
			}
			if (!alreadyimported) {
				sourcePackage.getImports().add(newImport);
			}
		}
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		oldReferences = filterBeanReferences(selections);
		return true;
	}

	private LBeanReference getNewReference(
			Collection<? extends EObject> selections) {

		List<LBeanReference> newTempRefs = filterBeanReferences(selections);
		newTempRefs.removeAll(oldReferences);
		LBeanReference newReference = newTempRefs.get(0);
		newTempRefs.addAll(oldReferences);
		return newReference;
	}

	private List<LBeanReference> filterBeanReferences(
			Collection<? extends EObject> selections) {
		List<LBeanReference> tmpList = new ArrayList<LBeanReference>();
		for (EObject eObject : selections) {
			for (LBeanFeature x : ((LBean) eObject).getFeatures()) {
				if (x instanceof LBeanReference && !tmpList.contains(x)) {
					tmpList.add((LBeanReference) x);
				}
			}
		}
		return tmpList;
	}

}
