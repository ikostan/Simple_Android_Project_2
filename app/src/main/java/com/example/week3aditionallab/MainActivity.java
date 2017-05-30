package com.example.week3aditionallab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int mealFactorIndx;
    private final int[] mealFactor = new int[]{1,2,3};
    private final double[] prices = new double[]{20.50, 10.40, 2.25};

    private RadioButton radio1, radio2, radio3;
    private ArrayList<RadioButton> btnArray = new ArrayList<>();
    private Spinner mealSize;
    private CheckBox cashCheck;
    private TextView txtResult, txtTotal;
    private Button btnCompute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setObjects(); //Instantiate all GUI objects
        this.setRadioBtn(); //Instantiate radio buttons
        this.setListArray(); //Put all radio buttons inside ArrayList

        //Spinner listener
        mealSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Log.d("testRadio", String.format("mealSize id: %d",position)); //DEBUG
                //Toast.makeText(getApplicationContext(), String.format("mealSize id: %d",position), Toast.LENGTH_SHORT).show(); //DEBUG
                mealFactorIndx = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnCompute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioId = testRadio();
                String message = "";

                if(radioId == -1){

                    message = "Please select one of the food items first.";
                    txtTotal.setText("$00.00");
                }
                else{

                    double mealPrice = prices[radioId] * mealFactor[mealFactorIndx]; //Calculate total price
                    DecimalFormat dFormat = new DecimalFormat("##.##");
                    txtTotal.setText(String.format("$%s", dFormat.format(mealPrice)));
                    message = String.format("$%s %s", dFormat.format(mealPrice), testCashBx());
                }

                //Print the result
                txtResult.setText(message);
                Toast.makeText(
                        getApplicationContext(),
                        message,
                        Toast.LENGTH_SHORT).show(); //Warning/Info message
            }
        });
    }

    //Instantiate radio buttons method
    private void setRadioBtn(){

        radio1 = (RadioButton) findViewById(R.id.radio1);
        radio2 = (RadioButton) findViewById(R.id.radio2);
        radio3 = (RadioButton) findViewById(R.id.radio3);
    }

    //Put all radio buttons inside ArrayList method
    private void setListArray(){

        btnArray.add(radio1);
        btnArray.add(radio2);
        btnArray.add(radio3);
    }

    //Find selected radio button method
    private int testRadio(){

        int testId = 0;
        int id = -1;

        for(RadioButton btn:btnArray){

            if(btn.isChecked() == true){
                //testId++;
                id = testId;
                break;
            }
            testId++;
        }

        Log.d("testRadio", String.format("Selected radio: %d",id)); //DEBUG
        //Toast.makeText(this.getApplicationContext(), String.format("Selected radio: %d",id), Toast.LENGTH_SHORT).show(); //DEBUG
        return id;
    }

    //Test is "Pay by Cash" checked
    private String testCashBx(){

        String cash = "";

        if(cashCheck.isChecked()){
            cash = "in cash";
        }

        return cash;
    }

    //Instantiate all GUI objects method
    private void setObjects(){

        txtTotal = (TextView) findViewById(R.id.txtTotal);
        txtResult = (TextView) findViewById(R.id.txtResult);
        btnCompute = (Button) findViewById(R.id.btnCompute);
        cashCheck = (CheckBox) findViewById(R.id.cashCheck);
        mealSize = (Spinner) findViewById(R.id.mealSize);
    }

    //END
}
