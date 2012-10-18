package rentinslovenia.com;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class GalerijaActivity extends Activity {

	private ImageView selectedImageView;

	private ImageView leftArrowImageView;

	private ImageView rightArrowImageView;

	ImageView infoBtn;

	TextView descriptionTxt;
	Button mMoreBtn;

	private Gallery gallery;

	private int selectedImagePosition = 0;

	private ArrayList<Bitmap> drawables;

	private PicAdapter galImageAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.galerija);

		getDrawablesList();
		setupUI();
	}

	private void setupUI() {

		selectedImageView = (ImageView) findViewById(R.id.selected_imageview);
		leftArrowImageView = (ImageView) findViewById(R.id.left_arrow_imageview);
		rightArrowImageView = (ImageView) findViewById(R.id.right_arrow_imageview);
		gallery = (Gallery) findViewById(R.id.gallery);
		infoBtn = (ImageView) findViewById(R.id.infoBtn);
		descriptionTxt = (TextView) findViewById(R.id.opis);
		mMoreBtn=(Button)findViewById(R.id.more);
		

		final Animation a = AnimationUtils
				.loadAnimation(this, R.anim.animacija);
		a.reset();

		leftArrowImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				descriptionTxt.setVisibility(View.INVISIBLE);
				if (selectedImagePosition > 0) {
					--selectedImagePosition;

				}

				gallery.setSelection(selectedImagePosition, false);
			}
		});
		
		mMoreBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(GalerijaActivity.this, RateActivity.class);
				startActivity(in);
			}
		});
		
		descriptionTxt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				descriptionTxt.setVisibility(View.INVISIBLE);
				leftArrowImageView.setVisibility(View.VISIBLE);
				rightArrowImageView.setVisibility(View.VISIBLE);
				infoBtn.setVisibility(View.VISIBLE);
				mMoreBtn.setVisibility(View.VISIBLE);
			}
		});

		infoBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// final Dialog dialog = new Dialog(GalerijaActivity.this);
				// dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
				// dialog.setContentView(R.layout.dialog);
				// dialog.setTitle(Choose._rezultat);
				//
				// dialog.setCancelable(true);
				//
				// TextView text = (TextView) dialog.findViewById(R.id.text);
				// text.setText(getIntent().getStringExtra("INFO"));
				//
				// Button button = (Button) dialog
				// .findViewById(R.id.dialogButtonOK);
				// button.setOnClickListener(new OnClickListener() {
				// @Override
				// public void onClick(View v) {
				// dialog.cancel();
				// }
				// });
				// dialog.show();
				// dialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,
				// R.drawable.mali_logo);
				descriptionTxt.setVisibility(View.VISIBLE);
				descriptionTxt.startAnimation(a);
				descriptionTxt.setText(getIntent().getStringExtra("INFO"));
				
				
				leftArrowImageView.setVisibility(View.INVISIBLE);
				rightArrowImageView.setVisibility(View.INVISIBLE);
				infoBtn.setVisibility(View.INVISIBLE);
				mMoreBtn.setVisibility(View.INVISIBLE);
				
			}
		});

		rightArrowImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				descriptionTxt.setVisibility(View.INVISIBLE);
				if (selectedImagePosition < drawables.size() - 1) {
					++selectedImagePosition;

				}

				gallery.setSelection(selectedImagePosition, false);

			}
		});

		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {

				selectedImagePosition = pos;

				if (selectedImagePosition > 0
						&& selectedImagePosition < drawables.size() - 1) {

					leftArrowImageView.setImageDrawable(getResources()
							.getDrawable(R.drawable.left_arrow));
					rightArrowImageView.setImageDrawable(getResources()
							.getDrawable(R.drawable.right_arrow));

				} else if (selectedImagePosition == 0) {

					leftArrowImageView.setImageDrawable(getResources()
							.getDrawable(R.drawable.left_arrow_disabled));

				} else if (selectedImagePosition == drawables.size() - 1) {

					rightArrowImageView.setImageDrawable(getResources()
							.getDrawable(R.drawable.right_arrow_disabled));
				}

				changeBorderForSelectedImage(selectedImagePosition);
				setSelectedImage(selectedImagePosition);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}

		});

		galImageAdapter = new PicAdapter(this, drawables);

		gallery.setAdapter(galImageAdapter);

		if (drawables.size() > 0) {

			gallery.setSelection(selectedImagePosition, false);

		}

		if (drawables.size() == 1) {

			rightArrowImageView.setImageDrawable(getResources().getDrawable(
					R.drawable.right_arrow_disabled));
		}

	}

	private void changeBorderForSelectedImage(int selectedItemPos) {

		int count = gallery.getChildCount();

		for (int i = 0; i < count; i++) {

			ImageView imageView = (ImageView) gallery.getChildAt(i);
			imageView.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.image_border));
			imageView.setPadding(3, 3, 3, 3);

		}

		ImageView imageView = (ImageView) gallery.getSelectedView();
		imageView.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.selected_image_border));
		imageView.setPadding(3, 3, 3, 3);
	}

	private void getDrawablesList() {
		drawables = SubMenuActivity._images;

	}

	private void setSelectedImage(int selectedImagePosition) {

		Bitmap bd = drawables.get(selectedImagePosition);

		selectedImageView.setImageBitmap(bd);
		selectedImageView.setScaleType(ScaleType.FIT_XY);

	}
}
