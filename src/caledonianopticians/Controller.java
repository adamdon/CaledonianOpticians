package caledonianopticians;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller
{
    View view;
    ObservableList<User> Users;
    ObservableList<Appointment> Appointments;

    public Controller() 
    {
       view = new View(getUser(), getAppointment());
       
       view.btnSearch.setOnAction(e -> handleBtnSearch());
       view.txtSearchTextField.setOnKeyPressed((event) -> { if(event.getCode() == KeyCode.ENTER) { handleBtnSearch(); } });
       //view.tabUserTable.setOnMouseClicked(e -> searchAppointments());
       view.tabUserTable.getSelectionModel().selectedIndexProperty().addListener((num) -> searchAppointments());
    }
    
    public void handleBtnSearch()
    {
        updateStatusBar(view.txtSearchTextField.getText());
        searchUsers(view.txtSearchTextField.getText());
    }
    
    public void  updateStatusBar(String srtPassedText)
    {
        view.lblStatusBarText.setText("Test: " + srtPassedText);
        System.out.println("Status bar updated with " + srtPassedText);
    }
    
    public void searchAppointments()
    {
        int intUserindex = view.tabUserTable.getSelectionModel().selectedIndexProperty().get();
        Integer intUserRefSelected = Users.get(intUserindex).getIntReference();
        
        ObservableList<Appointment> SearchedAppointments = FXCollections.observableArrayList();
        
        for(Appointment identifier: Appointments)
        {
            Integer ingAppointmentPatient = identifier.getIntAttendingPatient();
            
            if(ingAppointmentPatient.equals(intUserRefSelected))
            {
                SearchedAppointments.add(identifier);
            }
        }
        view.tabAppointmentTable.setItems(SearchedAppointments);       
        
    }
    
    public void searchUsers(String srtPassedText)
    {
        ObservableList<User> SearchedUsers = FXCollections.observableArrayList();
        
        if(srtPassedText.equals("") || srtPassedText.equals(" "))
        {
            SearchedUsers = Users;
        }
        else
        {
            updateStatusBar("Searching for " + srtPassedText);
            for(User identifier: Users)
            {
                String strFullName = identifier.getSrtFirstName() + " " + identifier.getSrtLastName();
                Integer ingIntergerRef = identifier.getIntReference(); // changing primitive data type int to Interger

                if(identifier.getSrtFirstName().equalsIgnoreCase(srtPassedText))
                {
                    SearchedUsers.add(identifier);
                }
                else if(identifier.getSrtLastName().equalsIgnoreCase(srtPassedText))
                {
                    SearchedUsers.add(identifier);
                }
                else if (strFullName.equalsIgnoreCase(srtPassedText))
                {
                    SearchedUsers.add(identifier);
                }
                else if (ingIntergerRef.toString().equals(srtPassedText))
                {
                    SearchedUsers.add(identifier);
                }
            } // end of for loop

        } //end of else 
        view.tabUserTable.setItems(SearchedUsers);
        view.txtSearchTextField.selectAll();
    }
    
    public ObservableList<User> getUser()
    {
        Users = FXCollections.observableArrayList();
        Users.add(new User("Homer", "Simpson", 100001, "24, new Road"));
        Users.add(new User("Ned", "Flanders", 100002, "25, new Road"));
        Users.add(new User("Troy", "McClure", 100003, "26, new Road"));
        Users.add(new User("Ralph", "Wiggum", 100004, "27, new Road"));
        Users.add(new User("Kent", "Brockman", 100005, "28, new Road"));
        Users.add(new User("Fat", "Tony", 100006, "29, new Road"));
        Users.add(new User("Fat", "Tony", 100007, "29, new Road"));
        Users.add(new User("Fat", "Tony", 100008, "29, new Road"));
        Users.add(new User("Fat", "Tony", 100009, "29, new Road"));
        Users.add(new User("Fat", "Tony", 100010, "29, new Road"));
        Users.add(new User("Fat", "Tony", 100011, "29, new Road"));
        Users.add(new User("Fat", "Tony", 100012, "29, new Road"));
        Users.add(new User("Fat", "Tony", 100013, "29, new Road"));
        Users.add(new User("Fat", "Tony", 100014, "29, new Road"));
        return Users;
    }
    
    public ObservableList<Appointment> getAppointment()
    {
        Appointments = FXCollections.observableArrayList();
        Appointments.add(new Appointment(200001, 100002, "Dr Leonard McCoy", "30.04.18 - 1430", "sore eyes"));
        Appointments.add(new Appointment(200002, 100002, "Dr Julian Bashir", "25.04.18 - 0930", "squinty eyes"));
        Appointments.add(new Appointment(200003, 100002, "Dr Leonard McCoy", "30.04.18 - 1430", "eyes too small"));
        Appointments.add(new Appointment(200004, 100004, "Dr Julian Bashir", "30.04.18 - 1430", "broken left eye"));
        Appointments.add(new Appointment(200005, 100005, "Dr Beverly Crusher", "30.04.18 - 1430", "pink eye"));
        Appointments.add(new Appointment(200006, 100005, "Dr Doctor Phlox", "30.04.18 - 1430", "dead eye"));
        Appointments.add(new Appointment(200007, 100005, "Dr The Doctor", "30.04.18 - 1430", "gone blind"));
        Appointments.add(new Appointment(200008, 100004, "Dr The Doctor", "30.04.18 - 1430", "gone blind"));
        Appointments.add(new Appointment(200009, 100001, "Dr The Doctor", "30.04.18 - 1430", "gone blind"));
        Appointments.add(new Appointment(200010, 100001, "Dr The Doctor", "30.04.18 - 1430", "gone blind"));
        Appointments.add(new Appointment(200011, 100004, "Dr The Doctor", "30.04.18 - 1430", "gone blind"));
        Appointments.add(new Appointment(200012, 100001, "Dr The Doctor", "30.04.18 - 1430", "gone blind"));
        Appointments.add(new Appointment(200013, 100009, "Dr The Doctor", "30.04.18 - 1430", "gone blind"));
        Appointments.add(new Appointment(200014, 100004, "Dr The Doctor", "30.04.18 - 1430", "gone blind"));
        return Appointments;
    }
    
    public Scene getSceneFromView()
    {       
        return view.getScene();
    }
    
}
