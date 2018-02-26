package caledonianopticians;

//import java.awt.Insets;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.text.Font;


/**
 *
 * @author adamdon <adamdon89@gmail.com>
 */
public class CaledonianOpticians extends Application implements EventHandler<ActionEvent>
{
    Button btnReg;
    Button btnSeach;
    Button btnMakeAp;
    TextField txtUserTextField;
    Label lblGcuLogo;


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
        
        btnMakeAp = new Button();
        btnMakeAp.setText("Make Appointment");
        
        txtUserTextField = new TextField();
        txtUserTextField.setText("P124564");
        
        lblGcuLogo = new Label();
        lblGcuLogo.setText("Caledonian Opticians");
        lblGcuLogo.setFont(new Font("Arial", 30));
        
        
        GridPane grid = new GridPane();
        grid.add(lblGcuLogo, 0, 0);   
        grid.add(txtUserTextField, 1, 0);    
        grid.add(btnSeach, 2, 0);
        grid.add(btnReg, 3, 0);
        grid.add(btnMakeAp, 4, 0);
        
       
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        Insets inset = new Insets(15, 15, 15, 15);
        grid.setPadding(inset);
        
        Scene scene = new Scene(grid, 1280, 650);
        primaryStage.setScene(scene);
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
