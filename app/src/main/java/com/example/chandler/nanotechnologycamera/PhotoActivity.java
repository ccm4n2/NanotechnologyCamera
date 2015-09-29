package com.example.chandler.nanotechnologycamera;

import android.app.Activity;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.FileOutputStream;

public class PhotoActivity extends Activity {
    public static int intensityValues[][];
    String TAG = "NanoTech";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get the URI of the selected photo from the Intent
        Uri photo_uri = getIntent().getParcelableExtra("photo_path");
        //Convert photo URI to absolute filepath
        String convertedPath = getRealPathFromURI(photo_uri);

        //Set ImageView image to selected image
        setContentView(R.layout.activity_photo);
        ImageView picked_image = (ImageView) findViewById(R.id.Test_image);
        Bitmap bm = BitmapFactory.decodeFile(convertedPath);
        picked_image.setImageBitmap(bm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photo, menu);
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

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };

        //This method was deprecated in API level 11
        //Cursor cursor = managedQuery(contentUri, proj, null, null, null);

        CursorLoader cursorLoader = new CursorLoader(
                this,
                contentUri, proj, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();

        int column_index =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String string = cursor.getString(column_index);
        cursor.close();
        return string;
    }

    public void processPhoto(View view){
        //accessing image
        Uri photo_uri = getIntent().getParcelableExtra("photo_path");
        String convertedPath = getRealPathFromURI(photo_uri);
        Bitmap chosenImage = BitmapFactory.decodeFile(convertedPath);

        //image dimensions
        int height = chosenImage.getHeight();
        int width = chosenImage.getWidth();

        //creating an array to store green pixel values
        intensityValues = new int[height][width];
        Bitmap new_bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        //iterating through image and obtaining green pixel value to be stored in the array
        //each row at a time
        for(int i = 0; i<height; i++){

            //each column in each row
            for(int j=0; j<width; j++){
                int color = chosenImage.getPixel(j,i);

                //green color value of that pixel
                int green = Color.green(color);
                intensityValues[i][j] = green;
                int new_color = Color.rgb(0, green, 0);
                new_bitmap.setPixel(j, i, new_color);


            }
        }
        setContentView(R.layout.activity_photo);
        ImageView picked_image = (ImageView) findViewById(R.id.Test_image);
        picked_image.setImageBitmap(new_bitmap);
        //Intent intent = new Intent(this, ProcessedData.class);
        //intent.putExtra("new_bitmap", new_bitmap);
        //startActivity(intent);
    }

    public static int[][] getIntensityValues(){
        return intensityValues;
    }

}
