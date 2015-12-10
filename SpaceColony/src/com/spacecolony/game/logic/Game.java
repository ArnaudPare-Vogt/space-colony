/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacecolony.game.logic;

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

    private Vec2 pos = new Vec2();

    private Thread updateThread;

    private final Runnable updateLoop = () -> {
        long lastTime = System.nanoTime();
        long dt;
        while (!updateThread.isInterrupted()) {
            if ((dt = System.nanoTime() - lastTime) > UPDATE_RATE) {
                lastTime = System.nanoTime();
                update(dt);
            }
        }
    };

    /**
     * Creates a new game width the given size
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
        gc.setFill(Color.BLACK);
        gc.fillRect(pos.x, pos.y, 10, 10);
    }

    private void update(long dt) {
        pos.x += 1;
    }

}
