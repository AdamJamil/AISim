package hja;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application implements Constants
{
    private ViewPortLoader viewPortLoader;
    private InputHandler inputHandler = new InputHandler();
    private Data data = new Data();
    private int turn = 0;
    private boolean gameOver = false;

    @Override
    public void start(Stage primaryStage)
    {
        //loads javafx setup
        //this code is mostly irrelevant and only implements the
        //javafx canvas, root and scene.
        //the screen size and listeners are also added in here
        primaryStage.setTitle("Ayy Tic Tac Toe");
        Canvas canvas = new Canvas(pixelWidth, pixelHeight);
        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, pixelWidth, pixelHeight);
        scene.setOnKeyPressed(this::onKeyPressed);
        scene.setOnKeyReleased(this::onKeyReleased);
        scene.setOnMouseClicked(this::onMouseClicked);
        primaryStage.setScene(scene);
        primaryStage.show();

        //gives the ViewPortLoader the relevant information with which to draw
        viewPortLoader = new ViewPortLoader(canvas.getGraphicsContext2D(), data);

        //sets loop to 4ms delay, and calls the art loader
        KeyFrame frame = new KeyFrame(Duration.millis(4f), (event) ->
        {
            if (!gameOver)
            {
                viewPortLoader.loadViewPort();
                Position click = inputHandler.getLastClicked();
                int x = click.x;
                int y = click.y;
                if (x != -1 && y != -1)
                    if (data.move((turn % 2 == 0) ? 1 : -1, x, y))
                        turn++;
                if ((new Checker()).checkWin((turn % 2 == 0) ? -1 : 1, data))
                {
                    gameOver = true;
                    System.out.println(((turn % 2 == 0) ? "O" : "X") + " wins");
                    viewPortLoader.loadViewPort();
                }
            }
        });

        //this just puts the timeline (or main game logic) in motion
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(frame);
        timeline.play();
    }

    private void onKeyPressed(KeyEvent e)
    {
        inputHandler.keyPressed(e);
    }

    private void onKeyReleased(KeyEvent e)
    {
        inputHandler.keyReleased(e);
    }

    private void onMouseClicked(MouseEvent e)
    {
        inputHandler.mousePressed((int)e.getX(), (int)e.getY());
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}