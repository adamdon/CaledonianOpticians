package caledonianopticians;

public class Appointment 
{
    private int intAppointmentRef;
    private int intAttendingPatient;
    private String strAttendingOptician;
    private String strAppointmentTime;
    private String strAppointmentType;
    private String srtNote;

    public Appointment(int intAppointmentRef, int intAttendingPatient, String strAttendingOptician, String strAppointmentTime, String appointmentType, String psrtNote)
    {
        this.intAppointmentRef = intAppointmentRef;
        this.intAttendingPatient = intAttendingPatient;
        this.strAttendingOptician = strAttendingOptician;
        this.strAppointmentTime = strAppointmentTime;
        this.strAppointmentType = appointmentType;
        this.srtNote = psrtNote;
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
    
    public String getSrtNote()
    {
        return srtNote;
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
        return strAppointmentType;
    }

    public void setAppointmentType(String appointmentType)
    {
        this.strAppointmentType = appointmentType;
    }
    
     public void setSrtNote(String srtNote)
    {
        this.srtNote = srtNote;
    }
    
    
    
    
    
    
}
