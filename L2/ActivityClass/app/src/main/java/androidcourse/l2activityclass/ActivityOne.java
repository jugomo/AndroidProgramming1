package androidcourse.l2activityclass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import course.labs.activitylab.R;

public class ActivityOne extends Activity {

	// Use these as keys when you're saving state between reconfigurations
	private static final String RESTART_KEY = "restart";
	private static final String RESUME_KEY = "resume";
	private static final String START_KEY = "start";
	private static final String CREATE_KEY = "create";

	// String for LogCat documentation
	private final static String TAG = "Lab-ActivityOne";

	// Lifecycle counters

	// Create variables named
	// mCreate, mRestart, mStart and mResume
	// to count calls to onCreate(), onRestart(), onStart() and
	// onResume(). These variables should not be defined as static.
	int mCreate = 0;
	int mRestart = 0;
	int mStart = 0;
	int mResume = 0;

	// You will need to increment these variables' values when their
	// corresponding lifecycle methods get called.

	// Create variables for each of the TextViews
	// named mTvCreate, mTvRestart, mTvStart, mTvResume.
	// for displaying the current count of each counter variable
	TextView mTvCreate;
	TextView mTvRestart;
	TextView mTvStart;
	TextView mTvResume;

	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one);

		context = getApplicationContext();

		// Assign the appropriate TextViews to the TextView variables
		// Hint: Access the TextView by calling Activity's findViewById()
		mTvCreate = (TextView) findViewById(R.id.create);
		mTvRestart = (TextView) findViewById(R.id.restart);
		mTvStart = (TextView) findViewById(R.id.start);
		mTvResume = (TextView) findViewById(R.id.resume);

		Button launchActivityTwoButton = (Button) findViewById(R.id.bLaunchActivityTwo);
		launchActivityTwoButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Launch Activity Two
				// Hint: use Context's startActivity() method

				// Create an intent stating which Activity you would like to
				// start
				Intent intent = new Intent(context, ActivityTwo.class);

				// Launch the Activity using the intent
				startActivity(intent);
			}
		});

		// Has previous state been saved?
		if (savedInstanceState != null) {

			// Restore value of counters from saved state
			// Only need 4 lines of code, one for every count variable
			mRestart = savedInstanceState.getInt(RESTART_KEY, 0);
			mCreate = savedInstanceState.getInt(CREATE_KEY, 0);
			mResume = savedInstanceState.getInt(RESUME_KEY, 0);
			mStart = savedInstanceState.getInt(START_KEY, 0);
		}

		// Emit LogCat message
		mCreate++;
		Log.i(TAG, "Entered the onCreate() method " + mCreate + " times.");

		// Update the appropriate count variable
		displayCounts();

	}

	// Lifecycle callback overrides

	@Override
	public void onStart() {
		super.onStart();

		// Emit LogCat message
		mStart++;
		Log.i(TAG, "Entered the onStart() method " + mStart + " times.");

		// Update the appropriate count variable
		displayCounts();

	}

	@Override
	public void onResume() {
		super.onResume();

		// Emit LogCat message
		mResume++;
		Log.i(TAG, "Entered the onResume() method " + mResume + " times.");

		// Update the appropriate count variable
		displayCounts();

	}

	@Override
	public void onPause() {
		super.onPause();

		// Emit LogCat message
		Log.i(TAG, "Entered the onPause() method");
	}

	@Override
	public void onStop() {
		super.onStop();

		// Emit LogCat message
		Log.i(TAG, "Entered the onStop() method");
	}

	@Override
	public void onRestart() {
		super.onRestart();

		// Emit LogCat message
		mRestart++;
		Log.i(TAG, "Entered the onRestart() method " + mRestart + " times.");

		// Update the appropriate count variable
		displayCounts();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		// Emit LogCat message
		Log.i(TAG, "Entered the onDestroy() method");
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		// Save state information with a collection of key-value pairs
		// 4 lines of code, one for every count variable
		savedInstanceState.putInt(RESTART_KEY, mRestart);
		savedInstanceState.putInt(CREATE_KEY, mCreate);
		savedInstanceState.putInt(RESUME_KEY, mResume);
		savedInstanceState.putInt(START_KEY, mStart);
	}

	// Updates the displayed counters
	// This method expects that the counters and TextView variables use the
	// names
	// specified above
	public void displayCounts() {

		mTvCreate.setText("onCreate() calls: " + mCreate);
		mTvStart.setText("onStart() calls: " + mStart);
		mTvResume.setText("onResume() calls: " + mResume);
		mTvRestart.setText("onRestart() calls: " + mRestart);

	}
}
