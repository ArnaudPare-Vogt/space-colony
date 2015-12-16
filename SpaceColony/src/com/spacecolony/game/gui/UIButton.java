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
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author 1448607
 */
public class UIButton extends UILabel {

    protected Color onNothingColor;
    protected Color onHoverColor;
    protected Color onClickColor;
    protected Runnable onClickAction;

    public UIButton(String text) {
        this(text, new Color(0x77000000), new Color(0xdd000000));
    }

    public UIButton(String text, Color onHoverColor, Color onClickColor) {
        super(text);
        this.onHoverColor = onHoverColor;
        this.onClickColor = onClickColor;
        this.onNothingColor = background;
    }

    public UIButton(Color onHoverColor, Color onClickColor, Color background, Color fontColor, TrueTypeFont font, String text, float padding) {
        super(background, fontColor, font, text, padding);
        this.onHoverColor = onHoverColor;
        this.onClickColor = onClickColor;
        this.onNothingColor = background;
    }

    public void setOnClickAction(Runnable onClickAction) {
        this.onClickAction = onClickAction;
    }

    @Override
    public void clicked() {
        background = onClickColor;
        if (onClickAction != null) {
            onClickAction.run();
        }
    }

    @Override
    public void mouseEnter() {
        background = onHoverColor;
    }

    @Override
    public void mouseExit() {
        background = onNothingColor;
    }

    @Override
    public void mouseReleased() {
        background = onHoverColor;
    }
}
