package com.example.alokikpathak.sqliteminiproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AnalyseActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnTop,btnBack;
    TextView ttvTopper,ttvAvg;

    String TopperStudent="", AvgStudentsList="";

    private MyHelperclass myHelper2;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyse);

        ttvTopper=(TextView)findViewById(R.id.ttvTopper);
        ttvAvg=(TextView) findViewById(R.id.ttvAvg);

        btnTop= (Button)findViewById(R.id.btnTop);
        btnBack=(Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        btnTop.setOnClickListener(this);


        myHelper2 = new MyHelperclass(this);

      //  Toast.makeText(this,"Inside Analyse...!",Toast.LENGTH_LONG).show();


       // onRetrive();
        ////
      //  Toast.makeText(this,"Inside Analyse Retrive...!",Toast.LENGTH_LONG).show();

        String [] columns= {"id","Name","Roll", "Marks"};
        SQLiteDatabase sqldb1 = myHelper2.getWritableDatabase();
        String result = "";  //stores result

        int highest=0,mark=0;

        String topperName="", topperRoll="", topperMarks="";

        String avgName="", avgRoll="", avgMarks="";

        Cursor cursor= sqldb1.query("studentDetails",columns,null,null,null,null,null);  //cursor initially points to -1 index of row so it is incremented and points to row which we want to extract
        //it runs the loop till cursor moveNext is false
        String row="";

        while(cursor.moveToNext()){
            //stores the row getString(0) points to 1st column similarly 1 for 2nd col
            result = result+cursor.getString(0)+" "+cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+"\n";

            mark = Integer.parseInt(cursor.getString(3));  //converting marks to int

            row = "Row: "+cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3);
            Toast.makeText(this, row,Toast.LENGTH_SHORT).show();

            //Storing the Topper details
            if( mark > highest){
                highest = mark;
                //Storing results of Topper
                topperName=cursor.getString(1);
                topperRoll=cursor.getString(2);
                topperMarks=cursor.getString(3);

            }


            //Storing details of Avg Students who scored b/w 60-65%

            if(mark >= 60 && mark <= 65 ){
                avgName=cursor.getString(1);
                avgRoll=cursor.getString(2);
                avgMarks=cursor.getString(3);

                AvgStudentsList+=avgName+","+avgRoll+" , "+avgMarks+";\n";

            }

        }//end of While

        //Satoring topper details
        TopperStudent = topperName+" , "+topperRoll+" , "+topperMarks;

        Toast.makeText(this,"Database Details: \n"+result,Toast.LENGTH_LONG).show();

        sqldb1.close();

        ////


        ttvAvg.setText(AvgStudentsList);
    }

    @Override
    public void onClick(View view) {

        if(view == btnTop){
           // Toast.makeText(this,"Topper Clicked...!",Toast.LENGTH_LONG).show();


            ttvTopper.setText(TopperStudent);
        }

        else if(view == btnBack){
         //   Toast.makeText(this,"Back Clicked...!",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }
/*
    public void onRetrive(){
        MyHelperclass myHelper2 = null;
        Toast.makeText(this,"Inside Analyse Retrive...!",Toast.LENGTH_LONG).show();

        String [] columns= {"id","Name","Roll", "Marks"};
        SQLiteDatabase sqldb = myHelper2.getWritableDatabase();
        String result = "";  //stores result

        int highest=0,mark=0;

        String topperName="", topperRoll="", topperMarks="";

        String avgName="", avgRoll="", avgMarks="";

        Cursor cursor= sqldb.query("studentDetails",columns,null,null,null,null,null);  //cursor initially points to -1 index of row so it is incremented and points to row which we want to extract
        //it runs the loop till cursor moveNext is false

        while(cursor.moveToNext()){
            //stores the row getString(0) points to 1st column similarly 1 for 2nd col
            result = result+cursor.getString(0)+" "+cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+"\n";

            mark = Integer.parseInt(cursor.getString(3));  //converting marks to int

            //Storing the Topper details
            if( mark > highest){
                highest = mark;
                //Storing results of Topper
                topperName=cursor.getString(1);
                topperRoll=cursor.getString(2);
                topperMarks=cursor.getString(3);

            }


            //Storing details of Avg Students who scored b/w 60-65%

            if(mark >= 60 && mark <= 65 ){
                avgName=cursor.getString(1);
                avgRoll=cursor.getString(2);
                avgMarks=cursor.getString(3);

                AvgStudentsList+=avgName+","+avgRoll+" , "+avgMarks+";\n";

            }

        }//end of While

        //Satoring topper details
        TopperStudent = topperName+" , "+topperRoll+" , "+topperMarks;

        Toast.makeText(this,"Database Details: \n"+result,Toast.LENGTH_LONG).show();

        sqldb.close();
    }end of Retrive()*/
}
