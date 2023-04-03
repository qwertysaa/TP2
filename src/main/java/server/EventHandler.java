package server;

/**
 * Définit que tous les eventHandlers auront la même méthode.
 */
@FunctionalInterface
public interface EventHandler {
    void handle(String cmd, String arg);
}
