package com.example.ehu.first_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {
    //一時保存
    BigDecimal temp = new BigDecimal("0");
    //a+b, a-bなど
    String a = "0", b = "0";
    //計算結果
    String sum;
    //記号を判断する
    int markCheck = 0;

    //桁を保存する変数
    int chara = 0;

    //resultViewに表示する変数
    String result = "";
    //小数点フラグ
    boolean isPointNum = false;
    //小数入力時に入力値の位をずらすための
    double pointCnt = 10;

    //電卓の計算結果のTextView
    TextView resultText;
    double pointDivideNum = 1;

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
            chara++;
            //タグを文字列に変換
            String numString = String.valueOf(v.getTag());
            BigDecimal bdNumString = new BigDecimal(numString);
            if (isPointNum == false) {

                temp=temp.multiply(new BigDecimal(10)).add(bdNumString);
            }
//            else {
//                pointDivideNum = pointDivideNum / 10;
//                temp = temp + temp * pointDivideNum;
//            }


//            //TextViewにnumの値を反映させる
//            if((int)temp - temp == 0){
//                resultText.setText(String.valueOf((int)temp));
//            }else{
//                resultText.setText(String.valueOf(temp));
//            }
            resultText.setText(temp.toString());
        }
    }

//    //記号を押した時の処理
//    public void markClick(View v) {
//        //タグを文字列に変換
//        String num = String.valueOf(v.getTag());
//        //文字列をintへ返還させる
//        //0:足し算 1:引き算 2:掛け算 3:割り算
//        markCheck = Integer.parseInt(num);
//        //tempの値をaに代入する
//        a = temp;
//        //tempの初期化
//        temp = "0";
//
//        //resultの初期化
//        result = "";
//        //小数点のフラグを初期化
//        isPointNum = false;
//        //pointCntの初期化
//        pointCnt = 10;
//    }

//    //=を押した時の処理
//    public void equalClick(View v) {
//        //tempの値をbに代入する
//        b = temp;
//        //tempの初期化
//        temp = "0";
//
//        //小数点のフラグを初期化
//        isPointNum = false;
//
//        //mark_Checkに対応した四則演算をさせる
//        switch (markCheck) {
//            case 0:
//                sum = a + b;
//                break;
//            case 1:
//                sum = a - b;
//                break;
//            case 2:
//                sum = a * b;
//                break;
//            case 3:
//                if (b != 0) {
//                    sum = a / b;
//                }
//                break;
//            default:
//                break;
//        }
//        //sumの値をresultTextに反映させる
//        resultText.setText(conFormat(String.valueOf(sum)));
//    }
//
//    //Cを押した時の処理
//    public void clearClick(View v) {
//        //値の初期化
//        a = 0;
//        b = 0;
//        temp = 0;
//        sum = 0;
//
//        //小数点のフラグを初期化
//        isPointNum = false;
//        //pointCntの初期化
//        pointCnt = 10;
//        //resultTextに反映させる
//        result = "";
//        resultText.setText("0");
//    }
//
//    //小数点メソッド
//    public void pointNum(View v) {
//        if (isPointNum == false) {
//            //小数点をつける
//            result = String.valueOf((int) temp) + ".";
//            //小数点をつけたフラグ
//            isPointNum = true;
//            //TextViewにnumの値を反映させる
//            resultText.setText(result);
//        }
//
//    }
//
//    //「.0」を削除するメソッド
//    public String conFormat(String stSum) {
//        if (stSum.endsWith(".0") == true) {
//            stSum = stSum.substring(0, stSum.length() - 2);
//        }
//        return stSum;
//    }
}