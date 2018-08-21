package com.my.toolbox.timer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.my.toolbox.R;

import java.util.LinkedHashSet;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author
 */
public class TimerTestActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.timer)
    AppCompatButton timerBtn;
    @BindView(R.id.schedule)
    AppCompatButton scheduleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_test);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.timer, R.id.schedule})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.timer:
                Toast.makeText(this, "timer", Toast.LENGTH_SHORT).show();
                testTimer();
                break;
            case R.id.schedule:
                Toast.makeText(this, "schedule", Toast.LENGTH_SHORT).show();
                testSchedule();
                break;
            default:
                break;
        }
    }


    private void testSchedule() {
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
//                3,
//                30,TimeUnit.SECONDS,
//                new SynchronousQueue<Runnable>());
//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                Log.d("lyc","scheduleBtn = " + scheduleBtn);
//            }
//        });


        ScheduledThreadPoolExecutor executor1 = new ScheduledThreadPoolExecutor(1
        );
        executor1.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                Log.d("lyc","scheduleBtn = " + scheduleBtn);
            }
        }, 1, 5, TimeUnit.SECONDS);

//        ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();
//
//        pool.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                Log.d("lyc","scheduleBtn = " + scheduleBtn);
//            }
//        }, 0 ,5, TimeUnit.SECONDS);

    }


    private Timer timer;
    private void testTimer() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
               Log.d("lyc","timerBtn = " + timerBtn);
            }
        };

        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(task, 1000, 5000);
    }


}
