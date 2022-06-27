package com.example.fruitninjahassan;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.media.*;
import javafx.util.Duration;
//import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GameMainApplication extends Application {
     int countScore=0;
     int Speed=2;
     int timeToPlayLivesAgain=3700;
     boolean gameOverFlag=false;
    Timeline timer;            //game timer
    StackPane gameFruitPane;   //pane to add fruits on it
    int    playerScore   = 0;  //score variable
    int    gameTimer     = 30; //variable to control the game timer length
    Button backButton    = new Button();
    Label scoreTxt       = new Label("Score : ");
    Label scoreValue     = new Label(playerScore+""); //lable to show the score
    Label timerTxt       = new Label("Timer : ");
    Label timerValue     = new Label(gameTimer+""); //lable to show remaining time

    @Override
    public void start(Stage primaryStage) throws IOException {
        /************************ TIMER MODE SCENE *********************************/

        /************************ Gaming Page Background ***************************/
        Image gameImage = new Image(new FileInputStream("items/gameplay.jpg"));
        ImageView gameView = new ImageView(gameImage);
        gameView.setFitWidth(1150);
        gameView.setFitHeight(680);
        /*************************** Gaming Page Scene *****************************/
        FlowPane backPane = new FlowPane(backButton);
        backPane.setAlignment(Pos.BOTTOM_LEFT);

        HBox scorePane = new HBox(10);  //score nodes added later
        HBox timerPane = new HBox(10);  //timer nodes added later
        Pane gameBackgroundPane = new Pane(gameView); //adding previous gaming view
        gameFruitPane = new StackPane(); //fruits added later

        HBox score_timerPane = new HBox(600,scorePane ,timerPane);
        BorderPane gamePlayPane = new BorderPane(gameFruitPane);
        gamePlayPane.setTop(score_timerPane);
        gamePlayPane.setLeft(backPane);
        StackPane gamePane = new StackPane(gameBackgroundPane , gamePlayPane);
        Scene timerGamingScene = new Scene(gamePane, 1080, 660);

        /*************************** Score Label *************************************/
        scoreTxt.setFont( Font.font("Times New Roman" , FontWeight.BOLD ,50) );
        scoreTxt.setTextFill(Color.CORNSILK);
        scoreValue.setFont( Font.font("Times New Roman" , FontWeight.EXTRA_BOLD ,50) );
        scoreValue.setTextFill(Color.CORNSILK);
        scorePane.getChildren().addAll(scoreTxt,scoreValue);

        /*************************** Timer Label *************************************/
        timerTxt.setFont( Font.font("Times New Roman" , FontWeight.BOLD ,50) );
        timerTxt.setTextFill(Color.DARKCYAN);
        timerValue.setFont( Font.font("Times New Roman" , FontWeight.EXTRA_BOLD ,50) );
        timerValue.setTextFill(Color.DARKGOLDENROD);
        timerPane.getChildren().addAll(timerTxt,timerValue);

        /********************************* Back Button *********************************/

        Image backBtnImage =new Image(new FileInputStream("items/Back.png") );
        ImageView backBtnView = new ImageView(backBtnImage);
        backBtnView.setFitHeight(50);   backBtnView.setFitWidth(125);

        backButton.setGraphic(backBtnView);//added image to button
        backButton.setBackground(Background.EMPTY); //made button hidden to just view image



        /**************** LIVES MODE SCENE ***************/

        Pane pane = new Pane();
        BorderPane startLives=new BorderPane();
        Scene startForm=new Scene(startLives,1024,660);
        Scene livesModeV1 = new Scene(pane, 1024, 660);

        /*********** BOOM MODE SCENES ***********/

        BorderPane start    = new BorderPane();
        BorderPane gameOver = new BorderPane();
        BorderPane gameplay = new BorderPane();
        BorderPane gameChoose = new BorderPane();
        BorderPane gameBorder= new BorderPane();
        Pane MusicPane = new Pane();
        Pane boomPlayPane = new Pane();

        Scene gameOverScene = new Scene(gameOver,1024,660);
        Scene startScene    = new Scene(start,1024,660);
        Scene gameChooseScene = new Scene(gameChoose,1024,660);
        Scene boomPlayScene = new Scene(boomPlayPane,1024,660);


 /************************ Setting The Background ***************/
        FileInputStream fileInputStream_background = new  FileInputStream("Photos//background1.jpg");
        Image im = new Image(fileInputStream_background);
        ImageView imageView = new ImageView(im);

 /****************** Creating Lives Viewer ****************/
        Label label_misses = new Label();
        // Set Lives Label Size & Graphics
        label_misses.setBackground(Background.EMPTY);
        FileInputStream fileInputStream_lives = new FileInputStream("Photos//lives.png");
        Image image_lives = new  Image(fileInputStream_lives);
        ImageView imageView_lives = new  ImageView(image_lives);
        imageView_lives.setFitWidth(100);
        imageView_lives.setFitHeight(50);
        label_misses.setGraphic(imageView_lives);
        // Setting Graphics Of Number Of Lives Remaining

        Circle circle_1stMiss = new Circle(0,0,24, Color.GREEN);
        circle_1stMiss.setStroke(Color.LIGHTGREEN);
        circle_1stMiss.setStrokeWidth(3);

        Circle circle_2ndMiss = new Circle(0,0,24, Color.GREEN);
        circle_2ndMiss.setStroke(Color.LIGHTGREEN);
        circle_2ndMiss.setStrokeWidth(3);

        Circle circle_3rdMiss = new Circle(0,0,24, Color.GREEN);
        circle_3rdMiss.setStroke(Color.LIGHTGREEN);
        circle_3rdMiss.setStrokeWidth(3);

        HBox hBox_misses = new HBox(10);
        // Adding 3 lives Horizontally //
        hBox_misses.getChildren().addAll(circle_1stMiss, circle_2ndMiss, circle_3rdMiss);
        // Combining 3 Lives + Lives Label Vertically //
        VBox vBox_misses = new VBox(10);
        vBox_misses.getChildren().addAll(label_misses, hBox_misses);
 /************* Creating Score Viewer ***************/
        Label label_score = new Label();
        // Set Score Label Size & Graphics //
        label_score.setBackground(Background.EMPTY);
        FileInputStream fileInputStream_score = new FileInputStream("Photos//score.png");
        Image image_score = new Image(fileInputStream_score);
        ImageView imageView_score = new ImageView(image_score);
        imageView_score.setFitWidth(100);
        imageView_score.setFitHeight(50);
        label_score.setGraphic(imageView_score);
        // Creating Score Counter
        Label label_scoreCount = new Label("0");
        label_scoreCount.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,26));
        label_scoreCount.setTextFill(Color.WHITE);
 /**************** Combining Score Label + Score Counter Vertically *******************/
        VBox vBox_score = new VBox(8);
        vBox_score.getChildren().addAll(label_score, label_scoreCount);
 /*************** Combining Lives Viewer + Score Viewer Horizontally ****************/
        HBox hBox_combined = new HBox(690);
        hBox_combined.getChildren().addAll(vBox_score,vBox_misses);
        hBox_combined.setPadding(new Insets(15,20,20,20));
 /****************** create random times for each fruit **************************/
        double [] seconds = new double[4];
        for (int i = 0 ; i < seconds.length ; i++){
            seconds[i] = 0.6 + 3 * Math.random();
        }

 /*************************** LIVES MODE TIME LINES ****************************/
 /*****************  Adding Watermelons ***********/
        KeyFrame watermelonTime = new
                KeyFrame(Duration.seconds(seconds[1]), event -> {
            try {if (primaryStage.getScene()==livesModeV1){
                NinjaFruit watermelon = create_fruit_stream(new FileInputStream("Photos//watermelon.png"),
                                                           new FileInputStream("Photos//halfwatermelon.png"),
                                                           new FileInputStream("Photos//halfwatermelon2.png"));
                watermelon.setEventHandling();

                watermelon.fruit_paths[0].setOnFinished(e-> setLivesColor(circle_1stMiss, circle_2ndMiss, primaryStage,livesModeV1));
                pane.getChildren().add(watermelon);}
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        Timeline timeline_watermelon = new Timeline(watermelonTime);
        timeline_watermelon.setCycleCount(Timeline.INDEFINITE);

 /******************* Adding Apples ******************/
        KeyFrame appleTime = new
                KeyFrame(Duration.seconds(seconds[1]), event -> {
            try {if (primaryStage.getScene()==livesModeV1){
                NinjaFruit apple = create_fruit_stream(new FileInputStream("Photos//apple.png"),
                                                        new FileInputStream("Photos//half_apple.png"),
                                                        new FileInputStream("Photos//half_apple2.png"));
                apple.setEventHandling();
                apple.fruit_paths[0].setOnFinished(e-> setLivesColor(circle_1stMiss, circle_2ndMiss, primaryStage,livesModeV1));
                pane.getChildren().add(apple);}
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        Timeline timeline_apple = new Timeline(appleTime);
        timeline_apple.setCycleCount(Timeline.INDEFINITE);

 /****************** Adding Pineapples ********************/
        KeyFrame pineappleTime = new KeyFrame(Duration.seconds(seconds[2]), event -> {
            try {if (primaryStage.getScene()==livesModeV1){
                NinjaFruit pineapple = create_fruit_stream(new FileInputStream("Photos//pineapple.png"),
                                                           new FileInputStream("Photos//top_pineapple.png"),
                                                            new FileInputStream("Photos//bottom_pineapple.png"));
                pineapple.setEventHandling();
                pineapple.fruit_paths[0].setOnFinished(e-> setLivesColor(circle_1stMiss, circle_2ndMiss, primaryStage,livesModeV1));
                pane.getChildren().add(pineapple);}
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        Timeline timeline_pineapple = new Timeline(pineappleTime);
        timeline_pineapple.setCycleCount(Timeline.INDEFINITE);

         /************** Adding Lemons *****************/
        KeyFrame lemonTime = new KeyFrame(Duration.seconds(seconds[3]), event -> {
            try {if (primaryStage.getScene()==livesModeV1){
                NinjaFruit lemon = create_fruit_stream(new FileInputStream("Photos//lemon.png"),
                                                       new FileInputStream("Photos//half_lemon.png"),
                                                       new FileInputStream("Photos//half_lemon.png"));
                lemon.setEventHandling();
                lemon.fruit_paths[0].setOnFinished(e-> setLivesColor(circle_1stMiss, circle_2ndMiss, primaryStage,livesModeV1));
                pane.getChildren().add(lemon);}
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        Timeline timeline_lemon = new Timeline(lemonTime);
        timeline_lemon.setCycleCount(Timeline.INDEFINITE);

        Timeline lost = new Timeline(new KeyFrame(Duration.millis(100),e->{
            // if player lost , reset the variables to the default
            timeToPlayLivesAgain+=100;
            if(gameOverFlag){
                timeToPlayLivesAgain=0;
                gameOverFlag=false;
                pane.getChildren().removeAll(pane.getChildren());
                primaryStage.setScene(gameOverScene);
                NinjaFruit.score=0;

                pane.getChildren().addAll(imageView, hBox_combined); //re add the needed views to the pane again
                label_scoreCount.setText(NinjaFruit.score + ""); //show the score after reset

            }
            if((primaryStage.getScene()==gameOverScene)||(primaryStage.getScene()==startForm)){// reset the color of lives
                circle_1stMiss.setFill(Color.GREEN);
                circle_2ndMiss.setFill(Color.GREEN);
            }

        }));
        lost.setCycleCount(Timeline.INDEFINITE);
        lost.play();
 /****************** Show Score ****************************/
        Timeline score_show = new Timeline(new KeyFrame(Duration.millis(500),
                e -> label_scoreCount.setText(NinjaFruit.score + "")));
        score_show.setCycleCount(-1);
        score_show.play();
 /******************** Adding Background, Lives Viewer, Score Viewer to a Pane ****************/
        pane.getChildren().addAll(imageView, hBox_combined);
        label_scoreCount.setText(NinjaFruit.score + "");
        imageView.setFitHeight(livesModeV1.getHeight());
        imageView.setFitWidth(livesModeV1.getWidth());
        /**************************************************************************************************/
                        /******************* Bomb Mode ****************************/
        /**************************************************************************************************/

         /************ Audio *****************/
        //background
        Media  backGroundSound= new Media(new File("items/ninja.mp3").toURI().toString());
        MediaPlayer backGroundSound1 = new MediaPlayer(backGroundSound);
        backGroundSound1.setAutoPlay(true);
        backGroundSound1.setCycleCount(-1);
        //buttonHover
        Media  buttonHoverSound= new Media(new File("items/buttonHover.wav").toURI().toString());
        MediaPlayer buttonHoverSound1 = new MediaPlayer(buttonHoverSound);
        //buttonClick
        Media  buttonClickSound= new Media(new File("items/buttonClick.wav").toURI().toString());
        MediaPlayer buttonClickSound1 = new MediaPlayer(buttonClickSound);
        //sliceSound
        Media  sliceSound= new Media(new File("SliceOld.wav").toURI().toString());
        MediaPlayer sliceSound1 = new MediaPlayer(sliceSound);
        //throwSound
        Media  throwSound= new Media (new File("Throw.wav").toURI().toString());
        MediaPlayer throwSound1 = new MediaPlayer(throwSound);

        /********** Score **************/
        Text Score =new Text(Integer.toString(countScore));
        /*********************************
                Mouse Cursor
        **********************************/
        FileInputStream mouseImageFile = new FileInputStream("mouse.png");
        Image mouseImage = new Image(mouseImageFile);
        ImageCursor mouseImageCursor = new ImageCursor(mouseImage);


        /**********************************
              BOMB MODE Time lines
        **********************************/

        KeyFrame fruitInterval = new KeyFrame(Duration.millis(1200), e-> {

            Sphere sphere = creatThrowing(); //create new fruit or boom sphere
            if (primaryStage.getScene() == boomPlayScene){
                throwSound1.play();
                throwSound1.setCycleCount(1);
                boomPlayPane.getChildren().add(sphere);

                sphere.setOnMouseEntered(z -> {
                    if (boomPlayScene.getCursor() == mouseImageCursor) {
                        countScore++;
                        if (sphere.getRadius() == 50) { /******* as the only sphere of r = 50 is the boom *********/
                        //reset all required parameters
                            Speed=2;
                            countScore=0;
                            boomPlayPane.getChildren().removeAll(boomPlayPane.getChildren());
                            boomPlayPane.getChildren().add(gameBorder);
                            primaryStage.setScene(gameOverScene);
                        }
                        sliceSound1.play();
                        sliceSound1.setCycleCount(1);
                        sphere.setRadius(0);
                        Score.setText(Integer.toString(countScore));
                    }
                    if (sphere.getRadius() == 50) {
                        boomPlayScene.setCursor(Cursor.CROSSHAIR);
                    }
                });
                sphere.setOnMouseExited(z -> {
                    if (boomPlayScene.getCursor() == Cursor.CROSSHAIR) {
                        boomPlayScene.setCursor(Cursor.DEFAULT);
                        sliceSound1.stop();
                    }

                });
            }
        });

        Timeline time  = new Timeline(fruitInterval);
        time.setRate(2);
        time.setCycleCount(Timeline.INDEFINITE);

        Timeline timeRate= new Timeline(new KeyFrame(Duration.millis(1000),e->{
            if ((countScore%10==0)&&(countScore!=0)){time.setRate(time.getRate()+Math.random()*Speed*0.02);
                Speed++;
            }
        }));
        timeRate.setCycleCount(Timeline.INDEFINITE);

        Timeline timeStop = new Timeline(new KeyFrame(Duration.millis(100),e->{
            if(primaryStage.getScene()==gameOverScene)
                timeStop(time,timeRate);

        }));
        timeStop.play();

        /*********** BUTTONS *************/
        // play button
             Button playButton = new Button();
             playButton.setBackground(Background.EMPTY);
             FileInputStream playPath=new FileInputStream("items/play.png");
             Image playImage=new Image(playPath);
             ImageView playImageView=new ImageView(playImage);
             playImageView.setFitHeight(100);
             playImageView.setFitWidth(200);
             playButton.setGraphic(playImageView);

            playButton.setOnMouseEntered(e->{
            buttonHoverSound1.play();
            buttonHoverSound1.setCycleCount(1);
        });
             playButton.setOnMouseExited(e-> buttonHoverSound1.stop());
             playButton.setOnAction(e->{
            buttonClickSound1.play();
            primaryStage.setScene(gameChooseScene);
            buttonClickSound1.stop();
        });



    ////////////////exit button
        Button exitButton=new Button();
        exitButton.setBackground(Background.EMPTY);
        FileInputStream exitPath=new FileInputStream("items/Exit.png");
        Image exitImage=new Image(exitPath);
        ImageView exitImageView=new ImageView(exitImage);
        exitImageView.setFitHeight(100);
        exitImageView.setFitWidth(200);
        exitButton.setGraphic(exitImageView);
        exitButton.setOnMouseEntered(e->{
            buttonHoverSound1.play();
            buttonHoverSound1.setCycleCount(1);
        });
        exitButton.setOnMouseExited(e-> buttonHoverSound1.stop());
        exitButton.setOnAction(e->primaryStage.close());


    //////////////// credit button
        Button creditsButton=new Button();
        creditsButton.setBackground(Background.EMPTY);
        FileInputStream creditsPath=new FileInputStream("items/Credits.png");
        Image creditsImage=new Image(creditsPath);
        ImageView creditsImageView=new ImageView(creditsImage);
        creditsImageView.setFitHeight(100);
        creditsImageView.setFitWidth(200);
        creditsButton.setGraphic(creditsImageView);
        creditsButton.setOnMouseEntered(e->{
            buttonHoverSound1.play();
            buttonHoverSound1.setCycleCount(1);
        });
        creditsButton.setOnMouseExited(e->buttonHoverSound1.stop());
        creditsButton.setOnAction(e->{
            buttonClickSound1.play();
            buttonClickSound1.setCycleCount(1);
           // primaryStage.setScene(gameoverscene);
            buttonClickSound1.stop();
        });

        /***********  make like animation when clicked or released ***********/
        creditsButton.setOnMousePressed( e ->{
            creditsImageView.setFitHeight(85);
            creditsImageView.setFitWidth(185);
        });
        creditsButton.setOnMouseReleased( e -> {
            creditsImageView.setFitHeight(100);
            creditsImageView.setFitWidth(200);
        });

    /////////////// try again button
        Button tryagainbutton = new Button();
        tryagainbutton.setBackground(Background.EMPTY);
        FileInputStream tryagainpath = new FileInputStream("items/TryAgain.png");
        Image tryagainimage = new Image(tryagainpath);
        ImageView tryagainimageview = new ImageView(tryagainimage);
        tryagainimageview.setFitHeight(60);
        tryagainimageview.setFitWidth(100);
        tryagainbutton.setGraphic(tryagainimageview);
        tryagainbutton.setOnMouseEntered(e->{
            buttonHoverSound1.play();
            buttonHoverSound1.setCycleCount(1);
        });
        tryagainbutton.setOnMouseExited(e-> buttonHoverSound1.stop());
        tryagainbutton.setOnAction(e->{
            buttonClickSound1.play();
            primaryStage.setScene(gameChooseScene);
            buttonClickSound1.stop();
        });


        /////////////// home button
        Button homebutton = new Button();
        homebutton.setBackground(Background.EMPTY);
        FileInputStream homepath = new FileInputStream("items/home.png");
        Image homeimage = new Image(homepath);
        ImageView homeimageview = new ImageView(homeimage);
        homeimageview.setFitHeight(50);
        homeimageview.setFitWidth(50);
        homebutton.setGraphic(homeimageview);
        homebutton.setOnMouseEntered(e->{
            buttonHoverSound1.play();
            buttonHoverSound1.setCycleCount(1);
        });
        homebutton.setOnMouseExited(e-> buttonHoverSound1.stop());
        homebutton.setOnAction(e->{
            buttonClickSound1.play();
            primaryStage.setScene(startScene);
            buttonClickSound1.stop();
        });

        /************* timer back buttun *********************/
        backButton.setOnMousePressed( e -> {
            /***********  make like animation when clicked or released ***********/
            backBtnView.setFitHeight(55);
            backBtnView.setFitWidth(110);
        });
        backButton.setOnMouseReleased( e -> {
            backBtnView.setFitHeight(65);
            backBtnView.setFitWidth(120);
            /******************* Go to Game Over scene  **********************/
            primaryStage.setScene(gameChooseScene);
            endRandThrow(gameFruitPane);
        });

        /////////////// back home button
        Button backtohomebutton = new Button();
        backtohomebutton.setBackground(Background.EMPTY);
        FileInputStream backpath = new FileInputStream("items/Back.png");
        Image backimage = new Image(backpath);
        ImageView backimageview = new ImageView(backimage);
        backimageview.setFitHeight(50);
        backimageview.setFitWidth(100);
        backtohomebutton.setGraphic(backimageview);
        backtohomebutton.setOnMouseEntered(e->{
            buttonHoverSound1.play();
            buttonHoverSound1.setCycleCount(1);
        });
        backtohomebutton.setOnMouseExited(e-> buttonHoverSound1.stop());
        backtohomebutton.setOnAction(e->{
            buttonClickSound1.play();
            primaryStage.setScene(startScene);
            buttonClickSound1.stop();
        });

        /////////////// back choose button
        Button backBoomToChooseButton = new Button();
        backBoomToChooseButton.setBackground(Background.EMPTY);
        ImageView backBoomToChooseimageview = new ImageView(backimage);
        backBoomToChooseimageview.setFitHeight(50);
        backBoomToChooseimageview.setFitWidth(100);
        backBoomToChooseButton.setGraphic(backBoomToChooseimageview);
        backBoomToChooseButton.setOnAction(e-> {
            buttonClickSound1.play();
            buttonClickSound1.setCycleCount(1);
            time.stop();
            timeRate.stop();
            Speed=2;
            countScore=0;
            Score.setText(Integer.toString(countScore));
            primaryStage.setScene(gameChooseScene);
            boomPlayPane.getChildren().removeAll(boomPlayPane.getChildren());
            boomPlayPane.getChildren().add(gameBorder);
            buttonClickSound1.stop();});

        /////////////// boommode button
        Button boomModeButton = new Button();
        boomModeButton.setBackground(Background.EMPTY);
        FileInputStream boomimagepath = new FileInputStream("items/boom1.png");
        Image boomImage = new Image(boomimagepath);
        ImageView boomImageView = new ImageView(boomImage);
        boomImageView.setFitHeight(100);
        boomImageView.setFitWidth(200);
        boomModeButton.setGraphic(boomImageView);
        boomModeButton.setOnMouseEntered(e->{
            buttonHoverSound1.play();
            buttonHoverSound1.setCycleCount(1);
        });
        boomModeButton.setOnMouseExited(e-> buttonHoverSound1.stop());
        boomModeButton.setOnAction(e->{
            buttonClickSound1.play();
            BombModePlay(primaryStage,boomPlayScene,time,timeRate);
            buttonClickSound1.stop();


        });

        /////////////// timer mode button
        Button timerModeButton = new Button();
        timerModeButton.setBackground(Background.EMPTY);
        FileInputStream timerImagePath = new FileInputStream("items/timer.png");
        Image timerImage  = new Image(timerImagePath);
        ImageView timerImageView = new ImageView(timerImage);
        timerImageView.setFitHeight(100);
        timerImageView.setFitWidth(200);
        timerModeButton.setGraphic(timerImageView);
        timerModeButton.setOnMouseEntered(e->{
            buttonHoverSound1.play();
            buttonHoverSound1.setCycleCount(1);
        });
        timerModeButton.setOnMouseExited(e-> buttonHoverSound1.stop());
        timerModeButton.setOnAction(e->{
            buttonClickSound1.play();
            buttonClickSound1.stop();
        });
        timerModeButton.setOnAction(e->{
            startTimerMode(primaryStage,timerGamingScene,gameFruitPane);
        });

        /////////////// lives mode button
        Button livesModeButton = new Button();
        livesModeButton.setBackground(Background.EMPTY);
        FileInputStream livesImagePath = new FileInputStream("items/lives1.png");
        Image livesImage = new Image(livesImagePath);
        ImageView livesImageView = new ImageView(livesImage);
        livesImageView.setFitHeight(100);
        livesImageView.setFitWidth(200);
        livesModeButton.setGraphic(livesImageView);
        livesModeButton.setOnMouseEntered(e->{
            buttonHoverSound1.play();
            buttonHoverSound1.setCycleCount(1);
        });
        livesModeButton.setOnMouseExited(e-> buttonHoverSound1.stop());

        livesModeButton.setOnAction(e->{
            buttonClickSound1.play();
            livesModePlay(primaryStage,livesModeV1,timeline_apple ,
                    timeline_lemon,
                    timeline_pineapple,
                    timeline_watermelon);
                    buttonClickSound1.stop();
        });
        /**************** back lives to choose button ***************/
        Button backLivesToChooseButton = new Button();
        backLivesToChooseButton.setBackground(Background.EMPTY);
        ImageView backLivesToChooseImageView = new ImageView(backimage);
        backLivesToChooseImageView.setFitHeight(50);
        backLivesToChooseImageView.setFitWidth(100);
        backLivesToChooseButton.setGraphic(backLivesToChooseImageView);
        

        /********** close sound ************/
        Button playMusic = new Button();
        Button stopMusic = new Button();
        stopMusic.setVisible(false);

        playMusic.setBackground(Background.EMPTY);
        FileInputStream playMusicPath=new FileInputStream("items/playsound.png");
        Image playMusicImage=new Image(playMusicPath);
        ImageView playMusicImageView=new ImageView(playMusicImage);
        playMusicImageView.setFitHeight(50);
        playMusicImageView.setFitWidth(50);
        playMusic.setGraphic(playMusicImageView);

        stopMusic.setBackground(Background.EMPTY);
        FileInputStream stopMusicPath=new FileInputStream("items/stopsound.png");
        Image stopMusicImage=new Image(stopMusicPath);
        ImageView stopMusicImageView=new ImageView(stopMusicImage);
        stopMusicImageView.setFitHeight(50);
        stopMusicImageView.setFitWidth(50);
        stopMusic.setGraphic(stopMusicImageView);

        stopMusic.setOnAction(e->{backGroundSound1.play();playMusic.setVisible(true);stopMusic.setVisible(false);});
        playMusic.setOnAction(e->{backGroundSound1.pause();stopMusic.setVisible(true);playMusic.setVisible(false);});
        MusicPane.getChildren().addAll(playMusic,stopMusic);


    /************ create scenes *****************/
    ////////////// start scene  /////////////////////////////////////////////////////////
        HBox startControl = new HBox();
        startControl.setAlignment(Pos.BOTTOM_CENTER);
        startControl.getChildren().addAll(playButton,creditsButton,exitButton);
        FileInputStream startimagepath = new FileInputStream("items/start.png");
        Image startimage = new Image(startimagepath);
        BackgroundImage startBackGroundImage = new BackgroundImage(startimage, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT ,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        Background startBackGround = new Background(startBackGroundImage);
        start.setBackground(startBackGround);
        start.setTop(MusicPane);
        start.setLeft(startControl);

   /********************** game play scene *********************************/
        //Score Image
        FileInputStream scoreimagefile =new FileInputStream("items/Score.png");
        Image scoreimage=new Image(scoreimagefile);
        ImageView scoreimageview=new ImageView(scoreimage);
        scoreimageview.setFitHeight(50);
        scoreimageview.setFitWidth(150);
        Pane scorepane = new Pane();
        scorepane.getChildren().add(scoreimageview);
    ////////////////////////////////////////
        HBox gameplaycontrol = new HBox();
        gameplaycontrol.setAlignment(Pos.BOTTOM_CENTER);
        gameplaycontrol.getChildren().addAll(backBoomToChooseButton);
        FileInputStream gameplayimagepath = new FileInputStream("items/gameplay.jpg");
        Image gameplayimage = new Image(gameplayimagepath);
        BackgroundImage gameplaybackgroundimage = new BackgroundImage(gameplayimage, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT ,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        Background gameplaybackground = new Background(gameplaybackgroundimage);
        gameplay.setBackground(gameplaybackground);
        gameplay.setTop(scorepane);
        gameplay.setLeft(gameplaycontrol);

        ////////////// gameo over scene ////////////////////////////////////////////////////
        HBox gameOverControl = new HBox();
        gameOverControl.setAlignment(Pos.BOTTOM_CENTER);
        gameOverControl.getChildren().addAll(homebutton,tryagainbutton);
        FileInputStream gameoverimagepath = new FileInputStream("items/GameOver.jpg");
        Image gameoverimage = new Image(gameoverimagepath);
        BackgroundImage gameOverBackGroundImage = new BackgroundImage(gameoverimage, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT ,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        Background gameoverbackground = new Background(gameOverBackGroundImage);
        gameOver.setBackground(gameoverbackground);
        gameOver.setLeft(gameOverControl);

        /***************************** game choose scene ***********************************/
        VBox gameChooseControl = new VBox();
        gameChooseControl.setAlignment(Pos.CENTER);
        gameChooseControl.getChildren().addAll(boomModeButton,timerModeButton,livesModeButton);
        FileInputStream gameChooseImagePath = new FileInputStream("items/choosescene.png");
        Image gameChooseImage = new Image(gameChooseImagePath);
        BackgroundImage gameChooseBackGroundImage = new BackgroundImage(gameChooseImage, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT ,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        Background gameChooseBackground = new Background(gameChooseBackGroundImage);
        gameChoose.setBackground(gameChooseBackground);
        gameChoose.setBottom(backtohomebutton);
        gameChoose.setCenter(gameChooseControl);


        //Boom play scene
        //Tip
        Text Tip =new Text("DON'T HIT THE BIG BOMB");
        Tip.setLayoutX(600);
        Tip.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        Tip.setFill(Color.RED);
        //Score
        Score.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));

        BackgroundImage gamePlayBackgroundImage=new BackgroundImage(gameplayimage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background gamePlayBackground=new Background(gamePlayBackgroundImage);
        boomPlayPane.setBackground(gamePlayBackground);
        boomPlayScene.setOnKeyPressed(y-> boomPlayScene.setCursor(mouseImageCursor));
        boomPlayScene.setOnKeyReleased(y-> boomPlayScene.setCursor(Cursor.DEFAULT));
        //HPane
        HBox hPane = new HBox();
        hPane.getChildren().add(0,scoreimageview);
        hPane.getChildren().add(1,Tip);
        hPane.setSpacing(180);

        //Borderpane

        gameBorder.setMinWidth(1000);
        gameBorder.setMinHeight(768);
        gameBorder.setBottom(backBoomToChooseButton);
        gameBorder.setTop(hPane);

        gameBorder.setLeft(Score);
        boomPlayPane.getChildren().add(gameBorder);


        /******************** TIMER MODE TimeLines   *********************************/

        timer = new Timeline(new KeyFrame(Duration.millis(1000),e->{
            gameTimer--;
            if(gameTimer == 0){ //if time of current game ended then reset game parameters
                timer.stop(); //stop it so it don't run in background
                gameTimer = 30; //reset timing value
                playerScore = 0; //reset player score
                timerValue.setText(gameTimer+""); //update lables
                scoreValue.setText(playerScore+""); //update lables

                primaryStage.setScene(gameOverScene); //show game over scene
            }
            else
                timerValue.setText(gameTimer+""); //if time didn't end yet then update timer lable with current remaining time
        }));
        timer.setCycleCount(Timeline.INDEFINITE);

        /**
        ************** stage show ****************
        **/
        primaryStage.setTitle("Fruit Ninja!");
        primaryStage.setResizable(false);
        primaryStage.setScene(startScene);
        primaryStage.show();
    }
    public Sphere creatThrowing()  {
        return new Throwing((int)(Math.random()*6)).sphere;
    }
    public  void timeStop(Timeline t,Timeline tt){
        t.stop();
        tt.stop();
    }
    /************** V1.01 **************/
    public NinjaFruit create_fruit_stream(FileInputStream whole_fruit, FileInputStream half_1, FileInputStream half_2) throws FileNotFoundException {
        return new NinjaFruit(whole_fruit, half_1, half_2);
    }
    public void setLivesColor(Circle circle, Circle circle1,Stage stage,Scene playing) {
        if(stage.getScene()==playing){
            if (circle1.getFill().equals(Color.RED)) {
                gameOverFlag=true;
            }
            else if (circle.getFill().equals(Color.RED)){
                circle1.setFill(Color.RED);
            }
            else {
                circle.setFill(Color.RED);
            }}
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
/****************************** related to timer mode ***************************/
    /** randomize keyFrame so we use the timeline in the method */
    KeyFrame randomKey = new KeyFrame(Duration.millis(1000) ,
            randomizeEvent->{
                //each second create new sphere with random radius and set its arc path with random x radius and y radius
                Sphere f = new Fruit(30+Math.random()*10,400+Math.random()*400,500+Math.random()*600).sphere;
                gameFruitPane.getChildren().add( f ); //add that sphere
                f.setOnMouseEntered(hitEvent->{ //if sphere was touched
                    gameFruitPane.getChildren().remove( f ); // remove that single sphere
                    playerScore++; //then increase the score and update the lable
                    scoreValue.setText(playerScore+"");
                    if(playerScore%15 == 0 && playerScore >= 0) { //if the score is dividable with 15 . . increase game timer by 10
                        gameTimer += 10;
                        timerValue.setText(gameTimer+""); // update new time in the lable
                    }
                });

            });
    Timeline randomize= new Timeline(randomKey); //create the timeline but don't run it yet

    // method to start timer mode with defaut score and game timer on the gaming scene
    void startTimerMode(Stage stage  , Scene timerScene , StackPane fruitPane){
        stage.setScene(timerScene);
        playerScore = 0;
        gameTimer =30;
        timerValue.setText(gameTimer+"");
        scoreValue.setText(playerScore+"");
        timer.play(); // start the game timer counting
        endRandThrow(fruitPane); // end or delete any old throwing if exists
        startRandThrow(); // start new random throwing
    }

    void startRandThrow(){
        randomize.setCycleCount(Timeline.INDEFINITE);
        //randomize.setRate(1); // is supposed to be changed periodicly to increase game throwing speed
        randomize.play(); //start creating spheres randomly
    }
    void endRandThrow(StackPane pane){
        randomize.pause(); // end random spheres creation
        pane.getChildren().removeAll(pane.getChildren()); //remove all the spheres on the fruit pane to be ready for new gaming trials
    }
    public static void main(String[] args) {
        launch();
    }
}