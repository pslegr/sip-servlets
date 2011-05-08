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

package org.mobicents.media.server;

import java.util.Map;
import java.util.Set;

import org.mobicents.media.server.resource.ChannelFactory;
import org.mobicents.media.server.spi.MediaType;

/**
 *
 * @author kulikov
 * @author amit bhayani
 */
public class ConnectionFactory {
	
	private ConnectionStateManager connectionStateManager;
    private ChannelFactory[] rxFactory = new ChannelFactory[2];
    private ChannelFactory[] txFactory = new ChannelFactory[2];
    
    public ChannelFactory[] getRxFactory() {
        return rxFactory;
    }

    public void setRxChannelFactory(Map<String, ChannelFactory> config) {
        if (config != null) {
            define(config, rxFactory);
        }
    }
    
    public ChannelFactory[] getTxFactory() {
        return txFactory;
    }

    public void setTxChannelFactory(Map<String, ChannelFactory> config) {
        if (config != null) {
            define(config, txFactory);
        }
    }

	public ConnectionStateManager getConnectionStateManager() {
		return connectionStateManager;
	}

	public void setConnectionStateManager(
			ConnectionStateManager connectionStateManager) {
		this.connectionStateManager = connectionStateManager;
	}

	private void define(Map<String, ChannelFactory> config, ChannelFactory[] factories) {
        Set<String> names = config.keySet();
        for (String name : names) {
            MediaType mediaType = MediaType.getInstance(name);
            ChannelFactory factory = config.get(name);
            factory.setMediaType(mediaType);
            factories[mediaType.getCode()] = factory;
        }
    }
}