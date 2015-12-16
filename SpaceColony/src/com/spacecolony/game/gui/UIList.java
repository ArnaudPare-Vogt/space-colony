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
    
    public UIList() {
    }
    
    public void addElement(UIElement element){
        elements.add(element);
        updateSize();
    }
    
    private void updateSize(){
        size.x = padding;
        size.y = padding;
        
        float maxWidth = 0;
        for (Iterator<UIElement> it = elements.iterator(); it.hasNext();) {
            UIElement element = it.next();
            element.setPos(pos.x + padding, pos.y + size.y);
            size.y += element.size.y;
            if(maxWidth < element.size.x){
                maxWidth = element.size.x;
            }
            if(it.hasNext()){
                size.y += padding;
            }
        }
        size.x += maxWidth;
        
        size.x += padding;
        size.y += padding;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(pos.x, pos.y, size.x, size.y);
        
        for (UIElement element : elements) {
            element.repaint(g);
        }
    }

    @Override
    public void setPos(Vector2f pos) {
        super.setPos(pos);
        updateSize();
    }

    @Override
    public void setPos(float x, float y) {
        super.setPos(x, y);
        updateSize();
    }

    @Override
    public List<UIElement> getChildrens() {
        return elements;
    }
}
