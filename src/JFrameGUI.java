// import modules & librarys
import java.util.List;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class JFrameGUI {
    // initialize vars
    private JFrame frame;
    private JPanel panel;
    private JTable table;
    private JTextField searchField;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;

    public JFrameGUI(List<Contacts> data) {
        // create frame
        frame = new JFrame();

        // configure table model
        String[] columnNames = {"ID", "Name", "Surname", "Phone Number"};
        tableModel = new DefaultTableModel(columnNames, 0);
        
        // filling table with content from objects
        for (Contacts obj : data) {
            Object[] rowData = {obj.getId(), obj.getGivenName(), obj.getSurname(), obj.getPhoneNumber()};
            tableModel.addRow(rowData);
        }
        
        // create table, panel and scroll pane
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        // create search text field
        searchField = new JTextField();
        searchField.setColumns(1);
        // handle event listener
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override // instruct compiler to override method in superclass
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable();
            }
        });

        // panel settings
        panel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        
        // add components to panel
        panel.add(searchField, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.SOUTH);

        // frame (window) settings
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Contacts");
        frame.pack();
        frame.setVisible(true);
    }

    private void filterTable() {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        String textInput = searchField.getText(); // extract text from textfield into var
        // condition set to input being not null
        if (textInput.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            // set filter for all rows
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textInput));
        }
    }

    public static void main(String[] args) {
        // setting up db connection
        if (DBConnection.dbConnection() == null) {
            System.exit(0);
        } else {
            // exec function build list from objects
            List<Contacts> dataList = createDataList();

            // build application
            SwingUtilities.invokeLater(() -> {
                new JFrameGUI(dataList);
            });
        }
    }

    public static List<Contacts> createDataList() {
        return List.of(
            new Contacts(1,  "Michael", "Leichtl", "Null"),
            new Contacts(2,  "Max", "Schwaderer", "Null")
        );
    }
}