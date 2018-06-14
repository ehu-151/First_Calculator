package com.example.ehu.first_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    //一時保存
    BigDecimal temp = new BigDecimal(0);
    //a+b, a-bなど
    BigDecimal a = new BigDecimal(0), b = new BigDecimal(0);
    //計算結果
    BigDecimal sum;
    //記号を判断する
    int markCheck = 0;

    //桁を保存する変数
    int chara = 0;

    //小数点フラグ
    boolean isPointNum = false;

    //電卓の計算結果のTextView
    TextView resultText;

    //押した数字が小数になる時、その数を10^nで割ります。
    //例：電卓画面が「23.」の時、「7」を押す。pointDivideNumを10で割り、「7」とかけます。
    //その数を電卓画面の数字を足し合わせ「23.7」にする。
    //numClick()を参照
    int pointDivideNum = 1;

    //画面上に表示するフォーマットを指定します。
    //「23.03」の「0」を押した時の表示を可能にします。
    DecimalFormat format = new DecimalFormat("#.#");
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
                temp = temp.multiply(new BigDecimal(10)).add(bdNumString);
            } else {
                //指定小数点以下の計算だけ
                BigDecimal decimal=bdNumString.divide(new BigDecimal(1/(Math.pow(10,-pointDivideNum))));

                //フォーマットを指定します。
                format.setMaximumFractionDigits(pointDivideNum);
                format.setMinimumFractionDigits(pointDivideNum);
                //tempと指定小数点以下の足し算
                temp = temp.add(decimal);

                //formatを決める
                //format.format(temp)はString型が返ってくるのでBigDecimal1で型を生成、代入
                temp = new BigDecimal(format.format(temp));

                pointDivideNum++;
            }


//            //TextViewにnumの値を反映させる
//            if((int)temp - temp == 0){
//                resultText.setText(String.valueOf((int)temp));
//            }else{
//                resultText.setText(String.valueOf(temp));
//            }
            resultText.setText(temp.toString());
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
        temp = new BigDecimal(0);

        //小数点のフラグを初期化
        isPointNum = false;

        //pointDivideNumを初期化
        pointDivideNum = 1;
    }

    //=を押した時の処理
    public void equalClick(View v) {
        //tempの値をbに代入する
        b = temp;
        //tempの初期化
        temp = new BigDecimal(0);

        //小数点のフラグを初期化
        isPointNum = false;

        //mark_Checkに対応した四則演算をさせる
        switch (markCheck) {
            case 0:
                sum = a.add(b);
                break;
            case 1:
                sum = a.subtract(b);
                break;
            case 2:
                sum = a.multiply(b);
                break;
            case 3:
                if (b.toString() != "0") {
                    sum = a.divide(b);
                }
                break;
            default:
                break;
        }
        //sumの値をresultTextに反映させる
        resultText.setText(sum.toString());
    }

    //Cを押した時の処理
    public void clearClick(View v) {
        //値の初期化
        a = new BigDecimal(0);
        b = new BigDecimal(0);
        temp = new BigDecimal(0);
        sum = new BigDecimal(0);

        //小数点のフラグを初期化
        isPointNum = false;

        //pointDivideNumを初期化
        pointDivideNum = 1;

        //resultTextに反映させる
        resultText.setText("0");
    }

    //小数点メソッド
    public void pointNum(View v) {
        if (!isPointNum) {
            //小数点をつけたフラグ
            isPointNum = true;

            //TextViewにnumの値を反映させる
            resultText.setText(resultText.getText().toString() + ".");
        }

    }
//
//    //「.0」を削除するメソッド
//    public String conFormat(String stSum) {
//        if (stSum.endsWith(".0") == true) {
//            stSum = stSum.substring(0, stSum.length() - 2);
//        }
//        return stSum;
//    }
}