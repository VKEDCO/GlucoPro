package hippocraticapps.glucopro.activities;
import hippocraticapps.glucopro.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MealInput extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_input);
        
        Button b = (Button)findViewById(R.id.meal_input_button);
        final TextView text = (TextView)findViewById(R.id.meal_input_input);
        final MealInput thisActivity = this;
        
        b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				int value = Integer.parseInt(text.getText().toString());
				Toast.makeText(getApplicationContext(), "Entered " + value + " into our records.", 
						   Toast.LENGTH_SHORT).show();
				
				//TODO: enter MealRecord into database
				
				Intent intent = new Intent();
                intent.setClass(thisActivity, Recommend.class);
                startActivity(intent);
                
                thisActivity.finish();
			}
        });
    }
}
