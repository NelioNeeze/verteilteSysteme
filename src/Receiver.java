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

                for (String lol : nachrichten) {
                    System.out.println(lol);
                }

                if (nachrichten[1].trim().equals("error")) {

                    System.out.println("Error!!!");

                } else if (nachrichten[1].trim().equals("user text notify")) {

                    for (int j = 6; j < nachrichten.length; j++) {
                        System.out.println(gibSchon(nachrichten[2]) + nachrichten[j]);

                    }
                }

            }

        } catch (Exception e) {
            System.out.println("Fehler beim Einlesen der Nachrichten!");
            e.toString();
        }

    }

    public static String gibSchon(String jo){
        return "(" + jo + ") ";
    }

}
