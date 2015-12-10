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
 * A cube.
 * @author 1448607
 */
public class CubeObject extends GameObject{

    /**
     * Creates a new Cube with the given size
     * @param size the size of the cube
     */
    public CubeObject(Vec2 size) {
        setSize(size);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.translate(pos.x, pos.y);
        gc.fillRect(0, 0, size.x, size.y);
    }

    @Override
    public void update(float dt) {
        pos.x += 100 * dt;
        pos.y += 100 * dt;
    }
    
}
