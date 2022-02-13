import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Client {

    public static void main(String[] args) {

        String hostName = "localhost";
        int port = 8080;

        try (
                Socket socket = new Socket(hostName, port);
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                PrintWriter printWriter = new PrintWriter(outputStream, true);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        ) {
            System.out.println("Successfully connected to port " + port);

            System.out.println("Welcome, please select an option below: "
                    + "\n\nOptions:\t\t\t\t\tCommand:"
                    + "\nAdd booking.  \t\t\t\tADD <BookingID> <BookingDate> <StartTime> <ClientID> <SpecialismID> <TrainerID>"
                    + "\nList all bookings  \t\t\tLISTALL"
                    + "\nList personal trainer.  \tLISTPT <TrainerID>"
                    + "\nList client bookings.  \t\tLISTCLIENT <ClientID>"
                    + "\nList booking date.  \t\tLISTDAY <BookingDate>"
                    + "\nUpdate booking.  \t\t\tUPDATE UPDATE <BookingDetail> <NewValue> <BookingID>"
                    + "\nDelete booking. \t\t\tDELETE <BookingID>"
                    + "\nExit.  \t\t\t\t\t\tEXIT");

            while (true) {
                System.out.print(">> Option: ");
                String userInput = stdIn.readLine();
                String[] user = userInput.split("\\s+");

                if (user[0].equals("ADD")) {
                    ADD(user, printWriter, bufferedReader);
                } else if (user[0].equals("LISTALL")) {
                    LISTALL(user, inputStream, printWriter);
                } else if (user[0].equals("LISTPT")) {
                    LISTPT(user, printWriter, inputStream);
                } else if(user[0].equals("LISTCLIENT")) {
                    LISTCLIENT(user, printWriter, inputStream);
                } else if(user[0].equals("LISTDAY")) {
                    LISTDAY(user, printWriter, inputStream);
                }else if (user[0].equals("UPDATE")) {
                    UPDATEBOOKING(user, printWriter, bufferedReader);
                } else if (user[0].equals("DELETE")) {
                    DELETEBOOKING(user, printWriter, bufferedReader);
                } else if (user[0].equals("EXIT")) {
                    System.out.println("Exiting...");
                    System.exit(0);
                } else {
                    System.out.println("Invalid Command!");
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public static void ADD(String[] args, PrintWriter printWriter, BufferedReader bufferedReader) throws Exception {
        if (args.length != 7) {
            System.err.println("Usage: ADD <BookingID> <BookingDate> <StartTime> <ClientID> <SpecialismID> <TrainerID>");
            System.exit(1);
        } else {
            for (int i = 0; i < 7; i++) {
                printWriter.println(args[i]);
            }

            String message = bufferedReader.readLine();
            System.out.println(message);
        }
    }

    public static void LISTALL(String[] args, InputStream inputStream, PrintWriter printWriter) throws Exception {
        printWriter.println(args[0]);

        if (args.length != 1) {
            System.err.println("Usage: LISTALL");
            System.exit(1);
        } else {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            ArrayList<Booking> bookingArrayList = (ArrayList<Booking>) objectInputStream.readObject();

            for (Booking booking : bookingArrayList) {
                System.out.println("BookingID : " + booking.getBookingID()
                        + "\nBooking Date: " + booking.getBookingDate()
                        + "\nStart time: " + booking.getStartTime()
                        + "\nClientID: " + booking.getClientID()
                        + "\nSpecialismID: " + booking.getSpecialismID()
                        + "\nTrainerID: " + booking.getTrainerID());
            }
        }
    }

    public static void LISTPT(String[] args, PrintWriter printWriter, InputStream inputStream) throws Exception {

        if (args.length != 2) {
            System.err.println("Usage: LISTPT <TrainerID>");
            System.exit(1);
        } else {
            printWriter.println(args[0]);
            printWriter.println(args[1]);

            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            ArrayList<Booking> bookingArrayList = (ArrayList<Booking>) objectInputStream.readObject();

            for(Booking booking: bookingArrayList) {
                System.out.println("TrainerID: " + booking.getTrainerID()
                        + "\nBookingID : " + booking.getBookingID()
                        + "\nBooking Date: " + booking.getBookingDate()
                        + "\nStart time: " + booking.getStartTime()
                        + "\nClientID: " + booking.getClientID()
                        + "\nSpecialismID: " + booking.getSpecialismID());
            }
        }
    }

    public static void LISTCLIENT(String[] args, PrintWriter printWriter, InputStream inputStream) throws Exception {

        if (args.length != 2) {
            System.err.println("Usage: LISTCLIENT <ClientID>");
            System.exit(1);
        } else {
            printWriter.println(args[0]);
            printWriter.println(args[1]);

            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            ArrayList<Booking> bookingArrayList = (ArrayList<Booking>) objectInputStream.readObject();

            for(Booking booking: bookingArrayList) {
                System.out.println("ClientID: " + booking.getClientID()
                        + "\nBooking Date: " + booking.getBookingDate()
                        + "\nStart time: " + booking.getStartTime()
                        + "\nBookingID : " + booking.getBookingID()
                        + "\nSpecialismID: " + booking.getSpecialismID()
                        + "\nTrainerID: " + booking.getTrainerID());
            }
        }
    }

    public static void LISTDAY(String[] args, PrintWriter printWriter, InputStream inputStream) throws Exception {

        if (args.length != 2) {
            System.err.println("Usage: LISTDAY <BookingDate>");
            System.exit(1);
        } else {
            printWriter.println(args[0]);
            printWriter.println(args[1]);

            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            ArrayList<Booking> bookingArrayList = (ArrayList<Booking>) objectInputStream.readObject();

            for(Booking booking: bookingArrayList) {
                System.out.println("Booking Date: " + booking.getBookingDate()
                        + "\nClientID: " + booking.getClientID()
                        + "\nStart time: " + booking.getStartTime()
                        + "\nBookingID : " + booking.getBookingID()
                        + "\nSpecialismID: " + booking.getSpecialismID()
                        + "\nTrainerID: " + booking.getTrainerID());
            }
        }
    }

    public static void UPDATEBOOKING(String[] args, PrintWriter printWriter, BufferedReader bufferedReader) throws Exception {
        if (args.length != 4) {
            System.err.println("Usage: UPDATE <BookingDetail> <NewValue> <BookingID>");
            System.exit(1);
        } else {
            for (int i = 0; i < 4; i++) {
                printWriter.println(args[i]);
            }

            String message = bufferedReader.readLine();
            System.out.println(message);
        }
    }

    public static void DELETEBOOKING(String[] args, PrintWriter printWriter, BufferedReader bufferedReader) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: DELETE <BookingID>");
            System.exit(1);
        } else {
            printWriter.println(args[0]);
            printWriter.println(args[1]);

            String message = bufferedReader.readLine();
            System.out.println(message);
        }
    }
}
