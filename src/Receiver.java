import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class Receiver extends Thread {

    Socket socket;

    public Receiver(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        try {

            while (true) {

                InputStreamReader isr = new InputStreamReader(socket.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(isr);

                char[] buffer = new char[200];
                int anzahlZeichen = bufferedReader.read(buffer, 0, 200);
                String nachricht = new String(buffer, 0, anzahlZeichen);
                String[] nachrichten = nachricht.split("\n");


                if (nachrichten[1].trim().equals("error")) {
                    System.out.println("Error: " + nachrichten[4]);
                    throw new Exception();

                } else if (nachrichten[1].trim().equals("user text notify")) {

                    for (int j = 6; j < nachrichten.length; j++) {
                        //String sender = "(" + nachrichten[2] + ") ";
                        //System.out.println( sender.concat(nachrichten[j]) );
                        System.out.print("(" + nachrichten[2]);
                        System.out.println( "(" + nachrichten[2].trim() + ") " + nachrichten[j] );

                    }
                }

            }

        } catch (Exception e) {
            System.exit(0);
        }

    }

}
