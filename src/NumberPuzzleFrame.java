import com.sun.javafx.font.directwrite.RECT;
import sun.plugin2.message.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NumberPuzzleFrame extends JPanel implements ActionListener, MouseListener {
    int[] np = new int[16];
    NumberPuzzleRect[] rect = new NumberPuzzleRect[16];
    int emptyRect=15;
    int shiftInt=0;
    int tempRect=0;
    Timer mainTimer = new Timer(1, this); //Запускет actionPerform каждые 20 милисекунд
    public NumberPuzzleFrame() {
        mainTimer.start();
        addMouseListener(this);
        addKeyListener(new MyKeyAdaptor());
        setFocusable(true);
        //NumberPuzzleClass.setNP(np);
        NumberPuzzleClass.setRandomNP(np, 10);
        NumberPuzzleClass.print(np);

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

    @Override
    public void mouseClicked(MouseEvent e) {
        int resultMoveIsPossible=-1;
        JOptionPane.showMessageDialog(null,tempRect);
        for (int i = 0; i < 15; i++) {
            if(np[i]!=99)
                resultMoveIsPossible=NumberPuzzleClass.moveIsPossible(np, np[i]);
            //System.out.println(i+" "+rect[i].isMouseClicked(e.getPoint())+" "+rect[i].getText()+" "+np[i]+" "+resultMoveIsPossible);
            if(rect[i].isMouseClicked(e.getPoint())&&resultMoveIsPossible>=0) {
                tempRect = i;
                NumberPuzzleClass.moveIsPossible(resultMoveIsPossible, np);
                if(resultMoveIsPossible==KeyEvent.VK_DOWN&&emptyRect - 4 >=0){
                    emptyRect -= 4;
                    rect[i].keyReleased(resultMoveIsPossible);
                    shiftInt=100;
                    //JOptionPane.showMessageDialog(null, rect[i].getText());
                    System.out.println("down "+np[i]+" "+rect[i].getText());
                }
                if(resultMoveIsPossible==KeyEvent.VK_UP&&emptyRect + 4 <= 15){
                    emptyRect += 4;
                    rect[i].keyReleased(resultMoveIsPossible);
                    shiftInt=100;
                    System.out.println("up "+tempRect+" "+np[i]+" "+rect[i].getText());
                }
                if(resultMoveIsPossible==KeyEvent.VK_LEFT&&emptyRect%4 + 1 < 4){
                    emptyRect ++;
                    rect[i].keyReleased(resultMoveIsPossible);
                    shiftInt=100;
                    System.out.println("left");
                }
                if(resultMoveIsPossible==KeyEvent.VK_RIGHT&&emptyRect%4 - 1 >= 0){
                    emptyRect --;
                    rect[i].keyReleased(resultMoveIsPossible);
                    shiftInt=100;
                    System.out.println("right");
                }
                NumberPuzzleClass.print(np);
                print(rect);
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }


    private class MyKeyAdaptor extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            int tempPos=0;
            JOptionPane.showMessageDialog(null,tempRect);
            if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                tempPos=NumberPuzzleClass.moveIsPossible(e.getKeyCode(), np);
                for(int i=0; i<16;i++) {
                    if(rect[i].getText().equals(Integer.toString(tempPos)))
                        tempRect = i;
                }
                if ((emptyRect - 4 >=0)) {
                    emptyRect -= 4;
                    rect[tempRect].keyReleased(e.getKeyCode());
                    shiftInt=100;
                }
                NumberPuzzleClass.print(np);
            }
            if(e.getKeyCode()==KeyEvent.VK_UP) {
                tempPos=NumberPuzzleClass.moveIsPossible(e.getKeyCode(), np);
                for(int i=0; i<16;i++) {
                    if(rect[i].getText().equals(Integer.toString(tempPos))) {
                        tempRect = i;
                        //JOptionPane.showMessageDialog(null, rect[i].getText()+" "+tempPos);
                    }
                }
                if ((emptyRect + 4 <= 15)) {
                    //JOptionPane.showMessageDialog(null, rect[tempRect].getText()+" "+tempPos);
                    emptyRect += 4;
                    rect[tempRect].keyReleased(e.getKeyCode());
                    shiftInt=100;

                    System.out.println("up "+tempRect+" "+np[tempRect]+" "+rect[tempRect].getText());
                }
                NumberPuzzleClass.print(np);
            }
            if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
                tempPos=NumberPuzzleClass.moveIsPossible(e.getKeyCode(), np);
                for(int i=0; i<16;i++) {
                    if(rect[i].getText().equals(Integer.toString(tempPos)))
                        tempRect=i;
                }
                if ((emptyRect%4 - 1 >= 0)) {
                    emptyRect --;
                    rect[tempRect].keyReleased(e.getKeyCode());
                    shiftInt=100;
                }
                NumberPuzzleClass.print(np);
            }
            if(e.getKeyCode()==KeyEvent.VK_LEFT) {
                tempPos=NumberPuzzleClass.moveIsPossible(e.getKeyCode(), np);
                for(int i=0; i<16;i++) {
                    if(rect[i].getText().equals(Integer.toString(tempPos)))
                        tempRect=i;
                }
                if (((emptyRect%4 + 1) < 4)) {
                    emptyRect ++;
                    rect[tempRect].keyReleased(e.getKeyCode());
                    shiftInt=100;
                }
                NumberPuzzleClass.print(np);
            }
        }
    }
    public static void print(NumberPuzzleRect[] rect) {
        System.out.println();
        for(int i=0;i<16;i++) {
            if(rect[i].getText().equals("99"))
                System.out.print("   ");
            else
            if (Integer.parseInt(rect[i].getText())< 10)
                System.out.print(Integer.parseInt(rect[i].getText()) + "  ");
            else
                System.out.print(Integer.parseInt(rect[i].getText()) + " ");
            if ((i + 1) % 4 == 0)
                System.out.println();
        }
    }
}
