package ch.teko.bruttonettorechner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    public final static String TAG = "ResultActivity";
    TextView displayGross;
    TextView displayTax;
    TextView displayNet;
    float resultGross;
    float resultNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle extras = getIntent().getExtras();
        String moneyUserInput = extras.getString("moneyUserInput");
        String checkedRadioButton = extras.getString("checkedRadioButton");
        int selectedTaxRatio = extras.getInt("selectedTaxRatio");

        displayGross = findViewById(R.id.textView_result_gross);
        displayTax = findViewById(R.id.textView_result_tax);
        displayNet = findViewById(R.id.textView_result_net);
        computeTax(moneyUserInput, checkedRadioButton, selectedTaxRatio);

        displayGross.setText(String.valueOf(resultGross));
        displayTax.setText(String.valueOf(selectedTaxRatio));
        displayNet.setText(String.valueOf(resultNet));

        //test
        String str = String.format("%s %s %d", moneyUserInput, checkedRadioButton, selectedTaxRatio);
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    private void computeTax(String moneyUserInput, String checkedRadioButton, int selectedTaxRatio){
        String gross_selected = getResources().getString(R.string.all_gross);
        String net_selected = getResources().getString(R.string.all_net);

        if(checkedRadioButton.equals(gross_selected)) {
            resultGross = Float.parseFloat(moneyUserInput);
            resultNet = resultGross / (1.0f + (float)selectedTaxRatio * 0.01f);
        } else if(checkedRadioButton.equals(net_selected)) {
            resultNet = Float.parseFloat(moneyUserInput);
            resultGross = resultNet * (1.0f + (float)selectedTaxRatio * 0.01f);
        } else {
            Log.d(TAG, "computeDisplay() problem");
            Log.d(TAG, "checkedRadioButton = " + checkedRadioButton +
                    ", gross_selected = " + gross_selected + ", net_selected = " + net_selected);
            resultGross = -1.0f;
            resultNet = -1.0f;
        }
    }
}
