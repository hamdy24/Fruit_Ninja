package fruit.ninja.fruit_ninja_hamdy;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;

public class Fruit extends HBox{
    Arc arcPath;
    PathTransition sphereTrans;
    Sphere sphere;

    /**
     * Constructor is set to receive the random sphere radius and arc x and y radius
     * **/
    Fruit(double sphRadius , double xRadius ,double yRadius ){
        sphere = new Sphere(sphRadius);
        sphere.setTranslateX(-1000);//to make sphere start behind the scene
        arcPath = new Arc(100,600,xRadius,yRadius,180,-180);
        sphereTrans = new PathTransition(Duration.millis(5000),arcPath ,sphere);

        sphereTrans.setCycleCount(Timeline.INDEFINITE);

        sphereTrans.play();

    }
}