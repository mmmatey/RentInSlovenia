package rentinslovenia.com;

import android.util.FloatMath;
import android.util.Log;

public class Distance {

	public String _dolzina;
	public String _start;
	public String _end;

	@Override
	public String toString() {
		return _dolzina;
	}

	public double gps2m(float lat_a, float lng_a, float lat_b, float lng_b) {
		float pk = (float) (180 / 3.14169);

		float a1 = lat_a / pk;
		float a2 = lng_a / pk;
		float b1 = lat_b / pk;
		float b2 = lng_b / pk;

		float t1 = FloatMath.cos(a1) * FloatMath.cos(a2) * FloatMath.cos(b1)
				* FloatMath.cos(b2);
		float t2 = FloatMath.cos(a1) * FloatMath.sin(a2) * FloatMath.cos(b1)
				* FloatMath.sin(b2);
		float t3 = FloatMath.sin(a1) * FloatMath.sin(b1);
		double tt = Math.acos(t1 + t2 + t3);
		Log.d("REST", tt+"");
		return 6366000 * tt;
	}

	public String gps2m(double lat, double lon, double parseDouble,
			double parseDouble2) {
		// TODO Auto-generated method stub
		return null;
	}
}
