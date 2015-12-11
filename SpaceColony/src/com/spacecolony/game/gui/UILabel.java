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

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author 1448607
 */
public class UILabel extends UIElement{

    protected Color background;
    protected Color fontColor;
    protected TrueTypeFont font;
    protected String text;
    protected float padding;

    public UILabel(Color background, Color fontColor, TrueTypeFont font, String text, float padding) {
        this.background = background;
        this.fontColor = fontColor;
        this.font = font;
        this.text = text;
        this.padding = padding;
        
        updateSize();
    }
    
    public UILabel(String text){
        this(new Color(0x66000000), new Color(0xffffffff), new TrueTypeFont(new Font("Verdana", Font.BOLD, 20), true), text, 10);
    }

    @Override
    public void render(Graphics g) {
        g.setFont(font);
        g.setColor(background);
        g.fillRect(pos.x, pos.y, size.x, size.y);
        g.setColor(fontColor);
        g.drawString(text, pos.x + padding, pos.y + padding);
    }

    public void setText(String text) {
        this.text = text;
        updateSize();
    }

    public void updateSize(){
        size.y = font.getHeight(text) + 2 * padding;
        size.x = font.getWidth(text) + 2 * padding;
    }
}
