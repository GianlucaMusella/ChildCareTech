package serverRMI;

import java.rmi.Remote;

public interface InterfaceRMI extends Remote {

    boolean login(String username, String password) throws Exception;

}
