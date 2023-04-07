package server;

/**
 * Définit la seule méthode qu'auront tous les eventHandlers.
 */
@FunctionalInterface
public interface EventHandler {
    void handle(String cmd, String arg);
}
