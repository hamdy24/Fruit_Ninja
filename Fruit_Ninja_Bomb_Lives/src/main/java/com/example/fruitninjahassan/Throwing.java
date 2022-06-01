package com.example.fruitninjahassan;

import javafx.animation.PathTransition;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;


class Throwing {

    //create object data to be used every time new object is created
    Sphere sphere = new Sphere(25,50);
    Path path = new Path();
    PathTransition pathTransition = new PathTransition();
    PhongMaterial Bomb =new PhongMaterial();
    PhongMaterial nature = new PhongMaterial();

    // create a default constructor
    Throwing() {
        create(1);
    }
    Throwing(int x) {
        create(x);
        // use the random number passed in variable x to randomize color of spheres
        switch (x){
            case 0:Bomb.setDiffuseColor(Color.DARKRED);
                sphere.setRadius(50);
                break;
            case 1:nature.setDiffuseColor(Color.SILVER);break;
            case 2:nature.setDiffuseColor(Color.YELLOW);break;
            case 3:nature.setDiffuseColor(Color.RED);break;
            case 4:nature.setDiffuseColor(Color.BLUE);break;
            case 5:nature.setDiffuseColor(Color.GOLD);break;
            default:nature.setDiffuseColor(Color.TOMATO);break;
        }
    }

    /// method that create the spheres and set its throwing start point randomly
    public void create(int x){
        sphere.setTranslateX(-50);
        sphere.setTranslateY(400+Math.random()*200);
        sphere.setMaterial(nature);
        switch (x){

            case 1:
                sphere.setTranslateX(-50);
                sphere.setTranslateY(300+Math.random()*200);break;
            case 2:
                sphere.setTranslateX(-50);
                sphere.setTranslateY(350+Math.random()*200);break;
            case 3:
                sphere.setTranslateX(200+Math.random()*100);
                sphere.setTranslateY(786);break;
            case 4:
                sphere.setTranslateX(250+Math.random()*100);
                sphere.setTranslateY(786);break;
            case 5:
                sphere.setTranslateX(300+Math.random()*100);
                sphere.setTranslateY(786);break;
            default:
                sphere.setTranslateX(-50);
                sphere.setTranslateY(400+Math.random()*200);break;
        }


        /// create the cubic curve path of the fruit spheres
        if (x!=0){
            path.getElements().add(new MoveTo(sphere.getTranslateX(), sphere.getTranslateY()));
            if(sphere.getTranslateY()!=786)
                path.getElements().add(new CubicCurveTo(400, 100, 600, 200, 1074, sphere.getTranslateY()-200));
            else
                path.getElements().add(new CubicCurveTo(400, 50, 600, 100, 1274- sphere.getTranslateX(), 800));

            pathTransition.setDuration(Duration.millis(3500));
            pathTransition.setNode(sphere);
            pathTransition.setPath(path);

        }
        /// create the cubic curve path of the bomb spheres

        else {
            sphere.setMaterial(Bomb);
            path.getElements().add(new MoveTo(sphere.getTranslateX(), sphere.getTranslateY()+300));
            path.getElements().add(new CubicCurveTo(400, 200, 600, 300, 1074, sphere.getTranslateY()+100));
            pathTransition.setDuration(Duration.millis(3500));
            pathTransition.setNode(sphere);
            pathTransition.setPath(path);
            pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

        }
        pathTransition.setAutoReverse(false);
        pathTransition.play();
    }

}
