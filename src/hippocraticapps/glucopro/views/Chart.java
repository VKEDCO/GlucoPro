package hippocraticapps.glucopro.views;

import java.util.ArrayList;
import java.util.Random;
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
import android.widget.Toast;

public class Chart extends View {

    private Paint background_, point_, line_;
    private SugarRecord[] records_;
    private static final float POINT_RADIUS = 5f;
    private Context context_;
    
    
    public Chart(Context context, AttributeSet atrs) {
        super(context, atrs);

        background_ = new Paint();
        background_.setColor(getResources().getColor(R.color.settings_background));
        
        point_ = new Paint();
        point_.setColor(getResources().getColor(R.color.settings_shift_circle));
        point_.setStrokeWidth(10);
        point_.setStyle(Paint.Style.STROKE);
        
        line_ = new Paint();
        line_.setColor(getResources().getColor(R.color.settings_offset_crosshair));
        line_.setStrokeWidth(6);
        line_.setStyle(Paint.Style.STROKE);
        
        //records_ = SugarTable.peekRange(GlucoDBAdapter.getInstance(), 28);
        //TODO: fully finish this
        records_ = new SugarRecord[14];
        Random prng = new Random(56);
        for (int j = 0; j < 14; j++)
        	records_[j] = new SugarRecord(j, j % 4, 0, prng.nextInt(200) + 100, j * 1000);
        
        context_ = context;
    }



    public void draw(Canvas canvas) {
        canvas.drawPaint(background_);
        
        float minTime = Long.MAX_VALUE, maxTime = Long.MIN_VALUE;
        for (SugarRecord record : records_) {
            minTime = Math.min(minTime, record.time);
            maxTime = Math.max(maxTime, record.time);
        }
        
        //Toast.makeText(context_, minTime + ", " + maxTime, 
		//		   Toast.LENGTH_SHORT).show();
        
        ArrayList<PointF> points = new ArrayList<PointF>(records_.length);
        //float scale = ;
        for (SugarRecord record : records_)
        	points.add(new PointF(record.level + 200, (record.time - minTime) / (maxTime - minTime) * getHeight()));
        
        for (int j = 0; j < points.size(); j++) {
        	if (j < points.size() - 1)
        		canvas.drawLine(points.get(j).x, points.get(j).y,
        			points.get(j + 1).x, points.get(j + 1).y, line_);
            canvas.drawCircle(points.get(j).x, points.get(j).y, POINT_RADIUS, point_);
        }
    }
}
