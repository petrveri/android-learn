package com.et4334.appofpetr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class CalcActivity extends BaseFeatureActivity {

    private static final String EXPR_KEY = "CALC_EXPR";
    public String expr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        if (savedInstanceState != null) {
            expr = savedInstanceState.getString(EXPR_KEY);
            showExpr();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_calc_close) {
            gotoMainActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EXPR_KEY, expr);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        expr = savedInstanceState.getString(EXPR_KEY);
        showExpr();
    }

    public void onClickBtn7(View view) {
        handleClick("7");
    }

    public void onClickBtn8(View view) {
        handleClick("8");
    }

    public void onClickBtn9(View view) {
        handleClick("9");
    }

    public void onClickBtnDiv(View view) {
        handleClick("/");
    }

    public void onClickBtn4(View view) {
        handleClick("4");
    }

    public void onClickBtn5(View view) {
        handleClick("5");
    }

    public void onClickBtn6(View view) {
        handleClick("6");
    }

    public void onClickBtnMult(View view) {
        handleClick("*");
    }

    public void onClickBtn1(View view) {
        handleClick("1");
    }

    public void onClickBtn2(View view) {
        handleClick("2");
    }

    public void onClickBtn3(View view) {
        handleClick("3");
    }

    public void onClickBtnMinus(View view) {
        handleClick("-");
    }

    public void onClickBtnDot(View view) {
        handleClick(".");
    }

    public void onClickBtn0(View view) {
        handleClick("0");
    }

    public void onClickBtnC(View view) {
        handleClick("C");
    }

    public void onClickBtnPlus(View view) {
        handleClick("+");
    }

    public void onClickBtnEquals(View view) {
        handleClick("=");
    }

    private void handleClick(String value) {
        expr = expr.concat(value);
        showExpr();
        showToast(value);
    }

    private void showExpr() {
        ((TextView) findViewById(R.id.textCalcExpr)).setText(expr);
    }

    private void showToast(String value) {
        Toast toast = Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT);
        toast.show();
    }

}
