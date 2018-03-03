package com.example.alokikpathak.sqliteminiproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import static android.R.attr.version;

/**
 * Created by Alokik Pathak on 15-01-2018.
 */

public class MyHelperclass extends SQLiteOpenHelper {

    private static final String DATABSE_NAME = "myDataBase";
    private static final int DATABASE_VERSION =1;
    Context context;


    public MyHelperclass(Context context) {
        super(context, DATABSE_NAME, null, DATABASE_VERSION);

        this.context = context;
      //  Toast.makeText(context,"I'm inside constructor", Toast.LENGTH_SHORT ).show();
    }//end of constructor

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "create table studentDetails (id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR(30), Roll VARCHAR(10), Marks VARCHAR(10));";


        try{

            db.execSQL(query);
          //  Toast.makeText(context, "Table created...!!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception exp){

        }

     //   Toast.makeText(context, "inside Oncreate...!!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

     //   Toast.makeText(context, "Inside OnUpgrade...!!", Toast.LENGTH_SHORT).show();

        String query = "drop table if exist studentDetails";

        try{

            db.execSQL(query);
          //  Toast.makeText(context, "Table dropped...!!", Toast.LENGTH_SHORT).show();

            onCreate(db);
        }
        catch (Exception e){
          //  Toast.makeText(context, "Exception in OnUpgrade...!!", Toast.LENGTH_SHORT).show();

        }

    }
}
