import java.io.PrintWriter;
import java.util.LinkedList;

public class BufferCompartilhado {
    private LinkedList<Integer> buffer = new LinkedList<>();
    private int capacidade;
    private PrintWriter output;

    public BufferCompartilhado(int capacidade, PrintWriter output) {
        this.capacidade = capacidade;
        this.output = output;
    }

    public synchronized void colocar(int valor) throws InterruptedException {
        while (buffer.size() == capacidade) {
            wait();
        }
        buffer.add(valor);
        output.println("Produtor produziu: " + valor);
        notify();
    }

    public synchronized int pegar() throws InterruptedException {
        while (buffer.isEmpty()) {
            wait();
        }
        int valor = buffer.removeFirst();
        output.println("Consumidor consumiu: " + valor);
        notify();
        return valor;
    }
}
