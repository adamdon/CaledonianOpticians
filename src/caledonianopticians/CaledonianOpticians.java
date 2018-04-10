/**
 * @author adamdon <adamdon89@gmail.com>
 */

package caledonianopticians;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CaledonianOpticians extends Application
{
    Controller controller;
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException
    {
        controller = new Controller();
        
        primaryStage.setScene(controller.getSceneFromView());
        primaryStage.setTitle("Caledonian Opticians - Appointments System");
        
        FileInputStream fisIconStream = new FileInputStream("img/gcuicon.png");
        Image imgGcuIcon = new Image(fisIconStream);
        primaryStage.getIcons().add(imgGcuIcon); //sets stage icon for OS
        
        primaryStage.show();
    }   
}
