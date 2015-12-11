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

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.Log;

/**
 *
 * @author 1448607
 */
public class Game extends BasicGame {

    public static final int SCALE = 3;

    private Image tm;

    private Vector2f pos = new Vector2f(0, 0);

    private Vector2f lastMousePos = new Vector2f(0, 0);

    public Game(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        tm = new Image("Images/Test.png");
        tm.setFilter(Image.FILTER_NEAREST);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        float dt = delta / 1000.f;

        Input input = container.getInput();
        
        if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
            lastMousePos.x = input.getMouseX();
            lastMousePos.y = input.getMouseY();
        }
        
        if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))  {
            float dx = input.getMouseX() - lastMousePos.x;
            float dy = input.getMouseY() - lastMousePos.y;
            
            lastMousePos.x = input.getMouseX();
            lastMousePos.y = input.getMouseY();
            
            pos.x += dx;
            pos.y += dy;
        }

        if (input.isKeyDown(Input.KEY_ESCAPE)) {
            container.exit();
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        g.scale(SCALE, SCALE);
        g.translate(pos.x, pos.y);
        g.drawImage(tm, 0, 0);
    }

}
