package multiple;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultipleClientServer {
    public static void main(String[] args) {
        int portNumber = 8005;
        try(ServerSocket serverSocket = new ServerSocket(portNumber)){
            System.out.println("Čekam na klijente....");
            while(true){
                Socket clientSocket = serverSocket.accept();
                Runnable requestHandler = new RequestHandler(clientSocket);
                new Thread(requestHandler).start();
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
