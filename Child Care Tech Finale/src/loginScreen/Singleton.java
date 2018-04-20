package loginScreen;

import serverRMI.ClientRMI;
import serverRMI.InterfaceRMI;
import serverRMI.User;

public class Singleton {

    private static Singleton instance = null;

    private Singleton(){}

    public static Singleton getInstance(){
        if(instance == null)
            instance = new Singleton();
        return instance;
    }

    public InterfaceRMI rmiLookup(){
        User inte;
        inte = new ClientRMI();
        InterfaceRMI rmi = null;
        try{
            rmi = inte.getUser();

        }catch(Exception e){
            e.printStackTrace();
        }

        return rmi;
    }
}
