package chat;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClienteImpl extends UnicastRemoteObject
        implements ChatCliente {

    private String nick;

    public ChatClienteImpl(String nick) throws RemoteException {
        this.nick = nick;
    }

    @Override
    public void receberMensagem(String de, String msg)
            throws RemoteException {
        System.out.println("[" + de + "]: " + msg);
    }
}
