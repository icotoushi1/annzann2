package click.nobiru.annzann2;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.amazon.device.ads.AdLayout;
import com.amazon.device.ads.AdRegistration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;

public class MainActivity extends AppCompatActivity  {



    int ANS=0,RAND=0,sleepTime=1000;
    int select =1;//1=admob
    int select2 =1;//1=admob
    TextView textQuestion,textMsg1,textMsg2;
    EditText edAns;
    Button btnStart,buttonAns;
    Handler handler ;
    MyCommn mc = new MyCommn();
    DataControl dc ;
    int q_cnt=5,q_min=3,q_max=12;
    MyRand mr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyInit1();
        MyInit2();


    }

    public void MyInit1(){
        setContentView(R.layout.activity_main);
        selectAd(select);
        MainViewSet();
    }
    public void MyInit2(){
        dbSyori();
        setDbData();
    }



    public void dbSyori(){
        dc = new DataControl(this);
        dc.StartDB();
        dc.setTableData();
        dc.setFirstData();

        mr = new MyRand();
    }

    public void setDbData(){
        q_cnt = dc.q_num;
        q_max = dc.q_max;
        q_min = dc.q_min;
    }



    public void MainViewSet(){
        textQuestion = findViewById(R.id.text1);
        textMsg1      = findViewById(R.id.textMsg1);
        textMsg2      = findViewById(R.id.textMsg2);
        edAns         = findViewById(R.id.ed_text1);
        btnStart      = findViewById(R.id.b_start);
        buttonAns     = findViewById(R.id.b_ans);
        handler       = new Handler();
    }

    public void GetSetteiViewSetData(){

        try {
            EditText ed_min = findViewById(R.id.ed_min);
            EditText ed_max = findViewById(R.id.ed_max);
            Spinner s_kannkaku = findViewById(R.id.sp_kannkaku);
            Spinner s_kosuu = findViewById(R.id.sp_kosuu);

            q_min = Integer.parseInt(ed_min.getText().toString());
            q_max = Integer.parseInt(ed_max.getText().toString());
            sleepTime = Integer.parseInt(s_kannkaku.getSelectedItem().toString());
            q_cnt = Integer.parseInt(s_kosuu.getSelectedItem().toString());
        }catch (Exception e){}

    }

    public void onClickReturnToMain(View view){

        GetSetteiViewSetData();
        MyInit1();
        setFOucus();

    }

    public void setFOucus(){
        edAns.setFocusable(true);
        edAns.setFocusableInTouchMode(true);
        edAns.setEnabled(true);
        edAns.requestFocus();
    }



    public void onClickBStart(View view){
        ANS=0;
        gameStart();
        setFOucus();
    }



    public void gameStart(){

        // TODO Auto-generated method stub
        new Thread(new Runnable() {
            @Override
            public void run() {
               // TODO Auto-generated method stub
                textCler();
                gameSyori();
            }
        }).start();
    }
    public void gameSyori(){

        for(int i=0;i<q_cnt;i++){
            setQuestion();
        }
    }
    public void setQuestion(){

        setRand();
        Mysleep(sleepTime);
        mc.setText(handler,textQuestion,"");
        Mysleep(sleepTime/5);
    }

    public void textCler(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                textQuestion.setText("");
                textMsg1.setText("");
                textMsg2.setText("");
                edAns.setText("");
            }
        });
    }




    public void setRand(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                RAND = mr.getRand7(q_min,q_max);
                String randStr = Integer.toString(RAND);
                textQuestion.setText(randStr);
                ANS = ANS + RAND;
            }
        });
    }
    private void Mysleep(int inSleepTime) {
        try {
            Thread.sleep(inSleepTime);
        } catch (InterruptedException ie) {
            Log.v("loadFile", "error");
        }

    }
    public void onClickBAns(View view){
        textQuestion.setText("");
        String ansStr = String.valueOf(ANS);
        CharSequence str;
        int inputAns=0;

        try {
            inputAns= Integer.parseInt(edAns.getText().toString());
        }catch (Exception e){
        }
        if(inputAns==ANS){
            str = getString(R.string.correct);

            textMsg1.setText(str);

        }else{
            str = getString(R.string.inCorrect);
            textMsg1.setText(str);
        }
        str = getString(R.string.correct_anser);
        textMsg2.setText(str + "：" + ansStr);
    }




    public void selectAd(int select){
        if(select==1){
            Admob();
        }else{
            AmazonAd();
        }
    }

    public void Admob(){
        // Test App ID
        MobileAds.initialize(this,
                "");

//        AdView adView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);

    }
    public int AmazonAd(){
//        AdLayout adView = (AdLayout) findViewById(R.id.amazonn_ad_view);
//        String APP_KEY
//                ="";
//        AdRegistration.setAppKey(APP_KEY);
//        adView.loadAd();
//        adView.showAd();
        return 0;
    }



    public void onClickApriDL(View view){

        Uri uri = Uri.parse(view.getTag().toString());
        Intent i = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(i);
    }
    public void onClickSettei(View view){
        setContentView(R.layout.settei);
    }


    public void onClickApEnd(View view){
        onDestroy();
        System.exit(RESULT_OK);
    }
    public void onClickLine(View view){
        setLineMsg(mc.apri_syoukai_msg);
    }

    public boolean setLineMsg(String str){

        //Lineがインストールされているかチェック
        String appId = "jp.naver.line.android";
        try {
            PackageManager pm = getPackageManager();
            ApplicationInfo appInfo = pm.getApplicationInfo
                    (appId, PackageManager.GET_META_DATA);
            //インストールされてたら、Lineへ
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("line://msg/text/" + str));
            startActivity(intent);
        } catch(PackageManager.NameNotFoundException e) {
            //インストールされてなかったら、インストールを要求する
        }
        return true;
    }
    public void onClickTwitter(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,mc.apri_syoukai_msg);
        intent.setType("text/plain");
        startActivity(intent);
    }


    public void onClickOpenWebSite(View view){
        Intent intent = new Intent(
          Intent.ACTION_VIEW,
          Uri.parse("https://nobiru.click/"));
        startActivity(intent);
    }



}
