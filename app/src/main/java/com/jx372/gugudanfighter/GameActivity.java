package com.jx372.gugudanfighter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jx372.gugudanfighter.test.TimerTaskTestActivity;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    private Timer timer = new Timer();
    private Button btn[] = new Button[9];
    private  int leftNumber;
    private  int rightNumber;
    private  int correctNumber;
    private  int totalcount=0;
    private  int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        timer.schedule(new PlayGameTimerTask(),1000,1000);

        leftNumber = (int)(Math.random() * 9)  + 1;
        rightNumber = (int)(Math.random() * 9)  + 1;
        correctNumber = leftNumber * rightNumber;


        ((TextView)findViewById(R.id.textViewLeftOperand)).setText("" + leftNumber);
        ((TextView)findViewById(R.id.textViewRightOperand)).setText(""  + rightNumber);

        btn[0] = (Button)findViewById(R.id.button_0_0);
        btn[1] = (Button)findViewById(R.id.button_0_1);
        btn[2] = (Button)findViewById(R.id.button_0_2);
        btn[3] = (Button)findViewById(R.id.button_1_0);
        btn[4] = (Button)findViewById(R.id.button_1_1);
        btn[5] = (Button)findViewById(R.id.button_1_2);
        btn[6] = (Button)findViewById(R.id.button_2_0);
        btn[7] = (Button)findViewById(R.id.button_2_1);
        btn[8] = (Button)findViewById(R.id.button_2_2);

        for(int i =0 ;i < 9; i++){
            int left = (int)(Math.random() * 9) +1;
            int right = (int)(Math.random() * 9) +1;
            btn[i].setText("" + (left*right));
            for(int j =0; j < 9-i; j++){
                if(btn[j].getText() == ""+left*right){
                    btn[i].setText("" + (left*right) + 1);
                    break;
                }

            }

        }
        int correctpoint = (int)(Math.random() * 8)  + 1;
        btn[correctpoint].setText("" + correctNumber);


        //((TextView)findViewById(R.id.textViewScore)).setText();

        btn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn[0].getText() == ""+correctNumber)
                {
                    score++;
                    totalcount++;
                }
            }
        });

    }
    public  static int randomize(int from, int to){
        return (int)(Math.random() * to) + from;
    }
    private  class PlayGameTimerTask extends TimerTask {
        private int seconds;
        @Override
        public void run() {
            ++seconds;
            if(seconds >= 30){
                timer.cancel();
                //게임 종료 결과 액티비티 호출
                finish();
                Intent intent = new Intent(GameActivity.this,ResultActivity.class);
                startActivity(intent);
                return ;
            }
            runOnUiThread(new Runnable() {  //스레드가 생성되는 건 아니다. 오해 하지 않길... 다만 메세지 큐에 넣어두면 main스레드가 수행해줄것임.
                @Override
                public void run() {
                    ((TextView)findViewById(R.id.textView22)).setText(30-seconds + "초");
                }
            });
            // System.out.println(seconds);
        }
    }
}
