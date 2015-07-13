import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class Server {
	// Singleton Designpattern
	private static Server instance;	
	public static Server instance() {
		if(instance == null)
			instance = new Server();
		return instance;
	}

	private ServerSocket listenSocket;
	private volatile boolean running;
	private List<ClientHandler> clients;
	private Game game;

	public Game getGame() {
		return game;
	}

	public void broadcast(String msg) {
		synchronized(clients) {
			for(ClientHandler i : clients)
				i.send(msg);
		}
	}

	public int clientCount() {
		synchronized(clients) {
			return clients.size();
		}
	}

	/**
	 * Resets the game and all the clienthandlers to be standard clients again
	 */
	public void resetGame() {
		if(game != null)
			game.close();
		game = new Game();
		synchronized(clients) {
			ListIterator<ClientHandler> i = clients.listIterator();
			while(i.hasNext()) {
				ClientHandler tmp = i.next();

				if(!tmp.isAlive() || !tmp.isRunning()) {
					i.remove();
					continue;
				}

				tmp.setCommands((new Client()).createCommands());
			}
		}
	}

	public Server() {
		clients = new LinkedList<ClientHandler>();
	}

	public void run() {
		running=true;
		while(running) {
			clients.clear();
			resetGame();
			try {
				listenSocket = new ServerSocket(25002);
				System.out.println("Server started");

				while(running) {
					ClientHandler tmp = ClientHandler.factory(listenSocket.accept());
					tmp.setCommands((new Client()).createCommands());
					tmp.send("Welcome to the Snake server - send \"help\" to get all available commands");
					tmp.send("Remember to end each command with a \\n");
					tmp.start();
					synchronized(clients) {
						clients.add(tmp);
					}

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				System.out.println("Shutting down");
				try {
					listenSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		Server.instance().run();
	}

}
