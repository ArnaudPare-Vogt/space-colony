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

import com.spacecolony.game.util.Coordinate;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.tiled.TiledMap;

/**
 * A node structure that relies on a tilemap.
 *
 * @author 1448607
 */
public class NodeMapStructure {

    private TiledMap dataStructure;
    private List<Node> nodes = new ArrayList<>();

    public NodeMapStructure(TiledMap dataStructure) {
        this.dataStructure = dataStructure;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    /**
     * Gives a node that matches the x and y positions that are given, or null
     * if no node have the correct x and y
     *
     * @param position the position of the querries node
     * @return The node with the x and y positions, or null if there are none
     */
    public Node getNodeAtPos(Coordinate position) {
        for (Node node : nodes) {
            if (node.getX() == position.x
                    && node.getY() == position.y) {
                return node;
            }
        }
        return null;
    }
    
    

}
