package sample;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import javax.sound.sampled.*;
import java.io.*;
import java.sql.Time;


class Throwing {

    Throwing() {
        create(1);
    }
    Throwing(int x) {
        create(x);
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
    Sphere sphere = new Sphere(25,50);

    Path path = new Path();
    PathTransition pathTransition = new PathTransition();

    //FileInputStream bombImageFile = new FileInputStream("Bomb.jpg");
    //Image bombImage = new Image(bombImageFile);

    final PhongMaterial Bomb =new PhongMaterial();
    final PhongMaterial nature = new PhongMaterial();
    public void create(int x){
        sphere.setTranslateX(-50);
        sphere.setTranslateY(400+Math.random()*200);
        sphere.setMaterial(nature);
        switch (x){
            //case 0:
            // break;
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
        if (x!=0){
            path.getElements().add(new MoveTo(sphere.getTranslateX(), sphere.getTranslateY()));
            if(sphere.getTranslateY()!=786)
                path.getElements().add(new CubicCurveTo(400, 100, 600, 200, 1074, sphere.getTranslateY()-200));
            else  path.getElements().add(new CubicCurveTo(400, 50, 600, 100, 1274- sphere.getTranslateX(), 800));

            pathTransition.setDuration(Duration.millis(3500));
            pathTransition.setNode(sphere);
            pathTransition.setPath(path);
            pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition.setAutoReverse(false);
            pathTransition.play();}
        else {

            //Bomb.setDiffuseMap(bombImage);

            sphere.setMaterial(Bomb);
            path.getElements().add(new MoveTo(sphere.getTranslateX(), sphere.getTranslateY()+300));
            path.getElements().add(new CubicCurveTo(400, 200, 600, 300, 1074, sphere.getTranslateY()+100));
            pathTransition.setDuration(Duration.millis(3500));
            pathTransition.setNode(sphere);
            pathTransition.setPath(path);
            pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);pathTransition.setCycleCount(3);
            pathTransition.setAutoReverse(false);
            pathTransition.play();

        }
    }

}
public class Main extends Application {
    public static int countScore=0;
    public static int countLives=3;
    public static int Speed=2;
    public static  int flag=0;

    @Override
    public void start(Stage primaryStage) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        /*
         *********************************
         *Scenes*
         *********************************
         */
        BorderPane start=new BorderPane();
        BorderPane gameBorder= new BorderPane();

        Pane gamePlayPane = new Pane();
        Scene gamePlay = new Scene(gamePlayPane, 1024, 768);

        BorderPane gameOver=new BorderPane();
        Scene gameOverForm=new Scene(gameOver,1024,768);
        /*
         *********************************
         *Audio*
         *********************************
         */
        //sliceSound
        File sliceFile =new File("Slice.wav").getAbsoluteFile();
        AudioInputStream sliceInputStream = AudioSystem.getAudioInputStream(sliceFile);
        Clip sliceSound=AudioSystem.getClip();
        sliceSound.open(sliceInputStream);
        //throwSound
        File throwFile =new File("Throw.wav").getAbsoluteFile();
        AudioInputStream throwInputStream = AudioSystem.getAudioInputStream(throwFile);
        Clip throwSound=AudioSystem.getClip();
        throwSound.open(throwInputStream);


        //background
        File backGroundFile =new File("Sounds/ElevatorMusic.wav").getAbsoluteFile();
        AudioInputStream backGroundInputStream = AudioSystem.getAudioInputStream(backGroundFile);
        Clip backGroundSound=AudioSystem.getClip();
        backGroundSound.open(backGroundInputStream);
        backGroundSound.loop(Clip.LOOP_CONTINUOUSLY);
        //buttonHover
        File buttonHoverFile =new File("Sounds/buttonHover.wav").getAbsoluteFile();
        AudioInputStream buttonHoverInputStream = AudioSystem.getAudioInputStream(buttonHoverFile);
        Clip buttonHoverSound=AudioSystem.getClip();
        buttonHoverSound.open(buttonHoverInputStream);
        //buttonClick
        File buttonClickFile =new File("Sounds/buttonClick.wav").getAbsoluteFile();
        AudioInputStream buttonClickInputStream = AudioSystem.getAudioInputStream(buttonClickFile);
        Clip buttonClickSound=AudioSystem.getClip();
        buttonClickSound.open(buttonClickInputStream);
        //Score
        Text Score =new Text(Integer.toString(countScore));
        /*
         v********************************
         *Mouse Cursor*
         *********************************
         */
        FileInputStream mouseImageFile = new FileInputStream("mouse.png");
        Image mouseImage=new Image(mouseImageFile);
        ImageCursor mouseImageCursor=new ImageCursor(mouseImage);

        /*
         *********************************
         *Time lines*
         *********************************
         */

        KeyFrame fruitInterval = new KeyFrame(Duration.millis(1200), e-> {
            Sphere sphere = creatThrowing();
            if (primaryStage.getScene() == gamePlay){

                throwSound.loop(1);
            gamePlayPane.getChildren().add(sphere);


            sphere.setOnMouseEntered(z -> {
                if (gamePlay.getCursor() == mouseImageCursor) {
                    countScore++;
                    if (sphere.getRadius() == 50) {
                        Speed=2;
                        countScore=0;
                        gamePlayPane.getChildren().removeAll(gamePlayPane.getChildren());
                        gamePlayPane.getChildren().add(gameBorder);
                        primaryStage.setScene(gameOverForm);

                    }

                    sliceSound.loop(1);
                    sphere.setRadius(0);
                    Score.setText(Integer.toString(countScore));
                }
                if (sphere.getRadius() == 50) {
                    gamePlay.setCursor(Cursor.CROSSHAIR);

                }


            });
            sphere.setOnMouseExited(z -> {
                if (gamePlay.getCursor() == Cursor.CROSSHAIR) {
                    gamePlay.setCursor(Cursor.DEFAULT);
                }
            });

        }
        });

        Timeline time = new Timeline(fruitInterval);
        time.setRate(2);
        time.setCycleCount(Timeline.INDEFINITE);


        Timeline timeRate= new Timeline(new KeyFrame(Duration.millis(1000),e->{
            if ((countScore%10==0)&&(countScore!=0)){time.setRate(time.getRate()+Math.random()*Speed*0.02);
                Speed++;
            }


        }));
        timeRate.setCycleCount(Timeline.INDEFINITE);

        Timeline timeStop = new Timeline(new KeyFrame(Duration.millis(100),e->{
            timeStop(time,timeRate);

        }));
        timeStop.play();


        //play button
        Button playButton=new Button();
        playButton.setBackground(Background.EMPTY);
        FileInputStream playPath=new FileInputStream("Start//play.png");
        Image playImage=new Image(playPath);
        ImageView playImageView=new ImageView(playImage);
        playImageView.setFitHeight(100);
        playImageView.setFitWidth(200);
        playButton.setGraphic(playImageView);
        playButton.setOnMouseEntered(e->{
            buttonHoverSound.loop(1);
        });
        playButton.setOnAction(e->{
            buttonClickSound.loop(1);
            primaryStage.setScene(gamePlay);
            time.play();
            timeRate.play();
        });

        

        //credits button
        Button creditsButton=new Button();
        creditsButton.setBackground(Background.EMPTY);
        FileInputStream creditsPath=new FileInputStream("Start//Credits.png");
        Image creditsImage=new Image(creditsPath);
        ImageView creditsImageView=new ImageView(creditsImage);
        creditsImageView.setFitHeight(100);
        creditsImageView.setFitWidth(200);
        creditsButton.setGraphic(creditsImageView);
        creditsButton.setOnMouseEntered(e->{
            buttonHoverSound.loop(1);
        });
        creditsButton.setOnAction(e->{
            buttonClickSound.loop(1);
        });


        //exit button
        Button exitButton=new Button();
        exitButton.setBackground(Background.EMPTY);
        FileInputStream exitPath=new FileInputStream("Start//Exit.png");
        Image exitImage=new Image(exitPath);
        ImageView exitImageView=new ImageView(exitImage);
        exitImageView.setFitHeight(100);
        exitImageView.setFitWidth(200);
        exitButton.setGraphic(exitImageView);
        exitButton.setOnMouseEntered(e->{
            buttonHoverSound.loop(1);
        });
        exitButton.setOnAction(e->{
            primaryStage.close();
            buttonClickSound.loop(1);
        });
        //Try Again button
        Button tryAgainButton=new Button();
        tryAgainButton.setBackground(Background.EMPTY);
        FileInputStream tryAgainPath=new FileInputStream("Start//TryAgain.png");
        Image tryAgainImage=new Image(tryAgainPath);
        ImageView tryAgainImageView=new ImageView(tryAgainImage);
        tryAgainImageView.setFitHeight(100);
        tryAgainImageView.setFitWidth(200);
        tryAgainButton.setGraphic(tryAgainImageView);
        tryAgainButton.setOnMouseEntered(e->{
            buttonHoverSound.loop(1);
        });
        tryAgainButton.setOnAction(e->{
            buttonClickSound.loop(1);
            primaryStage.setScene(gamePlay);
            time.play();
            timeRate.play();

        });



        //Start

        VBox sidebar=new VBox();
        sidebar.maxWidth(100);
        sidebar.setAlignment(Pos.BOTTOM_LEFT);
        sidebar.getChildren().addAll(playButton,creditsButton,exitButton);
        start.setLeft(sidebar);
        FileInputStream startPath=new FileInputStream("Start/Background.jpg");
        Image startImage=new Image(startPath);
        BackgroundImage startBackgroundImage=new BackgroundImage(startImage,BackgroundRepeat.NO_REPEAT,
                                                                              BackgroundRepeat.NO_REPEAT,
                                                                              BackgroundPosition.DEFAULT,
                                                                              BackgroundSize.DEFAULT);
        Background startBackground=new Background(startBackgroundImage);
        start.setBackground(startBackground);
        Scene startForm=new Scene(start,1024,768);

        //GamePlay
        //Tip
        Text Tip =new Text("DON'T HIT THE BIG BOMB");
        Tip.setLayoutX(600);
        Tip.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        Tip.setFill(Color.RED);
        //Score
        Score.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        //Scene
        //FileInputStream backGroundFile =new FileInputStream("Background.jpg");
        //Image backGroundImage=new Image(backGroundFile);
        //*//

        //*//

        BackgroundImage gamePlayBackgroundImage=new BackgroundImage(startImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background gamePlayBackground=new Background(gamePlayBackgroundImage);
        gamePlayPane.setBackground(gamePlayBackground);
        gamePlay.setOnKeyPressed(y-> {
            gamePlay.setCursor(mouseImageCursor);
        });
        gamePlay.setOnKeyReleased(y->{
            gamePlay.setCursor(Cursor.DEFAULT);
        });




        //Back Button
        Button back = new Button();
        FileInputStream backImageFile =new FileInputStream("Back.png");
        Image backImage=new Image(backImageFile);
        ImageView backImageView=new ImageView(backImage);
        backImageView.setFitHeight(50);
        backImageView.setFitWidth(100);

        back.setGraphic(backImageView);
        back.setBackground(Background.EMPTY);
        back.setOnMouseClicked(e->{
            time.stop();
            timeRate.stop();
            Speed=2;
            countScore=0;
            Score.setText(Integer.toString(countScore));
            primaryStage.setScene(startForm);
            gamePlayPane.getChildren().removeAll(gamePlayPane.getChildren());
            gamePlayPane.getChildren().add(gameBorder);

        });

        //Score Image
        FileInputStream scoreImageFile =new FileInputStream("Score.png");
        Image scoreImage=new Image(scoreImageFile);
        ImageView scoreImageView=new ImageView(scoreImage);
        scoreImageView.setFitHeight(50);
        scoreImageView.setFitWidth(150);

        //HPane
        HBox hPane = new HBox();
        hPane.getChildren().add(0,scoreImageView);
        hPane.getChildren().add(1,Tip);
        hPane.setSpacing(180);

        //Borderpane

        gameBorder.setMinWidth(1000);
        gameBorder.setMinHeight(768);
        gameBorder.setBottom(back);
        gameBorder.setTop(hPane);
        //gameBorder.setTop(Tip);
        //BorderPane.setAlignment(Tip, Pos.CENTER);
        gameBorder.setLeft(Score);
        gamePlayPane.getChildren().add(gameBorder);


        //GameOver

        BorderPane.setAlignment(tryAgainButton,Pos.BOTTOM_CENTER);
        tryAgainButton.setAlignment(Pos.CENTER);
        gameOver.setBottom(tryAgainButton);
        FileInputStream gameOverPath=new FileInputStream("Start/GameOver.jpg");
        Image gameOverImage=new Image(gameOverPath);
        BackgroundImage gameOverBackgroundImage=new BackgroundImage(gameOverImage,BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background gameOverBackground=new Background(gameOverBackgroundImage);
        gameOver.setBackground(gameOverBackground);


        //Stage
        primaryStage.setTitle("Fruit Ninja");
        primaryStage.setResizable(false);
        primaryStage.setScene(startForm);
        primaryStage.show();
    }
    public Sphere creatThrowing()  {
        Throwing t;
        t = new Throwing((int)(Math.random()*6));
        return t.sphere;
    }
   public  void timeStop(Timeline t,Timeline tt){
        t.stop();
        tt.stop();
   }
   public void createGamePlayPane(Pane gamePlayPane,BorderPane border){

       gamePlayPane.getChildren().add(border);


   }
    /* public Timeline[] timeStop(Timeline t,Timeline tt){
        Timeline [] tarray= new Timeline[2];
        tarray[0]=t;
        tarray[0].stop();
        tarray[1]=tt;
        tarray[1].stop();
        return tarray;

    }*/


    public static void main(String[] args) {
        launch(args);
    }
}
