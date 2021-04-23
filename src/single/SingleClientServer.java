package single;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SingleClientServer {

    public static void main(String[] args) {
        if(args.length!=1){
            System.err.println("Hej meni treba broj porta koji ti nemaš");
            System.exit(1);
        }
        String portNumberText = args[0];
        int portNumber = Integer.parseInt(portNumberText);
        System.out.println("Server je konfigurisan na portu: " + portNumber);
        try(ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            Scanner in = new Scanner(clientSocket.getInputStream())){
            System.out.println("Klijent se povezao na portu: " + portNumber);
            while(in.hasNext()){
                String line = in.nextLine();
                System.out.println("Primljena poruka: " + line+" od klijenta: " + clientSocket);
                out.println("Evo ti tvoja poruka natrag: " + line);
            }

        }catch (IOException e){
            System.err.println("Problem prilikom osluškivanja na portu: " + portNumber+" -->"+e.getMessage());
        }
    }
}
