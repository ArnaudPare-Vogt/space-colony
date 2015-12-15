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

import com.spacecolony.game.data.level.machines.Machine;
import com.spacecolony.game.util.Coordinate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

/**
 *
 * @author 1448607
 */
public class Level implements TileBasedMap {

    public static final int TILE_RES = 16;

    private int width, height;

    private Tile[][] tiles;

    public Level(int width, int height, Random rnd) {
        this.width = width;
        this.height = height;

        tiles = new Tile[width][height];

        int x = 10, y = 10;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                buildTile(x+i, y+j);
            }
        }
        
        tiles[x][y].setMachine(Machine.COMMAND_POST);

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
                    tiles[i][j].render(g, i * TILE_RES, j * TILE_RES);
                }
            }
        }
    }
    
    public void renderTop(Graphics g, Vector2f pos, Vector2f port) {
        int bx = (int) pos.x / TILE_RES;
        int by = (int) pos.y / TILE_RES;
        int ex = bx + (int) port.x / TILE_RES + 2;
        int ey = by + (int) port.y / TILE_RES + 2;

        ex = ex < width ? ex : width;
        ey = ey < height ? ey : height;

        for (int i = bx; i < ex; i++) {
            for (int j = by; j < ey; j++) {
                if (tiles[i][j] != null) {
                    tiles[i][j].renderTop(g, i * TILE_RES, j * TILE_RES);
                }
            }
        }
    }

    public void update(float dt, ResourceManager rm) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(tiles[i][j] != null){
                    if(tiles[i][j].isWorked() && tiles[i][j].getMachine() != null){
                        tiles[i][j].getMachine().getProductor().procuce(rm);
                    }
                }
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public TileInfo getNearestTile(Vector2f floatPosition) {
        int x = (int) (floatPosition.x / TILE_RES);
        int y = (int) (floatPosition.y / TILE_RES);
        if (x >= 0 && y >= 0 && x < width && y < height) {
            return new TileInfo(tiles[x][y], x, y, x * TILE_RES, y * TILE_RES);
        }
        return null;
    }

    public boolean isNearTiles(int x, int y) {
        Tile[] t = getNearTiles(x, y);
        boolean b = false;

        for (Tile t1 : t) {
            b = b || t1 != null;
        }

        return b;
    }

    public boolean isNextToTile(int x, int y) {
        return (getTile(x - 1, y) != null)
                || (getTile(x + 1, y) != null)
                || (getTile(x, y - 1) != null)
                || (getTile(x, y + 1) != null);
    }

    public final Tile[] getNearTiles(int x, int y) {
        Tile[] nearTiles = new Tile[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                nearTiles[i + j * 3] = getTile(x - 1 + i, y - 1 + j);
            }
        }
        nearTiles[4] = null;//no center tile
        return nearTiles;
    }

    public List<Coordinate> getNearbyCoords(Coordinate target) {
        List<Coordinate> coords = new ArrayList<>();
        if (getTile(target.x + 1, target.y) != null) {
            coords.add(new Coordinate(target.x + 1, target.y));
        }
        if (getTile(target.x, target.y + 1) != null) {
            coords.add(new Coordinate(target.x, target.y + 1));
        }
        if (getTile(target.x - 1, target.y) != null) {
            coords.add(new Coordinate(target.x - 1, target.y));
        }
        if (getTile(target.x, target.y - 1) != null) {
            coords.add(new Coordinate(target.x, target.y - 1));
        }
        return coords;
    }

    public float getMovCost(Coordinate t1, Coordinate t2) {
        return 1;
    }

    /**
     * Returns a tile at the given position, or null if there is none or if the
     * position is out of bounds
     *
     * @param x the x position
     * @param y the y position
     * @return the tile at the given position, or null if there is none or if
     * the position is out of bounds
     */
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return null;
        }
        return tiles[x][y];
    }

    /**
     * Builds a basic tile at position x,y
     *
     * @param x the x position of the tile
     * @param y the y position of the tile
     */
    public void buildTile(int x, int y) {
        if (isInBounds(x, y)) {
            tiles[x][y] = new ConnectedTile(TileType.DEFAULT_TYPE_CONNECTED);
        }
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Tile t = getTile(x + i, y + j);
                if (t != null) {
                    t.refresh(getNearTiles(x + i, y + j));
                }
            }
        }
    }

    private boolean isInBounds(int x, int y) {
        return (x >= 0 && y >= 0 && x < width && y < height);
    }

    public Vector2f centerOnCell(int x, int y) {
        return new Vector2f(x + .5f, y + .5f).scale(TILE_RES);
    }

    @Override
    public int getWidthInTiles() {
        return width;
    }

    @Override
    public int getHeightInTiles() {
        return height;
    }

    @Override
    public void pathFinderVisited(int x, int y) {
    }

    @Override
    public boolean blocked(PathFindingContext context, int tx, int ty) {
        return getTile(tx, ty) == null;
    }

    @Override
    public float getCost(PathFindingContext context, int tx, int ty) {
        return 1;
    }
}
