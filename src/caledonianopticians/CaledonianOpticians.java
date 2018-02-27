package caledonianopticians;

//import java.awt.Insets;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    TableView<User> tabUserTable;


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
        
        
        
        //TableView added with 4 columns
        TableColumn<User, String> colTableFirstName = new TableColumn<>("1st Name");
        colTableFirstName.setMinWidth(50);
        colTableFirstName.setCellValueFactory(new PropertyValueFactory<>("srtFirstName"));
        
        TableColumn<User, String> colTableLastName = new TableColumn<>("2nd Name");
        colTableLastName.setMinWidth(50);
        colTableLastName.setCellValueFactory(new PropertyValueFactory<>("srtLastName"));
        
        TableColumn<User, Integer> colTableReference = new TableColumn<>("Reference Number");
        colTableReference.setMinWidth(50);
        colTableReference.setCellValueFactory(new PropertyValueFactory<>("intReference"));
        
        TableColumn<User, String> colTableAddress = new TableColumn<>("Address");
        colTableAddress.setMinWidth(200);
        colTableAddress.setCellValueFactory(new PropertyValueFactory<>("srtAddress"));
       
        tabUserTable = new TableView<>();
        tabUserTable.setItems(getUser());
        tabUserTable.getColumns().addAll(colTableFirstName, colTableLastName, colTableReference, colTableAddress);
        tabUserTable.setMaxHeight(150);
        
        
        
        GridPane grid = new GridPane();
        grid.add(lblGcuLogo, 0, 0);   
        grid.add(txtUserTextField, 1, 0);    
        grid.add(btnSeach, 2, 0);
        grid.add(btnReg, 3, 0);
        grid.add(btnMakeAp, 4, 0);
        grid.add(tabUserTable, 0, 2, 5, 1); //last 2 paramiters for row/column span
        
       
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
    
    public ObservableList<User> getUser()
    {
        ObservableList<User> Users = FXCollections.observableArrayList();
        Users.add(new User("Homer", "Simpson", 100001, "24, new Road"));
        Users.add(new User("Ned", "Flanders", 100002, "25, new Road"));
        Users.add(new User("Troy", "McClure", 100003, "26, new Road"));
        Users.add(new User("Ralph", "Wiggum", 100004, "27, new Road"));
        Users.add(new User("Kent", "Brockman", 100005, "28, new Road"));
        Users.add(new User("Fat", "Tony", 100006, "29, new Road"));
        return Users;
    }
    
}
