import java.net.*;

public class Server {

    public static void main(String[] args) {

        final int PORT = 8080;

        try {
            System.out.println("Starting the server at port: " + PORT);
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                while (true) {
                    Socket socket = serverSocket.accept();

                    new Thread(new ServerRunnable(socket)).start();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

}
