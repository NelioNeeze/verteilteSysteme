import java.io.*;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) {

        try {

            String userName = args[0];

            Socket sock = new Socket("dbl44.beuth-hochschule.de", 21);

            Receiver receive = new Receiver(sock);
            receive.start();

            OutputStreamWriter osr = new OutputStreamWriter(sock.getOutputStream());
            PrintWriter printWriter = new PrintWriter(osr);

            printWriter.print("dslp/2.0\r\n");
            printWriter.print("user join\r\n");
            printWriter.print(userName + "\r\n");
            printWriter.print("dslp/body\r\n");
            printWriter.flush();

            System.out.println("Joined as user " + userName + " ...");


            while (true) {

                Scanner sc = new Scanner(System.in);
                String eingabe = sc.nextLine();

                if (eingabe.trim().equals("quit")) {
                    break;
                } else {
                    String[] enter = eingabe.split(":");
                    String receiver = enter[0].trim();
                    eingabe = enter[1].trim();
                    sendMessage(printWriter, eingabe, receiver, userName);
                }

            }

            printWriter.print("dslp/2.0\r\n");
            printWriter.print("user leave\r\n");
            printWriter.print(userName + "\r\n");
            printWriter.print("dslp/body\r\n");
            printWriter.flush();

            System.out.println("Verbindung zum Server wurde unterbrochen!");
            System.exit(0);


        } catch (Exception e) {
            System.out.println("Es konnte keine Verbindung zum Server hergestellt werden!");
            e.getMessage();
        }

    }

    public static void sendMessage(PrintWriter printWriter, String message, String receiver, String userName) {

        printWriter.print("dslp/2.0\r\n");
        printWriter.print("user text notify\r\n");
        printWriter.print(userName + "\r\n");
        printWriter.print(receiver + "\r\n");
        printWriter.print("1\r\n");
        printWriter.print("dslp/body\r\n");
        printWriter.print(message + "\r\n");
        printWriter.flush();
    }

}
