package anglehack.grabngo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import com.molpay.molpayxdk.MOLPayActivity;
import com.molpay.molpayxdk.MOLPayService;
import java.util.HashMap;


public class MolPay extends AppCompatActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MOLPayActivity.MOLPayXDK && resultCode == RESULT_OK){
            Log.d(MOLPayActivity.MOLPAY, "MOLPay result 1234 = " + data.getStringExtra(MOLPayActivity.MOLPayTransactionResult));
            TextView tw = (TextView)findViewById(R.id.molPayStatus);
            tw.setText(data.getStringExtra(MOLPayActivity.MOLPayTransactionResult));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mol_pay);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        HashMap<String, Object> paymentDetails = new HashMap<>();

        paymentDetails.put("mp_amount" , "12.45");
        paymentDetails.put("mp_username" , "api_MOLWallet_Dev1");
        paymentDetails.put("mp_password" , "api_walletdev1");
        paymentDetails.put("mp_merchant_ID" , "MOLWallet_Dev1");
        paymentDetails.put("mp_app_name" , "ahkl01");
        paymentDetails.put("mp_verification_key" , "c6c5cdaabf772a366e1b5ba8a512afac");
        paymentDetails.put("mp_order_ID" , "1221");
        paymentDetails.put("mp_currency" , "MYR");
        paymentDetails.put("mp_country" , "MY");
        paymentDetails.put("mp_bill_description" , "test desc");
        paymentDetails.put("mp_bill_name" , "test name");
        paymentDetails.put("mp_bill_email" , "test@mail.com");
        paymentDetails.put("mp_bill_mobile" , "01293120938");
        paymentDetails.put("domain_mode", "1");

        Intent intent = new Intent(MolPay.this, MOLPayActivity.class);
        intent.putExtra(MOLPayActivity.MOLPayPaymentDetails, paymentDetails);
        startActivityForResult(intent, MOLPayActivity.MOLPayXDK);



        //transactionRequest example
        MOLPayService mpservice = new MOLPayService();
        mpservice.transactionRequest(paymentDetails, new MOLPayService.Callback() {
            @Override
            public void onTransactionRequestCompleted(String result) {
                Log.d("d", "Completed");
                Log.d(MOLPayActivity.MOLPAY, "onTransactionRequestCompleted result= " + result);
            }

            @Override
            public void onTransactionRequestFailed(String error) {
                Log.d("d", "Failed");
                Log.d(MOLPayActivity.MOLPAY, "onTransactionRequestCompleted result= " + error);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
