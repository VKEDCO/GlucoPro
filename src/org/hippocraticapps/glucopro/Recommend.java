package org.hippocraticapps.glucopro;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class Recommend extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recommendation);
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}