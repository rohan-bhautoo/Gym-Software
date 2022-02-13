import java.io.Serializable;

public class Booking implements Serializable {
    private String BookingID;
    private String BookingDate;
    private String StartTime;
    private String ClientID;
    private String SpecialismID;
    private String TrainerID;

    public Booking() {
        this.BookingID = " ";
        this.BookingDate = " ";
        this.StartTime = " ";
        this.ClientID = " ";
        this.SpecialismID = " ";
        this.TrainerID = " ";
    }

    public Booking(String BookingID, String BookingDate, String StartTIme, String ClientID, String SpecialismID, String TrainerID) {
        this.BookingID = BookingID;
        this.BookingDate = BookingDate;
        this.StartTime = StartTIme;
        this.ClientID = ClientID;
        this.SpecialismID = SpecialismID;
        this.TrainerID = TrainerID;
    }

    public String getBookingID() {
        return BookingID;
    }

    public void setBookingID(String bookingID) {
        BookingID = bookingID;
    }

    public String getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(String bookingDate) {
        BookingDate = bookingDate;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getClientID() {
        return ClientID;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public String getSpecialismID() {
        return SpecialismID;
    }

    public void setSpecialismID(String specialismID) {
        SpecialismID = specialismID;
    }

    public String getTrainerID() {
        return TrainerID;
    }

    public void setTrainerID(String trainerID) {
        TrainerID = trainerID;
    }
}
