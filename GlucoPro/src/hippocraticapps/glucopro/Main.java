package hippocraticapps.glucopro;

import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class Main extends Activity {
	SugarTable sTable = new SugarTable();
	InsulinTable iTable = new InsulinTable();
	GlucoDBAdapter adptr = new GlucoDBAdapter(this);
	private BluetoothAdapter mBlutoothAdapter;
	private BluetoothChatService mChatService;
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
		SugarRecord[] sr = sTable.peekRange(adptr, 8);
		
		for(SugarRecord r: sr){
			Log.d("SugarRecord: ", "id="+r.id);
		}
		
		InsulinRecord test2 = new InsulinRecord(0,1,(float)120.0,12345678,0);
		iTable.insert(adptr, test2);
		test2 = new InsulinRecord(1,1,(float)120.1,12345678,0);
		iTable.insert(adptr, test2);
		test2 = new InsulinRecord(2,1,(float)120.2,12345678,1);
		iTable.insert(adptr, test2);
		InsulinRecord[] ir = iTable.peekRange(adptr, 150);
		
		for(InsulinRecord i: ir){
			Log.d("InsulinRecord: ", "dosage="+i.dose);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void launchConnectionActivity(View view){
		Intent intent = new Intent(this,DeviceListActivity.class);
		startActivity(intent);
	}

}
