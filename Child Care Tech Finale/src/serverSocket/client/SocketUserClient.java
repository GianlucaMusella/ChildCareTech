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
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


public class SocketUserClient implements InterfaceRMI {

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
    public ArrayList<SupplierGS> viewSupplier() throws RemoteException, SQLException {

        try{

            toServer.writeUTF("viewSupplier");
            toServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            return (ArrayList<SupplierGS>) fromServer.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public ArrayList<SupplierGS> searchSupplier(String azienda, String fornitura, String partitaIva) throws Exception {

        try{

            toServer.writeUTF("searchSupplier");
            toServer.flush();
            toServer.writeUTF(String.valueOf(azienda));
            toServer.flush();
            toServer.writeUTF(String.valueOf(fornitura));
            toServer.flush();
            toServer.writeUTF(String.valueOf(partitaIva));
            toServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            return (ArrayList<SupplierGS>) fromServer.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public void modifySupplier(String azienda, String nome, String cognome, String fornitura, String partitaIva) throws Exception {

    }

    @Override
    public boolean addOrder(String azienda, String ordini, String quantità) throws Exception {

        boolean success = false;

        try{

            toServer.writeUTF("addOrder");
            toServer.flush();
            toServer.writeUTF(azienda);
            toServer.flush();
            toServer.writeUTF(ordini);
            toServer.flush();
            toServer.writeUTF(quantità);
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
    public boolean deleteSupplier(String azienda) throws Exception {

        try{

            toServer.writeUTF("deleteSupplier");
            toServer.flush();
            toServer.writeUTF(azienda);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            return fromServer.readBoolean();
        }catch (IOException e){
            e.printStackTrace();
        }

        return true;

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

        try{

            toServer.writeUTF("searchChild");
            toServer.flush();
            toServer.writeUTF(String.valueOf(name));
            toServer.flush();
            toServer.writeUTF(String.valueOf(surname));
            toServer.flush();
            toServer.writeUTF(String.valueOf(cod));
            toServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            return (ArrayList<ChildGS>) fromServer.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public ArrayList<ChildGS> viewChild() throws Exception {

        try{

            toServer.writeUTF("viewChild");
            toServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            return (ArrayList<ChildGS>) fromServer.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void modifyChild(String codiceFiscale, String Nome, String cognome, String luogo, LocalDate data, String idBambino) throws Exception {

    }

    @Override
    public boolean deleteChild(String codiceFiscale) throws Exception {

        boolean success = false;

        try{

            toServer.writeUTF("deleteChild");
            toServer.flush();
            toServer.writeUTF(codiceFiscale);
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

        try{

            toServer.writeUTF("viewStaff");
            toServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            return (ArrayList<StaffGS>) fromServer.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public ArrayList<StaffGS> searchStaff(String nome, String cognome, String cod) throws Exception {

        try{

            toServer.writeUTF("searchStaff");
            toServer.flush();
            toServer.writeUTF(String.valueOf(nome));
            toServer.flush();
            toServer.writeUTF(String.valueOf(cognome));
            toServer.flush();
            toServer.writeUTF(String.valueOf(cod));
            toServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            return (ArrayList<StaffGS>) fromServer.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public void modifyStaff(String codiceFiscale, String nome, String cognome, String luogo, LocalDate data, String mansione) throws Exception {

    }

    @Override
    public boolean deleteStaff(String codiceFiscale) throws Exception {

        try{

            toServer.writeUTF("deleteStaff");
            toServer.flush();
            toServer.writeUTF(codiceFiscale);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            return fromServer.readBoolean();
        }catch (IOException e){
            e.printStackTrace();
        }

        return true;

    }

    @Override
    public boolean addParents(String codiceFiscale, String nome, String cognome, LocalDate data, String luogo, String telefono, String sesso) throws Exception {

        boolean success = false;

        try{

            toServer.writeUTF("addParents");
            toServer.flush();
            toServer.writeUTF(codiceFiscale);
            toServer.flush();
            toServer.writeUTF(nome);
            toServer.flush();
            toServer.writeUTF(cognome);
            toServer.flush();
            toServer.writeUTF(String.valueOf(data));
            toServer.flush();
            toServer.writeUTF(luogo);
            toServer.flush();
            toServer.writeUTF(telefono);
            toServer.flush();
            toServer.writeUTF(sesso);
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
    public ArrayList<ParentsGS> viewParents() throws Exception {

        try{

            toServer.writeUTF("viewParents");
            toServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            return (ArrayList<ParentsGS>) fromServer.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public ArrayList<ParentsGS> searchParents(String name, String cod) throws Exception {

        try{

            toServer.writeUTF("searchParents");
            toServer.flush();
            toServer.writeUTF(String.valueOf(name));
            toServer.flush();
            toServer.writeUTF(String.valueOf(cod));
            toServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            return (ArrayList<ParentsGS>) fromServer.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public void modifyParents(String codiceFiscale, String nome, String cognome, String luogo, LocalDate data, String telefono) throws Exception {

    }

    @Override
    public boolean deleteParents(String codiceFiscale) throws Exception {

        try{

            toServer.writeUTF("deleteParents");
            toServer.flush();
            toServer.writeUTF(codiceFiscale);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            return fromServer.readBoolean();
        }catch (IOException e){
            e.printStackTrace();
        }

        return true;

    }

    @Override
    public boolean addContact(String codiceFiscale, String nome, String cognome, String telefono) throws Exception {

        boolean success = false;

        try{

            toServer.writeUTF("addContact");
            toServer.flush();
            toServer.writeUTF(codiceFiscale);
            toServer.flush();
            toServer.writeUTF(nome);
            toServer.flush();
            toServer.writeUTF(cognome);
            toServer.flush();
            toServer.writeUTF(telefono);
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
    public ArrayList<ContactGS> viewContacts() throws RemoteException, SQLException {

        try{

            toServer.writeUTF("viewContacts");
            toServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            return (ArrayList<ContactGS>) fromServer.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public ArrayList<ContactGS> searchContacts(String nome, String cod) throws Exception {

        try{

            toServer.writeUTF("searchContacts");
            toServer.flush();
            toServer.writeUTF(String.valueOf(nome));
            toServer.flush();
            toServer.writeUTF(String.valueOf(cod));
            toServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            return (ArrayList<ContactGS>) fromServer.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;


    }

    @Override
    public void modifyContact(String codiceFiscale, String nome, String cognome, String telefono) throws Exception {

    }

    @Override
    public boolean deleteContacts(String codiceFiscale) throws Exception {

        try{

            toServer.writeUTF("deleteContacts");
            toServer.flush();
            toServer.writeUTF(codiceFiscale);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            return fromServer.readBoolean();
        }catch (IOException e){
            e.printStackTrace();
        }

        return true;

    }

    @Override
    public boolean addDoctor(String codiceFiscale, String nome, String cognome, LocalDate data, String luogo, String sesso) throws Exception {

        boolean success = false;

        try{

            toServer.writeUTF("addDoctor");
            toServer.flush();
            toServer.writeUTF(codiceFiscale);
            toServer.flush();
            toServer.writeUTF(nome);
            toServer.flush();
            toServer.writeUTF(cognome);
            toServer.flush();
            toServer.writeUTF(String.valueOf(data));
            toServer.flush();
            toServer.writeUTF(luogo);
            toServer.flush();
            toServer.writeUTF(sesso);
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
    public ArrayList<DoctorGS> viewDoctors() throws Exception {

        try{

            toServer.writeUTF("viewDoctor");
            toServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<DoctorGS> response = null;
        try{
            response = (ArrayList<DoctorGS>) fromServer.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return response;

    }

    @Override
    public ArrayList<DoctorGS> searchDoctors(String name, String cod) throws Exception {

        try{

            toServer.writeUTF("searchDoctor");
            toServer.flush();
            toServer.writeUTF(String.valueOf(name));
            toServer.flush();
            toServer.writeUTF(String.valueOf(cod));
            toServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            return (ArrayList<DoctorGS>) fromServer.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;


    }

    @Override
    public void modifyDoctor(String codiceFiscale, String nome, String cognome, String luogo, LocalDate data) throws Exception {

    }

    @Override
    public boolean deleteDoctors(String codiceFiscale) throws Exception {

        try{

            toServer.writeUTF("deleteDoctor");
            toServer.flush();
            toServer.writeUTF(codiceFiscale);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            return fromServer.readBoolean();
        }catch (IOException e){
            e.printStackTrace();
        }

        return true;

    }

    @Override
    public boolean addMenu(String nome, String primo, String secondo, LocalDate giorno) throws Exception {

        boolean success = false;

        try{

            toServer.writeUTF("addMenu");
            toServer.flush();
            toServer.writeUTF(nome);
            toServer.flush();
            toServer.writeUTF(primo);
            toServer.flush();
            toServer.writeUTF(secondo);
            toServer.flush();
            toServer.writeUTF(String.valueOf(giorno));
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
    public ArrayList<FirstDishGS> viewFirst() throws Exception {

        try{

            toServer.writeUTF("viewFirst");
            toServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            return (ArrayList<FirstDishGS>) fromServer.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;    }

    @Override
    public ArrayList<SecondDishGS> viewSecond() throws Exception {

        try{

            toServer.writeUTF("viewSecond");
            toServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            return (ArrayList<SecondDishGS>) fromServer.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public ArrayList<AllergyPeopleGS> viewAllergy() throws Exception {

        try{

            toServer.writeUTF("viewAllergy");
            toServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            return (ArrayList<AllergyPeopleGS>) fromServer.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public ArrayList<MenuGS> viewMenu() throws Exception {

        try{

            toServer.writeUTF("viewMenu");
            toServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            return (ArrayList<MenuGS>) fromServer.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public boolean addPrimo(String nome, String allergeni) throws Exception {

        boolean success = false;

        try{

            toServer.writeUTF("addPrimo");
            toServer.flush();
            toServer.writeUTF(nome);
            toServer.flush();
            toServer.writeUTF(allergeni);
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
    public boolean addSecondo(String nome, String allergeni) throws Exception {

        boolean success = false;

        try{

            toServer.writeUTF("addSecondo");
            toServer.flush();
            toServer.writeUTF(nome);
            toServer.flush();
            toServer.writeUTF(allergeni);
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
    public boolean deleteMenu(String nomeMenu) throws Exception {

        try{

            toServer.writeUTF("deleteMenu");
            toServer.flush();
            toServer.writeUTF(nomeMenu);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            return fromServer.readBoolean();
        }catch (IOException e){
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean newTrip(String id, String meta, LocalDate andata, LocalDate ritorno) throws Exception {

        boolean success = false;

        try{

            toServer.writeUTF("newTrip");
            toServer.flush();
            toServer.writeUTF(id);
            toServer.flush();
            toServer.writeUTF(meta);
            toServer.flush();
            toServer.writeUTF(String.valueOf(andata));
            toServer.flush();
            toServer.writeUTF(String.valueOf(ritorno));
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
    public ArrayList<TripGS> viewTrip() throws Exception {

        try{

            toServer.writeUTF("viewTrip");
            toServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            return (ArrayList<TripGS>) fromServer.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public ArrayList<AppealGS> loadDataServer(int idGita) throws Exception {

        try{

            toServer.writeUTF("loadDataServer");
            toServer.flush();
            toServer.writeUTF(String.valueOf(idGita));
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

    }

    @Override
    public void bambinoAssenteServer(String codiceFiscale, int idGita) throws Exception {

    }

    @Override
    public boolean newTappaServer(String numeroTappa, String tappa, String idGita, LocalDate giorno, String ora) throws Exception {

        boolean success = false;

        try{

            toServer.writeUTF("newTappa");
            toServer.flush();
            toServer.writeUTF(numeroTappa);
            toServer.flush();
            toServer.writeUTF(tappa);
            toServer.flush();
            toServer.writeUTF(idGita);
            toServer.flush();
            toServer.writeUTF(String.valueOf(giorno));
            toServer.flush();
            toServer.writeUTF(ora);
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
    public boolean newpartecipanteTrip(String codiceFiscale, String idGita) throws Exception {

        boolean success = false;

        try{

            toServer.writeUTF("newPartecipante");
            toServer.flush();
            toServer.writeUTF(codiceFiscale);
            toServer.flush();
            toServer.writeUTF(idGita);
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
    public boolean deleteTrip(String idGita) throws Exception {

        try{

            toServer.writeUTF("deleteTrip");
            toServer.flush();
            toServer.writeUTF(idGita);
            toServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            return fromServer.readBoolean();
        }catch (IOException e){
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public void pullmanCount(String idGita) throws Exception {

    }
}
