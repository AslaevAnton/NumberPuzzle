import java.awt.*;

public class NumberPuzzleRect {
    private int x;
    private int y;
    private int size;
    final private String text;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
        this.size = size;
        this.text = Integer.toString(text);
    }
    public void draw(Graphics g){
        g.drawRect(x,y,size,size);

        Font font = new Font("Arial", Font.ITALIC, size/2);
        g.setFont(font);
        g.drawString(text,(int)(x+size/2.8), (int)(y+size/1.5));
    }
}
