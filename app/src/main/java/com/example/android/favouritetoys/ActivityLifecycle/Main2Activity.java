package com.example.android.favouritetoys.ActivityLifecycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.android.favouritetoys.R;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    /**
     * This tag will be used for logging.
     * It is best practice to use the class's name using
     * getSimpleName() as that will greatly help to identify
     * the location from which your logs are being posted.
     */
    private static final String TAG = Main2Activity.class.getSimpleName();

    /* Constant values for the names of each respective lifecycle callback */
    private static final String ON_CREATE = "onCreate";
    private static final String ON_START = "onStart";
    private static final String ON_RESUME = "onResume";
    private static final String ON_PAUSE = "onPause";
    private static final String ON_STOP = "onStop";
    private static final String ON_RESTART = "onRestart";
    private static final String ON_DESTROY = "onDestroy";
    private static final String ON_SAVE_INSTANCE_STATE = "onSaveInstanceState";

    private static final String LIFECYCLE_CALLBACKS_TEXT_KEY = "callbacks";

    /* This TextView will contain a running log of every lifecycle callback
     * method called from this Activity. This TextView can be reset
     * to its default state by clicking the Button labeled "Reset Log" */
    private TextView mLifecycleDisplay;

    private static ArrayList<String> mLifecycleCallbacks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mLifecycleDisplay = findViewById(R.id.tv_lifecycle_events_display);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(LIFECYCLE_CALLBACKS_TEXT_KEY)) {
                String allPreviousLifecycleCallbacks = savedInstanceState
                        .getString(LIFECYCLE_CALLBACKS_TEXT_KEY);
                mLifecycleDisplay.setText(allPreviousLifecycleCallbacks);
            }
        }

        /* The reason we iterate(повторять) starting from the back of the
         * A.List and ending in the front is that the most recent callbacks
         * are inserted(вставлены) into the front of the ArrayList, so
         * naturally the older callbacks are stored further back.
         * We could have used a Queue to do this, but Java has strange API
         * names for the Queue interface that we thought might be
         * more confusing than this ArrayList solution.
         */
        for (int i = mLifecycleCallbacks.size() - 1; i >= 0; i--) {
            mLifecycleDisplay.append(mLifecycleCallbacks.get(i)
                    + "\n");
        }

        /*
         * Once we've appended each callback from the ArrayList to the
         *  TextView, we need to clean the ArrayList so we
         * don't get duplicate entries in the TextView.
         */
        mLifecycleCallbacks.clear();

        logAndAppend(ON_CREATE);
    }

    /**
     * Called when the activity is becoming visible to the user.
     * <p>
     * Followed by onResume() if the activity comes to the foreground, or onStop() if it becomes
     * hidden.
     */
    @Override
    protected void onStart() {
        super.onStart();

        logAndAppend(ON_START);
    }

    /**
     * Called when the activity will start interacting with the user.
     * At this point your activity is at the top of the activity stack,
     * with user input going to it.
     * <p>
     * Always followed by onPause().
     */
    @Override
    protected void onResume() {
        super.onResume();

        logAndAppend(ON_RESUME);
    }

    /**
     * Called when the system is about to start resuming a previous activity.
     * This is typically used to commit unsaved changes to persistent data,
     * stop animations and other things that may be consuming CPU, etc.
     * Implementations of this method must be very quick because the next
     * activity will not be resumed until this method returns.
     * <p>
     * Followed by either onResume() if the activity returns back to the front, or onStop() if it
     * becomes invisible to the user.
     */
    @Override
    protected void onPause() {
        super.onPause();

        logAndAppend(ON_PAUSE);
    }

    /**
     * Called when the activity is no longer visible to the user,
     * because another activity has been resumed and is covering this one.
     * This may happen either because a new activity is being
     * started, an existing one is being brought in front of this one,
     * or this one is being destroyed.
     * <p>
     * Followed by either onRestart() if this activity is coming back to interact with the user, or
     * onDestroy() if this activity is going away.
     */
    @Override
    protected void onStop() {
        super.onStop();

        /*
         * Since any updates to the UI we make after onSaveInstanceState
         *  (onStop, onDestroy, etc), we use an ArrayList to track if
         *  these lifecycle events had occurred. If any of them have
         * occurred(произошло), we append their respective name to the TextView.
         */
        mLifecycleCallbacks.add(0, ON_STOP);

        logAndAppend(ON_STOP);
    }

    /**
     * Called after your activity has been stopped, prior to it being started again.
     * <p>
     * Always followed by onStart()
     */
    @Override
    protected void onRestart() {
        super.onRestart();

        logAndAppend(ON_RESTART);
    }

    /**
     * The final call you receive before your activity is destroyed.
     * This can happen either because the activity is finishing
     * (someone called finish() on it, or because the system is
     * temporarily destroying this instance of the activity to save space.
     * You can distinguish(различать) between these two scenarios
     * with the isFinishing() method.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        mLifecycleCallbacks.add(0, ON_DESTROY);

        logAndAppend(ON_DESTROY);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        logAndAppend(ON_SAVE_INSTANCE_STATE);
        String lifececleDisplayTextViewContents = mLifecycleDisplay
                .getText().toString();
        outState.putString(LIFECYCLE_CALLBACKS_TEXT_KEY,
                lifececleDisplayTextViewContents);
    }

    /**
     * Logs to the console and appends the lifecycle method name to the TextView
     * so that you can view the series of method callbacks that are called both
     * from the app and from within Android Studio's Logcat.
     *
     * @param lifecycleEvent The name of the event to be logged.
     */
    private void logAndAppend(String lifecycleEvent) {
        Log.d(TAG, "Lifecycle Event:" + lifecycleEvent);

        mLifecycleDisplay.append(lifecycleEvent + "\n");
    }

    /* This method resets the contents of the TextView to its
     * default text of "Lifecycle callbacks"
     *
     * @param view The View that was clicked. In this case,
     *             it is the Button from our layout. */
    public void resetLifecycleDisplay(View view) {
        mLifecycleDisplay.setText("Lifecycle callbacks:\n");
    }
}
