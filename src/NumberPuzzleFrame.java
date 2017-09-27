import com.sun.javafx.font.directwrite.RECT;
import sun.plugin2.message.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class NumberPuzzleFrame extends JPanel implements ActionListener, MouseListener {
    int[] np = new int[16];
    NumberPuzzleRect[] rect = new NumberPuzzleRect[16];
    int shiftInt=0;
    int tempRect=0;
    Timer mainTimer = new Timer(1, this); //Запускет actionPerform каждые .. милисекунд
    ArrayList<NumberPuzzleRect> shiftRectList = new ArrayList();
    public NumberPuzzleFrame() {
        mainTimer.start();
        addMouseListener(this);
        addKeyListener(new MyKeyAdaptor());
        setFocusable(true);
        NumberPuzzleClass.setNP(np);
        NumberPuzzleClass.setRandomNP(np, 10);
        NumberPuzzleClass.print(np);

        int r=100;
        for (int i = 0; i < 16; i++) {
            rect[i]= new NumberPuzzleRect((i)%4,(i)/4, r, np[i]);
        }

    }

    public void paint(Graphics g) {
        g=(Graphics2D)g;

        g.clearRect(0,0,700,600);
        Font font = new Font("Arial", Font.ITALIC, 10);
        for (int i = 0; i < 15; i++) {
            rect[i].draw(g);

            //g.setColor(Color.black);
            //g.setFont(font);
            //g.drawString("x="+rect[i].getX()+" y="+rect[i].getY()+" dx:"+rect[i].getDx()+" dy:"+rect[i].getDy()+"text: "+rect[i].getText(), 450, 120+i*10);
        }
        g.setColor(Color.black);
        g.setFont(font);
        g.drawString(Integer.toString(shiftRectList.size()),450, 120);
        Iterator<NumberPuzzleRect> i = shiftRectList.iterator();
        int pos=0;
        while (i.hasNext()){
            g.drawString(i.next().getText(),450, 150+pos*10);
            pos++;
        }
        //g.drawString(Integer.toString(emptyRect), 450, 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(shiftInt>0){
            try {
                //rect[tempRect].shift();
                Iterator<NumberPuzzleRect> i = shiftRectList.iterator();
                while (i.hasNext()){
                    i.next().shift();
                    //i.remove();
                }
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            shiftInt--;
        }
        else {

            //rect[tempRect].shift();
            Iterator<NumberPuzzleRect> i = shiftRectList.iterator();
            while (i.hasNext()){
                i.next();
                i.remove();
            }
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
        Point point = e.getPoint();
        Point shiftPoint = e.getPoint();
        int currentRect=-1;
        for (int i = 0; i < 15; i++) {
            resultMoveIsPossible=NumberPuzzleClass.moveIsPossible(np, Integer.parseInt(rect[i].getText()));

            if(rect[i].isMouseClicked(e.getPoint())) {
                currentRect=i;
                if(resultMoveIsPossible>=0) {
                    System.out.println("point "+e.getPoint()+" "+rect[i].getRect()+ " "+rect[i].getText()+" "+resultMoveIsPossible);
                    shift(resultMoveIsPossible, Integer.parseInt(rect[i].getText()));
                    NumberPuzzleClass.print(np);

                    i = 15;
                    return;
                }
            }
            System.out.println(i+" "+resultMoveIsPossible);
        }
        for (int i = 0; i < 15; i++) {
            resultMoveIsPossible=NumberPuzzleClass.moveIsPossible(np, Integer.parseInt(rect[i].getText()));
            if(point.x+100<400&&resultMoveIsPossible>0){
                shiftPoint.setLocation(point.x+100,point.y);

                if(rect[i].isMouseClicked(shiftPoint)&&resultMoveIsPossible>=0) {
                    System.out.println("point.x+100<400"+point+" "+shiftPoint+ " "+rect[i].getText());
                    shift(resultMoveIsPossible, Integer.parseInt(rect[i].getText()));
                    if(currentRect>=0)
                        shift(resultMoveIsPossible, Integer.parseInt(rect[currentRect].getText()));
                    NumberPuzzleClass.print(np);
                    //i=15;
                    //mouseClicked(e);
                    return;
                }
            }
        }
        for (int i = 0; i < 15; i++) {
            resultMoveIsPossible=NumberPuzzleClass.moveIsPossible(np, Integer.parseInt(rect[i].getText()));
            if (point.x - 100 >= 0 && resultMoveIsPossible > 0) {
                shiftPoint.setLocation(point.x - 100, point.y);

                if (rect[i].isMouseClicked(shiftPoint) && resultMoveIsPossible >= 0) {
                    System.out.println("point.x-100>=0" + point + " " + shiftPoint+ " "+rect[i].getText());
                    shift(resultMoveIsPossible, Integer.parseInt(rect[i].getText()));
                    if(currentRect>=0)
                        shift(resultMoveIsPossible, Integer.parseInt(rect[currentRect].getText()));
                    NumberPuzzleClass.print(np);
                    //i=15;
                    //mouseClicked(e);
                    return;
                }
            }
        }
        for (int i = 0; i < 15; i++) {
            resultMoveIsPossible=NumberPuzzleClass.moveIsPossible(np, Integer.parseInt(rect[i].getText()));
            if (point.y + 100 < 400 && resultMoveIsPossible > 0) {
                shiftPoint.setLocation(point.x, point.y + 100);

                if (rect[i].isMouseClicked(shiftPoint) && resultMoveIsPossible >= 0) {
                    System.out.println("point.y+100<400" + point + " " + shiftPoint+ " "+rect[i].getText());
                    shift(resultMoveIsPossible, Integer.parseInt(rect[i].getText()));
                    if(currentRect>=0)
                        shift(resultMoveIsPossible, Integer.parseInt(rect[currentRect].getText()));
                    NumberPuzzleClass.print(np);
                    //i=15;
                    //mouseClicked(e);
                    return;
                }
            }
        }
        for (int i = 0; i < 15; i++) {
            resultMoveIsPossible=NumberPuzzleClass.moveIsPossible(np, Integer.parseInt(rect[i].getText()));
            if(point.y - 100 >= 0 && resultMoveIsPossible>0){
                shiftPoint.setLocation(point.x,point.y - 100);

                if(rect[i].isMouseClicked(shiftPoint)&&resultMoveIsPossible>=0) {
                    System.out.println("point.y-100>=0"+point+" "+shiftPoint+ " "+rect[i].getText());
                    shift(resultMoveIsPossible, Integer.parseInt(rect[i].getText()));
                    if(currentRect>=0)
                        shift(resultMoveIsPossible, Integer.parseInt(rect[currentRect].getText()));
                    NumberPuzzleClass.print(np);
                    //i=15;
                    //mouseClicked(e);
                    break;
                }
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

            shift(e.getKeyCode());
            NumberPuzzleClass.print(np);
        }
    }
    public void shift(int e_KeyCode){
        int tempPos=0;
        tempPos=NumberPuzzleClass.moveIsPossible(e_KeyCode, np);
        for(int i=0; i<16;i++) {
            if(rect[i].getText().equals(Integer.toString(tempPos))) {
                tempRect = i;
                shiftRectList.add(0,rect[i]);
            }
        }
            rect[tempRect].keyReleased(e_KeyCode);
        shiftInt=100;
    }
    public void shift(int e_KeyCode,int tempPos){
        NumberPuzzleClass.moveIsPossible(e_KeyCode, np);
        for(int i=0; i<16;i++) {
            if(rect[i].getText().equals(Integer.toString(tempPos))){
                tempRect = i;
                /*if((shiftRectList.size()>0&&!shiftRectList.get(0).getText().equals(rect[i].getText()))||shiftRectList.size()==0)*/shiftRectList.add(0,rect[i]);
            }
        }
        rect[tempRect].keyReleased(e_KeyCode);
        shiftInt=100;
    }
}
