import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class Snake {
	public enum Direction {
		NORTH(0, 1),
		EAST(1, 0),
		SOUTH(0, -1),
		WEST(-1, 0);
		
		int x, y;
		private Direction(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public Direction left() {
			switch(this) {
			case NORTH:
				return Direction.WEST;
			
			case EAST:
				return Direction.NORTH;
				
			case SOUTH:
				return Direction.EAST;
				
			case WEST:
				return Direction.SOUTH;
			}
			return Direction.NORTH;
		}
		
		public Direction right() {
			switch(this) {
			case NORTH:
				return Direction.EAST;
				
			case EAST:
				return Direction.SOUTH;
				
			case SOUTH:
				return Direction.WEST;
				
			case WEST:
				return Direction.NORTH;
			}
			return Direction.NORTH;
		}
		
	}
	
	public static class Point {
		public int x;
		public int y;
		
		public Point() {
			x = y = 0;
		}
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public Point(Point p) {
			this(p.x, p.y);
		}
		
		public void move(Direction d) {
			x += d.getX();
			y += d.getY();
		}
	}

	private Player player;
	private List<Point> body;
	private Direction movement;
	private int id;
	private boolean alive;
	
	public Snake(Point startPos, Direction mv, Player player, int id) {
		body = new LinkedList<Point>();
		body.add(startPos);
		this.player = player;
		this.id = id;
		movement = mv;
		alive = true;
	}
	
	public List<Point> getBody() {
		return body;
	}
	
	public boolean isAlive() {
		return alive;
	}

	public void kill(String msg) {
		if(alive)
			Server.instance().broadcast("Player " + getId() + " died: " + msg);
		alive = false;
	}

	public int getId() {
		return id;
	}
	
	private void resetTurns() {
		player.setDirection(Player.Direction.STRAIGHT);
	}

	public void move(boolean grow) {
		if(player.getDirection() == Player.Direction.LEFT)
			movement = movement.left();
		else if(player.getDirection() == Player.Direction.RIGHT)
			movement = movement.right();
		
		ListIterator<Point> i = body.listIterator();
		
		Point lastPosition = new Point(i.next());
		lastPosition.move(movement);
		i.previous();
		
		while(i.hasNext()) {
			Point tmp1 = i.next();
			Point tmp2 = new Point(tmp1);
			tmp1.x=lastPosition.x;
			tmp1.y=lastPosition.y;
			lastPosition = tmp2;
		}
		
		if(grow) {
			body.add(lastPosition);
		}
		
		resetTurns();
	}
	
}
