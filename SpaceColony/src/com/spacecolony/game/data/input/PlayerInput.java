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

import com.spacecolony.game.Game;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author 1448607
 */
public class PlayerInput {

    public static final int MOUSE_CLICK_LIMIT_DISPLACEMENT = 5;
    public static final int MOUSE_BUTTON_NUM = 3;
    public static final int[] MOUSE_BUTTON_MAP = {
        Input.MOUSE_LEFT_BUTTON,
        Input.MOUSE_MIDDLE_BUTTON,
        Input.MOUSE_RIGHT_BUTTON
    };

    public static final int MOUSE_LEFT_BUTTON = 0;
    public static final int MOUSE_MIDDLE_BUTTON = 1;
    public static final int MOUSE_RIGHT_BUTTON = 2;

    private Input input;

    private boolean[] mousePressed = new boolean[MOUSE_BUTTON_NUM];
    private boolean[] mouseWasRelesed = new boolean[MOUSE_BUTTON_NUM];
    private boolean[] mouseWasPressed = new boolean[MOUSE_BUTTON_NUM];
    private boolean[] clicked = new boolean[MOUSE_BUTTON_NUM];

    private Vector2f[] mouseDownPosition = new Vector2f[MOUSE_BUTTON_NUM];
    private Vector2f[] mouseLastDownPosition = new Vector2f[MOUSE_BUTTON_NUM];
    private Vector2f[] mouseDelta = new Vector2f[MOUSE_BUTTON_NUM];

    private Vector2f mousePosInGameSpace = new Vector2f();
    private Vector2f gameSpaceDecal;

    public PlayerInput(Vector2f gameSpaceDecal) {
        this.gameSpaceDecal = gameSpaceDecal;
        for (int i = 0; i < MOUSE_BUTTON_NUM; i++) {
            mouseDownPosition[i] = new Vector2f();
            mouseLastDownPosition[i] = new Vector2f();
            mouseDelta[i] = new Vector2f();
        }
    }

    public void processInput(Input input) {
        this.input = input;

        for (int i = 0; i < MOUSE_BUTTON_NUM; i++) {
            mouseWasPressed[i] = input.isMousePressed(MOUSE_BUTTON_MAP[i]);
            mouseWasRelesed[i] = mousePressed[i] && !input.isMouseButtonDown(MOUSE_BUTTON_MAP[i]);
            mousePressed[i] = input.isMouseButtonDown(MOUSE_BUTTON_MAP[i]);

            if (mouseWasPressed[i]) {
                mouseDownPosition[i].x = input.getMouseX();
                mouseDownPosition[i].y = input.getMouseY();
                
                mouseLastDownPosition[i].x = input.getMouseX();
                mouseLastDownPosition[i].y = input.getMouseY();
            }

            if (mousePressed[i]) {
                mouseDelta[i].x = input.getMouseX() - mouseLastDownPosition[i].x;
                mouseDelta[i].y = input.getMouseY() - mouseLastDownPosition[i].y;
                
                mouseLastDownPosition[i].x = input.getMouseX();
                mouseLastDownPosition[i].y = input.getMouseY();
            }else{
                mouseDelta[i].x = 0;
                mouseDelta[i].y = 0;
            }

            clicked[i] = false;
            if (mouseWasRelesed[i]) {
                clicked[i]
                        = Math.abs(input.getMouseX() - mouseDownPosition[i].x) <= MOUSE_CLICK_LIMIT_DISPLACEMENT
                        && Math.abs(input.getMouseY() - mouseDownPosition[i].y) <= MOUSE_CLICK_LIMIT_DISPLACEMENT;
            }
        }

        mousePosInGameSpace.x = input.getMouseX();
        mousePosInGameSpace.y = input.getMouseY();

        mousePosInGameSpace.scale(1.f / Game.SCALE).add(gameSpaceDecal);
    }

    public Input getInput() {
        return input;
    }

    public boolean isMousePressed(int mouseButton) {
        return mousePressed[mouseButton];
    }

    public boolean wasMousePressed(int mouseButton) {
        return mouseWasPressed[mouseButton];
    }

    public boolean wasMouseRelesed(int mouseButton) {
        return mouseWasRelesed[mouseButton];
    }

    public boolean clicked(int mouseButton) {
        return clicked[mouseButton];
    }

    public Vector2f getMousePosInGameSpace() {
        return mousePosInGameSpace;
    }

    public Vector2f getMouseDelta(int mouseButtonBtn) {
        return mouseDelta[mouseButtonBtn];
    }

}
