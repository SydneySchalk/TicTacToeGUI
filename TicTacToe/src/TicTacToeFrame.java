import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;



public class TicTacToeFrame extends JFrame {
    private final JButton[][] buttons;
    private String currentPlayer;
    private int moves;

    public TicTacToeFrame(JFrame frame) {
        setTitle("Tic Tac Toe");
        setPreferredSize(new Dimension(400, 400));
        pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) (screenSize.getWidth() / 2 - getWidth() / 2);
        int centerY = (int) (screenSize.getHeight() / 2 - getHeight() / 2);

        setLocation(centerX, centerY);

        setLayout(new GridLayout(4, 3));
        buttons = new JButton[3][3];
        currentPlayer = "X";
        moves = 0;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 48));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].addActionListener(new ButtonClickListener(row, col));
                add(buttons[row][col]);
            }
        }

        JPanel blankPanel = new JPanel();
        add(blankPanel);


        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, "Do you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        add(quitButton);
    }

    private void playAgainPrompt() {
        int result = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
    }

    private void checkForWin() {
        if (isColWin(currentPlayer) || isRowWin(currentPlayer) || isDiagonalWin(currentPlayer)) {
            announceWinner(currentPlayer);
        }
    }

    private boolean isColWin(String player) {
        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(player) &&
                    buttons[1][col].getText().equals(player) &&
                    buttons[2][col].getText().equals(player)) {
                return true;
            }
        }
        return false;
    }

    private boolean isRowWin(String player) {
        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(player) &&
                    buttons[row][1].getText().equals(player) &&
                    buttons[row][2].getText().equals(player)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDiagonalWin(String player) {
        return (buttons[0][0].getText().equals(player) && buttons[1][1].getText().equals(player) && buttons[2][2].getText().equals(player)) ||
                (buttons[0][2].getText().equals(player) && buttons[1][1].getText().equals(player) && buttons[2][0].getText().equals(player));
    }

    private void announceWinner(String player) {
        JOptionPane.showMessageDialog(this, "Player " + player + " wins!", "Winner", JOptionPane.INFORMATION_MESSAGE);
        playAgainPrompt();
    }

    // Add a new method to check for a tie
    // Modify the isTie method to handle ties regardless of the board being fully filled
    private boolean isTie() {
        if (moves >= 9) {
            return true; // If there have been 9 moves and there is no winner, it's a tie
        }

            boolean xFlag = false;
            boolean oFlag = false;

            // Check all 8 win vectors for an X and O so no win is possible
            // Check for row ties
            for (int row = 0; row < 3; row++) {
                if (buttons[row][0].getText().equals("X") ||
                        buttons[row][1].getText().equals("X") ||
                        buttons[row][2].getText().equals("X"))
                {
                    xFlag = true; // there is an X in this row
                }
                if (buttons[row][0].getText().equals("O") ||
                        buttons[row][1].getText().equals("O") ||
                        buttons[row][2].getText().equals("O"))
                {
                    oFlag = true; // there is an O in this row
                }

                if (!(xFlag && oFlag)) {
                    return false; // No tie can still have a row win
                }

                xFlag = oFlag = false;
            }

            // Now scan the columns
            for (int col = 0; col < 3; col++) {
                if (buttons[0][col].getText().equals("X") ||
                        buttons[1][col].getText().equals("X") ||
                        buttons[2][col].getText().equals("X"))
                {
                    xFlag = true; // there is an X in this col
                }
                if (buttons[0][col].getText().equals("O") ||
                        buttons[1][col].getText().equals("O") ||
                        buttons[2][col].getText().equals("O"))
                {
                    oFlag = true; // there is an O in this col
                }

                if (!(xFlag && oFlag)) {
                    return false; // No tie can still have a col win
                }
            }

            // Now check for the diagonals
            xFlag = oFlag = false;

            if (buttons[0][0].getText().equals("X") ||
                    buttons[1][1].getText().equals("X") ||
                    buttons[2][2].getText().equals("X"))
            {
                xFlag = true;
            }
            if (buttons[0][0].getText().equals("O") ||
                    buttons[1][1].getText().equals("O") ||
                    buttons[2][2].getText().equals("O"))
            {
                oFlag = true;
            }
            if (!(xFlag && oFlag)) {
                return false; // No tie can still have a diag win
            }

            xFlag = oFlag = false;

            if (buttons[0][2].getText().equals("X") ||
                    buttons[1][1].getText().equals("X") ||
                    buttons[2][0].getText().equals("X")) {
                xFlag = true;
            }
            if (buttons[0][2].getText().equals("O") ||
                    buttons[1][1].getText().equals("O") ||
                    buttons[2][0].getText().equals("O"))
            {
                oFlag = true;
            }
        return xFlag && oFlag; // No tie can still have a diag win

            // Checked every vector, so we know we have a tie
    }
    private void checkForTie() {
        if (isTie()) {
            JOptionPane.showMessageDialog(this, "It's a tie!", "Tie", JOptionPane.INFORMATION_MESSAGE);
            playAgainPrompt();
        }
    }

    private void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        currentPlayer = "X";
        moves = 0;
    }

     class ButtonClickListener implements ActionListener {
        private final int row;
        private final int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void actionPerformed(ActionEvent e) {
            if (buttons[row][col].getText().isEmpty()) {
                buttons[row][col].setText(currentPlayer);
                moves++;
                checkForWin();
                checkForTie();
                switchPlayer();
            } else {
                JOptionPane.showMessageDialog(TicTacToeFrame.this, "Invalid move! Try again.", "Invalid Move", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}


