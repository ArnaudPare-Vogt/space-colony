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
import java.util.Arrays;

/**
 *
 * @author root
 */
public class ConnectedTile extends Tile {

    int metaData = 0;
    
    public ConnectedTile(TileType tileType) {
        super(tileType);
    }

    @Override
    public Sprite getSprite() {
        return tileType.getSprite(metaData);
    }

    @Override
    public void refresh(Tile[] adjTiles) {
        boolean[] near = new boolean[adjTiles.length];
        for (int i = 0; i < adjTiles.length; i++) {
            near[i] = adjTiles[i] != null && adjTiles[i].getTileType() == tileType;
        }
        metaData = ConnectedSprite.getMetaFromNearTable(near);
    }
    
}
