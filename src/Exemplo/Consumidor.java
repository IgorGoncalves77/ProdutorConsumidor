public class Consumidor extends Thread {
    private BufferCompartilhado buffer;
    private int maxConsumo;
    
    public Consumidor(BufferCompartilhado buffer, int maxConsumo) {
        this.buffer = buffer;
        this.maxConsumo = maxConsumo;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i < maxConsumo +1; i++) {
                buffer.pegar(); // Retira um item do buffer
                Thread.sleep(1000); // Simulando tempo de consumo
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
