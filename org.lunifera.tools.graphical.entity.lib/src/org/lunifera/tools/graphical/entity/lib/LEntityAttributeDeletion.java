package org.lunifera.tools.graphical.entity.lib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.lunifera.dsl.semantic.entity.LEntityAttribute;
import org.lunifera.dsl.semantic.entity.LEntityFeature;

public class LEntityAttributeDeletion implements IExternalJavaAction {

	public LEntityAttributeDeletion() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Collection<? extends EObject> selections,
			Map<String, Object> parameters) {
		LEntityAttribute attr = (LEntityAttribute) new ArrayList<EObject>(
				selections).get(0);
		EList<LEntityFeature> huhu = attr.getEntity().getFeatures();
		huhu.remove(attr);
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
