package server;

/**
 * Démarre le serveur.
 */
public class ServerLauncher {
    /**
     * Représente le port du serveur auquel le client va se connecter.
     */
    public final static int PORT = 1337;

    /**
     * Active le serveur afin qu'il soit prêt à recevoir des commandes.
     *
     * @param args
     */
    public static void main(String[] args) {
        Server server;
        try {
            server = new Server(PORT);
            System.out.println("Server is running...");
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}