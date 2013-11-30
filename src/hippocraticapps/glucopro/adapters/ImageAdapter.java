package hippocraticapps.glucopro.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import hippocraticapps.glucopro.R;

public class ImageAdapter extends BaseAdapter
{
	private Context context;
	private static final String[] gridValues = new String[] {
		"Test", "Information", "Settings"};


	public ImageAdapter(Context context)
	{
		this.context = context;
	}



	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View gridView;

		if (convertView == null)
		{
			gridView = new View(context);

			// get layout from mobile.xml
			gridView = inflater.inflate(R.layout.main_gridcell, null);

			// set value into textview
			TextView textView = (TextView)gridView.findViewById(R.id.gridItemLabel);
			textView.setText(gridValues[position]);

			// set image based on selected text
			ImageView imageView = (ImageView)gridView.findViewById(R.id.gridItemImage);

			String mobile = gridValues[position];

			if (mobile.equals("Test"))
				imageView.setImageResource(R.drawable.glucohealth_connect);
			else if (mobile.equals("Information"))
				imageView.setImageResource(R.drawable.information);
			else if (mobile.equals("Settings"))
				imageView.setImageResource(R.drawable.gear_settings);
			else
				imageView.setImageResource(R.drawable.blue_gradient);

		}
		else
			gridView = (View) convertView;

		return gridView;
	}



	@Override
	public int getCount()
	{
		return gridValues.length;
	}



	@Override
	public Object getItem(int position)
	{
		return null;
	}



	@Override
	public long getItemId(int position)
	{
		return 0;
	}
}
