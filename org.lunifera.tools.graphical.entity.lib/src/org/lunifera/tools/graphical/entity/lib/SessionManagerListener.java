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

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

public class SessionManagerListener implements
		org.eclipse.sirius.business.api.session.SessionManagerListener {

	private static final String DATATYPE_PLUGIN = "platform:/plugin/org.lunifera.dsl.datatype.lib/src/org/lunifera/dsl/common/datatypes/baseElements.datatypes";
	private static final String DATATYPE_RESOURCE = "platform:/resource/org.lunifera.dsl.datatype.lib/src/org/lunifera/dsl/common/datatypes/baseElements.datatypes";
	
	@Override
	public void notifyAddSession(Session newSession) {

		URI from = URI.createURI(DATATYPE_RESOURCE);
		URI to = URI.createURI(DATATYPE_PLUGIN);
		
//		newSession.getTransactionalEditingDomain().addResourceSetListener(new TransactionListener());
		
//		MapUriCommand muCommand = new MapUriCommand(newSession,from, to, null, null );
//		newSession.getTransactionalEditingDomain().getCommandStack()
//		.execute(muCommand);
//		
//		AddSemanticResourceCommand command = new AddSemanticResourceCommand(
//				newSession, URI.createURI(DATATYPE_RESOURCE),
//				new NullProgressMonitor());
//		newSession.getTransactionalEditingDomain().getCommandStack()
//				.execute(command);
	}

	@Override
	public void notifyRemoveSession(Session removedSession) {

	}

	@Override
	public void viewpointSelected(Viewpoint selectedSirius) {

	}

	@Override
	public void viewpointDeselected(Viewpoint deselectedSirius) {

	}

	@Override
	public void notify(Session updated, int notification) {
		
		int i;
	}

}
