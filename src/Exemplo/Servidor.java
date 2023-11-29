import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Servidor {
    public static void main(String[] args) throws Exception {
        ServerSocket servidor = new ServerSocket(5000);
        System.out.println("Servidor iniciado na porta 5000");

        while (true) {
            Socket socket = servidor.accept();
            new Thread(new ProdutorConsumidorHandler(socket)).start();
        }
    }
}

class ProdutorConsumidorHandler implements Runnable {
    private Socket socket;

    ProdutorConsumidorHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String line = input.readLine();
            int capacidade = Integer.parseInt(line);

            BufferCompartilhado buffer = new BufferCompartilhado(capacidade);
            Produtor produtor = new Produtor(buffer);
            Consumidor consumidor = new Consumidor(buffer);

            produtor.start();
            consumidor.start();

            produtor.join();
            consumidor.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
