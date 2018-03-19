package caledonianopticians;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
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
 * @author admin
 */
public class View
{
    Scene scene;
    
    GridPane gridRoot;
    GridPane gridTop;
    GridPane gridLeft;
    GridPane gridRight;
                   
    Button btnUserNew;
    Button btnUserModify;
    Button btnAppointmentNew;
    Button btnAppointmentModify;
    Button btnSearch;
    Button btnMakeAp;
    TextField txtSearchTextField;
    TextField txtUserFirstName;
    TextField txtUserLastName;
    TextField txtUserRefNumber;
    TextField txtUserAddress;
    TextField txtAppointmentRef;
    TextField txtApointmentUserRef;
    TextField txtAppointmentOptician;
    TextField txtAppointmentTime;
    TextField txtAppointmentType;
    Label lblGcuLogo;
    Label lblUserTitle;
    Label lblUserFirstName;
    Label lblUserLastName;
    Label lblUserRefNumber;
    Label lblUserAddress;
    Label lblAppointmentTitle;
    Label lblAppointmentRef;
    Label lblAppointmentUserRef;
    Label lblAppointmentOptician;
    Label lblAppointmentTime;
    Label lblAppointmentType;
    Label lblSpacerBlankH;
    Label lblSpacerBlankW;
    Label lblStatusBarLabel;
    Label lblStatusBarText;

    FileInputStream fisImageStream;    
    Image imgGcuLogo;
    ImageView imvGcuLogo;
    
    TableView<User> tabUserTable;
    TableView<Appointment> tabAppointmentTable;
    
    ObservableList<User> Users;
    ObservableList<Appointment> Appointments;

    TableColumn<User, String> colTableFirstName;
    TableColumn<User, String> colTableLastName;
    TableColumn<User, Integer> colTableReference;
    TableColumn<User, String> colTableAddress;
    TableColumn<Appointment, Integer> colTableAppointmentRef;
    TableColumn<Appointment, Integer> colTableAttendingPatient;
    TableColumn<Appointment, String> colTableAttendingOptician;
    TableColumn<Appointment, String> colTableAppointmentTime;
    TableColumn<Appointment, String> colTableAppointmentType;
    
    public View()
    {       
        setupNodes();
        setupLayout();   
        scene = new Scene(gridRoot, 1280, 650);
    }

    public Scene getScene()
    {       
        return scene;
    }
      
    public void setupNodes()
    {
        btnUserNew = new Button();
        btnUserNew.setText("Register User");
 
        btnUserModify = new Button();
        btnUserModify.setText("Modify user");
        
        btnAppointmentNew  = new Button();
        btnAppointmentNew.setText("Register Appointment");
       
        btnAppointmentModify = new Button();
        btnAppointmentModify.setText("Modify Appointment");
        
        btnSearch = new Button();
        btnSearch.setText("Search");
                
        btnMakeAp = new Button();
        btnMakeAp.setText("Make Appointment");
        
        txtSearchTextField = new TextField();
        txtSearchTextField.setText("P124564");
        
        txtUserFirstName = new TextField("Homer ");
        txtUserLastName = new TextField("Simpson ");
        txtUserRefNumber = new TextField("10001 ");
        txtUserAddress = new TextField("24 New Road ");
        
        txtAppointmentRef = new TextField("200001 ");
        txtApointmentUserRef = new TextField("100004 ");
        txtAppointmentOptician = new TextField("Dr Leonard McCoy ");
        txtAppointmentTime = new TextField("30.04.18 - 1430 ");
        txtAppointmentType = new TextField("sore eyes ");
        
        lblGcuLogo = new Label();
        lblGcuLogo.setText("Caledonian Opticians");
        lblGcuLogo.setFont(new Font("Arial", 30));
        
        lblUserTitle = new Label ("User Details");
        lblUserTitle.setStyle("-fx-font-weight: bold");  
        
        lblUserFirstName = new Label("First Name:");
        lblUserLastName = new Label("Last Name:");
        lblUserRefNumber = new Label("Ref Number:");
        lblUserAddress = new Label("Address:") ;   
        
        lblAppointmentTitle = new Label ("Appointment Details");
        lblAppointmentTitle.setStyle("-fx-font-weight: bold");
        
        lblAppointmentRef = new Label("Appointment Ref:");
        lblAppointmentUserRef = new Label("Attending Patient:");
        lblAppointmentOptician = new Label("Attending Optician:");
        lblAppointmentTime = new Label("Appointment Time:");
        lblAppointmentType = new Label("Appointment Type:");
        
        lblSpacerBlankH = new Label(" ");
        lblSpacerBlankH.setMinHeight(100);
        lblSpacerBlankW = new Label(" ");
        lblSpacerBlankW.setMinWidth(520);
        
        lblStatusBarLabel = new Label("Status Bar: ");
        lblStatusBarText = new Label("Searching for Users...");
        
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
        tabUserTable.setItems(Users);
        tabUserTable.getColumns().addAll(colTableFirstName, colTableLastName, colTableReference, colTableAddress);
        tabUserTable.setMaxSize(850, 275);
        tabUserTable.setMinSize(850, 275);
        
        
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
        tabAppointmentTable.setItems(Appointments);
        tabAppointmentTable.getColumns().addAll(colTableAppointmentRef, colTableAttendingPatient, colTableAttendingOptician, colTableAppointmentTime, colTableAppointmentType);
        tabAppointmentTable.setMaxSize(850, 275);
        tabAppointmentTable.setMinSize(850, 275);
    }
        
    public void setupLayout()
    {
        gridTop = new GridPane();
        gridTop.setAlignment(Pos.TOP_LEFT);
        gridTop.setHgap(10);
        gridTop.setVgap(5);
        gridTop.setPadding(new Insets(1, 10, 0, 10)); //bottom padding set to 0 
                
        gridTop.add(lblGcuLogo, 0, 0);   
        gridTop.add(txtSearchTextField, 1, 0);    
        gridTop.add(btnSearch, 2, 0);
        gridTop.add(lblSpacerBlankW, 3, 0);
        gridTop.add(imvGcuLogo, 4, 0);
        
        
        
        gridLeft = new GridPane();
        gridLeft.setAlignment(Pos.TOP_LEFT);
        gridLeft.setHgap(5);
        gridLeft.setVgap(5);
        gridLeft.setPadding(new Insets(0, 0, 10, 10)); //top and right padding set to 0 
        
        gridLeft.add(tabUserTable, 0, 0, 2, 1); //can add 2 paramiters for row/column span
        gridLeft.add(tabAppointmentTable, 0, 1, 2, 1); //can 2 paramiters for row/column span
        gridLeft.add(lblStatusBarLabel, 0, 2);
        gridLeft.add(lblStatusBarText, 1, 2);
        
        
        
        gridRight = new GridPane();
        //gridRight.setAlignment(Pos.TOP_LEFT);
        gridRight.setAlignment(Pos.TOP_CENTER);
        gridRight.setHgap(5);
        gridRight.setVgap(5);
        gridRight.setPadding(new Insets(0, 10, 10, 0)); //top and left padding set to 0 
        gridRight.setHalignment(lblUserTitle, HPos.CENTER);
        gridRight.setHalignment(lblAppointmentTitle, HPos.CENTER);
        
        gridRight.add(lblUserTitle, 0, 0, 2, 1);
        gridRight.add(lblUserFirstName, 0, 1);
        gridRight.add(lblUserLastName, 0, 2);
        gridRight.add(lblUserRefNumber, 0, 3);
        gridRight.add(lblUserAddress, 0, 4);
        gridRight.add(txtUserFirstName, 1, 1);
        gridRight.add(txtUserLastName, 1, 2);
        gridRight.add(txtUserRefNumber, 1, 3);
        gridRight.add(txtUserAddress, 1, 4);
        gridRight.add(btnUserNew, 0, 5);
        gridRight.add(btnUserModify, 1, 5);
        
        gridRight.add(lblSpacerBlankH, 0, 6);
        gridRight.add(lblAppointmentTitle, 0, 7, 2, 1);
        gridRight.add(lblAppointmentRef, 0, 8);
        gridRight.add(lblAppointmentUserRef, 0, 9);
        gridRight.add(lblAppointmentOptician, 0, 10);
        gridRight.add(lblAppointmentTime, 0, 11);
        gridRight.add(lblAppointmentType, 0, 12);
        gridRight.add(txtAppointmentRef, 1, 8);
        gridRight.add(txtApointmentUserRef, 1, 9);
        gridRight.add(txtAppointmentOptician, 1, 10);       
        gridRight.add(txtAppointmentTime, 1, 11);
        gridRight.add(txtAppointmentType, 1, 12);    
        gridRight.add(btnAppointmentNew, 0, 13); 
        gridRight.add(btnAppointmentModify, 1, 13); 
        
        gridRoot = new GridPane();
        gridRoot.setAlignment(Pos.TOP_LEFT);
        gridRoot.setHgap(5);
        gridRoot.setVgap(5);
        gridRoot.setPadding(new Insets(1, 10, 10, 10));
        
        gridRoot.setConstraints(gridTop, 0, 0, 2, 1);
        gridRoot.setConstraints(gridLeft, 0, 1);
        gridRoot.setConstraints(gridRight, 1, 1);
        gridRoot.getChildren().add(gridTop);
        gridRoot.getChildren().add(gridLeft);
        gridRoot.getChildren().add(gridRight);
    } 
    
}
