package interfaces;

import getterAndSetter.food.*;
import getterAndSetter.people.*;
import getterAndSetter.trip.AppealGS;
import getterAndSetter.trip.TripGS;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface InterfaceServer extends Remote{

    boolean login(String username, String password) throws Exception;

    boolean addSupplier(String name, String surname, String azienda, String fornitura, String partitaIva) throws Exception;
    ArrayList<SupplierGS> viewSupplier() throws Exception;
    ArrayList<SupplierGS> searchSupplier(String azienda, String fornitura, String partitaIva) throws Exception;
    boolean modifySupplier(String azienda, String nome, String cognome, String fornitura, String partitaIva) throws Exception;
    boolean addOrder(String azienda, String ordini, String quantità) throws Exception;
    boolean deleteSupplier(String azienda) throws Exception;
    boolean controlSupplier(String azienda) throws Exception;

    boolean addChild(String codiceFiscale, String idBambino, String nome, String cognome, LocalDate data, String luogo, String allergie, String genitore1, String genitore2, String sesso, String pediatra, String Contatto) throws Exception;
    ArrayList<ChildGS> searchC(String name, String surname, String cod) throws Exception;
    ArrayList<ChildGS> viewChild() throws Exception;
    boolean modifyChild(String codiceFiscale, String Nome, String cognome, String luogo, LocalDate data, String idBambino) throws Exception;
    boolean deleteChild (String codiceFiscale) throws Exception;
    boolean controlChild(String codiceFiscale) throws Exception;
    boolean controlID(String idBambino) throws Exception;


    boolean addTeacher(String nome, String cognome, String codiceFiscale, LocalDate data, String luogo, String allergie, String sesso, String insegnante, String username, String password) throws Exception;
    boolean addStaff(String nome, String cognome, String codiceFiscale, LocalDate data, String luogo, String allergie, String sesso, String mansione) throws Exception;
    ArrayList<StaffGS> viewStaff() throws Exception;
    ArrayList<StaffGS> searchStaff(String nome, String cognome, String cod) throws Exception;
    boolean modifyStaff(String codiceFiscale, String nome, String cognome, String luogo, LocalDate data, String mansione) throws Exception;
    boolean deleteStaff(String codiceFiscale) throws Exception;
    boolean controlStaff(String codiceFiscale) throws Exception;

    boolean addParents(String codiceFiscale, String nome, String cognome, LocalDate data, String luogo, String telefono, String sesso) throws Exception;
    ArrayList<ParentsGS> viewParents() throws Exception;
    ArrayList<ParentsGS> searchParents(String name, String cod) throws Exception;
    boolean modifyParents(String codiceFiscale, String nome, String cognome, String luogo, LocalDate data, String telefono) throws Exception;
    boolean deleteParents(String codiceFiscale) throws Exception;
    boolean controlParents(String codiceFiscale) throws Exception;

    boolean addContact(String codiceFiscale, String nome, String cognome, String telefono) throws Exception;
    ArrayList<ContactGS> viewContacts() throws Exception;
    ArrayList<ContactGS> searchContacts(String nome, String cod) throws Exception;
    boolean modifyContact(String codiceFiscale, String nome, String cognome, String telefono) throws Exception;
    boolean deleteContacts(String codiceFiscale) throws Exception;
    boolean controlContact(String codiceFiscale) throws Exception;

    boolean addDoctor(String codiceFiscale, String nome, String cognome, LocalDate data, String luogo, String sesso) throws Exception;
    ArrayList<DoctorGS> viewDoctors() throws Exception;
    ArrayList<DoctorGS> searchDoctors(String name, String cod) throws Exception;
    boolean modifyDoctor(String codiceFiscale, String nome, String cognome, String luogo, LocalDate data) throws Exception;
    boolean deleteDoctors(String codiceFiscale) throws Exception;
    boolean controlDoctor(String codiceFiscale) throws Exception;

    boolean addMenu(String nome, String primo, String secondo, String contorno, LocalDate giorno) throws Exception;
    ArrayList<FirstDishGS> viewFirst() throws Exception;
    ArrayList<SecondDishGS> viewSecond() throws Exception;
    ArrayList<SideDishGS> viewSide() throws Exception;
    ArrayList<MenuGS> viewMenu() throws Exception;
    ArrayList<BambiniAllergici> viewCheck(String primo, String secondo, String contorno) throws Exception;
    boolean addPrimo(String nome, String allergeni) throws Exception;
    boolean addSecondo(String nome, String allergeni) throws Exception;
    boolean addAllergy(String allergeni) throws Exception;
    boolean addSide(String nome, String allergeni) throws Exception;
    boolean deleteMenu(String nomeMenu) throws Exception;
    boolean controlAllergy(String allergeni) throws Exception;


    boolean newTrip(String id, String meta, LocalDate andata, LocalDate ritorno) throws Exception;
    ArrayList<TripGS> viewTrip() throws Exception;
    ArrayList<AppealGS> loadDataServer(int idGita) throws Exception;
    void bambinoPresenteServer(String codiceFiscale, int idGita, int pullman) throws Exception;
    void bambinoAssenteServer(String codiceFiscale, int idGita) throws Exception;
    boolean newTappaServer(String tappa, String idGita, LocalDate giorno, String ora) throws Exception;
    boolean newpartecipanteTrip(String codiceFiscale, String idGita) throws Exception;
    boolean deleteTrip(String idGita) throws Exception;
    void pullmanCount(String idGita) throws Exception;
    void assegnaPullman(String codiceFiscale, String idGita) throws Exception;
    void assenzaAll(int idGita) throws Exception;
    boolean controlGita(String id) throws Exception;


}