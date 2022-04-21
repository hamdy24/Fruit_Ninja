package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.sound.sampled.*;
import java.beans.EventHandler;
import java.io.*;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        //sounds
        //background
        File backGroundSoundFile =new File("Sounds/ElevatorMusic.wav").getAbsoluteFile();
        AudioInputStream backGroundInputStream = AudioSystem.getAudioInputStream(backGroundSoundFile);
        Clip backGroundSound=AudioSystem.getClip();
        backGroundSound.open(backGroundInputStream);
        backGroundSound.start();
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
            System.out.println("play");
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
            System.out.println("tryAgain");

        });


        //Start
        BorderPane start=new BorderPane();
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

        //GameOver
        BorderPane gameOver=new BorderPane();
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
        Scene gameOverForm=new Scene(gameOver,1024,768);

        //Stage
        primaryStage.setTitle("Fruit Ninja");
        primaryStage.setResizable(false);
        primaryStage.setScene(startForm);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
