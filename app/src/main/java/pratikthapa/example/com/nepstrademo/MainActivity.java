package pratikthapa.example.com.nepstrademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabSelectListener;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Spinner spinnerStates;
    CarouselView carouselview;
    ArrayAdapter<CharSequence> adapter;
    BottomBar bottomBar;
    ViewPager viewPager;
    int[] images = {R.drawable.nepstrab, R.drawable.nepstraa, R.drawable.nepstrac};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

    /*    spinnerStates = (Spinner) findViewById(R.id.spinner_states);
        adapter = ArrayAdapter.createFromResource(this, R.array.states_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStates.setAdapter(adapter);*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        carouselview = (CarouselView) findViewById(R.id.carouselview);
        carouselview.setPageCount(images.length);
        carouselview.setImageListener(imagelistener);
     /*   TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Products"));
        tabLayout.addTab(tabLayout.newTab().setText("Search"));
//
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerrAdapter adapter = new PagerrAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

*/


        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        BottomBarTab dummy = bottomBar.getTabWithId(R.id.tab_home1);
        dummy.setVisibility(View.GONE);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_home1:
                        break;

                    case R.id.tab_home:
                        break;


                    case R.id.tab_products:
                        Intent in = new Intent(MainActivity.this, CategoriesActivity.class);
                        startActivity(in);

                        break;
                    case R.id.tab_order:
                        Intent iiin = new Intent(MainActivity.this, OrderActivity.class);
                        startActivity(iiin);

                        break;
                    case R.id.tab_account:
                        Intent iin = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(iin);
                        break;
                    default:
                        break;

                }
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        return true;
    }
    ImageListener imagelistener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(images[position]);
        }
    };


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        Fragment fragment = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_new_arrival) {
            Intent i = new Intent( MainActivity.this, MainActivity.class);
            startActivity(i);
            Toast.makeText(this, "new arrival clicked", Toast.LENGTH_SHORT).show();
            // Handle the camera action
//                    fragment = new DefaultFragment();
//                    fragmentManager.beginTransaction().replace(R.id.first_container, fragment).commit();

        } else if (id == R.id.nav_womens) {
            Intent intent = new Intent(MainActivity.this, WomensActivity.class);
            startActivity(intent);
//                    fragment = new ItemListFragment();
//                    fragmentManager.beginTransaction().replace(R.id.first_container, fragment).commit();

        } else if (id == R.id.nav_mens) {
            Toast.makeText(MainActivity.this, "No products were found matching your selection.", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_kids) {
            Toast.makeText(MainActivity.this, "No products were found matching your selection.", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_jewelry) {
            Toast.makeText(MainActivity.this, "No products were found matching your selection.", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_books) {
            Toast.makeText(MainActivity.this, "No products were found matching your selection.", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_sports) {
            Toast.makeText(MainActivity.this, "No products were found matching your selection.", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_art_and_craft) {
            Intent intent1 = new Intent(MainActivity.this, ArtCraft.class);
            startActivity(intent1);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}