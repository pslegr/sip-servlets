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

package org.mobicents.media.server.impl.dsp.audio.g729;

/**
 * This is just a buffer that stores temporary info. FIFO/old info get's overwritten.
 * Used to compensate irregularly delivered RTP packets.
 * 
 * @author vralev
 *
 */
public class CircularBuffer {
	
	private byte[] buffer;
	private int cursor = 0;
	private int availableData = 0;
	
	public CircularBuffer(int size) {
		buffer = new byte[size];
	}
	
	synchronized public void addData(byte[] data) {
		boolean zeros = false;
		//for(int q=0; q<data.length; q++) if(data[q]!=0) zeros = false;
		if(!zeros) {
			for(int q=0; q<data.length; q++) {
				buffer[(cursor+q)%buffer.length] = data[q];
			}
			availableData += data.length;
			if(availableData > buffer.length) availableData = buffer.length;
		}
	}
	
	synchronized public byte[] getData(int size) {
		if(availableData<size) return null;
		
		byte[] data = new byte[size];
		for(int q=0; q<data.length; q++) {
			data[q] = buffer[(cursor+q)%buffer.length];
		}
		cursor = (cursor + data.length)%buffer.length;
		availableData -= size;
		return data;
	}

}