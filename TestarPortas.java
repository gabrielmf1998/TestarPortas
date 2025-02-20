import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TestarPortas {
    private static ServerSocket serverSocket;
    private static JFrame frame;
    private static JLabel statusLabel;
    private static JTextField portField;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createUI());
    }

    private static void createUI() {
        if (frame == null) {
            frame = new JFrame("TCP Port Listener");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);

            JPanel panel = new JPanel(new GridLayout(4, 1));
            JLabel instructionLabel = new JLabel("Digite uma porta TCP (acima de 1024):");
            portField = new JTextField();
            JButton startButton = new JButton("Iniciar");
            statusLabel = new JLabel("", SwingConstants.CENTER);

            panel.add(instructionLabel);
            panel.add(portField);
            panel.add(startButton);
            panel.add(statusLabel);

            frame.add(panel);

            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String portText = portField.getText();
                    try {
                        int port = Integer.parseInt(portText);
                        if (port <= 1024) {
                            JOptionPane.showMessageDialog(frame, "Por favor, insira uma porta acima de 1024.", "Erro", JOptionPane.ERROR_MESSAGE);
                        } else {
                            startListening(port);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Por favor, insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }

        frame.setVisible(true);
        statusLabel.setText("");
        portField.setText("");
    }

    private static void startListening(int port) {
        new Thread(() -> {
            try {
                if (serverSocket != null && !serverSocket.isClosed()) {
                    serverSocket.close();
                }
                serverSocket = new ServerSocket(port);
                statusLabel.setText("Aguardando conexão na porta " + port + "...");

                Socket clientSocket = serverSocket.accept();
                statusLabel.setText("Redirecionamento de portas funcionando!");

                int option = JOptionPane.showConfirmDialog(frame, "Redirecionamento de portas funcionando! Deseja testar outra porta?", "Conexão recebida", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    serverSocket.close();
                    SwingUtilities.invokeLater(() -> createUI());
                } else {
                    serverSocket.close();
                    System.exit(0);
                }
            } catch (IOException ex) {
                statusLabel.setText("Erro: " + ex.getMessage());
            }
        }).start();
    }
}
