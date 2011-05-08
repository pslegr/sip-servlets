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

package org.jdiameter.server.impl;

import org.jdiameter.api.*;
import org.jdiameter.client.api.StackState;

public class StackImpl extends org.jdiameter.client.impl.StackImpl implements StackImplMBean{

    public MetaData getMetaData() {
        if (state == StackState.IDLE)
            throw new IllegalStateException("Meta data not defined");
        return (MetaData) assembler.getComponentInstance(MetaDataImpl.class);
    }

    public boolean isWrapperFor(Class<?> aClass) throws InternalException {
        if (aClass == MutablePeerTable.class)
            return true;
        else if (aClass == Network.class)
            return true;
        else if (aClass == OverloadManager.class)
            return true;
        else
            return super.isWrapperFor(aClass);
    }

    public <T> T unwrap(Class<T> aClass) throws InternalException {
        if (aClass == MutablePeerTable.class)
            return (T) assembler.getComponentInstance(aClass);
        if (aClass == Network.class)
            return (T) assembler.getComponentInstance(aClass);
        if (aClass == OverloadManager.class)
            return (T) assembler.getComponentInstance(aClass);
        else
            return (T) super.unwrap(aClass);
    }
}