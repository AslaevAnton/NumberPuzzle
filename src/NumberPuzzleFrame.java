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
    int emptyRect=15;
    int shiftInt=0;
    int tempRect=0;
    Timer mainTimer = new Timer(2, this); //Запускет actionPerform каждые 20 милисекунд
    public NumberPuzzleFrame() {
        mainTimer.start();
        addKeyListener(new MyKeyAdaptor());
        setFocusable(true);
        NumberPuzzleClass.setNP(np);
        NumberPuzzleClass.print(np);
        NumberPuzzleClass.setRandomNP(np, 10);

        int r=100;
        for (int i = 0; i < 16; i++) {
            rect[i]= new NumberPuzzleRect((i)%4,(i)/4, r, np[i]);

        }

    }

    public void paint(Graphics g) {
        g=(Graphics2D)g;

        //int r=(int)(Math.min(this.getSize().getHeight(),this.getSize().getWidth())-5)/4;

       // for (int i = 0; i < 15; i++) {
            //rect[i].setXYSize((i)%4,(i)/4);
            //rect[i].setText(np[i]);
        //}

        g.clearRect(0,0,700,600);
        Font font = new Font("Arial", Font.ITALIC, 10);
        for (int i = 0; i < 15; i++) {
            rect[i].draw(g);

            //g.setColor(Color.black);
            //g.setFont(font);
            //g.drawString("x="+rect[i].getX()+" y="+rect[i].getY()+" dx:"+rect[i].getDx()+" dy:"+rect[i].getDy()+"text: "+rect[i].getText(), 450, 120+i*10);
        }

        //g.drawString(Integer.toString(emptyRect), 450, 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(shiftInt>0){
            try {
                rect[tempRect].shift();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            shiftInt--;
        }
        repaint();
        testWin();

    }

    private void testWin() {
        if(NumberPuzzleClass.win(np)){
            JOptionPane.showMessageDialog(null, "Победа!!");
            System.exit(0);
        }
    }


    private class MyKeyAdaptor extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            int tempPos=0;
            if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                tempPos=NumberPuzzleClass.moveIsPossible(0, np);
                for(int i=0; i<16;i++) {
                    if(rect[i].getText().equals(Integer.toString(tempPos)))
                        tempRect = i;
                }
                if ((emptyRect - 4 >=0)) {
                    emptyRect -= 4;
                    rect[tempRect].keyReleased(e);
                    shiftInt=100;
                }
                NumberPuzzleClass.print(np);
            }
            if(e.getKeyCode()==KeyEvent.VK_UP) {
                tempPos=NumberPuzzleClass.moveIsPossible(1, np);
                for(int i=0; i<16;i++) {
                    if(rect[i].getText().equals(Integer.toString(tempPos))) {
                        tempRect = i;
                        //JOptionPane.showMessageDialog(null, rect[i].getText()+" "+tempPos);
                    }
                }
                if ((emptyRect + 4 <= 15)) {
                    //JOptionPane.showMessageDialog(null, rect[tempRect].getText()+" "+tempPos);
                    emptyRect += 4;
                    rect[tempRect].keyReleased(e);
                    shiftInt=100;
                }
                NumberPuzzleClass.print(np);
            }
            if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
                tempPos=NumberPuzzleClass.moveIsPossible(2, np);
                for(int i=0; i<16;i++) {
                    if(rect[i].getText().equals(Integer.toString(tempPos)))
                        tempRect=i;
                }
                if ((emptyRect%4 - 1 >= 0)) {
                    emptyRect --;
                    rect[tempRect].keyReleased(e);
                    shiftInt=100;
                }
                NumberPuzzleClass.print(np);
            }
            if(e.getKeyCode()==KeyEvent.VK_LEFT) {
                tempPos=NumberPuzzleClass.moveIsPossible(3, np);
                for(int i=0; i<16;i++) {
                    if(rect[i].getText().equals(Integer.toString(tempPos)))
                        tempRect=i;
                }
                if (((emptyRect%4 + 1) < 4)) {
                    emptyRect ++;
                    rect[tempRect].keyReleased(e);
                    shiftInt=100;
                }
                NumberPuzzleClass.print(np);
            }
        }
    }
}
