import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame("NumberPuzzle");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 400);
        f.setLocationRelativeTo(null);
        NumberPuzzleFrame a=new NumberPuzzleFrame(f);
        f.add(a);
        f.setVisible(true);
    }
}
