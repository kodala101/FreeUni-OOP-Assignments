import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class MetropolisFrame extends JFrame {
    private JButton addButton;
    private JButton searchButton;
    private JTable table;
    private JTextField metropolis, continent, population;
    private JComboBox populationCombo;
    private JComboBox matchingCombo;
    private MetropolisTableModel model;

    public MetropolisFrame() {
        super("Metropolis Viewer");

        model = new MetropolisTableModel();
        table = new JTable(model);

        metropolis = new JTextField(10);
        continent = new JTextField(10);
        population = new JTextField(10);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        topPanel.add(new JLabel("Metropolis: "));
        topPanel.add(metropolis);
        topPanel.add(new JLabel("Continent: "));
        topPanel.add(continent);
        topPanel.add(new JLabel("Population: "));
        topPanel.add(population);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(0, 1, 10, 10));

        JPanel rightPanelWrapper = new JPanel(new FlowLayout());
        rightPanelWrapper.add(rightPanel);

        addButton = new JButton("Add");
        searchButton = new JButton("Search");

        String[] popOptions = {"Population Larger Than", "Population Smaller Than (or equal)"};
        populationCombo = new JComboBox<String>(popOptions);
        String[] matchOptions = {"Exact Match", "Partial Match"};
        matchingCombo = new JComboBox<String>(matchOptions);

        rightPanel.add(addButton);
        rightPanel.add(searchButton);
        rightPanel.add(populationCombo);
        rightPanel.add(matchingCombo);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(rightPanelWrapper, BorderLayout.EAST);

        actions();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void actions() {
        String city = metropolis.getText().trim();
        String cont = continent.getText().trim();
        String pop = population.getText().trim();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer.parseInt(pop);
                    if (!(city.isEmpty() || cont.isEmpty() || pop.isEmpty()))  model.add(city, cont, pop);
                } catch (NumberFormatException ignored) {}
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!population.getText().isEmpty()) {
                        Integer.parseInt(population.getText());
                    }
                    model.search(city, cont, pop,
                            (String) populationCombo.getSelectedItem(), (String) matchingCombo.getSelectedItem());
                } catch (NumberFormatException ignored) {}
            }
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }

        MetropolisFrame mFrame = new MetropolisFrame();
    }
}
