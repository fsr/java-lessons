import java.util.Map;
import java.util.function.BiFunction;


public class Player extends Client {
	
	public enum Direction {
		LEFT,
		RIGHT,
		STRAIGHT;
	}
	
	private Direction nextStep;
	
	public Player() {
		super();
		nextStep = Direction.STRAIGHT;
	}
	
	public Direction getDirection() {
		Direction tmp;
		synchronized(nextStep) {
			tmp = nextStep;
		}
		return tmp;
	}
	
	public void setDirection(Direction next) {
		synchronized(nextStep) {
			nextStep = next;
		}
	}
	
	public String left(String parameters, ClientHandler caller) {
		setDirection(Direction.LEFT);
		return "Next step left";
	}
	
	public String right(String parameters, ClientHandler caller) {
		setDirection(Direction.RIGHT);
		return "Next step right";
	}
	
	public String neutral(String parameters, ClientHandler caller) {
		setDirection(Direction.STRAIGHT);
		return "Next step straight";
	}
	
	public Map<String, BiFunction<String, ClientHandler, String>> createCommands() {
		Map<String, BiFunction<String, ClientHandler, String>> commands = super.createCommands();
		
		commands.put("left", (a, b) -> this.left(a, b));
		commands.put("right", (a, b) -> this.right(a, b));
		commands.put("neutral", (a, b) -> this.neutral(a, b));
		
		return commands;
	}
}
