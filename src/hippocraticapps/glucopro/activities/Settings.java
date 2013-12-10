package hippocraticapps.glucopro.activities;
import hippocraticapps.glucopro.R;
import hippocraticapps.glucopro.views.ShiftRotator;

import android.os.Bundle;
import android.app.Activity;

public class Settings extends Activity {
    private ShiftRotator shiftRotator_;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        shiftRotator_ = (ShiftRotator)findViewById(R.id.shiftRotator);
    }
}
