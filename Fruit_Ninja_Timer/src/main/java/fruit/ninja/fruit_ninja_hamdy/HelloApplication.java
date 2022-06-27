package fruit.ninja.fruit_ninja_hamdy;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.IOException;

public class HelloApplication extends Application {
    Timeline timer;            //game timer
    StackPane gameFruitPane;   //pane to add fruits on it
    int    playerScore   = 0;  //score variable
    int    gameTimer     = 30; //variable to control the game timer length

    Button playButton    = new Button();
    Button creditsButton = new Button();
    Button tryAgainButton= new Button();
    Button homeButton    = new Button();
    Button exitButton    = new Button();
    Button backButton    = new Button();
    Label scoreTxt       = new Label("Score : ");
    Label scoreValue     = new Label(playerScore+""); //lable to show the score
    Label timerTxt       = new Label("Timer : ");
    Label timerValue     = new Label(gameTimer+""); //lable to show remaining time
    @Override
    public void start(Stage stage) throws IOException {

        /************************ Starting Page Background ***************************/
        Image startImage = new Image(new FileInputStream("src/main/resources/R.png"));
        ImageView startView = new ImageView(startImage);
        startView.setFitWidth(1150);
        startView.setFitHeight(680);
        /*************************** Starting Page Scene *****************************/

        FlowPane buttonsPane = new FlowPane(playButton , creditsButton , exitButton);
        buttonsPane.setAlignment(Pos.BOTTOM_LEFT);
        Pane homeBackgroundPane = new Pane(startView);
        StackPane startPane = new StackPane(homeBackgroundPane , buttonsPane);
        Scene startScene = new Scene(startPane, 1080, 650);

        /************************ Gaming Page Background ***************************/
        Image gameImage = new Image(new FileInputStream("src/main/resources/Start/Background.jpg"));
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
        Scene gamingScene = new Scene(gamePane, 1080, 650);

        /************************ Game Over Page Background ***************************/
        Image gameOverImage = new Image(new FileInputStream("src/main/resources/Start/GameOver.jpg"));
        ImageView gameOverView = new ImageView(gameOverImage);
        gameOverView.setFitWidth(1150);
        gameOverView.setFitHeight(680);
        /*************************** Game over Scene *****************************/

        HBox tryPane = new HBox(20 , homeButton ,tryAgainButton );
        tryPane.setAlignment(Pos.BOTTOM_LEFT);
        StackPane gameOverPane =new StackPane(gameOverView , tryPane);
        Scene gameOverScene = new Scene(gameOverPane, 1080, 650);

        /*********************** Buttons creation and adjusting ***********************/
        /*********************************** Play Button ******************************/

        Image playBtnImage =new Image(new FileInputStream("src/main/resources/Start/play.png") );
        ImageView playBtnView = new ImageView(playBtnImage);
        playBtnView.setFitHeight(90);      playBtnView.setFitWidth(150);

        playButton.setGraphic(playBtnView);//added image to button
        playButton.setBackground(Background.EMPTY); //made button hidden to just view image

        /*********************************** Credits Button *********************************/

        Image creditsBtnImage =new Image(new FileInputStream("src/main/resources/Start/Credits.png") );
        ImageView creditsBtnView = new ImageView(creditsBtnImage);
        creditsBtnView.setFitHeight(100);   creditsBtnView.setFitWidth(150);

        creditsButton.setGraphic(creditsBtnView);//added image to button
        creditsButton.setBackground(Background.EMPTY); //made button hidden to just view image
        /********************************* Exit Button *********************************/

        Image exitBtnImage =new Image(new FileInputStream("src/main/resources/Start/Exit.png") );
        ImageView exitBtnView = new ImageView(exitBtnImage);
        exitBtnView.setFitHeight(90);   exitBtnView.setFitWidth(150);

        exitButton.setGraphic(exitBtnView);//added image to button
        exitButton.setBackground(Background.EMPTY); //made button hidden to just view image

        /********************************* Try Again Button *********************************/

        Image tryAgainBtnImage =new Image(new FileInputStream("src/main/resources/Start/TryAgain.png") );
        ImageView tryAgainBtnView = new ImageView(tryAgainBtnImage);
        tryAgainBtnView.setFitHeight(70);   tryAgainBtnView.setFitWidth(125);

        tryAgainButton.setGraphic(tryAgainBtnView);//added image to button
        tryAgainButton.setBackground(Background.EMPTY); //made button hidden to just view image

        /********************************* Home Button *********************************/

        Image homeBtnImage =new Image(new FileInputStream("src/main/resources/HomeBtn.png") );
        ImageView homeBtnView = new ImageView(homeBtnImage);
        homeBtnView.setFitHeight(70);   homeBtnView.setFitWidth(125);

        homeButton.setGraphic(homeBtnView);//added image to button
        homeButton.setBackground(Background.EMPTY); //made button hidden to just view image

        /********************************* Back Button *********************************/

        Image backBtnImage =new Image(new FileInputStream("src/main/resources/Back.png") );
        ImageView backBtnView = new ImageView(backBtnImage);
        backBtnView.setFitHeight(50);   backBtnView.setFitWidth(125);

        backButton.setGraphic(backBtnView);//added image to button
        backButton.setBackground(Background.EMPTY); //made button hidden to just view image

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

        /********************************* Sounds ->> currently it is the exit btn *********************************/
        //FileInputStream soundStream = new FileInputStream("Sounds/ElevatorMusic.mp3");
        //AudioClip soundClip = new AudioClip(getClass().getResource("Sounds/ElevatorMusic.mp3").toExternalForm());

      //  Media media = new Media(new File("ElevatorMusic.wav").toURI().toString());

 /******************************** Buttons Action *********************************/

        /************************* play button action *******************************/

        playButton.setOnMousePressed( e -> {
            /***********  make like animation when clicked or released ***********/
            playBtnView.setFitHeight(80);
            playBtnView.setFitWidth(120);
        });


        playButton.setOnMouseReleased( btnEvent -> {
                playBtnView.setFitHeight(90);
                playBtnView.setFitWidth(150);
                /******************* Go to Game Playing area  **********************/
                startTimerMode(stage,gamingScene,gameFruitPane);

        });

        /************************* credits button action *******************************/

        creditsButton.setOnMousePressed( e -> {
            /***********  make like animation when clicked or released ***********/
            creditsBtnView.setFitHeight(80);
            creditsBtnView.setFitWidth(120);
        });
        creditsButton.setOnMouseReleased( e -> {
            creditsBtnView.setFitHeight(100);
            creditsBtnView.setFitWidth(150);
        /******************* Go to Gaming scene ->> supposed to view credits  **********************/

        });

        /************************* exit button action *******************************/

        exitButton.setOnMousePressed( e -> {
            /***********  make like animation when clicked or released ***********/
            exitBtnView.setFitHeight(80);
            exitBtnView.setFitWidth(120);
        });
        exitButton.setOnMouseReleased( e -> {
            exitBtnView.setFitHeight(90);
            exitBtnView.setFitWidth(150);
            /******************* close application  **********************/
            stage.close();
        });

        /************************* try again button action *******************************/

        tryAgainButton.setOnMousePressed( e -> {
            /***********  make like animation when clicked or released ***********/
            tryAgainBtnView.setFitHeight(60);
            tryAgainBtnView.setFitWidth(115);
        });
        tryAgainButton.setOnMouseReleased( e -> {
            tryAgainBtnView.setFitHeight(70);
            tryAgainBtnView.setFitWidth(125);
            /******************* Go to Gaming scene  **********************/

            startTimerMode(stage,gamingScene,gameFruitPane);
            /*
            stage.setScene(gamingScene);
            endRandThrow(gameFruitPane);
            startRandThrow();
            timer.play();
            */
        });

        /************************* home button action *******************************/

        homeButton.setOnMousePressed( e -> {
            /***********  make like animation when clicked or released ***********/
            homeBtnView.setFitHeight(60);
            homeBtnView.setFitWidth(110);
        });
        homeButton.setOnMouseReleased( e -> {
            homeBtnView.setFitHeight(70);
            homeBtnView.setFitWidth(125);
            /******************* Go to Game Start scene  **********************/
            stage.setScene(startScene);
            endRandThrow(gameFruitPane);
        });

        /************************* back button action *******************************/

        backButton.setOnMousePressed( e -> {
            /***********  make like animation when clicked or released ***********/
            backBtnView.setFitHeight(55);
            backBtnView.setFitWidth(110);
        });
        backButton.setOnMouseReleased( e -> {
            backBtnView.setFitHeight(65);
            backBtnView.setFitWidth(120);
            /******************* Go to Game Over scene  **********************/
            stage.setScene(startScene);
            endRandThrow(gameFruitPane);
        });

        /********************   TimeLines   *********************************/

        timer = new Timeline(new KeyFrame(Duration.millis(1000),e->{
            gameTimer--;
            if(gameTimer == 0){ //if time of current game ended then reset game parameters
                timer.stop(); //stop it so it don't run in background
                gameTimer = 30; //reset timing value
                playerScore = 0; //reset player score
                timerValue.setText(gameTimer+""); //update lables
                scoreValue.setText(playerScore+""); //update lables

                stage.setScene(gameOverScene); //show game over scene
            }
            else
                timerValue.setText(gameTimer+""); //if time didn't end yet then update timer lable with current remaining time
        }));
        timer.setCycleCount(Timeline.INDEFINITE);

        /************************ Stage default scene show ***************************/
        stage.setTitle("Fruit Ninja");

        stage.setResizable(false);
        stage.setScene(startScene);
        stage.show();
    }
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
    void exitTimerMode(Stage stage ,Scene returnScene , StackPane fruitPane){
        stage.setScene(returnScene);
        timer.pause();
        endRandThrow(fruitPane);
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