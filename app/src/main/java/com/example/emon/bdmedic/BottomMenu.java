package com.example.emon.bdmedic;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.emon.bdmedic.ContactList.ContactFragment;
import com.example.emon.bdmedic.Media.MediaFragment;

import com.example.emon.bdmedic.NavDrawer.Profile;
import com.example.emon.bdmedic.PriceCalculator.PriceCalculator;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class BottomMenu extends AppCompatActivity {

    private TextView mTextMessage;
    private Toolbar toolbar;

  private Fragment selectedFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            //selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchFragment(new MenuFeed());
//                    selectedFragment = new MenuFeed2();
                    break;

                case R.id.navigation_contact:
  //                  selectedFragment = new ContactFragment();
                    switchFragment(new ContactFragment());
                    break;
                case R.id.navigation_media:
                    //  selectedFragment = new ContactFragment();
                    switchFragment(new MediaFragment());
                    break;
            }

            return true;
        }
    };

    @Override
    public void onBackPressed() {
        finishAffinity();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_menu);

        mTextMessage = (TextView) findViewById(R.id.message);


        //toolbar
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_health);
        BottomNavigationView  navigation =  findViewById(R.id.bNavigation);

        new DrawerBuilder().withActivity(this).build();

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.drawer_item_home).withIcon(R.drawable.profile_image);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.share).withIcon(R.drawable.ic_share_black_24dp);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName(R.string.rate_the_app).withIcon(R.drawable.ic_grade_black_24dp);
//        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIcon(R.drawable.ic_disease_s).;


//create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        item2,
                        item3

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        int id = (int) drawerItem.getIdentifier();
                        switch (id){
                            case 1:
                                Intent intent = new Intent(BottomMenu.this, Profile.class);
                                startActivity(intent);
//                                Toast.makeText(BottomMenu.this,"1 clicked",Toast.LENGTH_SHORT).show();
                            break;
                            case 2:
                                Intent i = new Intent();
                                i.setAction(Intent.ACTION_SEND);
                                i.putExtra(Intent.EXTRA_TEXT,"Massege");
                                i.setType("text/plain");
                                startActivity(Intent.createChooser(i,"Share"));
                                Toast.makeText(BottomMenu.this,"2 clicked",Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                                final String appPackageNameRating = getPackageName(); // getPackageName() from Context or Activity object
                                try {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageNameRating)));
                                }
                                catch (android.content.ActivityNotFoundException anfe) {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageNameRating)));
                                }
                                break;
                        }
                        // do something with the clicked item :D


                        return true;
                    }

                })
                .build();
       /* DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navigation.setSelectedItemId(R.id.navigation_home);
    }


  /*  @Override
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
        if (id == R.id.action_search) {
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

  /*  public void switchFragment(Fragment baseFragment){
        try{
            FragmentTransaction
        }
    }*/

    public void switchFragment(Fragment baseFragment) {
        try {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            if (getSupportFragmentManager().findFragmentById(R.id.frame_layout) == null) {
                ft.add(R.id.frame_layout, baseFragment);

            } else {
                ft.replace(R.id.frame_layout, baseFragment);
            }
            ft.addToBackStack(null);
            ft.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


  /*  @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }*/
   /* @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.nav_camera) {
            // Handle the camera action
//            switchDrawerFragment(new ProfileFragment());
           Intent intent = new Intent(BottomMenu.this, PriceCalculator.class);
           startActivity(intent);

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/

   /* public void switchDrawerFragment(Fragment baseFragment) {
        try {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            if (getSupportFragmentManager().findFragmentById(R.id.drawer_frame) == null) {
                ft.add(R.id.drawer_frame, baseFragment);

            } else {
                ft.replace(R.id.drawer_frame, baseFragment);
            }
            ft.addToBackStack(null);
            ft.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
