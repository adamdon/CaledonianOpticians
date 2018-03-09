package caledonianopticians;

import javafx.application.Application;
import javafx.stage.Stage;
/**
 * @author adamdon <adamdon89@gmail.com>
 */
public class CaledonianOpticians extends Application
{
    Controller controller;
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        controller = new Controller();
        
        primaryStage.setScene(controller.getSceneFromView());
        primaryStage.setTitle("Caledonian Opticians - Appointments System");
        primaryStage.show();
    }   
}
