public class TestXat {

    public static void main(String [] args){
        String [] clients = {"David", "Youssef"};
        for (int i = 0; i < clients.length; i++){
            new Client(clients[i],"localhost",6666);
        }
    }
}
