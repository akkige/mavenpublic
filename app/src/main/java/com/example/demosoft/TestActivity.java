package com.example.demosoft;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.softlib.ui.home.AmountFragment;

public class TestActivity extends AppCompatActivity {

    private AmountFragment fragment = new AmountFragment();
    private EditText edt_amount;
    private Button mSubmitBtn;
    private String enteredAmount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);

        enteredAmount = getIntent().getStringExtra("EXTRA_AMOUNT");
        if(enteredAmount!=null){
            edt_amount.setText(enteredAmount);
            mSubmitBtn.setText(" Thank you");
            mSubmitBtn.setEnabled(false);
        }
        Log.d("amount", "entered B" + enteredAmount);
        edt_amount = (EditText) findViewById(R.id.ed_enter_amount);

        Log.d("amount","entered A"+enteredAmount);
        mSubmitBtn = (Button) findViewById(R.id.btn_submit);

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AmountFragment.class);
                enteredAmount = edt_amount.getText().toString();
                Log.d("amount","entered A"+enteredAmount);
                intent.putExtra("EXTRA_AMOUNT", enteredAmount);
                startActivity(intent);
            }
        });
    }

}
