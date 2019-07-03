package ch.teko.bruttonettorechner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    TextView result_gross;
    TextView result_tax;
    TextView result_net;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle extras = getIntent().getExtras();
        String moneyUserInput = extras.getString("moneyUserInput");
        String checkedRadioButton = extras.getString("checkedRadioButton");
        int selectedTaxRatio = extras.getInt("selectedTaxRatio");

        result_gross = findViewById(R.id.textView_result_gross);
        result_tax = findViewById(R.id.textView_result_tax);
        result_net = findViewById(R.id.textView_result_net);
        computeAndDisplay(moneyUserInput, checkedRadioButton, selectedTaxRatio);

        //test
        String str = String.format("%s %s %d", moneyUserInput, checkedRadioButton, selectedTaxRatio);
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    private void computeAndDisplay(String moneyUserInput, String checkedRadioButton, int selectedTaxRatio){
        String gross_selected = getResources().getString(R.string.str_gross);
        String net_selected = getResources().getString(R.string.str_net);
        float gross;
        float net;

        if(checkedRadioButton.equals(gross_selected)) {
            gross = Float.parseFloat(moneyUserInput);
            net = gross / (1.0f + (float)selectedTaxRatio * 0.01f);
        } else if(checkedRadioButton.equals(net_selected)) {
            net = Float.parseFloat(moneyUserInput);
            gross = net * (1.0f + (float)selectedTaxRatio * 0.01f);
        } else {
            Log.e("ResultActivity", "computeDisplay() problem");
            Log.e("ResultActivity", "checkedRadioButton = " + checkedRadioButton +
                    ", gross_selected = " + gross_selected + ", net_selected = " + net_selected);
            gross = -1.0f;
            net = -1.0f;
        }

        result_gross.setText(String.valueOf(gross));
        result_tax.setText(String.valueOf(selectedTaxRatio));
        result_net.setText(String.valueOf(net));

    }
}
