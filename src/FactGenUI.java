import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.*;

import FactScraper.FactScraper;
import WebHandler.WebHandler;
import jdk.nashorn.internal.scripts.JO;

public class FactGenUI {

    public FactGenUI() {

        generateFact.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                FactScraper theFact = null;
                String exceptionVal = "";
                factImageLabel.setText("Please Wait...");
                try {
                    theFact = new FactScraper();
                    factImageLabel.setText(" ");
                    factImageLabel.setIcon(new ImageIcon(theFact.getFactImage()));
                    textArea3.setText(theFact.getFactTitle());
                    textArea1.setText(theFact.getFactContent());
                    textArea2.setText(theFact.getFactSauce());
                    generateFact.setText("Generate Fact");
                }
                catch (IOException except){
                    exceptionVal = except.getLocalizedMessage();
                    factTitleLabel.setText(exceptionVal);
                    factTextLabel.setText(exceptionVal);
                    factSauceLabel.setText(exceptionVal);
                }

            }
        });
        textArea2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Length: "+textArea2.getText().length());
                WebHandler.openURI(textArea2.getText());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("FactGenUI by Yoffa");
        int input = JOptionPane.showConfirmDialog(null, "This program requires Internet connection, " +
                "\nand it may take ~5 seconds to get a fact, \nplease continue if you understand the risk");

        switch (input){
            case 0:
                break;
            case 1:
                System.exit(0);
            case 2:
                System.exit(0);
        }

        frame.setResizable(false);
        frame.setContentPane(new FactGenUI().rootPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 700));
        frame.pack();
        frame.setVisible(true);

    }

    private JPanel rootPanel;
    private JButton generateFact;
    private JPanel factImagePanel;
    private JLabel factTextLabel;
    private JLabel factSauceLabel;
    private JLabel factTitleLabel;
    private JLabel factImageLabel;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea textArea3;
}
