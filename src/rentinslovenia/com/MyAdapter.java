package rentinslovenia.com;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyAdapter<T> extends ArrayAdapter<T> {

	public MyAdapter(Context ctx, T [] objects)
    {
        super(ctx, android.R.layout.simple_list_item_checked, objects);
    }
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
		((TextView) view).setGravity(Gravity.CENTER);
		return view;
	};
	
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = super.getView(position, convertView, parent);

        //we know that simple_spinner_item has android.R.id.text1 TextView:         
		((TextView) view).setGravity(Gravity.CENTER);
        /* if(isDroidX) {*/
            TextView text = (TextView)view.findViewById(android.R.id.text1);
            text.setTextColor(Color.parseColor("#0E7010"));
            //text.setGravity(Gravity.CENTER_HORIZONTAL);
            //choose your color :)
            

            

        /*}*/

        return view;
	}
}
