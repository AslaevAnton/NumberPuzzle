import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class NumberPuzzleRect {
    private int x, dx, ddx=0;
    private int y, dy, ddy=0;
    private int size;
    private String text;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public String getText() {
        return text;
    }

    public void setX(int x) {
        this.x = x;
        this.dx = x*size;
    }

    public void setY(int y) {
        this.y = y;
        this.dy = y*size;
    }



    public void setXYSize(int x, int y/*, int size*/){
        this.setX(x);
        this.setY(y);
        //this.size=size;
    }

    public void setText(String text) {
        this.text = text;
    }
    public void setText(int text) {
        this.text = Integer.toString(text);
    }

    public NumberPuzzleRect(int x, int y, int size, String text) {

        this.x = x;
        this.y = y;
        this.size = size;
        this.text = text;
    }
    public NumberPuzzleRect(int x, int y, int size, int text) {

        this.x = x;
        this.y = y;
        this.dx = x*size;
        this.dy = y*size;
        this.size = size;
        this.text = Integer.toString(text);
    }
    public void draw(Graphics g){
        g.setColor(Color.black);
        g.drawRect(dx,dy,size,size);

        Font font = new Font("Arial", Font.ITALIC, size/2);
        g.setColor(this.inPosition()?Color.green:Color.red);
        g.setFont(font);
        g.drawString(!text.equals("99")?text:"",(int)(dx+size/(2.8*text.length())), (int)(dy+size/1.5));
    }
    public boolean inPosition(){
        return ((y*4 + x + 1) == Integer.parseInt(text));
    }
    public void shift() throws InterruptedException {
        if(dx+ddx>=0&&dx+ddx<=300)
            this.dx+=ddx;
        if(dy+ddy>=0&&dy+ddy<=300)
            this.dy+=ddy;
        this.x=this.dx/100;
        this.y=this.dy/100;
        //JOptionPane.showMessageDialog(null,this.text);

               // Thread.sleep(100);

    }
    public void keyReleased(int e) {
        if (e == KeyEvent.VK_RIGHT){
            ddx=1;
            ddy=0;
        }
        if(e == KeyEvent.VK_LEFT) {
            ddx=-1;
            ddy=0;
        }
        if (e == KeyEvent.VK_UP) {
            ddy = -1;
            ddx=0;
        }
        if(e == KeyEvent.VK_DOWN) {
            ddy = 1;
            ddx=0;
        }
    }
    public boolean isMouseClicked (Point p){
        return(p.getX()>=dx&&p.getX()<=dx+size&&p.getY()>=dy&&p.getY()<=dy+size);
    }
}
