package com.example.basiccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView result;
    boolean Positive = true;
    boolean EqualRepeat = false;
    boolean OperatorRepeat = false;
    int Num1=0, Num2=0;
    String Operator="+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.textViewResult);
    }

    public void ClickNumberFunction(View view) {
        if (view instanceof Button){
            Button button = (Button) view;
            String str = button.getText().toString();
            result.append(str);
        }
    }


    public void ChangeSignFunction(View view) {
        if (view instanceof Button){
            String str;
            if(Positive) {
                str = "-" + result.getText().toString();
            }
            else{
                str = result.getText().toString().substring(1);
            }
            Positive = !Positive;
            result.setText(str);
        }
    }

    @SuppressLint("SetTextI18n")
    public void ClickEqualsFunction(View view) {
        if (view instanceof Button){
            int num;
            // if no number entered
            try {
                num = Integer.parseInt(result.getText().toString());
            } catch (Exception e){
                num = 0;
            }
            try{
                // (result + number = new_result) for repeating equal
                if (EqualRepeat)
                        Num1 = num;
                else
                    Num2 = num;
                // switch case for the operator to calculate
                switch (Operator) {
                    case "+":
                        Num1 = Num1 + Num2;
                        break;
                    case "x":
                        Num1 = Num1 * Num2;
                        break;
                    case "-":
                        Num1 = Num1 - Num2;
                        break;
                    default:
                        // check devision by 0
                        if (Num2 != 0)
                            Num1 = Num1 / Num2;
                        else
                            throw new Exception("Cannot divide by zero");
                        break;
            }
            // show results
            result.setText(Integer.toString(Num1));
            } catch (Exception e) {
                result.setText(e.getMessage());
            } finally {
                // mark equal button was clicked
                Positive = Num1 >= 0;
                EqualRepeat = true;
                OperatorRepeat = false;
            }
            }
        }

    public void SetOperatorFunction(View view) {
        if (view instanceof Button){
            Button button = (Button) view;
            Operator = button.getText().toString();
            // if we repeated the operator button then we only
            // want to change the operator
            if (!OperatorRepeat) {
                // if no number entered
                try {
                    Num1 = Integer.parseInt(result.getText().toString());
                } catch (Exception e) {
                    Num1 = 0;
                }
            }
            // mark operator was clicked
            Positive = true;
            EqualRepeat = false;
            OperatorRepeat = true;
            result.setText("");
        }
    }

    public void ClickClearFunction(View view){
        if (view instanceof Button){
            // reset everything
            Num1 = 0;
            Num2 = 0;
            Positive = true;
            EqualRepeat = false;
            OperatorRepeat = false;
            Operator = "+";
            result.setText("");
        }
    }
}