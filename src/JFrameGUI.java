// import modules & librarys
import java.util.List;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.sql.SQLException;

public class JFrameGui {
    // initialize vars
    private JFrame frame;
    private JPanel panel;
    private JTable table;
    private JTextField searchField;
    private JScrollPane scrollPane;
    private NonEditableIdTableModel tableModel;

    public JFrameGui(List<Contacts> data) {
        // create frame
        frame = new JFrame();

        // configure table model
        String[] columnNames = {"ID", "Name", "Surname", "Phone Number"};
        tableModel = new NonEditableIdTableModel(columnNames, 0);
        
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

        // create save & add new row button
        JButton saveButton = new JButton("Save");
        JButton addRowButton = new JButton("Add New Row");
        // handle event listener
        saveButton.addActionListener(e -> saveChangesToDatabase());
        addRowButton.addActionListener(e -> addNewRow());

        // panel settings
        panel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        
        // add components to panel
        panel.add(searchField, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(saveButton, BorderLayout.SOUTH);
        panel.add(addRowButton, BorderLayout.SOUTH);

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

    private void saveChangesToDatabase() {
        // count rows
        int rowCount = table.getRowCount();
        // save each row
        for (int i = 0; i < rowCount; i++) {
            int id = (int) table.getValueAt(i, 0);
            String givenName = (String) table.getValueAt(i, 1);
            String surname = (String) table.getValueAt(i, 2);
            String phoneNumber = (String) table.getValueAt(i, 3);
    
            // query database
            //AlterDbData.updateContact(id, givenName, surname, phoneNumber);
        }
    }

    private void addNewRow() {
        // get the number of columns
        int columnCount = tableModel.getColumnCount();
        int rowCount = tableModel.getRowCount();

        // create an array to hold default values for the new row
        Object[] newRowData = new Object[columnCount];

        // add default values to the array
        newRowData[0] = rowCount + 1; 
        for (int i = 1; i < columnCount; i++) {
            newRowData[i] = "Null";
        }

        // add the new row to the table model
        tableModel.addRow(newRowData);
    }

    public static void main(String[] args) {
        // setting up db connection
        if (DbSetup.dbConnection() != null) {
            // show success status
            JOptionPane.showMessageDialog(null, "Connected to the database.");

            // build application
            SwingUtilities.invokeLater(() -> {
                new JFrameGui(AlterDbData.getAllContactsFromDb());
            });
        } else {
            // show failure status
            JOptionPane.showMessageDialog(null, "Can't establish connection to database.");

            // exit sys
            System.exit(0);
        }
    }

    public static List<Contacts> createDataList() {
        return List.of(
            new Contacts(1,  "Michael", "Leichtl", "Null"),
            new Contacts(2,  "Max", "Schwaderer", "Null")
        );
    }
}