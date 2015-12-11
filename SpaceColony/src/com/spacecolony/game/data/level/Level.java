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

import com.spacecolony.game.data.level.body.Body;
import com.spacecolony.game.data.level.body.GameCharacter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author 1448607
 */
public class Level {

    public static final int TILE_RES = 16;

    private int width, height;

    private Tile[][] tiles;

    private List<Body> bodies = new ArrayList<>();

    public Level(int width, int height, Random rnd) {
        this.width = width;
        this.height = height;

        tiles = new Tile[width][height];

        int x = rnd.nextInt(width),
                y = rnd.nextInt(height);

        tiles[x][y] = new Tile(TileType.DEFAULT_TYPE);

        bodies.add(new GameCharacter((x + .5f) * TILE_RES, (y + .5f) * TILE_RES, rnd));
    }

    public void render(Graphics g, Vector2f pos, Vector2f port) {
        int bx = (int) pos.x / TILE_RES;
        int by = (int) pos.y / TILE_RES;
        int ex = bx + (int) port.x / TILE_RES + 2;
        int ey = by + (int) port.y / TILE_RES + 2;

        ex = ex < width ? ex : width;
        ey = ey < height ? ey : height;

        for (int i = bx; i < ex; i++) {
            for (int j = by; j < ey; j++) {
                if (tiles[i][j] != null) {
                    g.drawImage(tiles[i][j].getSprite().getImage(), i * TILE_RES, j * TILE_RES);
                }
            }
        }

        for (Body body : bodies) {
            body.render(g);
        }
    }
    
    public void update(float dt){
        for (Body body : bodies) {
            body.update(dt);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public TileInfo getNearestTile(Vector2f floatPosition){
        int x = (int)(floatPosition.x / TILE_RES);
        int y = (int)(floatPosition.y / TILE_RES);
        if(x > 0 && y > 0 && x < width && y < height){
            return new TileInfo(tiles[x][y], x, y, x * TILE_RES, y * TILE_RES);
        }
        return null;
    }
}
