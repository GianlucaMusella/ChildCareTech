package serverSocket.server;

import getterAndSetter.food.*;
import getterAndSetter.people.*;
import getterAndSetter.trip.AppealGS;
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

            System.out.println("Logging in...");
            try {
                String user = (String) inputFromClient.readUnshared();
                String password = (String) inputFromClient.readUnshared();
                responce = rmiServer.login(user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return responce;

        } else if (commandMethod.equals("addSupplier")) {

            try {
                String nome = (String) inputFromClient.readUnshared();
                String cognome = (String) inputFromClient.readUnshared();
                String azienda = (String) inputFromClient.readUnshared();
                String fornitura = (String) inputFromClient.readUnshared();
                String partitaIVA = (String) inputFromClient.readUnshared();

                responce= rmiServer.addSupplier(nome, cognome, azienda, fornitura, partitaIVA);

            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return responce;

        }else if(commandMethod.equals("viewSupplier")){

            System.out.println("Carico Dati da Socket");
            ArrayList<SupplierGS> ready = rmiServer.viewSupplier();
            if (ready == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(ready);
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
            ArrayList<SupplierGS> ready = rmiServer.searchSupplier(azienda, fornitura, partitaIva);
            if (ready == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(ready);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("modifySupplier")){

            try{

                String azienda = (String) inputFromClient.readUnshared();
                String nome = (String) inputFromClient.readUnshared();
                String cognome = (String) inputFromClient.readUnshared();
                String fornitura = (String) inputFromClient.readUnshared();
                String partitaIva = (String) inputFromClient.readUnshared();
                responce = rmiServer.modifySupplier(azienda, nome, cognome, fornitura, partitaIva);

            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return responce;

        }else if(commandMethod.equals("addOrder")){

            try {

                String azienda = (String) inputFromClient.readUnshared();
                String ordini = (String) inputFromClient.readUnshared();
                String quantità = (String) inputFromClient.readUnshared();
                responce = rmiServer.addOrder(azienda, ordini, quantità);


            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return responce;

        }else if(commandMethod.equals("deleteSupplier")){

            try {
                System.out.println("Sto eseguendo da Socket");
                String azienda = (String) inputFromClient.readUnshared();

                responce = rmiServer.deleteSupplier(azienda);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return responce;

        }


        else if(commandMethod.equals("addChild")){

            try {
                String codiceFiscale = (String) inputFromClient.readUnshared();
                String idBambino = (String) inputFromClient.readUnshared();
                String nome = (String) inputFromClient.readUnshared();
                String cognome = (String) inputFromClient.readUnshared();
                LocalDate date = LocalDate.parse((CharSequence) inputFromClient.readUnshared());
                String luogo = (String) inputFromClient.readUnshared();
                String allergie = (String) inputFromClient.readUnshared();
                String genitore1 = (String) inputFromClient.readUnshared();
                String genitore2 = (String) inputFromClient.readUnshared();
                String sesso = (String) inputFromClient.readUnshared();
                String pediatra = (String) inputFromClient.readUnshared();
                String contatto = (String) inputFromClient.readUnshared();

                responce = rmiServer.addChild(codiceFiscale, idBambino, nome, cognome, date, luogo, allergie, genitore1, genitore2, sesso, pediatra, contatto);
            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return responce;

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
            ArrayList<ChildGS> ready = rmiServer.searchC(nome, cognome, codiceFiscale);
            if (ready == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(ready);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("viewChild")){

            System.out.println("Carico Dati da Socket");
            ArrayList<ChildGS> ready = rmiServer.viewChild();
            if (ready == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(ready);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("modifyChild")){

            try{

                String codiceFiscale = (String) inputFromClient.readUnshared();
                String nome = (String) inputFromClient.readUnshared();
                String cognome = (String) inputFromClient.readUnshared();
                String luogo = (String) inputFromClient.readUnshared();
                LocalDate data = LocalDate.parse((CharSequence) inputFromClient.readUnshared());
                String idBambino = (String) inputFromClient.readUnshared();
                responce = rmiServer.modifyChild(codiceFiscale, nome, cognome, luogo, data, idBambino);

            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return responce;

        }else if(commandMethod.equals("deleteChild")){

            try {

                System.out.println("Sto eseguendo da Socket");
                String codiceFiscale = (String) inputFromClient.readUnshared();

                responce = rmiServer.deleteChild(codiceFiscale);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return responce;

        }


        else if(commandMethod.equals("addTeacher")){

            try {

                String nome = (String) inputFromClient.readUnshared();
                String cognome = (String) inputFromClient.readUnshared();
                String codiceFiscale = (String) inputFromClient.readUnshared();
                LocalDate data = LocalDate.parse((CharSequence) inputFromClient.readUnshared());
                String luogo = (String) inputFromClient.readUnshared();
                String allergie = (String) inputFromClient.readUnshared();
                String sesso = (String) inputFromClient.readUnshared();
                String insegnante = (String) inputFromClient.readUnshared();
                String username = (String) inputFromClient.readUnshared();
                String password = (String) inputFromClient.readUnshared();

                responce = rmiServer.addTeacher( nome,  cognome, codiceFiscale,  data,  luogo,  allergie,  sesso,  insegnante,  username,  password);

            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return responce;


        }else if(commandMethod.equals("addStaff")){

            try {

                String nome = (String) inputFromClient.readUnshared();
                String cognome = (String) inputFromClient.readUnshared();
                String codiceFiscale = (String) inputFromClient.readUnshared();
                LocalDate data = LocalDate.parse((CharSequence) inputFromClient.readUnshared());
                String luogo = (String) inputFromClient.readUnshared();
                String allergie = (String) inputFromClient.readUnshared();
                String sesso = (String) inputFromClient.readUnshared();
                String mansione = (String) inputFromClient.readUnshared();

                responce = rmiServer.addStaff(nome, cognome, codiceFiscale, data, luogo, allergie, sesso, mansione);

            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return responce;


        }else if(commandMethod.equals("viewStaff")){

            System.out.println("Carico Dati da Socket");
            ArrayList<StaffGS> ready = rmiServer.viewStaff();
            if (ready == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(ready);
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
            ArrayList<StaffGS> ready = rmiServer.searchStaff(nome, cognome, codiceFiscale);
            if (ready == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(ready);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("modifyStaff")){

            try{

                String codiceFiscale = (String) inputFromClient.readUnshared();
                String nome = (String) inputFromClient.readUnshared();
                String cognome = (String) inputFromClient.readUnshared();
                String luogo = (String) inputFromClient.readUnshared();
                LocalDate data = LocalDate.parse((CharSequence) inputFromClient.readUnshared());
                String mansione = (String) inputFromClient.readUnshared();
                responce = rmiServer.modifyStaff(codiceFiscale, nome, cognome, luogo, data, mansione);

            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return responce;

        }else if(commandMethod.equals("deleteStaff")){

            try {
                System.out.println("Sto eseguendo da Socket");
                String codiceFiscale = (String) inputFromClient.readUnshared();

                responce = rmiServer.deleteStaff(codiceFiscale);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return responce;

        }


        else if(commandMethod.equals("addParents")){

            try {

                String codiceFiscale = (String) inputFromClient.readUnshared();
                String nome = (String) inputFromClient.readUnshared();
                String cognome = (String) inputFromClient.readUnshared();
                LocalDate date = LocalDate.parse((CharSequence) inputFromClient.readUnshared());
                String luogo = (String) inputFromClient.readUnshared();
                String telefono = (String) inputFromClient.readUnshared();
                String sesso = (String) inputFromClient.readUnshared();

                responce = rmiServer.addParents(codiceFiscale, nome, cognome, date, luogo, telefono, sesso);
            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return responce;

        }else if(commandMethod.equals("viewParents")){

            System.out.println("Carico Dati da Socket");
            ArrayList<ParentsGS> ready = rmiServer.viewParents();
            if (ready == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(ready);
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
            ArrayList<ParentsGS> ready = rmiServer.searchParents(nome, codiceFiscale);
            if (ready == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(ready);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("modifyParents")){

            try{

                String codiceFiscale = (String) inputFromClient.readUnshared();
                String nome = (String) inputFromClient.readUnshared();
                String cognome = (String) inputFromClient.readUnshared();
                String luogo = (String) inputFromClient.readUnshared();
                LocalDate data = LocalDate.parse((CharSequence) inputFromClient.readUnshared());
                String telefono = (String) inputFromClient.readUnshared();
                responce = rmiServer.modifyParents(codiceFiscale, nome, cognome, luogo, data, telefono);

            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return responce;

        }else if(commandMethod.equals("deleteParents")){

            try {
                System.out.println("Sto eseguendo da Socket");
                String codiceFiscale = (String) inputFromClient.readUnshared();

                responce = rmiServer.deleteParents(codiceFiscale);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return responce;
        }


        else if(commandMethod.equals("addContacts")){

            try {

                String codiceFiscale = (String) inputFromClient.readUnshared();
                String nome = (String) inputFromClient.readUnshared();
                String cognome = (String) inputFromClient.readUnshared();
                String telefono = (String) inputFromClient.readUnshared();

                responce = rmiServer.addContact(codiceFiscale, nome, cognome, telefono);
            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return responce;

        }else if(commandMethod.equals("viewContacts")){

            System.out.println("Carico Dati da Socket");
            ArrayList<ContactGS> ready = rmiServer.viewContacts();
            if (ready == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(ready);
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
            ArrayList<ContactGS> ready = rmiServer.searchContacts(nome, codiceFiscale);
            if (ready == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(ready);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("modifyContacts")){

            try{

                String codiceFiscale = (String) inputFromClient.readUnshared();
                String nome = (String) inputFromClient.readUnshared();
                String cognome = (String) inputFromClient.readUnshared();
                String telefono = (String) inputFromClient.readUnshared();
                responce = rmiServer.modifyContact(codiceFiscale, nome, cognome, telefono);

            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return responce;

        }else if(commandMethod.equals("deleteContacts")){

            try {
                System.out.println("Sto eseguendo da Socket");
                String codiceFiscale = (String) inputFromClient.readUnshared();

                responce = rmiServer.deleteContacts(codiceFiscale);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return responce;

        }


        else if(commandMethod.equals("addDoctor")){

            try {

                String codiceFiscale = (String) inputFromClient.readUnshared();
                String nome = (String) inputFromClient.readUnshared();
                String cognome = (String) inputFromClient.readUnshared();
                LocalDate date = LocalDate.parse((CharSequence) inputFromClient.readUnshared());
                String luogo = (String) inputFromClient.readUnshared();
                String sesso = (String) inputFromClient.readUnshared();

                responce = rmiServer.addDoctor(codiceFiscale, nome, cognome, date, luogo, sesso);
            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return responce;

        }else if(commandMethod.equals("viewDoctor")){

            System.out.println("Carico Dati da Socket");
            ArrayList<DoctorGS> ready = rmiServer.viewDoctors();
            if (ready == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(ready);
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
            ArrayList<DoctorGS> ready = rmiServer.searchDoctors(nome, codiceFiscale);
            if (ready == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(ready);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("modifyDoctor")){

            try{

                String codiceFiscale = (String) inputFromClient.readUnshared();
                String nome = (String) inputFromClient.readUnshared();
                String cognome = (String) inputFromClient.readUnshared();
                String luogo = (String) inputFromClient.readUnshared();
                LocalDate data = LocalDate.parse((CharSequence) inputFromClient.readUnshared());
                responce = rmiServer.modifyDoctor(codiceFiscale, nome, cognome, luogo, data);

            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return responce;

        }else if(commandMethod.equals("deleteDoctor")){

            try {
                System.out.println("Sto eseguendo da Socket");
                String codiceFiscale = (String) inputFromClient.readUnshared();

                Boolean success = rmiServer.deleteDoctors(codiceFiscale);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return responce;
        }


        else if(commandMethod.equals("addMenu")){

            try {
                String nome = (String) inputFromClient.readUnshared();
                String primo = (String) inputFromClient.readUnshared();
                String secondo = (String) inputFromClient.readUnshared();
                LocalDate giorno = LocalDate.parse((CharSequence) inputFromClient.readUnshared());
                String contorno = (String) inputFromClient.readUnshared(); 
                responce = rmiServer.addMenu(nome, primo, secondo, contorno, giorno);

            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return responce;

        }else if(commandMethod.equals("viewFirst")){

            System.out.println("Carico Dati da Socket");
            ArrayList<FirstDishGS> ready = rmiServer.viewFirst();
            if (ready == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(ready);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("viewSecond")){

            System.out.println("Carico Dati da Socket");
            ArrayList<SecondDishGS> ready = rmiServer.viewSecond();
            if (ready == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(ready);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("viewSide")) {

            System.out.println("Carico Dati da Socket");
            ArrayList<SideDishGS> ready = rmiServer.viewSide();
            if (ready == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(ready);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("viewMenu")){

            System.out.println("Carico Dati da Socket");
            ArrayList<MenuGS> ready = rmiServer.viewMenu();
            if (ready == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(ready);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("viewCheck")){

            System.out.println("Cerco dal Socket");
            String primo = null;
            String secondo = null;
            String contorno = null;

            try {
                primo = (String) inputFromClient.readUnshared();
                secondo = (String) inputFromClient.readUnshared();
                contorno = (String) inputFromClient.readUnshared();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ArrayList<BambiniAllergici> ready = rmiServer.viewCheck(primo, secondo, contorno);
            if (ready == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(ready);
                outputToClient.flush();
                responce = true;
            }
            return responce;


        }else if(commandMethod.equals("addPrimo")){

            try {
                String nome = (String) inputFromClient.readUnshared();
                String allergeni = (String) inputFromClient.readUnshared();

                responce = rmiServer.addPrimo(nome, allergeni);

            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return responce;

        }else if(commandMethod.equals("addSecondo")){

            try {
                String nome = (String) inputFromClient.readUnshared();
                String allergeni = (String) inputFromClient.readUnshared();

                responce = rmiServer.addSecondo(nome, allergeni);

            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return responce;

        }else if(commandMethod.equals("addSide")){

            try {
                String nome = (String) inputFromClient.readUnshared();
                String allergeni = (String) inputFromClient.readUnshared();

                responce = rmiServer.addSide(nome, allergeni);

            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return responce;

        }else if(commandMethod.equals("deleteMenu")){

            try {
                System.out.println("Sto eseguendo da Socket");
                String nomeMenu = (String) inputFromClient.readUnshared();

                responce = rmiServer.deleteMenu(nomeMenu);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return responce;

        }


        else if(commandMethod.equals("AddTrip")){

            try {

                String id = (String) inputFromClient.readUnshared();
                String meta = (String) inputFromClient.readUnshared();
                LocalDate andata = LocalDate.parse((CharSequence) inputFromClient.readUnshared());
                LocalDate ritorno = LocalDate.parse((CharSequence) inputFromClient.readUnshared());

                responce = rmiServer.newTrip(id, meta, andata, ritorno);


            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return responce;

        }else if(commandMethod.equals("viewTrip")){

            System.out.println("Carico Dati da Socket");
            ArrayList<TripGS> ready = rmiServer.viewTrip();
            if (ready == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(ready);
                outputToClient.flush();
                responce = true;
            }
            return responce;

        }else if(commandMethod.equals("loadDataServer")){

            String idGita = null;

            try {
                idGita = (String) inputFromClient.readUnshared();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ArrayList<AppealGS> ready = rmiServer.loadDataServer(Integer.parseInt(idGita));
            if (ready == null) {
                outputToClient.writeUnshared(true);
                outputToClient.flush();
                responce = false;
            } else {
                outputToClient.writeUnshared(false);
                outputToClient.flush();
                outputToClient.writeUnshared(ready);
                outputToClient.flush();
                responce = true;
            }
            return responce;


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

            try {

                String tappa = (String) inputFromClient.readUnshared();
                String idGita = (String) inputFromClient.readUnshared();
                LocalDate giorno = LocalDate.parse((CharSequence) inputFromClient.readUnshared());
                String ora = (String) inputFromClient.readUnshared();

                responce = rmiServer.newTappaServer(tappa, idGita, giorno, ora);

            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return responce;

        }else if(commandMethod.equals("newPartecipante")){

            try {

                String codiceFiscale = (String) inputFromClient.readUnshared();
                String idGita = (String) inputFromClient.readUnshared();

                responce = rmiServer.newpartecipanteTrip(codiceFiscale, idGita);

            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return responce;

        }else if(commandMethod.equals("deleteTrip")){

            try {
                System.out.println("Sto eseguendo da Socket");
                String idGita = (String) inputFromClient.readUnshared();

                responce = rmiServer.deleteTrip(idGita);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return responce;

        }else if(commandMethod.equals("pullmanCount")){

            String idGita = (String) inputFromClient.readUnshared();

            rmiServer.pullmanCount(idGita);
            return true;

        }

        return false;
    }
}
