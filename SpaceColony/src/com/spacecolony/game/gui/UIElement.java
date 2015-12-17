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
package com.spacecolony.game.gui;

import java.util.List;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import static com.spacecolony.game.gui.UIElement.Anchor.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author 1448607
 */
public abstract class UIElement {

    public enum Anchor {
        TOP_LEFT, BOTTOM_LEFT, TOP_RIGHT
    }

    private Vector2f pos = new Vector2f();
    private Vector2f size = new Vector2f();

    private Anchor anchor = TOP_LEFT;
    private final Vector2f posDecal = new Vector2f();

    private boolean mouseWasIn = false;

    protected final BooleanProperty visible = new SimpleBooleanProperty(true);

    public UIElement() {
    }

    public void setSize(Vector2f size) {
        this.size = size;
        updateAnchor();
        onSizeChange();
    }

    public void setPos(Vector2f pos) {
        this.pos = pos;
        updateAnchor();
        onPosChange();
    }

    public void setSize(float x, float y) {
        size.x = x;
        size.y = y;
        updateAnchor();
        onSizeChange();
    }

    /**
     * Don't modify the retuned vector, it dosen't work that way
     * @return the size of this object
     */
    public Vector2f getSize() {
        return size;
    }
    
    public void setSizeX(float x) {
        size.x = x;
        updateAnchor();
        onSizeChange();
    }
    
    public void setSizeY(float y) {
        size.y = y;
        updateAnchor();
        onSizeChange();
    }
    

    public void setPos(float x, float y) {
        pos.x = x;
        pos.y = y;
        updateAnchor();
        onPosChange();
    }

    public Vector2f getPos() {
        return pos.copy().add(posDecal);
    }

    public final void repaint(Graphics g) {
        if (isVisible()) {
            render(g);
        }
    }

    public void render(Graphics g) {
    }

    public void clicked() {
    }

    public Shape getBoundingBox() {
        return new Rectangle(getPos().x, getPos().y, size.x, size.y);
    }

    public final void mouseIn() {
        if (!mouseWasIn) {
            mouseEnter();
            mouseWasIn = true;
        }
    }

    public final void mouseOut() {
        if (mouseWasIn) {
            mouseExit();
            mouseWasIn = false;
        }
    }

    public void mouseEnter() {
    }

    public void mouseExit() {
    }

    public void mouseReleased() {
    }

    public void setVisible(boolean visible) {
        this.visible.set(visible);
    }

    public boolean isVisible() {
        return visible.get();
    }
    
    public BooleanProperty getVisibleProprety(){
        return visible;
    }

    public List<UIElement> getChildrens() {
        return null;
    }

    public void update(float dt) {
    }

    public void setAnchor(Anchor anchor) {
        this.anchor = anchor;
        updateAnchor();
    }

    public Anchor getAnchor() {
        return anchor;
    }
    
    private void updateAnchor(){
        switch (anchor) {
            case TOP_LEFT:
                posDecal.x = 0;
                posDecal.y = 0;
                break;
            case BOTTOM_LEFT:
                posDecal.x = 0;
                posDecal.y = -size.y;
                break;
            case TOP_RIGHT:
                posDecal.x = -size.x;
                posDecal.y = 0;
                break;
        }
    }
    
    protected void onSizeChange(){
    }
    
    protected void onPosChange(){
    }
}
