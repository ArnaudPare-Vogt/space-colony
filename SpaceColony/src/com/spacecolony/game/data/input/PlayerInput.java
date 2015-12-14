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
package com.spacecolony.game.data.input;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author 1448607
 */
public class PlayerInput {

    public static final int MOUSE_CLICK_LIMIT_DISPLACEMENT = 5;

    private Input input;

    private boolean leftMousePressed = false;
    private boolean leftMouseWasRelesed = false;
    private boolean leftMouseWasPressed = false;
    private boolean leftClicked = false;

    private Vector2f mouseDownPosition = new Vector2f();

    public void processInput(Input input) {
        this.input = input;
        leftMouseWasPressed = input.isMousePressed(Input.MOUSE_LEFT_BUTTON);
        leftMouseWasRelesed = leftMousePressed && !input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
        leftMousePressed = input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);

        if (leftMouseWasPressed) {
            mouseDownPosition.x = input.getMouseX();
            mouseDownPosition.y = input.getMouseY();
        }

        leftClicked = false;
        if (leftMouseWasRelesed) {
            leftClicked
                    = Math.abs(input.getMouseX() - mouseDownPosition.x) <= MOUSE_CLICK_LIMIT_DISPLACEMENT
                    && Math.abs(input.getMouseY() - mouseDownPosition.y) <= MOUSE_CLICK_LIMIT_DISPLACEMENT;
        }

    }

    public Input getInput() {
        return input;
    }

    public boolean isLeftMousePressed() {
        return leftMousePressed;
    }

    public boolean wasLeftMousePressed() {
        return leftMouseWasPressed;
    }

    public boolean wasLeftMouseRelesed() {
        return leftMouseWasRelesed;
    }

    public boolean leftClicked() {
        return leftClicked;
    }

}
