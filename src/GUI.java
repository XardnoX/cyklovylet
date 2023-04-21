import javax.swing.*;
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
    private JScrollBar scrollBar;
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
        jMenu.add(refresh);
        jMenu.add(nacti);
        jMenuBar.add(jMenu);


        setVisible(true);
        setContentPane(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
    }
    public List<Cyklovylet> getFileData(){
        int result = jFileChooser.showOpenDialog(this);
        if (result == JFileChooser.CANCEL_OPTION){
            System.out.println("špatná možnost!");
            return null;
        }
        return scan(jFileChooser.getSelectedFile());
    }
    private static List<Cyklovylet> scan(File file) {
        List<Cyklovylet> list = new ArrayList<>();
        try (Scanner scanner = new Scanner((new BufferedReader(new FileReader(file))))){

            while(scanner.hasNextLine()){

                String[] data = scanner.nextLine().split(SPLITTER);
                int cisla = Integer.parseInt(data[1]);
                LocalDate ld = LocalDate.now();
                list.add(new Cyklovylet(data[0], cisla, ld ));
            }
        }
        catch (IOException e ){
            JOptionPane.showMessageDialog(null, "soubor nelze přečíst");
        }
        return list;
    }

    private void refresh(){
        field.setText("");
        text.setText("");
    }
}


