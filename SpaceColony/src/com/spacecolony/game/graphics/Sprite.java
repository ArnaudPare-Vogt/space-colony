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

    public static final Sprite DEFAULT_TEST = new Sprite(SpriteSheet.TEST, 0, 0, 16, 16);

    public static class Chars{
        public static class Default{
            public static final Sprite F = new Sprite(SpriteSheet.CHARACTERS, 0, 0, 8, 8);
            public static final Sprite R = new Sprite(SpriteSheet.CHARACTERS, 8, 0, 8, 8);
            public static final Sprite B = new Sprite(SpriteSheet.CHARACTERS, 0, 8, 8, 8);
            public static final Sprite L = new Sprite(SpriteSheet.CHARACTERS, 8, 8, 8, 8);

            public static final Sprite F_M = new Sprite(SpriteSheet.CHARACTERS, 16, 0, 8, 8);
            public static final Sprite R_M = new Sprite(SpriteSheet.CHARACTERS, 16 + 8, 0, 8, 8);
            public static final Sprite B_M = new Sprite(SpriteSheet.CHARACTERS, 16, 8, 8, 8);
            public static final Sprite L_M = new Sprite(SpriteSheet.CHARACTERS, 16 + 8, 8, 8, 8);
        }
    }
    
    public static class Machines{
        public static final Sprite COMMAND_POST = new Sprite(SpriteSheet.MACHINES, 0, 0, 16, 16);
        public static final Sprite O2_GENERATOR = new Sprite(SpriteSheet.MACHINES, 0, 16, 16, 16);
        public static class TopLayer{
            public static final Sprite COMMAND_POST = new Sprite(SpriteSheet.MACHINES, 16, 0, 16, 16);
            public static final Sprite O2_GENERATOR = new Sprite(SpriteSheet.MACHINES, 16, 16, 16, 16);
        }
    }
    

    private Image image;

    public Sprite(SpriteSheet ss, int x, int y, int w, int h) {
        this.image = ss.getSubImage(x, y, w, h);
    }

    public Image getImage(int meta) {
        return image;
    }

}
