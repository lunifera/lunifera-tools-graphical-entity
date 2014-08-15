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

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.lunifera.dsl.semantic.common.types.LunTypesFactory;
import org.lunifera.dsl.semantic.common.types.LunTypesPackage;

import org.lunifera.dsl.semantic.common.types.provider.LAnnotationTargetItemProvider;

import org.lunifera.dsl.semantic.dto.LDtoFeature;
import org.lunifera.dsl.semantic.dto.LunDtoFactory;
import org.lunifera.dsl.semantic.dto.LunDtoPackage;

/**
 * This is the item provider adapter for a {@link org.lunifera.dsl.semantic.dto.LDtoFeature} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class LDtoFeatureItemProvider extends LAnnotationTargetItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LDtoFeatureItemProvider(AdapterFactory adapterFactory) {
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

			addNamePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_LFeature_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_LFeature_name_feature", "_UI_LFeature_type"),
				 LunTypesPackage.Literals.LFEATURE__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
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
			childrenFeatures.add(LunTypesPackage.Literals.LFEATURE__MULTIPLICITY);
			childrenFeatures.add(LunTypesPackage.Literals.LFEATURE__ANNOTATION_INFO);
			childrenFeatures.add(LunDtoPackage.Literals.LDTO_FEATURE__MAPPER);
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
	 * This returns LDtoFeature.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/LDtoFeature"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((LDtoFeature)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_LDtoFeature_type") :
			getString("_UI_LDtoFeature_type") + " " + label;
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

		switch (notification.getFeatureID(LDtoFeature.class)) {
			case LunDtoPackage.LDTO_FEATURE__NAME:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case LunDtoPackage.LDTO_FEATURE__MULTIPLICITY:
			case LunDtoPackage.LDTO_FEATURE__ANNOTATION_INFO:
			case LunDtoPackage.LDTO_FEATURE__MAPPER:
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
				(LunTypesPackage.Literals.LFEATURE__MULTIPLICITY,
				 LunTypesFactory.eINSTANCE.createLMultiplicity()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LFEATURE__ANNOTATION_INFO,
				 LunDtoFactory.eINSTANCE.createLDto()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LFEATURE__ANNOTATION_INFO,
				 LunDtoFactory.eINSTANCE.createLDtoFeature()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LFEATURE__ANNOTATION_INFO,
				 LunDtoFactory.eINSTANCE.createLDtoInheritedAttribute()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LFEATURE__ANNOTATION_INFO,
				 LunDtoFactory.eINSTANCE.createLDtoAttribute()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LFEATURE__ANNOTATION_INFO,
				 LunDtoFactory.eINSTANCE.createLDtoInheritedReference()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LFEATURE__ANNOTATION_INFO,
				 LunDtoFactory.eINSTANCE.createLDtoReference()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LFEATURE__ANNOTATION_INFO,
				 LunDtoFactory.eINSTANCE.createLDtoOperation()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LFEATURE__ANNOTATION_INFO,
				 LunTypesFactory.eINSTANCE.createLAnnotationTarget()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LFEATURE__ANNOTATION_INFO,
				 LunTypesFactory.eINSTANCE.createLType()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LFEATURE__ANNOTATION_INFO,
				 LunTypesFactory.eINSTANCE.createLScalarType()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LFEATURE__ANNOTATION_INFO,
				 LunTypesFactory.eINSTANCE.createLDataType()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LFEATURE__ANNOTATION_INFO,
				 LunTypesFactory.eINSTANCE.createLEnum()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LFEATURE__ANNOTATION_INFO,
				 LunTypesFactory.eINSTANCE.createLClass()));

		newChildDescriptors.add
			(createChildParameter
				(LunTypesPackage.Literals.LFEATURE__ANNOTATION_INFO,
				 LunTypesFactory.eINSTANCE.createLOperation()));

		newChildDescriptors.add
			(createChildParameter
				(LunDtoPackage.Literals.LDTO_FEATURE__MAPPER,
				 LunDtoFactory.eINSTANCE.createLDtoMapper()));
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
