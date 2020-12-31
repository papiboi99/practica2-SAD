import java.io.*;

public class Client {

    private static String nick;
    private static String host;
    private static int port;

    public Client (String nick, String host, int port){
        this.nick = nick;
        this.host = host;
        this.port = port;
    }

    public static void main (String [] args){
        MySocket s = new MySocket(host, port);

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        Thread inputThread = new Thread(() -> {
            try{
                String line;
                while ((line = input.readLine()) != null){
                    s.writeString(line);
                }
            }catch (IOException e){
                System.out.println("Couldn't read from Keyboard");
            }
        });
        inputThread.run();

        Thread outputThread = new Thread(() -> {
            while (s.readBoolean()){
                System.out.println(s.readString());
            }
        });
        outputThread.run();
    }
}
