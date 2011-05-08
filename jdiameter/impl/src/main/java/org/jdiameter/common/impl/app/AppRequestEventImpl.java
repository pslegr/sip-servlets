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

package org.jdiameter.common.impl.app;

import static org.jdiameter.api.Avp.DESTINATION_HOST;
import static org.jdiameter.api.Avp.DESTINATION_REALM;

import org.jdiameter.api.Avp;
import org.jdiameter.api.AvpDataException;
import org.jdiameter.api.Message;
import org.jdiameter.api.app.AppRequestEvent;

public class AppRequestEventImpl extends AppEventImpl implements AppRequestEvent {

  private static final long serialVersionUID = 1L;

  public AppRequestEventImpl(Message message) {
    super(message);
  }

  public String getDestinationHost() throws AvpDataException {
    Avp destHostAvp = message.getAvps().getAvp(DESTINATION_HOST);
    if (destHostAvp  != null) {
      return destHostAvp.getOctetString();
    }
    else {
      throw new AvpDataException("Avp DESTINATION_HOST not found");
    }
  }

  public String getDestinationRealm() throws AvpDataException {
    Avp destRealmAvp = message.getAvps().getAvp(DESTINATION_REALM);
    if (destRealmAvp != null) {
      return destRealmAvp.getOctetString();
    }
    else {
      throw new AvpDataException("Avp DESTINATION_REALM not found");
    }
  }
}