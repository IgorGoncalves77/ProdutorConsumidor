import java.util.LinkedList;

public class BufferCompartilhado {
    private LinkedList<Integer> buffer = new LinkedList<>();
    private int capacidade;

    public BufferCompartilhado(int capacidade) {
        this.capacidade = capacidade;
    }

    // Método para colocar um item no buffer. É sincronizado para garantir que 
    // apenas uma thread (produtor ou consumidor) possa acessá-lo por vez.
    public synchronized void colocar(int valor) throws InterruptedException {
        // Aguarda se o buffer estiver cheio
        while (buffer.size() == capacidade) {
            wait();
        }
        buffer.add(valor);
        System.out.println("Produtor produziu: " + valor);

        // Notifica outra thread que está esperando (possivelmente um consumidor)
        notify();
    }

    // Método para retirar um item do buffer. Sincronizado por razões semelhantes.
    public synchronized int pegar() throws InterruptedException {
        // Aguarda se o buffer estiver vazio
        while (buffer.isEmpty()) {
            wait();
        }
        int valor = buffer.removeFirst();
        System.out.println("Consumidor consumiu: " + valor);

        // Notifica outra thread que está esperando (possivelmente um produtor)
        notify();
        return valor;
    }
}
