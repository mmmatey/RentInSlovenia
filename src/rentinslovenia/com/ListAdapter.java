package rentinslovenia.com;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

	LayoutInflater inflater = null;
	private ArrayList<Rent> data;
	private Activity activity;

	public ListAdapter(Activity a, ArrayList<Rent> f) {
		data = f;
		activity = a;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// imageLoader = ImageLoader.getInstance();
		// imageLoader.init(ImageLoaderConfiguration.createDefault(a
		// .getApplicationContext()));
		// imageLoader.clearMemoryCache();
		// imageLoader.clearDiscCache();
		// ImageLoaderConfiguration config = new
		// ImageLoaderConfiguration.Builder(
		// a.getApplicationContext())
		// .memoryCacheExtraOptions(
		// (int) (RentInSloveniaActivity.width * 0.25),
		// (int) (RentInSloveniaActivity.height * 0.25))
		// // max width, max height
		// // Can slow ImageLoader, use it carefully (Better don't use it)
		// .threadPoolSize(3)
		// .threadPriority(Thread.NORM_PRIORITY - 1)
		// .denyCacheImageMultipleSizesInMemory()
		// .offOutOfMemoryHandling()
		// .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
		// // You can pass your own memory cache implementation// You can
		// // pass your own disc cache implementation
		// .discCacheFileNameGenerator(new HashCodeFileNameGenerator())
		// .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
		// .enableLogging().build();

		// imageLoader.init(config);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		View vi = convertView;
		final Context con = activity.getBaseContext();
		final int pos = position;

		Rent f = data.get(position);
		if (convertView == null) {
			vi = inflater.inflate(R.layout.rezult, null);
			holder = new ViewHolder();
			holder.address = (TextView) vi.findViewById(R.id.label);
			holder.mMapImage = (ImageView) vi.findViewById(R.id.mapa);
			holder.mPhoneImage = (ImageView) vi.findViewById(R.id.klic);
			Log.d("REST", f._image);
			holder.mMapImage.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent in = new Intent(con, Gmaps.class);
					in.putExtra("X",
							SubMenuActivity._rezultReal.get(pos)._X);
					in.putExtra("Y",SubMenuActivity._rezultReal.get(pos)._Y);
					in.putExtra("Name",SubMenuActivity._rezultReal.get(pos)._name);
					in.putExtra("Address", SubMenuActivity._rezultReal.get(pos)._address);
					//MoreActivity mm = new MoreActivity();
					
					activity.startActivity(in);

				}
			});

			holder.mPhoneImage.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub

				}
			});
			// holder.image = (ImageView) vi.findViewById(R.id.icon);
			// holder.time = (TextView) vi.findViewById(R.id.textView1);
			// holder.place = (TextView) vi.findViewById(R.id.textView2);
			vi.setTag(holder);
		} else {
			holder = (ViewHolder) vi.getTag();
		}

		int h = (int) (RentInSloveniaActivity.height * 0.25);
		int w = (int) (RentInSloveniaActivity.width * 0.50);

		Log.d("REST", "");
		Distance d = SubMenuActivity._razdaljeReal.get(position);
		Log.d("REST", "Razdalja" + SubMenuActivity._razdaljeReal.get(position));
		holder.address.setText(f._type);
		// holder.time.setText("");
		// holder.place.setText(d._dolzina + "");

		// Drawable dr = vi.getResources().getDrawable(R.drawable.load);
		// holder.image.setImageDrawable(dr);

		// imageLoader.displayImage(imageUrl, holder.image);
		// holder.image.setImageBitmap(SubMenuActivity._images.get(position));
		// holder.image.getLayoutParams().height = h;
		// holder.image.getLayoutParams().width = w;

		return vi;
	}

	static class ViewHolder {
		TextView address;
		ImageView mMapImage;
		ImageView mPhoneImage;
	}

}
