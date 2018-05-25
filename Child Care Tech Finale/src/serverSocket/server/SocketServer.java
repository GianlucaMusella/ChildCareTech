package serverSocket.server;

import getterAndSetter.food.*;
import getterAndSetter.people.*;
import getterAndSetter.trip.TripGS;
import serverRMI.server.RMIServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;

public class SocketServer extends Thread implements Runnable {

    //private String command = null;
    private ObjectOutputStream outputToClient;
    private ObjectInputStream inputFromClient;
    private Socket s = null;
    private RMIServer rmiServer;

    public SocketServer(Socket s, RMIServer rmiServer) {
        this.rmiServer = rmiServer;
        this.s = s;
    }

    boolean responce = false;

    @Override
    public void run(){
        try {

            outputToClient = new ObjectOutputStream(s.getOutputStream());
            outputToClient.flush();
            inputFromClient = new ObjectInputStream(s.getInputStream());

        } catch (IOException e) {
            System.out.println("IO errore");
            e.printStackTrace();
        }

        try {
            for(;;) {
                System.out.println("Aspetto il Comando");
                String command = (String) inputFromClient.readUnshared();

                System.out.println("Il comando ricevuto è " + command);

                responce = method(command);

                System.out.println(responce);
                outputToClient.writeUnshared(responce);
                outputToClient.flush();
                outputToClient.reset();
            }

        } catch (Exception e) {
            String threadName = this.getName();
            System.out.println("EOF: " + threadName + " terminated");
            e.printStackTrace();
            try {
                s.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public boolean method(String commandMethod) throws Exception {

        if (commandMethod.equals("login")){

            String user = (String) inputFromClient.readUnshared();
            String password = (String) inputFromClient.readUnshared();
            boolean success = rmiServer.login(user, password);
            outputToClient.writeUnshared(success);
            outputToClient.flush();
            return success;

        } else if (commandMethod.equals("addSupplier")) {

            String nome = (String) inputFromClient.readUnshared();
            String cognome = (String) inputFromClient.readUnshared();
            String azienda = (String) inputFromClient.readUnshared();
            String fornitura = (String) inputFromClient.readUnshared();
            String partitaIVA = (String) inputFromClient.readUnshared();

            boolean success = rmiServer.addSupplier(nome, cognome, azienda, fornitura, partitaIVA);

            outputToClient.writeUnshared(success);
            outputToClient.flush();

            return success;

        }else if(commandMethod.equals("viewSupplier")){

            System.out.println("Carico Dati da Socket");
            ArrayList<SupplierGS> isLoadedal = rmiServer.viewSupplier();
            if (isLoadedal == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(isLoadedal);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("searchSupplier")){

            System.out.println("Cerco dal Socket");
            String azienda = null;
            String fornitura = null;
            String partitaIva = null;

            try {
                azienda = (String) inputFromClient.readUnshared();
                fornitura = (String) inputFromClient.readUnshared();
                partitaIva = (String) inputFromClient.readUnshared();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ArrayList<SupplierGS> isLoadedal = rmiServer.searchSupplier(azienda, fornitura, partitaIva);
            if (isLoadedal == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(isLoadedal);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("modifySupplier")){

            String azienda = (String) inputFromClient.readUnshared();
            String nome = (String) inputFromClient.readUnshared();
            String cognome = (String) inputFromClient.readUnshared();
            String fornitura = (String) inputFromClient.readUnshared();
            String partitaIva = (String) inputFromClient.readUnshared();
            rmiServer.modifySupplier(azienda, nome, cognome, fornitura, partitaIva);
            return true;

        }else if(commandMethod.equals("addOrder")){

            String azienda = (String) inputFromClient.readUnshared();
            String ordini = (String) inputFromClient.readUnshared();
            String quantità = (String) inputFromClient.readUnshared();

            boolean success = rmiServer.addOrder(azienda, ordini, quantità);

            outputToClient.writeUnshared(success);
            outputToClient.flush();

            return success;

        }else if(commandMethod.equals("deleteSupplier")){

            System.out.println("Sto eseguendo da Socket");
            String azienda = (String) inputFromClient.readUnshared();

            boolean success = rmiServer.deleteSupplier(azienda);

            outputToClient.writeUnshared(success);
            outputToClient.flush();

            return success;
        }


        else if(commandMethod.equals("addChild")){

            String codiceFiscale = (String) inputFromClient.readUnshared();
            String idBambino = (String) inputFromClient.readUnshared();
            String nome = (String) inputFromClient.readUnshared();
            String cognome = (String) inputFromClient.readUnshared();
            LocalDate date = LocalDate.parse((String) inputFromClient.readUnshared());
            String luogo = (String) inputFromClient.readUnshared();
            String allergie = (String) inputFromClient.readUnshared();
            String genitore1 = (String) inputFromClient.readUnshared();
            String genitore2 = (String) inputFromClient.readUnshared();
            String sesso = (String) inputFromClient.readUnshared();
            String pediatra = (String) inputFromClient.readUnshared();
            String contatto = (String) inputFromClient.readUnshared();

            boolean success = rmiServer.addChild(codiceFiscale, idBambino, nome, cognome, date, luogo,  allergie,  genitore1,  genitore2,  sesso,  pediatra, contatto);

            outputToClient.writeUnshared(success);
            outputToClient.flush();

            return success;

        }else if(commandMethod.equals("searchChild")){

            System.out.println("Cerco dal Socket");
            String nome = null;
            String cognome = null;
            String codiceFiscale = null;

            try {
                nome = (String) inputFromClient.readUnshared();
                cognome = (String) inputFromClient.readUnshared();
                codiceFiscale = (String) inputFromClient.readUnshared();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ArrayList<ChildGS> isLoadedal = rmiServer.searchC(nome, cognome, codiceFiscale);
            if (isLoadedal == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(isLoadedal);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("viewChild")){

            System.out.println("Carico Dati da Socket");
            ArrayList<ChildGS> isLoadedal = rmiServer.viewChild();
            if (isLoadedal == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(isLoadedal);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("modifyChild")){

            String codiceFiscale = (String) inputFromClient.readUnshared();
            String nome = (String) inputFromClient.readUnshared();
            String cognome = (String) inputFromClient.readUnshared();
            String luogo = (String) inputFromClient.readUnshared();
            LocalDate data = LocalDate.parse((String) inputFromClient.readUnshared());
            String idBambino = (String) inputFromClient.readUnshared();
            rmiServer.modifyChild(codiceFiscale, nome, cognome, luogo, data, idBambino);
            return true;

        }else if(commandMethod.equals("deleteChild")){

            System.out.println("Sto eseguendo da Socket");
            String codiceFiscale = (String) inputFromClient.readUnshared();

            Boolean success = rmiServer.deleteChild(codiceFiscale);

            outputToClient.writeUnshared(success);
            outputToClient.flush();

            return success;

        }


        else if(commandMethod.equals("addTeacher")){

            String nome = (String) inputFromClient.readUnshared();
            String cognome = (String) inputFromClient.readUnshared();
            String codiceFiscale = (String) inputFromClient.readUnshared();
            LocalDate data = LocalDate.parse((String) inputFromClient.readUnshared());
            String luogo = (String) inputFromClient.readUnshared();
            String allergie = (String) inputFromClient.readUnshared();
            String sesso = (String) inputFromClient.readUnshared();
            String insegnante = (String) inputFromClient.readUnshared();
            String username = (String) inputFromClient.readUnshared();
            String password = (String) inputFromClient.readUnshared();

            boolean success = rmiServer.addTeacher( nome,  cognome, codiceFiscale,  data,  luogo,  allergie,  sesso,  insegnante,  username,  password);

            outputToClient.writeUnshared(success);
            outputToClient.flush();

            return success;

        }else if(commandMethod.equals("addStaff")){

            String nome = (String) inputFromClient.readUnshared();
            String cognome = (String) inputFromClient.readUnshared();
            String codiceFiscale = (String) inputFromClient.readUnshared();
            LocalDate data = LocalDate.parse((String) inputFromClient.readUnshared());
            String luogo = (String) inputFromClient.readUnshared();
            String allergie = (String) inputFromClient.readUnshared();
            String sesso = (String) inputFromClient.readUnshared();
            String mansione = (String) inputFromClient.readUnshared();

            boolean success = rmiServer.addStaff(nome, cognome, codiceFiscale, data, luogo, allergie, sesso, mansione);

            outputToClient.writeUnshared(success);
            outputToClient.flush();

            return success;

        }else if(commandMethod.equals("viewStaff")){

            System.out.println("Carico Dati da Socket");
            ArrayList<StaffGS> isLoadedal = rmiServer.viewStaff();
            if (isLoadedal == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(isLoadedal);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("searchStaff")){

            System.out.println("Cerco dal Socket");
            String nome = null;
            String cognome = null;
            String codiceFiscale = null;

            try {
                nome = (String) inputFromClient.readUnshared();
                cognome = (String) inputFromClient.readUnshared();
                codiceFiscale = (String) inputFromClient.readUnshared();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ArrayList<StaffGS> isLoadedal = rmiServer.searchStaff(nome, cognome, codiceFiscale);
            if (isLoadedal == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(isLoadedal);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("modifyStaff")){

            String codiceFiscale = (String) inputFromClient.readUnshared();
            String nome = (String) inputFromClient.readUnshared();
            String cognome = (String) inputFromClient.readUnshared();
            String luogo = (String) inputFromClient.readUnshared();
            LocalDate data = LocalDate.parse((String) inputFromClient.readUnshared());
            String mansione = (String) inputFromClient.readUnshared();
            rmiServer.modifyStaff(codiceFiscale, nome, cognome, luogo, data, mansione);
            return true;

        }else if(commandMethod.equals("deleteStaff")){

            System.out.println("Sto eseguendo da Socket");
            String codiceFiscale = (String) inputFromClient.readUnshared();

            Boolean success = rmiServer.deleteStaff(codiceFiscale);

            outputToClient.writeUnshared(success);
            outputToClient.flush();

            return success;

        }


        else if(commandMethod.equals("addParents")){

            String codiceFiscale = (String) inputFromClient.readUnshared();
            String nome = (String) inputFromClient.readUnshared();
            String cognome = (String) inputFromClient.readUnshared();
            LocalDate date = LocalDate.parse((String) inputFromClient.readUnshared());
            String luogo = (String) inputFromClient.readUnshared();
            String telefono = (String) inputFromClient.readUnshared();
            String sesso = (String) inputFromClient.readUnshared();

            boolean success = rmiServer.addParents(codiceFiscale, nome, cognome, date, luogo, telefono, sesso);

            outputToClient.writeUnshared(success);
            outputToClient.flush();

            return success;

        }else if(commandMethod.equals("viewParents")){

            System.out.println("Carico Dati da Socket");
            ArrayList<ParentsGS> isLoadedal = rmiServer.viewParents();
            if (isLoadedal == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(isLoadedal);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("searchParents")){

            System.out.println("Cerco dal Socket");
            String nome = null;
            String codiceFiscale = null;

            try {
                nome = (String) inputFromClient.readUnshared();
                codiceFiscale = (String) inputFromClient.readUnshared();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ArrayList<ParentsGS> isLoadedal = rmiServer.searchParents(nome, codiceFiscale);
            if (isLoadedal == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(isLoadedal);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("modifyParents")){

            String codiceFiscale = (String) inputFromClient.readUnshared();
            String nome = (String) inputFromClient.readUnshared();
            String cognome = (String) inputFromClient.readUnshared();
            String luogo = (String) inputFromClient.readUnshared();
            LocalDate data = LocalDate.parse((String) inputFromClient.readUnshared());
            String telefono = (String) inputFromClient.readUnshared();
            rmiServer.modifyParents(codiceFiscale, nome, cognome, luogo, data, telefono);
            return true;

        }else if(commandMethod.equals("deleteParents")){

            System.out.println("Sto eseguendo da Socket");
            String codiceFiscale = (String) inputFromClient.readUnshared();

            Boolean success = rmiServer.deleteParents(codiceFiscale);

            outputToClient.writeUnshared(success);
            outputToClient.flush();

            return success;

        }


        else if(commandMethod.equals("addContact")){

            String codiceFiscale = (String) inputFromClient.readUnshared();
            String nome = (String) inputFromClient.readUnshared();
            String cognome = (String) inputFromClient.readUnshared();
            String telefono = (String) inputFromClient.readUnshared();

            boolean success = rmiServer.addContact(codiceFiscale, nome, cognome, telefono);

            outputToClient.writeUnshared(success);
            outputToClient.flush();

            return success;


        }else if(commandMethod.equals("viewContacts")){

            System.out.println("Carico Dati da Socket");
            ArrayList<ContactGS> isLoadedal = rmiServer.viewContacts();
            if (isLoadedal == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(isLoadedal);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("searchContacts")){

            System.out.println("Cerco dal Socket");
            String nome = null;
            String codiceFiscale = null;

            try {
                nome = (String) inputFromClient.readUnshared();
                codiceFiscale = (String) inputFromClient.readUnshared();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ArrayList<ContactGS> isLoadedal = rmiServer.searchContacts(nome, codiceFiscale);
            if (isLoadedal == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(isLoadedal);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("modifyContacts")){

            String codiceFiscale = (String) inputFromClient.readUnshared();
            String nome = (String) inputFromClient.readUnshared();
            String cognome = (String) inputFromClient.readUnshared();
            String telefono = (String) inputFromClient.readUnshared();
            rmiServer.modifyContact(codiceFiscale, nome, cognome, telefono);
            return true;

        }else if(commandMethod.equals("deleteContacts")){

            System.out.println("Sto eseguendo da Socket");
            String codiceFiscale = (String) inputFromClient.readUnshared();

            Boolean success = rmiServer.deleteContacts(codiceFiscale);

            outputToClient.writeUnshared(success);
            outputToClient.flush();

            return success;

        }


        else if(commandMethod.equals("addDoctor")){

            String codiceFiscale = (String) inputFromClient.readUnshared();
            String nome = (String) inputFromClient.readUnshared();
            String cognome = (String) inputFromClient.readUnshared();
            LocalDate date = LocalDate.parse((String) inputFromClient.readUnshared());
            String luogo = (String) inputFromClient.readUnshared();
            String sesso = (String) inputFromClient.readUnshared();

            boolean success = rmiServer.addDoctor(codiceFiscale, nome, cognome, date, luogo, sesso);

            outputToClient.writeUnshared(success);
            outputToClient.flush();

            return success;

        }else if(commandMethod.equals("viewDoctor")){

            System.out.println("Carico Dati da Socket");
            ArrayList<DoctorGS> isLoadedal = rmiServer.viewDoctors();
            if (isLoadedal == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(isLoadedal);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("searchDoctor")){

            System.out.println("Cerco dal Socket");
            String nome = null;
            String codiceFiscale = null;

            try {
                nome = (String) inputFromClient.readUnshared();
                codiceFiscale = (String) inputFromClient.readUnshared();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ArrayList<DoctorGS> isLoadedal = rmiServer.searchDoctors(nome, codiceFiscale);
            if (isLoadedal == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(isLoadedal);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("modifyDoctor")){

            String codiceFiscale = (String) inputFromClient.readUnshared();
            String nome = (String) inputFromClient.readUnshared();
            String cognome = (String) inputFromClient.readUnshared();
            String luogo = (String) inputFromClient.readUnshared();
            LocalDate data = LocalDate.parse((String) inputFromClient.readUnshared());
            rmiServer.modifyDoctor(codiceFiscale, nome, cognome, luogo, data);
            return true;

        }else if(commandMethod.equals("deleteDoctor")){

            System.out.println("Sto eseguendo da Socket");
            String codiceFiscale = (String) inputFromClient.readUnshared();

            Boolean success = rmiServer.deleteDoctors(codiceFiscale);

            outputToClient.writeUnshared(success);
            outputToClient.flush();

            return success;

        }


        else if(commandMethod.equals("addMenu")){

            String nome = (String) inputFromClient.readUnshared();
            String primo = (String) inputFromClient.readUnshared();
            String secondo = (String) inputFromClient.readUnshared();
            LocalDate giorno = LocalDate.parse((String) inputFromClient.readUnshared());

            boolean success = rmiServer.addMenu(nome, primo, secondo, giorno);

            outputToClient.writeUnshared(success);
            outputToClient.flush();

            return success;

        }else if(commandMethod.equals("viewFirst")){

            System.out.println("Carico Dati da Socket");
            ArrayList<FirstDishGS> isLoadedal = rmiServer.viewFirst();
            if (isLoadedal == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(isLoadedal);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("viewSecond")){

            System.out.println("Carico Dati da Socket");
            ArrayList<SecondDishGS> isLoadedal = rmiServer.viewSecond();
            if (isLoadedal == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(isLoadedal);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("viewSide")) {

            System.out.println("Carico Dati da Socket");
            ArrayList<SideDishGS> isLoadedal = rmiServer.viewSide();
            if (isLoadedal == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(isLoadedal);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("viewAllergy")){

            System.out.println("Carico Dati da Socket");
            ArrayList<SideDishGS> isLoadedal = rmiServer.viewSide();
            if (isLoadedal == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(isLoadedal);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("viewMenu")){

            System.out.println("Carico Dati da Socket");
            ArrayList<MenuGS> isLoadedal = rmiServer.viewMenu();
            if (isLoadedal == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(isLoadedal);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("viewCheck")){

            System.out.println("Cerco dal Socket");
            String nomeMenu = null;

            try {
                nomeMenu = (String) inputFromClient.readUnshared();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ArrayList<BambiniAllergici> isLoadedal = rmiServer.viewCheck(nomeMenu);
            if (isLoadedal == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(isLoadedal);
                outputToClient.flush();
                responce = true;
            }
            return responce;


        }else if(commandMethod.equals("addPrimo")){

            String nome = (String) inputFromClient.readUnshared();
            String allergeni = (String) inputFromClient.readUnshared();

            boolean success = rmiServer.addPrimo(nome, allergeni);

            outputToClient.writeUnshared(success);
            outputToClient.flush();

            return success;

        }else if(commandMethod.equals("addSecondo")){

            String nome = (String) inputFromClient.readUnshared();
            String allergeni = (String) inputFromClient.readUnshared();

            boolean success = rmiServer.addSecondo(nome, allergeni);

            outputToClient.writeUnshared(success);
            outputToClient.flush();

            return success;

        }else if(commandMethod.equals("addSide")){

            String nome = (String) inputFromClient.readUnshared();
            String allergeni = (String) inputFromClient.readUnshared();

            boolean success = rmiServer.addSide(nome, allergeni);

            outputToClient.writeUnshared(success);
            outputToClient.flush();

            return success;

        }else if(commandMethod.equals("deleteMenu")){

            System.out.println("Sto eseguendo da Socket");
            String nomeMenu = (String) inputFromClient.readUnshared();

            Boolean success = rmiServer.deleteMenu(nomeMenu);

            outputToClient.writeUnshared(success);
            outputToClient.flush();

            return success;
        }


        else if(commandMethod.equals("AddTrip")){

            String id = (String) inputFromClient.readUnshared();
            String meta = (String) inputFromClient.readUnshared();
            LocalDate andata = LocalDate.parse((String) inputFromClient.readUnshared());
            LocalDate ritorno = LocalDate.parse((String) inputFromClient.readUnshared());

            boolean success = rmiServer.newTrip(id, meta, andata, ritorno);

            outputToClient.writeUnshared(success);
            outputToClient.flush();

            return success;

        }else if(commandMethod.equals("viewTrip")){

            System.out.println("Carico Dati da Socket");
            ArrayList<TripGS> isLoadedal = rmiServer.viewTrip();
            if (isLoadedal == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(isLoadedal);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("loadDataServer")){

            System.out.println("Sto Eseguendo da Socket");
            String idGita = (String) inputFromClient.readUnshared();
            outputToClient.writeUnshared(rmiServer.loadDataServer(Integer.parseInt(idGita)));
            outputToClient.flush();
            return true;

        }else if(commandMethod.equals("bambinoPresenteServer")){

            String codiceFiscale = (String) inputFromClient.readUnshared();
            String idGita = (String) inputFromClient.readUnshared();

            rmiServer.bambinoPresenteServer(codiceFiscale, Integer.parseInt(idGita));
            return true;

        }else if(commandMethod.equals("bambinoAssenteServer")){

            String codiceFiscale = (String) inputFromClient.readUnshared();
            String idGita = (String) inputFromClient.readUnshared();

            rmiServer.bambinoAssenteServer(codiceFiscale, Integer.parseInt(idGita));
            return true;

        }else if(commandMethod.equals("newTappa")){

            String tappa = (String) inputFromClient.readUnshared();
            String idGita = (String) inputFromClient.readUnshared();
            LocalDate giorno = LocalDate.parse((String) inputFromClient.readUnshared());
            String ora = (String) inputFromClient.readUnshared();

            boolean success = rmiServer.newTappaServer(tappa, idGita, giorno, ora);

            outputToClient.writeUnshared(success);
            outputToClient.flush();

            return success;


        }else if(commandMethod.equals("newPartecipante")){

            String codiceFiscale = (String) inputFromClient.readUnshared();
            String idGita = (String) inputFromClient.readUnshared();

            boolean success = rmiServer.newpartecipanteTrip(codiceFiscale, idGita);

            outputToClient.writeUnshared(success);
            outputToClient.flush();

            return success;


        }else if(commandMethod.equals("deleteTrip")){

            System.out.println("Sto eseguendo da Socket");
            String idGita = (String) inputFromClient.readUnshared();

            Boolean success = rmiServer.deleteTrip(idGita);

            outputToClient.writeUnshared(success);
            outputToClient.flush();

            return success;

        }else if(commandMethod.equals("pullmanCount")){

            String idGita = (String) inputFromClient.readUnshared();

            rmiServer.pullmanCount(idGita);
            return true;

        }

        return false;
    }
}
