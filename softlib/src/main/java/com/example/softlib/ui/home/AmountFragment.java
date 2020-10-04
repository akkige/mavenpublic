package com.example.softlib.ui.home;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.softlib.R;

public class AmountFragment extends AppCompatActivity {
    private static final int REQUEST_LOCATION = 18;
    private int amount = 0;
    private Short transactionType;
    private boolean isExternalDevice;
    //    private TransactionViewModel viewModel;
    private String stringAmount = "";
    private Dialog dialog;
    LocationManager locationManager;
    String latitude, longitude;
    private TextView lib_text;
    private Button btnProceed;
    private String enteredAmount;
    private EditText edAmount;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.fragment_amount);
        enteredAmount = getIntent().getStringExtra("EXTRA_AMOUNT");
        Log.d("amount", "entered B" + enteredAmount);
        edAmount = (EditText) findViewById(R.id.amount);
        edAmount.setText(enteredAmount);

        btnProceed = (Button) findViewById(R.id.okBTN);
        // btnProceed.setText(sessionId);
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ContactlessFragment.class);

                Log.d("amount", "amount fragment" + enteredAmount);
                intent.putExtra("EXTRA_AMOUNT", enteredAmount);
                startActivity(intent);
                finish();
            }
        });
    }


}
