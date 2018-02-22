package caledonianopticians;

//import java.awt.Insets;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;


/**
 *
 * @author adamdon <adamdon89@gmail.com>
 */
public class CaledonianOpticians extends Application implements EventHandler<ActionEvent>
{
    Button btnReg;
    Button btnSeach;
    TextField userTextField;

    
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        btnReg = new Button();
        btnReg.setText("Register user");
        btnReg.setOnAction(this);
        
        btnSeach = new Button();
        btnSeach.setText("Search Appointments");
        btnSeach.setOnAction(this);
        
        userTextField = new TextField();
        

        
        
        GridPane grid = new GridPane();
        grid.add(btnReg, 0, 2);
        grid.add(btnSeach, 1, 1);
        grid.add(userTextField, 0, 1);
       
        
        
        

        
        Scene scene = new Scene(grid, 1280, 650);
        primaryStage.setScene(scene);
        
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        Insets inset = new Insets(25, 25, 25, 25);
        grid.setPadding(inset);
        
        
        primaryStage.setTitle("Caledonian Opticians - Appointments System");
        primaryStage.show();
    }
    
    public void handle(ActionEvent event)
    {
        if (event.getSource() == btnReg)
        {
            System.out.println("Test Print - Register user");
        }
        
        if (event.getSource() == btnSeach)
        {
            System.out.println("Test Print - Search");
        }
    }
    
}
