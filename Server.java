
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Server {
    private static final int PORT = 6666;

    private static final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private static final Lock r = rwl.readLock();
    private static final Lock w = rwl.writeLock();

    private static Map<String, MySocket> clients = new TreeMap<String, MySocket>();

    public static void main(String[] args){
        MyServerSocket listener = new MyServerSocket(PORT);

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        System.out.println(dateFormat.format(new Date())+" [SERVER] Waiting for a connection...");

        while (true){
            MySocket client = listener.accept();
            client.setNick(client.readString());
            System.out.println(dateFormat.format(new Date())+" [SERVER] " + client.getNick() + " has just connected!");

            Thread clientHandlerThread = new Thread(() -> {
                String myNick = client.getNick();
                putClient(myNick, client);

                String line;
                while((line = client.readString()) != null){
                    sendBroadcast(line, myNick);
                    System.out.println(dateFormat.format(new Date())+" [SERVER] " + myNick + " says: \""+line+"\"");
                }
                System.out.println(dateFormat.format(new Date())+" [SERVER] " + myNick + " has just left");
                removeClient(myNick);
                client.close();
            });
            clientHandlerThread.start();

        }

    }

    public static void putClient(String nick, MySocket client){
        w.lock();
        try {
            clients.put(nick, client);
        }
        finally { w.unlock(); }
    }

    public static void removeClient(String nick){
        w.lock();
        try { clients.remove(nick); }
        finally { w.unlock(); }
    }

    public static void sendBroadcast(String line, String myNick){
        r.lock();
        try {
            for(Map.Entry<String,MySocket> entry : clients.entrySet()) {
                String nick = entry.getKey();
                if (nick != myNick){
                    DateFormat dateFormat = new SimpleDateFormat("HH:mm");
                    entry.getValue().writeString(dateFormat.format(new Date())+" >> ["+myNick+"]: "+line);
                }
            }
        }
        finally { r.unlock(); }
    }

}
