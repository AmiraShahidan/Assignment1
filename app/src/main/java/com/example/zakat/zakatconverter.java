package com.example.zakat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class zakatconverter extends AppCompatActivity implements View.OnClickListener {

    EditText etgram;
    Button btnconvert;
    TextView zakatoutput;
    Toolbar converterToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zakatconverter);

        etgram = findViewById(R.id.etGoldWeight);
        btnconvert = findViewById(R.id.btnconvert);
        zakatoutput = findViewById((R.id.zakatoutput));

        btnconvert.setOnClickListener(this);

        converterToolbar = findViewById(R.id.converter_toolbar);
        setSupportActionBar(converterToolbar);
        getSupportActionBar().setTitle("ZAKAT CONVERTER");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnconvert) {
            EditText etGram = findViewById(R.id.etGoldWeight);
            RadioGroup radioGroup = findViewById(R.id.radioGroup);

            if (etGram.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter the weight in grams", Toast.LENGTH_SHORT).show();
                return;
            }

            double gram = Double.parseDouble(etGram.getText().toString());
            String goldType = "";

            int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
            if (checkedRadioButtonId == R.id.radio_one) {
                goldType = "Keep";
            } else if (checkedRadioButtonId == R.id.radio_two) {
                goldType = "Wear";
            }

            RadioGroup goldValueRadioGroup = findViewById(R.id.radioGroupGoldValuePerGram);
            int checkedGoldValueId = goldValueRadioGroup.getCheckedRadioButtonId();
            double goldValuePerGram = 0.0;

            if (checkedGoldValueId == R.id.radio_gold_250) {
                goldValuePerGram = 250;
            } else if (checkedGoldValueId == R.id.radio_gold_280) {
                goldValuePerGram = 280;
            } else {
                Toast.makeText(this, "Please select the current gold value per gram", Toast.LENGTH_SHORT).show();
                return;
            }



            // Perform zakat calculation based on the inputs
            calculateZakat(gram, goldType, goldValuePerGram);
        }
    }

    public void calculateZakat(double weightInGrams, String goldType, double goldValuePerGram) {
        double goldValue = weightInGrams * goldValuePerGram; // Total value of the gold
        double totalGoldValueZakatPayable = 0.0; // Total gold value that is zakat payable
        double zakatValue = 0.0; // Total zakat

        String goldTypeLabel = goldType.equals("Keep") ? "Keep" : "Wear";
        double xGram = goldType.equals("Keep") ? 85 : 200;

        // Calculate total gold value that is zakat payable and total zakat based on gold type and weight
        if (weightInGrams > xGram) {
            totalGoldValueZakatPayable = (weightInGrams - xGram) * goldValuePerGram;
            zakatValue = totalGoldValueZakatPayable * 0.025;
        }

        // Update the TextView with the calculated zakat value and other information
        TextView zakatOutputTextView = findViewById(R.id.zakatoutput);
        String output = "Gold Type: " + goldTypeLabel +
                "\nGold Weight: " + weightInGrams +
                "\nGold Value Per Gram: RM" + goldValuePerGram +
                "\nGold Weight Minus X: " + (weightInGrams - xGram) +
                "\nTotal Gold Value: RM" + goldValue +
                "\nTotal Gold Value that is Zakat Payable: RM" + totalGoldValueZakatPayable +
                "\nTotal Zakat: RM" + zakatValue;
        zakatOutputTextView.setText(output);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}