package hippocraticapps.glucopro.activities;
import hippocraticapps.glucopro.R;
import hippocraticapps.glucopro.adapters.ImageAdapter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.widget.AdapterView.OnItemClickListener;

public class Main extends Activity
{
	GridView gridView;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		gridView = (GridView)findViewById(R.id.mainGridView);

		gridView.setAdapter(new ImageAdapter(getApplicationContext()));

		gridView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View v, int position, long id)
			{
				//Toast.makeText(
				//   getApplicationContext(),
				//   ((TextView) v.findViewById(R.id.grid_item_label))
				//   .getText(), Toast.LENGTH_SHORT).show();
			}
		});
	}
}
