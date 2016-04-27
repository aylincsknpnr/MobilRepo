package com.example.aylin.databasesmple;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class MainActivity extends Activity implements OnClickListener{
    EditText name, phone_no, id;
    Button addButton, deleteButton;
    TextView tv;
    List<StudentsModel> list = new ArrayList<StudentsModel>();
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(getApplicationContext());
        name = (EditText) findViewById(R.id.editText1);
        phone_no = (EditText) findViewById(R.id.editText2);
        id = (EditText) findViewById(R.id.editText3);
        addButton = (Button) findViewById(R.id.add);
        deleteButton = (Button) findViewById(R.id.delete);
        tv = (TextView) findViewById(R.id.tv);
        addButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
//add some students
        StudentsModel student = new StudentsModel();
        student.name = "Rajat";
        student.phone_number = "999999999";
        db.addStudentDetail(student);
        student.name = "Eric";
        student.phone_number = "8888888888";
        db.addStudentDetail(student);
        list = db.getAllStudentsList();
        print(list);
    }

    private void print(List<StudentsModel> list) {
// TODO Auto-generated method stub
        String value = "";
        for(StudentsModel sm : list){
            value = value+"id: "+sm.id+", name: "+sm.name+" Ph_no: "+sm.phone_number+"\n";
        }
        tv.setText(value);
    }

    @Override
    public void onClick(View v) {
// TODO Auto-generated method stub
        if(v == findViewById(R.id.delete)){
            tv.setText("");
            String student_id = id.getText().toString();
            db.deleteEntry(Integer.parseInt(student_id));
            list = db.getAllStudentsList();
            print(list);
        }
        if(v == findViewById(R.id.add)){
            tv.setText("");
            StudentsModel student = new StudentsModel();
            student.name = name.getText().toString();
            student.phone_number = phone_no.getText().toString();
            db.addStudentDetail(student);
            list = db.getAllStudentsList();
            print(list);
        }
    }


}