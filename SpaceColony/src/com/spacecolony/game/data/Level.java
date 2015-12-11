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
package com.spacecolony.game.data;

import com.spacecolony.game.graphics.Sprite;
import com.spacecolony.game.graphics.SpriteSheet;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author 1448607
 */
public class Level {

    private int width, height;

    private Tile[][] tiles;

    private TileType tt;

    public Level(int width, int height, SpriteSheet ss) {
        this.width = width;
        this.height = height;

        tt = new TileType(new Sprite(ss, 0, 0, 16, 16));

        tiles = new Tile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j] = new Tile(tt);
            }
        }
    }

    public void render(Graphics g, Vector2f pos, Vector2f port) {
        int bx = -(int) pos.x / 16;
        int by = -(int) pos.y / 16;
        int ex = bx + (int) port.x / 16 + 2;
        int ey = by + (int) port.y / 16 + 2;
        
        ex = ex<width?ex:width;
        ey = ey<height?ey:height;
        
        for (int i = bx; i < ex; i++) {
            for (int j = by; j < ey; j++) {
                g.drawImage(tiles[i][j].getTileType().getSprite().getImage(), i * 16, j * 16);
            }
        }
    }
}
