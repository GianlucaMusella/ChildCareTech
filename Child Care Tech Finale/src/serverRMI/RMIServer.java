package serverRMI;

import connectionDatabase.ConnectionDatabase;
import dataEntry.ChildGS;
import dataEntry.Contact;
import dataEntry.Doctor;
import dataEntry.Parents;
import menuFood.AllergyGS;
import menuFood.FirstDishesGS;
import menuFood.SecondDishesGS;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class RMIServer extends UnicastRemoteObject implements InterfaceRMI{

    protected RMIServer() throws RemoteException {
        super();
    }
    private static final long serialVersionUID = 1L;

    @Override
    public boolean login(String username,String password) throws Exception {

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String login = "SELECT Username,Password FROM mydb.personaleconaccesso WHERE Username = " + '"' + username + '"' + "AND Password = " + '"' + password + '"';

        try{
            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(login);
            resultSet = preparedStatement.executeQuery();

            ArrayList<String> utente = new ArrayList<>();

            try {
                if(!resultSet.next()){
                    System.out.println("Utente non registrato nel database");
                    return false;
                }else {

                    resultSet.beforeFirst();
                    while(resultSet.next()){

                        utente.add(resultSet.getString("Username"));
                        System.out.println(resultSet.getString("Username"));
                        System.out.println(resultSet.getString("Password"));
                        if(!utente.isEmpty())

                            return true;

                    }

                }
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                try {
                    if (resultSet != null)
                        resultSet.close();      //chiudo le connessioni al db una volta effettuato il controllo
                }catch (Exception e){
                    e.printStackTrace();
                }
                try {
                    if(preparedStatement != null)
                        preparedStatement.close();      //chiudo le connessioni al db una volta effettuato il controllo
                }catch (Exception e){
                    e.printStackTrace();
                }
            }


        }catch (Exception e){
            System.out.println(e);
        }

        return false;

    }

    @Override
    public boolean addSupplier(String name, String surname, String azienda, String fornitura, String partitaIva) throws Exception {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String supplier = "INSERT INTO mydb.fornitori (Nome,Cognome,Azienda,TipoDiFornitura,PartitaIVA) VALUES (?,?,?,?,?)";

        try{

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(supplier);

            preparedStatement.setString(1,name);
            preparedStatement.setString(2,surname);
            preparedStatement.setString(3,azienda);
            preparedStatement.setString(4,fornitura);
            preparedStatement.setString(5,partitaIva);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {

                if(preparedStatement != null){
                    preparedStatement.close();      //chiudo le connessioni al db una volta effettuato il controllo
                    return true;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean addChild(String codiceFiscale, String idBambino, String nome, String cognome, LocalDate data, String luogo, String allergie, String genitore1, String genitore2, String sesso, String pediatra, String contatto) throws Exception {

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;

        String child = "INSERT INTO mydb.bambini (CodiceFiscale,idBambino,Nome,Cognome,Data_di_Nascita,Luogo_di_Nascita,Allergie,Sesso,Pediatra_CodiceFiscale) VALUES (?,?,?,?,?,?,?,?,?)";
        String parents = "INSERT INTO mydb.bambini_has_genitori (Bambini_idBambino,Genitori_CodiceFiscale) VALUES (?,?)";
        String contact = "INSERT INTO mydb.bambini_has_contatti (Bambini_idBambino,Contatti_CodiceFiscale) VALUES (?,?)";

        try{

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(child);
            preparedStatement1 = connectionDatabase.initializeConnection().prepareStatement(parents);
            preparedStatement2 = connectionDatabase.initializeConnection().prepareStatement(contact);


            preparedStatement.setString(1,codiceFiscale);
            preparedStatement.setString(2,idBambino);
            preparedStatement.setString(3,nome);
            preparedStatement.setString(4, cognome);
            preparedStatement.setDate(5, java.sql.Date.valueOf(data));
            preparedStatement.setString(6, luogo);
            preparedStatement.setString(7, allergie);
            preparedStatement.setString(8, sesso);
            preparedStatement.setString(9, pediatra);
            preparedStatement.executeUpdate();

            preparedStatement1.setString(1, idBambino);
            preparedStatement1.setString(2, genitore1);
            preparedStatement1.executeUpdate();

            preparedStatement2.setString(1, idBambino);
            preparedStatement2.setString(2, contatto);
            preparedStatement2.executeUpdate();



        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {

                if(preparedStatement != null && preparedStatement1 != null && preparedStatement2 != null){
                    preparedStatement.close();
                    preparedStatement1.close(); //chiudo le connessioni al db una volta effettuato il controllo
                    preparedStatement2.close();
                    return true;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean addTeacher(String nome, String cognome, String codiceFiscale, LocalDate data, String luogo, String allergie, String sesso, String insegnante, String username, String password) throws Exception {

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;

        String teacher = "INSERT INTO mydb.personaleinterno (CodiceFiscale,Nome,Cognome,Data_di_Nascita,Allergie,Luogo_di_Nascita,Sesso,Mansione) VALUES (?,?,?,?,?,?,?,?)";
        String teacherCredentials = "INSERT INTO mydb.personaleconaccesso(Username,Password,PersonaleInterno_CodiceFiscale) VALUES (?,?,?)";
        try{

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(teacher);
            preparedStatement1 = connectionDatabase.initializeConnection().prepareStatement(teacherCredentials);


            preparedStatement.setString(1,codiceFiscale);
            preparedStatement.setString(2,nome);
            preparedStatement.setString(3,cognome);
            preparedStatement.setDate(4, java.sql.Date.valueOf(data));
            preparedStatement.setString(5, allergie);
            preparedStatement.setString(6, luogo);
            preparedStatement.setString(7, sesso);
            preparedStatement.setString(8, insegnante);
            preparedStatement.executeUpdate();


            preparedStatement1.setString(1, username);
            preparedStatement1.setString(2, password);
            preparedStatement1.setString(3, codiceFiscale);
            preparedStatement1.executeUpdate();



        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {

                if(preparedStatement != null && preparedStatement1 != null){
                    preparedStatement.close();
                    preparedStatement1.close(); //chiudo le connessioni al db una volta effettuato il controllo
                    return true;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }


        return false;
    }

    @Override
    public boolean addStaff(String nome, String cognome, String codiceFiscale, LocalDate data, String luogo, String allergie, String sesso, String mansione) throws Exception {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String teacher = "INSERT INTO mydb.personaleinterno (CodiceFiscale,Nome,Cognome,Data_di_Nascita,Allergie,Luogo_di_Nascita,Sesso,Mansione) VALUES (?,?,?,?,?,?,?,?)";

        try{

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(teacher);


            preparedStatement.setString(1,codiceFiscale);
            preparedStatement.setString(2,nome);
            preparedStatement.setString(3,cognome);
            preparedStatement.setDate(4, java.sql.Date.valueOf(data));
            preparedStatement.setString(5, allergie);
            preparedStatement.setString(6, luogo);
            preparedStatement.setString(7, sesso);
            preparedStatement.setString(8, mansione);
            preparedStatement.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {

                if(preparedStatement != null){
                    preparedStatement.close();  //chiudo le connessioni al db una volta effettuato il controllo
                    return true;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }


        return false;
    }

    @Override
    public ArrayList<ChildGS> searchC(String name, String surname, String cod) throws Exception {

        ArrayList<ChildGS> values = new ArrayList<>();
        String sql = ("SELECT * FROM mydb.bambini WHERE ");
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();


        if (surname.isEmpty() && cod.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "Nome = '" + name + "'");
            while (rs.next()) {
                String colonnanome = rs.getString("Nome");
                String colonnacognome = rs.getString("Cognome");
                String colonnacodicefiscale = rs.getString("CodiceFiscale");
                String colonnaluogodinascita = rs.getString("Luogo_di_Nascita");
                Date colonnadatadinascita = rs.getDate("Data_di_Nascita");
                String colonnaID = rs.getString("idBambino");


                values.add(new ChildGS(colonnanome, colonnacognome, colonnacodicefiscale, colonnaluogodinascita, colonnadatadinascita, colonnaID));

            }
        } else if (name.isEmpty() && cod.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "Cognome = '" + surname + "'");
            while (rs.next()) {
                String colonnanome = rs.getString("Nome");
                String colonnacognome = rs.getString("Cognome");
                String colonnacodicefiscale = rs.getString("CodiceFiscale");
                String colonnaluogodinascita = rs.getString("Luogo_di_Nascita");
                Date colonnadatadinascita = rs.getDate("Data_di_Nascita");

                String colonnaID = rs.getString("idBambino");


                values.add(new ChildGS(colonnanome, colonnacognome, colonnacodicefiscale, colonnaluogodinascita, colonnadatadinascita, colonnaID));


            }
        } else if (name.isEmpty() && surname.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "CodiceFiscale = '" + cod + "'");
            while (rs.next()) {
                String colonnanome = rs.getString("Nome");
                String colonnacognome = rs.getString("Cognome");
                String colonnacodicefiscale = rs.getString("CodiceFiscale");
                String colonnaluogodinascita = rs.getString("Luogo_di_Nascita");
                Date colonnadatadinascita = rs.getDate("Data_di_Nascita");
                String colonnaID = rs.getString("idBambino");


                values.add(new ChildGS(colonnanome, colonnacognome, colonnacodicefiscale, colonnaluogodinascita, colonnadatadinascita, colonnaID));

            }
        } else if (name.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "CodiceFiscale = '" + cod + "' AND Cognome = '" + surname + "'");
            while (rs.next()) {
                String colonnanome = rs.getString("Nome");
                String colonnacognome = rs.getString("Cognome");
                String colonnacodicefiscale = rs.getString("CodiceFiscale");
                String colonnaluogodinascita = rs.getString("Luogo_di_Nascita");
                Date colonnadatadinascita = rs.getDate("Data_di_Nascita");
                String colonnaID = rs.getString("idBambino");


                values.add(new ChildGS(colonnanome, colonnacognome, colonnacodicefiscale, colonnaluogodinascita, colonnadatadinascita, colonnaID));

            }
        } else if (surname.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "CodiceFiscale = '" + cod + "' AND Nome = '" + name + "'");
            while (rs.next()) {
                String colonnanome = rs.getString("Nome");
                String colonnacognome = rs.getString("Cognome");
                String colonnacodicefiscale = rs.getString("CodiceFiscale");
                String colonnaluogodinascita = rs.getString("Luogo_di_Nascita");
                Date colonnadatadinascita = rs.getDate("Data_di_Nascita");
                String colonnaID = rs.getString("idBambino");


                values.add(new ChildGS(colonnanome, colonnacognome, colonnacodicefiscale, colonnaluogodinascita, colonnadatadinascita, colonnaID));

            }
        } else if (cod.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "Nome = '" + name + "' AND Cognome = '" + surname + "'");
            while (rs.next()) {
                String colonnanome = rs.getString("Nome");
                String colonnacognome = rs.getString("Cognome");
                String colonnacodicefiscale = rs.getString("CodiceFiscale");
                String colonnaluogodinascita = rs.getString("Luogo_di_Nascita");
                Date colonnadatadinascita = rs.getDate("Data_di_Nascita");
                String colonnaID = rs.getString("idBambino");


                values.add(new ChildGS(colonnanome, colonnacognome, colonnacodicefiscale, colonnaluogodinascita, colonnadatadinascita, colonnaID));

            }
            rs.close();
        }

        return values;
    }

    @Override
    public boolean addParents(String codiceFiscale, String nome, String cognome, LocalDate data, String luogo, String telefono, String sesso) throws Exception {

        PreparedStatement preparedStatement = null;

        String parents = "INSERT INTO mydb.genitori (CodiceFiscale,Nome,Cognome,Data_di_Nascita,Luogo_di_Nascita,Telefono,Sesso) VALUES (?,?,?,?,?,?,?)";

        try{

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(parents);


            preparedStatement.setString(1,codiceFiscale);
            preparedStatement.setString(2,nome);
            preparedStatement.setString(3,cognome);
            preparedStatement.setDate(4, java.sql.Date.valueOf(data));
            preparedStatement.setString(5, luogo);
            preparedStatement.setString(6, telefono);
            preparedStatement.setString(7, sesso);
            preparedStatement.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {

                if(preparedStatement != null){
                    preparedStatement.close();  //chiudo le connessioni al db una volta effettuato il controllo
                    return true;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean addContact(String codiceFiscale, String nome, String cognome, String telefono) throws Exception {

        PreparedStatement preparedStatement = null;

        String contact = "INSERT INTO mydb.contatti (CodiceFiscale,Nome,Cognome,Telefono) VALUES (?,?,?,?)";

        try{

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(contact);


            preparedStatement.setString(1,codiceFiscale);
            preparedStatement.setString(2,nome);
            preparedStatement.setString(3,cognome);
            preparedStatement.setString(4,telefono);
            preparedStatement.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {

                if(preparedStatement != null){
                    preparedStatement.close();  //chiudo le connessioni al db una volta effettuato il controllo
                    return true;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public ArrayList<ChildGS> viewChild() throws Exception {

        ArrayList<ChildGS> values = new ArrayList<>();
        String sql = ("SELECT * FROM mydb.bambini ");
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()){
            String colonnanome = rs.getString("Nome");
            String colonnacognome = rs.getString("Cognome");
            String colonnacodicefiscale = rs.getString("CodiceFiscale");
            String colonnaluogodinascita = rs.getString("Luogo_di_Nascita");
            Date colonnadatadinascita = rs.getDate("Data_di_Nascita");
            String colonnaID = rs.getString("idBambino");

            values.add(new ChildGS(colonnanome, colonnacognome, colonnacodicefiscale, colonnaluogodinascita, colonnadatadinascita, colonnaID));
        }
        rs.close();
        return values;
    }

    @Override
    public boolean addDoctor(String codiceFiscale, String nome, String cognome, LocalDate data, String luogo, String sesso) throws Exception {

        PreparedStatement preparedStatement = null;

        String doctor = "INSERT INTO mydb.pediatra (CodiceFiscale,Nome,Cognome,Luogo_di_Nascita, Data_di_Nascita,Sesso) VALUES (?,?,?,?,?,?)";

        try{

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(doctor);


            preparedStatement.setString(1,codiceFiscale);
            preparedStatement.setString(2,nome);
            preparedStatement.setString(3,cognome);
            preparedStatement.setString(4, luogo);
            preparedStatement.setDate(5, java.sql.Date.valueOf(data));
            preparedStatement.setString(6, sesso);
            preparedStatement.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {

                if(preparedStatement != null){
                    preparedStatement.close();  //chiudo le connessioni al db una volta effettuato il controllo
                    return true;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return false;
    }


    @Override
    public ArrayList<Parents> viewParents() throws Exception {

        ArrayList<Parents> values = new ArrayList<>();
        String sql = ("SELECT * FROM mydb.genitori ");
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()){
            String colonnanome = rs.getString("Nome");
            String colonnacodicefiscale = rs.getString("CodiceFiscale");

            values.add(new Parents(colonnanome, colonnacodicefiscale));
        }

        rs.close();
        return values;
    }

    @Override
    public ArrayList<Contact> viewContacts() throws Exception {

        ArrayList<Contact> values = new ArrayList<>();
        String sql = ("SELECT * FROM mydb.contatti ");
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()){
            String colonnanome = rs.getString("Nome");
            String colonnacodicefiscale = rs.getString("CodiceFiscale");

            values.add(new Contact(colonnanome, colonnacodicefiscale));
        }

        rs.close();
        return values;

    }

    @Override
    public ArrayList<Doctor> viewDoctors() throws Exception {

        ArrayList<Doctor> values = new ArrayList<>();
        String sql = ("SELECT * FROM mydb.pediatra ");
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()){
            String colonnanome = rs.getString("Nome");
            String colonnacodicefiscale = rs.getString("CodiceFiscale");

            values.add(new Doctor(colonnanome, colonnacodicefiscale));
        }

        rs.close();
        return values;

    }

    @Override
    public ArrayList<AllergyGS> viewAlletgy() throws Exception {

        ArrayList<AllergyGS> values = new ArrayList<>();
        String sql = ("SELECT * FROM mydb.bambini ");
        String sql1 = ("SELECT * FROM mydb.personaleinterno");

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();
        Statement statement1 = connectionDatabase.initializeConnection().createStatement();


        ResultSet rs = statement.executeQuery(sql);
        ResultSet rs1 = statement1.executeQuery(sql1);

        while (rs.next() && rs1.next()){
            String allergieBambini = rs.getString("Allergie");
            String allergiePersonale = rs1.getString("Allergie");

            values.add(new AllergyGS(allergieBambini, allergiePersonale));
        }

        statement.close();
        statement1.close();

        return values;
    }

    @Override
    public boolean addMenu(String nome, String primo, String secondo) throws Exception {

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;
        PreparedStatement preparedStatement4 = null;
        PreparedStatement preparedStatement5 = null;


        String nomeMenu = "INSERT INTO mydb.menu (Nome) VALUES (?)";
        String primoPiatto = "INSERT INTO mydb.primi (Nome) VALUES (?)";
        String secondoPiatto = "INSERT INTO mydb.secondi (Nome) VALUES (?)";
        /*String tabellaIntermediaPrimi = "INSERT INTO mydb.menu_has_primi (Menu_idMenu, Primi_Nome) (?,?)";
        String tabellaIntermediaSecondi = "INSERT INTO mydb.menu_has_secondi (Menu_idMenu, Secondi_Nome) (?,?)";
        String sql = ("SELECT * FROM mydb.menu ");
*/
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        /*Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(sql);
        int Menu = 0;
        
        while (rs.next()){

            int idMenu = rs.getInt("idMenu");
            Menu = idMenu;
        }
*/

        try{

            //ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(nomeMenu);
            preparedStatement2 = connectionDatabase.initializeConnection().prepareStatement(primoPiatto);
            preparedStatement3 = connectionDatabase.initializeConnection().prepareStatement(secondoPiatto);
            /*preparedStatement4 = connectionDatabase.initializeConnection().prepareStatement(tabellaIntermediaPrimi);
            preparedStatement5 = connectionDatabase.initializeConnection().prepareStatement(tabellaIntermediaSecondi);*/


            preparedStatement.setString(1,nome);
            preparedStatement.executeUpdate();

            preparedStatement2.setString(1,primo);
            preparedStatement2.executeUpdate();

            preparedStatement3.setString(1,secondo);
            preparedStatement3.executeUpdate();

            /*preparedStatement4.setInt(1,Menu);
            preparedStatement4.setString(2,secondo);
            preparedStatement4.executeUpdate();*/


        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {

                if(preparedStatement != null && preparedStatement2 != null && preparedStatement3 != null){
                    preparedStatement.close();  //chiudo le connessioni al db una volta effettuato il controllo
                    preparedStatement2.close();
                    preparedStatement3.close();
                    return true;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return false;

    }

    @Override
    public ArrayList<FirstDishesGS> viewFirst() throws Exception {

        ArrayList<FirstDishesGS> values = new ArrayList<>();
        String sql = ("SELECT * FROM mydb.allergeni_has_primi ");
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()){
            String colonnaPrimi = rs.getString("Allergeni_Nome");
            String colonnaAllergene = rs.getString("Primi_Nome");

            values.add(new FirstDishesGS(colonnaAllergene, colonnaPrimi));
        }

        rs.close();
        return values;

    }

    @Override
    public ArrayList<SecondDishesGS> viewSecond() throws Exception{

        ArrayList<SecondDishesGS> values = new ArrayList<>();
        String sql = ("SELECT * FROM mydb.allergeni_has_secondi ");
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()){
            String colonnaAllergeni = rs.getString("Allergeni_nome");
            String colonnaSecondi = rs.getString("Secondi_Nome");

            values.add(new SecondDishesGS(colonnaSecondi, colonnaAllergeni));
        }

        rs.close();
        return values;

    }

    @Override
    public boolean addPrimo(String nome, String allergeni) throws Exception {
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;


        String primoPiatto = "INSERT INTO mydb.primi (Nome) VALUES (?)";
        String sqlAllergeni = "INSERT INTO mydb.allergeni (Nome) VALUES (?)";
        String primiEAllergeni = "INSERT INTO mydb.allergeni_has_primi (Allergeni_Nome, Primi_Nome) VALUES (?,?)";

        try{

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(primoPiatto);
            preparedStatement1 = connectionDatabase.initializeConnection().prepareStatement(sqlAllergeni);
            preparedStatement2 = connectionDatabase.initializeConnection().prepareStatement(primiEAllergeni);

            preparedStatement.setString(1,nome);
            preparedStatement.executeUpdate();

            preparedStatement1.setString(1,allergeni);
            preparedStatement1.executeUpdate();

            preparedStatement2.setString(1,allergeni);
            preparedStatement2.setString(2,nome);
            preparedStatement2.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {

                if(preparedStatement != null && preparedStatement1 != null && preparedStatement2 != null){
                    preparedStatement.close();  //chiudo le connessioni al db una volta effettuato il controllo
                    preparedStatement1.close();
                    preparedStatement2.close();
                    return true;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return false;

    }

    @Override
    public boolean addSecondo(String nome, String allergeni) throws Exception {
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;

        String secondoPiatto = "INSERT INTO mydb.secondi (Nome) VALUES (?)";
        String sqlAllergeni = "INSERT INTO mydb.allergeni (Nome) VALUES (?)";
        String primiEAllergeni = "INSERT INTO mydb.allergeni_has_secondi (Allergeni_Nome, Secondi_Nome) VALUES (?,?)";

        try{

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(secondoPiatto);
            preparedStatement1 = connectionDatabase.initializeConnection().prepareStatement(sqlAllergeni);
            preparedStatement2 = connectionDatabase.initializeConnection().prepareStatement(primiEAllergeni);

            preparedStatement.setString(1,nome);
            preparedStatement.executeUpdate();

            preparedStatement1.setString(1,allergeni);
            preparedStatement1.executeUpdate();

            preparedStatement2.setString(1,allergeni);
            preparedStatement2.setString(2,nome);
            preparedStatement2.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {

                if(preparedStatement != null && preparedStatement1 != null && preparedStatement2 != null){
                    preparedStatement.close();  //chiudo le connessioni al db una volta effettuato il controllo
                    preparedStatement1.close();
                    preparedStatement2.close();
                    return true;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return false;

    }

    @Override
    public void modifyChild(String CodicefiscaleOld, String CodicefiscaleNew, String Nome, String Cognome, String Luogo, LocalDate data) throws Exception {

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();

        String SQL = ("UPDATE mydb.bambini SET ");
        String equal = ("WHERE CodiceFiscale = '" + CodicefiscaleOld + "'");
        if (!Nome.isEmpty()) {
            int s = stmt.executeUpdate(SQL + "Nome = '" + Nome + "'" + equal);
            // System.out.println(Nome);
        }
        if (!Cognome.isEmpty()) {
            int p = stmt.executeUpdate(SQL + "Cognome = '" + Cognome + "'" + equal);
            // System.out.println(Cognome);
        }
        if (!Luogo.isEmpty()) {
            int x = stmt.executeUpdate(SQL + "Luogo_di_Nascita = '" + Luogo + "'" + equal);
            // System.out.println(Luogo);
        }
        if (data != null ) {
            int z = stmt.executeUpdate(SQL + "Data_di_Nascita = '" + data + "'" + equal);
            // System.out.println(data);
        }
        if (!CodicefiscaleNew.isEmpty()) {
            int n = stmt.executeUpdate(SQL + "CodiceFiscale = '" + CodicefiscaleNew + "'" + equal);
            // System.out.println(CodicefiscaleNew);
        }
    }

    @Override
    public boolean deleteChild(String idBambino) throws Exception {
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();

        PreparedStatement st = null;

        String queryDelete = "DELETE FROM mydb.bambini WHERE idBambino = '" + idBambino + "';";

        try{

            st = connectionDatabase.initializeConnection().prepareStatement(queryDelete);
            st.executeUpdate(queryDelete);
            System.out.println("Deleted from bambini.");


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }


}

