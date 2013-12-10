package hippocraticapps.glucopro.views;

import hippocraticapps.glucopro.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Chart extends View {

    private Paint background_;


    public Chart(Context context, AttributeSet atrs) {
        super(context, atrs);

        // http://www.tayloredmktg.com/rgb/
        background_ = new Paint();
        background_.setColor(getResources().getColor(R.color.settings_background));
    }



    public void draw(Canvas canvas) {
        canvas.drawPaint(background_);

    }
}
