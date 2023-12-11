// import modules & librarys
import javax.swing.table.DefaultTableModel;

public class CustomTableModel extends DefaultTableModel {
    public CustomTableModel(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    // override method to allow editing cells
    @Override
    public boolean isCellEditable(int row, int column) {
        // make the ID column non-editable
        return column != 0;
    }

    // override method to allow adding new rows
    @Override
    public void addRow(Object[] rowData) {
        super.addRow(rowData);
    }
}