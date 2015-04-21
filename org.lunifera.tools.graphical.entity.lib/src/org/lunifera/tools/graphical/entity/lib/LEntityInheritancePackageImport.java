package org.lunifera.tools.graphical.entity.lib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.lunifera.dsl.semantic.common.types.LImport;
import org.lunifera.dsl.semantic.common.types.LTypedPackage;
import org.lunifera.dsl.semantic.common.types.LunTypesFactory;
import org.lunifera.dsl.semantic.entity.LEntity;

public class LEntityInheritancePackageImport implements IExternalJavaAction {

	public LEntityInheritancePackageImport() {
	}

	@Override
	public void execute(Collection<? extends EObject> selections,
			Map<String, Object> parameters) {
		LEntity subtype = (LEntity) new ArrayList<EObject>(selections).get(0);
		LEntity supertype = subtype.getSuperType();

		LTypedPackage sourcePackage = (LTypedPackage) subtype.eContainer();
		LTypedPackage targetPackage = (LTypedPackage) supertype.eContainer();

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
		return true;
	}

}
