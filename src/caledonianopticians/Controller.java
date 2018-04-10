/**
 * @author adamdon <adamdon89@gmail.com>
 */

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
        
       DataInterface.setupUserDatabase(); //checks if User database present and if not creates one 
       DataInterface.setupAppointmentDatabase(); //checks if Appointment database present and if not creates one 
       view = new View(); // instantiates full UI with View Class 
       
       
       allUsers = getUser(); //gets all users Array
       allAppointments = getAppointment(); //gets all Appointments Array
       updateUsersTable(allUsers); //updates User Table view with all users
       updateAppointmentsTable(allAppointments); //updates appontments Table with all appointments
       
       view.btnSearch.setOnAction(e -> handleBtnSearch()); //Event handler for search button
       view.txtSearchTextField.setOnKeyPressed((event) -> { if(event.getCode() == KeyCode.ENTER) { handleBtnSearch(); } });
       
       view.btnUserModify.setOnAction(e -> handleBtnUserModify()); //Event handler for User Modify button
       view.btnUserNew.setOnAction(e -> handleBtnUserRegister()); //Event handler for User Register button
       view.btnUserSave.setOnAction(e -> handleBtnUserSave()); //Event handler User Save button 
       view.btnUserCancel.setOnAction(e -> handleBtnUserCancel()); //Event handler for User Cancel button
       
       view.btnAppointmentModify.setOnAction(e -> handleBtnAppointmentModify());  //Event handler for Appointment Modify button
       view.btnAppointmentNew.setOnAction(e -> handleBtnAppointmentRegister()); //Event handler for Appointment Register button
       view.btnAppointmentSave.setOnAction(e -> handleBtnAppointmentSave()); //Event handler Appointment Save button 
       view.btnAppointmentCancel.setOnAction(e -> handleBtnAppointmentCancel()); //Event handler Appointment  Cancel button
       
       view.tabUserTable.getSelectionModel().selectedIndexProperty().addListener((num) -> handleListenerSelectionUserTable());
       view.tabAppointmentTable.getSelectionModel().selectedIndexProperty().addListener((num) -> handleListenerSelectionAppointmentTable());
       
    }
    

    
    
    //handle for click of Appointment Modify button
    public void handleBtnAppointmentModify()
    {
        isAppointmentModifyModeActive = true;
        if(view.txtAppointmentRef.getText().equals(""))//check that text fields aren't black 
        {
            updateStatusBar("Select Appointment first");
        }
        else
        {
            appointmentDetailsMakeEditable(); 
            updateStatusBar("Make Appointment changes then Save");
        } 
    }
    
    //handle for click of Appointment Register button
    public void handleBtnAppointmentRegister()
    {
        isAppointmentModifyModeActive = false;
        clearAppointmentDetails();
        view.txtAppointmentRef.setText(Integer.toString(allAppointments.get(allAppointments.size() - 1).getIntAppointmentRef() + 1));
        appointmentDetailsMakeEditable();
        updateStatusBar("Type all Appointment details then Save");
    }
    
    //handle for click of Appointment Save button
    public void handleBtnAppointmentSave()
    {
        if(isAppointmentDetailsValid() == true)
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
        

    }
    
    //handle for click of Appointment Cancel button
    public void handleBtnAppointmentCancel()
    {
        clearAppointmentDetails();
        appointmentDetailsMakeNonEditable();
        updateStatusBar("...");
    }
    
    //input validation for Appointment Details 
    public boolean isAppointmentDetailsValid()
    {
        if(view.txtAppointmentRef.getText().equals("") //makes sure there isn't any blank fields
         | view.txtApointmentUserRef.getText().equals("")
         | view.txtAppointmentOptician.getText().equals("")
         | view.txtAppointmentTime.getText().equals("")  
         | view.txtAppointmentType.getText().equals("")
         | view.txtAppointmentNote.getText().equals(""))
        {
            updateStatusBar("All details not inputed, update and save again");
            return false;
        }
        else if (isNumeric(view.txtApointmentUserRef.getText()) == false) //makes sure User Ref is a number
        {
            updateStatusBar("Reference number can only contain 0-9");
            return false;
        }
        
        return true; /// returns true only if above conditions are met 
    }
    
    
    //gets index of the Appointment Array for the record that is populated in the details field
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
    
    
    //Makes and returns an Appointment object from the details entered in the text fields  
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
    
    //updates the allAppointments Array with new object passed to method
    public void setElementOfAppointmentsArray(Appointment appointmentFromDetails, int intAppointmentTableindex)
    {
        allAppointments.set(intAppointmentTableindex, appointmentFromDetails);
    }
    
    //adds a new Appointment Object to the allAppointments array
    public void addElementOfAppointmentsArray(Appointment appointmentFromDetails)
    {
        allAppointments.add(appointmentFromDetails);
    }
    
    //Makes all Appointment details text fields editable
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
    
    
    //Makes all Appointment details text fields non-editable
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
    
     //handle for click of User Modify button
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
    
    //handle for click of User Register button
    public void handleBtnUserRegister()
    {
        isUserModifyModeActive = false;
        clearUserDetails();
        view.txtUserRefNumber.setText(Integer.toString(allUsers.get(allUsers.size() - 1).getIntReference() + 1));
        userDetailsMakeEditable();
        updateStatusBar("Type all User details then Save");
    }
    
    //handle for click of User Save button
    public void handleBtnUserSave()
    {
        if(isUserDetailsValid() == true)
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

    }
    
     //handle for click of User Cancel button
    public void handleBtnUserCancel()
    {
        clearUserDetails();
        userDetailsMakeNonEditable();
        updateStatusBar("...");
    }
    
    //input validation for User Details 
    public boolean isUserDetailsValid()
    {
        if(view.txtUserFirstName.getText().equals("")
         | view.txtUserLastName.getText().equals("")
         | view.txtUserRefNumber.getText().equals("")
         | view.txtUserAddress.getText().equals(""))       
        {
            updateStatusBar("All details not inputed, update and save again");
            return false;
        }
        else if (isNumeric(view.txtUserRefNumber.getText()) == false)
        {
            updateStatusBar("Reference number can only contain 0-9");
            return false;
        }
        
        return true;
    }
    
    //gets index of the User Array for the record that is populated in the details field
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
    
    //updates the allUsers Array with new object passed to method
    public void setElementOfUsersArray(User userFromDetails, int intUserTableindex)
    {
        allUsers.set(intUserTableindex, userFromDetails);
    }
    
    //adds a new User Object to the allUsers array
    public void addElementOfUsersArray(User userFromDetails)
    {
        allUsers.add(userFromDetails);
    }
    
    
     //Makes and returns an User object from the details entered in the text fields
    public User getUserFromDetails()
    {
        User userFromDetails = new User();
        userFromDetails.setIntReference(Integer.parseInt(view.txtUserRefNumber.getText()));
        userFromDetails.setSrtFirstName(view.txtUserFirstName.getText());
        userFromDetails.setSrtLastName(view.txtUserLastName.getText());
        userFromDetails.setSrtAddress(view.txtUserAddress.getText());
        
        return userFromDetails; 
    }
     
    
    //Makes all User details text fields editable
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
    
    //Makes all User details text fields non-editable
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
    
    //takes the inputed text from the search field and makes user is isn't empty
    public void searchUsersValidator(String srtPassedSearchTextField)
    {
        ObservableList<User> validatedSearchedUsers = searchUsersAlgorithm(srtPassedSearchTextField); //returns searched Users 
        
        if(srtPassedSearchTextField.equals("") || validatedSearchedUsers.isEmpty()) //checking fields is empty or 
        {
            updateUsersTable(allUsers); //updates table with all records 
            updateAppointmentsTable(allAppointments); //updates table with all records 
            clearAppointmentDetails(); // clears all Appointment text fields 
            clearUserDetails(); // clears all Appointment text fields 
            view.txtSearchTextField.selectAll(); //highlights all text in the search text field 
            updateStatusBar("No results found for " + srtPassedSearchTextField + " - All users displayed");
        }
        else
        {
            updateUsersTable(validatedSearchedUsers); // Updated table with the searched users
            populateUserDetails(getUserTableSelection()); //updates User details with selected index (defult 0)
            searchAppointments(getUserTableSelection()); // Updated table with the searched Appointments
            populateAppointmentDetails(getAppointmentTableSelection()); //updates Appointments details with selected index (defult 0)
            view.txtSearchTextField.selectAll(); //highlights all text in the search text field
            updateStatusBar("Search results for " + srtPassedSearchTextField + " complete");
        }
 
    }
    
    
    //Takes the text from the Search text field then returns an Array of Users that match
    public ObservableList<User> searchUsersAlgorithm(String srtPassedSearchTextField)
    {
        ObservableList<User> searchedUsers = FXCollections.observableArrayList();
        for(User identifier: allUsers)
        {
            String strFullName = identifier.getSrtFirstName() + " " + identifier.getSrtLastName();
            Integer ingIntergerRef = identifier.getIntReference(); // changing primitive data type int to Interger

            if(identifier.getSrtFirstName().equalsIgnoreCase(srtPassedSearchTextField)) //checks for match
            {
                searchedUsers.add(identifier); //addeds User to Array of Users if true
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
    
    //Takes user table index, searches for the User Ref then updates Appointments table with results
    public void searchAppointments(int intUserTableindex)
    {
        ObservableList<Appointment> SearchedAppointments = FXCollections.observableArrayList();
        Integer intUserRefSelected = displayedUsers.get(intUserTableindex).getIntReference();
        
        for(Appointment identifier: allAppointments)
        {
            Integer ingAppointmentPatient = identifier.getIntAttendingPatient();
            
            if(ingAppointmentPatient.equals(intUserRefSelected)) //check to see if user ref matches
            {
                SearchedAppointments.add(identifier); //adds Apponitment object to Array of appointments
            }
        }
        updateAppointmentsTable(SearchedAppointments); //updates appointment table with resullts       
    }
    
    // Updates the User details text fields with the selected row in table
    public void populateUserDetails(int intUserTableindex)
    {
        Integer intUserRefSelected = displayedUsers.get(intUserTableindex).getIntReference();
        
        view.txtUserFirstName.setText(displayedUsers.get(intUserTableindex).getSrtFirstName());
        view.txtUserLastName.setText(displayedUsers.get(intUserTableindex).getSrtLastName());
        view.txtUserRefNumber.setText(intUserRefSelected.toString());
        view.txtUserAddress.setText(displayedUsers.get(intUserTableindex).getSrtAddress()); 
    }
    
    // Updates the Appointment details text fields with the selected row in table
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
    
    
    //sets all User details text fields to blank
    public void clearUserDetails()
    {
        view.txtUserFirstName.setText("");
        view.txtUserLastName.setText("");
        view.txtUserRefNumber.setText("");
        view.txtUserAddress.setText(""); 
    }
    
    //sets all User details text fields to blank
    public void clearAppointmentDetails()
    {
        view.txtAppointmentRef.setText("");
        view.txtApointmentUserRef.setText("");
        view.txtAppointmentOptician.setText("");
        view.txtAppointmentTime.setText(""); 
        view.txtAppointmentType.setText(""); 
        view.txtAppointmentNote.setText(""); 
    }
    
    //returns the index of the selected row of the table 
    public int getUserTableSelection()
    {
        int intUserTableindex = 0;
        if ((view.tabUserTable.getSelectionModel().selectedIndexProperty().get()) >= 0)
        {
            intUserTableindex = view.tabUserTable.getSelectionModel().selectedIndexProperty().get();
        }
        return  intUserTableindex;       
    }
    
    //returns the index of the selected row of the table 
    public int getAppointmentTableSelection()
    {
        int intAppointmentTableindex = 0;
        if ((view.tabAppointmentTable.getSelectionModel().selectedIndexProperty().get()) >= 0)
        {
            intAppointmentTableindex = view.tabAppointmentTable.getSelectionModel().selectedIndexProperty().get();
        }
        return  intAppointmentTableindex;       
    }
    
    //Updates User table with passed Array of Users
    public final void updateUsersTable(ObservableList<User> updatedUsers)
    {
        displayedUsers = updatedUsers;
        view.tabUserTable.setItems(displayedUsers);
    }
    
    //Updates User table with passed Array of Users
    public final void updateAppointmentsTable(ObservableList<Appointment> updatedAppointments)
    {
        displayedAppointments = updatedAppointments;
        view.tabAppointmentTable.setItems(displayedAppointments);
    }
    
    
    //updates a lable used for status bar with passed text with flashing text
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
    
    //returns the scene from inside the View class
    public Scene getSceneFromView()
    {       
        return view.getScene();
    }
    
    //returns the User Array from the disk via DataInterface class
    public final ObservableList<User> getUser()
    {
        allUsers = FXCollections.observableArrayList();
        allUsers = DataInterface.readUserDatabaseFromDisk();

        return allUsers;
    }
    
    //returns the Appointment Array from the disk via DataInterface class
    public final ObservableList<Appointment> getAppointment()
    {
        allAppointments = FXCollections.observableArrayList();
        allAppointments = DataInterface.readAppointmentDatabaseFromDisk();

        return allAppointments;
    }  
    
     //handle for click of Search button
     public void handleBtnSearch()
    {
        searchUsersValidator(view.txtSearchTextField.getText());
    }
    
     // //handle for Lister that checks what indext of User table is selected 
    public void handleListenerSelectionUserTable()
    {    
        searchAppointments(getUserTableSelection());
        populateUserDetails(getUserTableSelection());
        populateAppointmentDetails(getAppointmentTableSelection());
        updateStatusBar("User selected");
    }
    
    // //handle for Lister that checks what indext of Appointment table is selected 
    public void handleListenerSelectionAppointmentTable()
    {    
        populateAppointmentDetails(getAppointmentTableSelection());
        updateStatusBar("Appointment selected");
    }
    
    
    //returns ture if passed text is a number
    public boolean isNumeric(String strIsNumber)  
    {
        try
        {
            Integer ingIsNumber = Integer.parseInt(strIsNumber);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        
        return true;        
    }

}
