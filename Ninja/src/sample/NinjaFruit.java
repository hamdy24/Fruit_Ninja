package sample;
/*********************  V1.01 ***************/
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.CubicCurve;
import javafx.util.Duration;
import java.io.FileInputStream;
public class NinjaFruit extends Pane {
    PathTransition[] fruit_paths = new PathTransition[3];
    FileInputStream[] fruit_file = new
            FileInputStream[3];
    Image[] fruit_image = new Image[3];
    ImageView[] fruit_imageview = new ImageView[3];
    RotateTransition[] rotateTransition_fruit = new
            RotateTransition[3];
    TranslateTransition[] translate_fruit = new
            TranslateTransition[2];
    double start_x = 450 * Math.random();
    double end_x = 800 + 150 * Math.random();
    double start_y = 700 + 50 * Math.random();
    double end_y = 700 + 100 * Math.random();
    double control_x = 400 + 200 * Math.random();
    public static int score = 0;
    CubicCurve cubicCurve;
    public NinjaFruit(FileInputStream whole_fruit,
                      FileInputStream half1, FileInputStream half2) {
        cubicCurve = new CubicCurve(start_x, start_y,
                control_x,0,control_x,0 , end_x, end_y);
        fruit_file[0] = whole_fruit;
        fruit_file[1] = half1;
        fruit_file[2] = half2;
        for (int i = 0; i < 3; i++) {
            fruit_image[i] = new Image(fruit_file[i]);
            fruit_imageview[i] = new
                    ImageView(fruit_image[i]);
            fruit_imageview[i].setFitWidth(80);
            fruit_imageview[i].setFitHeight(80);
            fruit_imageview[i].setX(50);
            fruit_imageview[i].setY(700);
            getChildren().add(fruit_imageview[i]);
        }
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
        for (int i = 0; i < 3; i++) {
            fruit_paths[i] = new
                    PathTransition(Duration.millis(millis), cubicCurve);
            fruit_paths[i].setNode(fruit_imageview[i]);
            fruit_paths[i].play();
        }
    }
    public void setRotation() {
        for (int i = 0; i < 3; i++) {
            rotateTransition_fruit[i] = new
                    RotateTransition();

            rotateTransition_fruit[i].setNode(fruit_imageview[i]);
            rotateTransition_fruit[i].setByAngle(360);
            rotateTransition_fruit[i].setCycleCount(-1);
        }

        rotateTransition_fruit[0].setDuration(Duration.millis(400
        ));
        rotateTransition_fruit[0].play();

        rotateTransition_fruit[1].setDuration(Duration.millis(2000));

        rotateTransition_fruit[2].setDuration(Duration.millis(2000));
    }
    public void setHalfDrop() {
        for (int i = 0; i < 2; i++) {
            translate_fruit[i] = new
                    TranslateTransition(Duration.millis(3000),
                    fruit_imageview[i + 1]);
            translate_fruit[i].setByY(1000);
        }
        translate_fruit[0].setByX(-300);
        translate_fruit[1].setByX(300);
    }
    public void setEventHandling() {
        fruit_imageview[0].setOnMouseMoved(mouseEvent ->
                {
                    fruit_paths[0].stop();
                    fruit_paths[1].stop();
                    fruit_paths[2].stop();
                    fruit_imageview[0].setVisible(false);
                    fruit_imageview[1].setVisible(true);
                    fruit_imageview[2].setVisible(true);
                    translate_fruit[0].play();
                    translate_fruit[1].play();
                    rotateTransition_fruit[1].play();
                    rotateTransition_fruit[2].play();
                    score += 10 ;
                }
        );
    }
    public boolean isOutOfBounds() {
        return fruit_imageview[0].getTranslateX() > 700
                && fruit_imageview[0].getTranslateY() > 650;
    }
}
