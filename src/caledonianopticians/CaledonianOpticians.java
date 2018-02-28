package caledonianopticians;

//import java.awt.Insets;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

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
    TableView<Appointment> tabAppointmentTable;

    FileInputStream fisImageStream;    
    Image imgGcuLogo;
    ImageView imvGcuLogo;

    TableColumn<User, String> colTableFirstName;
    TableColumn<User, String> colTableLastName;
    TableColumn<User, Integer> colTableReference;
    TableColumn<User, String> colTableAddress;
    TableColumn<Appointment, Integer> colTableAppointmentRef;
    TableColumn<Appointment, Integer> colTableAttendingPatient;
    TableColumn<Appointment, String> colTableAttendingOptician;
    TableColumn<Appointment, String> colTableAppointmentTime;
    TableColumn<Appointment, String> colTableAppointmentType;

    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        setupNodes();

        GridPane grid = new GridPane();
        grid.add(imvGcuLogo, 0, 0);
        grid.add(lblGcuLogo, 1, 0);   
        grid.add(txtUserTextField, 2, 0);    
        grid.add(btnSeach, 3, 0);
        grid.add(btnReg, 4, 0);
        grid.add(btnMakeAp, 5, 0);
        grid.add(tabUserTable, 0, 2, 5, 1); //last 2 paramiters for row/column span
        grid.add(tabAppointmentTable, 0, 3, 5, 1); //last 2 paramiters for row/column span
        
       
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
    
    public void setupNodes()
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
        
        //imageView code for GCU logo
        try {fisImageStream = new FileInputStream("img/gculogo.png");}
        catch (FileNotFoundException ex) {Logger.getLogger(CaledonianOpticians.class.getName()).log(Level.SEVERE, null, ex);}
        imgGcuLogo = new Image(fisImageStream);
        imvGcuLogo = new ImageView();
        imvGcuLogo.setImage(imgGcuLogo);
        imvGcuLogo.setFitHeight(56);
        imvGcuLogo.setFitWidth(100);
        
        
        //TableView for users added with 4 columns
        colTableFirstName = new TableColumn<>("1st Name");
        colTableFirstName.setMinWidth(50);
        colTableFirstName.setCellValueFactory(new PropertyValueFactory<>("srtFirstName"));
        
        colTableLastName = new TableColumn<>("2nd Name");
        colTableLastName.setMinWidth(50);
        colTableLastName.setCellValueFactory(new PropertyValueFactory<>("srtLastName"));
        
        colTableReference = new TableColumn<>("Reference Number");
        colTableReference.setMinWidth(50);
        colTableReference.setCellValueFactory(new PropertyValueFactory<>("intReference"));
        
        colTableAddress = new TableColumn<>("Address");
        colTableAddress.setMinWidth(200);
        colTableAddress.setCellValueFactory(new PropertyValueFactory<>("srtAddress"));
       
        tabUserTable = new TableView<>();
        tabUserTable.setItems(getUser());
        tabUserTable.getColumns().addAll(colTableFirstName, colTableLastName, colTableReference, colTableAddress);
        tabUserTable.setMaxHeight(150);
        
        
        //TableView for appointments added with 5 columns
        colTableAppointmentRef = new TableColumn<>("Appointment Ref");
        colTableAppointmentRef.setMinWidth(50);
        colTableAppointmentRef.setCellValueFactory(new PropertyValueFactory<>("intAppointmentRef"));
        
        colTableAttendingPatient = new TableColumn<>("Attending Patient");
        colTableAttendingPatient.setMinWidth(50);
        colTableAttendingPatient.setCellValueFactory(new PropertyValueFactory<>("intAttendingPatient"));

        colTableAttendingOptician = new TableColumn<>("Attending Optician");
        colTableAttendingOptician.setMinWidth(50);
        colTableAttendingOptician.setCellValueFactory(new PropertyValueFactory<>("strAttendingOptician"));
        
        colTableAppointmentTime = new TableColumn<>("Appointment Time");
        colTableAppointmentTime.setMinWidth(50);
        colTableAppointmentTime.setCellValueFactory(new PropertyValueFactory<>("strAppointmentTime"));        
        
        colTableAppointmentType = new TableColumn<>("Appointment Type");
        colTableAppointmentType.setMinWidth(50);
        colTableAppointmentType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));        
        
        tabAppointmentTable = new TableView<>();
        tabAppointmentTable.setItems(getAppointment());
        tabAppointmentTable.getColumns().addAll(colTableAppointmentRef, colTableAttendingPatient, colTableAttendingOptician, colTableAppointmentTime, colTableAppointmentType);
        tabAppointmentTable.setMaxHeight(150);
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
    
    public ObservableList<Appointment> getAppointment()
    {
        ObservableList<Appointment> Appointments = FXCollections.observableArrayList();
        Appointments.add(new Appointment(200001, 100004, "Dr Leonard McCoy", "30.04.18 - 1430", "sore eyes"));
        Appointments.add(new Appointment(200002, 100004, "Dr Julian Bashir", "25.04.18 - 0930", "squinty eyes"));
        Appointments.add(new Appointment(200003, 100004, "Dr Leonard McCoy", "30.04.18 - 1430", "eyes too small"));
        Appointments.add(new Appointment(200004, 100004, "Dr Julian Bashir", "30.04.18 - 1430", "broken left eye"));
        Appointments.add(new Appointment(200005, 100004, "Dr Beverly Crusher", "30.04.18 - 1430", "pink eye"));
        Appointments.add(new Appointment(200006, 100004, "Dr Doctor Phlox", "30.04.18 - 1430", "dead eye"));
        Appointments.add(new Appointment(200007, 100004, "Dr The Doctor", "30.04.18 - 1430", "gone blind"));
        return Appointments;
    }
    
    
}
