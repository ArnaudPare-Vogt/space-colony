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
package com.spacecolony.game.data.level;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

/**
 * Manages all of the resoueces of the game
 * @author 1448607
 */
public class ResourceManager {
    
    /**
     * Between -1 and 1, -1 : station is crashing, 1 : perfectly stable
     * <br/>In between sould cause nasty effects :
     * <br/><br/>
     * Ideas : <ul>
     * <li>occasional shakes making people fall</li>
     * <li>noises, and other minor diagrments not allowing people to rest correctly</li>
     * <li>les productivity for the space craft</li>
     * <li>reduced change of evading progectile on a slow course</li>
     * <li>augmented reaction period to change orbit in oeder to evade large,slow bodies</li>
     * <li>augmented changes of structural failure in weak parts of the station</li>
     * <li>more fumbles in battles on board</li>
     * <li>etc...</li>
     * </ul>
     */
    private FloatProperty stabilityProp = new SimpleFloatProperty(1.f);
    
    /**
     * The rate at which the oxygen regenerates.
     */
    private FloatProperty oxygenRegenLevelProp = new SimpleFloatProperty(1.f);
    
    private float stability = 1.f;
    private float oxygenRegenLevel = 1.f;
    
    
    
    public void clear(){
        stability = 0;
        oxygenRegenLevel = 0;
    }
    
    public void update(float dt){
        if(stability > 1){
            stability = 1;
        }else if(stability < -1){
            stability = -1;
        }
        
        stabilityProp.set(stability);
        oxygenRegenLevelProp.set(oxygenRegenLevel);
    }
    
    public void stabilityAugment(int level){
        stability += level;
    }

    public float getStability() {
        return stabilityProp.floatValue();
    }

    public FloatProperty getStabilityProp() {
        return stabilityProp;
    }

    public float getOxygenRegenLevel() {
        return oxygenRegenLevelProp.floatValue();
    }

    public FloatProperty getOxygenRegenLevelProp() {
        return oxygenRegenLevelProp;
    }
}
