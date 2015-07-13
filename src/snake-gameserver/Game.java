import java.util.LinkedList;
import java.util.List;


public class Game extends Thread {
	public class Field {
		public static final int height = 10;
		public static final int width = 10;

		public class Pixel {
			public int playerID = -1;
			public boolean head = false;

			public char toChar() {
				if(playerID == -1)
					return '.';
				if(head)
					return Integer.toString(playerID).charAt(0);
				return 'X';
			}
		}

		private Pixel[][] pixel;

		public Field() {
			pixel = new Pixel[width][height];
			for(int x = 0; x < width; x++) {
				for(int y = 0; y < width; y++) {
					pixel[x][y] = new Pixel();
				}
			}
		}

		/**
		 * creates the field
		 */
		public void create() {
			// Render all the body parts
			for(Snake i : snakes) {
				boolean head = true;
				for(Snake.Point p : i.getBody()) {
					if(head)
						head = false;
					else {
						try {
							pixel[p.x][p.y].playerID = i.getId();
							pixel[p.x][p.y].head = false;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				}
			}

			// Do this twice so a frontal collision leads in the death of both snakes
			//for(int count=0; count<1; count++) {
				// Try to place the heads
				for(Snake i : snakes) {

					// Check if snake crashed a wall
					Snake.Point head = i.getBody().get(0);
					if(head.x < 0 || head.x >= width ||
							head.y < 0 || head.y >= height) {
						i.kill("He crashed into a wall");
						continue;
					}

					// Try to render head
					if(pixel[head.x][head.y].playerID != -1) {
						// Crashed into another snake
						if(pixel[head.x][head.y].playerID != i.getId()) {
							i.kill("He crashed into the snake of player " + pixel[head.x][head.y].playerID);
							// If crashed into the head, the other snake has to die aswell
							if(pixel[head.x][head.y].head) {
								for(Snake j : snakes) {
									if(j.getId() == pixel[head.x][head.y].playerID)
										j.kill("He crashed into the snake of player " + i.getId());
								}
							}
							
							pixel[head.x][head.y].playerID = i.getId();
							pixel[head.x][head.y].head = true;
						} else if(!pixel[head.x][head.y].head) {
							i.kill("He crashed into himself");
							continue;
						}
					}
					else {
						pixel[head.x][head.y].playerID = i.getId();
						pixel[head.x][head.y].head = true;
					}
				}
			//}
		}

		/**
		 * renders the field
		 */
		public String toString() {
			StringBuilder str = new StringBuilder();
			str.append("fieldbegin\n");
			for(int y = height-1; y >= 0; y--) {
				for(int x = 0; x < width; x++) {
					str.append(pixel[x][y].toChar());
				}
				str.append('\n');
			}
			str.append("fieldend\n");

			return str.toString();
		}
	}

	private volatile boolean running;
	private volatile int playerCount;
	private int sleepcount;
	
	private Field field;

	private List<Snake> snakes;

	public Game() {
		running = false;
		playerCount = 0;
		snakes = new LinkedList<Snake>();
		
	}

	/**
	 * Main method of the whole thing
	 */
	private void gameStep(boolean grow) {
		for(Snake i : snakes) {
			i.move(grow);
		}

		field = new Field();
		field.create();
		Server.instance().broadcast(field.toString());
		
		int numPlayersAlive = 0;
		int lastAlivePlayer = -1;
		for(Snake i : snakes) {
			if(i.isAlive()) {
				numPlayersAlive++;
				lastAlivePlayer = i.getId();
			}
		}
		
		// Victory
		if(playerCount > 1 && numPlayersAlive == 1) {
			Server.instance().broadcast("Victory - Player " + lastAlivePlayer + " has won!");
			Server.instance().resetGame();
			System.out.println("Victory - Player " + lastAlivePlayer + " has won!");
			close();
			
		} else if(numPlayersAlive == 0) {
			Server.instance().broadcast("Draw - noone has won");
			Server.instance().resetGame();
			System.out.println("Draw - noone has won");
			close();
		}
	}


	/**
	 * To be called from outside
	 * 
	 * @param player
	 * @return player-ID or -1
	 */
	public synchronized int registerPlayer(Player player) {
		if(playerCount >= 4 || playerCount < 0 || running)
			return -1;

		switch(playerCount) {
		case 0:
			// Player 0 starts top right and goes left
			snakes.add(new Snake(new Snake.Point(Field.width-1, Field.height-1), Snake.Direction.WEST, player, playerCount));
			break;
		case 1:
			// Player 1 starts bottom left and goes right
			snakes.add(new Snake(new Snake.Point(0, 0), Snake.Direction.EAST, player, playerCount));
			break;
		case 2:
			// Player 2 starts bottom right and goes top
			snakes.add(new Snake(new Snake.Point(Field.width-1, 0), Snake.Direction.NORTH, player, playerCount));
			break;
		case 3:
			// Player 3 starts top left and goes down
			snakes.add(new Snake(new Snake.Point(0, Field.height-1), Snake.Direction.SOUTH, player, playerCount));
			break;
		}

		return playerCount++;
	}

	public synchronized boolean startGame(int sleepcount) {
		if(!running && (playerCount >= 1)) {
			running = true;
			start();
			this.sleepcount = sleepcount;
			System.out.println("Starting new game");
			return true;
		}
		return false;
	}

	public void close() {
		running = false;
	}

	public void run() {
		running = true;
		int stepCount = 0;
		field = new Field();
		field.create();
		Server.instance().broadcast(field.toString());
		while(running) {
			stepCount++;
			try {
				sleep(sleepcount);
			} catch (InterruptedException e) {
				e.printStackTrace();
				continue;
			}
			gameStep(stepCount%5 == 0);
		}
	}

	public boolean isRunning() {
		return running;
	}

	public int getPlayerCount() {
		return playerCount;
	}
}
