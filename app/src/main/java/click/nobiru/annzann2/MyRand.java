package click.nobiru.annzann2;

import java.util.Random;

public class MyRand {

    //min ～　max の範囲でランダムな数字を取得
    public int getRand7(int min,int max){

        if(min>max){
            int temp=min;
            min=max;
            max=min;
        }
        if(min==max){
            max=max+1;
        }

        int hanni = max-min;
        Random r = new Random();
        int n = r.nextInt(hanni) + min;

        return (n);
    }


    //-hanni ～　＋hanni の範囲でランダムな数字を取得
    //int n = (int)Math.random()*100 + 1;
    public int getRand1(int hanni){

        Random r = new Random();
        int n = r.nextInt(hanni *2) + 1;
        n=n-hanni;
        return (n);
    }
    //1 ～　hanni の範囲でランダムな数字を取得
    public int getRand2(int hanni){
        Random r = new Random();
        int n = r.nextInt(hanni) + 1;
        return (n);
    }
    //1 ～　hanni の範囲でランダムな数字を取得
    public int getRand5(int hanni){
        Random r = new Random();
        int n = r.nextInt(hanni);
        return (n);
    }

    //0 ～ hanni-1 の範囲でランダムな数字を取得
    //int numで指定した個数の乱数を取得する。
    //乱数に重複はない
    //setNumをランダムな位置に格納する
    public int[] getRand3(int hanni,int num,int setNum){

        int rand[] = new int[num];
        int temp;
        boolean torf=true;
        for(int i=0;i < num ; i++){
            rand[i]=hanni+1;
        }

        for(int i=0;i < num ; i++){
             temp= getRand5(hanni);

            for(int j=0;j<(i+1);j++){
                if(rand[j]==temp){
                    torf=false;
                    i--;
                    break;
                }
            }
            if(torf){
                rand[i]=temp;
            }
            torf=true;
        }

        for(int i=0;i < num ; i++){
            if(rand[i]==setNum){
                torf=false;
            }
        }
        if(torf){
            rand[getRand5(num)]=setNum;//setNumをランダムな位置に格納する。
        }
        return(rand);
    }
}
