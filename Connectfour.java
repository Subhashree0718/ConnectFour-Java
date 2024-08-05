import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ConnectFour extends JFrame {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private JButton[][] buttons = new JButton[ROWS][COLS];
    private boolean playerOneTurn = true;
    private boolean gameEnded = false;
    public ConnectFour() {
        setTitle("Connect Four");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(ROWS + 1, COLS));
        for (int row = 0; row < ROWS + 1; row++) {
            for (int col = 0; col < COLS; col++) {
                if (row == 0) {
                    JButton button = new JButton("Drop");
                    button.addActionListener(new DropButtonListener(col));
                    add(button);
                } else {
                    JButton button = new JButton();
                    button.setEnabled(false); 
                    buttons[row - 1][col] = button;
                    add(button);
                } }}}
    private class DropButtonListener implements ActionListener {
        private int col;
        public DropButtonListener(int col) {
            this.col = col;
        }
        public void actionPerformed(ActionEvent e) {
            if (!gameEnded) {
                for (int row = ROWS - 1; row >= 0; row--) {
                    if (!buttons[row][col].isEnabled()) {
                        if (playerOneTurn) {
                            buttons[row][col].setBackground(Color.RED);
                        } else {
                            buttons[row][col].setBackground(Color.YELLOW);
                        }
                        buttons[row][col].setEnabled(true); 
                        checkWin(row, col);
                        playerOneTurn = !playerOneTurn;
                        break;
                    }} }}
        private void checkWin(int row, int col) {
            int count = 1;
            for (int c = col - 1; c >= 0; c--) {
                if (buttons[row][c].isEnabled() && buttons[row][c].getBackground() == buttons[row][col].getBackground()) {
                    count++;
                } else {
                    break;
                } }
            for (int c = col + 1; c < COLS; c++) {
                if (buttons[row][c].isEnabled() && buttons[row][c].getBackground() == buttons[row][col].getBackground()) {
                    count++;
                } else {
                    break;
                }}
            if (count >= 4) {
                gameEnded = true;
                displayWinner();
                return;}
            count = 1;
            for (int r = row - 1; r >= 0; r--) {
                if (buttons[r][col].isEnabled() && buttons[r][col].getBackground() == buttons[row][col].getBackground()) {
                    count++;
                } else {
                    break;}
            }
            for (int r = row + 1; r < ROWS; r++) {
                if (buttons[r][col].isEnabled() && buttons[r][col].getBackground() == buttons[row][col].getBackground()) {
                    count++;
                } else {
                    break;
                }}
            if (count >= 4) {
                gameEnded = true;
                displayWinner();
                return;
            }
            count = 1;
            for (int i = 1; row - i >= 0 && col + i < COLS; i++) {
                if (buttons[row - i][col + i].isEnabled() && buttons[row - i][col + i].getBackground() == buttons[row][col].getBackground()) {
                    count++;
                } else {
                    break;
                }}
            for (int i = 1; row + i < ROWS && col - i >= 0; i++) {
                if (buttons[row + i][col - i].isEnabled() && buttons[row + i][col - i].getBackground() == buttons[row][col].getBackground()) {
                    count++;
                } else {
                    break;
                } }
            if (count >= 4) {
                gameEnded = true;
                displayWinner();
                return;
            }
            count = 1;
            for (int i = 1; row - i >= 0 && col - i >= 0; i++) {
                if (buttons[row - i][col - i].isEnabled() && buttons[row - i][col - i].getBackground() == buttons[row][col].getBackground()) {
                    count++;
                } else {
                    break;
                }}
            for (int i = 1; row + i < ROWS && col + i < COLS; i++) {
                if (buttons[row + i][col + i].isEnabled() && buttons[row + i][col + i].getBackground() == buttons[row][col].getBackground()) {
                    count++;
                } else {
                    break;
                }}
            if (count >= 4) {
                gameEnded = true;
                displayWinner();
            } }
        private void displayWinner() {
            if (playerOneTurn) {
                JOptionPane.showMessageDialog(null, "Player 1 (Red) wins!");
            } else {
                JOptionPane.showMessageDialog(null, "Player 2 (Yellow) wins!");
            } } }
                public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                    new ConnectFour().setVisible(true);
                }
            });
        }
    }
    

