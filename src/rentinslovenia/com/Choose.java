package rentinslovenia.com;

import java.util.ArrayList;

import rentinslovenia.com.Localization.Croatia;
import rentinslovenia.com.Localization.Deutch;
import rentinslovenia.com.Localization.English;
import rentinslovenia.com.Localization.French;
import rentinslovenia.com.Localization.Italiano;
import rentinslovenia.com.Localization.Slovenija;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Choose extends Activity {

	// Controls
	// ImageView mOkBtn;
	// Spinner mChooseSpinner;
	// TextView mChosenTxt;
	// TextView mChosePlace;
	// TextView mChoseDistanceTxt;
	// Spinner mChosePriceSpinner;
	// Spinner mChoseDistanceSpinner;
	// TextView mChoosePriceTxt;
	// Button mChangeMainCategory;
	// EditText mSelectedKategor;

	// Buttons
	Button drinkBtn;
	Button foodDrinkBtn;
	Button accomodBtn;
	Button attractionBtn;
	Button eventBtn;
	Button excursionBtn;
	Button driveBtn;
	Button hotelsBtn;
	Button backBtn;
	
	//Images
	ImageView image1;
	ImageView image2;
	ImageView image3;
	ImageView image4;
	ImageView image5;
	ImageView image6;
	ImageView image7;
	ImageView image8;

	String _category;
	public static String _lang;
	String _price;
	String _distance;
	int _categorija = 0;

	// Localization strings
	String _distances[];
	String _prices[];
	String _places[];
	String _warning;
	String _addresOfChosen;
	public static String _napaka;
	String _povezava;
	public static String _nalaganje;
	public static String _rezultat;
	public static String _pogoj;
	String _cena;
	String _razdalja;

	// categories
	public static String _rentArray[];
	public static String _foodDrink[];
	public static String _accommodation[];
	public static String _attractions[];
	public static String _events[];
	public static String _excursionPoints[];
	public static String _rentalVenicles[];
	public static String _drink[];
	public static String _resort[];
//	
//	public static String _excursionNaturalAttractions[];
//	public static String _excursionCulturalAttractions[];
//	public static String _excursionVisitorFarms[];
//	public static String _excursionThemeTrails[];
//	public static String _rental[];
//	
//	public static String _rentalVessel[];
//	public static String _rentalSportEquipment[];
//	public static String _alongTheWay[];
//	public static String _alongTheWayRoadAssistance[];
//	public static String _alongTheWayUsefulINfo[];

	// Tree adapters for spinners categories
	MyAdapter<CharSequence> cat1;
	MyAdapter<CharSequence> cat2 = null;
	MyAdapter<CharSequence> cat3;

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
		setContentView(R.layout.chose);
		/*
		 * Button foodBtn; Button foodDrinkBtn; Button accomodBtn; Button
		 * attractionBtn; Button eventBtn; Button excursionBtn; Button driveBtn;
		 * Button hotelsBtn;
		 */

		drinkBtn = (Button) findViewById(R.id.pijaca);
		foodDrinkBtn = (Button) findViewById(R.id.hranainpijaca);
		accomodBtn = (Button) findViewById(R.id.nastanitev);
		attractionBtn = (Button) findViewById(R.id.znamenitosti);
		eventBtn = (Button) findViewById(R.id.dogodki);
		excursionBtn = (Button) findViewById(R.id.izletnisketocke);
		driveBtn = (Button) findViewById(R.id.prevoz);
		hotelsBtn = (Button) findViewById(R.id.letovisca);
		backBtn = (Button) findViewById(R.id.back);
		
		image1=(ImageView)findViewById(R.id.imageView1);
		image2=(ImageView)findViewById(R.id.imageView2);
		image3=(ImageView)findViewById(R.id.imageView3);
		image4=(ImageView)findViewById(R.id.imageView4);
		image5=(ImageView)findViewById(R.id.imageView5);
		image6=(ImageView)findViewById(R.id.imageView6);
		image7=(ImageView)findViewById(R.id.imageView7);
		image8=(ImageView)findViewById(R.id.imageView8);
		

		setPading(accomodBtn);
		setPading(foodDrinkBtn);
		setPading(drinkBtn);
		setPading(attractionBtn);
		setPading(eventBtn);
		setPading(excursionBtn);
		setPading(driveBtn);
		setPading(hotelsBtn);
		
//		setPading(image1);
//		setPading(image2);
//		setPading(image3);
//		setPading(image4);
//		setPading(image5);
//		setPading(image6);
//		setPading(image7);
//		setPading(image8);

		// mOkBtn = (ImageView) findViewById(R.id.okbutton);
		// mOkBtn.setMaxHeight((int) (RentInSloveniaActivity.height * 0.25));
		// mOkBtn.setMaxWidth((int) (RentInSloveniaActivity.width * 0.25));
		// //mChooseSpinner = (Spinner) findViewById(R.id.chooseSpinner);
		// mChosenTxt = (TextView) findViewById(R.id.choosenText);
		// mChoseDistanceTxt = (TextView) findViewById(R.id.textView1);
		// mChosePriceSpinner = (Spinner) findViewById(R.id.priceSpinner);
		// mChoseDistanceSpinner = (Spinner) findViewById(R.id.distanceSpinner);
		// mChangeMainCategory =(Button)findViewById(R.id.changeMain);
		// mSelectedKategor = (EditText) findViewById(R.id.kategorija);
		//
		// mChosePlace = (TextView) findViewById(R.id.Locationtxt);
		// int h = (int) (RentInSloveniaActivity.height * 0.06);
		// Log.d("REST", RentInSloveniaActivity.height + "."
		// + RentInSloveniaActivity.width);
		//
		// mChosePlace.setPadding(0, h, 0, 0);
		// mChosenTxt.setPadding(0, h, 0, 0);
		// mChoseDistanceTxt.setPadding(0, h, 0, 0);
		//
		// // Get my location
		// LocationResult locationResult = new LocationResult() {
		// @Override
		// public void gotLocation(Location location) {
		//
		// lat = (float) location.getLatitude();
		// lon = (float) location.getLongitude();
		// }
		// };
		// MyLocation myLocation = new MyLocation();
		// myLocation.getLocation(this, locationResult);
		//
		_lang = getIntent().getStringExtra("Lang");
		//
		// /*
		// * Localization
		// */
		if (_lang.equals("EN")) {
			//Main categories
			_rentArray = Localization.English._rentArray;
			//Sub categories
			_accommodation = Localization.English._accommodation;
			_foodDrink = Localization.English._foodDrink;
			_events = Localization.English._events;
			_excursionPoints = Localization.English._excursionPoints;
			_rentalVenicles = Localization.English._venichles;
			_drink=Localization.English._drink;
			_attractions=Localization.English._attractions;
			_resort=Localization.English._resort;
			
			_prices = English._prices;
			_distances = English._distances;
			_warning = Localization.English._warning;
			_addresOfChosen = Localization.English._choose;
			_napaka = English._error;
			_povezava = English._connection;
			_nalaganje = English._loading;
			_rezultat = English._noRezults;
			_pogoj = English._Conditions;
			_cena = English._price;
			_razdalja = English._distance;

		}
		if (_lang.equals("SI")) {

			//Main categories
			_rentArray = Localization.Slovenija._rentArray;
			//Sub categories
			_accommodation = Localization.Slovenija._accommodation;
			_foodDrink = Localization.Slovenija._foodDrink;
			_events = Localization.Slovenija._events;
			_excursionPoints = Localization.Slovenija._excursionPoints;
			_rentalVenicles = Localization.Slovenija._venichles;
			_drink=Localization.Slovenija._drink;
			_attractions=Localization.Slovenija._attractions;
			_resort=Localization.Slovenija._resort;
			
			_prices = Slovenija._prices;
			_distances = Slovenija._distances;
			_warning = Localization.Slovenija._warning;
			_addresOfChosen = Localization.Slovenija._choose;
			_napaka = Slovenija._error;
			_povezava = Slovenija._connection;
			_nalaganje = Slovenija._loading;
			_rezultat = Slovenija._noRezults;
			_pogoj = Slovenija._Conditions;
			_cena = Slovenija._price;
			_razdalja = Slovenija._distance;
		}
		if (_lang.equals("HR")) {
			//Main categories
			_rentArray = Localization.Croatia._rentArray;
			//Sub categories
			_accommodation = Localization.Croatia._accommodation;
			_foodDrink = Localization.Croatia._foodDrink;
			_events = Localization.Croatia._events;
			_excursionPoints = Localization.Croatia._excursionPoints;
			_rentalVenicles = Localization.Croatia._venichles;
			_drink=Localization.Croatia._drink;
			_attractions=Localization.Croatia._attractions;
			_resort=Localization.Croatia._resort;
			
			_prices = Croatia._prices;
			_distances = Croatia._distances;
			_warning = Localization.Croatia._warning;
			_addresOfChosen = Localization.Croatia._choose;
			_napaka = Croatia._error;
			_povezava = Croatia._connection;
			_nalaganje = Croatia._loading;
			_rezultat = Croatia._noRezults;
			_pogoj = Croatia._Conditions;
			_cena = Croatia._price;
			_razdalja = Croatia._distance;
		}
		if (_lang.equals("FR")) {
			//Main categories
			_rentArray = Localization.French._rentArray;
			//Sub categories
			_accommodation = Localization.French._accommodation;
			_foodDrink = Localization.French._foodDrink;
			_events = Localization.French._events;
			_excursionPoints = Localization.French._excursionPoints;
			_rentalVenicles = Localization.French._venichles;
			_drink=Localization.French._drink;
			_attractions=Localization.French._attractions;
			_resort=Localization.French._resort;
			
			_prices = French._prices;
			_distances = French._distances;
			_warning = Localization.French._warning;
			_addresOfChosen = Localization.French._choose;
			_napaka = French._error;
			_povezava = French._connection;
			_nalaganje = French._loading;
			_rezultat = French._noRezults;
			_pogoj = French._Conditions;
			_cena = French._price;
			_razdalja = French._distance;
		}
		if (_lang.equals("DE")) {
			//Main categories
			_rentArray = Localization.Deutch._rentArray;
			//Sub categories
			_accommodation = Localization.Deutch._accommodation;
			_foodDrink = Localization.Deutch._foodDrink;
			_events = Localization.Deutch._events;
			_excursionPoints = Localization.Deutch._excursionPoints;
			_rentalVenicles = Localization.Deutch._venichles;
			_drink=Localization.Deutch._drink;
			_attractions=Localization.Deutch._attractions;
			_resort=Localization.Deutch._resort;
			
			_prices = Deutch._prices;
			_distances = Deutch._distances;
			_warning = Localization.Deutch._warning;
			_addresOfChosen = Localization.Deutch._choose;
			_napaka = Deutch._error;
			_povezava = Deutch._connection;
			_nalaganje = Deutch._loading;
			_rezultat = Deutch._noRezults;
			_pogoj = Deutch._Conditions;
			_cena = Deutch._price;
			_razdalja = Deutch._distance;
		}
		if (_lang.equals("IT")) {
			//Main categories
			_rentArray = Localization.Italiano._rentArray;
			//Sub categories
			_accommodation = Localization.Italiano._accommodation;
			_foodDrink = Localization.Italiano._foodDrink;
			_events = Localization.Italiano._events;
			_excursionPoints = Localization.Italiano._excursionPoints;
			_rentalVenicles = Localization.Italiano._venichles;
			_drink=Localization.Italiano._drink;
			_attractions=Localization.Italiano._attractions;
			_resort=Localization.Italiano._resort;
			
			_prices = Italiano._prices;
			_distances = Italiano._distances;
			_warning = Localization.Italiano._warning;
			_addresOfChosen = Localization.Italiano._choose;
			_napaka = Italiano._error;
			_povezava = Italiano._connection;
			_nalaganje = Italiano._loading;
			_rezultat = Italiano._noRezults;
			_pogoj = Italiano._Conditions;
			_cena = Italiano._price;
			_razdalja = Italiano._distance;
		}

		//Set localized Text for Buttons
		accomodBtn.setText(_rentArray[0]);
		foodDrinkBtn.setText(_rentArray[1]);
		drinkBtn.setText(_rentArray[2]);
		attractionBtn.setText(_rentArray[3]);
		excursionBtn.setText(_rentArray[4]);
		driveBtn.setText(_rentArray[5]);
		hotelsBtn.setText(_rentArray[6]);
		eventBtn.setText(_rentArray[7]);
		
		accomodBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setKlikImage(accomodBtn);
				OpenIntent(accomodBtn.getText().toString(), 0);
			}
		});
		
		foodDrinkBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setKlikImage(foodDrinkBtn);
				OpenIntent(foodDrinkBtn.getText().toString(),1);
			}
		});
		
		drinkBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setKlikImage(drinkBtn);
				OpenIntent(drinkBtn.getText().toString(),2);
			}
		});
		
		attractionBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setKlikImage(attractionBtn);
				OpenIntent(attractionBtn.getText().toString(),3);
			}
		});
		
		excursionBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setKlikImage(excursionBtn);
				OpenIntent(excursionBtn.getText().toString(),4);
			}
		});
		
		driveBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setKlikImage(driveBtn);
				OpenIntent(driveBtn.getText().toString(),5);
			}
		});
		
		hotelsBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setKlikImage(hotelsBtn);
				OpenIntent(hotelsBtn.getText().toString(),6);
			}
		});
		
		eventBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setKlikImage(eventBtn);
				OpenIntent(eventBtn.getText().toString(),7);
			}
		});
		
		backBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(getBaseContext(), RentInSloveniaActivity.class);
				startActivity(in);
				finish();
			}
		});

		//
		// mChoseDistanceTxt.setText(_razdalja);
		// mChosePlace.setText("Izberi podkategorijo:");
		//
		// if (!isOnline()) {
		// Toast.makeText(getBaseContext(), _povezava, Toast.LENGTH_LONG)
		// .show();
		// }
		//
		// MyAdapter<CharSequence> spinnerArrayAdapter = new
		// MyAdapter<CharSequence>(
		// this, _rentArray);
		//
		// MyAdapter<CharSequence> spinnerDistanceAdapter = new
		// MyAdapter<CharSequence>(
		// this, _distances);
		//
		// MyAdapter<CharSequence> spinnerPriceAdapter = new
		// MyAdapter<CharSequence>(
		// this, _prices);
		//
		// //mChooseSpinner.setAdapter(spinnerArrayAdapter);// Prvi spinner je
		// vedno
		// // isti
		//
		// ShowAlertBox(getBaseContext());
		//
		// mChangeMainCategory.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// ShowAlertBox(getBaseContext());
		// }
		// });
		//
		// //v drugem spinnerju prikažem podkategorijo
		//
		//
		// mChoseDistanceSpinner.setAdapter(spinnerDistanceAdapter);
		//
		// mChosenTxt.setText(_addresOfChosen);
		//
		// mOkBtn.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		//
		// // if (mChooseSpinner.getSelectedItem().toString() == ""
		// // || mChooseSpinner.getSelectedItem().toString() == "") {
		// // Toast.makeText(getBaseContext(), R.string.warning,
		// // Toast.LENGTH_SHORT).show();
		// // } else {
		// _category = Slovenija._rentArray[_categorija];
		// _price = mChosePriceSpinner.getSelectedItem().toString();
		// _distance = Slovenija._distances[(int) mChoseDistanceSpinner
		// .getSelectedItemPosition()].toString();
		//
		// sendCommand(_category + "/"+_price+"/prazen");
		// //}
		// }
		// });

	}

//	public void ShowAlertBox(Context co) {
//		AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
//
//		alertbox.setTitle("Katere informacije želite danes?");
//
//		alertbox.setItems(_rentArray, new DialogInterface.OnClickListener() {
//
//			public void onClick(DialogInterface arg0, int item) {
//				// TODO Auto-generated method stub
//
//				if (item == 0)// Nastanitev
//				{
//					cat2 = new MyAdapter<CharSequence>(getApplicationContext(),
//							_accommodation);
//					// tretje kategorije tukaj nimamo
//				}
//				if (item == 1) {
//					cat2 = new MyAdapter<CharSequence>(getApplicationContext(),
//							_food);
//					// tretje kategorije tukaj nimamo
//				}
//
//				if (item == 2) {
//					cat2 = new MyAdapter<CharSequence>(getApplicationContext(),
//							_party);
//					// tretje kategorije tukaj nimamo
//				}
//				if (item == 3) {
//					cat2 = new MyAdapter<CharSequence>(getApplicationContext(),
//							_events);
//					// Pride tretja kategorija, èe je kultura izbrana
//				}
//
//				if (item == 4) {
//					cat2 = new MyAdapter<CharSequence>(getApplicationContext(),
//							_activities);
//					// tretje kategorije tukaj ni
//				}
//
//				if (item == 5) {
//					cat2 = new MyAdapter<CharSequence>(getApplicationContext(),
//							_excursionPoints);
//					// Pride tretja kategorija èe je izbrana Naravna, kulturna
//					// izletniška, ali tematsko turistièna pot
//				}
//
//				if (item == 6) {
//					cat2 = new MyAdapter<CharSequence>(getApplicationContext(),
//							_rental);
//					// Pride tretja kategorija, èe je vozila, plovila, športna
//					// oprema
//				}
//
//				if (item == 7) {
//					cat2 = new MyAdapter<CharSequence>(getApplicationContext(),
//							_alongTheWay);
//					// Pride tretja èe izberemo pomoè ali koristne info
//				}
//
//				if (cat2 != null) {
//					mChosePriceSpinner.setAdapter(cat2);
//				}
//
//				_categorija = item;
//
//				mSelectedKategor.setText("|->" + _rentArray[item]);
//
//			}
//		});
//
//		// show it
//		alertbox.create();
//		alertbox.show();
//
//		mSelectedKategor.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				ShowAlertBox(getBaseContext());
//			}
//		});
//
//	}

	/*
	 * Function to check if is internet connection
	 */
	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	/*
	 * Function for set padding
	 */

	public void setPading(Button b) {
		b.setPadding(0, 5, 0, 5);
		b.getLayoutParams().height = RentInSloveniaActivity.height/8;
	}
	
	public void setPading(ImageView b) {
		b.setPadding(0, 5, 0, 5);
		b.getLayoutParams().height = RentInSloveniaActivity.height/8;
		b.getLayoutParams().width = RentInSloveniaActivity.width/8;
	}
	
	/*
	 * Function for set src image on click
	 */
	
	public void setKlikImage(Button b)
	{
		Drawable dr = getResources().getDrawable(R.drawable.meni_gumb_click);
		b.setBackgroundDrawable(dr);
	}
	
	public void OpenIntent(String mainMeni, int num)
	{
		Intent in = new Intent(getApplicationContext(), SubMenuActivity.class);
		in.putExtra("GLAVNI", mainMeni);
		in.putExtra("NUM", num);
		startActivity(in);
	}

	/*
	 * Function send command to Rest server
	 */

	public void Loading() {
		// Toast.makeText(Choose.this, "Loading...", Toast.LENGTH_LONG)
		// .show();
	}

	public void Error(String result) {
		if (result.equals("ERROR"))
			Toast.makeText(Choose.this, _napaka, Toast.LENGTH_SHORT).show();
		else {
			Intent intent = new Intent(getApplicationContext(), Gmaps.class);
			startActivity(intent);
		}
	}

	
}
