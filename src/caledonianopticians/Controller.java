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
    ObservableList<User> allUsers;
    ObservableList<User> searchedUsers;
    ObservableList<User> displayedUsers;
    ObservableList<Appointment> allAppointments;
    ObservableList<Appointment> SearchedAppointments;
    ObservableList<Appointment> displayedAppointments;

    public Controller() 
    {
       view = new View();
       
       allUsers = getUser();
       allAppointments = getAppointment();
       updateUsersTable(allUsers);
       updateAppointmentsTable(allAppointments);
       
       view.btnSearch.setOnAction(e -> handleBtnSearch());
       view.txtSearchTextField.setOnKeyPressed((event) -> { if(event.getCode() == KeyCode.ENTER) { handleBtnSearch(); } });
       view.tabUserTable.getSelectionModel().selectedIndexProperty().addListener((num) -> handleListenerSelectionUserTable());
    }
    
    public void handleBtnSearch()
    {
        updateStatusBar(view.txtSearchTextField.getText());
        searchUsers(view.txtSearchTextField.getText());
    }
    
    public void handleListenerSelectionUserTable()
    {    
        searchAppointments(getUserTableSelection());
    }

    public void searchAppointments(int intUserTableindex)
    {
        SearchedAppointments = FXCollections.observableArrayList();
        Integer intUserRefSelected = displayedUsers.get(intUserTableindex).getIntReference();
        
        for(Appointment identifier: allAppointments)
        {
            Integer ingAppointmentPatient = identifier.getIntAttendingPatient();
            
            if(ingAppointmentPatient.equals(intUserRefSelected))
            {
                SearchedAppointments.add(identifier);
            }
        }
        updateAppointmentsTable(SearchedAppointments);       
    }
    
    public void searchUsers(String srtPassedText)
    {
        searchedUsers = FXCollections.observableArrayList();
        
        if(srtPassedText.equals("") || srtPassedText.equals(" "))
        {
            searchedUsers = allUsers;
        }
        else
        {
            updateStatusBar("Searching for " + srtPassedText);
            for(User identifier: allUsers)
            {
                String strFullName = identifier.getSrtFirstName() + " " + identifier.getSrtLastName();
                Integer ingIntergerRef = identifier.getIntReference(); // changing primitive data type int to Interger

                if(identifier.getSrtFirstName().equalsIgnoreCase(srtPassedText))
                {
                    searchedUsers.add(identifier);
                }
                else if(identifier.getSrtLastName().equalsIgnoreCase(srtPassedText))
                {
                    searchedUsers.add(identifier);
                }
                else if (strFullName.equalsIgnoreCase(srtPassedText))
                {
                    searchedUsers.add(identifier);
                }
                else if (ingIntergerRef.toString().equals(srtPassedText))
                {
                    searchedUsers.add(identifier);
                }
            } // end of for loop
            
        } //end of else
        
        if(searchedUsers.isEmpty())
        {
            updateStatusBar("No results found for: " + srtPassedText);
        }
        else
        {
            updateUsersTable(searchedUsers);
            searchAppointments(getUserTableSelection());
        }
        
        view.txtSearchTextField.selectAll();
    }
    
    public void updateUsersTable(ObservableList<User> updatedUsers)
    {
        displayedUsers = updatedUsers;
        view.tabUserTable.setItems(displayedUsers);
    }
    
    public void updateAppointmentsTable(ObservableList<Appointment> updatedAppointments)
    {
        displayedAppointments = updatedAppointments;
        view.tabAppointmentTable.setItems(displayedAppointments);
    }
    
    
    public ObservableList<User> getUser()
    {
        allUsers = FXCollections.observableArrayList();
        allUsers.add(new User("Homer", "Simpson", 100001, "24, new Road"));
        allUsers.add(new User("Ned", "Flanders", 100002, "25, new Road"));
        allUsers.add(new User("Troy", "McClure", 100003, "26, new Road"));
        allUsers.add(new User("Ralph", "Wiggum", 100004, "27, new Road"));
        allUsers.add(new User("Kent", "Brockman", 100005, "28, new Road"));
        allUsers.add(new User("Fat", "Tony", 100006, "29, new Road"));
        allUsers.add(new User("Fat", "Tony", 100007, "29, new Road"));
        allUsers.add(new User("Fat", "Tony", 100008, "29, new Road"));
        allUsers.add(new User("Fat", "Tony", 100009, "29, new Road"));
        allUsers.add(new User("Fat", "Tony", 100010, "29, new Road"));
        allUsers.add(new User("Fat", "Tony", 100011, "29, new Road"));
        allUsers.add(new User("Fat", "Tony", 100012, "29, new Road"));
        allUsers.add(new User("Fat", "Tony", 100013, "29, new Road"));
        allUsers.add(new User("Fat", "Tony", 100014, "29, new Road"));
        return allUsers;
    }
    
    public ObservableList<Appointment> getAppointment()
    {
        allAppointments = FXCollections.observableArrayList();
        allAppointments.add(new Appointment(200001, 100002, "Dr Leonard McCoy", "30.04.18 - 1430", "sore eyes"));
        allAppointments.add(new Appointment(200002, 100002, "Dr Julian Bashir", "25.04.18 - 0930", "squinty eyes"));
        allAppointments.add(new Appointment(200003, 100002, "Dr Leonard McCoy", "30.04.18 - 1430", "eyes too small"));
        allAppointments.add(new Appointment(200004, 100004, "Dr Julian Bashir", "30.04.18 - 1430", "broken left eye"));
        allAppointments.add(new Appointment(200005, 100005, "Dr Beverly Crusher", "30.04.18 - 1430", "pink eye"));
        allAppointments.add(new Appointment(200006, 100005, "Dr Doctor Phlox", "30.04.18 - 1430", "dead eye"));
        allAppointments.add(new Appointment(200007, 100005, "Dr The Doctor", "30.04.18 - 1430", "gone blind"));
        allAppointments.add(new Appointment(200008, 100004, "Dr The Doctor", "30.04.18 - 1430", "gone blind"));
        allAppointments.add(new Appointment(200009, 100001, "Dr The Doctor", "30.04.18 - 1430", "gone blind"));
        allAppointments.add(new Appointment(200010, 100001, "Dr The Doctor", "30.04.18 - 1430", "gone blind"));
        allAppointments.add(new Appointment(200011, 100004, "Dr The Doctor", "30.04.18 - 1430", "gone blind"));
        allAppointments.add(new Appointment(200012, 100001, "Dr The Doctor", "30.04.18 - 1430", "gone blind"));
        allAppointments.add(new Appointment(200013, 100009, "Dr The Doctor", "30.04.18 - 1430", "gone blind"));
        allAppointments.add(new Appointment(200014, 100004, "Dr The Doctor", "30.04.18 - 1430", "gone blind"));
        return allAppointments;
    }
    
    public int getUserTableSelection()
    {
        int intUserTableindex = 0;
        if ((view.tabUserTable.getSelectionModel().selectedIndexProperty().get()) >= 0)
        {
            intUserTableindex = view.tabUserTable.getSelectionModel().selectedIndexProperty().get();
        }
        return  intUserTableindex;       
    }
    
    public void updateStatusBar(String srtPassedText)
    {
        view.lblStatusBarText.setText("Test: " + srtPassedText);
        System.out.println("Status bar updated with " + srtPassedText);
    }
    
    public Scene getSceneFromView()
    {       
        return view.getScene();
    }
    
}
