package serverSocket.client;

import getterAndSetter.food.*;
import getterAndSetter.people.*;
import interfaces.InterfaceServer;
import getterAndSetter.trip.AppealGS;
import getterAndSetter.trip.TripGS;

import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SocketUserClient implements InterfaceServer {

    //protected final Socket socket;
    private ObjectOutputStream toServer;
    private ObjectInputStream fromServer;

    public SocketUserClient(Socket s) {

        //this.socket = s;

        try{

            toServer = new ObjectOutputStream(s.getOutputStream());
            toServer.flush();
            fromServer = new ObjectInputStream(s.getInputStream());

        }catch (IOException e){
            System.out.println("Errore di tipo IO nel server");
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }


    @Override
    public boolean login(String username, String password) throws Exception {

        boolean success = false;

        try{

            toServer.writeUnshared("login");
            toServer.flush();
            toServer.writeUnshared(username);
            toServer.flush();
            toServer.writeUnshared(password);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{

            success = (boolean) fromServer.readUnshared();
            System.out.println("Client " + success);

        }catch (IOException e){
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean addSupplier(String name, String surname, String azienda, String fornitura, String partitaIva) throws Exception {

        boolean success = false;

        try{
            toServer.writeUnshared("addSupplier");
            toServer.flush();
            toServer.writeUnshared(name);
            toServer.flush();
            toServer.writeUnshared(surname);
            toServer.flush();
            toServer.writeUnshared(azienda);
            toServer.flush();
            toServer.writeUnshared(fornitura);
            toServer.flush();
            toServer.writeUnshared(partitaIva);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            success = (boolean) fromServer.readUnshared();
        }catch (IOException e){
            e.printStackTrace();
        }

        return success;
    }

    @Override
    public ArrayList<SupplierGS> viewSupplier() throws RemoteException, SQLException {

        boolean ok = false;
        ArrayList<SupplierGS> arrayList = new ArrayList<>();
        Object object = new Object();

        try {
            toServer.writeUnshared("viewSupplier");
            toServer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean isEmpty = false;
        try {
            isEmpty = (boolean) fromServer.readUnshared();
            System.out.println("Is db empty? " + isEmpty);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!isEmpty) {
            try {
                object = fromServer.readUnshared();
                System.out.println("Read array as Object");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            if (object instanceof ArrayList<?>) {
                //get list
                ArrayList<?> loadedAl = (ArrayList<?>) object;
                if (loadedAl.size() > 0) {
                    for (Object element : loadedAl) {
                        if (element instanceof SupplierGS) {
                            SupplierGS myElement = (SupplierGS) element;
                            arrayList.add(myElement);
                        }
                    }
                }
            }

        }
        try {
            ok = (boolean) fromServer.readUnshared ();
            System.out.println("Read reply from server");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println(ok);

        if(ok){
            return arrayList;
        }
        return null;
    }

    @Override
    public ArrayList<SupplierGS> searchSupplier(String azienda, String fornitura, String partitaIva) throws Exception {

        boolean ok = false;
        try{

            toServer.writeUnshared ("searchSupplier");
            toServer.flush();
            toServer.writeUnshared (azienda);
            toServer.flush();
            toServer.writeUnshared (fornitura);
            toServer.flush();
            toServer.writeUnshared (partitaIva);
            toServer.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<SupplierGS> arrayListToReturn = new ArrayList<>();
        boolean isEmpty = false;
        try {
            isEmpty = (boolean) fromServer.readUnshared();
            System.out.println("Is db empty? " +isEmpty);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!isEmpty) {
            try {
                Object loaded = fromServer.readUnshared();
                if (loaded instanceof ArrayList<?>) {
                    //get list
                    ArrayList<?> loadedAl = (ArrayList<?>) loaded;
                    if (loadedAl.size() > 0) {
                        for (Object element : loadedAl) {
                            if (element instanceof SupplierGS) {
                                SupplierGS myElement = (SupplierGS) element;
                                arrayListToReturn.add(myElement);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            ok = (boolean) fromServer.readUnshared ();
            System.out.println("Read reply from server");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println(ok);

        if(ok){
            return arrayListToReturn;
        }
        return null;

    }

    @Override
    public boolean modifySupplier(String azienda, String nome, String cognome, String fornitura, String partitaIva) throws Exception {

        boolean ok = false;
        try{

            toServer.writeUnshared("modifySupplier");
            toServer.flush();
            toServer.writeUnshared(azienda);
            toServer.flush();
            toServer.writeUnshared(nome);
            toServer.flush();
            toServer.writeUnshared(cognome);
            toServer.flush();
            toServer.writeUnshared(fornitura);
            toServer.flush();
            toServer.writeUnshared(partitaIva);
            toServer.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            ok = (boolean) fromServer.readUnshared();
            System.out.println("Read reply from server");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }


    @Override
    public boolean addOrder(String azienda, String ordini, String quantità) throws Exception {

        boolean success = false;

        try{

            toServer.writeUnshared("addOrder");
            toServer.flush();
            toServer.writeUnshared(azienda);
            toServer.flush();
            toServer.writeUnshared(ordini);
            toServer.flush();
            toServer.writeUnshared(quantità);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            success = (boolean) fromServer.readUnshared();
        }catch (IOException e){
            e.printStackTrace();
        }

        return success;

    }

    @Override
    public boolean deleteSupplier(String azienda) throws Exception {

        try{

            toServer.writeUnshared("deleteSupplier");
            toServer.flush();
            toServer.writeUnshared(azienda);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            return (boolean) fromServer.readUnshared();
        }catch (IOException e){
            e.printStackTrace();
        }

        return true;

    }

    @Override
    public boolean addChild(String codiceFiscale, String idBambino, String nome, String cognome, LocalDate data, String luogo, String allergie, String genitore1, String genitore2, String sesso, String pediatra, String Contatto) throws Exception {

        boolean success = false;

        try{

            toServer.writeUnshared("addChild");
            toServer.flush();
            toServer.writeUnshared(codiceFiscale);
            toServer.flush();
            toServer.writeUnshared(idBambino);
            toServer.flush();
            toServer.writeUnshared(nome);
            toServer.flush();
            toServer.writeUnshared(cognome);
            toServer.flush();
            toServer.writeUnshared(String.valueOf(data));
            toServer.flush();
            toServer.writeUnshared(luogo);
            toServer.flush();
            toServer.writeUnshared(allergie);
            toServer.flush();
            toServer.writeUnshared(genitore1);
            toServer.flush();
            toServer.writeUnshared(genitore2);
            toServer.flush();
            toServer.writeUnshared(sesso);
            toServer.flush();
            toServer.writeUnshared(pediatra);
            toServer.flush();
            toServer.writeUnshared(Contatto);
            toServer.flush();


        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            success = (boolean) fromServer.readUnshared();
        }catch (IOException e){
            e.printStackTrace();
        }

        return success;
    }

    @Override
    public ArrayList<ChildGS> searchC(String name, String surname, String cod) throws Exception {

        boolean ok = false;
        try{
            toServer.writeUnshared ("searchChild");
            toServer.flush();
            toServer.writeUnshared (name);
            toServer.flush();
            toServer.writeUnshared(surname);
            toServer.flush();
            toServer.writeUnshared(cod);
            toServer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<ChildGS> arrayListToReturn = new ArrayList<>();
        boolean isEmpty = false;
        try {
            isEmpty = (boolean) fromServer.readUnshared();
            System.out.println("Is db empty? " +isEmpty);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!isEmpty) {
            try {
                Object loaded = fromServer.readUnshared();
                if (loaded instanceof ArrayList<?>) {
                    //get list
                    ArrayList<?> loadedAl = (ArrayList<?>) loaded;
                    if (loadedAl.size() > 0) {
                        for (Object element : loadedAl) {
                            if (element instanceof ChildGS) {
                                ChildGS myElement = (ChildGS) element;
                                arrayListToReturn.add(myElement);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            ok = (boolean) fromServer.readUnshared ();
            System.out.println("Read reply from server");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println(ok);

        if(ok){
            return arrayListToReturn;
        }
        return null;
    }

    @Override
    public ArrayList<ChildGS> viewChild() throws Exception {

        boolean ok = false;
        ArrayList<ChildGS> arrayList = new ArrayList<>();
        Object object = new Object();

        try {
            toServer.writeUnshared("viewChild");
            toServer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean isEmpty = false;
        try {
            isEmpty = (boolean) fromServer.readUnshared();
            System.out.println("Is db empty? " + isEmpty);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!isEmpty) {
            try {
                object = fromServer.readUnshared();
                System.out.println("Read array as Object");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            if (object instanceof ArrayList<?>) {
                //get list
                ArrayList<?> loadedAl = (ArrayList<?>) object;
                if (loadedAl.size() > 0) {
                    for (Object element : loadedAl) {
                        if (element instanceof ChildGS) {
                            ChildGS myElement = (ChildGS) element;
                            arrayList.add(myElement);
                        }
                    }
                }
            }

        }
        try {
            ok = (boolean) fromServer.readUnshared ();
            System.out.println("Read reply from server");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println(ok);

        if(ok){
            return arrayList;
        }
        return null;
    }

    @Override
    public boolean modifyChild(String codiceFiscale, String Nome, String cognome, String luogo, LocalDate data, String idBambino) throws Exception {

        boolean ok = false;
        try{

            toServer.writeUnshared("modifyChild");
            toServer.flush();
            toServer.writeUnshared(codiceFiscale);
            toServer.flush();
            toServer.writeUnshared(Nome);
            toServer.flush();
            toServer.writeUnshared(cognome);
            toServer.flush();
            toServer.writeUnshared(luogo);
            toServer.flush();
            toServer.writeUnshared(String.valueOf(data));
            toServer.flush();
            toServer.writeUnshared(idBambino);
            toServer.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            ok = (boolean) fromServer.readUnshared();
            System.out.println("Read reply from server");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public boolean deleteChild(String codiceFiscale) throws Exception {

        boolean success = false;

        try{

            toServer.writeUnshared("deleteChild");
            toServer.flush();
            toServer.writeUnshared(codiceFiscale);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            success = (boolean) fromServer.readUnshared();
        }catch (IOException e){
            e.printStackTrace();
        }

        return success;

    }

    @Override
    public boolean controlChild(String codiceFiscale) throws Exception {
        boolean success = false;

        try{

            toServer.writeUnshared("controlChild");
            toServer.flush();
            toServer.writeUnshared(codiceFiscale);
            toServer.flush();


        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            success = (boolean) fromServer.readUnshared();
        }catch (Exception e){
            e.printStackTrace();
        }

        return success;

    }

    @Override
    public boolean addTeacher(String nome, String cognome, String codiceFiscale, LocalDate data, String luogo, String allergie, String sesso, String insegnante, String username, String password) throws Exception {

        boolean success = false;

        try{

            toServer.writeUnshared("addTeacher");
            toServer.flush();
            toServer.writeUnshared(nome);
            toServer.flush();
            toServer.writeUnshared(cognome);
            toServer.flush();
            toServer.writeUnshared(codiceFiscale);
            toServer.flush();
            toServer.writeUnshared(String.valueOf(data));
            toServer.flush();
            toServer.writeUnshared(luogo);
            toServer.flush();
            toServer.writeUnshared(allergie);
            toServer.flush();
            toServer.writeUnshared(sesso);
            toServer.flush();
            toServer.writeUnshared(insegnante);
            toServer.flush();
            toServer.writeUnshared(username);
            toServer.flush();
            toServer.writeUnshared(password);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            success = (boolean) fromServer.readUnshared();
        }catch (IOException e){
            e.printStackTrace();
        }

        return success;

    }

    @Override
    public boolean addStaff(String nome, String cognome, String codiceFiscale, LocalDate data, String luogo, String allergie, String sesso, String mansione) throws Exception {

        boolean success = false;

        try{

            toServer.writeUnshared("addTeacher");
            toServer.flush();
            toServer.writeUnshared(nome);
            toServer.flush();
            toServer.writeUnshared(cognome);
            toServer.flush();
            toServer.writeUnshared(String.valueOf(data));
            toServer.flush();
            toServer.writeUnshared(luogo);
            toServer.flush();
            toServer.writeUnshared(allergie);
            toServer.flush();
            toServer.writeUnshared(sesso);
            toServer.flush();
            toServer.writeUnshared(mansione);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            success = (boolean) fromServer.readUnshared();
        }catch (IOException e){
            e.printStackTrace();
        }

        return success;

    }

    @Override
    public ArrayList<StaffGS> viewStaff() throws Exception {

        boolean ok = false;
        ArrayList<StaffGS> arrayList = new ArrayList<>();
        Object object = new Object();

        try {
            toServer.writeUnshared("viewStaff");
            toServer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean isEmpty = false;
        try {
            isEmpty = (boolean) fromServer.readUnshared();
            System.out.println("Is db empty? " + isEmpty);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!isEmpty) {
            try {
                object = fromServer.readUnshared();
                System.out.println("Read array as Object");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            if (object instanceof ArrayList<?>) {
                //get list
                ArrayList<?> loadedAl = (ArrayList<?>) object;
                if (loadedAl.size() > 0) {
                    for (Object element : loadedAl) {
                        if (element instanceof StaffGS) {
                            StaffGS myElement = (StaffGS) element;
                            arrayList.add(myElement);
                        }
                    }
                }
            }

        }
        try {
            ok = (boolean) fromServer.readUnshared ();
            System.out.println("Read reply from server");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println(ok);

        if(ok){
            return arrayList;
        }
        return null;

    }

    @Override
    public ArrayList<StaffGS> searchStaff(String nome, String cognome, String cod) throws Exception {

        boolean ok = false;
        try{
            toServer.writeUnshared ("searchStaff");
            toServer.flush();
            toServer.writeUnshared (nome);
            toServer.flush();
            toServer.writeUnshared(cognome);
            toServer.flush();
            toServer.writeUnshared(cod);
            toServer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<StaffGS> arrayListToReturn = new ArrayList<>();
        boolean isEmpty = false;
        try {
            isEmpty = (boolean) fromServer.readUnshared();
            System.out.println("Is db empty? " +isEmpty);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!isEmpty) {
            try {
                Object loaded = fromServer.readUnshared();
                if (loaded instanceof ArrayList<?>) {
                    //get list
                    ArrayList<?> loadedAl = (ArrayList<?>) loaded;
                    if (loadedAl.size() > 0) {
                        for (Object element : loadedAl) {
                            if (element instanceof StaffGS) {
                                StaffGS myElement = (StaffGS) element;
                                arrayListToReturn.add(myElement);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            ok = (boolean) fromServer.readUnshared ();
            System.out.println("Read reply from server");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println(ok);

        if(ok){
            return arrayListToReturn;
        }
        return null;
    }

    @Override
    public boolean modifyStaff(String codiceFiscale, String nome, String cognome, String luogo, LocalDate data, String mansione) throws Exception {

        boolean ok = false;
        try{

            toServer.writeUnshared("modifyStaff");
            toServer.flush();
            toServer.writeUnshared(codiceFiscale);
            toServer.flush();
            toServer.writeUnshared(nome);
            toServer.flush();
            toServer.writeUnshared(cognome);
            toServer.flush();
            toServer.writeUnshared(luogo);
            toServer.flush();
            toServer.writeUnshared(String.valueOf(data));
            toServer.flush();
            toServer.writeUnshared(mansione);
            toServer.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            ok = (boolean) fromServer.readUnshared();
            System.out.println("Read reply from server");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public boolean deleteStaff(String codiceFiscale) throws Exception {

        try{

            toServer.writeUnshared("deleteStaff");
            toServer.flush();
            toServer.writeUnshared(codiceFiscale);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            return (boolean) fromServer.readUnshared();
        }catch (IOException e){
            e.printStackTrace();
        }

        return true;

    }

    @Override
    public boolean addParents(String codiceFiscale, String nome, String cognome, LocalDate data, String luogo, String telefono, String sesso) throws Exception {

        boolean success = false;

        try{

            toServer.writeUnshared("addParents");
            toServer.flush();
            toServer.writeUnshared(codiceFiscale);
            toServer.flush();
            toServer.writeUnshared(nome);
            toServer.flush();
            toServer.writeUnshared(cognome);
            toServer.flush();
            toServer.writeUnshared(String.valueOf(data));
            toServer.flush();
            toServer.writeUnshared(luogo);
            toServer.flush();
            toServer.writeUnshared(telefono);
            toServer.flush();
            toServer.writeUnshared(sesso);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            success = (boolean) fromServer.readUnshared();
        }catch (IOException e){
            e.printStackTrace();
        }

        return success;

    }

    @Override
    public ArrayList<ParentsGS> viewParents() throws Exception {

        boolean ok = false;
        ArrayList<ParentsGS> arrayList = new ArrayList<>();
        Object object = new Object();

        try {
            toServer.writeUnshared("viewParents");
            toServer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean isEmpty = false;
        try {
            isEmpty = (boolean) fromServer.readUnshared();
            System.out.println("Is db empty? " + isEmpty);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!isEmpty) {
            try {
                object = fromServer.readUnshared();
                System.out.println("Read array as Object");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            if (object instanceof ArrayList<?>) {
                //get list
                ArrayList<?> loadedAl = (ArrayList<?>) object;
                if (loadedAl.size() > 0) {
                    for (Object element : loadedAl) {
                        if (element instanceof ParentsGS) {
                            ParentsGS myElement = (ParentsGS) element;
                            arrayList.add(myElement);
                        }
                    }
                }
            }

        }
        try {
            ok = (boolean) fromServer.readUnshared ();
            System.out.println("Read reply from server");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println(ok);

        if(ok){
            return arrayList;
        }
        return null;
    }

    @Override
    public ArrayList<ParentsGS> searchParents(String name, String cod) throws Exception {

        boolean ok = false;
        try{
            toServer.writeUnshared ("searchParents");
            toServer.flush();
            toServer.writeUnshared (name);
            toServer.flush();
            toServer.writeUnshared(cod);
            toServer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<ParentsGS> arrayListToReturn = new ArrayList<>();
        boolean isEmpty = false;
        try {
            isEmpty = (boolean) fromServer.readUnshared();
            System.out.println("Is db empty? " +isEmpty);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!isEmpty) {
            try {
                Object loaded = fromServer.readUnshared();
                if (loaded instanceof ArrayList<?>) {
                    //get list
                    ArrayList<?> loadedAl = (ArrayList<?>) loaded;
                    if (loadedAl.size() > 0) {
                        for (Object element : loadedAl) {
                            if (element instanceof ParentsGS) {
                                ParentsGS myElement = (ParentsGS) element;
                                arrayListToReturn.add(myElement);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            ok = (boolean) fromServer.readUnshared ();
            System.out.println("Read reply from server");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println(ok);

        if(ok){
            return arrayListToReturn;
        }
        return null;
    }

    @Override
    public boolean modifyParents(String codiceFiscale, String nome, String cognome, String luogo, LocalDate data, String telefono) throws Exception {

        boolean ok = false;
        try{

            toServer.writeUnshared("modifyParents");
            toServer.flush();
            toServer.writeUnshared(codiceFiscale);
            toServer.flush();
            toServer.writeUnshared(nome);
            toServer.flush();
            toServer.writeUnshared(cognome);
            toServer.flush();
            toServer.writeUnshared(luogo);
            toServer.flush();
            toServer.writeUnshared(String.valueOf(data));
            toServer.flush();
            toServer.writeUnshared(telefono);
            toServer.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            ok = (boolean) fromServer.readUnshared();
            System.out.println("Read reply from server");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public boolean deleteParents(String codiceFiscale) throws Exception {

        try{

            toServer.writeUnshared("deleteParents");
            toServer.flush();
            toServer.writeUnshared(codiceFiscale);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            return (boolean) fromServer.readUnshared();
        }catch (IOException e){
            e.printStackTrace();
        }

        return true;

    }

    @Override
    public boolean addContact(String codiceFiscale, String nome, String cognome, String telefono) throws Exception {

        boolean success = false;

        try{

            toServer.writeUnshared("addContacts");
            toServer.flush();
            toServer.writeUnshared(codiceFiscale);
            toServer.flush();
            toServer.writeUnshared(nome);
            toServer.flush();
            toServer.writeUnshared(cognome);
            toServer.flush();
            toServer.writeUnshared(telefono);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            success = (boolean) fromServer.readUnshared();
        }catch (IOException e){
            e.printStackTrace();
        }

        return success;

    }

    @Override
    public ArrayList<ContactGS> viewContacts() throws RemoteException, SQLException {

        boolean ok = false;
        ArrayList<ContactGS> arrayList = new ArrayList<>();
        Object object = new Object();

        try {
            toServer.writeUnshared("viewContacts");
            toServer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean isEmpty = false;
        try {
            isEmpty = (boolean) fromServer.readUnshared();
            System.out.println("Is db empty? " + isEmpty);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!isEmpty) {
            try {
                object = fromServer.readUnshared();
                System.out.println("Read array as Object");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            if (object instanceof ArrayList<?>) {
                //get list
                ArrayList<?> loadedAl = (ArrayList<?>) object;
                if (loadedAl.size() > 0) {
                    for (Object element : loadedAl) {
                        if (element instanceof ContactGS) {
                            ContactGS myElement = (ContactGS) element;
                            arrayList.add(myElement);
                        }
                    }
                }
            }

        }
        try {
            ok = (boolean) fromServer.readUnshared ();
            System.out.println("Read reply from server");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println(ok);

        if(ok){
            return arrayList;
        }
        return null;

    }

    @Override
    public ArrayList<ContactGS> searchContacts(String nome, String cod) throws Exception {

        boolean ok = false;
        try{
            toServer.writeUnshared ("searchContacts");
            toServer.flush();
            toServer.writeUnshared (nome);
            toServer.flush();
            toServer.writeUnshared(cod);
            toServer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<ContactGS> arrayListToReturn = new ArrayList<>();
        boolean isEmpty = false;
        try {
            isEmpty = (boolean) fromServer.readUnshared();
            System.out.println("Is db empty? " +isEmpty);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!isEmpty) {
            try {
                Object loaded = fromServer.readUnshared();
                if (loaded instanceof ArrayList<?>) {
                    //get list
                    ArrayList<?> loadedAl = (ArrayList<?>) loaded;
                    if (loadedAl.size() > 0) {
                        for (Object element : loadedAl) {
                            if (element instanceof ContactGS) {
                                ContactGS myElement = (ContactGS) element;
                                arrayListToReturn.add(myElement);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            ok = (boolean) fromServer.readUnshared ();
            System.out.println("Read reply from server");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println(ok);

        if(ok){
            return arrayListToReturn;
        }
        return null;

    }

    @Override
    public boolean modifyContact(String codiceFiscale, String nome, String cognome, String telefono) throws Exception {
        boolean ok = false;
        try{

            toServer.writeUnshared("modifyContacts");
            toServer.flush();
            toServer.writeUnshared(codiceFiscale);
            toServer.flush();
            toServer.writeUnshared(nome);
            toServer.flush();
            toServer.writeUnshared(cognome);
            toServer.flush();
            toServer.writeUnshared(telefono);
            toServer.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            ok = (boolean) fromServer.readUnshared();
            System.out.println("Read reply from server");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public boolean deleteContacts(String codiceFiscale) throws Exception {

        try{

            toServer.writeUnshared("deleteContacts");
            toServer.flush();
            toServer.writeUnshared(codiceFiscale);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            return (boolean) fromServer.readUnshared();
        }catch (IOException e){
            e.printStackTrace();
        }

        return true;

    }

    @Override
    public boolean addDoctor(String codiceFiscale, String nome, String cognome, LocalDate data, String luogo, String sesso) throws Exception {

        boolean success = false;

        try{

            toServer.writeUnshared("addDoctor");
            toServer.flush();
            toServer.writeUnshared(codiceFiscale);
            toServer.flush();
            toServer.writeUnshared(nome);
            toServer.flush();
            toServer.writeUnshared(cognome);
            toServer.flush();
            toServer.writeUnshared(String.valueOf(data));
            toServer.flush();
            toServer.writeUnshared(luogo);
            toServer.flush();
            toServer.writeUnshared(sesso);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            success = (boolean) fromServer.readUnshared();
        }catch (IOException e){
            e.printStackTrace();
        }

        return success;

    }

    @Override
    public ArrayList<DoctorGS> viewDoctors() throws Exception {

        boolean ok = false;
        ArrayList<DoctorGS> arrayList = new ArrayList<>();
        Object object = new Object();

        try {
            toServer.writeUnshared("viewDoctor");
            toServer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean isEmpty = false;
        try {
            isEmpty = (boolean) fromServer.readUnshared();
            System.out.println("Is db empty? " + isEmpty);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!isEmpty) {
            try {
                object = fromServer.readUnshared();
                System.out.println("Read array as Object");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            if (object instanceof ArrayList<?>) {
                //get list
                ArrayList<?> loadedAl = (ArrayList<?>) object;
                if (loadedAl.size() > 0) {
                    for (Object element : loadedAl) {
                        if (element instanceof DoctorGS) {
                            DoctorGS myElement = (DoctorGS) element;
                            arrayList.add(myElement);
                        }
                    }
                }
            }

        }
        try {
            ok = (boolean) fromServer.readUnshared ();
            System.out.println("Read reply from server");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println(ok);

        if(ok){
            return arrayList;
        }
        return null;

    }

    @Override
    public ArrayList<DoctorGS> searchDoctors(String name, String cod) throws Exception {

        boolean ok = false;
        try{
            toServer.writeUnshared ("searchDoctor");
            toServer.flush();
            toServer.writeUnshared (name);
            toServer.flush();
            toServer.writeUnshared(cod);
            toServer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<DoctorGS> arrayListToReturn = new ArrayList<>();
        boolean isEmpty = false;
        try {
            isEmpty = (boolean) fromServer.readUnshared();
            System.out.println("Is db empty? " +isEmpty);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!isEmpty) {
            try {
                Object loaded = fromServer.readUnshared();
                if (loaded instanceof ArrayList<?>) {
                    //get list
                    ArrayList<?> loadedAl = (ArrayList<?>) loaded;
                    if (loadedAl.size() > 0) {
                        for (Object element : loadedAl) {
                            if (element instanceof DoctorGS) {
                                DoctorGS myElement = (DoctorGS) element;
                                arrayListToReturn.add(myElement);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            ok = (boolean) fromServer.readUnshared ();
            System.out.println("Read reply from server");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println(ok);

        if(ok){
            return arrayListToReturn;
        }
        return null;
    }

    @Override
    public boolean modifyDoctor(String codiceFiscale, String nome, String cognome, String luogo, LocalDate data) throws Exception {

        boolean ok = false;
        try{

            toServer.writeUnshared("modifyDoctor");
            toServer.flush();
            toServer.writeUnshared(codiceFiscale);
            toServer.flush();
            toServer.writeUnshared(nome);
            toServer.flush();
            toServer.writeUnshared(cognome);
            toServer.flush();
            toServer.writeUnshared(luogo);
            toServer.flush();
            toServer.writeUnshared(String.valueOf(data));
            toServer.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            ok = (boolean) fromServer.readUnshared();
            System.out.println("Read reply from server");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public boolean deleteDoctors(String codiceFiscale) throws Exception {

        try{

            toServer.writeUnshared("deleteDoctor");
            toServer.flush();
            toServer.writeUnshared(codiceFiscale);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            return (boolean) fromServer.readUnshared();
        }catch (IOException e){
            e.printStackTrace();
        }

        return true;

    }

    @Override
    public boolean addMenu(String nome, String primo, String secondo, String contorno, LocalDate giorno) throws Exception {

        boolean success = false;

        try{

            toServer.writeUnshared("addMenu");
            toServer.flush();
            toServer.writeUnshared(nome);
            toServer.flush();
            toServer.writeUnshared(primo);
            toServer.flush();
            toServer.writeUnshared(secondo);
            toServer.flush();
            toServer.writeUnshared(String.valueOf(giorno));
            toServer.flush();
            toServer.writeUnshared(contorno);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            success = (boolean) fromServer.readUnshared();
        }catch (IOException e){
            e.printStackTrace();
        }

        return success;

    }

    @Override
    public ArrayList<FirstDishGS> viewFirst() throws Exception {

        boolean ok = false;
        ArrayList<FirstDishGS> arrayList = new ArrayList<>();
        Object object = new Object();

        try {
            toServer.writeUnshared("viewFirst");
            toServer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean isEmpty = false;
        try {
            isEmpty = (boolean) fromServer.readUnshared();
            System.out.println("Is db empty? " + isEmpty);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!isEmpty) {
            try {
                object = fromServer.readUnshared();
                System.out.println("Read array as Object");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            if (object instanceof ArrayList<?>) {
                //get list
                ArrayList<?> loadedAl = (ArrayList<?>) object;
                if (loadedAl.size() > 0) {
                    for (Object element : loadedAl) {
                        if (element instanceof FirstDishGS) {
                            FirstDishGS myElement = (FirstDishGS) element;
                            arrayList.add(myElement);
                        }
                    }
                }
            }

        }
        try {
            ok = (boolean) fromServer.readUnshared ();
            System.out.println("Read reply from server");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println(ok);

        if(ok){
            return arrayList;
        }
        return null;

    }

    @Override
    public ArrayList<SecondDishGS> viewSecond() throws Exception {

        boolean ok = false;
        ArrayList<SecondDishGS> arrayList = new ArrayList<>();
        Object object = new Object();

        try {
            toServer.writeUnshared("viewSecond");
            toServer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean isEmpty = false;
        try {
            isEmpty = (boolean) fromServer.readUnshared();
            System.out.println("Is db empty? " + isEmpty);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!isEmpty) {
            try {
                object = fromServer.readUnshared();
                System.out.println("Read array as Object");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            if (object instanceof ArrayList<?>) {
                //get list
                ArrayList<?> loadedAl = (ArrayList<?>) object;
                if (loadedAl.size() > 0) {
                    for (Object element : loadedAl) {
                        if (element instanceof SecondDishGS) {
                            SecondDishGS myElement = (SecondDishGS) element;
                            arrayList.add(myElement);
                        }
                    }
                }
            }

        }
        try {
            ok = (boolean) fromServer.readUnshared ();
            System.out.println("Read reply from server");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println(ok);

        if(ok){
            return arrayList;
        }
        return null;

    }

    @Override
    public ArrayList<SideDishGS> viewSide() throws Exception {

        boolean ok = false;
        ArrayList<SideDishGS> arrayList = new ArrayList<>();
        Object object = new Object();

        try {
            toServer.writeUnshared("viewSide");
            toServer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean isEmpty = false;
        try {
            isEmpty = (boolean) fromServer.readUnshared();
            System.out.println("Is db empty? " + isEmpty);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!isEmpty) {
            try {
                object = fromServer.readUnshared();
                System.out.println("Read array as Object");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            if (object instanceof ArrayList<?>) {
                //get list
                ArrayList<?> loadedAl = (ArrayList<?>) object;
                if (loadedAl.size() > 0) {
                    for (Object element : loadedAl) {
                        if (element instanceof SideDishGS) {
                            SideDishGS myElement = (SideDishGS) element;
                            arrayList.add(myElement);
                        }
                    }
                }
            }

        }
        try {
            ok = (boolean) fromServer.readUnshared ();
            System.out.println("Read reply from server");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println(ok);

        if(ok){
            return arrayList;
        }
        return null;

    }

    @Override
    public ArrayList<MenuGS> viewMenu() throws Exception {

        boolean ok = false;
        ArrayList<MenuGS> arrayList = new ArrayList<>();
        Object object = new Object();

        try {
            toServer.writeUnshared("viewMenu");
            toServer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean isEmpty = false;
        try {
            isEmpty = (boolean) fromServer.readUnshared();
            System.out.println("Is db empty? " + isEmpty);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!isEmpty) {
            try {
                object = fromServer.readUnshared();
                System.out.println("Read array as Object");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            if (object instanceof ArrayList<?>) {
                //get list
                ArrayList<?> loadedAl = (ArrayList<?>) object;
                if (loadedAl.size() > 0) {
                    for (Object element : loadedAl) {
                        if (element instanceof MenuGS) {
                            MenuGS myElement = (MenuGS) element;
                            arrayList.add(myElement);
                        }
                    }
                }
            }

        }
        try {
            ok = (boolean) fromServer.readUnshared ();
            System.out.println("Read reply from server");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println(ok);

        if(ok){
            return arrayList;
        }
        return null;

    }

    @Override
    public ArrayList<BambiniAllergici> viewCheck(String primo, String secondo, String contorno) throws Exception {

        boolean ok = false;
        try{

            toServer.writeUnshared ("viewCheck");
            toServer.flush();
            toServer.writeUnshared (primo);
            toServer.flush();
            toServer.writeUnshared (secondo);
            toServer.flush();
            toServer.writeUnshared (contorno);
            toServer.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<BambiniAllergici> arrayListToReturn = new ArrayList<>();
        boolean isEmpty = false;
        try {
            isEmpty = (boolean) fromServer.readUnshared();
            System.out.println("Is db empty? " +isEmpty);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!isEmpty) {
            try {
                Object loaded = fromServer.readUnshared();
                if (loaded instanceof ArrayList<?>) {
                    //get list
                    ArrayList<?> loadedAl = (ArrayList<?>) loaded;
                    if (loadedAl.size() > 0) {
                        for (Object element : loadedAl) {
                            if (element instanceof BambiniAllergici) {
                                BambiniAllergici myElement = (BambiniAllergici) element;
                                arrayListToReturn.add(myElement);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            ok = (boolean) fromServer.readUnshared ();
            System.out.println("Read reply from server");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println(ok);

        if(ok){
            return arrayListToReturn;
        }
        return null;

    }

    @Override
    public boolean addPrimo(String nome, String allergeni) throws Exception {

        boolean success = false;

        try{

            toServer.writeUnshared("addPrimo");
            toServer.flush();
            toServer.writeUnshared(nome);
            toServer.flush();
            toServer.writeUnshared(allergeni);
            toServer.flush();


        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            success = (boolean) fromServer.readUnshared();
        }catch (IOException e){
            e.printStackTrace();
        }

        return success;

    }

    @Override
    public boolean addSecondo(String nome, String allergeni) throws Exception {

        boolean success = false;

        try{

            toServer.writeUnshared("addSecondo");
            toServer.flush();
            toServer.writeUnshared(nome);
            toServer.flush();
            toServer.writeUnshared(allergeni);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            success = (boolean) fromServer.readUnshared();
        }catch (IOException e){
            e.printStackTrace();
        }

        return success;

    }

    @Override
    public boolean addSide(String nome, String allergeni) throws Exception {

        boolean success = false;

        try{

            toServer.writeUnshared("addSide");
            toServer.flush();
            toServer.writeUnshared(nome);
            toServer.flush();
            toServer.writeUnshared(allergeni);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            success = (boolean) fromServer.readUnshared();
        }catch (IOException e){
            e.printStackTrace();
        }

        return success;
    }

    @Override
    public boolean deleteMenu(String nomeMenu) throws Exception {

        try{

            toServer.writeUnshared("deleteMenu");
            toServer.flush();
            toServer.writeUnshared(nomeMenu);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            return (boolean) fromServer.readUnshared();
        }catch (IOException e){
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean newTrip(String id, String meta, LocalDate andata, LocalDate ritorno) throws Exception {

        boolean success = false;

        try{

            toServer.writeUnshared("AddTrip");
            toServer.flush();
            toServer.writeUnshared(id);
            toServer.flush();
            toServer.writeUnshared(meta);
            toServer.flush();
            toServer.writeUnshared(String.valueOf(andata));
            toServer.flush();
            toServer.writeUnshared(String.valueOf(ritorno));
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            success = (boolean) fromServer.readUnshared();
        }catch (IOException e){
            e.printStackTrace();
        }

        return success;

    }

    @Override
    public ArrayList<TripGS> viewTrip() throws Exception {

        boolean ok = false;
        ArrayList<TripGS> arrayList = new ArrayList<>();
        Object object = new Object();

        try {
            toServer.writeUnshared("viewTrip");
            toServer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean isEmpty = false;
        try {
            isEmpty = (boolean) fromServer.readUnshared();
            System.out.println("Is db empty? " + isEmpty);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!isEmpty) {
            try {
                object = fromServer.readUnshared();
                System.out.println("Read array as Object");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            if (object instanceof ArrayList<?>) {
                //get list
                ArrayList<?> loadedAl = (ArrayList<?>) object;
                if (loadedAl.size() > 0) {
                    for (Object element : loadedAl) {
                        if (element instanceof TripGS) {
                            TripGS myElement = (TripGS) element;
                            arrayList.add(myElement);
                        }
                    }
                }
            }

        }
        try {
            ok = (boolean) fromServer.readUnshared ();
            System.out.println("Read reply from server");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println(ok);

        if(ok){
            return arrayList;
        }
        return null;
    }

    @Override
    public ArrayList<AppealGS> loadDataServer(int idGita) throws Exception {

        try{

            toServer.writeUnshared("loadDataServer");
            toServer.flush();
            toServer.writeUnshared(String.valueOf(idGita));
            toServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            return (ArrayList<AppealGS>) fromServer.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public void bambinoPresenteServer(String codiceFiscale, int idGita) throws Exception {
        try{

            toServer.writeUnshared("bambinoPresenteServer");
            toServer.flush();
            toServer.writeUnshared(codiceFiscale);
            toServer.flush();
            toServer.writeUnshared(String.valueOf(idGita));
            toServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bambinoAssenteServer(String codiceFiscale, int idGita) throws Exception {

        try{

            toServer.writeUnshared("bambinoAssenteServer");
            toServer.flush();
            toServer.writeUnshared(codiceFiscale);
            toServer.flush();
            toServer.writeUnshared(String.valueOf(idGita));
            toServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean newTappaServer(String tappa, String idGita, LocalDate giorno, String ora) throws Exception {

        boolean success = false;

        try{

            toServer.writeUnshared("newTappa");
            toServer.flush();
            toServer.writeUnshared(tappa);
            toServer.flush();
            toServer.writeUnshared(idGita);
            toServer.flush();
            toServer.writeUnshared(String.valueOf(giorno));
            toServer.flush();
            toServer.writeUnshared(ora);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            success = (boolean) fromServer.readUnshared();
        }catch (IOException e){
            e.printStackTrace();
        }

        return success;

    }

    @Override
    public boolean newpartecipanteTrip(String codiceFiscale, String idGita) throws Exception {

        boolean success = false;

        try{

            toServer.writeUnshared("newPartecipante");
            toServer.flush();
            toServer.writeUnshared(codiceFiscale);
            toServer.flush();
            toServer.writeUnshared(idGita);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            success = (boolean) fromServer.readUnshared();
        }catch (IOException e){
            e.printStackTrace();
        }

        return success;

    }

    @Override
    public boolean deleteTrip(String idGita) throws Exception {

        try{

            toServer.writeUnshared("deleteTrip");
            toServer.flush();
            toServer.writeUnshared(idGita);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            return (boolean) fromServer.readUnshared();
        }catch (IOException e){
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public void pullmanCount(String idGita) throws Exception {
        try{

            toServer.writeUnshared("pullmanCount");
            toServer.flush();
            toServer.writeUnshared(idGita);
            toServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void assegnaPullman(String codiceFiscale, String idGita) throws Exception {
        try{

            toServer.writeUnshared("pullmanAssign");
            toServer.flush();
            toServer.writeUnshared(codiceFiscale);
            toServer.flush();
            toServer.writeUnshared(idGita);
            toServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
