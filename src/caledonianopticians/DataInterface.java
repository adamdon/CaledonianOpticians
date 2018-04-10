/**
 * @author adamdon <adamdon89@gmail.com>
 */

package caledonianopticians;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataInterface 
{
    DataInterface()
    {

    }
    
    //checks if User database present and if not creates one 
    public static void setupUserDatabase()
    {
        if(isUserDatabasePresent() == true)
        {
            System.out.println("User DB found");
        }
        else if(isUserDatabasePresent() == false)
        {
            System.out.println("User DB not found");
            writeUserDatabaseToDisk(getDefaultUsers());
            System.out.println("User DB with default records written");
        }
    }
    
    //checks if Appointment database present and if not creates one 
    public static void setupAppointmentDatabase()
    {
        if(isAppointmentDatabasePresent() == true)
        {
            System.out.println("Appointment DB found");
        }
        else if(isAppointmentDatabasePresent() == false)
        {
            System.out.println("Appointment DB not found");
            writeAppointmentDatabaseToDisk(getDefaultAppointments());
            System.out.println("Appointment DB with default records written");
        }
    }
    
    //Writes an Users Array to disk
    public static void writeUserDatabaseToDisk(ObservableList<User> toBeWrittenUsers)
    {
        try
        {
            ArrayList<User> toBeWrittenArrayListUsers = new ArrayList(toBeWrittenUsers);

            FileOutputStream fosUserDatabase = new FileOutputStream("userDatabase.db");
            ObjectOutputStream oosUserDatabase = new ObjectOutputStream(fosUserDatabase);

            oosUserDatabase.writeObject(toBeWrittenArrayListUsers);
            oosUserDatabase.flush();
            oosUserDatabase.close();              
        }
        catch(IOException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
   
    }
    
    //Writes an Appointments Array to disk
    public static void writeAppointmentDatabaseToDisk(ObservableList<Appointment> toBeWrittenAppointments)
    {
        try
        {
            ArrayList<Appointment> toBeWrittenArrayListAppointments= new ArrayList(toBeWrittenAppointments);

            FileOutputStream fosAppointmentDatabase = new FileOutputStream("appointmentDatabase.db");
            ObjectOutputStream oosAppointmentDatabase = new ObjectOutputStream(fosAppointmentDatabase);

            oosAppointmentDatabase.writeObject(toBeWrittenArrayListAppointments);
            oosAppointmentDatabase.flush();
            oosAppointmentDatabase.close();              
        }
        catch(IOException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
   
    }
    
    //Reads an User Array to from disk
    public static ObservableList<User> readUserDatabaseFromDisk()
    {
        FileInputStream fisUserDatabase = null;
        ObjectInputStream oisUserDatabase = null;
        List<User> readListUsers = null;
        ObservableList<User> readObListUsers = null;
        
        try
        {
            fisUserDatabase = new FileInputStream("userDatabase.db");
            oisUserDatabase = new ObjectInputStream(fisUserDatabase); 
            readListUsers = (List<User>)oisUserDatabase.readObject();
            oisUserDatabase.close();
            readObListUsers = FXCollections.observableArrayList(readListUsers);
        }
        catch(IOException|ClassNotFoundException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        
        return readObListUsers;
    }
    
    //Reads an Appointments Array to from disk
    public static ObservableList<Appointment> readAppointmentDatabaseFromDisk()
    {
        FileInputStream fisAppointmentDatabase = null;
        ObjectInputStream oisAppointmentDatabase = null;
        List<Appointment> readListAppointments = null;
        ObservableList<Appointment> readObListAppointments = null;
        
        try
        {
            fisAppointmentDatabase = new FileInputStream("appointmentDatabase.db");
            oisAppointmentDatabase = new ObjectInputStream(fisAppointmentDatabase); 
            readListAppointments = (List<Appointment>)oisAppointmentDatabase.readObject();
            oisAppointmentDatabase.close();
            readObListAppointments = FXCollections.observableArrayList(readListAppointments);
        }
        catch(IOException|ClassNotFoundException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        
        return readObListAppointments;
    }
    
    //checks if a UserDataBase is present
    public static boolean isUserDatabasePresent()
    {
        File userDatabaseFile = new File("userDatabase.db");
        boolean isPresent = false; 
        
        if(userDatabaseFile.isFile() == true)
        {
            isPresent = true;
        }
        
        return isPresent;
    }
    
    //checks if a Appointment DataBase is present
    public static boolean isAppointmentDatabasePresent()
    {
        File appointmentDatabaseFile = new File("appointmentDatabase.db");
        boolean isPresent = false; 
        
        if(appointmentDatabaseFile.isFile() == true)
        {
            isPresent = true;
        }
        
        return isPresent;
    }
       
    //Makes then returns a Users Array from set of defult values   
    public static ObservableList<User> getDefaultUsers()
    {
        ObservableList<User> defaultUsers = FXCollections.observableArrayList();
        defaultUsers.add(new User("Homer", "Simpson", 100001, "24, new Road"));
        defaultUsers.add(new User("Ned", "Flanders", 100002, "25, new Road"));
        defaultUsers.add(new User("Troy", "McClure", 100003, "26, new Road"));
        defaultUsers.add(new User("Ralph", "Wiggum", 100004, "27, new Road"));
        defaultUsers.add(new User("Kent", "Brockman", 100005, "28, new Road"));
        defaultUsers.add(new User("Fat", "Tony", 100006, "29, new Road"));
        defaultUsers.add(new User("Fat", "Tony", 100007, "29, new Road"));
        defaultUsers.add(new User("Fat", "Tony", 100008, "29, new Road"));
        defaultUsers.add(new User("Fat", "Tony", 100009, "29, new Road"));
        defaultUsers.add(new User("Fat", "Tony", 100010, "29, new Road"));
        defaultUsers.add(new User("Fat", "Tony", 100011, "29, new Road"));
        defaultUsers.add(new User("Fat", "Tony", 100012, "29, new Road"));
        defaultUsers.add(new User("Fat", "Tony", 100013, "29, new Road"));
        defaultUsers.add(new User("Fat", "Tony", 100014, "29, new Road"));
        return defaultUsers;
    }
    
    
    //Makes then returns a Appointment Array from set of defult values   
    public static ObservableList<Appointment> getDefaultAppointments()
    {
        ObservableList<Appointment> defaultAppointments = FXCollections.observableArrayList();
        defaultAppointments.add(new Appointment(200001, 100002, "Dr Leonard McCoy", "30.04.18 - 1430", "sore eyes", "Note 1"));
        defaultAppointments.add(new Appointment(200002, 100002, "Dr Julian Bashir", "25.04.18 - 0930", "squinty eyes", "Note 1"));
        defaultAppointments.add(new Appointment(200003, 100002, "Dr Leonard McCoy", "30.04.18 - 1430", "eyes too small", "Note 1"));
        defaultAppointments.add(new Appointment(200004, 100004, "Dr Julian Bashir", "30.04.18 - 1430", "broken left eye", "Note 1"));
        defaultAppointments.add(new Appointment(200005, 100005, "Dr Beverly Crusher", "30.04.18 - 1430", "pink eye", "Note 1"));
        defaultAppointments.add(new Appointment(200006, 100005, "Dr Doctor Phlox", "30.04.18 - 1430", "dead eye", "Note 1"));
        defaultAppointments.add(new Appointment(200007, 100005, "Dr The Doctor", "30.04.18 - 1430", "gone blind", "Note 1"));
        defaultAppointments.add(new Appointment(200008, 100004, "Dr The Doctor", "30.04.18 - 1430", "gone blind", "Note 1"));
        defaultAppointments.add(new Appointment(200009, 100001, "Dr The Doctor", "30.04.18 - 1430", "gone blind", "Note 1"));
        defaultAppointments.add(new Appointment(200010, 100001, "Dr The Doctor", "30.04.18 - 1430", "gone blind", "Note 1"));
        defaultAppointments.add(new Appointment(200011, 100004, "Dr The Doctor", "30.04.18 - 1430", "gone blind", "Note 1"));
        defaultAppointments.add(new Appointment(200012, 100001, "Dr The Doctor", "30.04.18 - 1430", "gone blind", "Note 1"));
        defaultAppointments.add(new Appointment(200013, 100009, "Dr The Doctor", "30.04.18 - 1430", "gone blind", "Note 1"));
        defaultAppointments.add(new Appointment(200014, 100004, "Dr The Doctor", "30.04.18 - 1430", "gone blind", "Note 1"));
        return defaultAppointments;
    }  
    
    
}
