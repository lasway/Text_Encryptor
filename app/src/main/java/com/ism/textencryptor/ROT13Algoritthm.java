package com.ism.textencryptor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ROT13Algoritthm extends AppCompatActivity implements View.OnClickListener {
    TextView receiver_msg;
    String val;
    String cipher;
    String plaintext;
    String output;
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rot13_algoritthm);

        receiver_msg = findViewById(R.id.received_value_id);
        Button DecryptButton = findViewById(R.id.DecryptButton);
        DecryptButton.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            val = extras.getString("input");
        }
        Rot encrypt = new Rot();
        cipher = encrypt.encryptData(val,13);
        receiver_msg.setText("The CipherText is " + cipher);
    }

    @Override
    public void onClick(View v) {
        Rot decrypt = new Rot();
        output = decrypt.decryptData(cipher,13);
        displayAreaDialogMessage(output);
    }
    private void displayAreaDialogMessage(String output) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("The plaintext is " + output)
                .setTitle("ROT-13 decryption");

        builder.setPositiveButton(android.R.string.ok, (dialog, id) -> {

        });
        builder.setNegativeButton(android.R.string.cancel, (dialog, id) -> {

        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    static class Rot{
        // create encryptData() method for encrypting user input string with given shift key
        public String encryptData(String inputStr, int shiftKey) {
            // convert inputStr into lower case
            inputStr = inputStr.toLowerCase();

            // encryptStr to store encrypted data
            String encryptStr = "";

            // use for loop for traversing each character of the input string
            for (int i = 0; i < inputStr.length(); i++) {
                // get position of each character of inputStr in ALPHABET
                int pos = ALPHABET.indexOf(inputStr.charAt(i));

                // get encrypted char for each char of inputStr
                int encryptPos = (shiftKey + pos) % 26;
                char encryptChar = ALPHABET.charAt(encryptPos);

                // add encrypted char to encrypted string
                encryptStr += encryptChar;
            }

            // return encrypted string
            return encryptStr;

        }


        // create decryptData() method for decrypting user input string with given shift key
        public String decryptData(String inputStr, int shiftKey) {

            // convert inputStr into lower case
            inputStr = inputStr.toLowerCase();

            // decryptStr to store decrypted data
            String decryptStr = "";

            // use for loop for traversing each character of the input string
            for (int i = 0; i < inputStr.length(); i++) {

                // get position of each character of inputStr in ALPHABET
                int pos = ALPHABET.indexOf(inputStr.charAt(i));

                // get decrypted char for each char of inputStr
                int decryptPos = (pos - shiftKey) % 26;

                // if decryptPos is negative
                if (decryptPos < 0) {
                    decryptPos = ALPHABET.length() + decryptPos;
                }
                char decryptChar = ALPHABET.charAt(decryptPos);

                // add decrypted char to decrypted string
                decryptStr += decryptChar;
            }
            // return decrypted string
            return decryptStr;
        }
    }

}