/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.lunifera.org.eclipse.sirius.common.xtext.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.SavingPolicy;
import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.resource.XtextResource;

import com.google.common.collect.Maps;

/**
 * A saving policy dedicated to Xtext aware sessions. It actually work around
 * the bug #432795 by always adding options to ignore the concrete syntax
 * validation of Xtext while saving.
 * 
 * This policy only add the option and delegate the actual work to another one
 * passed as a constructor argument.
 * 
 * @author cbrun
 * 
 */
public class XtextSavingPolicy implements SavingPolicy {

    private final SavingPolicy delegate;

    /**
     * Create the saving policy.
     * 
     * @param delegate
     *            the delegate which is going to be called for the save
     *            operations, but with Xtext specific options.
     */
    public XtextSavingPolicy(SavingPolicy delegate) {
        this.delegate = delegate;
    }

    @Override
    public Collection<Resource> save(Iterable<Resource> resourcesToSave, Map<?, ?> options, IProgressMonitor monitor) {
        Map<Object, Object> newOptions = Maps.newHashMap();
        if (options != null) {
            newOptions.putAll(options);
        }
        newOptions.putAll(SaveOptions.newBuilder().noValidation().getOptions().toOptionsMap());
        return delegate.save(computeResourcesToSave(resourcesToSave), newOptions, monitor);
    }

    /**
     * Computes the set of resources to save. Subclasses only need to override
     * this method.
     *
     * @param scope
     *            the set of resources to consider.
     * @return a sub-set of the Resources in scope that must actually be saved
     *         on disk.
     */
    protected Iterable<Resource> computeResourcesToSave(Iterable<Resource> scope) {
        for (Iterator<Resource> iterator = scope.iterator(); iterator.hasNext();) {
            Resource resource = iterator.next();
            if (resource instanceof XtextResource) {
                String fileExt = resource.getURI().fileExtension();
                if (!fileExt.equals("entitymodel")) {
                    iterator.remove();
                }
            }
        }
        return scope;
    }

}
