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
import com.spacecolony.game.graphics.Sprite;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 *
 * @author 1448607
 */
public class Tile {
    
    protected final TileType tileType;
    private int tileIndex = 0;
    
    private boolean worked = false;
    
    private Machine machine;

    public Tile(TileType tileType) {
        this(tileType,null);
    }

    public Tile(TileType tileType, Machine machine) {
        this.tileType = tileType;
        this.machine = machine;
    }
    
    public Sprite getSprite(){
        return tileType.getSprite();
    }

    public TileType getTileType() {
        return tileType;
    }
    
    public void refresh(Tile[] adjTiles){
    }

    public Image getImage() {
        return getSprite().getImage(0);
    }
    
    public void render(Graphics g, float xPos, float yPos){
        g.drawImage(getImage(), xPos, yPos);
        if(machine != null){
            g.drawImage(machine.getImage(), xPos, yPos);
        }
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Machine getMachine() {
        return machine;
    }
    
    

    void renderTop(Graphics g, int xPos, int yPos) {
        if(machine != null){
            g.drawImage(machine.getTopImage(), xPos, yPos);
        }
    }

    public void setWorked(boolean worked) {
        this.worked = worked;
    }

    public boolean isWorked() {
        return worked;
    }
}
