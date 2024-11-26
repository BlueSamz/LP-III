package act;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InventarioGrafico extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private int[] cantidades = {0, 0, 0}; 

    public InventarioGrafico() {
        setTitle("Administración de Inventario");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new Object[]{"Producto", "Categoría", "Cantidad"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.WEST);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        JTextField txtProducto = new JTextField();
        JComboBox<String> cmbCategoria = new JComboBox<>(new String[]{"Electrónica", "Ropa", "Alimentos"});
        JTextField txtCantidad = new JTextField();
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");

        inputPanel.add(new JLabel("Producto:"));
        inputPanel.add(txtProducto);
        inputPanel.add(new JLabel("Categoría:"));
        inputPanel.add(cmbCategoria);
        inputPanel.add(new JLabel("Cantidad:"));
        inputPanel.add(txtCantidad);
        inputPanel.add(btnAgregar);
        inputPanel.add(btnEliminar);

        add(inputPanel, BorderLayout.SOUTH);

        JPanel graficoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int total = cantidades[0] + cantidades[1] + cantidades[2];
                if (total == 0) return;

                int startAngle = 0;
                int[] angles = new int[3];
                for (int i = 0; i < cantidades.length; i++) {
                    angles[i] = (int) Math.round(cantidades[i] * 360.0 / total);
                }

                g.setColor(Color.BLUE);
                g.fillArc(50, 50, 300, 300, startAngle, angles[0]);
                startAngle += angles[0];

                g.setColor(Color.RED);
                g.fillArc(50, 50, 300, 300, startAngle, angles[1]);
                startAngle += angles[1];

                g.setColor(Color.GREEN);
                g.fillArc(50, 50, 300, 300, startAngle, angles[2]);

                g.setColor(Color.BLACK);
                g.drawString("Electrónica", 400, 100);
                g.setColor(Color.BLUE);
                g.fillRect(370, 90, 20, 20);

                g.setColor(Color.BLACK);
                g.drawString("Ropa", 400, 150);
                g.setColor(Color.RED);
                g.fillRect(370, 140, 20, 20);

                g.setColor(Color.BLACK);
                g.drawString("Alimentos", 400, 200);
                g.setColor(Color.GREEN);
                g.fillRect(370, 190, 20, 20);
            }
        };

        add(graficoPanel, BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> {
            try {
                String producto = txtProducto.getText();
                String categoria = (String) cmbCategoria.getSelectedItem();
                int cantidad = Integer.parseInt(txtCantidad.getText());
                if (!producto.isEmpty() && cantidad > 0) {
                    tableModel.addRow(new Object[]{producto, categoria, cantidad});
                    actualizarGrafico(categoria, cantidad);
                    graficoPanel.repaint();
                } else {
                    JOptionPane.showMessageDialog(this, "Complete todos los campos correctamente.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un número válido.");
            }
        });

        btnEliminar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String categoria = (String) tableModel.getValueAt(selectedRow, 1);
                int cantidad = (int) tableModel.getValueAt(selectedRow, 2);
                tableModel.removeRow(selectedRow);
                actualizarGrafico(categoria, -cantidad);
                graficoPanel.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar.");
            }
        });
    }

    private void actualizarGrafico(String categoria, int cantidad) {
        switch (categoria) {
            case "Electrónica":
                cantidades[0] += cantidad;
                break;
            case "Ropa":
                cantidades[1] += cantidad;
                break;
            case "Alimentos":
                cantidades[2] += cantidad;
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InventarioGrafico().setVisible(true));
    }
}
