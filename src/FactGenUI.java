import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;


public class FactGenUI{

    private FactGenUI() {

        generateFactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    FactScraper theFact = new FactScraper();

                    System.out.println("Retrieving fact image...");
                    //factFetchProgressBar.setString("Retrieving fact image...");
                    factImageLabel.setIcon(new ImageIcon(theFact.getFactImage()));

                    System.out.println("Retrieving fact title...");
                    //factFetchProgressBar.setString("Retrieving fact title...");
                    factTitleTextArea.setText(theFact.getFactTitle());

                    System.out.println("Retrieving fact content...");
                    //factFetchProgressBar.setString("Retrieving fact content...");
                    factContentTextArea.setText(theFact.getFactContent());

                    System.out.println("Retrieving fact sauce...");
                    //factFetchProgressBar.setString("Retrieving fact sauce...");
                    factSauceTextArea.setText(theFact.getFactSauce());

                    //factFetchProgressBar.setString("Done!");

                    // Open the fact sauce in default browser
                    factSauceTextArea.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            try {
                                WebHandler.openURI(factSauceTextArea.getText());
                            } catch (IOException | URISyntaxException except) {
                                showErrorPopup(except.toString());
                            }
                        }
                    });
                }
                catch (RuntimeException | IOException except){
                    showErrorPopup(except.toString());
                }
            }
        });



        // Show the fact image or not
        toggleFactImageCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (toggleFactImageCheckBox.isSelected()) factImageLabel.setVisible(true);
                else factImageLabel.setVisible(false);
            }
        });
    }



    public static void main(String[] args) throws IOException{
        frame = new JFrame("Fact Generator");
        int input = JOptionPane.showConfirmDialog(
                frame,
                "This program requires Internet connection, " +
                "\nand it may take ~5 seconds to get a fact, \nplease continue if you understand the risk");

        switch (input){
            case 0:
                break;
            case 1:
            case 2:
                System.exit(0);
        }


        // Swing JFrame preparation
        frame.setResizable(false);
        frame.setContentPane(new FactGenUI().rootPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 700));
        frame.setIconImage(ImageIO.read(new File("assets/fr-profile-192.jpg"))); // throws IOException
        frame.pack();
        frame.setVisible(true);
    }


    // Spawn a MessageBox containing caught exceptions
    private void showErrorPopup(String exceptionParam) {
        JOptionPane.showMessageDialog(
                frame,
                "Exception thrown: \n" + exceptionParam + "\nPlease make sure that you are connected to the Internet",
                "Error!",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    // Swing Elements declaration
    private static JFrame frame;
    private JPanel rootPanel;
    private JButton generateFactButton;
    private JPanel factImagePanel;
    private JLabel factTextLabel;
    private JLabel factSauceLabel;
    private JLabel factTitleLabel;
    private JLabel factImageLabel;
    private JTextArea factContentTextArea;
    private JTextArea factSauceTextArea;
    private JTextArea factTitleTextArea;
    private JCheckBox toggleFactImageCheckBox;
    private JPanel utamaPanel;
    private JTabbedPane tabbedPane1;
    private JPanel imagePanel;
    private JRadioButton smallImage;
    private JRadioButton mediumImage;
    private JRadioButton largeImage;

}