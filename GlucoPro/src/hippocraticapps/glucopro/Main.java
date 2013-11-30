package hippocraticapps.glucopro;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;

public class Main extends Activity {
	SugarTable sTable = new SugarTable();
	GlucoDBAdapter adptr = new GlucoDBAdapter(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		SugarRecord test = new SugarRecord(0,1,0,(float)120.2,12345678);
		sTable.insert(adptr, test);
		test = new SugarRecord(1,1,0,(float)120.2,12345678);
		sTable.insert(adptr, test);
		test = new SugarRecord(2,1,0,(float)120.2,12345678);
		sTable.insert(adptr, test);
		SugarRecord[] sr = sTable.peekRange(adptr, 3);
		
		for(SugarRecord r: sr){
			Log.d("SugarRecord: ", "id="+r.id);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
