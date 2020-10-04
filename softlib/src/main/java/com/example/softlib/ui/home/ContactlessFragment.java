package com.example.softlib.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.softlib.R;
import com.kaopiz.kprogresshud.KProgressHUD;

public class ContactlessFragment extends AppCompatActivity {
    private KProgressHUD progressHUD;
    private String enteredAmount;
    private View dotFirst;
    private View dotSecond;
    private View dotThird;
    private View dotFourth;
    private TextView messageTV;
    private TextView sarArabic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactless_frament);
         dotFirst = (View) findViewById(R.id.dotFirst);
         dotSecond = (View) findViewById(R.id.dotSecond);
         dotThird = (View) findViewById(R.id.dotThird);
         dotFourth = (View) findViewById(R.id.dotFourth);
         messageTV = (TextView) findViewById(R.id.messageTV);
         sarArabic = (TextView) findViewById(R.id.sar_in_arabic);
        enteredAmount = getIntent().getStringExtra("EXTRA_AMOUNT");
        if (enteredAmount.startsWith("succesful")) {
            Log.e("amount", "succesful" + enteredAmount);
            dotFirst.setBackgroundResource(R.drawable.elipse_green);
            dotSecond.setBackgroundResource(R.drawable.elipse_green);
            dotThird.setBackgroundResource(R.drawable.elipse_green);
            dotFourth.setBackgroundResource(R.drawable.elipse_green);
            sarArabic.setVisibility(View.GONE);
            messageTV.setText("Thank you for using SoftPos");
        }
        Log.d("amount", "contact less fragment" + enteredAmount);
        TextView tvTotalAmt = (TextView) findViewById(R.id.totalAmountTV);
        tvTotalAmt.setText(enteredAmount);
        // checkPOSService();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.tap_card_layout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReceiptTotalFragment.class);

                Log.d("amount", "amount fragment" + enteredAmount);
                intent.putExtra("EXTRA_AMOUNT", enteredAmount);
                startActivityForResult(intent,2);

             //   finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==2){
            if(resultCode == 2) {
                Log.e("amount", "succesful" + enteredAmount);
                dotFirst.setBackgroundResource(R.drawable.elipse_green);
                dotSecond.setBackgroundResource(R.drawable.elipse_green);
                dotThird.setBackgroundResource(R.drawable.elipse_green);
                dotFourth.setBackgroundResource(R.drawable.elipse_green);
                sarArabic.setVisibility(View.GONE);
                messageTV.setText("Thank you for using SoftPos");
            }
        }
    }

    private void checkPOSService() {
        progressHUD = KProgressHUD.create(getApplicationContext())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(getResources().getString(R.string.check_pos_service))
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
        //  getProjectApplication().getSoftPosUtil().checkPOSService(this);
    }
}
