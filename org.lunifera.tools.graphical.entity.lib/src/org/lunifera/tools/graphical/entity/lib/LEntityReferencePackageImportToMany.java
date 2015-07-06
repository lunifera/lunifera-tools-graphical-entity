/**
 * Copyright (c) 2011 - 2015, Lunifera GmbH (Gross Enzersdorf), Loetz KG (Heidelberg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *         Florian Pirchner - Initial implementation
 */

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
import org.lunifera.dsl.semantic.entity.LEntity;
import org.lunifera.dsl.semantic.entity.LEntityFeature;
import org.lunifera.dsl.semantic.entity.LEntityReference;

public class LEntityReferencePackageImportToMany implements IExternalJavaAction {
	private List<LEntityReference> oldReferences;

	public LEntityReferencePackageImportToMany() {
		oldReferences = new ArrayList<LEntityReference>();
	}

	@Override
	public void execute(Collection<? extends EObject> selections,
			Map<String, Object> parameters) {
		LEntityReference newRef = getNewReference(selections);
		importIfNecessary(newRef);
		LEntityReference opposite = getNewReference(selections).getOpposite();
		importIfNecessary(opposite);

	}

	private void importIfNecessary(LEntityReference newRef) {
		LTypedPackage sourcePackage = (LTypedPackage) newRef.getEntity()
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
		oldReferences = filterEntityReferences(selections);
		return true;
	}

	private LEntityReference getNewReference(
			Collection<? extends EObject> selections) {

		List<LEntityReference> newTempRefs = filterEntityReferences(selections);
		newTempRefs.removeAll(oldReferences);
		LEntityReference newReference = newTempRefs.get(0);
		newTempRefs.addAll(oldReferences);
		return newReference;
	}

	private List<LEntityReference> filterEntityReferences(
			Collection<? extends EObject> selections) {
		List<LEntityReference> tmpList = new ArrayList<LEntityReference>();
		for (EObject eObject : selections) {
			for (LEntityFeature x : ((LEntity) eObject).getFeatures()) {
				if (x instanceof LEntityReference && !tmpList.contains(x)) {
					tmpList.add((LEntityReference) x);
				}
			}
		}
		return tmpList;
	}

}
