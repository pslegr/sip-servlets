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

package org.mobicents.media.server.ctrl.mgcp;

import jain.protocol.ip.mgcp.JainMgcpResponseEvent;
import jain.protocol.ip.mgcp.message.ModifyConnection;
import jain.protocol.ip.mgcp.message.ModifyConnectionResponse;
import jain.protocol.ip.mgcp.message.parms.CallIdentifier;
import jain.protocol.ip.mgcp.message.parms.ConnectionDescriptor;
import jain.protocol.ip.mgcp.message.parms.ConnectionIdentifier;
import jain.protocol.ip.mgcp.message.parms.EndpointIdentifier;
import jain.protocol.ip.mgcp.message.parms.ReturnCode;

import java.util.concurrent.Callable;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.mobicents.media.server.spi.Connection;
import org.mobicents.media.server.spi.ConnectionMode;
import org.mobicents.media.server.spi.Endpoint;

/**
 * 
 * @author amit bhayani
 *
 */
public class ModifyConnectionAction implements Callable {

    private static Logger logger = Logger.getLogger(ModifyConnectionAction.class);
    private ModifyConnection mdcx;
    private MgcpController controller;
    private MgcpUtils utils = new MgcpUtils();

    protected ModifyConnectionAction(MgcpController controller, ModifyConnection req) {
        this.controller = controller;
        this.mdcx = req;
    }

    public JainMgcpResponseEvent call() throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("Request TX= " + mdcx.getTransactionHandle() + ", CallID = " + mdcx.getCallIdentifier() + ", Mode=" + mdcx.getMode() + ", Endpoint = " + mdcx.getEndpointIdentifier() + ", SDP present = " + (mdcx.getRemoteConnectionDescriptor() != null));
        }

        ModifyConnectionResponse response = null;

        EndpointIdentifier endpointID = mdcx.getEndpointIdentifier();
        String localEndpoint = endpointID.getLocalEndpointName();

        if (localEndpoint.contains("*") || localEndpoint.contains("$")) {
            return reject(ReturnCode.Protocol_Error);
        }

        CallIdentifier callID = mdcx.getCallIdentifier();
        ConnectionIdentifier connectionID = mdcx.getConnectionIdentifier();
        ConnectionMode mode = null;

        Endpoint endpoint = null;
        try {
            endpoint = controller.getServer().lookup(localEndpoint, true);
        } catch (Exception e) {
            if (logger.isEnabledFor(Level.ERROR)) {
                logger.error("Failed on endpoint lookup: " + localEndpoint, e);
            }
            return reject(ReturnCode.Endpoint_Unknown);
        }

        ConnectionActivity connectionActivity = controller.getActivity(localEndpoint, connectionID.toString());

        Connection connection = connectionActivity.getMediaConnection();

        if (mdcx.getMode() != null) {
            mode = utils.getMode(mdcx.getMode());
            connection.setMode(mode);
        }


        ConnectionDescriptor remoteConnectionDescriptor = mdcx.getRemoteConnectionDescriptor();
        if (remoteConnectionDescriptor != null) {
            connection.setRemoteDescriptor(remoteConnectionDescriptor.toString());
        }

        response = new ModifyConnectionResponse(this, ReturnCode.Transaction_Executed_Normally);
        response.setTransactionHandle(mdcx.getTransactionHandle());
	response.setLocalConnectionDescriptor(new ConnectionDescriptor(connection.getLocalDescriptor()));
        logger.info("Response TX = " + response.getTransactionHandle() + ", Response: " + response.getReturnCode());
        return response;
    }

    private ModifyConnectionResponse reject(ReturnCode code) {
        ModifyConnectionResponse response = new ModifyConnectionResponse(this, code);
        response.setTransactionHandle(mdcx.getTransactionHandle());
        return response;
    }
}