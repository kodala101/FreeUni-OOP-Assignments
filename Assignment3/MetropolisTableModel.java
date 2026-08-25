import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A TableModel - Manages Metropolis database data.
 * Connects to a MySQL database and performs searching
 * and adding operations.
 */
public class MetropolisTableModel extends AbstractTableModel {
    private String[] columns = {"Metropolis", "Continent", "Population"};
    private List<List<String>> rows;
    private Connection connection;

    /**
     * Initialization of the table model and connection to the MySQL database.
     */
    public MetropolisTableModel() {
        rows = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hw3_db",
                    "root",
                    ""
            );
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the number of columns in the model.
     */
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    /**
     * @return the number of rows in the model.
     */
    @Override
    public int getRowCount() {
        return rows.size();
    }

    /**
     * @param col the index of the column.
     * @return the name of the column.
     */
    @Override
    public String getColumnName(int col) {
        return columns[col];
    }

    /**
     * @param rowIndex the row which value is required of.
     * @param columnIndex the column which value is required of.
     * @return the value at the specific cell.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rows.get(rowIndex).get(columnIndex);
    }

    /**
     * Based on the parameters given by user builds a SQL statement
     * and updates presented rows and columns.
     * @param metropolis the name of the city.
     * @param continent the name of the continent.
     * @param population the population.
     * @param populationOption indicator of what type of search should be accomplished.
     *                         'larger than' or 'less than or equal'.
     * @param matchingOption indicator of if output should be exact match of arguments.
     */
    public void search(String metropolis, String continent, String population, String populationOption, String matchingOption) {
        rows.clear();
        String message = "SELECT * FROM metropolises";
        List<String> queries = new ArrayList<>();
        boolean isExact = matchingOption.equals("Exact Match");

        if (!metropolis.isEmpty()) {
            if (isExact) {
                 queries.add("metropolis = '" + metropolis + "'");
            } else {
                queries.add("metropolis LIKE '%" + metropolis + "%'");
            }
        }

        if (!continent.isEmpty()) {
            if (isExact) {
                queries.add("continent = '" + continent + "'");
            } else {
                queries.add("continent LIKE '%" + continent + "%'");
            }
        }

        if (!population.isEmpty()) {
            if (populationOption.equals("Population Larger Than")) {
                queries.add("population > " + population);
            } else {
                queries.add("population <= " + population);
            }
        }

        if (!queries.isEmpty()) {
           message += " WHERE " + String.join(" AND ", queries);
        }

        try {
            Statement command = connection.createStatement();
            ResultSet result = command.executeQuery(message);

            while (result.next()) {
                List<String> row = new ArrayList<>();
                row.add(result.getString("metropolis"));
                row.add(result.getString("continent"));
                row.add(result.getString("population"));
                rows.add(row);
            }

            fireTableDataChanged();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts a new row into the database and shows added row on the window.
     * @param metropolis the name of the city to be added.
     * @param continent the name of the continent to be added.
     * @param population the population of the city to be added.
     */
    public void add(String metropolis, String continent, String population) {
        String message = "INSERT INTO metropolises (metropolis, continent, population) VALUES ('" +
                metropolis + "', '" + continent + "', " + population + ");";

        try {
            Statement command = connection.createStatement();
            command.executeUpdate(message);
            search(metropolis, continent, population, "", "Exact Match");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}