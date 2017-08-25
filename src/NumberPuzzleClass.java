import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;

public class NumberPuzzleClass {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] np = new int[16];
        int num;
        setNP(np);
        print(np);
        for(int i=0;i<5;i++){
            num=Integer.parseInt(reader.readLine());
            System.out.println(moveIsPossible(np, num)?"":"Ход не возможен");
            print(np);
        }

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
            for(int i=0;i<16;i++)
                np[i]=i+1;
            np[15]=99;
        }
    public static boolean moveIsPossible(int[] np, int value){
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                if(np[i*4+j]==value){
                    if((i-1)>0 && np[(i-1)*4+j]==99){
                        np[(i-1)*4+j]=np[i*4+j];
                        np[i*4+j]=99;
                        return true;
                    }

                    if((i+1)<4 && np[(i+1)*4+j]==99){
                        np[(i+1)*4+j]=np[i*4+j];
                        np[i*4+j]=99;
                        return true;
                    }
                    if((j-1)>0 && np[i*4+(j-1)]==99){
                        np[i*4+(j-1)]=np[i*4+j];
                        np[i*4+j]=99;
                        return true;
                    }

                    if((j+1)<40 && np[i*4+(j+1)]==99) {
                        np[i*4+(j+1)]=np[i*4+j];
                        np[i*4+j]=99;
                        return true;
                    }
                }
        return false;

    }

}
