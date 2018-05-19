package serverSocket.server;

import serverRMI.RMIServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;

public class SocketServer extends Thread{

    private String command = null;
    private ObjectOutputStream outputToClient;
    private ObjectInputStream inputFromClient;
    private java.net.Socket s = null;
    private RMIServer rmiServer;

    public SocketServer(Socket s, RMIServer rmiServer) {
        this.rmiServer = rmiServer;
        this.s = s;
    }

    @Override
    public void run(){
        try {

            outputToClient = new ObjectOutputStream(s.getOutputStream());
            inputFromClient = new ObjectInputStream(s.getInputStream());

        } catch (IOException e) {
            System.out.println("IO errore");
        }

        try {
            while (true) {
                System.out.println("Ready to receive a message");
                command = inputFromClient.readUTF();

                System.out.println("Received " + command);

                boolean responce = method(command);
                System.out.println(responce);
                outputToClient.writeBoolean(responce);
                outputToClient.flush();
            }

        } catch (IOException e) {
            command = this.getName();
            System.out.println("IO Error/ Client " + command + " terminated abruptly");
        } catch (NullPointerException e) {
            command = this.getName();
            System.out.println("Client " + command + " closed");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("Connection Closing..");
                if (inputFromClient != null) {
                    inputFromClient.close();
                    System.out.println("Socket Input Stream Closed");
                }
                if (outputToClient != null) {
                    outputToClient.close();
                    System.out.println("Socket Out Closed");
                }
                if (s != null) {
                    s.close();
                    System.out.println("Socket Closed");
                }

            } catch (IOException ie) {
                System.out.println("Socket Close Error");
            }
        }
    }

    public boolean method(String commandMethod) throws Exception {

        if (commandMethod.equals("login")){

            String user = inputFromClient.readUTF();
            String password = inputFromClient.readUTF();
            boolean success = rmiServer.login(user, password);
            outputToClient.writeBoolean(success);

            return success;

        } else if (commandMethod.equals("addSupplier")) {

            String nome = inputFromClient.readUTF();
            String cognome = inputFromClient.readUTF();
            String azienda = inputFromClient.readUTF();
            String fornitura = inputFromClient.readUTF();
            String partitaIVA = inputFromClient.readUTF();

            boolean success = rmiServer.addSupplier(nome, cognome, azienda, fornitura, partitaIVA);

            outputToClient.writeBoolean(success);

            return success;

        }else if(commandMethod.equals("viewSupplier")){

        }else if(commandMethod.equals("searchSupplier")){

        }else if(commandMethod.equals("modifySupplier")){

        }else if(commandMethod.equals("addOrder")){

        }else if(commandMethod.equals("deleteSupplier")){

        }


        else if(commandMethod.equals("addChild")){

            String codiceFiscale = inputFromClient.readUTF();
            String idBambino = inputFromClient.readUTF();
            String nome = inputFromClient.readUTF();
            String cognome = inputFromClient.readUTF();
            LocalDate date = LocalDate.parse(inputFromClient.readUTF());
            String luogo = inputFromClient.readUTF();
            String allergie = inputFromClient.readUTF();
            String genitore1 = inputFromClient.readUTF();
            String genitore2 = inputFromClient.readUTF();
            String sesso = inputFromClient.readUTF();
            String pediatra = inputFromClient.readUTF();
            String contatto = inputFromClient.readUTF();

            boolean success = rmiServer.addChild(codiceFiscale, idBambino, nome, cognome, date, luogo,  allergie,  genitore1,  genitore2,  sesso,  pediatra, contatto);

            outputToClient.writeBoolean(success);

            return success;

        }else if(commandMethod.equals("searchChild")){

        }else if(commandMethod.equals("viewChild")){

        }else if(commandMethod.equals("modifyChild")){

        }else if(commandMethod.equals("deleteChild")){

        }


        else if(commandMethod.equals("addTeacher")){

            String nome = inputFromClient.readUTF();
            String cognome = inputFromClient.readUTF();
            String codiceFiscale = inputFromClient.readUTF();
            LocalDate data = LocalDate.parse(inputFromClient.readUTF());
            String luogo = inputFromClient.readUTF();
            String allergie = inputFromClient.readUTF();
            String sesso = inputFromClient.readUTF();
            String insegnante = inputFromClient.readUTF();
            String username = inputFromClient.readUTF();
            String password = inputFromClient.readUTF();

            boolean success = rmiServer.addTeacher( nome,  cognome, codiceFiscale,  data,  luogo,  allergie,  sesso,  insegnante,  username,  password);

            outputToClient.writeBoolean(success);

            return success;

        }else if(commandMethod.equals("addStaff")){

            String nome = inputFromClient.readUTF();
            String cognome = inputFromClient.readUTF();
            String codiceFiscale = inputFromClient.readUTF();
            LocalDate data = LocalDate.parse(inputFromClient.readUTF());
            String luogo = inputFromClient.readUTF();
            String allergie = inputFromClient.readUTF();
            String sesso = inputFromClient.readUTF();
            String mansione = inputFromClient.readUTF();

            boolean success = rmiServer.addStaff(nome, cognome, codiceFiscale, data, luogo, allergie, sesso, mansione);

            outputToClient.writeBoolean(success);

            return success;

        }else if(commandMethod.equals("viewStaff")){

        }else if(commandMethod.equals("searchStaff")){

        }else if(commandMethod.equals("modifyStaff")){

        }else if(commandMethod.equals("deleteStaff")){

        }


        else if(commandMethod.equals("addParents")){

        }else if(commandMethod.equals("viewParents")){

        }else if(commandMethod.equals("searchParents")){

        }else if(commandMethod.equals("modifyParents")){

        }else if(commandMethod.equals("deleteParents")){

        }


        else if(commandMethod.equals("addContact")){

        }else if(commandMethod.equals("viewContact")){

        }else if(commandMethod.equals("searchContact")){

        }else if(commandMethod.equals("modifyContact")){

        }else if(commandMethod.equals("deleteContact")){

        }


        else if(commandMethod.equals("addDoctor")){

        }else if(commandMethod.equals("viewDoctor")){

        }else if(commandMethod.equals("searchDoctor")){

        }else if(commandMethod.equals("modifyDoctor")){

        }else if(commandMethod.equals("deleteDoctor")){

        }


        else if(commandMethod.equals("addMenu")){

        }else if(commandMethod.equals("viewFirst")){

        }else if(commandMethod.equals("viewSecond")){

        }else if(commandMethod.equals("viewAllergy")){

        }else if(commandMethod.equals("viewMenu")){

        }else if(commandMethod.equals("addPrimo")){

        }else if(commandMethod.equals("addSecondo")){

        }else if(commandMethod.equals("deleteMenu")){

        }


        else if(commandMethod.equals("newTrip")){

        }else if(commandMethod.equals("viewTrip")){

        }else if(commandMethod.equals("loadDataServer")){

        }else if(commandMethod.equals("bambinoPresenteServer")){

        }else if(commandMethod.equals("newTappa")){

        }else if(commandMethod.equals("newPartecipante")){

        }else if(commandMethod.equals("deleteTrip")){

        }else if(commandMethod.equals("pullmanCount")){

        }


        return false;
    }
}
