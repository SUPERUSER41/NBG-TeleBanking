package nbg.telebanking.server;
/*
 * @ created by J.Laing*/

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import nbg.telebanking.models.Transaction;
import nbg.telebanking.models.User;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import nbg.telebanking.database.*;

public class SocketServer implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(SocketServer.class);

    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Socket socket;
    private ServerSocket serverSocket;
    private UserSQLProvider user;
    private TransactionSQLProvider trans;
    private MessageSQLProvider mess;
    private QuerySQLProvider query;

    public SocketServer() {
        this.createConnection();
        this.waitForRequest();
    }

    public void createConnection() {
        try {
            serverSocket = new ServerSocket(3333);
            LOGGER.debug("Connection created");

        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Connected was close\n" + e);
        }
    }

    private void getStreams() {
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Server was Disconnected \n" + e);
        }
    }

    public void closeConnection() {
        try {
            oos.close();
            ois.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("Server was Disconnected");
        }
    }

    public void waitForRequest() {

        try {
            while (true) {
                socket = serverSocket.accept();
                this.getStreams();
                String action = "";
                System.out.println("Waiting for client request");
                do {
                    try {
                        action = (String) ois.readObject();
                        if (action.equals("new account")){
                            User user1=new User();
                            user=(UserSQLProvider)ois.readObject();
                            oos.writeObject(user.create(user1));
                            LOGGER.info("A new user was added to the database.");
                        }
                        if (action.equals("Login")) {
                            User user1 = new User();
                            user = (UserSQLProvider) ois.readObject();
                            oos.writeObject(user.userLogIn(user1));
                        }
                        if (action.equals("search transaction")) {
                            trans=(TransactionSQLProvider)ois.readObject();

                        }
                        if (action.equals("view chart for debit")) {
                            System.out.println("");

                        }
                        if (action.equals("view chart for credit")) {
                            System.out.println("");

                        }
                        if (action.equals("search by date")) {
                            System.out.println("");

                        }
                        if (action.equals("send message")) {
                            System.out.println("");

                        }
                        if (action.equals("Open chat client")) {
                            System.out.println("");

                        }
                        if (action.equals("add funds")) {
                            System.out.println("");

                        }
                        if (action.equals("transfer funds")) {
                            trans=(TransactionSQLProvider)ois.readObject();

                        }
                        if (action.equals("bill payment")) {
                            Transaction transaction=new Transaction();
                            trans=(TransactionSQLProvider)ois.readObject();
                            oos.writeObject(transaction.getTransType());
                        }
                        if (action.equals("EXIT")) {

                            System.out.println("Thanks for used our application. good bye :)");
                            System.exit(0);
                        }
                    } catch (IOException e) {
                        oos.writeObject(false);
                        e.printStackTrace();
                        LOGGER.error("Server was Disconnected \n" + e);
                    } catch (ClassNotFoundException e) {
                        oos.writeObject(false);
                        e.printStackTrace();
                        LOGGER.error("Server was Disconnected \n" + e);
                    } catch (ClassCastException e) {
                        oos.writeObject(false);
                        LOGGER.error("Server was Disconnected \n" + e);
                    }
                } while (!action.equals("EXIT"));
                closeConnection();
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Server was Disconnected \n" + e);
        }
    }

    public void run() {

    }


}
