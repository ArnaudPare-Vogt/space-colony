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

import com.spacecolony.game.graphics.ConnectedSprite;
import com.spacecolony.game.graphics.Sprite;
import com.spacecolony.game.graphics.SpriteSheet;

/**
 *
 * @author 1448607
 */
public class TileType {
    
    public static final TileType DEFAULT_TYPE = new TileType(Sprite.DEFAULT_TEST);
    public static final TileType DEFAULT_TYPE_CONNECTED = new TileType(ConnectedSprite.DEFAULT_TEST);
    
    
    private Sprite sprite;

    public TileType(Sprite sprite) {
        this.sprite = sprite;
    }
    
    public Sprite getSprite() {
        return sprite;
    }
}
