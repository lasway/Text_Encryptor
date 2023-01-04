package com.ism.textencryptor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CeaserAlgorithms extends AppCompatActivity implements View.OnClickListener  {
    TextView receiver_msg;
    String val;
    String cipher;
    String plaintext;
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceaser_algorithms);
        receiver_msg = findViewById(R.id.received_value_id);
        Button DecryptButton = findViewById(R.id.DecryptButton);
        DecryptButton.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            val = extras.getString("input");
        }
        Ceaser ceaserEncrypt = new Ceaser();
        cipher = ceaserEncrypt.encryptData(val, 3);
        receiver_msg.setText("The CipherText is " + cipher);
    }
    private void displayAreaDialogMessage(String plaintext) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("The plaintext is " + plaintext)
                .setTitle("Ceaser decryption");

        builder.setPositiveButton(android.R.string.ok, (dialog, id) -> {

        });
        builder.setNegativeButton(android.R.string.cancel, (dialog, id) -> {

        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onClick(View v) {
        Ceaser ceaserDecrypt = new Ceaser();
        plaintext = ceaserDecrypt.decryptData(cipher,3);
        displayAreaDialogMessage(plaintext);
    }

    static class Ceaser {

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