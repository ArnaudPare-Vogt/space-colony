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
package com.spacecolony.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 * The main class. Initialyses the game.
 * @author 1448607
 */
public class Init {
    
    public static final int PLAY_STATE_ID = 1;
    
    
    /**
     * Main.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new Game("test"));
            app.setDisplayMode(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE, false);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

}
