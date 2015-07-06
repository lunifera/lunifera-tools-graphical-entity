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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.lunifera.dsl.semantic.common.types.LImport;
import org.lunifera.dsl.semantic.common.types.LScalarType;
import org.lunifera.dsl.semantic.common.types.LTypedPackage;
import org.lunifera.dsl.semantic.common.types.LunTypesFactory;
import org.lunifera.dsl.semantic.common.types.LunTypesPackage;
import org.lunifera.dsl.semantic.entity.LunEntityPackage;

public class TransactionListener implements ResourceSetListener {

	@Override
	public NotificationFilter getFilter() {
		return null;
	}

	@Override
	public Command transactionAboutToCommit(ResourceSetChangeEvent event)
			throws RollbackException {
		TransactionalEditingDomain editingDomain = event.getEditingDomain();
		for (Notification notification : event.getNotifications()) {
			if (notification.getEventType() == Notification.SET
					&& notification.getNotifier() instanceof EObject) {
				EObject eObject = (EObject) notification.getNotifier();
				EClass eClass = eObject.eClass();
				if (LunEntityPackage.eNS_URI.equals(eClass.getEPackage()
						.getNsURI())) {
					// do something
					if (notification.getFeature() instanceof EReference) {
						EReference ref = (EReference) notification.getFeature();
						if (ref == LunTypesPackage.Literals.LATTRIBUTE__TYPE) {
							LTypedPackage lPkg = findPackage(eObject);
							LScalarType type = (LScalarType) notification
									.getNewValue();
							if (!containsImport(lPkg, type)) {
								LImport lImport = LunTypesFactory.eINSTANCE
										.createLImport();
								LTypedPackage typePkg = findPackage(type);
								lImport.setImportedNamespace(typePkg.getName()
										+ ".*");

								AddCommand command = (AddCommand) AddCommand
										.create(editingDomain,
												lPkg,
												LunTypesPackage.Literals.LPACKAGE__IMPORTS,
												lImport);
								return command;
							}
						}
					}
				}
			}
		}
		return null;
	}

	private boolean containsImport(LTypedPackage lPkg, LScalarType type) {
		LTypedPackage typePkg = findPackage(type);

		if(typePkg == null){
			return true;
		}
		
		if (lPkg.getName().equals(typePkg.getName())) {
			return true;
		}

		String typePkgName = typePkg.getName();

		for (LImport lImport : lPkg.getImports()) {
			String cutImportName = lImport.getImportedNamespace().replace(
					typePkgName, "");
			// import may be "import org.datatype.String" or
			// "import org.datatype.*"
			if (cutImportName.equals("." + type.getName())
					|| cutImportName.equals(".*")) {
				return true;
			}
		}

		return false;
	}

	protected LTypedPackage findPackage(EObject eObject) {
		if (eObject == null) {
			return null;
		}

		if (eObject instanceof LTypedPackage) {
			return (LTypedPackage) eObject;
		} else {
			return findPackage(eObject.eContainer());
		}
	}

	@Override
	public void resourceSetChanged(ResourceSetChangeEvent event) {

	}

	@Override
	public boolean isAggregatePrecommitListener() {
		return false;
	}

	@Override
	public boolean isPrecommitOnly() {
		return true;
	}

	@Override
	public boolean isPostcommitOnly() {
		return false;
	}

}
