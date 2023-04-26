import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StringContent;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GUI extends JFrame {
    private JPanel panel;
    private JTextArea text;
    private JTextField field;
    private JButton smazatButton;
    private int i = 1;
    private final JFileChooser jFileChooser = new JFileChooser(".");
    private static final String SPLITTER = ",";

    public static void main(String[] args) {
        new GUI();
    }

    public GUI() {
        JMenuBar jMenuBar = new JMenuBar();
        setJMenuBar(jMenuBar);
        JMenu jMenu = new JMenu("Soubor");
        JMenuItem nacti = new JMenuItem("Načti");
        nacti.addActionListener(e -> getFileData());
        JMenuItem refresh = new JMenuItem("Refresh");
        refresh.addActionListener(e -> refresh());
        smazatButton.addActionListener(e -> del());
        jMenu.add(refresh);
        jMenu.add(nacti);
        jMenuBar.add(jMenu);
      

        setVisible(true);
        setContentPane(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
    }

    public List<Cyklovylet> getFileData() {
        refresh();
        int result = jFileChooser.showOpenDialog(this);
        if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("špatná možnost!");
            return null;
        }
        return scan(jFileChooser.getSelectedFile());
    }

    private List<Cyklovylet> scan(File file) {
        List<Cyklovylet> list = new ArrayList<>();
        list.clear();
        try (Scanner scanner = new Scanner((new BufferedReader(new FileReader(file))))) {

            while (scanner.hasNextLine()) {

                String[] data = scanner.nextLine().split(SPLITTER);
                int cisla = Integer.parseInt(data[1]);
                LocalDate ld = LocalDate.now();
                list.add(new Cyklovylet(data[0], cisla, ld));
                text.append((i++ + ") " + data[0] + " (" + cisla + " km) \n"));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "soubor nelze přečíst");
        }
        return list;
    }

    private void del() {

        try {
            int lineNumbers = text.getLineCount();
            int lineNumber = Integer.parseInt(field.getText());
            if (lineNumbers <= lineNumber) {
                JOptionPane.showMessageDialog(null, "řádek číslo " + field.getText() + " neexistuje!");
            }

            deleteLine(text, lineNumber);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "musíte napsat číslo řádku který chcete smazat");
        }
    }

    private void deleteLine(JTextArea text, int lineNumber) {

        String texts = text.getText();
        String[] lines = texts.split("\n");

        if (lineNumber >= 1 && lineNumber <= lines.length) {
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < lines.length; i++) {
                if (i != lineNumber - 1) {
                    builder.append(lines[i]).append("\n");
                }
            }

            text.setText(builder.toString());
        }
    }


    private void refresh() {
        field.setText("");
        text.setText("");
    }
}


