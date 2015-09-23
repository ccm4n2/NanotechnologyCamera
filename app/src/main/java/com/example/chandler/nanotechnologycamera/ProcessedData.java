package com.example.chandler.nanotechnologycamera;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

public class ProcessedData extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processed_data);

        //creates a graph to display the green pixel value from the image that was processed
        GraphView graph = (GraphView) findViewById(R.id.graph);

        //array of data points
        DataPoint values[] = new DataPoint[intensityValues[0].length*intensityValues.length];

        //fills DataPoint Array
        int dataCount = 0;
        for(int i = 0; i< intensityValues.length; i++){
            for(int j = 0;j< intensityValues[0].length; j++){
                DataPoint data = new DataPoint(Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2)), intensityValues[i][j]);
                values[dataCount] = data;
                dataCount++;
            }
        }

        //plots the data
        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<DataPoint>(values);
        graph.addSeries(series);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_processed_data, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
