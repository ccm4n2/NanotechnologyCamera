package com.example.chandler.nanotechnologycamera;

import android.app.Activity;
import android.net.Uri;
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
        //Intent for picking photo from Gallery
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        //Intent for starting PhotoActivity
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
        Log.d(TAG, "intent launched");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent returnedIntent){
        Log.d(TAG, "result");
        super.onActivityResult(requestCode, resultCode, returnedIntent);
        if (requestCode == SELECT_PHOTO){
            if (resultCode == RESULT_OK) {
                Log.d(TAG, "select photo matched");
                Intent photoClass = new Intent(this, PhotoActivity.class);
                //Put selected photo URI in Intent
                photoClass.putExtra("photo_path", returnedIntent.getData());
                startActivity(photoClass);
            }
        }
    }

    public void openCamera(View view){
        String package_name = "pl.vipek.camera2";
        Intent camera_intent = getPackageManager().getLaunchIntentForPackage(package_name);
        if (camera_intent != null) {
            camera_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(camera_intent);
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("market://details?id=" + package_name));
            startActivity(intent);
        }
    }
}
