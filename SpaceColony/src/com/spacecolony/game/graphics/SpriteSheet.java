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

import com.spacecolony.game.util.ExceptionCallback;
import com.spacecolony.game.util.ExceptionThrower;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author 1448607
 */
public class SpriteSheet{
    
    public static final SpriteSheet TEST = new SpriteSheet("Images/Test.png");
    
    private static final ExceptionThrower THROWER = new ExceptionThrower();

    private Image image;
    
    public SpriteSheet(String fileName) {
        load(fileName);
    }
    
    private void load(String fileName){
        try {
            image = new Image(fileName);
            image.setFilter(Image.FILTER_NEAREST);
        } catch (SlickException ex) {
            THROWER.throwException(ex);
        }
    }
    
    public Image getSubImage(int x, int y, int w, int h){
        return image.getSubImage(x, y, w, h);
    }
    
    public static void addExceptionCallback(ExceptionCallback e){
        THROWER.addCallback(e);
    }
}
