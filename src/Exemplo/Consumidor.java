public class Consumidor extends Thread {
    private BufferCompartilhado buffer;

    public Consumidor(BufferCompartilhado buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                buffer.pegar(); // Retira um item do buffer
                Thread.sleep(1000); // Simulando tempo de consumo
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
