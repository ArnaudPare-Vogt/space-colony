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

import javafx.beans.property.Property;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author 1448607
 */
public class UIDescriptionLabel extends UILabel{
    
    private String desc;
    private Property<?> value;
    
    public UIDescriptionLabel(String desc, Property<?> value) {
        super(desc + value.getValue().toString());
        this.value = value;
        this.desc = desc;
        
        value.addListener((eve)->{
            updateText();
        });
    }
    
    private void updateText(){
        super.setText(desc + value.getValue().toString());
    }

    @Override
    public void setText(String text) {
        desc = text;
        updateText();
    }
}
