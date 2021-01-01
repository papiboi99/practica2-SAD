import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerSocket {

    private ServerSocket myServerSocket;

    //hay que especificar el puerto al que hay que concectarse
    public MyServerSocket(int port) {
        try {
            myServerSocket = new ServerSocket(port);

        } catch (IOException e) {

        }
    }

    public MySocket accept() {
        //esperamos a recibir una conexion por parte de un cliente
        try {
            Socket c = myServerSocket.accept();
            return new MySocket(c);
        } catch (IOException e) {
            return null;
        }
    }


}
