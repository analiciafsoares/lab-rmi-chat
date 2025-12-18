package chat;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.List;
import java.util.Scanner;

public class ClienteMain {

    public static void main(String[] args) {

        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new RMISecurityManager());
            }

            Scanner sc = new Scanner(System.in);

            System.out.print("Digite seu @: ");
            String nick = sc.nextLine();

            ChatServidor servidor =
                (ChatServidor) Naming.lookup("//localhost/ChatServidor");

            ChatCliente cliente = new ChatClienteImpl(nick);

            if (!servidor.registrarCliente(nick, cliente)) {
                System.out.println("Nick já em uso.");
                return;
            }

            int opcao;

            do {
                System.out.println("\n--- MENU ---");
                System.out.println("1 - Listar clientes ativos");
                System.out.println("2 - Enviar mensagem privada");
                System.out.println("3 - Enviar mensagem para sala");
                System.out.println("0 - Sair");
                System.out.print("Escolha: ");

                opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {

                    case 1:
                        List<String> clientes = servidor.listarClientes();
                        System.out.println("Clientes ativos:");
                        for (String c : clientes) {
                            System.out.println("- " + c);
                        }
                        break;

                    case 2:
                        System.out.print("Digite o @ do destinatário: ");
                        String destino = sc.nextLine();

                        ChatCliente clienteDestino =
                                servidor.buscarCliente(destino);

                        if (clienteDestino == null) {
                            System.out.println("Cliente não encontrado.");
                        } else {
                            System.out.print("Mensagem: ");
                            String msgPrivada = sc.nextLine();
                            clienteDestino.receberMensagem(nick, msgPrivada);
                        }
                        break;

                    case 3:
                        System.out.print("Mensagem para sala: ");
                        String msgSala = sc.nextLine();
                        servidor.enviarMensagemSala(nick, msgSala);
                        break;

                    case 0:
                        servidor.removerCliente(nick);
                        System.out.println("Saindo...");
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }

            } while (opcao != 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

