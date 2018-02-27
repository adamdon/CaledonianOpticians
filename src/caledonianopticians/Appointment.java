package caledonianopticians;

public class Appointment 
{
    private int intAppointmentRef;
    private int intAttendingPatient;
    private String strAttendingOptician;
    private String strAppointmentTime;
    private String appointmentType;

    public Appointment(int intAppointmentRef, int intAttendingPatient, String strAttendingOptician, String strAppointmentTime, String appointmentType)
    {
        this.intAppointmentRef = intAppointmentRef;
        this.intAttendingPatient = intAttendingPatient;
        this.strAttendingOptician = strAttendingOptician;
        this.strAppointmentTime = strAppointmentTime;
        this.appointmentType = appointmentType;
    }

    public int getIntAppointmentRef()
    {
        return intAppointmentRef;
    }

    public void setIntAppointmentRef(int intAppointmentRef)
    {
        this.intAppointmentRef = intAppointmentRef;
    }

    public int getIntAttendingPatient()
    {
        return intAttendingPatient;
    }

    public void setIntAttendingPatient(int intAttendingPatient)
    {
        this.intAttendingPatient = intAttendingPatient;
    }

    public String getStrAttendingOptician()
    {
        return strAttendingOptician;
    }

    public void setStrAttendingOptician(String strAttendingOptician)
    {
        this.strAttendingOptician = strAttendingOptician;
    }

    public String getStrAppointmentTime()
    {
        return strAppointmentTime;
    }

    public void setStrAppointmentTime(String strAppointmentTime)
    {
        this.strAppointmentTime = strAppointmentTime;
    }

    public String getAppointmentType()
    {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType)
    {
        this.appointmentType = appointmentType;
    }
    
    
    
    
    
    
}
