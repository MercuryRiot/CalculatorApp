package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView inputTextView;
    private TextView outputTextView;
    private String currentInput = "";
    private double result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTextView = findViewById(R.id.inputTextView);
        outputTextView = findViewById(R.id.outputTextView);
    }

    public void onNumberClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        currentInput += buttonText;
        updateTextViews();
    }

    public void onOperatorClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        currentInput += " " + buttonText + " ";
        updateTextViews();
    }

    public void onEqualClick(View view) {
        String[] parts = currentInput.split(" ");

        if (parts.length != 3) {
            outputTextView.setText(getString(R.string.error_message)); // Use the string resource
            return;
        }

        double num1 = Double.parseDouble(parts[0]);
        String operator = parts[1];
        double num2 = Double.parseDouble(parts[2]);

        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    outputTextView.setText(getString(R.string.error_message)); // Use the string resource
                    return;
                }
                break;
            default:
                outputTextView.setText(getString(R.string.error_message)); // Use the string resource
                return;
        }

        outputTextView.setText(String.valueOf(result));
        currentInput = String.valueOf(result);
    }


    public void onClearClick(View view) {
        currentInput = "";
        result = 0;
        updateTextViews();
    }

    public void onBackspaceClick(View view) {
        if (!currentInput.isEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
            updateTextViews();
        }
    }

    private void updateTextViews() {
        inputTextView.setText(currentInput);
        outputTextView.setText("");
    }
}
