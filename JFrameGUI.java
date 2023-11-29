import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
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
            Contacts[] rowData = {obj.getId(), obj.getGivenName(), obj.getSurname(), obj.getPhoneNumber()};
            tableModel.addRow(rowData);
        }
        
        // create table and scroll pane
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // panel settings
        scrollPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        scrollPane.setLayout(new GridLayout(0, 1));

        // frame (window) settings
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Contacts");
        frame.pack();
        frame.setVisible(true);

    }
    public static void main(String[] args) {
        List<Contacts> dataList = createDataList();
        
        SwingUtilities.invokeLater(() -> {
            new JFrameGUI(dataList);
        });
    }
}
