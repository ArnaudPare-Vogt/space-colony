/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacecolony.game.graphics;

import com.spacecolony.game.logic.Game;
import com.spacecolony.game.util.math.Vec2;
import java.text.NumberFormat;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * The main frame of the game
 *
 * @author 1448607
 */
public class Frame extends Application {

    /**
     * The WIDTH of the Application
     */
    public static final int WIDTH = 400;
    /**
     * The HEIGHT of the application
     */
    public static final int HEIGHT = 500;

    private DoubleProperty fpsFraction = new SimpleDoubleProperty(0);

    private Canvas canvas;
    private Stage window;

    private Game game;

    private final AnimationTimer timer = new AnimationTimer() {

        public static final double FPS_TARGET = 60;
        private static final double REFRESH_RATE_TARGET = 1 / FPS_TARGET;

        private long lastTime = 0;

        @Override
        public void handle(long now) {
            double dt = (now - lastTime)/1000000000.;
            lastTime = now;
            fpsFraction.set(1/dt);
            game.render(canvas.getGraphicsContext2D());
        }
    };

    /**
     * Starts the application by initialyzing all the resources.
     * @param primaryStage the main stage
     */
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        Group root = new Group();
        Scene sceme = new Scene(root);

        canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);

        Label fps = new Label();
        fps.textProperty().bindBidirectional(fpsFraction, NumberFormat.getInstance());
        root.getChildren().add(fps);

        primaryStage.setScene(sceme);

        game = new Game(new Vec2(WIDTH, HEIGHT));

        primaryStage.setOnCloseRequest((WindowEvent event) -> {
            game.stop();
        });
        
        sceme.setOnKeyPressed((KeyEvent event) -> {
            keyPressed(event);
        });
        
        primaryStage.show();
        timer.start();
        game.start();
    }
    
    private void close(){
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
    
    private void keyPressed(KeyEvent eve){
        if(eve.getCode() == KeyCode.ESCAPE){
            close();
        }
    }

}
