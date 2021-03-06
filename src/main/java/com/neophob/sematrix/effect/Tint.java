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
 * The Class Tint.
 */
public class Tint extends Effect {

	/** The b. */
	private int r,g,b;
	
	/**
	 * Instantiates a new tint.
	 *
	 * @param controller the controller
	 */
	public Tint(PixelControllerEffect controller) {
		super(controller, EffectName.TINT, ResizeName.QUALITY_RESIZE);
		r=255;
		g=255;
		b=255;
	}

	/**
	 * update tint color.
	 *
	 * @param r the r
	 * @param g the g
	 * @param b the b
	 */
	public void setColor(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
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
    		cr = (short) ((col>>16)&255);
    		cr = (short)(cr*r/255);
    		cg = (short) ((col>>8)&255);
    		cg = (short)(cg*g/255);
    		cb = (short) (col&255);
    		cb = (short)(cb*b/255);
    		
    		ret[i]= (cr << 16) | (cg << 8) | cb;
		}
		return ret;

	}

	/**
	 * Gets the r.
	 *
	 * @return the r
	 */
	public int getR() {
		return r;
	}

	/**
	 * Gets the g.
	 *
	 * @return the g
	 */
	public int getG() {
		return g;
	}

	/**
	 * Gets the b.
	 *
	 * @return the b
	 */
	public int getB() {
		return b;
	}

	/* (non-Javadoc)
	 * @see com.neophob.sematrix.effect.Effect#shuffle()
	 */
	@Override
	public void shuffle() {
		if (Collector.getInstance().getShufflerSelect(ShufflerOffset.TINT)) {
			Random rand = new Random();
			r = rand.nextInt(255);
			g = rand.nextInt(255);
			b = rand.nextInt(255);					
		}
	}
	

	
}
