package serverSocket.client;

import getterAndSetter.food.AllergyPeopleGS;
import getterAndSetter.food.FirstDishGS;
import getterAndSetter.food.MenuGS;
import getterAndSetter.food.SecondDishGS;
import getterAndSetter.people.*;
import serverRMI.InterfaceRMI;
import trip.AppealGS;
import trip.TripGS;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;

public class SocketUserClient implements InterfaceRMI{
    
    protected final Socket socket;
    private ObjectOutputStream toServer;
    private ObjectInputStream fromServer;

    public SocketUserClient(Socket s) {

        this.socket = s;

        try{

            toServer = new ObjectOutputStream(s.getOutputStream());
            fromServer = new ObjectInputStream(s.getInputStream());

        }catch (IOException e){
            System.out.println("Errore di tipo IO nel server");
        }
    }


    @Override
    public boolean login(String username, String password) throws Exception {
        System.out.println("Ciao");
        boolean success = false;

        try{

            toServer.writeUTF("login");
            toServer.flush();
            toServer.writeUTF(username);
            toServer.flush();
            toServer.writeUTF(password);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{

            success = fromServer.readBoolean();
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
            toServer.writeUTF("addSupplier");
            toServer.flush();
            toServer.writeUTF(name);
            toServer.flush();
            toServer.writeUTF(surname);
            toServer.flush();
            toServer.writeUTF(azienda);
            toServer.flush();
            toServer.writeUTF(fornitura);
            toServer.flush();
            toServer.writeUTF(partitaIva);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            success = fromServer.readBoolean();
        }catch (IOException e){
            e.printStackTrace();
        }

        return success;
    }

    @Override
    public ArrayList<SupplierGS> viewSupplier() throws Exception {
        return null;
    }

    @Override
    public ArrayList<SupplierGS> searchSupplier(String azienda, String fornitura, String partitaIva) throws Exception {
        return null;
    }

    @Override
    public void modifySupplier(String azienda, String nome, String cognome, String fornitura, String partitaIva) throws Exception {

    }

    @Override
    public boolean addOrder(String azienda, String ordini, String quantità) throws Exception {
        return false;
    }

    @Override
    public boolean deleteSupplier(String azienda) throws Exception {
        return false;
    }

    @Override
    public boolean addChild(String codiceFiscale, String idBambino, String nome, String cognome, LocalDate data, String luogo, String allergie, String genitore1, String genitore2, String sesso, String pediatra, String Contatto) throws Exception {

        boolean success = false;

        try{

            toServer.writeUTF("addChild");
            toServer.flush();
            toServer.writeUTF(codiceFiscale);
            toServer.flush();
            toServer.writeUTF(idBambino);
            toServer.flush();
            toServer.writeUTF(nome);
            toServer.flush();
            toServer.writeUTF(cognome);
            toServer.flush();
            toServer.writeUTF(String.valueOf(data));
            toServer.flush();
            toServer.writeUTF(luogo);
            toServer.flush();
            toServer.writeUTF(allergie);
            toServer.flush();
            toServer.writeUTF(genitore1);
            toServer.flush();
            toServer.writeUTF(genitore2);
            toServer.flush();
            toServer.writeUTF(sesso);
            toServer.flush();
            toServer.writeUTF(pediatra);
            toServer.flush();
            toServer.writeUTF(Contatto);
            toServer.flush();


        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            success = fromServer.readBoolean();
        }catch (IOException e){
            e.printStackTrace();
        }

        return success;
    }

    @Override
    public ArrayList<ChildGS> searchC(String name, String surname, String cod) throws Exception {
        return null;
    }

    @Override
    public ArrayList<ChildGS> viewChild() throws Exception {
        return null;
    }

    @Override
    public void modifyChild(String codiceFiscale, String Nome, String cognome, String luogo, LocalDate data, String idBambino) throws Exception {

    }

    @Override
    public boolean deleteChild(String codiceFiscale) throws Exception {
        return false;
    }

    @Override
    public boolean addTeacher(String nome, String cognome, String codiceFiscale, LocalDate data, String luogo, String allergie, String sesso, String insegnante, String username, String password) throws Exception {

        boolean success = false;

        try{

            toServer.writeUTF("addTeacher");
            toServer.flush();
            toServer.writeUTF(nome);
            toServer.flush();
            toServer.writeUTF(cognome);
            toServer.flush();
            toServer.writeUTF(String.valueOf(data));
            toServer.flush();
            toServer.writeUTF(luogo);
            toServer.flush();
            toServer.writeUTF(allergie);
            toServer.flush();
            toServer.writeUTF(sesso);
            toServer.flush();
            toServer.writeUTF(insegnante);
            toServer.flush();
            toServer.writeUTF(username);
            toServer.flush();
            toServer.writeUTF(password);

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            success = fromServer.readBoolean();
        }catch (IOException e){
            e.printStackTrace();
        }

        return success;

    }

    @Override
    public boolean addStaff(String nome, String cognome, String codiceFiscale, LocalDate data, String luogo, String allergie, String sesso, String mansione) throws Exception {

        boolean success = false;

        try{

            toServer.writeUTF("addTeacher");
            toServer.flush();
            toServer.writeUTF(nome);
            toServer.flush();
            toServer.writeUTF(cognome);
            toServer.flush();
            toServer.writeUTF(String.valueOf(data));
            toServer.flush();
            toServer.writeUTF(luogo);
            toServer.flush();
            toServer.writeUTF(allergie);
            toServer.flush();
            toServer.writeUTF(sesso);
            toServer.flush();
            toServer.writeUTF(mansione);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            success = fromServer.readBoolean();
        }catch (IOException e){
            e.printStackTrace();
        }

        return success;

    }

    @Override
    public ArrayList<StaffGS> viewStaff() throws Exception {
        return null;
    }

    @Override
    public ArrayList<StaffGS> searchStaff(String nome, String cognome, String cod) throws Exception {
        return null;
    }

    @Override
    public void modifyStaff(String codiceFiscale, String nome, String cognome, String luogo, LocalDate data, String mansione) throws Exception {

    }

    @Override
    public boolean deleteStaff(String codiceFiscale) throws Exception {
        return false;
    }

    @Override
    public boolean addParents(String codiceFiscale, String nome, String cognome, LocalDate data, String luogo, String telefono, String sesso) throws Exception {
        return false;
    }

    @Override
    public ArrayList<ParentsGS> viewParents() throws Exception {
        return null;
    }

    @Override
    public ArrayList<ParentsGS> searchParents(String name, String cod) throws Exception {
        return null;
    }

    @Override
    public void modifyParents(String codiceFiscale, String nome, String cognome, String luogo, LocalDate data, String telefono) throws Exception {

    }

    @Override
    public boolean deleteParents(String codiceFiscale) throws Exception {
        return false;
    }

    @Override
    public boolean addContact(String codiceFiscale, String nome, String cognome, String telefono) throws Exception {
        return false;
    }

    @Override
    public ArrayList<ContactGS> viewContacts() throws Exception {
        return null;
    }

    @Override
    public ArrayList<ContactGS> searchContacts(String nome, String cod) throws Exception {
        return null;
    }

    @Override
    public void modifyContact(String codiceFiscale, String nome, String cognome, String telefono) throws Exception {

    }

    @Override
    public boolean deleteContacts(String codiceFiscale) throws Exception {
        return false;
    }

    @Override
    public boolean addDoctor(String codiceFiscale, String nome, String cognome, LocalDate data, String luogo, String sesso) throws Exception {
        return false;
    }

    @Override
    public ArrayList<DoctorGS> viewDoctors() throws Exception {
        return null;
    }

    @Override
    public ArrayList<DoctorGS> searchDoctors(String name, String cod) throws Exception {
        return null;
    }

    @Override
    public void modifyDoctor(String codiceFiscale, String nome, String cognome, String luogo, LocalDate data) throws Exception {

    }

    @Override
    public boolean deleteDoctors(String codiceFiscale) throws Exception {
        return false;
    }

    @Override
    public boolean addMenu(String nome, String primo, String secondo, LocalDate giorno) throws Exception {
        return false;
    }

    @Override
    public ArrayList<FirstDishGS> viewFirst() throws Exception {
        return null;
    }

    @Override
    public ArrayList<SecondDishGS> viewSecond() throws Exception {
        return null;
    }

    @Override
    public ArrayList<AllergyPeopleGS> viewAllergy() throws Exception {
        return null;
    }

    @Override
    public ArrayList<MenuGS> viewMenu() throws Exception {
        return null;
    }

    @Override
    public boolean addPrimo(String nome, String allergeni) throws Exception {
        return false;
    }

    @Override
    public boolean addSecondo(String nome, String allergeni) throws Exception {
        return false;
    }

    @Override
    public boolean deleteMenu(String nomeMenu) throws Exception {
        return false;
    }

    @Override
    public boolean newTrip(String id, String meta, LocalDate andata, LocalDate ritorno) throws Exception {
        return false;
    }

    @Override
    public ArrayList<TripGS> viewTrip() throws Exception {
        return null;
    }

    @Override
    public ArrayList<AppealGS> loadDataServer(int idGita) throws Exception {
        return null;
    }

    @Override
    public void bambinoPresenteServer(String codiceFiscale, int idGita) throws Exception {

    }

    @Override
    public void bambinoAssenteServer(String codiceFiscale, int idGita) throws Exception {

    }

    @Override
    public boolean newTappaServer(String numeroTappa, String tappa, String idGita, LocalDate giorno, String ora) throws Exception {
        return false;
    }

    @Override
    public boolean newpartecipanteTrip(String codiceFiscale, String idGita) throws Exception {
        return false;
    }

    @Override
    public boolean deleteTrip(String idGita) throws Exception {
        return false;
    }

    @Override
    public void pullmanCount(String idGita) throws Exception {

    }
}
