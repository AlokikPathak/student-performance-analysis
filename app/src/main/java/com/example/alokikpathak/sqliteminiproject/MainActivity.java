package com.example.alokikpathak.sqliteminiproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtName, edtRoll, edtEng, edtMaths, edtPhy;

    Button btnSubmit, btnAnalyse;

    private MyHelperclass myHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = (EditText)findViewById(R.id.edtName);
        edtRoll = (EditText)findViewById(R.id.edtRoll);
        edtEng = (EditText)findViewById(R.id.edtEng);
        edtMaths = (EditText)findViewById(R.id.edtMaths);
        edtPhy = (EditText)findViewById(R.id.edtPhy);

        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnAnalyse = (Button)findViewById(R.id.btnAnalyse);

        btnSubmit.setOnClickListener(this);
        btnAnalyse.setOnClickListener(this);

        myHelper = new MyHelperclass(this);

    }

    @Override
    public void onClick(View view) {

        if(view == btnSubmit){

          //  Toast.makeText(this,"Submit Clicked...!",Toast.LENGTH_LONG).show();


            String sName = edtName.getText().toString();
            String sRoll = edtRoll.getText().toString();

            //Adding the Marks of aLL THREE SUBJECTS
            int marks=0;
            marks += Integer.valueOf(edtEng.getText().toString());
            marks += Integer.valueOf(edtMaths.getText().toString());
            marks += Integer.valueOf(edtPhy.getText().toString());

           // Toast.makeText(this, "Marks1: "+marks,Toast.LENGTH_SHORT).show();

            marks = (marks*100)/300; //Finding the Percentage

           // Toast.makeText(this, "Marks2: "+marks,Toast.LENGTH_SHORT).show();


            //converting the marks in string
            String sMarks = Integer.toString(marks);

          //  Toast.makeText(this, "Marks: "+sMarks,Toast.LENGTH_SHORT).show();

            SQLiteDatabase sqldb = myHelper.getWritableDatabase();
            ContentValues cntval = new ContentValues();

            cntval.put("Name",sName);
            cntval.put("Roll",sRoll);
            cntval.put("Marks", sMarks);

            long id=sqldb.insert( "studentDetails" ,null,cntval );

            if(id<0){
              //  Toast.makeText(this,"Insertion Failed, id: "+id,Toast.LENGTH_LONG).show();
                Toast.makeText(this,"Insertion Failed...! ",Toast.LENGTH_LONG).show();


            }
            else {
               // Toast.makeText(this, "Insertion Successfull: id: " + id, Toast.LENGTH_LONG).show();
                Toast.makeText(this, "Insertion Successfull...!", Toast.LENGTH_SHORT).show();
            }



            sqldb.close(); //close the database after using preferred
        }


        else if(view == btnAnalyse){


            //Toast.makeText(this,"Analyse Clicked...!",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this,AnalyseActivity.class);
            startActivity(intent);
        }
    }
}
