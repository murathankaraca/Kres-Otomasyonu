package meltem.services.table_building;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;


import java.util.List;

public class TableBuilder {
    public static void BuildTable(TableView table, List<TableColumn> tableColumns, List<TableRow> tableRows) {
        table.getColumns().addAll(tableColumns);
    }
}
