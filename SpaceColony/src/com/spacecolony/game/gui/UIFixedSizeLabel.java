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

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author 1448607
 */
public class UIFixedSizeLabel extends UILabel {

    public UIFixedSizeLabel(String text, Vector2f size) {
        super(text);
        setSize(size);
    }

    public UIFixedSizeLabel(Vector2f size, Color background, Color fontColor, TrueTypeFont font, String text, float padding) {
        super(background, fontColor, font, text, padding);
        setSize(size);
    }

    @Override
    public void updateSize() {
    }

    @Override
    public void render(Graphics g) {
        int h = font.getHeight(text);
        int w = font.getWidth(text);

        g.setColor(background);
        g.fillRect(getPos().x, getPos().y, getSize().x, getSize().y);
        g.setColor(fontColor);
        g.drawString(text, getPos().x + (getSize().x - w) / 2, getPos().y + (getSize().y - h) / 2);
    }

}
