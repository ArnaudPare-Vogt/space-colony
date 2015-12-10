/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacecolony.game.logic;

import com.spacecolony.game.logic.gameobject.CubeObject;
import com.spacecolony.game.logic.gameobject.GameObject;
import com.spacecolony.game.util.math.Vec2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The logic of the game
 *
 * @author 1448607
 */
public class Game {

    /**
     * The update rate in nanoseconds
     */
    public static final long UPDATE_RATE = 16666666;

    private final Vec2 size;

    private GameObject go = new CubeObject(new Vec2(30));

    private Thread updateThread;

    private final Runnable updateLoop = () -> {
        long lastTime = System.nanoTime();
        long dt;
        while (!updateThread.isInterrupted()) {
            if ((dt = System.nanoTime() - lastTime) > UPDATE_RATE) {
                lastTime = System.nanoTime();
                update(dt / 1000000000.f);
            }
        }
    };

    /**
     * Creates a new game width the given size
     *
     * @param size the size of the game
     */
    public Game(Vec2 size) {
        this.size = size;
        updateThread = new Thread(updateLoop, "Update Loop");
    }

    /**
     * Starts the game by starting the update loop
     */
    public void start() {
        updateThread.start();
    }

    /**
     * Stops the game by interrupting the update loop
     */
    public void stop() {
        updateThread.interrupt();
    }

    /**
     * Call whenever needed
     *
     * @param gc the graphicsContext to render on
     */
    public void render(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, size.x, size.y);
        gc.save();
        gc.setFill(Color.BLACK);
        go.render(gc);
        gc.restore();
    }

    /**
     * Updates the game
     *
     * @param dt the delta-time in seconds
     */
    private void update(float dt) {
        go.update(dt);
    }

}
