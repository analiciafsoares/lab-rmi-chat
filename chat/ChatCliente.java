package chat;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatCliente extends Remote {

    void receberMensagem(String de, String msg) throws RemoteException;
}
