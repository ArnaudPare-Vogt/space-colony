/*
 * Copyright (C) 2015 1448607
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.spacecolony.game.data.level.machines;

import com.spacecolony.game.data.level.ResourceManager;
import com.spacecolony.game.data.level.body.GameCharacter;
import com.spacecolony.game.graphics.Sprite;
import org.newdawn.slick.Image;

/**
 *
 * @author 1448607
 */
public class Machine {
    
    
    public static final Machine COMMAND_POST = new Machine("Command post", 100, true, false, Sprite.Machines.COMMAND_POST, Sprite.Machines.TopLayer.COMMAND_POST, GameCharacter.ORIENTATION_LEFT, (rm)->{
        rm.stabilityAugment(1);
    });
    
    public static final Machine O2_GENERATOR = new Machine("O2 generator", 70, false, false, Sprite.Machines.O2_GENERATOR, Sprite.Machines.TopLayer.O2_GENERATOR, -1, (rm)->{
        rm.stabilityAugment(1);
    });
    
    private String name;
    private int totalHP;
    private boolean manned;
    private boolean droned;
    private Sprite sprite;
    private Sprite topSprite;
    private int orientation;
    
    private ResourceProductor productor;

    public Machine(String name, int totalHP, boolean manned, boolean droned, Sprite sprite, Sprite topSprite, int orientation, ResourceProductor productor) {
        this.name = name;
        this.totalHP = totalHP;
        this.manned = manned;
        this.droned = droned;
        this.sprite = sprite;
        this.topSprite = topSprite;
        this.orientation = orientation;
        this.productor = productor;
    }
    
    public String getName() {
        return name;
    }
    
    public Image getImage(){
        return sprite.getImage(0);
    }

    public Image getTopImage() {
        return topSprite.getImage(0);
    }

    public int getOrientation() {
        return orientation;
    }

    public ResourceProductor getProductor() {
        return productor;
    }

    public boolean isManned() {
        return manned;
    }
}
