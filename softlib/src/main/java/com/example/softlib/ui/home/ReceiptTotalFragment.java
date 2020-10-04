package com.example.softlib.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.softlib.R;

public class ReceiptTotalFragment extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_total);
Button btnDone = (Button) findViewById(R.id.done);
btnDone.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ContactlessFragment.class);


        intent.putExtra("EXTRA_AMOUNT", "Succesful transaction with SoftPos!");
        setResult(2,intent);
        finish();
    }
});
            }
}
