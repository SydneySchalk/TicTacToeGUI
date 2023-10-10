import javax.swing.*;

public class TicTacToeRunner {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            TicTacToeFrame frame = new TicTacToeFrame(new JFrame());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}



