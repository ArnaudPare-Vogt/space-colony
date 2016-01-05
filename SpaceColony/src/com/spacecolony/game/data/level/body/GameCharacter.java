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

import com.spacecolony.game.data.level.Level;
import com.spacecolony.game.data.level.Tile;
import com.spacecolony.game.data.level.TileInfo;
import com.spacecolony.game.graphics.Sprite;
import com.spacecolony.game.util.Coordinate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.Path;

/**
 *
 * @author 1448607
 */
public class GameCharacter extends Body {

    public static final int ORIENTATION_FACE = 0;
    public static final int ORIENTATION_LEFT = 1;
    public static final int ORIENTATION_RIGHT = 2;
    public static final int ORIENTATION_BACK = 3;

    public static final int CHAR_DEFAULT_SIZE = 8;
    public static final float SELECTED_CHARACTER_CIRCLE_DIAM = 8;
    public static final float SELECTED_CHARACTER_NODE_CIRCLE_DIAM = 2;

    private static final float SPEED = 32;//in pixels/second
    
    public static Mover NOT_IN_SPACE_MOVER = new Mover() {};
    public static Mover IN_SPACE_MOVER = new Mover() {};

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

    private Level station;
    private ArrayList<Coordinate> goals = new ArrayList<>();
    private Vector2f goalPos;
    private boolean walkingSomewhere = false;
    private boolean working = false;

    private int dir = 0;

    private boolean selected;
    
    private AStarPathFinder pathFinder;
    

    public GameCharacter(float x, float y, Random characterGenerator, Level station) {
        this.station = station;
        setPos(x - CHAR_DEFAULT_SIZE / 2, y - CHAR_DEFAULT_SIZE / 2);
        setSize(CHAR_DEFAULT_SIZE, CHAR_DEFAULT_SIZE);

        pathFinder = new AStarPathFinder(station, 100, false);
        
        color = POSSIBLE_COLORS[characterGenerator.nextInt(POSSIBLE_COLORS.length)];
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(spriteDirs[dir].getImage(0), pos.x, pos.y);
        g.drawImage(spriteDirsMask[dir].getImage(0), pos.x, pos.y, color);

        if (selected) {
            g.setColor(Color.red);
            Vector2f cent = getCenter();
            g.drawOval(cent.x - SELECTED_CHARACTER_CIRCLE_DIAM / 2, cent.y - SELECTED_CHARACTER_CIRCLE_DIAM / 2, SELECTED_CHARACTER_CIRCLE_DIAM, SELECTED_CHARACTER_CIRCLE_DIAM);

            if (!goals.isEmpty()) {
                Vector2f lastPos = size.copy().scale(1 / 2.f).add(pos);
                for (int i = 0; i < goals.size(); i++) {
                    Vector2f newPos = station.centerOnCell(goals.get(i).x, goals.get(i).y);
                    g.drawLine(lastPos.x, lastPos.y, newPos.x, newPos.y);
                    g.drawOval(newPos.x - SELECTED_CHARACTER_NODE_CIRCLE_DIAM / 2, newPos.y - SELECTED_CHARACTER_NODE_CIRCLE_DIAM / 2, SELECTED_CHARACTER_NODE_CIRCLE_DIAM, SELECTED_CHARACTER_NODE_CIRCLE_DIAM);
                    lastPos = newPos;
                }
            }
        }
    }

    @Override
    public void update(float dt) {
        if (walkingSomewhere) {
            goalPos = station.centerOnCell(goals.get(0).x, goals.get(0).y).sub(size.copy().scale(.5f));

            Vector2f dp = goalPos.copy().sub(pos);

            if (Math.abs(dp.getX()) < Math.abs(dp.getY())) {
                if (dp.getY() > 0) {
                    dir = 0;
                } else {
                    dir = 3;
                }
            } else {
                if (dp.getX() > 0) {
                    dir = 2;
                } else {
                    dir = 1;
                }
            }

            if (dp.lengthSquared() < SPEED * dt) {
                setPos(station.centerOnCell(goals.get(0).x, goals.get(0).y).sub(size.copy().scale(.5f)));
                nextGoal();
            } else {
                addToPos(dp.normalise().scale(SPEED * dt));
            }
        } else if (!working) {
            Tile tileStandingOn = station.getNearestTile(pos).getT();
            tileStandingOn.setOccupied(true);
            if (tileStandingOn.getMachine() != null) {
                if (tileStandingOn.getMachine().isManned()) {
                    setWorking(true);
                    dir = tileStandingOn.getMachine().getOrientation();
                }
            }
        }
    }

    private void setWorking(boolean w) {
        station.getNearestTile(pos).getT().setWorked(w);
        working = w;
    }

    private void quitTile() {
        station.getNearestTile(pos).getT().setOccupied(false);
    }

    public void goTo(int x, int y) {
        if (!station.isInBounds(x, y)) {
            return;
        }

        TileInfo currentTile = station.getNearestTile(pos);
        Coordinate pos;
        if (goals.isEmpty()) {
            pos = new Coordinate(currentTile.getX(), currentTile.getY());
        } else {
            pos = goals.get(goals.size() - 1);
        }

        Coordinate goal = new Coordinate(x, y);

        ArrayList<Coordinate> path = null;
        
        if (station.getTile(x, y) != null) {
            if (station.getTile(x, y).isOccupied()) {
                return;//TODO possibly send an error message or walk close?
            }
            path = findGoals(pos, goal, NOT_IN_SPACE_MOVER);
        } else {//we are atemplting to go in space!
            ArrayList<Coordinate> allHatches = station.getAllHatches();
            if (allHatches.isEmpty()) {
                return;//there is no doors to go to space!
            }
            ArrayList<ArrayList<Coordinate>> paths = new ArrayList<>();

            for (Iterator<Coordinate> iterator = allHatches.iterator(); iterator.hasNext();) {
                Coordinate next = iterator.next();
                ArrayList<Coordinate> curPath = findGoals(pos, next, NOT_IN_SPACE_MOVER);
                if (curPath == null) {
                    iterator.remove();
                } else {
                    paths.add(curPath);
                }
            }

            if (allHatches.isEmpty()) {
                return;
            }

            ArrayList<Coordinate> shortestPath = null;
            int index = 0;
            for (int i = 0; i < allHatches.size(); i++) {
                ArrayList<Coordinate> curPath = findGoals(allHatches.get(i), goal, IN_SPACE_MOVER);
                if (shortestPath == null) {
                    shortestPath = curPath;
                } else if (shortestPath.size() > curPath.size()) {
                    shortestPath = curPath;
                    index = i;
                }
            }
            
            path = shortestPath;
            path.addAll(0, paths.get(index));

        }

        if (path != null) {
            goals.addAll(path);
            walkingSomewhere = true;
            setWorking(false);
            quitTile();
        }
    }

    private void nextGoal() {
        goals.remove(0);
        if (goals.size() > 0) {
        } else {
            walkingSomewhere = false;
        }
    }

    private ArrayList<Coordinate> findGoals(Coordinate currentPos, Coordinate finalGoal, Mover mover) {
        ArrayList<Coordinate> coords = new ArrayList<>();

        Path p = pathFinder.findPath(mover, currentPos.x, currentPos.y, finalGoal.x, finalGoal.y);
        if (p == null) {
            return null;
        } else {
            for (int i = 0; i < p.getLength(); i++) {
                coords.add(new Coordinate(p.getX(i), p.getY(i)));
            }
        }

        Coordinate lastCoord = null;
        for (Iterator<Coordinate> it = coords.iterator(); it.hasNext();) {
            Coordinate coord = it.next();
            if (lastCoord != null) {
                int i = coords.indexOf(coord);
                if (i + 1 < coords.size()) {
                    Coordinate nextCoord = coords.get(i + 1);
                    int dxLast = coord.x - lastCoord.x;
                    int dyLast = coord.y - lastCoord.y;
                    int dxNext = nextCoord.x - coord.x;
                    int dyNext = nextCoord.y - coord.y;
                    boolean caseTR = ((dxLast == -1) && (dyLast == 0) && (dxNext == 0) && (dyNext == -1))
                            || ((dxLast == 0) && (dyLast == 1) && (dxNext == 1) && (dyNext == 0));
                    boolean caseTL = ((dxLast == 0) && (dyLast == 1) && (dxNext == -1) && (dyNext == 0))
                            || ((dxLast == 1) && (dyLast == 0) && (dxNext == 0) && (dyNext == -1));
                    boolean caseBR = ((dxLast == -1) && (dyLast == 0) && (dxNext == 0) && (dyNext == 1))
                            || ((dxLast == 0) && (dyLast == -1) && (dxNext == 1) && (dyNext == 0));
                    boolean caseBL = ((dxLast == 1) && (dyLast == 0) && (dxNext == 0) && (dyNext == 1))
                            || ((dxLast == 0) && (dyLast == -1) && (dxNext == -1) && (dyNext == 0));
                    if ((caseTR && (!station.blocked(pathFinder, coord.x + 1, coord.y - 1)))
                            || (caseTL && (!station.blocked(pathFinder, coord.x - 1, coord.y - 1)))
                            || (caseBR && (!station.blocked(pathFinder, coord.x + 1, coord.y + 1)))
                            || (caseBL && (!station.blocked(pathFinder, coord.x - 1, coord.y + 1)))) {
                        it.remove();
                        continue;
                    }
                }
            }
            lastCoord = coord;
        }

        return coords;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
