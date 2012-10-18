package rentinslovenia.com;

import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import rentinslovenia.com.MyLocation.LocationResult;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SubMenuActivity extends Activity {

	// Buttons for subcategories
	Button firstKatBtn;
	Button secondKatBtn;
	Button thirdKatBtn;
	Button fourthBtn;
	Button fifthBtn;
	Button backBtn;

	ImageView loader;

	private RestAsyncTask rat;

	// Main category string
	String main;
	int numOfArray = 0;
	String _subMenu[];

	public String _myLocation;
	public float lat;
	public float lon;

	public static ArrayList<Rent> _rezult = new ArrayList<Rent>();
	public static ArrayList<Distance> _razdalje = new ArrayList<Distance>();

	public static ArrayList<Rent> _rezultReal = new ArrayList<Rent>();
	public static ArrayList<Distance> _razdaljeReal = new ArrayList<Distance>();

	public static ArrayList<Bitmap> _images = new ArrayList<Bitmap>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose2);

		firstKatBtn = (Button) findViewById(R.id.kat1);
		secondKatBtn = (Button) findViewById(R.id.kat2);
		thirdKatBtn = (Button) findViewById(R.id.kat3);
		fourthBtn = (Button) findViewById(R.id.kat4);
		fifthBtn = (Button) findViewById(R.id.kat5);
		backBtn = (Button) findViewById(R.id.back);
		loader = (ImageView) findViewById(R.id.loader);

		main = getIntent().getStringExtra("GLAVNI");
		numOfArray = getIntent().getIntExtra("NUM", 0);

		// Get my location
		LocationResult locationResult = new LocationResult() {
			@Override
			public void gotLocation(Location location) {

				lat = (float) location.getLatitude();
				lon = (float) location.getLongitude();
			}
		};
		MyLocation myLocation = new MyLocation();
		myLocation.getLocation(this, locationResult);

		if (numOfArray == 0) {
			_subMenu = Choose._accommodation;
		}
		if (numOfArray == 1) {
			_subMenu = Choose._foodDrink;
		}
		if (numOfArray == 2) {
			_subMenu = Choose._drink;
		}
		if (numOfArray == 3) {
			_subMenu = Choose._attractions;
		}
		if (numOfArray == 4) {
			_subMenu = Choose._excursionPoints;
		}
		if (numOfArray == 5) {
			_subMenu = Choose._rentalVenicles;
		}
		if (numOfArray == 6) {
			_subMenu = Choose._resort;
		}
		if (numOfArray == 7) {
			_subMenu = Choose._events;
		}

		firstKatBtn.setText(_subMenu[0]);
		secondKatBtn.setText(_subMenu[1]);
		thirdKatBtn.setText(_subMenu[2]);
		fourthBtn.setText(_subMenu[3]);
		fifthBtn.setText(_subMenu[4]);

		for (String st : _subMenu) {
			Log.d("REST", st);
		}

		firstKatBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setKlikImage(firstKatBtn);
				sendCommand(main + "/" + firstKatBtn.getText());
			}
		});

		secondKatBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setKlikImage(secondKatBtn);
				sendCommand(main + "/" + secondKatBtn.getText());
			}
		});

		thirdKatBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setKlikImage(thirdKatBtn);
				sendCommand(main + "/" + thirdKatBtn.getText());
			}
		});

		fourthBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setKlikImage(fourthBtn);
				sendCommand(main + "/" + fourthBtn.getText());
			}
		});

		fifthBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setKlikImage(fifthBtn);
				sendCommand(main + "/" + fifthBtn.getText());
			}
		});

		backBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				Intent in = new Intent(getBaseContext(), Choose.class);
				in.putExtra("Lang", Choose._lang);
				startActivity(in);

			}
		});

		setPading(firstKatBtn);
		setPading(secondKatBtn);
		setPading(thirdKatBtn);
		setPading(fourthBtn);
		setPading(fifthBtn);
		setPading(backBtn);

	}

	/*
	 * Function for set padding
	 */

	public void setPading(Button b) {
		b.setPadding(0, 5, 0, 5);
		b.getLayoutParams().height = (RentInSloveniaActivity.height - 20) / 6;
	}

	/*
	 * Function for set src image on click
	 */

	public void setKlikImage(Button b) {
		Drawable dr = getResources().getDrawable(R.drawable.meni_gumb_click);
		b.setBackgroundDrawable(dr);
	}

	private void sendCommand(String com) {

		com = com.replace(" ", "%20");
		Log.d("REST", com);
		rat = new RestAsyncTask(Rent.RestUrl + com, "KAT");
		Log.d("REST", Rent.RestUrl + com);
		rat.execute();
	}

	private class RestAsyncTask extends AsyncTask<String, Void, String> {
		public Reader r;
		String url;
		public String backgroundFunction;

		public RestAsyncTask(String url, String function) {

			this.url = url;
			r = new Reader();
			this.backgroundFunction = function;
		}

		@Override
		protected String doInBackground(String... arg0) {

			r.toJson(url);
			String ressobse = r.response;

			if (backgroundFunction == "KAT") {
				ParseJson(ressobse);
			}

			ParseJsonDistance();
			return ressobse;
		}

		/*
		 * Function to parse JSON, getting RENTS order by category
		 */
		private void ParseJson(String resp) {
			try {

				JSONArray Renti = new JSONArray(resp);
				SubMenuActivity._rezult.clear();
				for (int i = 0; i < Renti.length(); i++) {
					Rent rent = new Rent();
					JSONObject o = Renti.getJSONObject(i);
					
					rent._address = o.getString("address");
					rent._name = o.getString("name");
					rent._ID = o.getString("ID");
					rent._eMail = o.getString("eMail");
					rent._X = o.getString("X");
					rent._Y = o.getString("Y");
					rent._phone = o.getString("Phone");
					rent._image = o.getString("Image");
					rent._price = o.getString("price");
					rent._type = o.getString("type");
					//offer times
					rent._ponOd = o.getString("ponOd");
					rent._ponDo = o.getString("ponDo");
					rent._torOd = o.getString("torOd");
					rent._torDo = o.getString("torDo");
					rent._sreOd = o.getString("sreOd");
					rent._sreDo = o.getString("sreDo");
					rent._cetOd = o.getString("cetOd");
					rent._cetDo = o.getString("cetDo");
					rent._petOd = o.getString("petOd");
					rent._petDo = o.getString("petDo");
					rent._sobOd = o.getString("sobOd");
					rent._sobDo = o.getString("sobDo");
					rent._nedOd = o.getString("nedOd");
					rent._nedDo = o.getString("nedDo");
					//description and rate..
					rent._description=o.getString("opis");
					rent._rate=o.getString("ocena");
					rent._numOfVotes=o.getString("stGlasov");
					//languages
					rent._slo=o.getString("slo");
					rent._ger=o.getString("ger");
					rent._cro=o.getString("cro");
					rent._ita=o.getString("ita");
					rent._fra=o.getString("fra");
					rent._eng=o.getString("eng");
					
					//TODO: Call if it's possible or not
					//***
					//rent._calling=o.getString("call");
					
					SubMenuActivity._rezult.add(rent);
				}

			} catch (Exception e) {
				// TODO: handle exception
				Log.d("REST", e.getLocalizedMessage());
			}
		}

		/*
		 * Function to get distance between two places
		 */

		int counter = 0;

		private void ParseJsonDistance() {
			try {

				_myLocation = lat + "," + lon;
				_razdalje.clear();
				_razdaljeReal.clear();
				_rezultReal.clear();
				counter = 0;
				_images.clear();
				for (Rent rr : SubMenuActivity._rezult) {
					Log.d("REST", _myLocation);
					Log.d("REST", "X lokala :" + rr._X);
					Distance distanca = new Distance();

					Location bLocation = new Location("reverseGeocoded");
					bLocation.setLatitude(Double.parseDouble(rr._X) / 1000000);
					bLocation.setLongitude(Double.parseDouble(rr._Y) / 1000000);

					Location aLocation = new Location("reverseGeocoded");
					aLocation.setLatitude(lat);
					aLocation.setLongitude(lon);

					float distance = aLocation.distanceTo(bLocation) / 1000;
					distance = (float) Math.round(distance * 10) / 10;

					String str = String.valueOf(distance) + "km";
					distanca._dolzina = str;
					_razdalje.add(distanca);

					Bitmap slika = downloadBitmap(rr._image);
					_images.add(slika);

					Log.d("REST", "Slika dodana v polje!");

					// Sort by price
					// int tempPrice = 0;
					// if (_price.equals("do 50 €")) {
					// tempPrice = 50;
					// }
					// if (_price.equals("do 100 €")) {
					// tempPrice = 100;
					// }
					// if (_price.equals("do 200 €")) {
					// tempPrice = 200;
					// }
					// if (_price.equals("do 300 €")) {
					// tempPrice = 300;
					// }
					// if (_price.equals("vse")) {
					// tempPrice = 0;
					// }
					//
					// // Sort by Distance
					// float tempDistanca = 0;
					//
					// if (_distance.equals("do 5km")) {
					// tempDistanca = 5;
					// }
					// if (_distance.equals("do 10km")) {
					// tempDistanca = 10;
					// }
					// if (_distance.equals("do 20km")) {
					// tempDistanca = 20;
					// }
					// if (_distance.equals("do 30km")) {
					// tempDistanca = 30;
					// }
					// if (_distance.equals("vse")) {
					// tempDistanca = 0;
					// }
					//
					// if ((distance <= tempDistanca || tempDistanca == 0)
					// && (Integer.parseInt(rr._price) <= tempPrice || tempPrice
					// == 0)) {
					//
					//
					// }
					_razdaljeReal.add(distanca);
					_rezultReal.add(rr);
					Log.d("REST", "Vse dodano v polje, vendar moèno dvomim!");
					counter++;
				}
				checkDuplicate(_rezultReal);

			} catch (Exception e) {

				Log.d("REST", e.getLocalizedMessage());
			}
		}

		protected Bitmap downloadBitmap(String urls) {
			String urldisplay = urls;
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("REST", e.getMessage());
				e.printStackTrace();
			}
			return mIcon11;
		}

		public void checkDuplicate(ArrayList<Rent> array) {
			for (int i = 0; i < array.size(); i++) {
				boolean found = false;
				for (int j = 0; j < i; j++)
					if (array.get(i)._type == array.get(j)._type) {
						array.remove(i);
						break;
					}
				if (!found) {
				}
			}
		}

		@Override
		protected void onPostExecute(String result) {

			if (result.equals("ERROR"))
				Toast.makeText(SubMenuActivity.this, Choose._napaka,
						Toast.LENGTH_SHORT).show();
			else {
				if (SubMenuActivity._rezultReal.size() != 0) {
					Intent intent = new Intent(getApplicationContext(),
							Rezults.class);
					intent.putExtra("DIS", 20);
					intent.putExtra("PRI", 0);
					startActivity(intent);
				} else {
					final Dialog dialog = new Dialog(SubMenuActivity.this);
					dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
					dialog.setContentView(R.layout.dialog);
					dialog.setTitle(Choose._rezultat);

					dialog.setCancelable(true);

					TextView text = (TextView) dialog.findViewById(R.id.text);
					text.setText(Choose._pogoj);

					Button button = (Button) dialog
							.findViewById(R.id.dialogButtonOK);
					button.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							dialog.cancel();
						}
					});
					// now that the dialog is set up, it's time to show it
					dialog.show();
					dialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,
							R.drawable.mali_logo);
				}
				
			}
		}

		@Override
		protected void onPreExecute() {
			final Animation a = AnimationUtils.loadAnimation(SubMenuActivity.this,
					R.anim.rotacija);
			a.reset();
			loader.setVisibility(View.VISIBLE);
			loader.startAnimation(a);
			loader.setVisibility(View.INVISIBLE);
			
		}
	}

}
