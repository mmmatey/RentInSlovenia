package rentinslovenia.com;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Rezults extends Activity {

	ListView list;
	ListAdapter adapter;
	TextView address;
	ImageView exitBtn;
	
	public static ArrayList<Info> _rezult = new ArrayList<Info>();
	public int pos = 0; // Position of click

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rezults);

		list = (ListView) findViewById(R.id.listek);
		adapter = new ListAdapter(this, SubMenuActivity._rezultReal);
		address = (TextView) findViewById(R.id.txtNaslov);
		exitBtn=(ImageView)findViewById(R.id.exitBtn);

		address.setText("Offer:");

		list.setAdapter(adapter);
		list.setOnItemClickListener(selectRent);
		
		exitBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(Rezults.this);
				builder.setTitle("Exit");
				builder.setMessage("Dou you want to close application?")
				       .setCancelable(false)
				       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int id) {
				        	   moveTaskToBack(true);
				           }
				       })
				       .setNegativeButton("NO", new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.cancel();
						}
					});
				AlertDialog alert = builder.create();
				alert.show();
				
			}
		});

	}

	private OnItemClickListener selectRent = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> a, View v, int pozition, long id) {

//			sendCommand("info/" + Choose._rezult.get(position)._ID + "/"
//					+ Choose._rezult.get(position)._IDcat, position);
			Intent in = new Intent(getBaseContext(), GalerijaActivity.class);
			Log.d("REST", "tusma");
			in.putExtra("INFO", SubMenuActivity._rezultReal.get(pozition)._address);
//			in.putExtra("X", Choose._rezultReal.get(pozition)._X);
//			in.putExtra("Y", Choose._rezultReal.get(pozition)._Y);
//			in.putExtra("Name", Choose._rezultReal.get(pozition)._name);
//			in.putExtra("Address", Choose._rezultReal.get(pozition)._address);
//			in.putExtra("Mail", Choose._rezultReal.get(pozition)._eMail);
//			in.putExtra("Phone", Choose._rezultReal.get(pozition)._phone);
//			in.putExtra("Cena", Choose._rezultReal.get(pozition)._price);
//			in.putExtra("Znamka", Choose._rezultReal.get(pozition)._type);
//			in.putExtra("Slika", Choose._rezultReal.get(pozition)._image);
//			in.putExtra("POS", pozition);
			startActivity(in);

		}
	};
}
