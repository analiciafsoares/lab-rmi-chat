package chat;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ChatServidor extends Remote {

    boolean registrarCliente(String nick, ChatCliente cliente)
            throws RemoteException;

    void removerCliente(String nick) throws RemoteException;

    List<String> listarClientes() throws RemoteException;

    ChatCliente buscarCliente(String nick) throws RemoteException;

    void enviarMensagemSala(String de, String msg) throws RemoteException;
}
