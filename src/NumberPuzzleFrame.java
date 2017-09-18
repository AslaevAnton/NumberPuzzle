import com.sun.javafx.font.directwrite.RECT;
import sun.plugin2.message.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NumberPuzzleFrame extends JPanel implements ActionListener {
    int[] np = new int[16];
    NumberPuzzleRect[] rect = new NumberPuzzleRect[16];
    Timer mainTimer = new Timer(20, this); //Запускет actionPerform каждые 20 милисекунд
    public NumberPuzzleFrame() {
        mainTimer.start();
        addKeyListener(new MyKeyAdaptor());
        setFocusable(true);
        NumberPuzzleClass.setNP(np);
        NumberPuzzleClass.setRandomNP(np, 10);

        int r=100;
        System.out.println(r);
        for (int i = 0; i < 16; i++) {
            rect[i]= new NumberPuzzleRect((i)%4*r,(i)/4*r, r, np[i]);

        }

    }

    public void paint(Graphics g) {
        int r=(int)(Math.min(this.getSize().getHeight(),this.getSize().getWidth())-5)/4;

        for (int i = 0; i < 16; i++) {
            rect[i].setXYSize((i)%4*r,(i)/4*r, r);
            rect[i].setText(np[i]);
        }


        for (int i = 0; i < 16; i++) {
            rect[i].draw(g);

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*for (int i = 0; i < 16; i++) {
            rect[i].setText(np[i]);
        }*/
        repaint();
    }

    private class MyKeyAdaptor extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                NumberPuzzleClass.moveIsPossible(0, np);
            }
            if(e.getKeyCode()==KeyEvent.VK_UP)
                NumberPuzzleClass.moveIsPossible(1,np);
            if(e.getKeyCode()==KeyEvent.VK_RIGHT)
                NumberPuzzleClass.moveIsPossible(2,np);
            if(e.getKeyCode()==KeyEvent.VK_LEFT)
                NumberPuzzleClass.moveIsPossible(3,np);
        }
    }
}
