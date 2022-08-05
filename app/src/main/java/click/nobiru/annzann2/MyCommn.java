package click.nobiru.annzann2;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.logging.Handler;


public class MyCommn extends AppCompatActivity {

    String FILE_NAME_KYOUKA_1  = "2019_tiriA (";
    String FILE_NAME_KYOUKA_2  = "2019_tiriB (";
    String FILE_NAME_KYOUKA_3  = "2019_chigaku (";
    String FILE_NAME_KYOUKA_4  = "2019_butsuri (";
    String FILE_NAME = FILE_NAME_KYOUKA_1;
    String KAKUTYOUSHI_NAME = ").png";

    int maxPajiKyouka1 = 31;
    int maxPajiKyouka2 = 35;
    int maxPajiKyouka3 = 33;
    int maxPajiKyouka4 = 33;
    int maxPaji = maxPajiKyouka1;
    TextView tempText;
    android.os.Handler tempHandler;
    String tempStr;


    String apri_syoukai_msg = "Androidアプリの紹介です。" +
            "http://www.kami-apuri.com/article/464094690.html" ;

    long startTime   = 0;
    long endTime     = 0;
    long keikaTime = 0;
    int  timeSecond  = 0;


    public void setText(android.os.Handler handler, TextView textView ,String str){
        tempText=textView;
        tempHandler=handler;
        tempStr=str;


        tempHandler.post(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                tempText.setText(tempStr);
            }
        });

    }






}
