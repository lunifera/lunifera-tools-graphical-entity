/**
 * Copyright (c) 2011 - 2014, Lunifera GmbH (Gross Enzersdorf), Loetz KG (Heidelberg)
 *  All rights reserved. This program and the accompanying materials 
 *  are made available under the terms of the Eclipse Public License v1.0 
 *  which accompanies this distribution, and is available at 
 *  http://www.eclipse.org/legal/epl-v10.html 
 * 
 *  Based on ideas from Xtext, Xtend, Xcore
 *    
 *  Contributors:  
 *  		Florian Pirchner - Initial implementation 
 *  
 */
package org.lunifera.dsl.semantic.dto.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.xtext.xbase.XbaseFactory;

import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotationsFactory;

import org.lunifera.dsl.semantic.dto.LDtoMapper;
import org.lunifera.dsl.semantic.dto.LunDtoPackage;

/**
 * This is the item provider adapter for a {@link org.lunifera.dsl.semantic.dto.LDtoMapper} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class LDtoMapperItemProvider 
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LDtoMapperItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO);
			childrenFeatures.add(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns LDtoMapper.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/LDtoMapper"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_LDtoMapper_type");
	}
	

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(LDtoMapper.class)) {
			case LunDtoPackage.LDTO_MAPPER__TO_DTO:
			case LunDtoPackage.LDTO_MAPPER__FROM_DTO:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XAnnotationsFactory.eINSTANCE.createXAnnotation()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXIfExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXSwitchExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXBlockExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXVariableDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXMemberFeatureCall()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXFeatureCall()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXConstructorCall()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXBooleanLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXNullLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXNumberLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXStringLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXListLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXSetLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXClosure()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXCastedExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXBinaryOperation()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXUnaryOperation()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXPostfixOperation()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXForLoopExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXBasicForLoopExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXDoWhileExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXWhileExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXTypeLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXInstanceOfExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXThrowExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXTryCatchFinallyExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXAssignment()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXReturnExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO,
				 XbaseFactory.eINSTANCE.createXSynchronizedExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XAnnotationsFactory.eINSTANCE.createXAnnotation()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXIfExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXSwitchExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXBlockExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXVariableDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXMemberFeatureCall()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXFeatureCall()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXConstructorCall()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXBooleanLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXNullLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXNumberLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXStringLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXListLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXSetLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXClosure()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXCastedExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXBinaryOperation()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXUnaryOperation()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXPostfixOperation()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXForLoopExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXBasicForLoopExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXDoWhileExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXWhileExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXTypeLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXInstanceOfExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXThrowExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXTryCatchFinallyExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXAssignment()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXReturnExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO,
				 XbaseFactory.eINSTANCE.createXSynchronizedExpression()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == LunDtoPackage.Literals.LDTO_MAPPER__TO_DTO ||
			childFeature == LunDtoPackage.Literals.LDTO_MAPPER__FROM_DTO;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return DTOEditPlugin.INSTANCE;
	}

}
