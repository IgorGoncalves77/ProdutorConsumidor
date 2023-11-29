public class Principal {
    public static void main(String[] args) {
        BufferCompartilhado buffer = new BufferCompartilhado(5);

        // Criação das threads de produtor e consumidor
        Produtor produtor = new Produtor(buffer);
        Consumidor consumidor = new Consumidor(buffer);

        // Iniciando as threads
        produtor.start();
        consumidor.start();
    }
}
