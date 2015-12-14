/*
 * Copyright (C) 2015 root
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

import java.util.Arrays;

/**
 *
 * @author root
 */
public class ConnectedSprite {
    
    public static final ConnectedSprite DEFAULT_TEST = new ConnectedSprite(SpriteSheet.TEST, 16);

    private Sprite[] images = new Sprite[41];

    public ConnectedSprite(SpriteSheet ss, int size) {
        int rowNum = ss.getWidth() / size;

        int i = 0;
        int k = 0;
        while (i < images.length) {
            for (int j = 0; (j < rowNum) && i < images.length; j++) {
                images[i] = new Sprite(ss, j * size, k * size, size, size);
                i++;
            }
            k++;
        }
    }

    public Sprite getSprite(int i) {
        return images[i];
    }

    public static int getMetaFromNearTable(boolean[] near) {
        boolean tl = near[0];
        boolean tm = near[1];
        boolean tr = near[2];
        boolean ml = near[3];
        boolean mr = near[5];
        boolean bl = near[6];
        boolean bm = near[7];
        boolean br = near[8];
        
        if(tm){
            if(bm){
                if(mr){
                    if(ml){
                        return 15;
                    }
                    return 14;
                }else if(ml){
                    return 12;
                }
                return 5;
            }else if(mr){
                if(ml){
                    return 13;
                }
                if(tr){
                    return 19;
                }else{
                    return 10;
                }
            }else if(ml){
                if(tl){
                    return 18;
                }else{
                    return 9;
                }
            }
            return 4;
        }else if(mr){
            if(bm){
                if(ml){
                    return 11;
                }
                if(br){
                    return 16;
                }else{
                    return 7;
                }
            }else if(ml){
                return 6;
            }
            return 1;
        }else if(ml){
            if(bm){
                if(bl){
                    return 17;
                }else{
                    return 8;
                }
            }
            return 2;
        }else if(bm){
            return 3;
        }
        
        
        
        
        return 0;
    }
    
}
