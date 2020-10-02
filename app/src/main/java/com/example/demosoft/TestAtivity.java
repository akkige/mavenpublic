package com.example.demosoft;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.softlib.ValidateTor;

public class TestAtivity extends AppCompatActivity {

    private Button btn_validate;

    private EditText edt_email;
    private String str_email;
    private EditText edt_pas;
    private String str_pas;
    private EditText edt_creditcard;
    private String str_creditcard;
    private ValidateTor mValidateTor = new ValidateTor();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        btn_validate = (Button) findViewById(R.id.btn_validate);
        edt_email = (EditText) findViewById(R.id.edt_email);
        str_email = edt_email.getText().toString();

        edt_pas = (EditText) findViewById(R.id.edt_password);
        str_pas = edt_pas.getText().toString();

        edt_creditcard = (EditText) findViewById(R.id.edt_creditcard);
        str_creditcard = edt_creditcard.getText().toString();

        setUpUiWidgets();
    }

    private void setUpUiWidgets() {
        btn_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateEmailField(str_email);
                validatePasswordField(str_pas);
                validateCreditCardField(str_creditcard);
            }
        });
    }

    private void validatePasswordField(String pas) {
        if (mValidateTor.isEmpty(pas)) {
            edt_pas.setHint("Empty");
            edt_pas.setHintTextColor(getResources().getColor(R.color.colorAccent));
        }

        if (mValidateTor.isAtleastLength(pas, 8)
                && mValidateTor.hasAtleastOneDigit(pas)
                && mValidateTor.hasAtleastOneUppercaseCharacter(pas)
                && mValidateTor.hasAtleastOneSpecialCharacter(pas)) {
            Toast.makeText(this, "Valid Password!", Toast.LENGTH_SHORT).show();
        } else {
            edt_pas.setText("Password needs to be a minimum length of 8" +
                    " characters and should have at least 1 digit, 1 upppercase letter and 1 special " +
                    "character ");
        }
    }

    private void validateEmailField(String email) {
        if (mValidateTor.isEmpty(email)) {
            edt_email.setHint("Empty");
            edt_email.setHintTextColor(getResources().getColor(R.color.colorAccent));
        }

    }

    private void validateCreditCardField(String creditcard) {
        if (mValidateTor.isEmpty(creditcard)) {
            edt_creditcard.setHint("Empty");
            edt_creditcard.setHintTextColor(getResources().getColor(R.color.colorAccent));
        }
        if (!mValidateTor.validateCreditCard(creditcard)) {
            edt_creditcard.setText("Invalid Credit Card number");
            edt_creditcard.setTextColor(getResources().getColor(R.color.colorAccent));

        } else {
            Toast.makeText(this, "Valid Credit Card Number!", Toast.LENGTH_SHORT).show();
        }
    }
}
