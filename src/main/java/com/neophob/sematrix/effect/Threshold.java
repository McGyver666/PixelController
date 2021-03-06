/**
 * Copyright (C) 2011 Michael Vogt <michu@neophob.com>
 *
 * This file is part of PixelController.
 *
 * PixelController is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PixelController is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with PixelController.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.neophob.sematrix.effect;

import java.util.Random;

import com.neophob.sematrix.glue.Collector;
import com.neophob.sematrix.glue.ShufflerOffset;
import com.neophob.sematrix.resize.Resize.ResizeName;

/**
 * The Class Threshold.
 *
 * @author michu
 */
public class Threshold extends Effect {

	/** The threshold. */
	private short threshold;
	
	/**
	 * Instantiates a new threshold.
	 *
	 * @param controller the controller
	 */
	public Threshold(PixelControllerEffect controller) {
		super(controller, EffectName.THRESHOLD, ResizeName.QUALITY_RESIZE);
		this.threshold = 128;
	}

	/* (non-Javadoc)
	 * @see com.neophob.sematrix.effect.Effect#getBuffer(int[])
	 */
	public int[] getBuffer(int[] buffer) {
		int[] ret = new int[buffer.length];
		
		short cr,cg,cb;
		int col;

		for (int i=0; i<buffer.length; i++){
			col = buffer[i];
    		cr=(short) ((col>>16)&255);
    		cg=(short) ((col>>8)&255);
    		cb=(short) ( col&255);
    		
    		if (cr<this.threshold) {
    			cr=0; 
    		} else {
    			cr=255;
    		}
    		if (cg<this.threshold) {
    			cg=0; 
    		} else {
    			cg=255;
    		}
    		if (cb<this.threshold) {
    			cb=0; 
    		} else {
    			cb=255;
    		}
    		
    		ret[i]= (cr << 16) | (cg << 8) | cb;
		}
		return ret;
	}
	
	/* (non-Javadoc)
	 * @see com.neophob.sematrix.effect.Effect#shuffle()
	 */
	@Override
	public void shuffle() {
		if (Collector.getInstance().getShufflerSelect(ShufflerOffset.THRESHOLD_VALUE)) {
			this.threshold = (short)new Random().nextInt(255);
		}		
	}
	
	
	/**
	 * Sets the threshold.
	 *
	 * @param threshold the new threshold
	 */
	public void setThreshold(int threshold) {
		this.threshold = (short)threshold;
	}	
	
	/**
	 * Gets the threshold.
	 *
	 * @return the threshold
	 */
	public short getThreshold() {
		return threshold;
	}
	
}
