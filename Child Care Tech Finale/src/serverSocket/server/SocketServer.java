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

            System.out.println("Sto Eseguendo da Socket");
            outputToClient.writeObject(rmiServer.viewSupplier());
            outputToClient.reset();
            return true;

        }else if(commandMethod.equals("searchSupplier")){

            System.out.println("Sto Eseguendo da Socket");
            String azienda = inputFromClient.readUTF();
            String fornitura = inputFromClient.readUTF();
            String partitaIva = inputFromClient.readUTF();
            outputToClient.writeObject(rmiServer.searchSupplier(azienda, fornitura, partitaIva));
            outputToClient.reset();
            return true;

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

            System.out.println("Sto Eseguendo da Socket");
            String nome = inputFromClient.readUTF();
            String cognome = inputFromClient.readUTF();
            String codiceFiscale = inputFromClient.readUTF();
            outputToClient.writeObject(rmiServer.searchC(nome, cognome, codiceFiscale));
            outputToClient.reset();
            return true;

        }else if(commandMethod.equals("viewChild")){

            System.out.println("Sto Eseguendo da Socket");
            outputToClient.writeObject(rmiServer.viewChild());
            outputToClient.reset();
            return true;

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

            System.out.println("Sto Eseguendo da Socket");
            outputToClient.writeObject(rmiServer.viewStaff());
            outputToClient.reset();
            return true;

        }else if(commandMethod.equals("searchStaff")){

        }else if(commandMethod.equals("modifyStaff")){

        }else if(commandMethod.equals("deleteStaff")){

        }


        else if(commandMethod.equals("addParents")){

            String codiceFiscale = inputFromClient.readUTF();
            String nome = inputFromClient.readUTF();
            String cognome = inputFromClient.readUTF();
            LocalDate date = LocalDate.parse(inputFromClient.readUTF());
            String luogo = inputFromClient.readUTF();
            String telefono = inputFromClient.readUTF();
            String sesso = inputFromClient.readUTF();

            boolean success = rmiServer.addParents(codiceFiscale, nome, cognome, date, luogo, telefono, sesso);

            outputToClient.writeBoolean(success);

            return success;

        }else if(commandMethod.equals("viewParents")){

            System.out.println("Sto Eseguendo da Socket");
            outputToClient.writeObject(rmiServer.viewParents());
            outputToClient.reset();

            return true;

        }else if(commandMethod.equals("searchParents")){

        }else if(commandMethod.equals("modifyParents")){

        }else if(commandMethod.equals("deleteParents")){

        }


        else if(commandMethod.equals("addContact")){

            String codiceFiscale = inputFromClient.readUTF();
            String nome = inputFromClient.readUTF();
            String cognome = inputFromClient.readUTF();
            String telefono = inputFromClient.readUTF();

            boolean success = rmiServer.addContact(codiceFiscale, nome, cognome, telefono);

            outputToClient.writeBoolean(success);

            return success;


        }else if(commandMethod.equals("viewContacts")){

            System.out.println("Sto Eseguendo da Socket");
            outputToClient.writeObject(rmiServer.viewContacts());
            outputToClient.reset();
            return true;

        }else if(commandMethod.equals("searchContacts")){

        }else if(commandMethod.equals("modifyContacts")){

        }else if(commandMethod.equals("deleteContacts")){

        }


        else if(commandMethod.equals("addDoctor")){

            String codiceFiscale = inputFromClient.readUTF();
            String nome = inputFromClient.readUTF();
            String cognome = inputFromClient.readUTF();
            LocalDate date = LocalDate.parse(inputFromClient.readUTF());
            String luogo = inputFromClient.readUTF();
            String sesso = inputFromClient.readUTF();

            boolean success = rmiServer.addDoctor(codiceFiscale, nome, cognome, date, luogo, sesso);

            outputToClient.writeBoolean(success);

            return success;

        }else if(commandMethod.equals("viewDoctor")){

            System.out.println("Sto Eseguendo da Socket");
            outputToClient.writeObject(rmiServer.viewDoctors());
            outputToClient.reset();

            return true;

        }else if(commandMethod.equals("searchDoctor")){

        }else if(commandMethod.equals("modifyDoctor")){

        }else if(commandMethod.equals("deleteDoctor")){

        }


        else if(commandMethod.equals("addMenu")){

            String nome = inputFromClient.readUTF();
            String primo = inputFromClient.readUTF();
            String secondo = inputFromClient.readUTF();
            LocalDate giorno = LocalDate.parse(inputFromClient.readUTF());

            boolean success = rmiServer.addMenu(nome, primo, secondo, giorno);

            outputToClient.writeBoolean(success);

            return success;

        }else if(commandMethod.equals("viewFirst")){

            System.out.println("Sto Eseguendo da Socket");
            outputToClient.writeObject(rmiServer.viewFirst());
            outputToClient.reset();
            return true;

        }else if(commandMethod.equals("viewSecond")){

            System.out.println("Sto Eseguendo da Socket");
            outputToClient.writeObject(rmiServer.viewSecond());
            outputToClient.reset();
            return true;

        }else if(commandMethod.equals("viewAllergy")){

            System.out.println("Sto Eseguendo da Socket");
            outputToClient.writeObject(rmiServer.viewAllergy());
            outputToClient.reset();
            return true;

        }else if(commandMethod.equals("viewMenu")){

            System.out.println("Sto Eseguendo da Socket");
            outputToClient.writeObject(rmiServer.viewMenu());
            outputToClient.reset();
            return true;

        }else if(commandMethod.equals("addPrimo")){

            String nome = inputFromClient.readUTF();
            String allergeni = inputFromClient.readUTF();

            boolean success = rmiServer.addPrimo(nome, allergeni);

            outputToClient.writeBoolean(success);

            return success;

        }else if(commandMethod.equals("addSecondo")){

            String nome = inputFromClient.readUTF();
            String allergeni = inputFromClient.readUTF();

            boolean success = rmiServer.addSecondo(nome, allergeni);

            outputToClient.writeBoolean(success);

            return success;

        }else if(commandMethod.equals("deleteMenu")){

        }


        else if(commandMethod.equals("newTrip")){

            String id = inputFromClient.readUTF();
            String meta = inputFromClient.readUTF();
            LocalDate andata = LocalDate.parse(inputFromClient.readUTF());
            LocalDate ritorno = LocalDate.parse(inputFromClient.readUTF());

            boolean success = rmiServer.newTrip(id, meta, andata, ritorno);

            outputToClient.writeBoolean(success);

            return success;


        }else if(commandMethod.equals("viewTrip")){

            System.out.println("Sto Eseguendo da Socket");
            outputToClient.writeObject(rmiServer.viewTrip());
            outputToClient.reset();
            return true;

        }else if(commandMethod.equals("loadDataServer")){

            System.out.println("Sto Eseguendo da Socket");
            String idGita = inputFromClient.readUTF();
            outputToClient.writeObject(rmiServer.loadDataServer(Integer.parseInt(idGita)));
            outputToClient.reset();
            return true;

        }else if(commandMethod.equals("bambinoPresenteServer")){

        }else if(commandMethod.equals("newTappa")){

            String numeroTappa = inputFromClient.readUTF();
            String tappa = inputFromClient.readUTF();
            String idGita = inputFromClient.readUTF();
            LocalDate giorno = LocalDate.parse(inputFromClient.readUTF());
            String ora = inputFromClient.readUTF();

            boolean success = rmiServer.newTappaServer(numeroTappa, tappa, idGita, giorno, ora);

            outputToClient.writeBoolean(success);

            return success;


        }else if(commandMethod.equals("newPartecipante")){

            String codiceFiscale = inputFromClient.readUTF();
            String idGita = inputFromClient.readUTF();

            boolean success = rmiServer.newpartecipanteTrip(codiceFiscale, idGita);

            outputToClient.writeBoolean(success);

            return success;


        }else if(commandMethod.equals("deleteTrip")){

        }else if(commandMethod.equals("pullmanCount")){

        }


        return false;
    }
}
