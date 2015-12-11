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
package com.spacecolony.game.graphics;

import org.newdawn.slick.Image;

/**
 *
 * @author 1448607
 */
public class Sprite {
    
    private Image image;

    public Sprite(SpriteSheet ss, int x, int y, int w, int h) {
        this.image = ss.getSubImage(x, y, w, h);
    }

    public Image getImage() {
        return image;
    }
    
}
