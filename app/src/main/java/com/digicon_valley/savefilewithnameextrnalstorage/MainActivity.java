package com.digicon_valley.savefilewithnameextrnalstorage;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText file_name;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        file_name=findViewById(R.id.id_file_name);
        editText=findViewById(R.id.id_text_message);
    }

    private boolean isExternalStorageWritable(){
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            Log.i("Status","Yes it is writable");
            return true;
        }else {
            return false;
        }

    }

    public void writeMessage(View view) {

        if (isExternalStorageWritable() && checkPErmission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){

            File file=new File(Environment.getExternalStorageDirectory(),file_name.getText().toString());
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(editText.getText().toString().getBytes());
                fileOutputStream.close();

                Toast.makeText(this,"File Saved",Toast.LENGTH_SHORT).show();

            }catch (IOException e){
                e.printStackTrace();
            }
        }else {
            Toast.makeText(this,"Cannot Write to External Storage",Toast.LENGTH_SHORT).show();

        }

    }


public boolean checkPErmission(String permission){
    int check=ContextCompat.checkSelfPermission(this,permission);
        return (check==PackageManager.PERMISSION_GRANTED);
}}
