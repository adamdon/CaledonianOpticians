# CaledonianOpticians
### Customer Management Database

 - [Download .jar (unzip)](https://github.com/adamdon/CaledonianOpticians/releases/download/1.0/CaledonianOpticians1.0.zip)
 
![Screenshot of UI](/img/CaledonianOpticians_screenshot01.png)

Developed by myself as part of a larger 1st year project to create an appointments management tool that could make, update, search and store bookings.

The UI was make using a JavaFX gridpane, the logic was implemented in the Controller class (using the Model–view–controller design) and the database utilised ObjectOutputStream with FileOutputStream to write the full array of objects to disk for persistence.

```markdown
public void handleBtnAppointmentRegister()
{
 isAppointmentModifyModeActive = false;
 clearAppointmentDetails();
 view.txtAppointmentRef.setText(Integer.toString(allAppointments.get(allAppointments.size() - 1).getIntAppointmentRef() + 1));
 appointmentDetailsMakeEditable();
 updateStatusBar("Type all Appointment details then Save");
}
```
 - [View Full Source (github)](https://github.com/adamdon/CaledonianOpticians/tree/1.0/src/caledonianopticians)
