import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JFrameGUI {
    public JFrameGUI(List<Contacts> data) {
        // create frame
        JFrame frame = new JFrame();

        // configure table model
        String[] columnNames = {"ID", "Name", "Surname", "Phone Number"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        
        // filling table with content
        for (Contacts obj : data) {
            Object[] rowData = {obj.getId(), obj.getGivenName(), obj.getSurname(), obj.getPhoneNumber()};
            tableModel.addRow(rowData);
        }
        
        // create table, panel and scroll pane
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel(new GridLayout(1, 1));

        // panel settings
        panel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        panel.add(scrollPane);

        // frame (window) settings
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Contacts");
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        List<Contacts> dataList = createDataList();
        
        // build application
        SwingUtilities.invokeLater(() -> {
            new JFrameGUI(dataList);
        });
    }

    public static List<Contacts> createDataList() {
        return List.of(
            new Contacts(1,  "Michael", "Leichtl", "Null"),
            new Contacts(2,  "Max", "Schwaderer", "Null")
        );
    }
}