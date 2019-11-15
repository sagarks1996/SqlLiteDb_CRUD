package com.example.sqllitedb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AppCompatEditText name,pass,currentname,newname,deletename;
    AppCompatButton addbutton,viewbutton,updatebutton,deletebutton;

    DatabaseAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name = findViewById(R.id.name);
        pass = findViewById(R.id.pass);
        addbutton = findViewById(R.id.addbutton);
        viewbutton = findViewById(R.id.viewbutton);
        currentname = findViewById(R.id.currentname);
        newname = findViewById(R.id.newname);
        updatebutton = findViewById(R.id.updatebutton);
        deletename = findViewById(R.id.deletename);
        deletebutton = findViewById(R.id.deletebutton);


        helper = new DatabaseAdapter(this);

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = name.getText().toString();
                String password = pass.getText().toString();

                if (username.isEmpty() || password.isEmpty()){

                    Toast.makeText(getApplicationContext(),"Enter Data",Toast.LENGTH_SHORT).show();

                }else {

                    Log.d("insertion","name:"+username);
                    Log.d("insertion","password:"+password);
                    long id = helper.insertdata(username,password);
                    Log.d("insertion","id:"+id);
                    if (id <= 0){
                        Log.d("insertion","failed");
                        Toast.makeText(getApplicationContext(),"Unsucessfull",Toast.LENGTH_SHORT).show();
                        name.setText("");
                        pass.setText("");
                    }else {
                        Log.d("insertion","success");
                        Toast.makeText(getApplicationContext(),"sucessfull",Toast.LENGTH_SHORT).show();
                        name.setText("");
                        pass.setText("");

                    }
                }
            }
        });


        viewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String data = helper.getdata();
                Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();
            }
        });



        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String oldname = currentname.getText().toString();
                String namenew = newname.getText().toString();

                if (oldname.isEmpty() || namenew.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter Data",Toast.LENGTH_SHORT).show();
                }else {
                    int a = helper.updatename(oldname,namenew);
                    if (a<=0){
                        Toast.makeText(getApplicationContext(),"unsuccessfull",Toast.LENGTH_SHORT).show();
                        currentname.setText("");
                        newname.setText("");
                    }else {
                        Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
                        currentname.setText("");
                        newname.setText("");
                    }
                }
            }
        });


        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String username = deletename.getText().toString();
                if (username.isEmpty()){
                    Toast.makeText(getApplicationContext(),"enter data",Toast.LENGTH_SHORT).show();

                }else {
                    int a = helper.deletefield(username);
                    if (a<=0){
                        Toast.makeText(getApplicationContext(),"unsuccessfull",Toast.LENGTH_SHORT).show();
                        deletename.setText("");
                    }else {
                        Toast.makeText(getApplicationContext(),"deleted",Toast.LENGTH_SHORT).show();
                        deletename.setText("");
                    }

                }
            }
        });




    }
}
