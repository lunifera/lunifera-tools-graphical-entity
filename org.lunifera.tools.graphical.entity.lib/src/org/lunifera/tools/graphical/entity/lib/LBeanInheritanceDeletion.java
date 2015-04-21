package org.lunifera.tools.graphical.entity.lib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.lunifera.dsl.semantic.entity.LBean;

public class LBeanInheritanceDeletion implements IExternalJavaAction {

	public LBeanInheritanceDeletion() {
	}

	@Override
	public void execute(Collection<? extends EObject> selections,
			Map<String, Object> parameters) {
		LBean subtype = (LBean) new ArrayList<EObject>(selections).get(0);
		subtype.setSuperType(null);
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
