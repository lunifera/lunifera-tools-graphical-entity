package org.lunifera.tools.graphical.entity.lib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.lunifera.dsl.semantic.entity.LBeanAttribute;
import org.lunifera.dsl.semantic.entity.LBeanFeature;

public class LBeanAttributeDeletion implements IExternalJavaAction {

	public LBeanAttributeDeletion() {
	}

	@Override
	public void execute(Collection<? extends EObject> selections,
			Map<String, Object> parameters) {
		LBeanAttribute attr = (LBeanAttribute) new ArrayList<EObject>(
				selections).get(0);
		EList<LBeanFeature> huhu = attr.getBean().getFeatures();
		huhu.remove(attr);
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
