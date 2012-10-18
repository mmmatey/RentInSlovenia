package rentinslovenia.com;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class RentInSloveniaActivity extends Activity {
	/** Called when the activity is first created. */

	ImageButton mSloLangButton;
	ImageButton mEngLangButton;
	ImageButton mCroLangButton;
	ImageButton mGerLangButton;
	ImageButton mItaLangButton;
	ImageButton mFraLangButton;

	// Layouts
	FrameLayout layer1;
	FrameLayout layer2;
	FrameLayout layer3;
	FrameLayout layer4;
	public static int height;
	public static int width;

	public String _myLocation;
	public float lat;
	public float lon;

	// private RestAsyncTask rat;

	public static ArrayList<Rent> _rezult = new ArrayList<Rent>();
	public static ArrayList<Distance> _razdalje = new ArrayList<Distance>();

	public static ArrayList<Rent> _rezultReal = new ArrayList<Rent>();
	public static ArrayList<Distance> _razdaljeReal = new ArrayList<Distance>();

	public static ArrayList<Bitmap> _images = new ArrayList<Bitmap>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mSloLangButton = (ImageButton) findViewById(R.id.slo);
		mEngLangButton = (ImageButton) findViewById(R.id.ang);
		mCroLangButton = (ImageButton) findViewById(R.id.cro);
		mGerLangButton = (ImageButton) findViewById(R.id.ger);
		mItaLangButton = (ImageButton) findViewById(R.id.ita);
		mFraLangButton = (ImageButton) findViewById(R.id.fra);

		setUnKlikImage(mCroLangButton);
		setUnKlikImage(mEngLangButton);
		setUnKlikImage(mSloLangButton);
		setUnKlikImage(mGerLangButton);
		setUnKlikImage(mItaLangButton);
		setUnKlikImage(mFraLangButton);

		layer1 = (FrameLayout) findViewById(R.id.frameLayout1);
		layer2 = (FrameLayout) findViewById(R.id.frameLayout2);
		layer3 = (FrameLayout) findViewById(R.id.frameLayout3);
		layer4 = (FrameLayout) findViewById(R.id.frameLayout4);

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		height = metrics.heightPixels;
		width = metrics.widthPixels;
		Log.d("REST", "h=" + height);
		Log.d("REST", "w=" + width);

		margins();

		mSloLangButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setKlikImage(mSloLangButton);
				Intent i = new Intent(getBaseContext(), Choose.class);
				i.putExtra("Lang", "SI");
				startActivity(i);
			}
		});
		mEngLangButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setKlikImage(mEngLangButton);
				Intent i = new Intent(getBaseContext(), Choose.class);
				i.putExtra("Lang", "EN");
				startActivity(i);
			}
		});
		mCroLangButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setKlikImage(mCroLangButton);
				Intent i = new Intent(getBaseContext(), Choose.class);
				i.putExtra("Lang", "HR");
				startActivity(i);

			}
		});
		mGerLangButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setKlikImage(mGerLangButton);
				Intent i = new Intent(getBaseContext(), Choose.class);
				i.putExtra("Lang", "DE");
				startActivity(i);
			}
		});
		mItaLangButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setKlikImage(mItaLangButton);
				Intent i = new Intent(getBaseContext(), Choose.class);
				i.putExtra("Lang", "IT");
				startActivity(i);

			}
		});
		mFraLangButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setKlikImage(mFraLangButton);
				Intent i = new Intent(getBaseContext(), Choose.class);
				i.putExtra("Lang", "FR");
				startActivity(i);
			}
		});
	}

	private void margins() {
		// TODO Auto-generated method stub
		int pTop = height - 320;

		pTop = pTop / 4;

		int pLeft = width - 240;
		pLeft = pLeft / 3;

		Log.d("REST", "button w=" + mSloLangButton.getLayoutParams().width + "pT=" + pTop);
		
		

		//layer1.setPadding(dpToPixels(getBaseContext(), pLeft), dpToPixels(getBaseContext(), pTop), dpToPixels(getBaseContext(), pLeft), 0);
		//layer2.setPadding(dpToPixels(getBaseContext(), pLeft), dpToPixels(getBaseContext(), pTop), dpToPixels(getBaseContext(), pLeft), 0);
		//layer3.setPadding(dpToPixels(getBaseContext(), pLeft), dpToPixels(getBaseContext(), pTop), dpToPixels(getBaseContext(), pLeft), 0);

		layer4.setPadding(pLeft, pTop, pLeft, pTop);
		// MarginLayoutParams marginParams = new
		// MarginLayoutParams(mSloLangButton.getLayoutParams());
		// marginParams.setMargins(pLeft, pTop, 0, 0);
		// FrameLayout.LayoutParams layoutParams = new
		// FrameLayout.LayoutParams(marginParams);
		// mSloLangButton.setLayoutParams(layoutParams);
		// mCroLangButton.setLayoutParams(layoutParams);
		// mItaLangButton.setLayoutParams(layoutParams);
		//
		// MarginLayoutParams marginParam = new
		// MarginLayoutParams(mSloLangButton.getLayoutParams());
		// marginParams.setMargins(0, pTop, pLeft, 0);
		// FrameLayout.LayoutParams layoutParam = new
		// FrameLayout.LayoutParams(marginParam);
		// mEngLangButton.setLayoutParams(layoutParam);
		// mGerLangButton.setLayoutParams(layoutParam);
		// mFraLangButton.setLayoutParams(layoutParam);

	}
	
	public static int dpToPixels(Context context, float dp) {
	    final float scale = context.getResources().getDisplayMetrics().density;
	    return (int) (dp * scale + 0.5f);
	}

	public void setKlikImage(ImageButton b) {
		Drawable dr = getResources().getDrawable(R.drawable.gumb_jezik_klik);
		b.setBackgroundDrawable(dr);
	}

	public void setUnKlikImage(ImageButton b) {
		Drawable dr = getResources().getDrawable(R.drawable.gumb_jezik);
		b.setBackgroundDrawable(dr);
	}

}