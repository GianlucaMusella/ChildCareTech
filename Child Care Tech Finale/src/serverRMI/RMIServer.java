package serverRMI;

import connectionDatabase.ConnectionDatabase;
import getterAndSetter.people.*;
import getterAndSetter.food.AllergyPeopleGS;
import getterAndSetter.food.FirstDishGS;
import getterAndSetter.food.MenuGS;
import getterAndSetter.food.SecondDishGS;
import trip.AppealGS;
import trip.TripGS;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class RMIServer extends UnicastRemoteObject implements InterfaceRMI {

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
            System.out.println(e);
        }

        return false;

    }

    @Override
    public boolean addSupplier(String name, String surname, String azienda, String fornitura, String partitaIva) throws Exception {

        PreparedStatement preparedStatement = null;

        String supplier = "INSERT INTO mydb.fornitori (Nome,Cognome,Azienda,TipoDiFornitura,PartitaIVA) VALUES (?,?,?,?,?)";

        try {

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
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
    public void modifySupplier(String azienda, String nome, String cognome, String fornitura, String partitaIva) throws Exception {

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();

        String SQL = ("UPDATE mydb.fornitori SET ");
        String equal = ("WHERE Azienda = '" + azienda + "'");
        if (!nome.isEmpty()) {
            stmt.executeUpdate(SQL + "Nome = '" + nome + "'" + equal);
            // System.out.println(Nome);
        }
        if (!cognome.isEmpty()) {
            stmt.executeUpdate(SQL + "Cognome = '" + cognome + "'" + equal);
            // System.out.println(Cognome);
        }
        if (!fornitura.isEmpty()) {
            stmt.executeUpdate(SQL + "TipoDiFornitura = '" + fornitura + "'" + equal);
            // System.out.println(Luogo);
        }
        if (partitaIva != null) {
            stmt.executeUpdate(SQL + "PartitaIVA = '" + partitaIva + "'" + equal);
            // System.out.println(data);
        }
    }

    @Override
    public boolean addOrder(String azienda, String ordini, String quantità) throws Exception {

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;

        String order = "INSERT INTO mydb.ordine (Nome, Quantità) VALUES (?,?)";
        String fornitura = "INSERT INTO mydb.fornitori_has_ordine (Fornitori_Azienda, Ordine_Nome) VALUES (?,?)";

        try {

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
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
                    return true;
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
    public boolean addChild(String codiceFiscale, String idBambino, String nome, String cognome, LocalDate data, String luogo, String allergie, String genitore1, String genitore2, String sesso, String pediatra, String contatto) throws Exception {

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;

        String child = "INSERT INTO mydb.bambini (idBambino,CodiceFiscale,Nome,Cognome,Data_di_Nascita,Luogo_di_Nascita,Allergie,Sesso,Pediatra_CodiceFiscale) VALUES (?,?,?,?,?,?,?,?,?)";
        String parents = "INSERT INTO mydb.bambini_has_genitori (Bambini_CodiceFiscale,Genitori_CodiceFiscale) VALUES (?,?)";
        String contact = "INSERT INTO mydb.bambini_has_contatti (Bambini_CodiceFiscale,Contatti_CodiceFiscale) VALUES (?,?)";

        try {

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
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

        return false;
    }

    @Override
    public boolean addTeacher(String nome, String cognome, String codiceFiscale, LocalDate data, String luogo, String allergie, String sesso, String insegnante, String username, String password) throws Exception {

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;

        String teacher = "INSERT INTO mydb.personaleinterno (CodiceFiscale,Nome,Cognome,Data_di_Nascita,Allergie,Luogo_di_Nascita,Sesso,Mansione) VALUES (?,?,?,?,?,?,?,?)";
        String teacherCredentials = "INSERT INTO mydb.personaleconaccesso(Username,Password,PersonaleInterno_CodiceFiscale) VALUES (?,?,?)";
        try {

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
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

        String teacher = "INSERT INTO mydb.personaleinterno (CodiceFiscale,Nome,Cognome,Data_di_Nascita,Allergie,Luogo_di_Nascita,Sesso,Mansione) VALUES (?,?,?,?,?,?,?,?)";

        try {

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
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
    public void modifyStaff(String codiceFiscale, String nome, String cognome, String luogo, LocalDate data, String mansione) throws Exception {

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();

        String SQL = ("UPDATE mydb.personaleinterno SET ");
        String equal = ("WHERE CodiceFiscale = '" + codiceFiscale + "'");
        if (!nome.isEmpty()) {
            stmt.executeUpdate(SQL + "Nome = '" + nome + "'" + equal);
            // System.out.println(Nome);
        }
        if (!cognome.isEmpty()) {
            stmt.executeUpdate(SQL + "Cognome = '" + cognome + "'" + equal);
            // System.out.println(Cognome);
        }
        if (!luogo.isEmpty()) {
            stmt.executeUpdate(SQL + "Luogo_di_Nascita = '" + luogo + "'" + equal);
            // System.out.println(Luogo);
        }
        if (data != null) {
            stmt.executeUpdate(SQL + "Data_di_Nascita = '" + data + "'" + equal);
            // System.out.println(data);
        }
        if (mansione != null) {
            stmt.executeUpdate(SQL + "Mansione = '" + mansione + "'" + equal);
            // System.out.println(data);
        }

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
    public void modifyChild(String codiceFiscale, String Nome, String cognome, String luogo, LocalDate data, String idBambino) throws Exception {
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();

        String SQL = ("UPDATE mydb.bambini SET ");
        String equal = ("WHERE CodiceFiscale = '" + codiceFiscale + "'");
        if (!Nome.isEmpty()) {
            stmt.executeUpdate(SQL + "Nome = '" + Nome + "'" + equal);
            // System.out.println(Nome);
        }
        if (!cognome.isEmpty()) {
            stmt.executeUpdate(SQL + "Cognome = '" + cognome + "'" + equal);
            // System.out.println(Cognome);
        }
        if (!luogo.isEmpty()) {
            stmt.executeUpdate(SQL + "Luogo_di_Nascita = '" + luogo + "'" + equal);
            // System.out.println(Luogo);
        }
        if (data != null ) {
            stmt.executeUpdate(SQL + "Data_di_Nascita = '" + data + "'" + equal);
            // System.out.println(data);
        }
        if (idBambino != null ) {
            stmt.executeUpdate(SQL + "idBambino = '" + Integer.parseInt(idBambino) + "'" + equal);
            // System.out.println(data);
        }

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
    public void modifyParents(String codiceFiscale, String nome, String cognome, String luogo, LocalDate data, String telefono) throws Exception {

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();

        String SQL = ("UPDATE mydb.genitori SET ");
        String equal = ("WHERE CodiceFiscale = '" + codiceFiscale + "'");
        if (!nome.isEmpty()) {
            stmt.executeUpdate(SQL + "Nome = '" + nome + "'" + equal);
            // System.out.println(Nome);
        }
        if (!cognome.isEmpty()) {
            stmt.executeUpdate(SQL + "Cognome = '" + cognome + "'" + equal);
            // System.out.println(Cognome);
        }
        if (!luogo.isEmpty()) {
            stmt.executeUpdate(SQL + "Luogo_di_Nascita = '" + luogo + "'" + equal);
            // System.out.println(Luogo);
        }
        if (data != null) {
            stmt.executeUpdate(SQL + "Data_di_Nascita = '" + data + "'" + equal);
            // System.out.println(data);
        }
        if (telefono != null) {
            stmt.executeUpdate(SQL + "Telefono = '" + telefono + "'" + equal);
            // System.out.println(data);
        }

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
    public void modifyContact(String codiceFiscale, String nome, String cognome, String telefono) throws Exception {
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();

        String SQL = ("UPDATE mydb.contatti SET ");
        String equal = ("WHERE CodiceFiscale = '" + codiceFiscale + "'");
        if (!nome.isEmpty()) {
            stmt.executeUpdate(SQL + "Nome = '" + nome + "'" + equal);
            // System.out.println(Nome);
        }
        if (!cognome.isEmpty()) {
            stmt.executeUpdate(SQL + "Cognome = '" + cognome + "'" + equal);
            // System.out.println(Cognome);
        }
        if (telefono != null) {
            stmt.executeUpdate(SQL + "Telefono = '" + telefono + "'" + equal);
            // System.out.println(data);
        }
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
    public ArrayList<DoctorGS> viewDoctors() throws Exception {

        ArrayList<DoctorGS> values = new ArrayList<>();
        String sql = ("SELECT * FROM mydb.pediatra ");
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            String colonnanome = rs.getString("Nome");
            String colonnacodicefiscale = rs.getString("CodiceFiscale");

            values.add(new DoctorGS(colonnanome, colonnacodicefiscale));
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
                String colonnacodicefiscale = rs.getString("CodiceFiscale");

                values.add(new DoctorGS(colonnanome, colonnacodicefiscale));

            }
        } else if (name.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "CodiceFiscale = '" + cod + "'");
            while (rs.next()) {
                String colonnanome = rs.getString("Nome");
                String colonnacodicefiscale = rs.getString("CodiceFiscale");

                values.add(new DoctorGS(colonnanome, colonnacodicefiscale));

            }

            rs.close();
        }

        return values;

    }

    @Override
    public void modifyDoctor(String codiceFiscale, String nome, String cognome, String luogo, LocalDate data) throws Exception {

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();

        String SQL = ("UPDATE mydb.pediatra SET ");
        String equal = ("WHERE CodiceFiscale = '" + codiceFiscale + "'");
        if (!nome.isEmpty()) {
            stmt.executeUpdate(SQL + "Nome = '" + nome + "'" + equal);
            // System.out.println(Nome);
        }
        if (!cognome.isEmpty()) {
            stmt.executeUpdate(SQL + "Cognome = '" + cognome + "'" + equal);
            // System.out.println(Cognome);
        }
        if (!luogo.isEmpty()) {
            stmt.executeUpdate(SQL + "Luogo_di_Nascita = '" + luogo + "'" + equal);
            // System.out.println(Luogo);
        }
        if (data != null) {
            stmt.executeUpdate(SQL + "Data_di_Nascita = '" + data + "'" + equal);
            // System.out.println(data);
        }


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
    public ArrayList<AllergyPeopleGS> viewAllergy() throws Exception {

        ArrayList<AllergyPeopleGS> values = new ArrayList<>();
        String sql = ("SELECT * FROM mydb.bambini ");
        String sql1 = ("SELECT * FROM mydb.personaleinterno");

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement statement = connectionDatabase.initializeConnection().createStatement();
        Statement statement1 = connectionDatabase.initializeConnection().createStatement();


        ResultSet rs = statement.executeQuery(sql);
        ResultSet rs1 = statement1.executeQuery(sql1);

        while (rs.next() && rs1.next()) {
            String allergieBambini = rs.getString("Allergie");
            String allergiePersonale = rs1.getString("Allergie");

            values.add(new AllergyPeopleGS(allergieBambini, allergiePersonale));
        }

        statement.close();
        statement1.close();

        return values;
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
    public boolean addMenu(String nome, String primo, String secondo, LocalDate giorno) throws Exception {

        PreparedStatement preparedStatement = null;

        String nomeMenu = "INSERT INTO mydb.menumensa (Nome, Giorno, Secondi_Nome, Primi_Nome) VALUES (?,?,?,?)";

        try {
            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(nomeMenu);

            preparedStatement.setString(1, nome);
            preparedStatement.setDate(2, java.sql.Date.valueOf(giorno));
            preparedStatement.setString(3, secondo);
            preparedStatement.setString(4, primo);
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
    public boolean addPrimo(String nome, String allergeni) throws Exception {
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;


        String primoPiatto = "INSERT INTO mydb.primi (Nome) VALUES (?)";
        String sqlAllergeni = "INSERT INTO mydb.allergeni (Nome) VALUES (?)";
        String primiEAllergeni = "INSERT INTO mydb.allergeni_has_primi (Allergeni_Nome, Primi_Nome) VALUES (?,?)";

        try {

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(primoPiatto);
            preparedStatement1 = connectionDatabase.initializeConnection().prepareStatement(sqlAllergeni);
            preparedStatement2 = connectionDatabase.initializeConnection().prepareStatement(primiEAllergeni);

            preparedStatement.setString(1, nome);
            preparedStatement.executeUpdate();

            preparedStatement1.setString(1, allergeni);
            preparedStatement1.executeUpdate();

            preparedStatement2.setString(1, allergeni);
            preparedStatement2.setString(2, nome);
            preparedStatement2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                if (preparedStatement != null && preparedStatement1 != null && preparedStatement2 != null) {
                    preparedStatement.close();  //chiudo le connessioni al db una volta effettuato il controllo
                    preparedStatement1.close();
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
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;

        String secondoPiatto = "INSERT INTO mydb.secondi (Nome) VALUES (?)";
        String sqlAllergeni = "INSERT INTO mydb.allergeni (Nome) VALUES (?)";
        String primiEAllergeni = "INSERT INTO mydb.allergeni_has_secondi (Allergeni_Nome, Secondi_Nome) VALUES (?,?)";

        try {

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(secondoPiatto);
            preparedStatement1 = connectionDatabase.initializeConnection().prepareStatement(sqlAllergeni);
            preparedStatement2 = connectionDatabase.initializeConnection().prepareStatement(primiEAllergeni);

            preparedStatement.setString(1, nome);
            preparedStatement.executeUpdate();

            preparedStatement1.setString(1, allergeni);
            preparedStatement1.executeUpdate();

            preparedStatement2.setString(1, allergeni);
            preparedStatement2.setString(2, nome);
            preparedStatement2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                if (preparedStatement != null && preparedStatement1 != null && preparedStatement2 != null) {
                    preparedStatement.close();  //chiudo le connessioni al db una volta effettuato il controllo
                    preparedStatement1.close();
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
        String SQL = ("SELECT mydb.bambini.Nome, mydb.bambini.Cognome, mydb.bambini.CodiceFiscale, mydb.bambini_has_gita.Presenza " +
                "FROM ((mydb.bambini_has_gita " +
                "INNER JOIN mydb.bambini ON mydb.bambini.CodiceFiscale = mydb.bambini_has_gita.Bambini_CodiceFiscale AND mydb.bambini_has_gita.Gita_idGita = ");
        ResultSet rs = stmt.executeQuery(SQL + idGita + "))");
        while (rs.next()){
            String colonnaNome = rs.getString("Nome");
            String colonnaCognome = rs.getString ("Cognome");
            String colonnaCodicefiscale = rs.getString("CodiceFiscale");
            String colonnaPresenza;

            if (rs.getBoolean("Presenza"))
                colonnaPresenza = ("Presente");
            else
                colonnaPresenza = ("Assente");

            values.add(new AppealGS(colonnaNome, colonnaCognome, colonnaCodicefiscale, colonnaPresenza));
        }
        return values;
    }

    @Override
    public void bambinoPresenteServer(String codiceFiscale, int idGita) throws Exception {

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();
        String SQL = ("UPDATE mydb.bambini_has_gita SET Presenza = 1 WHERE (mydb.bambini_has_gita.Bambini_CodiceFiscale = '"
                + codiceFiscale + "' AND mydb.bambini_has_gita.Gita_idGita = " + idGita + ")");
        stmt.executeUpdate(SQL);

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
    public boolean newTappaServer(String numeroTappa, String tappa, String idGita, LocalDate giorno, String ora) throws Exception {


        PreparedStatement preparedStatement = null;

        String trip = "INSERT INTO mydb.tappe (idTappa,Nome,Data,Gita_idGita) VALUES (?,?,?,?)";

        try {

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(trip);

            preparedStatement.setInt(1, Integer.parseInt(numeroTappa));
            preparedStatement.setString(2, tappa);
            preparedStatement.setDate(3, Date.valueOf(giorno));
            //preparedStatement.setTime(4, Time.valueOf(ora));
            preparedStatement.setInt(4, Integer.parseInt(idGita));
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

}
