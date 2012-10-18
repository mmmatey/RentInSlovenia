package rentinslovenia.com;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends Activity {

	private final int SPLASH_DISPLAY_LENGTH = 5000;
	ImageView logoAnimacija;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		final Animation a = AnimationUtils
				.loadAnimation(this, R.anim.rotacija);
		a.reset();
		
		logoAnimacija=(ImageView)findViewById(R.id.logo_animacija);
		logoAnimacija.setVisibility(View.VISIBLE);
		logoAnimacija.startAnimation(a);
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		boolean isSplashEnabled = sp.getBoolean("isSplashEnabled", true);

		if (isSplashEnabled) {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					SplashActivity.this.finish();
					// Create an Intent that will start the main activity.
					Intent mainIntent = new Intent(SplashActivity.this,
							RentInSloveniaActivity.class);
					SplashActivity.this.startActivity(mainIntent);
				}
			}, SPLASH_DISPLAY_LENGTH);
		} else {
			// if the splash is not enabled, then finish the activity
			// immediately and go to main.
			finish();
			Intent mainIntent = new Intent(SplashActivity.this,
					RentInSloveniaActivity.class);
			SplashActivity.this.startActivity(mainIntent);
		}
	}

}
