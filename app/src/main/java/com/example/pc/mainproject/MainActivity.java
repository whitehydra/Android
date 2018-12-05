package com.example.pc.mainproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button add_button = (Button)findViewById(R.id.button_add1);
        Button add_button2 = (Button)findViewById(R.id.button_add2);
        Button add_button3 = (Button)findViewById(R.id.button_add3);

        View.OnClickListener oclkBut = new View.OnClickListener() {
            @Override
            public void onClick(View v) { ;
                switch (v.getId()) {
                    case R.id.button_add1:
                        Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
                        intent.putExtra("NoteType","Consumption");
                        startActivity(intent);
                        break;
                    case R.id.button_add2:
                        intent = new Intent(MainActivity.this, CreateNoteActivity.class);
                        intent.putExtra("NoteType","Income");
                        startActivity(intent);
                        break;
                    case R.id.button_add3:
                        intent = new Intent(MainActivity.this, TestActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        };
        add_button.setOnClickListener(oclkBut);
        add_button2.setOnClickListener(oclkBut);
        add_button3.setOnClickListener(oclkBut);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_value) {
            Intent intent = new Intent(MainActivity.this, ValueActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_copy) {
            Intent intent = new Intent(MainActivity.this, ReservActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_protect) {
            Intent intent = new Intent(MainActivity.this, ProtectionActivity.class);
            startActivity(intent);

        }else if (id == R.id.nav_send) {
            String head = "Head";
            String body = "Body";

            String mailString = "mailto:" + "whitehydra@yandex.ru" + "?subject=" + head +
                    "&body=" + body;

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse(mailString));
            startActivity(emailIntent);

        }else if (id == R.id.nav_info) {
            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
