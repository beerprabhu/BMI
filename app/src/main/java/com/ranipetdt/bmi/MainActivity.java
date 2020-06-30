package com.ranipetdt.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
private Button bmicalculator;
private EditText weight,height;
    private static DecimalFormat df = new DecimalFormat("0.00");
private ImageView imageView;
    String BW;
    String BH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bmicalculator = findViewById(R.id.btn_BmiCal);
        weight = findViewById(R.id.txt_bmiWeight);
        height = findViewById(R.id.txt_bmiHeight);
        //BmiResult = findViewById(R.id.txt_view);
        weight.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(4, 2)});
        height.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(4, 2)});
        weight.addTextChangedListener(textWatcher);
        height.addTextChangedListener(textWatcher);
imageView = findViewById(R.id.img_view);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
             BW = weight.getText().toString();
             BH = height.getText().toString();

             bmicalculator.setEnabled(BW !=""&&!BW.isEmpty()&& !BH.isEmpty()&&BH!="");
           //  BmiResult.setText("");
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void bmiCalculator(View view) {
        try {
         BW = weight.getText().toString();
         BH = height.getText().toString();
    Float bmiH = Float.parseFloat(BH);
    Float bmiW = Float.parseFloat(BW);
    float bh = bmiH/100;

    Float bmi = (bmiW / (bh * bh));
    String lbl = "";
            if (Float.compare(bmi, 15f) <= 0) {
                lbl = getString(R.string.bmiVSUnder);

            } else if (Float.compare(bmi, 15f) > 0  &&  Float.compare(bmi, 16f) <= 0) {
                lbl = getString(R.string.bmiSUnder);
            } else if (Float.compare(bmi, 16f) > 0  &&  Float.compare(bmi, 18.5f) <= 0) {
                lbl = getString(R.string.bmiUnder);
            } else if (Float.compare(bmi, 18.5f) > 0  &&  Float.compare(bmi, 25f) <= 0) {
                lbl = getString(R.string.bmiNormal);
            } else if (Float.compare(bmi, 25f) > 0  &&  Float.compare(bmi, 30f) <= 0) {
                lbl = getString(R.string.bmiover);
            } else if (Float.compare(bmi, 30f) > 0  &&  Float.compare(bmi, 35f) <= 0) {
                lbl = getString(R.string.Mobesity);

            } else if (Float.compare(bmi, 35f) > 0  &&  Float.compare(bmi, 40f) <= 0) {
                lbl = getString(R.string.Sobesity);
            } else {
                lbl = getString(R.string.VSobesity);
            }

            try{

                // Creating alert Dialog with one Button

                AlertDialog alertDialog1 = new AlertDialog.Builder(MainActivity.this).create();

                // Setting Dialog Title
                alertDialog1.setTitle("BMI CALCULATOR");

                // Setting Dialog Message
                alertDialog1.setMessage(df.format(bmi) + "\n\n" + lbl);

                // Setting Icon to Dialog
                alertDialog1.setIcon(R.drawable.ic_heart);

                // Setting OK Button
                alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        // closed
//                        Toast.makeText(getApplicationContext(),
//                                "You clicked on OK", Toast.LENGTH_SHORT).show();
                    }
                });

                // Showing Alert Message
                alertDialog1.show();
            }catch (Exception e)
            {e.printStackTrace();}
                //BmiResult.setText(df.format(img) + "\n\n" + lbl);


        }catch (Exception e) {
            e.printStackTrace();
            Log.e("IO","IO"+e);

        }

    }
    class DecimalDigitsInputFilter implements InputFilter {
        private Pattern mPattern;
        DecimalDigitsInputFilter(int digitsBeforeZero, int digitsAfterZero) {
            mPattern = Pattern.compile("[0-9]{0," + (digitsBeforeZero - 1) + "}+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?");
        }
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Matcher matcher = mPattern.matcher(dest);
            if (!matcher.matches())
                return "";
            return null;
        }
}}
