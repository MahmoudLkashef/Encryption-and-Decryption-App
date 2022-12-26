package com.mohassaan.encryptionanddecryption;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    // declaring all essential variables
    private CardView cardView;
    private Button encrypt, decrypt;
    private EditText message, cipher, key;
    private TextView screen_output;
    private Spinner spinner;
    public int flag=0;
    private String result;
    private RowTransposition rowTransposition=new RowTransposition();
    private static final String alphabetString = "abcdefghijklmnopqrstuvwxyz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // findViewById is the method that
        // finds the View by the ID it is given
        cardView = findViewById(R.id.cardView);
        encrypt = findViewById(R.id.btnencrypt);
        decrypt = findViewById(R.id.btndecrypt);
        screen_output = findViewById(R.id.tV1);
        message = findViewById(R.id.inputMessage);
        cipher = findViewById(R.id.ciphEdt);
        key = findViewById(R.id.key_dt);
        spinner=findViewById(R.id.spinner);


        //Animation
        cardView.setY(-2000);
        cardView.animate().translationYBy(2000).setDuration(300);

        message.setY(-2000);
        message.animate().translationYBy(2000).setDuration(500);

        cipher.setX(2000);
        cipher.animate().translationXBy(-2000).setDuration(500);

        key.setX(-2000);
        key.animate().translationXBy(2000).setDuration(500);

        encrypt.setX(-2000);
        encrypt.animate().translationXBy(2000).setDuration(500);

        decrypt.setX(2000);
        decrypt.animate().translationXBy(-2000).setDuration(500);

        // setting onCLickListener on encrypt button
        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (flag){
                    case 0:{
                        Toast.makeText(MainActivity.this, "Please choose the algorithm", Toast.LENGTH_SHORT).show();
                    }
                    case 1:{
                        encrypt12(message.getText().toString(), Integer.parseInt(key.getText().toString()));
                    }break;
                    case 2:{
                        RailFence railFence=new RailFence(Integer.parseInt(key.getText().toString()));
                        result=railFence.getDecryptedData(message.getText().toString());
                        screen_output.setText(result);
                        cipher.setText(result);
                    }break;
                    case 3:{
                        Vernam vernam=new Vernam();
                        result=vernam.encrypt(message.getText().toString(),key.getText().toString());
                        screen_output.setText(result);
                        cipher.setText(result);
                    }break;
                    case 4:{
                        result=rowTransposition.encrypt(key.getText().toString(),message.getText().toString());
                        screen_output.setText(result);
                        cipher.setText(result);
                    }break;
                }
            }
        });

        // setting onCLickListener on decrypt button
        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (flag){
                    case 0:{
                        Toast.makeText(MainActivity.this, "Please choose the algorithm", Toast.LENGTH_SHORT).show();
                    }
                    case 1:{
                        decrypt12(cipher.getText().toString(), Integer.parseInt(key.getText().toString()));
                    }break;
                    case 2:{
                        RailFence railFence=new RailFence(Integer.parseInt(key.getText().toString()));
                        result=railFence.getDecryptedData(cipher.getText().toString());
                        screen_output.setText(result);
                        message.setText(result);
                    }break;
                    case 3:{
                        Vernam vernam=new Vernam();
                        result=vernam.decrypt(cipher.getText().toString(),key.getText().toString());
                        screen_output.setText(result);
                        message.setText(result);
                    }break;
                    case 4:{
                        result=rowTransposition.decrypt(key.getText().toString(),cipher.getText().toString());
                        screen_output.setText(result);
                        message.setText(result);
                    }break;
                }
            }
        });

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.algorithms, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }

    // method to show the final output on the output
    // textView when decrypt button is clicked
    public void decrypt12(String cipher, int key) {
        screen_output.setText((utility.decrypt1(cipher, key%26).toLowerCase()));
    }

    // method to show the final output on the output
    // textView when encrypt button is clicked
    public String encrypt12(String message, int shiftkey) {
        message = message.toLowerCase();
        String cipherText = "";
        for (int i = 0; i < message.length(); i++) {
            int charPosition = alphabetString.indexOf(message.charAt(i));
            int keyval = (shiftkey + charPosition) % 26;
            char replaceVAL = alphabetString.charAt(keyval);
            cipherText += replaceVAL;
            screen_output.setText(cipherText);
            cipher.setText(cipherText);
        }

        // returning the final ciphertext
        return cipherText;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice=adapterView.getItemAtPosition(i).toString();
        switch (i){
            case 1:
            {
                flag=1;
            }break;
            case 2:{
                flag=2;
            }break;
            case 3:{
                flag=3;
            }break;
            case 4:{
                flag=4;
            }break;
            default: Log.d("mainAc",choice);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }


}