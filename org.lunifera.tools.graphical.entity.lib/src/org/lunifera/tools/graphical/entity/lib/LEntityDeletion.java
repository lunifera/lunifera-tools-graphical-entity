package org.lunifera.tools.graphical.entity.lib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.lunifera.dsl.semantic.common.types.LTypedPackage;
import org.lunifera.dsl.semantic.entity.LEntity;

public class LEntityDeletion implements IExternalJavaAction {

	public LEntityDeletion() {
	}

	@Override
	public void execute(Collection<? extends EObject> selections,
			Map<String, Object> parameters) {
		LEntity entity = (LEntity) new ArrayList<EObject>(selections).get(0);
		
		Session session = SessionManager.INSTANCE.getSession(entity);
		SiriusUtil.delete(entity, session);
		
//		Collection<Setting> crossRefs = EcoreUtil.UsageCrossReferencer.find(entity, entity.eResource().getResourceSet());
//		for(Setting e : crossRefs){
//			System.out.println(e);
//		}
//		
//		LTypedPackage packag = (LTypedPackage) entity.eContainer();
//		packag.getTypes().remove(entity);
		
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
