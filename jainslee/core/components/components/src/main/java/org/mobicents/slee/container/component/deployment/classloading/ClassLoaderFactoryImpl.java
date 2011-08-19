/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

/**
 * 
 */
package org.mobicents.slee.container.component.deployment.classloading;

import java.net.URL;

import javax.slee.ComponentID;

import org.mobicents.slee.container.component.classloading.ClassLoaderFactory;
import org.mobicents.slee.container.component.classloading.ComponentClassLoader;
import org.mobicents.slee.container.component.classloading.ReplicationClassLoader;
import org.mobicents.slee.container.component.classloading.URLClassLoaderDomain;

/**
 * @author martins
 * 
 */
public class ClassLoaderFactoryImpl implements ClassLoaderFactory {

	private final ClassLoadingConfiguration configuration;
	
	/**
	 * 
	 */
	public ClassLoaderFactoryImpl(ClassLoadingConfiguration configuration) {
		this.configuration = configuration;
	}
	
	/**
	 * @return the configuration
	 */
	public ClassLoadingConfiguration getConfiguration() {
		return configuration;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.mobicents.slee.core.component.classloading.ClassLoaderFactory#
	 * newClassLoaderDomain(java.net.URL[], java.lang.ClassLoader)
	 */
	public URLClassLoaderDomain newClassLoaderDomain(URL[] urls,
			ClassLoader sleeClassLoader) {
		return new URLClassLoaderDomainImpl(urls, sleeClassLoader,
				configuration.isLoadClassesFirstFromAS());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.mobicents.slee.core.component.classloading.ClassLoaderFactory#
	 * newComponentClassLoader(javax.slee.ComponentID,
	 * org.mobicents.slee.core.component.classloading.URLClassLoaderDomain)
	 */
	public ComponentClassLoader newComponentClassLoader(
			ComponentID componentID, URLClassLoaderDomain parent) {
		return new ComponentClassLoaderImpl(componentID, parent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.mobicents.slee.core.component.classloading.ClassLoaderFactory#
	 * newReplicationClassLoader(java.lang.ClassLoader)
	 */
	public ReplicationClassLoader newReplicationClassLoader(
			ClassLoader sleeClassLoader) {
		return new ReplicationClassLoaderImpl(sleeClassLoader,
				configuration.isLoadClassesFirstFromAS());
	}

}