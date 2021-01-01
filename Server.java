import java.net.Socket;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Server {
    public static void main(String[] args){
        //declaramos el MyServerSocket
        MyServerSocket ss = new MyServerSocket(Integer.parseInt(args[0]));

        RWDictionary clientes = new RWDictionary();


        while (true){
            MySocket s =ss.accept();
            clientes.put(s.getNick(),s);

            //creamos el thread
            new  Thread(){
                public void run(){
                    String str;
                    while((str = s.readString()) != null){
                        s.writeString(str);
                    }
                    s.close();
                }
            }.start();
        }
    }

}
