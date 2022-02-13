import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerRunnable implements Runnable {

    private Socket socket;
    private Connection connection = Database.connectDb();
    private String query;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private ObjectOutputStream oos;

    public ServerRunnable(Socket sk) {
        socket = sk;
    }

    @Override
    public void run() {
        try (
                OutputStream outputStream = socket.getOutputStream();
                InputStream inputStream = socket.getInputStream();
                PrintWriter printWriter = new PrintWriter(outputStream, true);
                Scanner scanner = new Scanner(inputStream);
        ) {
            System.out.println("Successfully connected to client!");

            while (scanner.hasNextLine()) {
                String inputLine = scanner.nextLine();

                switch (inputLine) {
                    case "ADD":
                        addStaff(scanner, printWriter);
                        break;
                    case "LISTALL":
                        getAllBookings();
                        break;
                    case "LISTPT":
                        listPT(scanner, printWriter);
                        break;
                    case "LISTCLIENT":
                        listClient(scanner, printWriter);
                        break;
                    case "LISTDAY":
                        listDay(scanner, printWriter);
                        break;
                    case "UPDATE":
                        updateBooking(scanner, printWriter);
                        break;
                    case "DELETE":
                        deleteBooking(scanner, printWriter);
                        break;
                    default:
                        printWriter.println("Invalid command!");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void addStaff(Scanner scanner, PrintWriter printWriter) {
        try {
            query = "INSERT INTO Booking (BookingID, BookingDate, StartTime, ClientID, SpecialismID, TrainerID) VALUES(?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);

            String BookingID = scanner.nextLine();
            preparedStatement.setString(1, BookingID);

            String BookingDate = scanner.nextLine();
            preparedStatement.setString(2, BookingDate);

            String StartTime = scanner.nextLine();
            preparedStatement.setString(3, StartTime);

            String ClientID = scanner.nextLine();
            preparedStatement.setString(4, ClientID);

            String SpecialismID = scanner.nextLine();
            preparedStatement.setString(5, SpecialismID);

            String TrainerID = scanner.nextLine();
            preparedStatement.setString(6, TrainerID);

            int a = preparedStatement.executeUpdate();

            if (a > 0) {
                printWriter.println("Details successfully added!");
            } else {
                printWriter.println("Error! Data not added!");
            }
        } catch (Exception ex) {
            printWriter.println(ex);
        }
    }

    public ArrayList<Booking> getAllBookings() throws Exception {
        ArrayList<Booking> bookingArrayList = new ArrayList<>();

        query = "SELECT * FROM Booking";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            Booking booking = new Booking();

            booking.setBookingID(resultSet.getString("BookingID"));
            booking.setBookingDate(resultSet.getString("BookingDate"));
            booking.setStartTime(resultSet.getString("StartTime"));
            booking.setClientID(resultSet.getString("ClientID"));
            booking.setSpecialismID(resultSet.getString("SpecialismID"));
            booking.setTrainerID(resultSet.getString("TrainerID"));

            bookingArrayList.add(booking);
        }

        try {
            oos = new ObjectOutputStream(this.socket.getOutputStream());
            oos.writeObject(bookingArrayList);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return bookingArrayList;
    }

    public void listPT(Scanner scanner, PrintWriter printWriter) {
        try {
            query = "SELECT * FROM Booking WHERE TrainerID=?";
            preparedStatement = connection.prepareStatement(query);

            String trainerID = scanner.nextLine();
            preparedStatement.setString(1, trainerID);

            resultSet = preparedStatement.executeQuery();

            ArrayList<Booking> bookingArrayList = new ArrayList<>();

                while (resultSet.next()) {
                    Booking booking = new Booking();

                    booking.setBookingID(resultSet.getString("BookingID"));
                    booking.setBookingDate(resultSet.getString("BookingDate"));
                    booking.setStartTime(resultSet.getString("StartTime"));
                    booking.setClientID(resultSet.getString("ClientID"));
                    booking.setSpecialismID(resultSet.getString("SpecialismID"));
                    booking.setTrainerID(resultSet.getString("TrainerID"));

                    bookingArrayList.add(booking);
            }

            try {
                oos = new ObjectOutputStream(this.socket.getOutputStream());
                oos.writeObject(bookingArrayList);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        } catch (Exception ex) {
            printWriter.println(ex);
        }
    }

    public void listClient(Scanner scanner, PrintWriter printWriter) {
        try {
            query = "SELECT * FROM Booking WHERE ClientID=?";
            preparedStatement = connection.prepareStatement(query);

            String clientID = scanner.nextLine();
            preparedStatement.setString(1, clientID);

            resultSet = preparedStatement.executeQuery();

            ArrayList<Booking> bookingArrayList = new ArrayList<>();

            while (resultSet.next()) {
                Booking booking = new Booking();

                booking.setBookingID(resultSet.getString("BookingID"));
                booking.setBookingDate(resultSet.getString("BookingDate"));
                booking.setStartTime(resultSet.getString("StartTime"));
                booking.setClientID(resultSet.getString("ClientID"));
                booking.setSpecialismID(resultSet.getString("SpecialismID"));
                booking.setTrainerID(resultSet.getString("TrainerID"));

                bookingArrayList.add(booking);
            }

            try {
                oos = new ObjectOutputStream(this.socket.getOutputStream());
                oos.writeObject(bookingArrayList);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        } catch (Exception ex) {
            printWriter.println(ex);
        }
    }

    public void listDay(Scanner scanner, PrintWriter printWriter) {
        try {
            query = "SELECT * FROM Booking WHERE BookingDate=?";
            preparedStatement = connection.prepareStatement(query);

            String BookingDate = scanner.nextLine();
            preparedStatement.setString(1, BookingDate);

            resultSet = preparedStatement.executeQuery();

            ArrayList<Booking> bookingArrayList = new ArrayList<>();

            while (resultSet.next()) {
                Booking booking = new Booking();

                booking.setBookingID(resultSet.getString("BookingID"));
                booking.setBookingDate(resultSet.getString("BookingDate"));
                booking.setStartTime(resultSet.getString("StartTime"));
                booking.setClientID(resultSet.getString("ClientID"));
                booking.setSpecialismID(resultSet.getString("SpecialismID"));
                booking.setTrainerID(resultSet.getString("TrainerID"));

                bookingArrayList.add(booking);
            }

            try {
                oos = new ObjectOutputStream(this.socket.getOutputStream());
                oos.writeObject(bookingArrayList);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        } catch (Exception ex) {
            printWriter.println(ex);
        }
    }

    public void updateBooking(Scanner scanner, PrintWriter printWriter) {
        try {

            String bookingDetail = scanner.nextLine();

            query = "UPDATE Booking " + " SET " + bookingDetail + " = ?" + " WHERE BookingID=?";
            preparedStatement = connection.prepareStatement(query);

            String newValue = scanner.nextLine();
            preparedStatement.setString(1, newValue);

            String BookingID = scanner.nextLine();
            preparedStatement.setString(2, BookingID);

            int a = preparedStatement.executeUpdate();

            if (a > 0) {
                printWriter.println("Details successfully updated!");
            } else {
                printWriter.println("Error! Please check details again.");
            }
        } catch (Exception ex) {
            printWriter.println(ex);
        }
    }

    public void deleteBooking(Scanner scanner, PrintWriter printWriter) {
        try {
            query = "DELETE FROM Booking" + " WHERE BookingID = ?";
            preparedStatement = connection.prepareStatement(query);

            String bookingID = scanner.nextLine();
            preparedStatement.setString(1, bookingID);

            int a = preparedStatement.executeUpdate();

            if (a > 0) {
                printWriter.println("Details successfully deleted!");
            } else {
                printWriter.println("Error! No such record found!");
            }
        } catch (Exception ex) {
            printWriter.println(ex);
        }
    }

}
