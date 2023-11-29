import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Cliente {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 5000);
        System.out.println("Conectado ao servidor");

        // Código para interação com o servidor
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        // Envia comando para iniciar
        output.println("iniciar");

        String resposta;
        while ((resposta = input.readLine()) != null) {
            System.out.println("Servidor: " + resposta);
        }

        socket.close();
    }
}
