package chat;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ChatServidorImpl extends UnicastRemoteObject
        implements ChatServidor {

    private Map<String, ChatCliente> clientes;

    public ChatServidorImpl() throws RemoteException {
        clientes = new HashMap<>();
    }

    @Override
    public synchronized boolean registrarCliente(String nick, ChatCliente cliente)
            throws RemoteException {

        if (clientes.containsKey(nick)) {
            return false;
        }
        clientes.put(nick, cliente);
        System.out.println(nick + " entrou no chat.");
        return true;
    }

    @Override
    public synchronized void removerCliente(String nick)
            throws RemoteException {
        clientes.remove(nick);
        System.out.println(nick + " saiu do chat.");
    }

    @Override
    public List<String> listarClientes() throws RemoteException {
        return new ArrayList<>(clientes.keySet());
    }

    @Override
    public ChatCliente buscarCliente(String nick)
            throws RemoteException {
        return clientes.get(nick);
    }

    @Override
    public void enviarMensagemSala(String de, String msg)
            throws RemoteException {

        for (ChatCliente c : clientes.values()) {
            c.receberMensagem(de, msg);
        }
    }
}

