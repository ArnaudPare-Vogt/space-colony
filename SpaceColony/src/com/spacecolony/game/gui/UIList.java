/*
 * Copyright (C) 2015 root
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author root
 */
public class UIList extends UIElement{
    
    private List<UIElement> elements = new ArrayList<>();
    private float padding = 10;
    private Color backgroundColor = new Color(0x55000000);
    
    private Vector2f targetSize = new Vector2f();
    /**
     * Time in seconds it takes to resize
     */
    private final float speed = .1f;
    
    /**
     * If resize should be dampened
     */
    private boolean dampen = false;
    private float dx = 0;
    private float dy = 0;
    
    private boolean resizing = false;
    
    public UIList() {
        targetSize = getSize().copy();
    }
    
    public void addElement(UIElement element){
        elements.add(element);
        updateSize();
    }
    
    public void updateSize(){
        targetSize.x = padding;
        targetSize.y = padding;
        
        float maxWidth = 0;
        for (Iterator<UIElement> it = elements.iterator(); it.hasNext();) {
            UIElement element = it.next();
            if(element.isVisible()){
                element.setPos(getPos().x + padding, getPos().y + targetSize.y);
                targetSize.y += element.getSize().y;
                if(maxWidth < element.getSize().x){
                    maxWidth = element.getSize().x;
                }
                if(it.hasNext()){
                    targetSize.y += padding;
                }
            }
        }
        targetSize.x += maxWidth;
        
        targetSize.x += padding;
        targetSize.y += padding;
        if(!dampen){
            setSize(targetSize.copy());
        }
        
        dx = targetSize.x - getSize().x;
        dy = targetSize.y - getSize().y;
        
        resizing = (dx!=0 || dy!=0);
    }
    
    private void updateCompPos(){
        float height = padding;
        for (Iterator<UIElement> it = elements.iterator(); it.hasNext();) {
            UIElement element = it.next();
            if(element.isVisible()){
                element.setPos(getPos().x + padding, getPos().y + height);
                height += element.getSize().y;
                height += padding;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(getPos().x, getPos().y, getSize().x, getSize().y);
        
        g.setWorldClip((int) getPos().x, (int) getPos().y, (int) getSize().x, (int) getSize().y);
        for (UIElement element : elements) {
            element.repaint(g);
        }
        g.clearWorldClip();
    }

    @Override
    protected void onPosChange() {
        updateSize();
    }

    @Override
    public List<UIElement> getChildrens() {
        return elements;
    }

    /**
     * Sets if the resize should be dampened
     * @param dampen 
     */
    public void setDampen(boolean dampen) {
        this.dampen = dampen;
        if(!dampen){
            setSize(targetSize);
        }
    }
    
    @Override
    public void update(float dt){
        if(dampen){
            if(resizing){
                Vector2f size = getSize();

                setSizeX(dx*dt*(1/speed) + size.x);
                setSizeY(dy*dt*(1/speed) + size.y);
                updateCompPos();
                if(((targetSize.x - getSize().x) * Math.signum(-dx) > 0) 
                        && ((targetSize.y - getSize().y) * Math.signum(-dy) > 0)){
                    setSize(targetSize.copy());
                    resizing = false;
                    updateCompPos();
                }
            }
        }else{
            updateCompPos();
        }
    }
}
