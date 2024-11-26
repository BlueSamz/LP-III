package act;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionContactos extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtNombre, txtNumero, txtCorreo;

    public GestionContactos() {
        setTitle("Gestión de Contactos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new Object[]{"Nombre", "Número", "Correo"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        inputPanel.add(txtNombre);
        inputPanel.add(new JLabel("Número:"));
        txtNumero = new JTextField();
        inputPanel.add(txtNumero);
        inputPanel.add(new JLabel("Correo:"));
        txtCorreo = new JTextField();
        inputPanel.add(txtCorreo);

        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");
        inputPanel.add(btnAgregar);
        inputPanel.add(btnEliminar);
        add(inputPanel, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String numero = txtNumero.getText();
            String correo = txtCorreo.getText();
            if (!nombre.isEmpty() && !numero.isEmpty() && !correo.isEmpty()) {
                tableModel.addRow(new Object[]{nombre, numero, correo});
                txtNombre.setText("");
                txtNumero.setText("");
                txtCorreo.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            }
        });

        btnEliminar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un contacto para eliminar");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GestionContactos().setVisible(true));
    }
}

