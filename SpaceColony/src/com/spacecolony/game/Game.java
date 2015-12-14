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

import com.spacecolony.game.data.level.Level;
import com.spacecolony.game.data.input.PlayerInput;
import com.spacecolony.game.data.level.TileInfo;
import com.spacecolony.game.gui.UIButton;
import com.spacecolony.game.gui.UIElement;
import com.spacecolony.game.gui.UIFixedSizeLabel;
import com.spacecolony.game.gui.UILabel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author 1448607
 */
public class Game extends BasicGame {

    public static final int SCALE = 3;
    public static final int WIDTH = 200;
    public static final int HEIGHT = 150;

    private Level lvl;

    private Vector2f pos = new Vector2f(0, 0);
    private Vector2f lastMousePos = new Vector2f(0, 0);

    private boolean isInBuildMode = false;

    private boolean didAction = false;
    private UILabel buildButton;

    private PlayerInput plIn = new PlayerInput();

    private List<UIElement> uiElements = new ArrayList<>();

    public Game(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        lvl = new Level(20, 15, new Random());
        final UIButton b = new UIButton("BUILD MODE");
        b.setPos(new Vector2f(0, 50));
        uiElements.add(b);

        buildButton = new UIFixedSizeLabel("X", new Vector2f(Level.TILE_RES * SCALE, Level.TILE_RES * SCALE));
        buildButton.setVisible(isInBuildMode);
        uiElements.add(buildButton);

        b.setOnClickAction(() -> {
            isInBuildMode = !isInBuildMode;
            if (isInBuildMode) {
                b.setText("EXIT BUILD MODE");
            } else {
                b.setText("BUILD MODE");
            }
            didAction = true;
        });
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        didAction = false;
        float dt = delta / 1000.f;

        Input input = container.getInput();
        plIn.processInput(input);

        if (plIn.wasLeftMousePressed()) {
            lastMousePos.x = input.getMouseX();
            lastMousePos.y = input.getMouseY();
        }

        if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
            float dx = input.getMouseX() - lastMousePos.x;
            float dy = input.getMouseY() - lastMousePos.y;

            lastMousePos.x = input.getMouseX();
            lastMousePos.y = input.getMouseY();

            pos.x -= dx / SCALE;
            pos.y -= dy / SCALE;

            if (pos.x < 0) {
                pos.x = 0;
            }
            if (pos.x > (lvl.getWidth()) * 16 - WIDTH) {
                pos.x = (lvl.getWidth()) * 16 - WIDTH;
            }
            if (pos.y < 0) {
                pos.y = 0;
            }
            if (pos.y > lvl.getHeight() * 16 - HEIGHT) {
                pos.y = lvl.getHeight() * 16 - HEIGHT;
            }
        }

        if (input.isKeyDown(Input.KEY_ESCAPE)) {
            container.exit();
        }

        for (UIElement uiElement : uiElements) {
            if (uiElement.getBoundingBox().contains(input.getMouseX(), input.getMouseY())) {
                uiElement.mouseIn();
                if (plIn.leftClicked()) {
                    uiElement.clicked();
                } else if (plIn.wasLeftMouseRelesed()) {
                    uiElement.mouseReleased();
                }
            } else {
                uiElement.mouseOut();
            }
        }

        lvl.update(dt);

        if (isInBuildMode) {
            float x = (plIn.getInput().getMouseX()) / SCALE + pos.x;
            float y = (plIn.getInput().getMouseY()) / SCALE + pos.y;

            TileInfo tf = lvl.getNearestTile(new Vector2f(x, y));
            if (tf != null && tf.getT() == null) {//its a spot and there is no tile yet
                Vector2f bbPos = tf.getFloatPos().sub(pos).scale(((float) SCALE));
                buildButton.setPos(bbPos);

                if (lvl.isNextToTile(tf.getX(), tf.getY())) {
                    buildButton.setVisible(true);
                    if (plIn.leftClicked() && !didAction) {
                        lvl.buildTile(tf.getX(), tf.getY());
                    }
                } else {
                    buildButton.setVisible(false);
                }
            } else {
                buildButton.setVisible(false);
            }
        } else {
            buildButton.setVisible(false);
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        g.pushTransform();
        g.scale(SCALE, SCALE);
        g.setColor(Color.gray);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.translate(-pos.x, -pos.y);
        lvl.render(g, pos, new Vector2f(WIDTH, HEIGHT));
        g.popTransform();

        for (UIElement uiElement : uiElements) {
            uiElement.repaint(g);
        }

    }

}
