import javax.xml.bind.SchemaOutputResolver;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.util.Random;

public class NumberPuzzleClass {/*
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
    public static boolean moveIsPossible(int[] np, int value){
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                if(np[i*4+j]==value){
                    if((i-1)>=0 && np[(i-1)*4+j]==99){
                        np[(i-1)*4+j]=np[i*4+j];
                        np[i*4+j]=99;
                        return true;
                    }

                    if((i+1)<4 && np[(i+1)*4+j]==99){
                        np[(i+1)*4+j]=np[i*4+j];
                        np[i*4+j]=99;
                        return true;
                    }
                    if((j-1)>=0 && np[i*4+(j-1)]==99){
                        np[i*4+(j-1)]=np[i*4+j];
                        np[i*4+j]=99;
                        return true;
                    }

                    if((j+1)<4 && np[i*4+(j+1)]==99) {
                        np[i*4+(j+1)]=np[i*4+j];
                        np[i*4+j]=99;
                        return true;
                    }
                }
        return false;

    }
    public static int moveIsPossible( int key, int[] np){
        //key = 0 down
        //key = 1 up
        //key = 2 right
        //key = 3 left
        for (int z = 0; z < 16; z++) {


            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++)
                    if (np[i * 4 + j] == np[z]) {
                        //up
                        if ((i - 1) >= 0 && np[(i - 1) * 4 + j] == 99 && key == 1) {
                            np[(i - 1) * 4 + j] = np[i * 4 + j];
                            np[i * 4 + j] = 99;
                            return np[z];
                        }
                        //down
                        if ((i + 1) < 4 && np[(i + 1) * 4 + j] == 99 && key == 0) {
                            //return np[z];
                            np[(i + 1) * 4 + j] = np[i * 4 + j];
                            np[i * 4 + j] = 99;
                            return z;
                        }
                        //left
                        if ((j - 1) >= 0 && np[i * 4 + (j - 1)] == 99 && key == 3) {
                            np[i * 4 + (j - 1)] = np[i * 4 + j];
                            np[i * 4 + j] = 99;
                            return np[z];
                        }
                        //right
                        if ((j + 1) < 4 && np[i * 4 + (j + 1)] == 99 && key == 2) {
                            np[i * 4 + (j + 1)] = np[i * 4 + j];
                            np[i * 4 + j] = 99;
                            return np[z];
                        }
                    }
        }
        return 0;

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
        return true;
    }

}
