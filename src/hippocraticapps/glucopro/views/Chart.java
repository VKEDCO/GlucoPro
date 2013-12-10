package hippocraticapps.glucopro.views;

import java.util.ArrayList;
import hippocraticapps.glucopro.R;
import hippocraticapps.glucopro.database.GlucoDBAdapter;
import hippocraticapps.glucopro.database.SugarRecord;
import hippocraticapps.glucopro.database.SugarTable;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

public class Chart extends View {

    private Paint background_, point_, line_;
    private SugarRecord[] records_;
    private static final float POINT_RADIUS = 5f;

    
    public Chart(Context context, AttributeSet atrs) {
        super(context, atrs);

        background_ = new Paint();
        background_.setColor(getResources().getColor(R.color.settings_background));
        
        point_ = new Paint();
        point_.setColor(getResources().getColor(R.color.settings_shift_circle));
        point_.setStrokeWidth(2);
        point_.setStyle(Paint.Style.STROKE);
        
        line_ = new Paint();
        line_.setColor(getResources().getColor(R.color.settings_offset_crosshair));
        line_.setStrokeWidth(3);
        line_.setStyle(Paint.Style.STROKE);
        
        records_ = SugarTable.peekRange(new GlucoDBAdapter(context), 28);
    }



    public void draw(Canvas canvas) {
        canvas.drawPaint(background_);
        
        long minTime = Long.MAX_VALUE, maxTime = Long.MIN_VALUE;
        for (SugarRecord record : records_) {
            minTime = Math.min(minTime, record.time);
            maxTime = Math.max(maxTime, record.time);
        }
        
        ArrayList<PointF> points = new ArrayList<PointF>(records_.length);
        float scale = (maxTime - minTime) * getHeight();
        for (SugarRecord record : records_)
        	points.add(new PointF((record.time - minTime) * scale, record.level));
        
        for (int j = 0; j < points.size(); j++) {
        	if (j < points.size() - 1)
        		canvas.drawLine(points.get(j).x, points.get(j).y,
        			points.get(j + 1).x, points.get(j + 1).y, line_);
            canvas.drawCircle(points.get(j).x, points.get(j).y, POINT_RADIUS, point_);
        }
    }
}
