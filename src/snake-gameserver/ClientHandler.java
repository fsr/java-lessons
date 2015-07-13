import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Map;
import java.util.function.BiFunction;


public class ClientHandler extends Thread {
	private Map<String, BiFunction<String, ClientHandler, String>> commands;
	private Socket sock;
	private BufferedWriter out;
	private BufferedReader in;
	private volatile boolean running;

	private ClientHandler(Socket sock, BufferedWriter out, BufferedReader in) {
		super();
		this.sock = sock;
		this.out = out;
		this.in = in;
	}

	// FactoryMethod-Designpattern
	public static ClientHandler factory(Socket sock) throws IOException {
		return new ClientHandler(sock, 
				new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())),
				new BufferedReader(new InputStreamReader(sock.getInputStream())));
	}

	/**
	 * Will crash this clientHandler down. Call if client does bullshit
	 */
	public void close() {
		running=false;
		if(in != null) {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			in = null;
		}

		if(out != null) {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out = null;
		}

		if(sock != null && !sock.isClosed()) {
			try {
				sock.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sock = null;
		}
	}

	// A command is built in the way of "<command> <parameters>"
	private void parseCommand(String line) {
		String command = "";
		int i=0;
		for(; i<line.length(); i++) {
			if(line.charAt(i) == ' ')
				break;
			command += line.charAt(i);
		}

		String parameter = "";
		if(!command.equals(line))
			parameter = line.substring(i+1);

		BiFunction<String, ClientHandler, String> tmp = commands.get(command);
		if(tmp == null) {
			send("Invalid command, disconnecting");
			close();
		} else {
			send(tmp.apply(parameter, this));
		}

	}

	public void setCommands(Map<String, BiFunction<String, ClientHandler, String>> commands) {
		this.commands = commands;
	}

	public void send(String msg) {
		if(msg == null || msg.isEmpty() || out == null)
			return;

		if(msg.charAt(msg.length()-1) != '\n')
			msg += '\n';

		try {
			synchronized(out) {
				out.write(msg);
				out.flush();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
		} 

	}

	public boolean isRunning() {
		return running;
	}

	/**
	 *  Reacting on user input
	 */
	public void run() {
		running = true;
		try {
			while(running) {
				String line = in.readLine();
				if(!line.isEmpty())
					parseCommand(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
	}

}
