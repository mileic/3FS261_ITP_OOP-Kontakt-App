// import modules & librarys
import java.util.List;
import java.sql.Connection;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class JFrameGui {
    // initialize vars that are globally used
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JTable table;
    private JTextField searchField;
    private NonEditableIdTableModel tableModel;

    // setting up db connection
    static Connection dbConn = DbSetup.dbConnection();

    public JFrameGui(List<Contacts> data) {
        // create frame
        frame = new JFrame();

        // create main panel
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        // create and build the table and button panel using own methods
        confTablePanel(data);
        confButtonPanel();

        // add components to main panel
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // frame (window) settings
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Contacts");
        frame.pack();
        frame.setVisible(true);
    }

    private void confTablePanel(List<Contacts> data) {
        // configure table model
        String[] columnNames = {"ID", "Name", "Surname", "Phone Number"};
        tableModel = new NonEditableIdTableModel(columnNames, 0);

        // filling table with content from objects
        for (Contacts obj : data) {
            Object[] rowData = {obj.getId(), obj.getGivenName(), obj.getSurname(), obj.getPhoneNumber()};
            tableModel.addRow(rowData);
        }

        // create table, panel and scroll pane
        table = new JTable();
        table.setModel(tableModel);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);

        // create the table panel
        tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

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

        // add components to table panel
        tablePanel.add(searchField, BorderLayout.NORTH);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void confButtonPanel() {
        // create the button panel
        buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        // create buttons
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton addRowButton = new JButton("Add New Row");
        JButton removeRowButton = new JButton("Remove Selected Row");

        // add action listeners
        saveButton.addActionListener(e -> saveChangesToDatabase());
        loadButton.addActionListener(e -> loadFromDatabase());
        addRowButton.addActionListener(e -> addNewRow());
        removeRowButton.addActionListener(e -> removeSelectedRow());

        // add buttons to the button panel
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(addRowButton);
        buttonPanel.add(removeRowButton);
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
        // stop cell editing to ensure changes are committed
        if (table.isEditing()) {
            // get cell editor state
            TableCellEditor cellEditor = table.getCellEditor();
            if (cellEditor != null) {
                cellEditor.stopCellEditing(); // stop cell editing
            }
        }

        // count rows
        int rowCount = table.getRowCount();
        // save each row
        for (int i = 0; i < rowCount; i++) {
            String givenName = (String) table.getValueAt(i, 1);
            String surname = (String) table.getValueAt(i, 2);
            String phoneNumber = (String) table.getValueAt(i, 3);

            // query database
            AlterDbData.setContacts(dbConn, givenName, surname, phoneNumber);
        }

        // show success status
        JOptionPane.showMessageDialog(frame,
            "Data saved into local database.",
            "Data Saved",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void loadFromDatabase() {
        AlterDbData.fetchContacts(dbConn);

        // notifies tableModel about change of data -> refresh
        SwingUtilities.invokeLater(() -> {
            tableModel.fireTableDataChanged();
            table.repaint();
        });

        // show success status
        JOptionPane.showMessageDialog(frame,
            "Data successfully fetched from local database.",
            "Data Fetched",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void addNewRow() {
        // get the number of columns
        int columnCount = tableModel.getColumnCount();

        // create an array to hold default values for the new row
        Object[] newRowData = new Object[columnCount];

        // add default values to the array
        newRowData[0] = AlterDbData.getMaxIdFromDatabase(dbConn) + 1;
        for (int i = 1; i < columnCount; i++) {
            newRowData[i] = "Edit";
        }

        // add the new row to the table model
        tableModel.addRow(newRowData);
    }

    private void removeSelectedRow() {
        // get by cursor selected row
        int selectedRow = table.getSelectedRow();

        if (selectedRow != -1) {
            // ask for confirmation
            int confirmation = JOptionPane.showConfirmDialog(frame,
                "Do you want to remove the selected row?",
                "Confirm Removal",
                JOptionPane.YES_NO_OPTION);

            // if user confirms remove row from gui and db
            if (confirmation == JOptionPane.YES_OPTION) {
                // get id for selected row
                int id = (int) table.getValueAt(selectedRow, 0);

                // query database -> remove from db
                AlterDbData.removeContact(dbConn, id);
                tableModel.removeRow(selectedRow); // if selected -1 = none -> remove from gui

                // show success message
                JOptionPane.showMessageDialog(frame,
                "Contact successfully removed.",
                "Data Removed",
                JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame,
                "Please select a row to remove.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        if (dbConn != null) {
            // show success status
            JOptionPane.showMessageDialog(null,
                "Connected to the database.",
                "Connected",
                JOptionPane.INFORMATION_MESSAGE);

            // build application
            SwingUtilities.invokeLater(() -> {
                new JFrameGui(AlterDbData.fetchContacts(dbConn));
            });
        } else {
            // show failure status
            JOptionPane.showMessageDialog(null,
                "Can't establish connection to database.",
                "Connection Failure",
                JOptionPane.INFORMATION_MESSAGE);

            // exit sys
            System.exit(0);
        }
    }
}