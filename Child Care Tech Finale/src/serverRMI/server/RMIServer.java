package serverRMI.server;

import connectionDatabase.ConnectionDatabase;
import getterAndSetter.food.*;
import getterAndSetter.people.*;
import getterAndSetter.trip.AppealGS;
import getterAndSetter.trip.TripGS;
import interfaces.InterfaceServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class RMIServer extends UnicastRemoteObject implements InterfaceServer {

    public RMIServer() throws RemoteException {
        super();
    }

    private static final long serialVersionUID = 1L;

    @Override
    public boolean login(String username, String password) throws Exception {

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String login = "SELECT Username,Password FROM mydb.personaleconaccesso WHERE Username = " + '"' + username + '"' + "AND Password = " + '"' + password + '"';

        try {
            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(login);
            resultSet = preparedStatement.executeQuery();

            ArrayList<String> utente = new ArrayList<>();

            try {
                if (!resultSet.next()) {
                    System.out.println("Utente non registrato nel database");
                    return false;
                } else {

                    resultSet.beforeFirst();
                    while (resultSet.next()) {

                        utente.add(resultSet.getString("Username"));
                        System.out.println(resultSet.getString("Username"));
                        System.out.println(resultSet.getString("Password"));
                        if (!utente.isEmpty())

                            return true;

                    }

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (resultSet != null)
                        resultSet.close();      //chiudo le connessioni al db una volta effettuato il controllo
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (preparedStatement != null)
                        preparedStatement.close();      //chiudo le connessioni al db una volta effettuato il controllo
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    @Override
    public boolean addSupplier(String name, String surname, String azienda, String fornitura, String partitaIva) throws Exception {
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        PreparedStatement preparedStatement = null;

        String supplier = "INSERT INTO mydb.fornitori (Nome,Cognome,Azienda,TipoDiFornitura,PartitaIVA) VALUES (?,?,?,?,?)";

        try {

            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(supplier);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, azienda);
            preparedStatement.setString(4, fornitura);
            preparedStatement.setString(5, partitaIva);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                if (preparedStatement != null) {
                    preparedStatement.close();      //chiudo le connessioni al db una volta effettuato il controllo
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public ArrayList<SupplierGS> viewSupplier() throws Exception {

        ArrayList<SupplierGS> values = new ArrayList<>();
        String sql = ("SELECT * FROM mydb.fornitori ");
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            String colonnaAzienda = rs.getString("Azienda");
            String colonnaPartita = rs.getString("PartitaIVA");
            String colonnaFornitura = rs.getString("TipoDiFornitura");


            values.add(new SupplierGS(colonnaAzienda, colonnaPartita, colonnaFornitura));
        }
        rs.close();
        return values;
    }

    @Override
    public ArrayList<SupplierGS> searchSupplier(String azienda, String fornitura, String partitaIva) throws Exception {

        ArrayList<SupplierGS> values = new ArrayList<>();
        String sql = ("SELECT * FROM mydb.fornitori WHERE ");
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();


        if (fornitura.isEmpty() && partitaIva.isEmpty()) {

            ResultSet rs = stmt.executeQuery(sql + "Azienda = '" + azienda + "'");
            while (rs.next()) {
                String colonnaAzienda = rs.getString("Azienda");
                String colonnaFornitura = rs.getString("TipoDiFornitura");
                String colonnaPartitaIva = rs.getString("PartitaIVA");

                values.add(new SupplierGS(colonnaAzienda, colonnaFornitura, colonnaPartitaIva));

            }
        } else if (azienda.isEmpty() && partitaIva.isEmpty()) {

            ResultSet rs = stmt.executeQuery(sql + "TipoDiFornitura = '" + fornitura + "'");
            while (rs.next()) {
                String colonnaAzienda = rs.getString("Azienda");
                String colonnaFornitura = rs.getString("TipoDiFornitura");
                String colonnaPartitaIva = rs.getString("PartitaIVA");

                values.add(new SupplierGS(colonnaAzienda, colonnaFornitura, colonnaPartitaIva));

            }
        } else if (azienda.isEmpty() && fornitura.isEmpty()) {

            ResultSet rs = stmt.executeQuery(sql + "PartitaIVA = '" + partitaIva + "'");
            while (rs.next()) {
                String colonnaAzienda = rs.getString("Azienda");
                String colonnaFornitura = rs.getString("TipoDiFornitura");
                String colonnaPartitaIva = rs.getString("PartitaIVA");

                values.add(new SupplierGS(colonnaAzienda, colonnaFornitura, colonnaPartitaIva));

            }
        } else if (azienda.isEmpty()) {

            ResultSet rs = stmt.executeQuery(sql + "TipoDiFornitura = '" + fornitura + "' AND PartitaIVA = '" + partitaIva + "'");
            while (rs.next()) {
                String colonnaAzienda = rs.getString("Azienda");
                String colonnaFornitura = rs.getString("TipoDiFornitura");
                String colonnaPartitaIva = rs.getString("PartitaIVA");

                values.add(new SupplierGS(colonnaAzienda, colonnaFornitura, colonnaPartitaIva));

            }
        } else if (fornitura.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "PartitaIVA = '" + partitaIva + "' AND Azienda = '" + azienda + "'");
            while (rs.next()) {
                String colonnaAzienda = rs.getString("Azienda");
                String colonnaFornitura = rs.getString("TipoDiFornitura");
                String colonnaPartitaIva = rs.getString("PartitaIVA");

                values.add(new SupplierGS(colonnaAzienda, colonnaFornitura, colonnaPartitaIva));

            }
        } else if (partitaIva.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "Azienda = '" + azienda + "' AND TipoDiFornitura = '" + fornitura + "'");
            while (rs.next()) {
                String colonnaAzienda = rs.getString("Azienda");
                String colonnaFornitura = rs.getString("TipoDiFornitura");
                String colonnaPartitaIva = rs.getString("PartitaIVA");

                values.add(new SupplierGS(colonnaAzienda, colonnaFornitura, colonnaPartitaIva));

            }
            rs.close();

        }

        return values;
    }

    @Override
    public boolean modifySupplier(String azienda, String nome, String cognome, String fornitura, String partitaIva) throws Exception {

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();

        PreparedStatement preparedStatement = null;
        String query = ("UPDATE mydb.fornitori SET Nome ='" + nome + "', Cognome ='" + cognome + "', TipoDiFornitura ='" + fornitura + "', " +
                "PartitaIVA ='" + partitaIva + "'" + "WHERE Azienda = '" + azienda + "'");

        try {

            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(query);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    @Override
    public boolean addOrder(String azienda, String ordini, String quantità) throws Exception {

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();

        String order = "INSERT INTO mydb.ordine (Nome, Quantità) VALUES (?,?)";
        String fornitura = "INSERT INTO mydb.fornitori_has_ordine (Fornitori_Azienda, Ordine_Nome) VALUES (?,?)";

        try {

            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(order);
            preparedStatement1 = connectionDatabase.initializeConnection().prepareStatement(fornitura);

            preparedStatement.setString(1, ordini);
            preparedStatement.setInt(2, Integer.parseInt(quantità));
            preparedStatement.executeUpdate();

            preparedStatement1.setString(1, azienda);
            preparedStatement1.setString(2, ordini);
            preparedStatement1.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                if (preparedStatement != null && preparedStatement1 != null) {
                    preparedStatement.close();      //chiudo le connessioni al db una volta effettuato il controllo
                    preparedStatement1.close();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean deleteSupplier(String azienda) throws Exception {

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();

        PreparedStatement st = null;

        String queryDelete = "DELETE FROM mydb.fornitori WHERE Azienda = '" + azienda + "';";

        try {

            st = connectionDatabase.initializeConnection().prepareStatement(queryDelete);
            st.executeUpdate(queryDelete);
            System.out.println("Deleted from Fornitori.");

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

    @Override
    public boolean controlSupplier(String azienda) throws Exception {
        String control = "SELECT * FROM mydb.fornitori WHERE CodiceFiscale = '"+ azienda+"'";

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(control);

        if(!rs.next())
            return true;
        else
            return false;
    }

    @Override
    public boolean addChild(String codiceFiscale, String idBambino, String nome, String cognome, LocalDate data, String luogo, String allergie, String genitore1, String genitore2, String sesso, String pediatra, String contatto) throws Exception {

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();

        String child = "INSERT INTO mydb.bambini (idBambino,CodiceFiscale,Nome,Cognome,Data_di_Nascita,Luogo_di_Nascita,Allergie,Sesso,Pediatra_CodiceFiscale) VALUES (?,?,?,?,?,?,?,?,?)";
        String parents = "INSERT INTO mydb.bambini_has_genitori (Bambini_CodiceFiscale,Genitori_CodiceFiscale) VALUES (?,?)";
        String contact = "INSERT INTO mydb.bambini_has_contatti (Bambini_CodiceFiscale,Contatti_CodiceFiscale) VALUES (?,?)";

        try {


            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(child);
            preparedStatement1 = connectionDatabase.initializeConnection().prepareStatement(parents);
            preparedStatement2 = connectionDatabase.initializeConnection().prepareStatement(contact);

            preparedStatement.setInt(1, Integer.parseInt(idBambino));
            preparedStatement.setString(2, codiceFiscale);
            preparedStatement.setString(3, nome);
            preparedStatement.setString(4, cognome);
            preparedStatement.setDate(5, Date.valueOf(data));
            preparedStatement.setString(6, luogo);
            preparedStatement.setString(7, allergie);
            preparedStatement.setString(8, sesso);
            preparedStatement.setString(9, pediatra);
            System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();

            preparedStatement1.setString(1, codiceFiscale);
            preparedStatement1.setString(2, genitore1);
            preparedStatement1.executeUpdate();

            preparedStatement2.setString(1, codiceFiscale);
            preparedStatement2.setString(2, contatto);
            preparedStatement2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                if (preparedStatement != null && preparedStatement1 != null && preparedStatement2 != null) {
                    preparedStatement.close();
                    preparedStatement1.close(); //chiudo le connessioni al db una volta effettuato il controllo
                    preparedStatement2.close();
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    @Override
    public boolean addTeacher(String nome, String cognome, String codiceFiscale, LocalDate data, String luogo, String allergie, String sesso, String insegnante, String username, String password) throws Exception {

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();

        String teacher = "INSERT INTO mydb.personaleinterno (CodiceFiscale,Nome,Cognome,Data_di_Nascita,Allergie,Luogo_di_Nascita,Sesso,Mansione) VALUES (?,?,?,?,?,?,?,?)";
        String teacherCredentials = "INSERT INTO mydb.personaleconaccesso(Username,Password,PersonaleInterno_CodiceFiscale) VALUES (?,?,?)";
        try {


            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(teacher);
            preparedStatement1 = connectionDatabase.initializeConnection().prepareStatement(teacherCredentials);

            preparedStatement.setString(1, codiceFiscale);
            preparedStatement.setString(2, nome);
            preparedStatement.setString(3, cognome);
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


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                if (preparedStatement != null && preparedStatement1 != null) {
                    preparedStatement.close();
                    preparedStatement1.close(); //chiudo le connessioni al db una volta effettuato il controllo
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean addStaff(String nome, String cognome, String codiceFiscale, LocalDate data, String luogo, String allergie, String sesso, String mansione) throws Exception {

        PreparedStatement preparedStatement = null;
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();

        String teacher = "INSERT INTO mydb.personaleinterno (CodiceFiscale,Nome,Cognome,Data_di_Nascita,Allergie,Luogo_di_Nascita,Sesso,Mansione) VALUES (?,?,?,?,?,?,?,?)";

        try {


            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(teacher);

            preparedStatement.setString(1, codiceFiscale);
            preparedStatement.setString(2, nome);
            preparedStatement.setString(3, cognome);
            preparedStatement.setDate(4, java.sql.Date.valueOf(data));
            preparedStatement.setString(5, allergie);
            preparedStatement.setString(6, luogo);
            preparedStatement.setString(7, sesso);
            preparedStatement.setString(8, mansione);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                if (preparedStatement != null) {
                    preparedStatement.close();  //chiudo le connessioni al db una volta effettuato il controllo

                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return false;
    }

    @Override
    public ArrayList<StaffGS> viewStaff() throws Exception {

        ArrayList<StaffGS> values = new ArrayList<>();
        String sql = ("SELECT * FROM mydb.personaleinterno ");
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            String colonnaNome = rs.getString("Nome");
            String colonnaCognome = rs.getString("Cognome");
            String colonnaCodiceFiscale = rs.getString("CodiceFiscale");
            String colonnaMansione = rs.getString("Mansione");

            values.add(new StaffGS(colonnaNome, colonnaCognome, colonnaCodiceFiscale, colonnaMansione));
        }

        rs.close();
        return values;
    }

    @Override
    public ArrayList<StaffGS> searchStaff(String nome, String cognome, String cod) throws Exception {

        ArrayList<StaffGS> values = new ArrayList<>();
        String sql = ("SELECT * FROM mydb.personaleinterno WHERE ");
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();


        if (cognome.isEmpty() && cod.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "Nome = '" + nome + "'");
            while (rs.next()) {
                String colonnanome = rs.getString("Nome");
                String colonnacognome = rs.getString("Cognome");
                String colonnacodicefiscale = rs.getString("CodiceFiscale");
                String colonnaMansione = rs.getString("Mansione");

                values.add(new StaffGS(colonnanome, colonnacognome, colonnacodicefiscale, colonnaMansione));

            }
        } else if (nome.isEmpty() && cod.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "Cognome = '" + cognome + "'");
            while (rs.next()) {
                String colonnanome = rs.getString("Nome");
                String colonnacognome = rs.getString("Cognome");
                String colonnacodicefiscale = rs.getString("CodiceFiscale");
                String colonnaMansione = rs.getString("Mansione");

                values.add(new StaffGS(colonnanome, colonnacognome, colonnacodicefiscale, colonnaMansione));

            }
        } else if (nome.isEmpty() && cognome.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "CodiceFiscale = '" + cod + "'");
            while (rs.next()) {
                String colonnanome = rs.getString("Nome");
                String colonnacognome = rs.getString("Cognome");
                String colonnacodicefiscale = rs.getString("CodiceFiscale");
                String colonnaMansione = rs.getString("Mansione");

                values.add(new StaffGS(colonnanome, colonnacognome, colonnacodicefiscale, colonnaMansione));

            }
        } else if (nome.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "CodiceFiscale = '" + cod + "' AND Cognome = '" + cognome + "'");
            while (rs.next()) {
                String colonnanome = rs.getString("Nome");
                String colonnacognome = rs.getString("Cognome");
                String colonnacodicefiscale = rs.getString("CodiceFiscale");
                String colonnaMansione = rs.getString("Mansione");

                values.add(new StaffGS(colonnanome, colonnacognome, colonnacodicefiscale, colonnaMansione));

            }
        } else if (cognome.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "CodiceFiscale = '" + cod + "' AND Nome = '" + nome + "'");
            while (rs.next()) {
                String colonnanome = rs.getString("Nome");
                String colonnacognome = rs.getString("Cognome");
                String colonnacodicefiscale = rs.getString("CodiceFiscale");
                String colonnaMansione = rs.getString("Mansione");

                values.add(new StaffGS(colonnanome, colonnacognome, colonnacodicefiscale, colonnaMansione));

            }
        } else if (cod.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "Nome = '" + nome + "' AND Cognome = '" + cognome + "'");
            while (rs.next()) {
                String colonnanome = rs.getString("Nome");
                String colonnacognome = rs.getString("Cognome");
                String colonnacodicefiscale = rs.getString("CodiceFiscale");
                String colonnaMansione = rs.getString("Mansione");

                values.add(new StaffGS(colonnanome, colonnacognome, colonnacodicefiscale, colonnaMansione));

            }
            rs.close();

        }

        return values;
    }

    @Override
    public boolean modifyStaff(String codiceFiscale, String nome, String cognome, String luogo, LocalDate data, String mansione) throws Exception {

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();

        PreparedStatement preparedStatement = null;
        String query = ("UPDATE mydb.personaleinterno SET Nome ='" + nome + "', Cognome ='" + cognome + "', Data_di_Nascita ='" + Date.valueOf(data) + "', " +
                "Luogo_di_Nascita ='" + luogo + "', Mansione='" + mansione + "'" + "WHERE CodiceFiscale = '" + codiceFiscale + "'");

        try {

            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(query);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    @Override
    public boolean deleteStaff(String codiceFiscale) throws Exception {
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();

        PreparedStatement st = null;

        String queryDelete = "DELETE FROM mydb.personaleinterno WHERE CodiceFiscale = '" + codiceFiscale + "';";

        try {

            st = connectionDatabase.initializeConnection().prepareStatement(queryDelete);
            st.executeUpdate(queryDelete);
            System.out.println("Deleted from personale interno.");

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

    @Override
    public boolean controlStaff(String codiceFiscale) throws Exception {
        String control = "SELECT * FROM mydb.personaleinterno WHERE CodiceFiscale = '"+ codiceFiscale+"'";

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(control);

        if(!rs.next())
            return true;
        else
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

                values.add(new ChildGS(colonnanome, colonnacognome, colonnacodicefiscale, colonnaID));
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

                values.add(new ChildGS(colonnanome, colonnacognome, colonnacodicefiscale, colonnaID));

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

                values.add(new ChildGS(colonnanome, colonnacognome, colonnacodicefiscale, colonnaID));
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

                values.add(new ChildGS(colonnanome, colonnacognome, colonnacodicefiscale, colonnaID));
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

                values.add(new ChildGS(colonnanome, colonnacognome, colonnacodicefiscale, colonnaID));
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

                values.add(new ChildGS(colonnanome, colonnacognome, colonnacodicefiscale, colonnaID));
            }
            rs.close();
        }

        return values;
    }

    @Override
    public boolean addParents(String codiceFiscale, String nome, String cognome, LocalDate data, String luogo, String telefono, String sesso) throws Exception {

        PreparedStatement preparedStatement = null;

        String parents = "INSERT INTO mydb.genitori (CodiceFiscale,Nome,Cognome,Data_di_Nascita,Luogo_di_Nascita,Telefono,Sesso) VALUES (?,?,?,?,?,?,?)";

        try {

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(parents);


            preparedStatement.setString(1, codiceFiscale);
            preparedStatement.setString(2, nome);
            preparedStatement.setString(3, cognome);
            preparedStatement.setDate(4, java.sql.Date.valueOf(data));
            preparedStatement.setString(5, luogo);
            preparedStatement.setString(6, telefono);
            preparedStatement.setString(7, sesso);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                if (preparedStatement != null) {
                    preparedStatement.close();  //chiudo le connessioni al db una volta effettuato il controllo
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean addContact(String codiceFiscale, String nome, String cognome, String telefono) throws Exception {

        PreparedStatement preparedStatement = null;

        String contact = "INSERT INTO mydb.contatti (CodiceFiscale,Nome,Cognome,Telefono) VALUES (?,?,?,?)";

        try {

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(contact);


            preparedStatement.setString(1, codiceFiscale);
            preparedStatement.setString(2, nome);
            preparedStatement.setString(3, cognome);
            preparedStatement.setString(4, telefono);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                if (preparedStatement != null) {
                    preparedStatement.close();  //chiudo le connessioni al db una volta effettuato il controllo
                    return true;
                }
            } catch (Exception e) {
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

        while (rs.next()) {
            String colonnanome = rs.getString("Nome");
            String colonnacognome = rs.getString("Cognome");
            String colonnacodicefiscale = rs.getString("CodiceFiscale");
            /*String colonnaluogodinascita = rs.getString("Luogo_di_Nascita");
            Date colonnadatadinascita = rs.getDate("Data_di_Nascita");*/
            String colonnaID = rs.getString("idBambino");

            values.add(new ChildGS(colonnanome, colonnacognome, colonnacodicefiscale, colonnaID));
        }
        rs.close();
        return values;
    }

    @Override
    public boolean modifyChild(String codiceFiscale, String Nome, String cognome, String luogo, LocalDate data, String idBambino) throws Exception {

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();

        PreparedStatement preparedStatement = null;
        String query = ("UPDATE mydb.bambini SET idBambino ='" + idBambino + "', Nome ='" + Nome + "', Cognome ='" + cognome + "', " +
                "Data_di_Nascita ='" + Date.valueOf(data) + "', Luogo_di_Nascita='" + luogo + "'" + "WHERE CodiceFiscale = '" + codiceFiscale + "'");

        try {

            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(query);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    @Override
    public boolean deleteChild(String codiceFiscale) throws Exception {
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();

        PreparedStatement st = null;

        String queryDelete = "DELETE FROM mydb.bambini WHERE CodiceFiscale = '" + codiceFiscale + "';";

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

    @Override
    public boolean controlChild(String codiceFiscale) throws Exception {

        String control = "SELECT * FROM mydb.bambini WHERE CodiceFiscale = '"+ codiceFiscale+"'";

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(control);

        if(!rs.next())
            return true;
        else
            return false;

    }

    @Override
    public boolean controlID(String idBambino) throws Exception {

        String control = "SELECT * FROM mydb.bambini WHERE idBambino = '"+ Integer.parseInt(idBambino)+"'";

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(control);

        if(!rs.next())
            return true;
        else
            return false;

    }

    @Override
    public boolean addDoctor(String codiceFiscale, String nome, String cognome, LocalDate data, String luogo, String sesso) throws Exception {

        PreparedStatement preparedStatement = null;

        String doctor = "INSERT INTO mydb.pediatra (CodiceFiscale,Nome,Cognome,Luogo_di_Nascita, Data_di_Nascita,Sesso) VALUES (?,?,?,?,?,?)";

        try {

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(doctor);

            preparedStatement.setString(1, codiceFiscale);
            preparedStatement.setString(2, nome);
            preparedStatement.setString(3, cognome);
            preparedStatement.setString(4, luogo);
            preparedStatement.setDate(5, java.sql.Date.valueOf(data));
            preparedStatement.setString(6, sesso);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                if (preparedStatement != null) {
                    preparedStatement.close();  //chiudo le connessioni al db una volta effettuato il controllo
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }


    @Override
    public ArrayList<ParentsGS> viewParents() throws Exception {

        ArrayList<ParentsGS> values = new ArrayList<>();
        String sql = ("SELECT * FROM mydb.genitori ");
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            String colonnaNome = rs.getString("Nome");
            String colonnaCognome = rs.getString("Cognome");
            String colonnaCodiceFiscale = rs.getString("CodiceFiscale");

            values.add(new ParentsGS(colonnaNome, colonnaCognome, colonnaCodiceFiscale));
        }

        rs.close();
        return values;
    }

    @Override
    public ArrayList<ParentsGS> searchParents(String name, String cod) throws Exception {

        ArrayList<ParentsGS> values = new ArrayList<>();
        String sql = ("SELECT * FROM mydb.genitori WHERE ");
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();

        if (cod.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "Nome = '" + name + "'");
            while (rs.next()) {
                String colonnaNome = rs.getString("Nome");
                String colonnaCognome = rs.getString("Cognome");
                String colonnaCodiceFiscale = rs.getString("CodiceFiscale");

                values.add(new ParentsGS(colonnaNome, colonnaCognome, colonnaCodiceFiscale));

            }
        } else if (name.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "CodiceFiscale = '" + cod + "'");
            while (rs.next()) {
                String colonnaNome = rs.getString("Nome");
                String colonnaCognome = rs.getString("Cognome");
                String colonnaCodiceFiscale = rs.getString("CodiceFiscale");

                values.add(new ParentsGS(colonnaNome, colonnaCognome, colonnaCodiceFiscale));

            }

            rs.close();
        }

        return values;
    }

    @Override
    public boolean modifyParents(String codiceFiscale, String nome, String cognome, String luogo, LocalDate data, String telefono) throws Exception {

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();

        PreparedStatement preparedStatement = null;
        String query = ("UPDATE mydb.genitori SET Nome ='" + nome + "', Cognome ='" + cognome + "', Data_di_Nascita ='" + Date.valueOf(data) + "', " +
                "Luogo_di_Nascita ='" + luogo + "', Telefono='" + telefono + "'" + "WHERE CodiceFiscale = '" + codiceFiscale + "'");

        try {

            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(query);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;

    }

    @Override
    public boolean deleteParents(String codiceFiscale) throws Exception {

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();

        PreparedStatement st = null;

        String queryDelete = "DELETE FROM mydb.genitori WHERE CodiceFiscale = '" + codiceFiscale + "';";

        try {

            st = connectionDatabase.initializeConnection().prepareStatement(queryDelete);
            st.executeUpdate(queryDelete);
            System.out.println("Deleted from Genitori.");


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

    @Override
    public boolean controlParents(String codiceFiscale) throws Exception {
        String control = "SELECT * FROM mydb.genitori WHERE CodiceFiscale = '"+ codiceFiscale+"'";

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(control);

        if(!rs.next())
            return true;
        else
            return false;
    }

    @Override
    public ArrayList<ContactGS> viewContacts() throws Exception {

        ArrayList<ContactGS> values = new ArrayList<>();
        String sql = ("SELECT * FROM mydb.contatti ");
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            String colonnaNome = rs.getString("Nome");
            String colonnaCognome = rs.getString("Cognome");
            String colonnaCodiceFiscale = rs.getString("CodiceFiscale");

            values.add(new ContactGS(colonnaNome, colonnaCognome, colonnaCodiceFiscale));
        }

        rs.close();
        return values;

    }

    @Override
    public ArrayList<ContactGS> searchContacts(String nome, String cod) throws Exception {

        ArrayList<ContactGS> values = new ArrayList<>();
        String sql = ("SELECT * FROM mydb.contatti WHERE ");
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();

        if (cod.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "Nome = '" + nome + "'");
            while (rs.next()) {
                String colonnaNome = rs.getString("Nome");
                String colonnaCognome = rs.getString("Cognome");
                String colonnaCodiceFiscale = rs.getString("CodiceFiscale");

                values.add(new ContactGS(colonnaNome, colonnaCognome, colonnaCodiceFiscale));

            }
        } else if (nome.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "CodiceFiscale = '" + cod + "'");
            while (rs.next()) {
                String colonnaNome = rs.getString("Nome");
                String colonnaCognome = rs.getString("Cognome");
                String colonnaCodiceFiscale = rs.getString("CodiceFiscale");

                values.add(new ContactGS(colonnaNome, colonnaCognome, colonnaCodiceFiscale));

            }

            rs.close();
        }

        return values;

    }

    @Override
    public boolean modifyContact(String codiceFiscale, String nome, String cognome, String telefono) throws Exception {
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();

        PreparedStatement preparedStatement = null;
        String query = ("UPDATE mydb.contatti SET Nome ='" + nome + "', Cognome ='" + cognome + "', telefono ='" + telefono + "'" + "WHERE CodiceFiscale = '" + codiceFiscale + "'");

        try {

            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(query);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    @Override
    public boolean deleteContacts(String codiceFiscale) throws Exception {
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();

        PreparedStatement st = null;

        String queryDelete = "DELETE FROM mydb.contatti WHERE CodiceFiscale = '" + codiceFiscale + "';";

        try {

            st = connectionDatabase.initializeConnection().prepareStatement(queryDelete);
            st.executeUpdate(queryDelete);
            System.out.println("Deleted from Contatti.");


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

    @Override
    public boolean controlContact(String codiceFiscale) throws Exception {
        String control = "SELECT * FROM mydb.contatti WHERE CodiceFiscale = '"+ codiceFiscale+"'";

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(control);

        if(!rs.next())
            return true;
        else
            return false;
    }

    @Override
    public ArrayList<DoctorGS> viewDoctors() throws Exception {

        ArrayList<DoctorGS> values = new ArrayList<>();
        String sql = ("SELECT * FROM mydb.pediatra ");
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            String colonnanome = rs.getString("Nome");
            String colonnacognome = rs.getString("Cognome");
            String colonnacodicefiscale = rs.getString("CodiceFiscale");

            values.add(new DoctorGS(colonnanome, colonnacognome, colonnacodicefiscale));
        }

        rs.close();
        return values;

    }

    @Override
    public ArrayList<DoctorGS> searchDoctors(String name, String cod) throws Exception {

        ArrayList<DoctorGS> values = new ArrayList<>();
        String sql = ("SELECT * FROM mydb.pediatra WHERE ");
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();

        if (cod.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "Nome = '" + name + "'");
            while (rs.next()) {
                String colonnanome = rs.getString("Nome");
                String colonnacognome = rs.getString("Cognome");
                String colonnacodicefiscale = rs.getString("CodiceFiscale");

                values.add(new DoctorGS(colonnanome, colonnacognome, colonnacodicefiscale));

            }
        } else if (name.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "CodiceFiscale = '" + cod + "'");
            while (rs.next()) {
                String colonnanome = rs.getString("Nome");
                String colonnacognome = rs.getString("Cognome");
                String colonnacodicefiscale = rs.getString("CodiceFiscale");

                values.add(new DoctorGS(colonnanome, colonnacognome, colonnacodicefiscale));

            }

            rs.close();
        }

        return values;

    }

    @Override
    public boolean modifyDoctor(String codiceFiscale, String nome, String cognome, String luogo, LocalDate data) throws Exception {

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();

        PreparedStatement preparedStatement = null;
        String query = ("UPDATE mydb.pediatra SET Nome ='" + nome + "', Cognome ='" + cognome + "', Luogo_di_Nascita ='" + luogo + "', " +
                "Data_di_Nascita ='" + Date.valueOf(data) + "'" + "WHERE CodiceFiscale = '" + codiceFiscale + "'");

        try {

            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(query);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    @Override
    public boolean deleteDoctors(String codiceFiscale) throws Exception {

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();

        PreparedStatement st = null;

        String queryDelete = "DELETE FROM mydb.pediatra WHERE CodiceFiscale = '" + codiceFiscale + "';";

        try {

            st = connectionDatabase.initializeConnection().prepareStatement(queryDelete);
            st.executeUpdate(queryDelete);
            System.out.println("Deleted from Pediatra.");


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

    @Override
    public boolean controlDoctor(String codiceFiscale) throws Exception {
        String control = "SELECT * FROM mydb.pediatra WHERE CodiceFiscale = '"+ codiceFiscale+"'";

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(control);

        if(!rs.next())
            return true;
        else
            return false;
    }

    @Override
    public ArrayList<MenuGS> viewMenu() throws Exception {

        ArrayList<MenuGS> values = new ArrayList<>();
        String sql = ("SELECT * FROM mydb.menumensa ");
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            String colonnaNome = rs.getString("Nome");
            String colonnaPrimo = rs.getString("Primi_Nome");
            String colonnaSecondi = rs.getString("Secondi_Nome");
            Date colonnaGiorno = rs.getDate("Giorno");

            values.add(new MenuGS(colonnaNome, colonnaPrimo, colonnaSecondi, colonnaGiorno));
        }
        rs.close();
        return values;
    }

    @Override
    public ArrayList<BambiniAllergici> viewCheck(String primo, String secondo, String contorno) throws Exception {
        ArrayList<BambiniAllergici> values = new ArrayList<>();
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();
        if (!primo.isEmpty()) {
            String SQLChild = ("SELECT  mydb.bambini.Nome, mydb.bambini.Cognome, mydb.Allergeni.Nome, mydb.Primi.Nome " +
                    "FROM ((( mydb.primi " +
                    "INNER JOIN mydb.allergeni_has_primi ON mydb.allergeni_has_primi.Primi_Nome = mydb.primi.nome) " +
                    "INNER JOIN mydb.allergeni ON mydb.allergeni_has_primi.Allergeni_Nome = mydb.allergeni.Nome) " +
                    "INNER JOIN mydb.bambini ON mydb.bambini.Allergie = mydb.allergeni.Nome) " +
                    "WHERE mydb.primi.Nome = '" + primo + "'");
            String SQLPersonal = ("SELECT  mydb.personaleinterno.Nome, mydb.personaleinterno.Cognome, mydb.Allergeni.Nome, mydb.Primi.Nome " +
                    "FROM ((( mydb.primi " +
                    "INNER JOIN mydb.allergeni_has_primi ON mydb.allergeni_has_primi.Primi_Nome = mydb.primi.nome) " +
                    "INNER JOIN mydb.allergeni ON mydb.allergeni_has_primi.Allergeni_Nome = mydb.allergeni.Nome) " +
                    "INNER JOIN mydb.personaleinterno ON mydb.personaleinterno.Allergie = mydb.allergeni.Nome) " +
                    "WHERE mydb.primi.Nome = '" + primo + "'");
            ResultSet rsChild = stmt.executeQuery(SQLChild);
            //System.out.println(rs.getString(1)); // prova per vedere se funziona

            while (rsChild.next())
                values.add(new BambiniAllergici(rsChild.getString(1), rsChild.getString(2), rsChild.getString(3),
                        rsChild.getString(4), null, null));
            ResultSet rsPersonal = stmt.executeQuery(SQLPersonal);
            while (rsPersonal.next())
                values.add(new BambiniAllergici(rsPersonal.getString(1), rsPersonal.getString(2), rsPersonal.getString(3),
                        rsPersonal.getString(4), null, null));
        }
        if (!secondo.isEmpty()){
            String SQL = ("SELECT  mydb.bambini.Nome, mydb.bambini.Cognome, mydb.Allergeni.Nome, mydb.Secondi.Nome " +
                "FROM ((( mydb.secondi " +
                "INNER JOIN mydb.allergeni_has_secondi ON mydb.allergeni_has_secondi.Secondi_Nome = mydb.secondi.nome) " +
                "INNER JOIN mydb.allergeni ON mydb.allergeni_has_secondi.Allergeni_Nome = mydb.allergeni.Nome) " +
                "INNER JOIN mydb.bambini ON mydb.bambini.Allergie = mydb.allergeni.Nome) " +
                "WHERE mydb.secondi.Nome = '" + secondo + "'");
            String SQLPersonal = ("SELECT  mydb.PersonaleInterno.Nome, mydb.PersonaleInterno.Cognome, mydb.Allergeni.Nome, mydb.Secondi.Nome " +
                    "FROM ((( mydb.secondi " +
                    "INNER JOIN mydb.allergeni_has_secondi ON mydb.allergeni_has_secondi.Secondi_Nome = mydb.secondi.nome) " +
                    "INNER JOIN mydb.allergeni ON mydb.allergeni_has_secondi.Allergeni_Nome = mydb.allergeni.Nome) " +
                    "INNER JOIN mydb.personaleinterno ON mydb.personaleinterno.Allergie = mydb.allergeni.Nome) " +
                    "WHERE mydb.secondi.Nome = '" + secondo + "'");
            ResultSet rs = stmt.executeQuery(SQL);


            while (rs.next())
                values.add(new BambiniAllergici(rs.getString(1), rs.getString(2), rs.getString(3),
                        null, rs.getString(4), null));
            ResultSet rsPersonal = stmt.executeQuery(SQLPersonal);
            while (rsPersonal.next())
                values.add(new BambiniAllergici(rsPersonal.getString(1), rsPersonal.getString(2), rsPersonal.getString(3),
                        null , rsPersonal.getString(4), null));
        }
        if (!contorno.isEmpty()){
            String SQL = ("SELECT  mydb.bambini.Nome, mydb.bambini.Cognome, mydb.Allergeni.Nome, mydb.contorno.Nome " +
                    "FROM ((( mydb.contorno " +
                    "INNER JOIN mydb.allergeni_has_contorno ON mydb.allergeni_has_contorno.Contorno_Nome = mydb.contorno.nome) " +
                    "INNER JOIN mydb.allergeni ON mydb.allergeni_has_contorno.Allergeni_Nome = mydb.allergeni.Nome) " +
                    "INNER JOIN mydb.bambini ON mydb.bambini.Allergie = mydb.allergeni.Nome) " +
                    "WHERE mydb.contorno.Nome = '" + contorno + "'");
            String SQLPersonal = ("SELECT  mydb.PersonaleInterno.Nome, mydb.PersonaleInterno.Cognome, mydb.Allergeni.Nome, mydb.contorno.Nome " +
                    "FROM ((( mydb.contorno " +
                    "INNER JOIN mydb.allergeni_has_contorno ON mydb.allergeni_has_contorno.Contorno_Nome = mydb.contorno.nome) " +
                    "INNER JOIN mydb.allergeni ON mydb.allergeni_has_contorno.Allergeni_Nome = mydb.allergeni.Nome) " +
                    "INNER JOIN mydb.personaleinterno ON mydb.personaleinterno.Allergie = mydb.allergeni.Nome) " +
                    "WHERE mydb.contorno.Nome = '" + contorno + "'");
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next())
                values.add(new BambiniAllergici(rs.getString(1), rs.getString(2), rs.getString(3),
                        null, null, rs.getString(4)));
            ResultSet rsPersonal = stmt.executeQuery(SQLPersonal);

            while (rsPersonal.next())
                values.add(new BambiniAllergici(rsPersonal.getString(1), rsPersonal.getString(2), rsPersonal.getString(3),
                        null , null, rsPersonal.getString(4)));
        }

        return values;
    }


    @Override
    public boolean addMenu(String nome, String primo, String secondo, String contorno, LocalDate giorno) throws Exception {

        PreparedStatement preparedStatement = null;

        String nomeMenu = "INSERT INTO mydb.menumensa (Nome, Giorno, Secondi_Nome, Primi_Nome, Contorno_Nome) VALUES (?,?,?,?,?)";

        try {
            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(nomeMenu);

            preparedStatement.setString(1, nome);
            preparedStatement.setDate(2, java.sql.Date.valueOf(giorno));
            preparedStatement.setString(3, secondo);
            preparedStatement.setString(4, primo);
            preparedStatement.setString(5, contorno);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                if (preparedStatement != null) {
                    preparedStatement.close();  //chiudo le connessioni al db una volta effettuato il controllo

                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;

    }

    @Override
    public ArrayList<FirstDishGS> viewFirst() throws Exception {

        ArrayList<FirstDishGS> values = new ArrayList<>();
        String sql = ("SELECT * FROM mydb.allergeni_has_primi ");
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            String colonnaPrimi = rs.getString("Allergeni_Nome");
            String colonnaAllergene = rs.getString("Primi_Nome");

            values.add(new FirstDishGS(colonnaAllergene, colonnaPrimi));
        }

        rs.close();
        return values;

    }

    @Override
    public ArrayList<SecondDishGS> viewSecond() throws Exception {

        ArrayList<SecondDishGS> values = new ArrayList<>();
        String sql = ("SELECT * FROM mydb.allergeni_has_secondi ");
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            String colonnaAllergeni = rs.getString("Allergeni_nome");
            String colonnaSecondi = rs.getString("Secondi_Nome");

            values.add(new SecondDishGS(colonnaSecondi, colonnaAllergeni));
        }

        rs.close();
        return values;

    }

    @Override
    public ArrayList<SideDishGS> viewSide() throws Exception {

        ArrayList<SideDishGS> values = new ArrayList<>();
        String sql = ("SELECT * FROM mydb.allergeni_has_contorno ");
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {

            String colonnaAllergeni = rs.getString("Allergeni_nome");
            String colonnaContorni = rs.getString("Contorno_Nome");

            values.add(new SideDishGS(colonnaContorni, colonnaAllergeni));
        }

        rs.close();
        return values;
    }

    @Override
    public boolean addPrimo(String nome, String allergeni) throws Exception {
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;


        String primoPiatto = "INSERT INTO mydb.primi (Nome) VALUES (?)";
        String primiEAllergeni = "INSERT INTO mydb.allergeni_has_primi (Allergeni_Nome, Primi_Nome) VALUES (?,?)";

        try {

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(primoPiatto);
            preparedStatement2 = connectionDatabase.initializeConnection().prepareStatement(primiEAllergeni);

            preparedStatement.setString(1, nome);
            preparedStatement.executeUpdate();

            preparedStatement2.setString(1, allergeni);
            preparedStatement2.setString(2, nome);
            preparedStatement2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                if (preparedStatement != null  && preparedStatement2 != null) {
                    preparedStatement.close();  //chiudo le connessioni al db una volta effettuato il controllo
                    preparedStatement2.close();
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;

    }

    @Override
    public boolean addSecondo(String nome, String allergeni) throws Exception {
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;

        String secondoPiatto = "INSERT INTO mydb.secondi (Nome) VALUES (?)";
        String primiEAllergeni = "INSERT INTO mydb.allergeni_has_secondi (Allergeni_Nome, Secondi_Nome) VALUES (?,?)";

        try {

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(secondoPiatto);
            preparedStatement2 = connectionDatabase.initializeConnection().prepareStatement(primiEAllergeni);

            preparedStatement.setString(1, nome);
            preparedStatement.executeUpdate();

            preparedStatement2.setString(1, allergeni);
            preparedStatement2.setString(2, nome);
            preparedStatement2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                if (preparedStatement != null && preparedStatement2 != null) {
                    preparedStatement.close();  //chiudo le connessioni al db una volta effettuato il controllo
                    preparedStatement2.close();
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;

    }

    @Override
    public boolean addAllergy(String allergeni) throws Exception {

        PreparedStatement preparedStatement = null;

        String sqlAllergeni = "INSERT INTO mydb.allergeni (Nome) VALUES (?)";

        try {

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(sqlAllergeni);

            preparedStatement.setString(1, allergeni);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                if (preparedStatement != null) {
                    preparedStatement.close();  //chiudo le connessioni al db una volta effettuato il controllo

                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;

    }

    @Override
    public boolean addSide(String nome, String allergeni) throws Exception {

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;

        String secondoPiatto = "INSERT INTO mydb.contorno (Nome) VALUES (?)";
        String contornoEAllergeni = "INSERT INTO mydb.allergeni_has_contorno (Allergeni_Nome, Contorno_Nome) VALUES (?,?)";

        try {

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(secondoPiatto);
            preparedStatement2 = connectionDatabase.initializeConnection().prepareStatement(contornoEAllergeni);

            preparedStatement.setString(1, nome);
            preparedStatement.executeUpdate();

            preparedStatement2.setString(1, allergeni);
            preparedStatement2.setString(2, nome);
            preparedStatement2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                if (preparedStatement != null && preparedStatement2 != null) {
                    preparedStatement.close();  //chiudo le connessioni al db una volta effettuato il controllo
                    preparedStatement2.close();
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;

    }

    @Override
    public boolean deleteMenu(String nomeMenu) throws Exception {

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();

        PreparedStatement st = null;

        String queryDelete = "DELETE FROM mydb.menumensa WHERE Nome = '" + nomeMenu + "';";

        try {

            st = connectionDatabase.initializeConnection().prepareStatement(queryDelete);
            st.executeUpdate(queryDelete);
            System.out.println("Deleted from Menù.");


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

    @Override
    public boolean deleteFirst(String first) throws Exception {
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();

        PreparedStatement st = null;

        String queryDelete = "DELETE FROM mydb.primi WHERE Nome = '" + first + "';";

        try {

            st = connectionDatabase.initializeConnection().prepareStatement(queryDelete);
            st.executeUpdate(queryDelete);
            System.out.println("Deleted from Primi.");


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

    @Override
    public boolean deleteSecond(String second) throws Exception {
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();

        PreparedStatement st = null;

        String queryDelete = "DELETE FROM mydb.secondi WHERE Nome = '" + second + "';";

        try {

            st = connectionDatabase.initializeConnection().prepareStatement(queryDelete);
            st.executeUpdate(queryDelete);
            System.out.println("Deleted from Secondi.");


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

    @Override
    public boolean deleteSide(String side) throws Exception {

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();

        PreparedStatement st = null;

        String queryDelete = "DELETE FROM mydb.menumensa WHERE Nome = '" + side + "';";

        try {

            st = connectionDatabase.initializeConnection().prepareStatement(queryDelete);
            st.executeUpdate(queryDelete);
            System.out.println("Deleted from Side.");


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

    @Override
    public boolean controlAllergy(String allergeni) throws Exception {
        String control = "SELECT * FROM mydb.allergeni WHERE Nome = '"+ allergeni+"'";

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(control);

        if(!rs.next())
            return true;
        else
            return false;
    }

    @Override
    public boolean newTrip(String id, String meta, LocalDate andata, LocalDate ritorno) throws Exception {

        PreparedStatement preparedStatement = null;

        String trip = "INSERT INTO mydb.gita (idGita,Meta,Data_Partenza,Data_Ritorno) VALUES (?,?,?,?)";

        try {

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(trip);

            preparedStatement.setInt(1, Integer.parseInt(id));
            preparedStatement.setString(2, meta);
            preparedStatement.setDate(3, Date.valueOf(andata));
            preparedStatement.setDate(4, Date.valueOf(ritorno));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                if (preparedStatement != null) {
                    preparedStatement.close();      //chiudo le connessioni al db una volta effettuato il controllo
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public ArrayList<TripGS> viewTrip() throws Exception {

        ArrayList<TripGS> values = new ArrayList<>();
        String sql = ("SELECT * FROM mydb.gita");
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            String colonnaId = rs.getString("idGita");
            String colonnaMeta = rs.getString("Meta");
            Date colonnaAndata = rs.getDate("Data_Partenza");
            Date colonnaRitorno = rs.getDate("Data_Ritorno");
            String pullman = rs.getString("NumPullman");

            values.add(new TripGS(colonnaId, colonnaMeta, colonnaAndata, colonnaRitorno, pullman));
        }
        rs.close();
        return values;
    }

    @Override
    public ArrayList<AppealGS> loadDataServer(int idGita) throws Exception {

        ArrayList<AppealGS> values = new ArrayList<>();
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();
        String SQL = ("SELECT mydb.bambini.Nome, mydb.bambini.Cognome, mydb.bambini.CodiceFiscale, mydb.bambini_has_gita.Presenza, mydb.bambini_has_gita.Pullman " +
                "FROM ((mydb.bambini_has_gita " +
                "INNER JOIN mydb.bambini ON mydb.bambini.CodiceFiscale = mydb.bambini_has_gita.Bambini_CodiceFiscale AND mydb.bambini_has_gita.Gita_idGita = ");
        ResultSet rs = stmt.executeQuery(SQL + idGita + "))");
        while (rs.next()){
            String colonnaNome = rs.getString("Nome");
            String colonnaCognome = rs.getString ("Cognome");
            String colonnaCodicefiscale = rs.getString("CodiceFiscale");
            int colonnaPullman = rs.getInt("Pullman");
            String colonnaPresenza;

            if (rs.getInt("Presenza") == 1)
                colonnaPresenza = ("Presente");
            else if (rs.getInt("Presenza") == 0)
                colonnaPresenza = ("Assente");
            else // if (rs.getInt("Presenza") == 2)
                colonnaPresenza = ("Presente sul pullman sbagliato");

            values.add(new AppealGS(colonnaNome, colonnaCognome, colonnaCodicefiscale, colonnaPresenza, colonnaPullman));
        }
        return values;
    }

    @Override
    public void bambinoPresenteServer(String codiceFiscale, int idGita, int pullman) throws Exception {

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmtInt = connectionDatabase.initializeConnection().createStatement();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();
        String SQL;
        String CheckPullman = ("SELECT mydb.bambini_has_gita.* FROM mydb.bambini_has_gita WHERE(mydb.bambini_has_gita.Bambini_CodiceFiscale = '"
                + codiceFiscale + "' AND mydb.bambini_has_gita.Gita_idGita = " + idGita + ")");
        ResultSet rs = stmtInt.executeQuery(CheckPullman);
        if (rs.next()) {
            if (rs.getInt("Pullman") == pullman)
                SQL = ("UPDATE mydb.bambini_has_gita SET Presenza = 1 WHERE (mydb.bambini_has_gita.Bambini_CodiceFiscale = '"
                        + codiceFiscale + "' AND mydb.bambini_has_gita.Gita_idGita = " + idGita + ")");
            else
                SQL = ("UPDATE mydb.bambini_has_gita SET Presenza = 2 WHERE (mydb.bambini_has_gita.Bambini_CodiceFiscale = '"
                        + codiceFiscale + "' AND mydb.bambini_has_gita.Gita_idGita = " + idGita + ")");
            stmt.executeUpdate(SQL);
        }
        }
    @Override
    public void bambinoAssenteServer(String codiceFiscale, int idGita) throws Exception {

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();
        String SQL = ("UPDATE mydb.bambini_has_gita SET Presenza = 0 WHERE (mydb.bambini_has_gita.Bambini_CodiceFiscale = '"
                + codiceFiscale + "' AND mydb.bambini_has_gita.Gita_idGita = " + idGita + ")");
        stmt.executeUpdate(SQL);
    }

    @Override
    public boolean newTappaServer(String tappa, String idGita, LocalDate giorno) throws Exception {


        PreparedStatement preparedStatement = null;

        String trip = "INSERT INTO mydb.tappe (Nome,Data,Gita_idGita) VALUES (?,?,?)";

        try {

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(trip);

            preparedStatement.setString(1, tappa);
            preparedStatement.setDate(2, Date.valueOf(giorno));
            //preparedStatement.setTime(4, Time.valueOf(ora));
            preparedStatement.setInt(3, Integer.parseInt(idGita));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                if (preparedStatement != null) {
                    preparedStatement.close();      //chiudo le connessioni al db una volta effettuato il controllo
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean newpartecipanteTrip(String codiceFiscale, String idGita) throws Exception {

        PreparedStatement preparedStatement = null;
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();

        String childTrip = "INSERT INTO mydb.bambini_has_gita (Bambini_CodiceFiscale, Gita_idGita) VALUES (?,?)";

        try {

            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(childTrip);

            preparedStatement.setString(1, codiceFiscale);
            preparedStatement.setInt(2, Integer.parseInt(idGita));
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                if (preparedStatement != null) {
                    preparedStatement.close();      //chiudo le connessioni al db una volta effettuato il controllo
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean deleteTrip(String idGita) throws Exception {
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();

        PreparedStatement st = null;

        String queryDelete = "DELETE FROM mydb.gita WHERE idGita = '" + idGita + "';";

        try{

            st = connectionDatabase.initializeConnection().prepareStatement(queryDelete);
            st.executeUpdate(queryDelete);
            System.out.println("Deleted from Gita.");

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

    @Override
    public void pullmanCount(String idGita) throws Exception {

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();
        String CountSQL = ("SELECT * FROM mydb.bambini_has_gita WHERE mydb.bambini_has_gita.Gita_idGIta = ");
        ResultSet rsbis = stmt.executeQuery(CountSQL + idGita);
        float count = 0.0f;
        while (rsbis.next()){
            count = count + 1;
        }
        float x = (float) (count / 1.00);
        int NumPullman = (int) Math.ceil(x);
        stmt.executeUpdate("UPDATE mydb.gita SET NumPullman = " + NumPullman + " WHERE mydb.gita.idGita = " + idGita);

    }

    @Override
    public void assegnaPullman(String codiceFiscale, String idGita) throws Exception {
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();

        String PullmanSQL = ("SELECT * FROM mydb.bambini_has_gita WHERE mydb.bambini_has_gita.Gita_idGIta = ");
        ResultSet rsbis = stmt.executeQuery(PullmanSQL + idGita);
        float counter = 0.0f;
        while (rsbis.next()){
            counter = counter + 1;
        }
        float x = (float) (counter / 1.00);
        int Pullman = (int) Math.ceil(x);
        String SQL = ("UPDATE mydb.bambini_has_gita SET Pullman = " + Pullman + " WHERE mydb.bambini_has_gita.Bambini_CodiceFiscale = '" +
                codiceFiscale  + "' AND mydb.bambini_has_gita.Gita_idGIta = '" + idGita + "'");
        stmt.executeUpdate(SQL);
    }

    @Override
    public void assenzaAll(int idGita) throws Exception {
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();
        String SQL = ("UPDATE mydb.bambini_has_gita SET Presenza = 0 WHERE mydb.bambini_has_gita.Gita_idGita = " + idGita );
        stmt.executeUpdate(SQL);
    }

    @Override
    public boolean controlGita(String id) throws Exception {
        String control = "SELECT * FROM mydb.gita WHERE idGita = '"+ Integer.parseInt(id)+"'";

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(control);

        if(!rs.next())
            return true;
        else
            return false;
    }

}
