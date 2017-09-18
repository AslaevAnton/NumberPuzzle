import java.awt.*;

public class NumberPuzzleRect {
    private int x;
    private int y;
    private int size;
    private String text;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getText() {
        return text;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setXYSize(int x, int y, int size){
        this.setX(x);
        this.setY(y);
        this.size=size;
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
        this.size = size;
        this.text = Integer.toString(text);
    }
    public void draw(Graphics g){
        g.setColor(Color.black);
        g.clearRect(x,y,size,size);
        g.drawRect(x,y,size,size);

        Font font = new Font("Arial", Font.ITALIC, size/2);
        g.setColor(this.inPosition()?Color.green:Color.red);
        g.setFont(font);
        g.drawString(!text.equals("99")?text:"",(int)(x+size/(2.8*text.length())), (int)(y+size/1.5));
    }
    public boolean inPosition(){
        return (((y/size)*4 + x/size + 1) == Integer.parseInt(text));
    }
}
