public class Produtor extends Thread {
    private BufferCompartilhado buffer;
    private int maxProducao;
    
    public Produtor(BufferCompartilhado buffer, int maxProducao) {
        this.buffer = buffer;
        this.maxProducao = maxProducao;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i < maxProducao +1; i++) {
                buffer.colocar(i); // Coloca um item no buffer
                Thread.sleep(500); // Simulando tempo de produção
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
