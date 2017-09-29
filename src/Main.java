import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame("NumberPuzzle");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600, 439);
        f.setLocationRelativeTo(null);
        f.add(new NumberPuzzleFrame(f));
        f.setVisible(true);
    }
}
