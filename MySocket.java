import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MySocket {
    Socket mySocket;
    BufferedReader in;
    PrintWriter out;

    //construimos un socket para conectar con un servidor indicado
    public MySocket(String host, int port) {
        try {
            mySocket = new Socket(host, port);
            iniStreams();
        } catch (IOException e) {

        }
    }

    //creamos un MySocket a partir de otro Socket
    public MySocket(Socket s) {
        mySocket = s;
        iniStreams();
    }

    private void iniStreams() {
        try {
            in = new BufferedReader(new InputStreamReader(
                    mySocket.getInputStream()
            ));
            out = new PrintWriter(mySocket.getOutputStream(), true);
        } catch (IOException e) {

        }
    }

    //lee el siguiente String si lo hay, sino devuelve null
    public String readString() {
        try {
            return in.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    //lee el siguiente Int si lo hay, sino devuelve null
    public int readInt() {
        return Integer.parseInt(readString());
    }

    //lee el siguiente Boolean si lo hay, sino devuelve null
    public boolean readBoolean() {
        return Boolean.parseBoolean(readString());
    }

    //escribe el string en el socket
    public void writeString(String s) {
        out.println(s);
    }

    //escribe el int en el socket
    public void writeInt(int i) {
        writeString(Integer.toString(i));
    }

    //cerramos el socket
    public void close() {
        try {
            in.close();
            out.close();
            mySocket.close();
        } catch (IOException e) {

        }
    }

}