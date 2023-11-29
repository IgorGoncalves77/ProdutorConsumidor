public class Produtor extends Thread {
    private BufferCompartilhado buffer;

    public Produtor(BufferCompartilhado buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                buffer.colocar(i); // Coloca um item no buffer
                Thread.sleep(1000); // Simulando tempo de produção
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
