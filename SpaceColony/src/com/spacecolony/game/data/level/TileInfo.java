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
package com.spacecolony.game.data.level;

import org.newdawn.slick.geom.Vector2f;

/**
 * Information about a tile
 * @author 1448607
 */
public class TileInfo {
    
    private Tile t;
    private int x,y;
    private Vector2f floatPos = new Vector2f();

    public TileInfo(Tile t, int x, int y, float sx, float sy) {
        this.t = t;
        this.x = x;
        this.y = y;
        floatPos.x = sx;
        floatPos.y = sy;
    }

    public Vector2f getFloatPos() {
        return floatPos;
    }

    public Tile getT() {
        return t;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
