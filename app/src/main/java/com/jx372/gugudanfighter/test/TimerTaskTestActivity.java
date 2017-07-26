package com.jx372.gugudanfighter.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import com.jx372.gugudanfighter.R;

public class TimerTaskTestActivity extends AppCompatActivity {
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_task_test);
        timer.schedule(new PlayGameTimerTask(),1000,1000);
    }
    private  class PlayGameTimerTask extends TimerTask {
        private int seconds;
        @Override
        public void run() {
            ++seconds;
            if(seconds >= 30){
                timer.cancel();
                finish();
                return ;
            }
            runOnUiThread(new Runnable() {  //스레드가 생성되는 건 아니다. 오해 하지 않길... 다만 메세지 큐에 넣어두면 main스레드가 수행해줄것임.
                @Override
                public void run() {
                    ((TextView)findViewById(R.id.textView)).setText(30-seconds + "초");
                }
            });
               // System.out.println(seconds);
        }
    }
}
