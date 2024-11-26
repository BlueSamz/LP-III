package act;

import javax.swing.*;
import java.awt.*;

public class GraficoBarras extends JFrame {
    private int[] valores = {50, 70, 30, 90, 60}; 
    private String[] etiquetas = {"LP", "Física", "Redes", "FSI", "Inglés"};

    public GraficoBarras() {
        setTitle("Gráfico de Barras Simple");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int width = getWidth();
                int height = getHeight();
                int barWidth = width / valores.length;

                for (int i = 0; i < valores.length; i++) {
                    int barHeight = (int) ((valores[i] / 100.0) * height);
                    g.setColor(Color.BLUE);
                    g.fillRect(i * barWidth, height - barHeight, barWidth - 10, barHeight);
                    g.setColor(Color.WHITE);
                    g.drawString(etiquetas[i], i * barWidth + 10, height - 5);
                }
            }
        }, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GraficoBarras().setVisible(true));
    }
}
