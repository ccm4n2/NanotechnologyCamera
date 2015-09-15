package com.example.chandler.nanotechnologycamera;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;

public class MainActivity extends Activity {

    private final int SELECT_PHOTO = 1;
    private static final String TAG = "NanoTech";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void goToPhotos(View view){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
        Log.d(TAG, "intent launched");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent returnedIntent){
        Log.d(TAG, "result");
        super.onActivityResult(requestCode, resultCode, returnedIntent);
        if (requestCode == SELECT_PHOTO){
            Log.d(TAG, "select photo matched");
            Intent photoClass = new Intent(this, PhotoActivity.class);
            photoClass.putExtra("photo_path", returnedIntent.getData());
            startActivity(photoClass);
        }
    }
}
