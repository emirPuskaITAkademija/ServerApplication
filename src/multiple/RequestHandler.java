package multiple;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

//Runnable requestHandlerRunnable = new RequestHandler();
//new Thread(requestHandlerRunnable).start();
public class RequestHandler implements Runnable{

    private final Socket clientSocket;

    public RequestHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try(Scanner in = new Scanner(clientSocket.getInputStream());
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)){
            System.out.println("Ovaj zahtjev obrađuje thread: " + Thread.currentThread().getName());
            while (in.hasNext()){
                String input = in.nextLine();
                System.out.println("Klijent poslao: " + input);
                out.println("ODGOVOR od Serverskog Threada: " + Thread.currentThread().getName()+" je vracena poruke ktebi: " + input );
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
