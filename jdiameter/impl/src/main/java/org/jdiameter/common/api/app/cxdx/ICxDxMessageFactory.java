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

package org.jdiameter.common.api.app.cxdx;

import org.jdiameter.api.Answer;
import org.jdiameter.api.Request;
import org.jdiameter.api.app.AppAnswerEvent;
import org.jdiameter.api.app.AppRequestEvent;

/**
 * Message Factory for Diameter Cx/Dx application.
 * 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public interface ICxDxMessageFactory {

  /**
   * Creates a Location-Info-Request event.
   * 
   * @param request the request to create the LIR from
   * @return an AppRequestEvent wrapping the LIR
   */
  AppRequestEvent createLocationInfoRequest(Request request);

  /**
   * Creates a User-Authorization-Request event.
   * 
   * @param request the request to create the UAR from
   * @return an AppRequestEvent wrapping the UAR
   */
  AppRequestEvent createUserAuthorizationRequest(Request request);

  /**
   * Creates a Server-Assignment-Request event.
   * 
   * @param request the request to create the SAR from
   * @return an AppRequestEvent wrapping the SAR
   */
  AppRequestEvent createServerAssignmentRequest(Request request);

  /**
   * Creates a Registration-Termination-Request event.
   * 
   * @param request the request to create the RTR from
   * @return an AppRequestEvent wrapping the RTR
   */
  AppRequestEvent createRegistrationTerminationRequest(Request request);

  /**
   * Creates a Multimedia-Auth-Request event.
   * 
   * @param request the request to create the MAR from
   * @return an AppRequestEvent wrapping the MAR
   */
  AppRequestEvent createMultimediaAuthRequest(Request request);

  /**
   * Creates a Push-Profile-Request event.
   * 
   * @param request the request to create the PPR from
   * @return an AppRequestEvent wrapping the PPR
   */
  AppRequestEvent createPushProfileRequest(Request request);

  /**
   * Creates a Push-Profile-Answer event.
   * 
   * @param answer the answer to create the PPA from
   * @return an AppAnswerEvent wrapping the PPA
   */
  AppAnswerEvent createPushProfileAnswer(Answer answer);

  /**
   * Creates a Location-Info-Answer event.
   * 
   * @param answer the answer to create the LIA from
   * @return an AppAnswerEvent wrapping the LIA
   */
  AppAnswerEvent createLocationInfoAnswer(Answer answer);

  /**
   * Creates a User-Authorization-Answer event.
   * 
   * @param answer the answer to create the UAA from
   * @return an AppAnswerEvent wrapping the UAA
   */
  AppAnswerEvent createUserAuthorizationAnswer(Answer answer);

  /**
   * Creates a Server-Assignment-Answer event.
   * 
   * @param answer the answer to create the SAA from
   * @return an AppAnswerEvent wrapping the SAA
   */
  AppAnswerEvent createServerAssignmentAnswer(Answer answer);

  /**
   * Creates a Registration-Termination-Answer event.
   * 
   * @param answer the answer to create the RTA from
   * @return an AppAnswerEvent wrapping the RTA
   */
  AppAnswerEvent createRegistrationTerminationAnswer(Answer answer);

  /**
   * Creates a Multimedia-Auth-Answer event.
   * 
   * @param answer the answer to create the MAA from
   * @return an AppAnswerEvent wrapping the MAA
   */
  AppAnswerEvent createMultimediaAuthAnswer(Answer answer);

  /**
   * Returns the Application-Id that this message factory is related to
   * 
   * @return the Application-Id value
   */
  long getApplicationId();

}