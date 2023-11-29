import java.io.PrintWriter;
import java.util.LinkedList;

public class BufferCompartilhado {
    private LinkedList<Integer> buffer = new LinkedList<>();
    private int capacidade;
    private PrintWriter output;

    // Construtor para inicializar o buffer com uma capacidade específica e um PrintWriter.
    public BufferCompartilhado(int capacidade, PrintWriter output) {
        this.capacidade = capacidade;
        this.output = output;
    }

    // Método sincronizado para colocar um valor no buffer.
    public synchronized void colocar(int valor) throws InterruptedException {
        // Loop para esperar se o buffer estiver cheio.
        while (buffer.size() == capacidade) {
            wait();
        }
        
        // Adiciona o valor ao buffer.
        buffer.add(valor);
        output.println("Produtor produziu: " + valor);
        
        // Notifica outras threads que podem estar esperando para ler ou escrever no buffer.
        notify();
    }

    // Método sincronizado para pegar/remover um valor do buffer.
    public synchronized int pegar() throws InterruptedException {
        // Loop para esperar se o buffer estiver vazio.
        while (buffer.isEmpty()) {
            wait();
        }
        
        // Remove o primeiro valor do buffer.
        int valor = buffer.removeFirst();
        output.println("Consumidor consumiu: " + valor);
        
        // Notifica outras threads que podem estar esperando para ler ou escrever no buffer.
        notify();
        return valor;
    }
}
