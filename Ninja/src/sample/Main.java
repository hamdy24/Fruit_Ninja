package sample;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
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
    public static boolean gameOverFlag=false;
    public static int timeToPlayLivesAgain=3700;

    @Override
    public void start(Stage primaryStage) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        /*********************** V1.01 *********** LIVES MODE ***************/
        BorderPane gameOver=new BorderPane();
        Scene gameOverForm=new Scene(gameOver,1024,768);
        BorderPane start=new BorderPane();
        Scene startForm=new Scene(start,1024,768);

            Pane pane = new Pane();
        Scene livesModeV1 = new Scene(pane, 1000, 600);
 /*
 Setting The Background
 */
            FileInputStream fileInputStream_background = new
                    FileInputStream("Photosv1.01//background1.jpg");
            Image im = new Image(fileInputStream_background);
            ImageView imageView = new ImageView(im);
 /*
 Creating Lives Viewer
 */
            Label label_misses = new Label();
            // Set Lives Label Size & Graphics //
            label_misses.setBackground(Background.EMPTY);
            FileInputStream fileInputStream_lives = new
                    FileInputStream("Photosv1.01//lives.png");
            Image image_lives = new
                    Image(fileInputStream_lives);
            ImageView imageView_lives = new
                    ImageView(image_lives);
            imageView_lives.setFitWidth(100);
            imageView_lives.setFitHeight(50);
            label_misses.setGraphic(imageView_lives);
            // Setting Graphics Of Number Of Lives Remaining
//
            Circle circle_1stmiss = new Circle(0,0,24,
                    Color.GREEN);
            circle_1stmiss.setStroke(Color.LIGHTGREEN);
            circle_1stmiss.setStrokeWidth(3);
            Circle circle_2ndmiss = new Circle(0,0,24,
                    Color.GREEN);
            circle_2ndmiss.setStroke(Color.LIGHTGREEN);
            circle_2ndmiss.setStrokeWidth(3);
            Circle circle_3rdmiss = new Circle(0,0,24,
                    Color.GREEN);
            HBox hBox_misses = new HBox(10);
            circle_3rdmiss.setStroke(Color.LIGHTGREEN);
            circle_3rdmiss.setStrokeWidth(3);
            // Adding 3 lives Horizontally //
            hBox_misses.getChildren().addAll(circle_1stmiss,
                    circle_2ndmiss, circle_3rdmiss);
            // Combining 3 Lives + Lives Label Vertically //
            VBox vBox_misses = new VBox(10);
            vBox_misses.getChildren().addAll(label_misses,
                    hBox_misses);
 /*
 Creating Score Viewer
 */
            Label label_score = new Label();
            // Set Score Label Size & Graphics //
            label_score.setBackground(Background.EMPTY);
            FileInputStream fileInputStream_score = new
                    FileInputStream("Photosv1.01//score.png");
            Image image_score = new
                    Image(fileInputStream_score);
            ImageView imageView_score = new
                    ImageView(image_score);
            imageView_score.setFitWidth(100);
            imageView_score.setFitHeight(50);
            label_score.setGraphic(imageView_score);
            // Creating Score Counter //
            Label label_scoreCount = new Label("0");
            label_scoreCount.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,26));
                    label_scoreCount.setTextFill(Color.WHITE);
 /*
 Combining Score Label + Score Counter Vertically
 */
            VBox vBox_score = new VBox(8);
            vBox_score.getChildren().addAll(label_score,
                    label_scoreCount);
 /*
 Combining Lives Viewer + Score Viewer
Horizontally
 */
            HBox hBox_combined = new HBox(690);

            hBox_combined.getChildren().addAll(vBox_score,vBox_misses
            );
            hBox_combined.setPadding(new
                    Insets(15,20,20,20));
            double [] seconds = new double[4];
            for (int i = 0 ; i < seconds.length ; i++){
                seconds[i] = 1 + 3 * Math.random();
            }
 /*
 Adding Watermelons
 */
            KeyFrame watermelonTime = new
                    KeyFrame(Duration.seconds(seconds[1]), event -> {
                try {if (primaryStage.getScene()==livesModeV1){
                    NinjaFruit watermelon =
                            create_fruit_stream(new FileInputStream("Photosv1.01//watermelon.png"),
                                    new FileInputStream("Photosv1.01//halfwatermelon.png"),
                                    new FileInputStream("Photosv1.01//halfwatermelon2.png"));
                    watermelon.setEventHandling();

                    watermelon.fruit_paths[0].setOnFinished(e->{
                            setLivesColor(circle_1stmiss, circle_2ndmiss,
                                    circle_3rdmiss,primaryStage,livesModeV1);});
                    pane.getChildren().add(watermelon);}
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            });
            Timeline timeline_watermelon = new
                    Timeline(watermelonTime);

            timeline_watermelon.setCycleCount(Timeline.INDEFINITE);
            //timeline_watermelon.play();
 /*
 Adding Apples
 */
            KeyFrame appleTime = new
                    KeyFrame(Duration.seconds(seconds[1]), event -> {
                try {if (primaryStage.getScene()==livesModeV1){
                    NinjaFruit apple =
                            create_fruit_stream(new FileInputStream("Photosv1.01//apple.png"),
                                    new FileInputStream("Photosv1.01//half_apple.png"),
                                    new FileInputStream("Photosv1.01//half_apple2.png"));
                    apple.setEventHandling();
                    apple.fruit_paths[0].setOnFinished(e->{
                          setLivesColor(circle_1stmiss, circle_2ndmiss,
                                  circle_3rdmiss,primaryStage,livesModeV1);});
                    pane.getChildren().add(apple);}
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            });
            Timeline timeline_apple = new
                    Timeline(appleTime);

            timeline_apple.setCycleCount(Timeline.INDEFINITE);
            //timeline_apple.play();
 /*
 Adding Pineapples
 */
            KeyFrame pineappleTime = new
                    KeyFrame(Duration.seconds(seconds[2]), event -> {
                try {if (primaryStage.getScene()==livesModeV1){
                    NinjaFruit pineapple = create_fruit_stream(new FileInputStream("Photosv1.01//pineapple.png"),
                                    new FileInputStream("Photosv1.01//top_pineapple.png"),
                                    new FileInputStream("Photosv1.01//bottom_pineapple.png"));
                    pineapple.setEventHandling();
                    pineapple.fruit_paths[0].setOnFinished(e->{
                            setLivesColor(circle_1stmiss, circle_2ndmiss,
                                    circle_3rdmiss,primaryStage,livesModeV1);});
                    pane.getChildren().add(pineapple);}
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            });
            Timeline timeline_pineapple = new
                    Timeline(pineappleTime);

            timeline_pineapple.setCycleCount(Timeline.INDEFINITE);
            //timeline_pineapple.play();
 /*
 Adding Lemons
 */
            KeyFrame lemonTime = new
                    KeyFrame(Duration.seconds(seconds[3]), event -> {
                try {if (primaryStage.getScene()==livesModeV1){
                    NinjaFruit lemon =
                            create_fruit_stream(new
                                            FileInputStream("Photosv1.01//lemon.png"),
                                    new
                                            FileInputStream("Photosv1.01//half_lemon.png"),
                                    new
                                            FileInputStream("Photosv1.01//half_lemon.png"));
                    lemon.setEventHandling();
                    lemon.fruit_paths[0].setOnFinished(e->{
                         setLivesColor(circle_1stmiss, circle_2ndmiss,
                                circle_3rdmiss,primaryStage,livesModeV1);});
                   pane.getChildren().add(lemon);}
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            });
            Timeline timeline_lemon = new
                    Timeline(lemonTime);

            timeline_lemon.setCycleCount(Timeline.INDEFINITE);
            //timeline_lemon.play();
            Timeline lost = new Timeline(new KeyFrame(Duration.millis(100),e->{
                timeToPlayLivesAgain+=100;
                if(gameOverFlag){
                    timeToPlayLivesAgain=0;
                    gameOverFlag=false;
                    pane.getChildren().removeAll(pane.getChildren());
                    primaryStage.setScene(gameOverForm);
                    //timeline_apple.stop();
                    //timeline_lemon.stop();
                    //timeline_pineapple.stop();
                    //timeline_watermelon.stop();
                    NinjaFruit.score=0;

                    pane.getChildren().addAll(imageView,
                            hBox_combined);
                    label_scoreCount.setText(NinjaFruit.score + "");



                }
                if((primaryStage.getScene()==gameOverForm)||(primaryStage.getScene()==startForm)){
                    circle_1stmiss.setFill(Color.GREEN);
                    circle_2ndmiss.setFill(Color.GREEN);

                }

            }));
            lost.setCycleCount(Timeline.INDEFINITE);
            lost.play();
 /*
 Show Score
 */
            Timeline score_show = new Timeline(new
                    KeyFrame(Duration.millis(500),
                    e ->
                            label_scoreCount.setText(NinjaFruit.score + "")));
            score_show.setCycleCount(-1);
            score_show.play();
 /*
 Adding Background, Lives Viewer, Score Viewer to
a Pane
 */
            // setScoreCount(watermelon.getScore,label_scoreCount);
            pane.getChildren().addAll(imageView,
                    hBox_combined);
            label_scoreCount.setText(NinjaFruit.score + "");
 /*
 Adding Pane to Scene
 */

            imageView.setFitHeight(livesModeV1.getHeight());
            imageView.setFitWidth(livesModeV1.getWidth());
 /*
 Creating Stage
 */
            primaryStage.setResizable(false);
            primaryStage.setTitle("Fruit Ninja");
       /***************************  ***************************/

       /******************* Bomb Mode***************************/

            /*
         *********************************
         *Scenes*
         *********************************
         */

        BorderPane gameBorder= new BorderPane();

        Pane gamePlayPane = new Pane();
        Scene gamePlay = new Scene(gamePlayPane, 1024, 768);



        /*
         *********************************
         *Audio*
         *********************************
         */
        //sliceSound

        /*File sliceFile =new File("Slice.wav").getAbsoluteFile();
        AudioInputStream sliceInputStream = AudioSystem.getAudioInputStream(sliceFile);
        Clip sliceSound=AudioSystem.getClip();
        sliceSound.open(sliceInputStream);*/
        //throwSound

        /*File throwFile =new File("Throw.wav").getAbsoluteFile();
        AudioInputStream throwInputStream = AudioSystem.getAudioInputStream(throwFile);
        Clip throwSound=AudioSystem.getClip();
        throwSound.open(throwInputStream);*/


        //background

        /*File backGroundFile =new File("Sounds/ElevatorMusic.wav").getAbsoluteFile();
        AudioInputStream backGroundInputStream = AudioSystem.getAudioInputStream(backGroundFile);
        Clip backGroundSound=AudioSystem.getClip();
        backGroundSound.open(backGroundInputStream);
        backGroundSound.loop(Clip.LOOP_CONTINUOUSLY);*/

        //buttonHover

        /*File buttonHoverFile =new File("Sounds/buttonHover.wav").getAbsoluteFile();
        AudioInputStream buttonHoverInputStream = AudioSystem.getAudioInputStream(buttonHoverFile);
        Clip buttonHoverSound=AudioSystem.getClip();
        buttonHoverSound.open(buttonHoverInputStream);*/

        //buttonClick

        /*File buttonClickFile =new File("Sounds/buttonClick.wav").getAbsoluteFile();
        AudioInputStream buttonClickInputStream = AudioSystem.getAudioInputStream(buttonClickFile);
        Clip buttonClickSound=AudioSystem.getClip();
        buttonClickSound.open(buttonClickInputStream);*/

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

                //throwSound.loop(1);
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

                    //sliceSound.loop(1);
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
            //buttonHoverSound.loop(1);
        });
        playButton.setOnAction(e->{
            //buttonClickSound.loop(1);
            /***** to play lives mode *********/
            /*livesModePlay(primaryStage,livesModeV1,timeline_apple ,
                    timeline_lemon,
                    timeline_pineapple,
                    timeline_watermelon);*/
            /*******to play bomb mode*************/
           //BombModePlay(primaryStage,gamePlay,time,timeRate);
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
            //buttonHoverSound.loop(1);
        });
        creditsButton.setOnAction(e->{
            //buttonClickSound.loop(1);
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
            //buttonHoverSound.loop(1);
        });
        exitButton.setOnAction(e->{
            primaryStage.close();
            //buttonClickSound.loop(1);
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
            //buttonHoverSound.loop(1);
        });
        tryAgainButton.setOnAction(e->{
            //buttonClickSound.loop(1);
            primaryStage.setScene(startForm);
            //time.play();
            //timeRate.play();

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
    /************** V1.01 **************/
    public NinjaFruit create_fruit_stream(FileInputStream
                                                  whole_fruit,
                                          FileInputStream
                                                  half_1,
                                          FileInputStream
                                                  half_2) throws FileNotFoundException {
        return new NinjaFruit(whole_fruit, half_1,
                half_2);
    }
    public void setLivesColor(Circle circle, Circle
            circle1, Circle circle2,Stage stage,Scene playing) {
        if(stage.getScene()==playing){
        if (circle1.getFill().equals(Color.RED)) {
            // RAAAAAAAAAAAAMYYYYYYYYYYYYYYY
            // HEEEEEEEEEEEEEEEEEEEEEEEEREEEEEEEEEEEEEEE
            // add Game Over Scene
            gameOverFlag=true;




        }
        else if (circle.getFill().equals(Color.RED)){
            circle1.setFill(Color.RED);
        }
        else {
            circle.setFill(Color.RED);
        }}
    }
    public void gameOverExcuter(Stage stage,Scene over,Timeline t1,Timeline t2,Timeline t3){
        stage.setScene(over);
        t1.stop();
        t2.stop();
        t3.stop();
    }
    public void livesModePlay(Stage primaryStage,Scene livesModeV1,Timeline timeline_apple ,
                              Timeline timeline_lemon,
                              Timeline timeline_pineapple,
                              Timeline timeline_watermelon){
        if(timeToPlayLivesAgain>=3700){
            primaryStage.setScene(livesModeV1);
            timeline_apple.play();
            timeline_lemon.play();
            timeline_pineapple.play();
            timeline_watermelon.play();}
    }
    public void BombModePlay(Stage primaryStage,Scene gamePlay,Timeline time,Timeline timeRate){
        primaryStage.setScene(gamePlay);
        time.play();
        timeRate.play();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
