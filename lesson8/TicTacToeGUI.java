package homework8;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class TicTacToeGUI extends JFrame {

    private static TicTacToe ticTacToe;
    private static String iconFilename = "./src/homework8/img/0.png";
    private static char res;

    public TicTacToeGUI(int row) {
        ticTacToe = new TicTacToe(row);

        setSize(400, 400);
        setLocation(500, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton[] jButtons = new JButton[row * row];

        JPanel ticTacToePanel = new JPanel(new GridLayout(row, row));

        for (int i = 0; i < jButtons.length; i++) {
            jButtons[i] = new MyJButton(i / row, i % row);

            jButtons[i].addMouseListener(new CustomMouseListener());

            ticTacToePanel.add(jButtons[i]);
        }

        add(ticTacToePanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static char getRes() { return res; }

    class CustomMouseListener implements MouseListener {

        public void mouseClicked(MouseEvent e) {
            MyJButton button = (MyJButton) e.getSource();

            if(button.getIcon() == null) {
                Dimension size = button.getSize();
                Insets insets = button.getInsets();

                size.width -= insets.left + insets.right;
                size.height -= insets.top + insets.bottom;

                if (size.width > size.height) {
                    size.width = -1;
                } else {
                    size.height = -1;
                }

                Image scaled = null;
                try {
                    scaled = ImageIO.read(new File(iconFilename)).getScaledInstance(size.width,
                            size.height, Image.SCALE_SMOOTH);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                button.setIcon(new ImageIcon(scaled));

                char playerSymb = iconFilename.charAt(iconFilename.length() - 5);

                int row = button.getGridx();
                int column = button.getGridy();

                ticTacToe.setPosition(row, column);
                res = ticTacToe.playRound(playerSymb);

                if(res == 'X' || res == '0') {
                    System.out.println("\nПобедил Игрок " + res + "!");
                    System.exit(0);
                } else if(res == 1) {
                    System.out.println("\nНичья!");
                    System.exit(0);
                }

                iconFilename = iconFilename.equals("./src/homework8/img/0.png") ? "./src/homework8/" +
                        "img/X.png" : "./src/homework8/img/0.png";
            }
        }

        public void mouseEntered(MouseEvent e) {
            JButton button = (JButton) e.getSource();
        }

        public void mouseExited(MouseEvent e) {
            JButton button = (JButton) e.getSource();
        }

        public void mousePressed(MouseEvent e) {
            JButton button = (JButton) e.getSource();
        }

        public void mouseReleased(MouseEvent e) {
            JButton button = (JButton) e.getSource();
        }
    }
}
