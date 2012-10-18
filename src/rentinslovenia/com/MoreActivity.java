package rentinslovenia.com;

import rentinslovenia.com.Localization.English;
import rentinslovenia.com.Localization.Slovenija;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class MoreActivity extends Activity {

	TextView mNapisTxt;
	TextView mAddressTxt;
	TextView mEmailTxt;
	TextView mPhoneTxt;
	TextView mInfoTxt;
	TextView mZnamkaTxt;
	TextView mPriceTxt;
	ImageView mMapButton;
	ImageView mEmailButton;
	ImageView extBtn;
	ImageView mTypeImg;
	ImageView mCallBtn;
	ImageView mAboutBtn;
	ImageView mOpenHoursBtn;

	String _mail;
	String _name;
	String _address;
	String _Phone;
	String _cena;
	String _type;
	// localizacija
	String _klicanje;
	String _vpKlicanje;
	String _posiljanje;
	String _vpPosiljanje;
	int _X;
	int _Y;
	int _pozicija = 0;
	private ImageLoader imageLoader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more);

		mNapisTxt = (TextView) findViewById(R.id.txtName);
		mAddressTxt = (TextView) findViewById(R.id.txtAddress);
		mEmailTxt = (TextView) findViewById(R.id.txtEmail);
		mPhoneTxt = (TextView) findViewById(R.id.txtPhone);
		mMapButton = (ImageView) findViewById(R.id.mapBtn);
		extBtn = (ImageView) findViewById(R.id.exbtn);
		mInfoTxt = (TextView) findViewById(R.id.textView4);
		mZnamkaTxt = (TextView) findViewById(R.id.txtZnamka);
		mPriceTxt = (TextView) findViewById(R.id.txtPreise);
		mTypeImg = (ImageView) findViewById(R.id.sredstvo);
		mEmailButton = (ImageView) findViewById(R.id.postaa);
		mAboutBtn = (ImageView) findViewById(R.id.about);
		mOpenHoursBtn = (ImageView) findViewById(R.id.odpcasi);

		if (Choose._lang.equals("EN")) {
			_klicanje = English._call;
			_vpKlicanje = English._questionCall;
			_posiljanje = English._message;
			_vpPosiljanje = English._questionEmail;
		}
		if (Choose._lang.equals("SI")) {
			_klicanje = Slovenija._call;
			_vpKlicanje = Slovenija._questionCall;
			_posiljanje = Slovenija._message;
			_vpPosiljanje = Slovenija._questionEmail;
		}

		mCallBtn = (ImageView) findViewById(R.id.call);
		// Typeface typeface = Typeface.createFromAsset(getAssets(),
		// "A_Font_with_Serifs.ttf");
		// mInfoTxt.setTypeface(typeface);
		// mZnamkaTxt.setTypeface(typeface);
		// mPriceTxt.setTypeface(typeface);

		_name = getIntent().getStringExtra("Name");
		_address = getIntent().getStringExtra("Address");
		_mail = getIntent().getStringExtra("Mail");
		_X = Integer.parseInt(getIntent().getStringExtra("X"));
		_Y = Integer.parseInt(getIntent().getStringExtra("Y"));
		_Phone = getIntent().getStringExtra("Phone");
		_cena = getIntent().getStringExtra("Cena");
		_type = getIntent().getStringExtra("Znamka");

		mNapisTxt.setText(_name);
		mAddressTxt.setText(_address);
		mPhoneTxt.setText(_Phone);
		mEmailTxt.setText(_mail);
		if(_cena.equals("0"))
		{
			mPriceTxt.setText("");
		}
		else
		{
			mPriceTxt.setText(_cena + "€");
		}
		
		mZnamkaTxt.setText(_type);
		Drawable dr = getResources().getDrawable(R.drawable.load);
		mTypeImg.setImageDrawable(dr);

		mAboutBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				AlertDialog.Builder builder = new AlertDialog.Builder(
//						MoreActivity.this);
//				builder.setTitle("About us - Contact");
//				builder.setMessage(
//						"Tezak & Tezak d.o.o,\nMariborska cesta 19,\n2250 Ptuj,Slovenija\nTel: +3862 788 5002")
//						.setPositiveButton("OK",
//								new DialogInterface.OnClickListener() {
//									public void onClick(DialogInterface dialog,
//											int id) {
//										dialog.cancel();
//									}
//								});
//				AlertDialog alert = builder.create();
//				alert.show();
				
				 final Dialog dialog = new Dialog(MoreActivity.this);
				 dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
	                dialog.setContentView(R.layout.dialog);
	                dialog.setTitle("Lorem Ipsum");
	                
	                dialog.setCancelable(true);
	                //there are a lot of settings, for dialog, check them all out!
	 
	                //set up text
	                TextView text = (TextView) dialog.findViewById(R.id.text);
	                text.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis viverra, nisi volutpat fringilla egestas, massa felis pharetra dolor, rutrum euismod risus leo vel lacus. Sed odio augue, rutrum eu ullamcorper eget, egestas sed eros. Sed ac iaculis purus. Nullam in orci enim, ut varius sapien. Nunc mattis massa");
	 
	                //set up image view
//	                ImageView img = (ImageView) dialog.findViewById(R.id.image);
//	                img.setImageResource(R.drawable.mali_logo);
	 
	                //set up button
	                Button button = (Button) dialog.findViewById(R.id.dialogButtonOK);
	                button.setOnClickListener(new OnClickListener() {
	                @Override
	                    public void onClick(View v) {
	                        dialog.cancel();
	                    }
	                });
	                //now that the dialog is set up, it's time to show it    
	                dialog.show();
	                dialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.mali_logo);
			}
		});

		mOpenHoursBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				int pozicija = getIntent().getIntExtra("POS", 0);
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MoreActivity.this);
				builder.setTitle("Odpiralni èasi");
				builder.setMessage(
						"Ponedeljek:\t" + Choose._rezultReal.get(pozicija)._ponOd.substring(0, 5)
								+ "-" + Choose._rezultReal.get(pozicija)._ponDo.substring(0, 5)
								+ "\n" + "Torek:\t"
								+ Choose._rezultReal.get(pozicija)._torOd.substring(0, 5) + "-"
								+ Choose._rezultReal.get(pozicija)._torDo.substring(0, 5)
								+ "\n" + "Sreda:\t"
								+ Choose._rezultReal.get(pozicija)._sreOd.substring(0, 5) + "-"
								+ Choose._rezultReal.get(pozicija)._sreDo.substring(0, 5)
								+ "\n" + "Èetrtek:\t"
								+ Choose._rezultReal.get(pozicija)._cetOd.substring(0, 5) + "-"
								+ Choose._rezultReal.get(pozicija)._cetDo.substring(0, 5)
								+ "\n" + "Petek:\t"
								+ Choose._rezultReal.get(pozicija)._petOd.substring(0, 5) + "-"
								+ Choose._rezultReal.get(pozicija)._petDo.substring(0, 5)
								+ "\n" + "Sobota:\t"
								+ Choose._rezultReal.get(pozicija)._sobOd.substring(0, 5) + "-"
								+ Choose._rezultReal.get(pozicija)._sobDo.substring(0, 5)
								+ "\n" + "Nedelja:\t"
								+ Choose._rezultReal.get(pozicija)._nedOd.substring(0, 5) + "-"
								+ Choose._rezultReal.get(pozicija)._nedDo.substring(0, 5)
								+ "\n").setPositiveButton("Zapri",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
				AlertDialog alert = builder.create();
				alert.show();
			}
		});

		// imageLoader = ImageLoader.getInstance();
		// imageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
		// imageLoader.clearMemoryCache();
		// imageLoader.clearDiscCache();
		// imageLoader.displayImage(getIntent().getStringExtra("Slika"),mTypeImg
		// );

		mTypeImg.setImageBitmap(Choose._images.get(getIntent().getIntExtra(
				"POS", 0)));
		mMapButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// prikaz renterja na mapi..
				Intent in = new Intent(getBaseContext(), Gmaps.class);
				in.putExtra("X", _X);
				in.putExtra("Y", _Y);
				in.putExtra("Name", _name);
				in.putExtra("Address", _address);
				startActivity(in);
				// final Intent intent = new Intent(Intent.ACTION_VIEW,
				// Uri.parse(
				// "http://maps.google.com/maps?" +
				// "saddr=&daddr=Fram"));
				// intent.setClassName(
				// "com.google.android.apps.maps",
				// "com.google.android.maps.MapsActivity");
				// startActivity(intent);
			}
		});

		mEmailButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MoreActivity.this);
				builder.setTitle(_posiljanje);
				builder.setMessage(_vpPosiljanje)
						.setCancelable(false)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										final Intent emailIntent = new Intent(
												android.content.Intent.ACTION_SEND);
										emailIntent.setType("plain/text");
										emailIntent
												.putExtra(
														android.content.Intent.EXTRA_EMAIL,
														new String[] { _mail });
										startActivity(emailIntent);
									}
								})
						.setNegativeButton("NO",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										dialog.cancel();
									}
								});
				AlertDialog alert = builder.create();
				alert.show();

			}
		});

		mPhoneTxt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				AlertDialog.Builder builder = new AlertDialog.Builder(
						MoreActivity.this);
				builder.setTitle(_klicanje);
				builder.setMessage(_vpKlicanje)
						.setCancelable(false)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										Intent intent = new Intent(
												Intent.ACTION_CALL);
										String ph = _Phone.replace(" ", "");
										intent.setData(Uri.parse("tel:" + ph));
										startActivity(intent);
									}
								})
						.setNegativeButton("NO",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										dialog.cancel();
									}
								});
				AlertDialog alert = builder.create();
				alert.show();
				// TODO Auto-generated method stub

			}
		});

		mCallBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MoreActivity.this);
				builder.setTitle(_klicanje);
				builder.setMessage(_vpKlicanje)
						.setCancelable(false)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										Intent intent = new Intent(
												Intent.ACTION_CALL);
										String ph = _Phone.replace(" ", "");
										intent.setData(Uri.parse("tel:" + ph));
										startActivity(intent);
									}
								})
						.setNegativeButton("NO",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										dialog.cancel();
									}
								});
				AlertDialog alert = builder.create();
				alert.show();
			}
		});

		extBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MoreActivity.this);
				builder.setTitle("Exit");
				builder.setMessage("Dou you want to close application?")
						.setCancelable(false)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										moveTaskToBack(true);
									}
								})
						.setNegativeButton("NO",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										dialog.cancel();
									}
								});
				AlertDialog alert = builder.create();
				alert.show();

			}
		});
		
		
	}
	
	public void startIn(Intent in)
	{
		startActivity(in);
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
