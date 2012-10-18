package rentinslovenia.com;

import java.util.List;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Projection;

public class Gmaps extends MapActivity implements LocationListener {

	MapView mMapView;
	GeoPoint p;
	MapController mapController;
	Button mapsView;
	Button sateliteView;
	public static Projection mProjection;

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void onCreate(Bundle icicle) {
		// TODO Auto-generated method stub
		super.onCreate(icicle);
		setContentView(R.layout.map);

		int latitude = Integer.parseInt(getIntent().getStringExtra("X"));
		int longitude = Integer.parseInt(getIntent().getStringExtra("Y"));
		String name = getIntent().getStringExtra("Name");
		String address = getIntent().getStringExtra("Address");
		mapsView=(Button)findViewById(R.id.maps);
		sateliteView=(Button)findViewById(R.id.satelite);

		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 500.0f,
				this);

		// for (Rent rr : Choose._rezult) {
		// tocke.add(new GeoPoint(Integer.parseInt(rr._X), Integer
		// .parseInt(rr._Y)));
		// }

		GeoPoint gp = new GeoPoint(latitude, longitude);

		mMapView = (MapView) findViewById(R.id.map_view);
		mMapView.setBuiltInZoomControls(true);
		mMapView.setSatellite(false);
		mMapView.setTraffic(true);
		
		mProjection = mMapView.getProjection();
		Point out = new Point();
		mMapView.getProjection().toPixels(gp, out);

		// List<Overlay> mapOverlays = mMapView.getOverlays();
		//
		// Drawable drawable = getResources().getDrawable(R.drawable.pin);
		// CustomOverlay itemizedOverlay = new CustomOverlay(drawable, this);
		//
		// OverlayItem overlayitem = new OverlayItem(gp, name, address);
		// itemizedOverlay.addOverlay(overlayitem);
		// mapOverlays.add(itemizedOverlay);
		// mapController = mMapView.getController();
		// mapController.animateTo(gp);
		// mapController.setZoom(10);

		List<Overlay> mapOverlays = mMapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.pin);
		CustomOverlay itemizedOverlay = new CustomOverlay(drawable, this);

		GeoPoint point = new GeoPoint(latitude, longitude);
		OverlayItem overlayitem = new OverlayItem(point, name,
				"\n"+address);

		itemizedOverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedOverlay);

		MapController mapController = mMapView.getController();
		mapController.setZoom(10);
		mapController.animateTo(point);
		
		mapsView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mMapView.setSatellite(false);
	        	mMapView.setTraffic(true);
			}
		});
		
		sateliteView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mMapView.setSatellite(true);
	        	mMapView.setTraffic(false);
			}
		});

	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		if (location != null) {
			double lat = location.getLatitude();
			double lng = location.getLongitude();
			p = new GeoPoint((int) lat * 1000000, (int) lng * 1000000);
			mapController.animateTo(p);
		}
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
        case R.id.satelit:    
        	mMapView.setSatellite(true);
        	mMapView.setTraffic(false);
                            break;
        case R.id.zemljevid:
        	mMapView.setSatellite(false);
        	mMapView.setTraffic(true);
                            break;
    }
    return true;
	}
}
