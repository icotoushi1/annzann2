package click.nobiru.annzann2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;

public class DataControl {

    public String DB_NAME = "annzann_db";
    public String TABLE_NAME = "t1";
    DataBaseHelper dh;

    public Cursor c;

    //カラム
    public int id=0,q_num=0,q_kannkaku=0,q_min=0,q_max=0,flg=0;




    public DataControl(Context con) {
        dh = new DataBaseHelper(con,DB_NAME);

    }

    public void DbStart(){

    }


    public Boolean setTableData(){
        // DBからデータ読み込み
        SQLiteDatabase db = dh.openDataBase();

        String str_sql = "select * from "+ TABLE_NAME;

        try {
            c = db.rawQuery(str_sql, null);

        } catch (Exception e) {
            Log.e("ERROR getDBData", e.toString());
        }
        return true;
    }

    public Boolean setFirstData(){

        c.moveToFirst();
        id          = c.getInt(0);
        q_num       = c.getInt(1);
        q_kannkaku = c.getInt(2);
        q_min       = c.getInt(3);
        q_max       = c.getInt(4);
        flg         = c.getInt(5);

        return true;
    }



    public void StartDB(){
        try {
            dh.startDataBase(DB_NAME);

        }catch (IOException e){
            Log.e("ERROR createDataBase", e.toString());
        }
    }


}
