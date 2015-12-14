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
package com.spacecolony.game.data.level.pathfinding;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 1448607
 */
public class Node implements Comparable<Node> {

    private int x;
    private int y;

    private float cost;
    private Node parent;
    private float heuristic;

    private int depth;

    private List<Node> neighbors = new ArrayList<>();

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the perent of the node
     *
     * @param parent the parent
     * @return the current depth thet we reached while searching
     */
    public int setParent(Node parent) {
        depth = parent.depth + 1;
        this.parent = parent;
        return depth;
    }

    /**
     * Used to sort the node lists
     *
     * @see Comparable#compareTo(Object)
     * @param o the node to compate to
     * @return an integer describing the place of the node relatively to o
     */
    @Override
    public int compareTo(Node o) {
        float f = heuristic + cost;
        float of = o.heuristic + o.cost;

        if (f < of) {
            return -1;
        } else if (f > of) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Resets the node to find another path
     */
    public void reset() {
        cost = 0;
        depth = 0;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }
    
    public void addNeighbor(Node neighbor){
        neighbors.add(neighbor);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * @return 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "[Node " + x + "," + y + "]";
    }

}
