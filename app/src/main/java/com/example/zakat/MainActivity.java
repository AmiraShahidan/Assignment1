package com.example.zakat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up toolbar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.itemconverter) {
            startActivity(new Intent(this, zakatconverter.class));
            return true;
        } else if (id == R.id.itemshare) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Please use my application - https://t.co/app");
            startActivity(Intent.createChooser(shareIntent, null));
            return true;
        } else if (id == R.id.itemabout) {
            startActivity(new Intent(this, aboutpage.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onFrameClick(View view) {
        if (view.getId() == R.id.frame1) {
            startActivity(new Intent(this, zakatconverter.class));
        }
    }

    public void onFrame2Click(View view) {
        if (view.getId() == R.id.frame2) {
            startActivity(new Intent(this, aboutpage.class));
        }
    }

    public void onFrame3Click(View view) {
        if (view.getId() == R.id.frame3) {
            startActivity(new Intent(this, profileactivity.class));
        }
    }
}
