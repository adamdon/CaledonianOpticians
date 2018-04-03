package caledonianopticians;

import java.util.Scanner;
import javafx.animation.Animation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

public class Controller
{
    View view;
    ObservableList<User> allUsers;
    ObservableList<User> displayedUsers;
    ObservableList<Appointment> allAppointments;
    ObservableList<Appointment> displayedAppointments;
    Boolean isUserModifyModeActive;

    public Controller() 
    {
       view = new View();
       
       allUsers = getUser();
       allAppointments = getAppointment();
       updateUsersTable(allUsers);
       updateAppointmentsTable(allAppointments);
       
       view.btnSearch.setOnAction(e -> handleBtnSearch());
       
       view.btnUserModify.setOnAction(e -> handleBtnUserModify());
       view.btnUserNew.setOnAction(e -> handleBtnUserRegister());
       view.btnUserSave.setOnAction(e -> handleBtnUserSave());
       view.btnUserCancel.setOnAction(e -> handleBtnUserCancel());
       
       view.txtSearchTextField.setOnKeyPressed((event) -> { if(event.getCode() == KeyCode.ENTER) { handleBtnSearch(); } });
       view.tabUserTable.getSelectionModel().selectedIndexProperty().addListener((num) -> handleListenerSelectionUserTable());
       view.tabAppointmentTable.getSelectionModel().selectedIndexProperty().addListener((num) -> handleListenerSelectionAppointmentTable());
       
    }
    

    
    
    public void handleBtnUserModify()
    {
        isUserModifyModeActive = true;
        userDetailsMakeEditable();
    }
    
    public void handleBtnUserRegister()
    {
        isUserModifyModeActive = false;
        clearUserDetails();
        view.txtUserRefNumber.setText(Integer.toString(allUsers.get(allUsers.size() - 1).getIntReference() + 1));
        userDetailsMakeEditable();
    }
    
    public void handleBtnUserSave()
    {
        if(isUserModifyModeActive == true)
        {
            setElementOfUsersArray(getUserFromDetails(), getAllUsersElement());
        }
        else if(isUserModifyModeActive == false)
        {
            addElementOfUsersArray(getUserFromDetails());
        }
        
        handleBtnSearch();
        userDetailsMakeNonEditable();
    }
    
    public void handleBtnUserCancel()
    {
        clearUserDetails();
        userDetailsMakeNonEditable();
    }
    
    public int getAllUsersElement()
    {
        Integer intUserRefUserDetils = Integer.parseInt(view.txtUserRefNumber.getText());
        int intElementOfAllUsersArray = 0;
        int intIndexForLoop = 0;
        
        for(User identifier: allUsers)
        {
            if(intUserRefUserDetils.equals(identifier.getIntReference()))
            {
                intElementOfAllUsersArray = intIndexForLoop;
            }//end if
            intIndexForLoop = intIndexForLoop + 1;
        } // end for
        
        return intElementOfAllUsersArray;
    }
    
    public void setElementOfUsersArray(User userFromDetails, int intUserTableindex)
    {
        allUsers.set(intUserTableindex, userFromDetails);
    }
    
    public void addElementOfUsersArray(User userFromDetails)
    {
        allUsers.add(userFromDetails);
    }
    
    public User getUserFromDetails()
    {
        User userFromDetails = new User();
        userFromDetails.setIntReference(Integer.parseInt(view.txtUserRefNumber.getText()));
        userFromDetails.setSrtFirstName(view.txtUserFirstName.getText());
        userFromDetails.setSrtLastName(view.txtUserLastName.getText());
        userFromDetails.setSrtAddress(view.txtUserAddress.getText());
        
        return userFromDetails; 
    }
      
    public void userDetailsMakeEditable()
    {
        view.txtUserFirstName.setEditable(true);
        view.txtUserFirstName.setOpacity(1.0);
        view.txtUserLastName.setEditable(true);
        view.txtUserLastName.setOpacity(1.0);
//        view.txtUserRefNumber.setEditable(true);
//        view.txtUserRefNumber.setOpacity(1.0);
        view.txtUserAddress.setEditable(true);
        view.txtUserAddress.setOpacity(1.0);
        
        view.btnUserSave.setDisable(false);
        view.btnUserCancel.setDisable(false);
        
        view.tabUserTable.setDisable(true);
        view.tabAppointmentTable.setDisable(true);
        view.txtSearchTextField.setDisable(true);
        view.btnSearch.setDisable(true);
        view.btnUserNew.setDisable(true);
        view.btnUserModify.setDisable(true);
        view.btnAppointmentNew.setDisable(true);
        view.btnAppointmentModify.setDisable(true);
    }
    
    public void userDetailsMakeNonEditable()
    {
        view.txtUserFirstName.setEditable(false);
        view.txtUserFirstName.setOpacity(0.75);
        view.txtUserLastName.setEditable(false);
        view.txtUserLastName.setOpacity(0.75);
//        view.txtUserRefNumber.setEditable(false);
//        view.txtUserRefNumber.setOpacity(0.75);
        view.txtUserAddress.setEditable(false);
        view.txtUserAddress.setOpacity(0.75);   
        
        view.btnUserSave.setDisable(true);
        view.btnUserCancel.setDisable(true);
        
        view.tabUserTable.setDisable(false);
        view.tabAppointmentTable.setDisable(false);
        view.txtSearchTextField.setDisable(false);
        view.btnSearch.setDisable(false);
        view.btnUserNew.setDisable(false);
        view.btnUserModify.setDisable(false);
        view.btnAppointmentNew.setDisable(false);
        view.btnAppointmentModify.setDisable(false);
    }
    
    
    public void searchUsersValidator(String srtPassedSearchTextField)
    {
        ObservableList<User> validatedSearchedUsers = searchUsersAlgorithm(srtPassedSearchTextField);
        
        if(srtPassedSearchTextField.equals("") || validatedSearchedUsers.isEmpty())
        {
            updateUsersTable(allUsers);
            updateAppointmentsTable(allAppointments);
            clearAppointmentDetails();
            clearUserDetails();
            view.txtSearchTextField.selectAll();
            updateStatusBar("No results found for " + srtPassedSearchTextField + " - All users displayed");
        }
        else
        {
            updateUsersTable(validatedSearchedUsers);
            populateUserDetails(getUserTableSelection());
            searchAppointments(getUserTableSelection());
            populateAppointmentDetails(getAppointmentTableSelection());
            view.txtSearchTextField.selectAll();
            updateStatusBar("Search results for " + srtPassedSearchTextField + " complete");
        }
 
    }
    
    public ObservableList<User> searchUsersAlgorithm(String srtPassedSearchTextField)
    {
        ObservableList<User> searchedUsers = FXCollections.observableArrayList();
        for(User identifier: allUsers)
        {
            String strFullName = identifier.getSrtFirstName() + " " + identifier.getSrtLastName();
            Integer ingIntergerRef = identifier.getIntReference(); // changing primitive data type int to Interger

            if(identifier.getSrtFirstName().equalsIgnoreCase(srtPassedSearchTextField))
            {
                searchedUsers.add(identifier);
            }
            else if(identifier.getSrtLastName().equalsIgnoreCase(srtPassedSearchTextField))
            {
                searchedUsers.add(identifier);
            }
            else if (strFullName.equalsIgnoreCase(srtPassedSearchTextField))
            {
                searchedUsers.add(identifier);
            }
            else if (ingIntergerRef.toString().equals(srtPassedSearchTextField))
            {
                searchedUsers.add(identifier);
            }
        } // end of for loop
        return searchedUsers;
    }
    
    public void searchAppointments(int intUserTableindex)
    {
        ObservableList<Appointment> SearchedAppointments = FXCollections.observableArrayList();
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
    
    
    public void populateUserDetails(int intUserTableindex)
    {
        Integer intUserRefSelected = displayedUsers.get(intUserTableindex).getIntReference();
        
        view.txtUserFirstName.setText(displayedUsers.get(intUserTableindex).getSrtFirstName());
        view.txtUserLastName.setText(displayedUsers.get(intUserTableindex).getSrtLastName());
        view.txtUserRefNumber.setText(intUserRefSelected.toString());
        view.txtUserAddress.setText(displayedUsers.get(intUserTableindex).getSrtAddress()); 
    }
    
    public void populateAppointmentDetails(int intAppointmentTableindex)
    {
        if(displayedAppointments.isEmpty())
        {
            clearAppointmentDetails();
        }
        else
        {
            Integer intAppointmentRefSelected = displayedAppointments.get(intAppointmentTableindex).getIntAppointmentRef();
            Integer intApointmentUserRefSelected = displayedAppointments.get(intAppointmentTableindex).getIntAttendingPatient();

            view.txtAppointmentRef.setText(intAppointmentRefSelected.toString());
            view.txtApointmentUserRef.setText(intApointmentUserRefSelected.toString());
            view.txtAppointmentOptician.setText(displayedAppointments.get(intAppointmentTableindex).getStrAttendingOptician());
            view.txtAppointmentTime.setText(displayedAppointments.get(intAppointmentTableindex).getStrAppointmentTime()); 
            view.txtAppointmentType.setText(displayedAppointments.get(intAppointmentTableindex).getAppointmentType());
            view.txtAppointmentNote.setText(displayedAppointments.get(intAppointmentTableindex).getSrtNote());
        }
    }
    
    public void clearUserDetails()
    {
        view.txtUserFirstName.setText("");
        view.txtUserLastName.setText("");
        view.txtUserRefNumber.setText("");
        view.txtUserAddress.setText(""); 
    }
    
    public void clearAppointmentDetails()
    {
        view.txtAppointmentRef.setText("");
        view.txtApointmentUserRef.setText("");
        view.txtAppointmentOptician.setText("");
        view.txtAppointmentTime.setText(""); 
        view.txtAppointmentType.setText(""); 
        view.txtAppointmentNote.setText(""); 
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
    
    public int getAppointmentTableSelection()
    {
        int intAppointmentTableindex = 0;
        if ((view.tabAppointmentTable.getSelectionModel().selectedIndexProperty().get()) >= 0)
        {
            intAppointmentTableindex = view.tabAppointmentTable.getSelectionModel().selectedIndexProperty().get();
        }
        return  intAppointmentTableindex;       
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
    
    public void updateStatusBar(String srtPassedText)
    {
        view.lblStatusBarText.setText(" " + srtPassedText);
        System.out.println("Status bar updated with " + srtPassedText);
        FadeTransition fadTransition = new FadeTransition(Duration.seconds(0.5), view.lblStatusBarText);
        fadTransition.setFromValue(0.1);
        fadTransition.setToValue(1.0);
        fadTransition.setCycleCount(3);
        fadTransition.play();
    }
    
    
    public Scene getSceneFromView()
    {       
        return view.getScene();
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
        allAppointments.add(new Appointment(200001, 100002, "Dr Leonard McCoy", "30.04.18 - 1430", "sore eyes", "Note 1"));
        allAppointments.add(new Appointment(200002, 100002, "Dr Julian Bashir", "25.04.18 - 0930", "squinty eyes", "Note 1"));
        allAppointments.add(new Appointment(200003, 100002, "Dr Leonard McCoy", "30.04.18 - 1430", "eyes too small", "Note 1"));
        allAppointments.add(new Appointment(200004, 100004, "Dr Julian Bashir", "30.04.18 - 1430", "broken left eye", "Note 1"));
        allAppointments.add(new Appointment(200005, 100005, "Dr Beverly Crusher", "30.04.18 - 1430", "pink eye", "Note 1"));
        allAppointments.add(new Appointment(200006, 100005, "Dr Doctor Phlox", "30.04.18 - 1430", "dead eye", "Note 1"));
        allAppointments.add(new Appointment(200007, 100005, "Dr The Doctor", "30.04.18 - 1430", "gone blind", "Note 1"));
        allAppointments.add(new Appointment(200008, 100004, "Dr The Doctor", "30.04.18 - 1430", "gone blind", "Note 1"));
        allAppointments.add(new Appointment(200009, 100001, "Dr The Doctor", "30.04.18 - 1430", "gone blind", "Note 1"));
        allAppointments.add(new Appointment(200010, 100001, "Dr The Doctor", "30.04.18 - 1430", "gone blind", "Note 1"));
        allAppointments.add(new Appointment(200011, 100004, "Dr The Doctor", "30.04.18 - 1430", "gone blind", "Note 1"));
        allAppointments.add(new Appointment(200012, 100001, "Dr The Doctor", "30.04.18 - 1430", "gone blind", "Note 1"));
        allAppointments.add(new Appointment(200013, 100009, "Dr The Doctor", "30.04.18 - 1430", "gone blind", "Note 1"));
        allAppointments.add(new Appointment(200014, 100004, "Dr The Doctor", "30.04.18 - 1430", "gone blind", "Note 1"));
        return allAppointments;
    }  
    
     public void handleBtnSearch()
    {
        searchUsersValidator(view.txtSearchTextField.getText());
    }
    
    public void handleListenerSelectionUserTable()
    {    
        searchAppointments(getUserTableSelection());
        populateUserDetails(getUserTableSelection());
        populateAppointmentDetails(getAppointmentTableSelection());
    }
    
    public void handleListenerSelectionAppointmentTable()
    {    
        populateAppointmentDetails(getAppointmentTableSelection());
    }
    
}
