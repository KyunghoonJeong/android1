package com.jkh.bubble;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btn_start;
    Button[] btns;  //버튼 9개를 담고 있는 배열
    int[] numArray; //랜덤한 숫자 9개를 담고 있는 배열
    int answer; // 현재 버튼을 눌렀을 때, 사라져야 하는 숫자 !
    int cnt; //makeRandom()에서 9이후에 더해줄 숫자
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = findViewById(R.id.btn_start);

        btns = new Button[9];
        numArray = new int[9];
        answer = 1; //1부터 시작 !


        for (int i = 0; i < 9; i++) {

            int resID = getResources().getIdentifier("btn" + (i + 1), "id", getPackageName());
            //어디서 꺼내오냐 id에서 , btn i+1 을 찾음
            btns[i]=findViewById(resID);
            btns[i].setVisibility(View.INVISIBLE);
            //잘 초기화됐다면 버튼이 사라질 것이다.


        }
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btn_start.setClickable(false);
                btn_start.setText("게임중");

                 makeRandom(cnt);

                //다른 풀이
                //랜덤한 두값 치환해서 한 100번정도 진행
//                for(int i = 0 ; i < numArray.length ; i++){
//                    numArray[i] = i+1;
//                }
//                for(int i =0; i<100; i++) {
//                    int num1 = random.nextInt(9);
//                    int num2 = random.nextInt(9);
//
//
//                    int temp = numArray[num1];
//                    numArray[num1] = numArray[num2];
//                    numArray[num2] = temp;
//                    //num1 num2가 백번 치환된다.
//
//                }
                    //버튼 9개에 랜덤한 숫자 부여 ! (numArray를 통해서)






            for (int i = 0 ; i <btns.length; i++){
                final int pos = i; //상수로 바꿔야 다음 메소드에서 진행가능
                btns[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(numArray[pos]==answer){
                        btns[pos].setVisibility(View.INVISIBLE);
                        answer++;
                        cnt++;
                        if(cnt %9==0 && cnt!=45){


                            makeRandom(cnt);

                        }
                        else if(cnt==45){
                            btn_start.setClickable(true);
                            btn_start.setText("START");
                            answer =1;
                            cnt =0 ;
                        }


                    }}
                });

            }




            }
                });

        }

    @NonNull
    public void makeRandom(int cnt) {
        Random random = new Random();
        //랜덤한 9개의 숫자 1~9까지!
        //중복되지 않게 (마치 로또처럼)
        //numArray에 저장하고


        for (int i = 0; i < 9; i++) {
            int a = random.nextInt(9) + 1 +cnt;
            numArray[i] = a;
            for (int j = 0; j < i; j++) {
                if (numArray[i] == numArray[j]) {
                    i--;
                }


            }
        }
        for (int j = 0; j < btns.length; j++) {
            btns[j].setText(String.valueOf(numArray[j]));
            btns[j].setVisibility(View.VISIBLE);

        }
    }

    }