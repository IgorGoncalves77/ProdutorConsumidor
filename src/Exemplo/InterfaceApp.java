package Exemplo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;

public class InterfaceApp extends javax.swing.JFrame {

    private JTextField textCapacidade;
    private JTextArea textArea;
    
    public InterfaceApp() {
        initComponents();
        setTitle("Produtor-Consumidor");
        setSize(400, 300);
        setLayout(new FlowLayout());

        JLabel labelCapacidade = new JLabel("Capacidade do Buffer:");
        textCapacidade = new JTextField(10);
        add(labelCapacidade);
        add(textCapacidade);

        JButton button = new JButton("Iniciar Produtor-Consumidor");
        add(button);

        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int capacidade = Integer.parseInt(textCapacidade.getText());
                    conectarESolicitar(capacidade);
                } catch (NumberFormatException ex) {
                    textArea.append("Por favor, insira um número válido para a capacidade.\n");
                }
            }
        });
    }

    private void conectarESolicitar(int capacidade) {
        try (Socket socket = new Socket("localhost", 5000);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            out.println(capacidade);
            textArea.append("Produtor-Consumidor iniciado com capacidade: " + capacidade + "\n");
        } catch (Exception ex) {
            textArea.append("Não foi possível conectar ao servidor: " + ex.getMessage() + "\n");
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new InterfaceApp().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
