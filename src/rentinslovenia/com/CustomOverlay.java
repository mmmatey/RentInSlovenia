package rentinslovenia.com;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Projection;

public class CustomOverlay extends ItemizedOverlay<OverlayItem> {
	public Projection projection;
	private ArrayList<OverlayItem> mapOverlays = new ArrayList<OverlayItem>();
	   
	   private Context context;
	   
	   public CustomOverlay(Drawable defaultMarker) {
	        super(boundCenterBottom(defaultMarker));
	   }
	   
	   public CustomOverlay(Drawable defaultMarker, Context context) {
	        this(defaultMarker);
	        this.context = context;
	   }

	   @Override
	   protected OverlayItem createItem(int i) {
	      return mapOverlays.get(i);
	   }

	   @Override
	   public int size() {
	      return mapOverlays.size();
	   }
	   
	   @Override
	   protected boolean onTap(int index) {
	      OverlayItem item = mapOverlays.get(index);
	      AlertDialog.Builder dialog = new AlertDialog.Builder(context);
	      dialog.setTitle(item.getTitle());
	      dialog.setMessage(item.getSnippet());
	      dialog.show();
	      return true;
	   }
	   
	   public void addOverlay(OverlayItem overlay) {
	      mapOverlays.add(overlay);
	       this.populate();
	   }

	   @Override
		public boolean onTouchEvent(MotionEvent event, MapView mapView) {
			// TODO Auto-generated method stub
			 if (event.getAction() == 1) {                
		         GeoPoint p = mapView.getProjection().fromPixels(
		             (int) event.getX(),
		             (int) event.getY());
		             //Log.d("REST", "X:"+p.getLatitudeE6()+" Y:"+p.getLongitudeE6());
		     }                            
		     return false;
		}

//	@Override
//	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
//		// TODO Auto-generated method stub
//		Paint   mPaint = new Paint();
//        mPaint.setDither(true);
//        mPaint.setColor(Color.RED);
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//        mPaint.setStrokeJoin(Paint.Join.ROUND);
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
//        mPaint.setStrokeWidth(2);
//
//        GeoPoint gP1 = new GeoPoint(19240000,-99120000);
//        GeoPoint gP2 = new GeoPoint(37423157, -122085008);
//
//        Point p1 = new Point();
//        Point p2 = new Point();
//        Path path = new Path();
//        projection=Gmaps.mProjection;
//        projection.toPixels(gP1, p1);
//        projection.toPixels(gP2, p2);
//
//        path.moveTo(p2.x, p2.y);
//        path.lineTo(p1.x,p1.y);
//
//        canvas.drawPath(path, mPaint);
//	}
	   

}
