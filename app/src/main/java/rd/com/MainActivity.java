package rd.com;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final long  starttime = 600000 ;

    private TextView mtextcountdown;
    private Button mstartpause;
    private Button mreset;

    private CountDownTimer mcountdown;
    private boolean mtimerrunning;

    private long mtimeleft = starttime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtextcountdown = findViewById(R.id.text_view_countdown);

        mstartpause = findViewById(R.id.button_start_pause);
        mreset = findViewById(R.id.button_Reset);

        mstartpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mtimerrunning) {
                    pauseTimer();

                }

                else {
                    startTimer();

                }

            }
        });

        mreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();

            }
        });

        updatecountdowntext();


    }

    private void startTimer(){

        mcountdown = new CountDownTimer( mtimeleft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mtimeleft = millisUntilFinished;
                updatecountdowntext();

            }

            @Override
            public void onFinish() {

                mtimerrunning = false;
                mstartpause.setText("Start");
                mstartpause.setVisibility(View.VISIBLE);
                mreset.setVisibility(View.INVISIBLE);

            }
        }.start();

        mtimerrunning = true;
        mstartpause.setText("Pause");
        mreset.setVisibility(View.VISIBLE);
    }
    private void pauseTimer(){

        mcountdown.cancel();
        mtimerrunning = false;
        mstartpause.setText("Start");
        mreset.setVisibility(View.VISIBLE);
    }

    private void resetTimer(){

        mtimeleft = starttime;
        updatecountdowntext();
        mreset.setVisibility(View.VISIBLE);
    }

    private void updatecountdowntext(){

        int min = (int) mtimeleft / 1000 / 60;
        int sec = (int) mtimeleft / 1000 % 60;

        String timeleftformatted = String.format(Locale.getDefault(),"%02d:%02d", min, sec);

        mtextcountdown.setText(timeleftformatted);


    }
}
