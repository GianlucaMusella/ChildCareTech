package serverRMI;

import java.rmi.Remote;
import java.time.LocalDate;

public interface InterfaceRMI extends Remote {

    boolean login(String username, String password) throws Exception;
    boolean newSupplier(String name, String surname, String azienda, String fornitura, String partitaIva) throws Exception;
    boolean addChild(String codiceFiscale, String idBambino, String nome, String cognome, LocalDate data, String luogo, String allergie, String genitore1, String genitore2, String pediatra) throws Exception;

}
