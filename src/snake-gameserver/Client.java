import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Represents a client on the server, not to be used on network-client
 * @author sph3re
 *
 */
public class Client {
	
	public String help(String parameters, ClientHandler caller) {
		return  "This is the help functionality for Nico's snake server.\n" +
				"In the following, there will be a listing of all available commands and their meaning.\n" +
				"Always remember to put a newline character at the end of your command!\n" +
				"---\n" +
				"--- General commands\n" +
				"help - Displays this help message\n" +
				"task - Tells you what you have to do to win\n" +
				"echo - Not really useful, sends back what you passed as parameter\n" +
				"ready - Tells the server that you want to participate in the next game. It will reject if the maximum number of players is already reached or another game is currently running\n" +
				"start - Request the server to start a game. It will reject if the minimum number of players is not yet reached. You can pass a frametime in ms as parameter\n" +
				"status - Show some status information about the server\n" +
				"--- Ingame commands\n" +
				"right - Turn right on the next step\n" +
				"left - Turn left on the next step\n" +
				"neutral - Go straight on the next step (default)\n" +
				"---\n" +
				"If your command is none of the above, your connection will be terminated.\n" +
				"As soon as a game started, all the connected clients will receive the field on every step"
				;
	}
	
	public String task(String parameters, ClientHandler caller) {
		return  "Your task is to write a snake client\n" +
				"At first you should experiment a little with the commands\n" +
				"Then, try to start a game with a simple UI(user interface)\n" +
				"Eventually, write an AI(artificial intelligence) that plays the game\n" +
				"If it outperforms the others, you win the tournament\n" +
				"---\n" +
				"You will have to control a snake, just as on the old nokias\n" +
				"The difference is: there is no apple\n" +
				"You fight against at least one other snake\n" +
				"The snakes will move at a fixed speed - we will start with 1 step per second\n" +
				"Every snake will grow one piece each five steps\n" +
				"If one snake runs into the screen border, it will lose\n" +
				"If one snake bites another snake or itself, it will lose\n" +
				"If all other snakes lost, the remaining snake will win\n" +
				"If all remaining snakes died in the same step, it is a draw\n"
				;
	}
	
	public String echo(String parameters, ClientHandler caller) {
		return parameters;
	}
	
	public String ready(String parameters, ClientHandler caller) {
		// Morph to player and try to register
		Player tmp = new Player();
		int playerID = Server.instance().getGame().registerPlayer(tmp);
		if(playerID < 0)
			return "Sorry, you were rejected from the game";
		caller.setCommands(tmp.createCommands());
		return "You are now registered as player " + playerID;
	}
	
	public String start(String parameters, ClientHandler caller) {
		int sleepcount = 2000;
		
		if(!parameters.isEmpty()) {
			try {
				sleepcount = Integer.valueOf(parameters);
			} catch(Exception e) {
				sleepcount = 2000;
			}
			
			if(sleepcount < 50 || sleepcount > 10000)
				sleepcount = 2000;
		}
		
		if(Server.instance().getGame().startGame(sleepcount))
			return "Starting game";
		else
			return "Starting game rejected.";
	}
	
	public String status(String parameters, ClientHandler caller) {
		return  "Client count: " + Server.instance().clientCount() + "\n" +
				"Game running: " + Server.instance().getGame().isRunning() + "\n" +
				"Registered players: " + Server.instance().getGame().getPlayerCount() + "\n" +
				"Field size: " + Game.Field.width + "x" + Game.Field.height + "\n";
	}
	
	public Map<String, BiFunction<String, ClientHandler, String>> createCommands() {
		Map<String, BiFunction<String, ClientHandler, String>> commands = new HashMap<String, BiFunction<String, ClientHandler, String>>();
		
		commands.put("help", (a, b) -> this.help(a, b));
		commands.put("task", (a, b) -> this.task(a, b));
		commands.put("echo", (a, b) -> this.echo(a, b));
		commands.put("ready", (a, b) -> this.ready(a, b));
		commands.put("start", (a, b) -> this.start(a, b));
		commands.put("status", (a, b) -> this.status(a, b));
		
		return commands;
	}

}
