package chat;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;

public class ServidorMain {

    public static void main(String[] args) {

        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new RMISecurityManager());
            }

            LocateRegistry.createRegistry(1099);

            ChatServidor servidor = new ChatServidorImpl();
            Naming.rebind("ChatServidor", servidor);

            System.out.println("Servidor de chat RMI pronto.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
