import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Servidor {
    public static void main(String[] args) throws Exception {
        // Cria um servidor de soquete na porta 5000.
        ServerSocket servidor = new ServerSocket(5000);
        System.out.println("Servidor iniciado na porta 5000");

        // Loop infinito para aceitar conexões de clientes.
        while (true) {
            // Aguarda e aceita uma conexão de um cliente.
            Socket socket = servidor.accept();
            // Inicia uma nova thread para lidar com a conexão do cliente.
            new Thread(new ProdutorConsumidorHandler(socket)).start();
        }
    }
}

class ProdutorConsumidorHandler implements Runnable {
    private Socket socket;

    // Construtor para inicializar a conexão de soquete.
    ProdutorConsumidorHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        // Cria um BufferedReader para ler dados do cliente.
        try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {
            
            // Lê a capacidade (presumivelmente, um número) do cliente.
            int capacidade = Integer.parseInt(input.readLine());
            
            // Cria instâncias de buffer compartilhado, produtor e consumidor.
            BufferCompartilhado buffer = new BufferCompartilhado(capacidade, output);
            Produtor produtor = new Produtor(buffer, capacidade);
            Consumidor consumidor = new Consumidor(buffer, capacidade);

            produtor.start();
            consumidor.start();

            // Aguarda até que as threads do produtor e consumidor terminem.
            produtor.join();
            consumidor.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}