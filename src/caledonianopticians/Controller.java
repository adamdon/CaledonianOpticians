package caledonianopticians;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
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
    Boolean isAppointmentModifyModeActive;

    public Controller() 
    {
        
       DataInterface.setupUserDatabase();
       DataInterface.setupAppointmentDatabase();
       view = new View();
       
       
       allUsers = getUser();
       allAppointments = getAppointment();
       updateUsersTable(allUsers);
       updateAppointmentsTable(allAppointments);
       
       view.btnSearch.setOnAction(e -> handleBtnSearch());
       view.txtSearchTextField.setOnKeyPressed((event) -> { if(event.getCode() == KeyCode.ENTER) { handleBtnSearch(); } });
       
       view.btnUserModify.setOnAction(e -> handleBtnUserModify());
       view.btnUserNew.setOnAction(e -> handleBtnUserRegister());
       view.btnUserSave.setOnAction(e -> handleBtnUserSave());
       view.btnUserCancel.setOnAction(e -> handleBtnUserCancel());
       
       view.btnAppointmentModify.setOnAction(e -> handleBtnAppointmentModify());
       view.btnAppointmentNew.setOnAction(e -> handleBtnAppointmentRegister());
       view.btnAppointmentSave.setOnAction(e -> handleBtnAppointmentSave());
       view.btnAppointmentCancel.setOnAction(e -> handleBtnAppointmentCancel());
       
       view.tabUserTable.getSelectionModel().selectedIndexProperty().addListener((num) -> handleListenerSelectionUserTable());
       view.tabAppointmentTable.getSelectionModel().selectedIndexProperty().addListener((num) -> handleListenerSelectionAppointmentTable());
       
    }
    

    
    
    
    public void handleBtnAppointmentModify()
    {
        isAppointmentModifyModeActive = true;
        if(view.txtAppointmentRef.getText().equals(""))
        {
            updateStatusBar("Select Appointment first");
        }
        else
        {
            appointmentDetailsMakeEditable();
            updateStatusBar("Make Appointment changes then Save");
        } 
    }
    
    public void handleBtnAppointmentRegister()
    {
        isAppointmentModifyModeActive = false;
        clearAppointmentDetails();
        view.txtAppointmentRef.setText(Integer.toString(allAppointments.get(allAppointments.size() - 1).getIntAppointmentRef() + 1));
        appointmentDetailsMakeEditable();
        updateStatusBar("Type all Appointment details then Save");
    }
    
    public void handleBtnAppointmentSave()
    {
        if(isAppointmentModifyModeActive == true)
        {
            setElementOfAppointmentsArray(getAppointmentFromDetails(), getAllAppointmentsElement());
        }
        else if(isAppointmentModifyModeActive == false)
        {
            addElementOfAppointmentsArray(getAppointmentFromDetails());
        }
        
        DataInterface.writeAppointmentDatabaseToDisk(allAppointments);
        handleBtnSearch();
        appointmentDetailsMakeNonEditable();
        updateStatusBar("Appointment Saved");
    }
    
    public void handleBtnAppointmentCancel()
    {
        clearAppointmentDetails();
        appointmentDetailsMakeNonEditable();
        updateStatusBar("...");
    }
    
    public int getAllAppointmentsElement()
    {
        Integer intAppointmentRefAppointmentDetils = Integer.parseInt(view.txtAppointmentRef.getText());
        int intElementOfAllAppointmentArray = 0;
        int intIndexForLoop = 0;
        
        for(Appointment identifier: allAppointments)
        {
            if(intAppointmentRefAppointmentDetils.equals(identifier.getIntAppointmentRef()))
            {
                intElementOfAllAppointmentArray = intIndexForLoop;
            }//end if
            intIndexForLoop = intIndexForLoop + 1;
        } // end for
        
        return intElementOfAllAppointmentArray;
    }
    
    public Appointment getAppointmentFromDetails()
    {
        Appointment appointmentFromDetails = new Appointment();
        appointmentFromDetails.setIntAppointmentRef(Integer.parseInt(view.txtAppointmentRef.getText()));
        appointmentFromDetails.setIntAttendingPatient(Integer.parseInt(view.txtApointmentUserRef.getText()));
        appointmentFromDetails.setStrAttendingOptician(view.txtAppointmentOptician.getText());
        appointmentFromDetails.setStrAppointmentTime(view.txtAppointmentTime.getText());
        appointmentFromDetails.setAppointmentType(view.txtAppointmentType.getText());
        appointmentFromDetails.setSrtNote(view.txtAppointmentNote.getText());
        
        return appointmentFromDetails; 
    }
    
    public void setElementOfAppointmentsArray(Appointment appointmentFromDetails, int intAppointmentTableindex)
    {
        allAppointments.set(intAppointmentTableindex, appointmentFromDetails);
    }
    
    public void addElementOfAppointmentsArray(Appointment appointmentFromDetails)
    {
        allAppointments.add(appointmentFromDetails);
    }
    
    
    public void appointmentDetailsMakeEditable()
    {
        view.txtAppointmentRef.setEditable(true);
        view.txtAppointmentRef.setOpacity(1.0);
        view.txtApointmentUserRef.setEditable(true);
        view.txtApointmentUserRef.setOpacity(1.0);
        view.txtAppointmentOptician.setEditable(true);
        view.txtAppointmentOptician.setOpacity(1.0);
        view.txtAppointmentTime.setEditable(true);
        view.txtAppointmentTime.setOpacity(1.0);
        view.txtAppointmentType.setEditable(true);
        view.txtAppointmentType.setOpacity(1.0);
        view.txtAppointmentNote.setEditable(true);
        view.txtAppointmentNote.setOpacity(1.0);
        
        view.btnAppointmentSave.setDisable(false);
        view.btnAppointmentCancel.setDisable(false);
        
        view.tabUserTable.setDisable(true);
        view.tabAppointmentTable.setDisable(true);
        view.txtSearchTextField.setDisable(true);
        view.btnSearch.setDisable(true);
        view.btnUserNew.setDisable(true);
        view.btnUserModify.setDisable(true);
        view.btnAppointmentNew.setDisable(true);
        view.btnAppointmentModify.setDisable(true);
    }
    
    public void appointmentDetailsMakeNonEditable()
    {
        view.txtAppointmentRef.setEditable(false);
        view.txtAppointmentRef.setOpacity(0.75);
        view.txtApointmentUserRef.setEditable(false);
        view.txtApointmentUserRef.setOpacity(0.75);
        view.txtAppointmentOptician.setEditable(false);
        view.txtAppointmentOptician.setOpacity(0.75);
        view.txtAppointmentTime.setEditable(false);
        view.txtAppointmentTime.setOpacity(0.75);
        view.txtAppointmentType.setEditable(false);
        view.txtAppointmentType.setOpacity(0.75);
        view.txtAppointmentNote.setEditable(false);
        view.txtAppointmentNote.setOpacity(0.75);
        
        view.btnAppointmentSave.setDisable(true);
        view.btnAppointmentCancel.setDisable(true);
        
        view.tabUserTable.setDisable(false);
        view.tabAppointmentTable.setDisable(false);
        view.txtSearchTextField.setDisable(false);
        view.btnSearch.setDisable(false);
        view.btnUserNew.setDisable(false);
        view.btnUserModify.setDisable(false);
        view.btnAppointmentNew.setDisable(false);
        view.btnAppointmentModify.setDisable(false);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void handleBtnUserModify()
    {
        isUserModifyModeActive = true;
        
        if(view.txtUserRefNumber.getText().equals(""))
        {
            updateStatusBar("Select User first");
        }
        else
        {
            userDetailsMakeEditable();
            updateStatusBar("Make User changes then Save");
        } 
    }
    
    public void handleBtnUserRegister()
    {
        isUserModifyModeActive = false;
        clearUserDetails();
        view.txtUserRefNumber.setText(Integer.toString(allUsers.get(allUsers.size() - 1).getIntReference() + 1));
        userDetailsMakeEditable();
        updateStatusBar("Type all User details then Save");
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
        
        DataInterface.writeUserDatabaseToDisk(allUsers);
        handleBtnSearch();
        userDetailsMakeNonEditable();
        updateStatusBar("User Saved");
    }
    
    public void handleBtnUserCancel()
    {
        clearUserDetails();
        userDetailsMakeNonEditable();
        updateStatusBar("...");
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
    

    public final void updateUsersTable(ObservableList<User> updatedUsers)
    {
        displayedUsers = updatedUsers;
        view.tabUserTable.setItems(displayedUsers);
    }
    
    public final void updateAppointmentsTable(ObservableList<Appointment> updatedAppointments)
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
        fadTransition.setAutoReverse(true);
        fadTransition.play();
    }
    
    
    public Scene getSceneFromView()
    {       
        return view.getScene();
    }
    public final ObservableList<User> getUser()
    {
        allUsers = FXCollections.observableArrayList();
        allUsers = DataInterface.readUserDatabaseFromDisk();

        return allUsers;
    }
    
    public final ObservableList<Appointment> getAppointment()
    {
        allAppointments = FXCollections.observableArrayList();
        allAppointments = DataInterface.readAppointmentDatabaseFromDisk();

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
        updateStatusBar("User selected");
    }
    
    public void handleListenerSelectionAppointmentTable()
    {    
        populateAppointmentDetails(getAppointmentTableSelection());
        updateStatusBar("Appointment selected");
    }
    
}
