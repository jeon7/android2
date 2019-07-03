package ch.teko.bruttonettorechner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int selectedTaxRatio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner_percent);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tax_ratio_string, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int[] taxRatio = getResources().getIntArray(R.array.tax_ratio_integer);
                selectedTaxRatio = taxRatio[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onButtonComputeClicked(View view){
        EditText moneyUserInput = findViewById(R.id.editText_money);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        RadioButton checkedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("moneyUserInput", moneyUserInput.getText().toString());
        intent.putExtra("checkedRadioButton", checkedRadioButton.getText().toString());
        intent.putExtra("selectedTaxRatio", selectedTaxRatio);
        startActivity(intent);
    }
}


