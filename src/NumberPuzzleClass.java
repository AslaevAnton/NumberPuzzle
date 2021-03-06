import javax.xml.bind.SchemaOutputResolver;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class NumberPuzzleClass {
    static int count=0;
    /*
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] np = new int[16];
        int num=1;
        setRandomNP(np, 10);
        print(np);
        System.out.println(solutionExsist(np));
        while(!win(np) && num>0){
            num=Integer.parseInt(reader.readLine());
            System.out.println(moveIsPossible(np, num)?"":"Ход не возможен");
            print(np);
        }

    }
*/
    //int[] np = new int[15];

    //public NumberPuzzleClass() {
    //    setNP(this.np);
    //}

    //public int[] getNp() {
    //    return np;
    //}


    public static int getCount() {
        return count;
    }

    public static void print(int[] np) {
        for(int i=0;i<16;i++) {
            if(np[i]==99)
                System.out.print("   ");
            else
                if (np[i] < 10)
                    System.out.print(np[i] + "  ");
                else
                    System.out.print(np[i] + " ");
            if ((i + 1) % 4 == 0)
               System.out.println();
        }
    }
    public static void setNP(int[] np) {
            for(int i=0;i<15;i++)
                np[i]=i+1;
            np[15]=99;
        }
    public static int moveIsPossible(int[] np, int value){
        for(int i=0;i<4&&value!=99;i++)
            for(int j=0;j<4;j++)
                if(np[i*4+j]==value){
                    if((i-1)>=0 && np[(i-1)*4+j]==99){
                        //np[(i-1)*4+j]=np[i*4+j];
                        //np[i*4+j]=99;
                        return KeyEvent.VK_UP;
                    }

                    if((i+1)<4 && np[(i+1)*4+j]==99){
                        //np[(i+1)*4+j]=np[i*4+j];
                        //np[i*4+j]=99;
                        return KeyEvent.VK_DOWN;
                    }
                    if((j-1)>=0 && np[i*4+(j-1)]==99){
                        //np[i*4+(j-1)]=np[i*4+j];
                        //np[i*4+j]=99;
                        return KeyEvent.VK_LEFT;
                    }

                    if((j+1)<4 && np[i*4+(j+1)]==99) {
                        //np[i*4+(j+1)]=np[i*4+j];
                        //np[i*4+j]=99;
                        return KeyEvent.VK_RIGHT;
                    }
                }
        return -1;

    }
    public static int moveIsPossible( int key, int[] np){
        //key = 0 down
        //key = 1 up
        //key = 2 right
        //key = 3 left
        int rezult=0;
        for (int z = 0; z < 16; z++) {


            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++)
                    if (np[i * 4 + j] == np[z]) {
                        rezult=np[z];
                        //up
                        if ((i - 1) >= 0 && np[(i - 1) * 4 + j] == 99 && key == KeyEvent.VK_UP) {
                            np[(i - 1) * 4 + j] = np[i * 4 + j];
                            np[i * 4 + j] = 99;
                            count++;
                            return rezult;
                        }
                        //down
                        if ((i + 1) < 4 && np[(i + 1) * 4 + j] == 99 && key == KeyEvent.VK_DOWN) {
                            //return np[z];
                            np[(i + 1) * 4 + j] = np[i * 4 + j];
                            np[i * 4 + j] = 99;
                            count++;
                            return rezult;
                        }
                        //left
                        if ((j - 1) >= 0 && np[i * 4 + (j - 1)] == 99 && key == KeyEvent.VK_LEFT) {
                            np[i * 4 + (j - 1)] = np[i * 4 + j];
                            np[i * 4 + j] = 99;
                            count++;
                            return rezult;
                        }
                        //right
                        if ((j + 1) < 4 && np[i * 4 + (j + 1)] == 99 && key == KeyEvent.VK_RIGHT) {
                            np[i * 4 + (j + 1)] = np[i * 4 + j];
                            np[i * 4 + j] = 99;
                            count++;
                            return rezult;
                        }
                    }
        }
        return 0;

    }
    public static ArrayList<NumberPuzzleRect> shiftList(NumberPuzzleRect[] rect,int[] np, int currentRect){
        int emptyI=-1,currentI=-1;
        ArrayList<NumberPuzzleRect> rezultList=new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            if(np[i]==99) {
                emptyI = i;
            }
            if(np[i]==currentRect) {
                currentI = i;
            }
        }
        System.out.println(np[currentI]+" "+np[emptyI]);
        if(emptyI/4==currentI/4){  //текущая клетка в одной строке с пустой
            int di = (currentI<emptyI)?-1:+1;
            int i =  emptyI+di;
            int exit;
            do{
                exit=(currentI<emptyI)?i-currentI:currentI-i;
                System.out.print(i+" "+(exit)+" ");
                System.out.println(np[i]);
                for (int j = 0; j < 16; j++) {
                    if(rect[j].getText().equals(Integer.toString(np[i])))
                        rezultList.add(rect[j]);
                }
                i+=di;

            }while (exit!=0);
        }
        if(emptyI%4==currentI%4){  //текущая клетка в одном стролбце с пустой
            /*for (int i =  (currentI<emptyI)?emptyI-4:emptyI+4; currentI-i!=0; i=(currentI<emptyI)?i-4:i+4) {
                System.out.println(np[i]+" "+i+" "+Math.abs(currentI-i));
                rezultList.add(0,np[i]);
            }*/
            int di = (currentI<emptyI)?-4:+4;
            int i =  emptyI+di;
            int exit;
            do{
                exit=(currentI<emptyI)?i-currentI:currentI-i;
                System.out.print(i+" "+(exit)+" ");
                System.out.println(np[i]);
                for (int j = 0; j < 16; j++) {
                    if(rect[j].getText().equals(Integer.toString(np[i])))
                        rezultList.add(rect[j]);
                }
                i+=di;

            }while (exit!=0);
        }

        return rezultList;
    }
    public static boolean solutionExsist(int[] np){
        int inv = 0;
        for (int i=0; i<16; ++i)
                for (int j=0; j<i; ++j)
                    if (np[j] > np[i])
                        ++inv;
        for (int i=0; i<16; ++i)
            if (np[i] == 0)
                inv += 1 + i / 4;

        return (inv%2==0?true:false);
    }
    public static void setRandomNP(int[] np) {
        setRandomNP(np,0);
    }
    public static void setRandomNP(int[] np, int randNum) {
        Random random =new Random();
        int tempNum;
        int tempI1, tempI2;
        for(int i=0;i<15;i++)
            np[i]=15-i;
        np[15]=99;
        while(!solutionExsist(np)){
            for(int i=0;i<randNum;i++){
                tempI1=random.nextInt(15);
                tempI2=random.nextInt(15);
                tempNum=np[tempI1];
                np[tempI1]=np[tempI2];
                np[tempI2]=tempNum;
            }
        }
    }
    public static boolean win(int[] np){
        for(int i=0;i<15;i++)
            if(np[i]>np[i+1])
                return false;
        print(np);
        return true;
    }

}
