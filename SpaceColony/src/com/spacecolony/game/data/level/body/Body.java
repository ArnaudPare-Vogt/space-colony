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

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

/**
 * A simple in-game body. (an object that moves)
 * @author 1448607
 */
public abstract class Body {
    
    protected Vector2f pos = new Vector2f();
    protected Vector2f size = new Vector2f();

    public Body() {
    }

    public void setSize(Vector2f size) {
        this.size = size;
    }

    public void setPos(Vector2f pos) {
        this.pos = pos;
    }

    public void setSize(float x, float y) {
        size.x = x;
        size.y = y;
    }

    public void setPos(float x, float y) {
        pos.x = x;
        pos.y = y;
    }
    
    public void addToPos(Vector2f pos){
        addToPos(pos.x,pos.y);
    }
    
    public void addToPos(float x, float y){
        pos.x += x;
        pos.y += y;
    }
    
    public void render(Graphics g){
    }
    
    public void update(float dt){
    }
    
    public boolean isSelectable(){
        return true;
    }
    
    public Shape getBoundingBox(){
        return new Rectangle(pos.x, pos.y, size.x, size.y);
    }
    
    public Vector2f getCenter(){
        return new Vector2f(pos.x + size.x/2, pos.y + size.y/2);
    }
}
