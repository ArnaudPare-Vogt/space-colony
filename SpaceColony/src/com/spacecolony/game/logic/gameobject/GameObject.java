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
package com.spacecolony.game.logic.gameobject;

import com.spacecolony.game.util.math.Vec2;
import javafx.scene.canvas.GraphicsContext;

/**
 * Represents an object in-game
 *
 * @author 1448607
 */
public abstract class GameObject {

    /**
     * The position on the object
     */
    protected Vec2 pos = new Vec2();
    /**
     * The Size of the object
     */
    protected Vec2 size = new Vec2();

    /**
     * Sets the position of the object
     *
     * @param pos the new position
     * @return this object, for convenience
     */
    public GameObject setPosition(Vec2 pos) {
        this.pos = pos;
        return this;
    }

    /**
     * Gets the position of this object
     *
     * @return the position of this object
     */
    public Vec2 getPosition() {
        return pos;
    }

    /**
     * Sets the size of this object
     *
     * @param size the size of this object
     * @return this object, for convenience
     */
    public GameObject setSize(Vec2 size) {
        this.size = size;
        return this;
    }

    /**
     * Gets the size of this object
     *
     * @return the size of this object
     */
    public Vec2 getSize() {
        return size;
    }

    /**
     * Renders this object (and all sub-objects) on the given context
     *
     * @param gc the graphicd context on whitch to render the graphics
     */
    public void render(GraphicsContext gc) {
    }

    /**
     * Updates this gameObject.
     * @param dt the time between the last call and this one (it is not exact)
     */
    public void update(float dt) {
    }

}
