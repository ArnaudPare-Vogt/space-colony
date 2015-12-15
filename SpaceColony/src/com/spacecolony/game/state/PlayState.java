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
package com.spacecolony.game.state;

import com.spacecolony.game.Init;
import com.spacecolony.game.data.input.PlayerInput;
import com.spacecolony.game.data.level.Level;
import com.spacecolony.game.data.level.ResourceManager;
import com.spacecolony.game.data.level.TileInfo;
import com.spacecolony.game.data.level.body.Body;
import com.spacecolony.game.data.level.body.GameCharacter;
import com.spacecolony.game.gui.UIButton;
import com.spacecolony.game.gui.UIDescriptionLabel;
import com.spacecolony.game.gui.UIElement;
import com.spacecolony.game.gui.UIFixedSizeLabel;
import com.spacecolony.game.gui.UILabel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author 1448607
 */
public class PlayState extends BasicGameState {

    private final int scale, width, height;

    private Level lvl;

    private Vector2f pos = new Vector2f(0, 0);

    private boolean isInBuildMode = false;
    private boolean didAction = false;
    
    private ResourceManager resourceManager;

    private List<UIElement> uiElements = new ArrayList<>();
    private UILabel buildButton;

    private PlayerInput plIn = new PlayerInput(pos);

    private List<Body> bodies = new ArrayList<>();
    private GameCharacter selectedCharacter;

    private Random rnd = new Random();
    
    public PlayState(int scale, int width, int height) {
        this.scale = scale;
        this.width = width;
        this.height = height;
    }

    @Override
    public int getID() {
        return Init.PLAY_STATE_ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        lvl = new Level(20, 15, rnd);
        bodies.add(new GameCharacter((int)(10.5 * Level.TILE_RES), (int)(10.5 * Level.TILE_RES), rnd, lvl));
        bodies.add(new GameCharacter((int)(10.5 * Level.TILE_RES), (int)(11.5 * Level.TILE_RES), rnd, lvl));
        
        resourceManager = new ResourceManager();

        final UIButton b = new UIButton("BUILD MODE");
        b.setPos(new Vector2f(0, 50));
        uiElements.add(b);

        buildButton = new UIFixedSizeLabel("X", new Vector2f(Level.TILE_RES * scale, Level.TILE_RES * scale));
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
        
        uiElements.add(new UIDescriptionLabel("Stability : ", resourceManager.getStabilityProp()));
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.pushTransform();
        g.scale(scale, scale);
        g.setColor(Color.gray);
        g.fillRect(0, 0, width, height);
        g.translate(-pos.x, -pos.y);
        lvl.render(g, pos, new Vector2f(width, height));
        renderBodies(g);
        lvl.renderTop(g, pos, new Vector2f(width, height));
        g.popTransform();

        for (UIElement uiElement : uiElements) {
            uiElement.repaint(g);
        }
    }

    private void renderBodies(Graphics g) {
        for (Body body : bodies) {
            body.render(g);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        didAction = false;
        float dt = delta / 1000.f;
        
        resourceManager.clear();
        
        Input input = container.getInput();
        plIn.processInput(input);

        moveCamera();
        
        if (input.isKeyDown(Input.KEY_ESCAPE)) {
            container.exit();
        }

        for (UIElement uiElement : uiElements) {
            if (uiElement.getBoundingBox().contains(input.getMouseX(), input.getMouseY())) {
                uiElement.mouseIn();
                if (plIn.clicked(PlayerInput.MOUSE_LEFT_BUTTON)) {
                    uiElement.clicked();
                } else if (plIn.wasMouseRelesed(PlayerInput.MOUSE_LEFT_BUTTON)) {
                    uiElement.mouseReleased();
                }
            } else {
                uiElement.mouseOut();
            }
        }

        lvl.update(dt, resourceManager);

        if (isInBuildMode) {

            TileInfo tf = lvl.getNearestTile(plIn.getMousePosInGameSpace());
            if (tf != null && tf.getT() == null) {//its a spot and there is no tile yet
                Vector2f bbPos = tf.getFloatPos().sub(pos).scale(((float) scale));
                buildButton.setPos(bbPos);

                if (lvl.isNextToTile(tf.getX(), tf.getY())) {
                    buildButton.setVisible(true);
                    if (plIn.clicked(PlayerInput.MOUSE_LEFT_BUTTON) && !didAction) {
                        lvl.buildTile(tf.getX(), tf.getY());
                    }
                } else {
                    buildButton.setVisible(false);
                }
            } else {
                buildButton.setVisible(false);
            }
        } else {
            if (plIn.clicked(PlayerInput.MOUSE_LEFT_BUTTON)) {
                selectBody(plIn.getMousePosInGameSpace());
            }
            if (plIn.clicked(PlayerInput.MOUSE_RIGHT_BUTTON)) {
                if(selectedCharacter != null){
                    TileInfo tf = lvl.getNearestTile(plIn.getMousePosInGameSpace());
                    if(tf != null && tf.getT() != null){
                        selectedCharacter.goTo(tf.getX(), tf.getY());
                    }
                }
            }
            buildButton.setVisible(false);
        }

        for (Body body : bodies) {
            body.update(dt);
        }
        
        resourceManager.update(dt);
    }

    private void moveCamera() {
        pos.sub(plIn.getMouseDelta(PlayerInput.MOUSE_LEFT_BUTTON).copy().scale(1.f / scale));

        if (pos.x < 0) {
            pos.x = 0;
        }
        if (pos.x > (lvl.getWidth()) * 16 - width) {
            pos.x = (lvl.getWidth()) * 16 - width;
        }
        if (pos.y < 0) {
            pos.y = 0;
        }
        if (pos.y > lvl.getHeight() * 16 - height) {
            pos.y = lvl.getHeight() * 16 - height;
        }
    }

    public void selectBody(Vector2f position) {
        if(selectedCharacter != null){
            selectedCharacter.setSelected(false);
        }
        selectedCharacter = null;
        for (Body body : bodies) {
            if (body.isSelectable()) {
                Shape s = body.getBoundingBox();
                if (s.contains(position.x, position.y)) {
                    if (body instanceof GameCharacter) {
                        selectedCharacter = ((GameCharacter) body);
                        selectedCharacter.setSelected(true);
                    }
                }
            }
        }
    }
}
