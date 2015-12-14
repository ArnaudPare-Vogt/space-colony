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
package com.spacecolony.game.data.level.body;

import com.spacecolony.game.graphics.Sprite;
import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author 1448607
 */
public class GameCharacter extends Body {

    public static final int CHAR_DEFAULT_SIZE = 8;

    private Sprite[] spriteDirs = new Sprite[]{
        Sprite.Chars.Default.F,
        Sprite.Chars.Default.L,
        Sprite.Chars.Default.R,
        Sprite.Chars.Default.B
    };
    private Sprite[] spriteDirsMask = new Sprite[]{
        Sprite.Chars.Default.F_M,
        Sprite.Chars.Default.L_M,
        Sprite.Chars.Default.R_M,
        Sprite.Chars.Default.B_M
    };

    private static final Color[] POSSIBLE_COLORS = new Color[]{
        new Color(0x440000ff),
        new Color(0x44ff0000),
        new Color(0x4400ff00),
        new Color(0x44999900),
        new Color(0x44009999),
        new Color(0x44990099)
    };
    
    private Color color;

    public GameCharacter(float x, float y, Random characterGenerator) {
        setPos(x - CHAR_DEFAULT_SIZE / 2, y - CHAR_DEFAULT_SIZE / 2);
        setSize(CHAR_DEFAULT_SIZE, CHAR_DEFAULT_SIZE);
        
        color = POSSIBLE_COLORS[characterGenerator.nextInt(POSSIBLE_COLORS.length)];
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(spriteDirs[0].getImage(), pos.x, pos.y);
        g.drawImage(spriteDirsMask[0].getImage(), pos.x, pos.y, color);
    }

    @Override
    public void update(float dt) {
        
    }
}
