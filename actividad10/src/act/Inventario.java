package act;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class Inventario extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private int[] cantidades = {0, 0, 0};

    public Inventario() {
        setTitle("Inventario");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new Object[]{"Producto", "Categoría", "Cantidad"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(3, 1));
        JTextField txtProducto = new JTextField();
        JTextField txtCantidad = new JTextField();

        inputPanel.add(new JLabel("Producto:"));
        inputPanel.add(txtProducto);
        inputPanel.add(new JLabel("Cantidad:"));
        inputPanel.add(txtCantidad);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> {
            try {
                String producto = txtProducto.getText();
                int cantidad = Integer.parseInt(txtCantidad.getText());
                if (!producto.isEmpty() && cantidad > 0) {
                    tableModel.addRow(new Object[]{producto, "General", cantidad});
                    cantidades[0] += cantidad;
                } else {
                    JOptionPane.showMessageDialog(this, "Complete todos los campos correctamente.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un número.");
            }
        });
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar");
            }
        });


        inputPanel.add(btnAgregar);
        inputPanel.add(btnEliminar);
        add(inputPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Inventario().setVisible(true));
    }
}
