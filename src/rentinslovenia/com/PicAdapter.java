package rentinslovenia.com;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class PicAdapter extends BaseAdapter {

	// use the default gallery background image
	private Activity context;

	private static ImageView imageView;

	private ArrayList<Bitmap> plotsImages;

	private static ViewHolder holder;

	public PicAdapter(Activity context, ArrayList<Bitmap> plotsImages) {

		this.context = context;
		this.plotsImages = plotsImages;

	}

	@Override
	public int getCount() {
		return plotsImages.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {

			holder = new ViewHolder();

			imageView = new ImageView(this.context);

			imageView.setPadding(3, 3, 3, 3);

			convertView = imageView;

			holder.imageView = imageView;

			convertView.setTag(holder);

		} else {

			holder = (ViewHolder) convertView.getTag();
		}

		holder.imageView.setImageBitmap(plotsImages.get(position));

		holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		holder.imageView.setLayoutParams(new Gallery.LayoutParams(150, 90));

		return imageView;
	}

	private static class ViewHolder {
		ImageView imageView;
	}

}