package com.carlosmuvi.sample;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.carlosmuvi.segmentedprogressbar.SegmentedProgressBar;

public class MainActivity extends AppCompatActivity {

    SegmentedProgressBar segmentedProgressBar;
    Button startButton;
    Button startWithoutAnimationButton;
    Button pauseResumeButton;
    Button resetButton;

    int totalSegments;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSegmentedProgressBar();

        startButton = (Button) findViewById(R.id.button);
        pauseResumeButton = (Button) findViewById(R.id.button3);
        resetButton = (Button) findViewById(R.id.button2);
        startWithoutAnimationButton = (Button) findViewById(R.id.button4);

        totalSegments = segmentedProgressBar.getTotalSegments();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                segmentedProgressBar.playSegment(3000);
            }
        });

        pauseResumeButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if (segmentedProgressBar.isPaused()) {
                    segmentedProgressBar.resume();
                } else {
                    segmentedProgressBar.pause();
                }
            }
        });

        startWithoutAnimationButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                segmentedProgressBar.incrementCompletedSegments();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                segmentedProgressBar.reset();
            }
        });

        segmentedProgressBar.setProgressCompletedListener(new SegmentedProgressBar.ProgressCompletedListener() {
            @Override
            public void progressCompleted(boolean isCompleted, int finishedSegments) {
                if(isCompleted && finishedSegments < totalSegments) {
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            segmentedProgressBar.playSegment(3000);
                        }
                    });
                }
            }
        });
    }

    private void initSegmentedProgressBar() {
        segmentedProgressBar = (SegmentedProgressBar) findViewById(R.id.segmented_progressbar);

        //set filled segments directly
        segmentedProgressBar.setCompletedSegments(1);
    }
}
