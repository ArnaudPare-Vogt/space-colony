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
package com.spacecolony.game.data.level;

import com.spacecolony.game.graphics.ConnectedSprite;
import com.spacecolony.game.graphics.Sprite;

/**
 *
 * @author root
 */
public class ConnectedTileType extends TileType{

    private ConnectedSprite sprite;
    
    public ConnectedTileType(ConnectedSprite sprite) {
        super(sprite.getSprite(0));
        this.sprite = sprite;
    }

    @Override
    public Sprite getSprite(int meta) {
        return sprite.getSprite(meta);
    }

    public ConnectedSprite getConnectedSprite() {
        return sprite;
    }
    
}
