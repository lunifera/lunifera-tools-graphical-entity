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
package org.lunifera.dsl.semantic.entity.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.xtext.xbase.XbaseFactory;

import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotationsFactory;

import org.lunifera.dsl.semantic.common.types.LunTypesPackage;

import org.lunifera.dsl.semantic.entity.LEntityAttribute;
import org.lunifera.dsl.semantic.entity.LunEntityPackage;

/**
 * This is the item provider adapter for a {@link org.lunifera.dsl.semantic.entity.LEntityAttribute} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class LEntityAttributeItemProvider extends LEntityFeatureItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LEntityAttributeItemProvider(AdapterFactory adapterFactory) {
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

			addIdPropertyDescriptor(object);
			addUuidPropertyDescriptor(object);
			addVersionPropertyDescriptor(object);
			addLazyPropertyDescriptor(object);
			addCascadingPropertyDescriptor(object);
			addTransientPropertyDescriptor(object);
			addDerivedPropertyDescriptor(object);
			addTypePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Id feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_LAttribute_id_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_LAttribute_id_feature", "_UI_LAttribute_type"),
				 LunTypesPackage.Literals.LATTRIBUTE__ID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Uuid feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUuidPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_LAttribute_uuid_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_LAttribute_uuid_feature", "_UI_LAttribute_type"),
				 LunTypesPackage.Literals.LATTRIBUTE__UUID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Version feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addVersionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_LAttribute_version_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_LAttribute_version_feature", "_UI_LAttribute_type"),
				 LunTypesPackage.Literals.LATTRIBUTE__VERSION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Lazy feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLazyPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_LAttribute_lazy_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_LAttribute_lazy_feature", "_UI_LAttribute_type"),
				 LunTypesPackage.Literals.LATTRIBUTE__LAZY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Cascading feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCascadingPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_LAttribute_cascading_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_LAttribute_cascading_feature", "_UI_LAttribute_type"),
				 LunTypesPackage.Literals.LATTRIBUTE__CASCADING,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Transient feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTransientPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_LAttribute_transient_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_LAttribute_transient_feature", "_UI_LAttribute_type"),
				 LunTypesPackage.Literals.LATTRIBUTE__TRANSIENT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Derived feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDerivedPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_LAttribute_derived_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_LAttribute_derived_feature", "_UI_LAttribute_type"),
				 LunTypesPackage.Literals.LATTRIBUTE__DERIVED,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_LAttribute_type_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_LAttribute_type_feature", "_UI_LAttribute_type"),
				 LunTypesPackage.Literals.LATTRIBUTE__TYPE,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
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
			childrenFeatures.add(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION);
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
	 * This returns LEntityAttribute.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/LEntityAttribute"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((LEntityAttribute)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_LEntityAttribute_type") :
			getString("_UI_LEntityAttribute_type") + " " + label;
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

		switch (notification.getFeatureID(LEntityAttribute.class)) {
			case LunEntityPackage.LENTITY_ATTRIBUTE__ID:
			case LunEntityPackage.LENTITY_ATTRIBUTE__UUID:
			case LunEntityPackage.LENTITY_ATTRIBUTE__VERSION:
			case LunEntityPackage.LENTITY_ATTRIBUTE__LAZY:
			case LunEntityPackage.LENTITY_ATTRIBUTE__CASCADING:
			case LunEntityPackage.LENTITY_ATTRIBUTE__TRANSIENT:
			case LunEntityPackage.LENTITY_ATTRIBUTE__DERIVED:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case LunEntityPackage.LENTITY_ATTRIBUTE__DERIVED_GETTER_EXPRESSION:
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
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XAnnotationsFactory.eINSTANCE.createXAnnotation()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXIfExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXSwitchExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXBlockExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXVariableDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXMemberFeatureCall()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXFeatureCall()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXConstructorCall()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXBooleanLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXNullLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXNumberLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXStringLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXListLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXSetLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXClosure()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXCastedExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXBinaryOperation()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXUnaryOperation()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXPostfixOperation()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXForLoopExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXBasicForLoopExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXDoWhileExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXWhileExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXTypeLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXInstanceOfExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXThrowExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXTryCatchFinallyExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXAssignment()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXReturnExpression()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LATTRIBUTE__DERIVED_GETTER_EXPRESSION,
				 XbaseFactory.eINSTANCE.createXSynchronizedExpression()));
	}

}
