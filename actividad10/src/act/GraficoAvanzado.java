package act;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GraficoAvanzado extends JFrame {
    private int[] valores = {10, 20, 15, 25, 30, 35, 40};
    private String[] etiquetas = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul"};

    public GraficoAvanzado() {
        setTitle("Gráfico de Líneas A/D");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel graficoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int width = getWidth();
                int height = getHeight();
                int padding = 50;

                g.drawLine(padding, height - padding, width - padding, height - padding); 
                g.drawLine(padding, height - padding, padding, padding); 

                for (int i = 0; i < valores.length - 1; i++) {
                    int x1 = padding + i * (width - 2 * padding) / (valores.length - 1);
                    int y1 = height - padding - valores[i] * (height - 2 * padding) / 50;
                    int x2 = padding + (i + 1) * (width - 2 * padding) / (valores.length - 1);
                    int y2 = height - padding - valores[i + 1] * (height - 2 * padding) / 50;

                    g.setColor(Color.BLUE);
                    g.drawLine(x1, y1, x2, y2);
                }

                for (int i = 0; i < valores.length; i++) {
                    int x = padding + i * (width - 2 * padding) / (valores.length - 1);
                    int y = height - padding - valores[i] * (height - 2 * padding) / 50;
                    g.setColor(Color.RED);
                    g.fillOval(x - 5, y - 5, 10, 10);
                    g.setColor(Color.BLACK);
                    g.drawString(etiquetas[i], x - 10, height - padding + 20);
                }
            }
        };

        add(graficoPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GraficoAvanzado().setVisible(true));
    }
}
