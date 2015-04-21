package org.lunifera.tools.graphical.entity.lib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.lunifera.dsl.semantic.common.types.LTypedPackage;
import org.lunifera.dsl.semantic.entity.LBean;

public class LBeanDeletion implements IExternalJavaAction {

	public LBeanDeletion() {
	}

	@Override
	public void execute(Collection<? extends EObject> selections,
			Map<String, Object> parameters) {
		LBean bean = (LBean) new ArrayList<EObject>(selections).get(0);
		LTypedPackage packag = (LTypedPackage) bean.eContainer();
		packag.getTypes().remove(bean);
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
