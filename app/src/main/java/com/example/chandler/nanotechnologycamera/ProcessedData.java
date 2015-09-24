package com.example.chandler.nanotechnologycamera;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;
import android.graphics.Color;
import android.widget.ImageView;

public class ProcessedData extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //create activity and access the chosen image from previous intent
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processed_data);
        String path = getIntent().getParcelableExtra("path");
        Bitmap chosenImage = BitmapFactory.decodeFile(path);

        //create a new bitmap that will become the processed image
        Bitmap convertedImage = Bitmap.createBitmap(chosenImage.getWidth(), chosenImage.getHeight(), Bitmap.Config.ARGB_8888);

        for(int i =0; i<convertedImage.getHeight();i++){
            for(int j=0; j<convertedImage.getWidth();j++){
                //get pixel color int
                int pixel = chosenImage.getPixel(j,i);

                //get green shade of pixel
                int green = Color.green(pixel);

                //set color of converted image to this green value
                convertedImage.setPixel(j,i,green);
            }
        }

        //set image view to processed image
        ImageView picked_image = (ImageView) findViewById(R.id.Test_image);
        picked_image.setImageBitmap(convertedImage);
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
