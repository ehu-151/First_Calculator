package com.example.ehu.first_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //一時保存
    int temp = 0;
    //a+b, a-bなど
    int a = 0, b = 0;
    //計算結果
    int sum;
    //記号を判断する
    int markCheck = 0;

    //電卓の計算結果のTextView
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //関連付け
        resultText = (TextView) findViewById(R.id.resultText);
    }

    //数字を押した時の処理
    public void numClick(View v) {
        if (resultText.length() < 9) {
            //タグを文字列に変換
            String numString = String.valueOf(v.getTag());
            //文字列をintへ変換する
            temp = temp * 10 + Integer.parseInt(numString);
            //TextViewにnumの値を反映させる
            resultText.setText(String.valueOf(temp));
        }
    }

    //記号を押した時の処理
    public void markClick(View v) {
        //タグを文字列に変換
        String num = String.valueOf(v.getTag());
        //文字列をintへ返還させる
        //0:足し算 1:引き算 2:掛け算 3:割り算
        markCheck = Integer.parseInt(num);
        //tempの値をaに代入する
        a = temp;
        //tempの初期化
        temp = 0;
    }

    //=を押した時の処理
    public void equalClick(View v) {
        //tempの値をbに代入する
        b = temp;
        //tempの初期化
        temp = 0;

        //mark_Checkに対応した四則演算をさせる
        switch (markCheck) {
            case 0:
                sum = a + b;
                break;
            case 1:
                sum = a - b;
                break;
            case 2:
                sum = a * b;
                break;
            case 3:
                sum = a / b;
                break;
            default:
                break;
        }
        //sumの値をresultTextに反映させる
        resultText.setText(String.valueOf(sum));
    }

    //Cを押した時の処理
    public void clearClick(View v) {
        //値の初期化
        a = 0;
        b = 0;
        temp = 0;
        sum = 0;

        //sumの値をresultTextに反映させる
        resultText.setText(String.valueOf(sum));
    }
}
