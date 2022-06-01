package com.example.fruitninjahassan;

import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.CubicCurve;
import javafx.util.Duration;
import java.io.FileInputStream;
/**
Creating Ninja Fruit Class
**/
public class NinjaFruit extends Pane {
    CubicCurve cubicCurve;
    // Initializing Random values for Path Curve
    double start_x = 300 * Math.random();
    double end_x = 724 + 450 * Math.random();
    double start_y = 868 + 50 * Math.random();
    double end_y = 868 + 100 * Math.random();
    double control_x = 350 + 400 * Math.random();
    // Initializing Score value
    public static int score = 0;
    // Creating Path Array for whole fruit & 2 halves
    PathTransition[] fruit_paths = new PathTransition[3];
    // Creating FilePath Array for whole fruit & 2 halves
    FileInputStream[] fruit_file = new
            FileInputStream[3];
    // Creating Image Array for whole fruit & 2 halves
    Image[] fruit_image = new Image[3];
    ImageView[] fruit_imageview = new ImageView[3];
    // Creating Rotation Effect Array for whole fruit & 2 halves
    RotateTransition[] rotateTransition_fruit = new RotateTransition[3];
    // Creating Drop Effect Array for 2 halves
    TranslateTransition[] translate_fruit = new TranslateTransition[2];

    // Choosing Path as a Cubic Curve (Reference only created)
    public NinjaFruit(FileInputStream whole_fruit, FileInputStream half1, FileInputStream half2) {
         /**
         Constructor
         Creates paths & adds fruit & its halves into a pane
         Args:
         (FileInputStream) whole_fruit - filepath of whole
        fruit
         (FileInputStream) half1 - filepath for 1st half
         (FileInputStream) half2 - filepath for 2nd half
         */
        // Specifying random values for cubic curve parameters
        cubicCurve = new CubicCurve(start_x, start_y, control_x,0,control_x,0 , end_x, end_y);
        // Initializing individual array elements
        fruit_file[0] = whole_fruit;
        fruit_file[1] = half1;
        fruit_file[2] = half2;
        for (int i = 0; i < 3; i++) {
            fruit_image[i] = new Image(fruit_file[i]);
            fruit_imageview[i] = new ImageView(fruit_image[i]);
            // Setting whole Fruit & halves size
            fruit_imageview[i].setFitWidth(80);
            fruit_imageview[i].setFitHeight(80);
            fruit_imageview[i].setX(26);
            fruit_imageview[i].setY(868);
            // Add whole fruit and sizes to pane
            getChildren().add(fruit_imageview[i]);
        }
        // Setting halves visibility
        fruit_imageview[1].setVisible(false);
        fruit_imageview[2].setVisible(false);
        create();
    }
    public void create() {
        setPathTransition(3700);
        setRotation();
        setHalfDrop();
    }
    public void setPathTransition(int millis) {
         /**
         Setting Duration (millis), Nodes (fruit & its
          halves ), and Path (Cubic Curve) to Path Transition
         */
        for (int i = 0; i < 3; i++) {
            fruit_paths[i] = new PathTransition(Duration.millis(millis), cubicCurve ,fruit_imageview[i]);
            fruit_paths[i].play();
        }
    }
    public void setRotation() {
         /**
         Setting Duration, Nodes (fruit --> 400
        milliseconds & its halves --> 2 seconds),
         and Angle of Rotation (360
        degrees full rotation)
         */
        for (int i = 0; i < 3; i++) {
            rotateTransition_fruit[i] = new RotateTransition();
            rotateTransition_fruit[i].setNode(fruit_imageview[i]);
            rotateTransition_fruit[i].setByAngle(360);
            rotateTransition_fruit[i].setCycleCount(-1);
        }

        rotateTransition_fruit[0].setDuration(Duration.millis(400));
        rotateTransition_fruit[0].play();

        rotateTransition_fruit[1].setDuration(Duration.millis(2000));

        rotateTransition_fruit[2].setDuration(Duration.millis(2000));
    }
    public void setHalfDrop() {
         /**
         Setting halves (only) Drop Path after slicing
         */

        for (int i = 0; i < 2; i++) {
            translate_fruit[i] = new TranslateTransition(Duration.millis(3000), fruit_imageview[i + 1]); // set motion of the halves
            translate_fruit[i].setByY(1000);
        }
        // Set a half to drop diagonally to the right and the other to the left
        translate_fruit[0].setByX(-350);
        translate_fruit[1].setByX(350);
    }
    public void setEventHandling() {
     /**
     Setting Actions if Whole Fruit is Sliced
     */
        fruit_imageview[0].setOnMouseMoved(mouseEvent ->
                {
                    // Stop Curve Movement
                    fruit_paths[0].stop();
                    fruit_paths[1].stop();
                    fruit_paths[2].stop();
                    // Whole Fruit Disappear & halves Appear
                    fruit_imageview[0].setVisible(false);
                    fruit_imageview[1].setVisible(true);
                    fruit_imageview[2].setVisible(true);
                    // Play Drop Path of halves
                    translate_fruit[0].play();
                    translate_fruit[1].play();
                    // Rotate Halves as they drop
                    rotateTransition_fruit[1].play();
                    rotateTransition_fruit[2].play();
                    // Increase Score
                    score += 10 ;
                }
        );
    }
}