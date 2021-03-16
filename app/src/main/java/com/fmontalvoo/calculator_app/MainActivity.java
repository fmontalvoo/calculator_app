package com.fmontalvoo.calculator_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {
    private EditText display;
    private String txt;
    private boolean existsPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.display = findViewById(R.id.display);
        this.display.setShowSoftInputOnFocus(false);
    }

    public void updateText(String strToAdd) {
        String oldStr = display.getText().toString();
        int cursorPosition = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPosition);
        String rightStr = oldStr.substring(cursorPosition);
        display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
        display.setSelection(cursorPosition + 1);
    }

    public void btnZero(View view) {
        updateText(getString(R.string.zero));
    }

    public void btnOne(View view) {
        updateText(getString(R.string.one));
    }

    public void btnTwo(View view) {
        updateText(getString(R.string.two));
    }

    public void btnThree(View view) {
        updateText(getString(R.string.three));
    }

    public void btnFour(View view) {
        updateText(getString(R.string.four));
    }

    public void btnFive(View view) {
        updateText(getString(R.string.five));
    }

    public void btnSix(View view) {
        updateText(getString(R.string.six));
    }

    public void btnSeven(View view) {
        updateText(getString(R.string.seven));
    }

    public void btnEight(View view) {
        updateText(getString(R.string.eight));
    }

    public void btnNine(View view) {
        updateText(getString(R.string.nine));
    }


    public void btnBackspace(View view) {
        int cursorPosition = display.getSelectionStart();
        int textLen = display.getText().length();
        if (cursorPosition != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPosition - 1, cursorPosition, "");
            display.setText(selection);
            display.setSelection(cursorPosition - 1);
        }
    }

    public void btnClear(View view) {
        display.setText("");
    }

    public void btnParentheses(View view) {
        int cursorPosition = display.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        String txt = display.getText().toString();
        int textLen = txt.length();
        for (int i = 0; i < cursorPosition; i++) {
            if (txt.substring(i, i + 1).equals("(")) openPar++;
            if (txt.substring(i, i + 1).equals(")")) closedPar++;
        }
        if (openPar == closedPar || txt.substring(textLen - 1, textLen).equals(")"))
            updateText("(");
        else if (closedPar < openPar && !txt.substring(textLen - 1, textLen).equals(")"))
            updateText(")");

        display.setSelection(cursorPosition + 1);
    }

    public void btnExponent(View view) {
        updateText(getString(R.string.exponent));
    }

    public void btnDivide(View view) {
        updateText(getString(R.string.divide));
    }

    public void btnMultiply(View view) {
        updateText(getString(R.string.multiply));
    }

    public void btnSubtract(View view) {
        updateText(getString(R.string.subtract));
    }

    public void btnAdd(View view) {
        updateText(getString(R.string.add));
    }

    public void btnPlusMinus(View view) {
        updateText(getString(R.string.subtract));
    }

    public void btnPoint(View view) {
//        int cursorPosition = display.getSelectionStart();
//        int point = 0;
//        String txt = display.getText().toString();
//        for (int i = 0; i < cursorPosition; i++) {
//            if (txt.substring(i, i + 1).equals("."))
//                point++;
//        }
//        if (point < 1)
            updateText(getString(R.string.point));
    }

    public void btnEquals(View view) {
        String inExp = display.getText().toString();
        inExp = inExp.replace("÷", "/").replace("×", "*");
        Expression exp = new Expression(inExp);
        String result = String.valueOf(exp.calculate());
        display.setText(result);
        display.setSelection(result.length());
    }

}