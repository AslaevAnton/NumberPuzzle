import com.sun.javafx.font.directwrite.RECT;
import sun.plugin2.message.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NumberPuzzleFrame extends JPanel {
    final int RECTSIZE=100;
    JFrame f;


    public NumberPuzzleFrame(JFrame f) {
        this.f=f;
        f.getWidth();
    }

    public void paint(Graphics g) {
        int rectSize=f.getWidth()/4;
        NumberPuzzleRect[] rect = new NumberPuzzleRect[15];
        for (int i = 0; i < 15; i++) {
            rect[i]= new NumberPuzzleRect(i%4*RECTSIZE,i/4*RECTSIZE, rectSize, i+1);
        }
        for (int i = 0; i < 15; i++) {
            rect[i].draw(g);
        }
    }
}
