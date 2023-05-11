import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GUI extends JFrame {

    private JPanel panel;
    private JTextField field;
    private JTextArea textData;
    private JButton smazButton;
    private List<Cyklovylet> list = new ArrayList<>();
    private JFileChooser jFileChooser = new JFileChooser(".");

    public static void main(String[] args) {
        new GUI();
    }

    public GUI() {
        JMenu jMenu = new JMenu("menu");
        JMenuBar jMenuBar = new JMenuBar();
        JMenuItem load = new JMenuItem("load");
        JMenuItem refresh = new JMenuItem("refresh");
        load.addActionListener(e -> loadData());
        smazButton.addActionListener(e -> delete());
        refresh.addActionListener(e -> refresh());
        jMenu.add(refresh);
        jMenu.add(load);
        jMenuBar.add(jMenu);
        setJMenuBar(jMenuBar);
        setContentPane(panel);
        setVisible(true);
        setBounds(800, 350, 500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private List<Cyklovylet> scan(File file) {

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(jFileChooser.getSelectedFile())))) {
            int i = 1;
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                int delka = Integer.parseInt(data[1].trim());
                LocalDate datum = LocalDate.parse(data[2].trim());
                list.add(new Cyklovylet(data[0], delka, datum));
                textData.append(i++ + ") " + data[0].trim() + " (" + delka + ") km" + "\n");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Nepodařilo se přečíst soubor!");
        }
        return list;
    }

    private List<Cyklovylet> loadData() {
        clear();
        int result = jFileChooser.showOpenDialog(this);
        if (result == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "Soubor nelze přečíst!");
            return null;
        }
        return scan(jFileChooser.getSelectedFile());
    }
    private void refresh(){
        clear();
        scan(jFileChooser.getSelectedFile());
    }
    private void delete() {
        int x = Integer.parseInt(field.getText());
        list.remove(x - 1);
        try (PrintWriter writer = new PrintWriter(new FileWriter(jFileChooser.getSelectedFile()))) {
            list.forEach(cykloVylet -> {
                writer.println(cykloVylet.getVylet() + "," + cykloVylet.getDelka() + "," + cykloVylet.getDatum());
            });
            System.getProperty("line.separator");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void clear() {
        field.setText("");
        textData.setText("");
    }

}
