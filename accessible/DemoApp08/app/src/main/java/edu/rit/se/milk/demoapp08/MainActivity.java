package edu.rit.se.milk.demoapp08;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String[] newsItems;
    NavigationView navigationView;
    TextView newsType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        newsType = (TextView) findViewById(R.id.textView2);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.getMenu().getItem(0).setChecked(true);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_breaking) {
            Toast.makeText(this,"Breaking News!", Toast.LENGTH_SHORT).show();
            newsType.setText("Breaking News");
            newsItems = new String[]{
                    "Breaking News 01",
                    "Breaking News 02",
                    "Breaking News 03",
                    "Breaking News 04",
                    "Breaking News 05",
                    "Breaking News 06",
                    "Breaking News 07",
                    "Breaking News 08",
                    "Breaking News 09",
                    "Breaking News 10",
                    "Breaking News 11",
                    "Breaking News 12"
            };
        } else if (id == R.id.nav_local) {
            Toast.makeText(this,"Local News!", Toast.LENGTH_SHORT).show();
            newsType.setText("Local News");
            newsItems = new String[]{
                    "Mayor steps down",
                    "Local man saves cat from drowning",
                    "Five alarm fire",
                    "Crash causes traffic chaos",
                    "Animal shelter closing down",
                    "Judge arrested",
                    "Local News 07",
                    "Local News 08",
                    "Local News 09",
                    "Local News 10",
                    "Local News 11",
                    "Local News 12"
            };
        } else if (id == R.id.nav_world) {
            Toast.makeText(this,"World News!", Toast.LENGTH_SHORT).show();
            newsType.setText("World News");
            newsItems = new String[]{
                    "Couple Admits to Crashing Wedding; Bride Finds Stunt Funny",
                    "An Alligator Lurking by the Road? No, Just a Plastic Toy",
                    "Police Officers Corral Loose Pig; Jokes Ensue",
                    "Meet the new heavyweight champion of dinosaurs: Patagotitan",
                    "Big Ben's bongs to fall silent next Monday for four years",
                    "Scientists find 91 volcanoes under Antarctic ice",
                    "World News 07",
                    "World News 08",
                    "World News 09",
                    "World News 10",
                    "World News 11",
                    "World News 12"
            };
        } else if (id == R.id.nav_sports) {
            Toast.makeText(this,"Sports News!", Toast.LENGTH_SHORT).show();
            newsType.setText("Sports News");
            newsItems = new String[]{
                    "Michael Owen shot England into the last eight of the World Cup",
                    "Never stop trying, Usain Bolt tells younger generation",
                    "Real Madrid C.F. to appeal Cristiano Ronaldo red card: Zinedine Zidane",
                    "Team India is a bunch of friends playing cricket together: Virat Kohli",
                    "Maria Sharapova withdraws from Cincinnati Open with arm injury",
                    "Alexander Zverev upsets Roger Federer for Rogers Cup title",
                    "Sports News 07",
                    "Sports News 08",
                    "Sports News 09",
                    "Sports News 10",
                    "Sports News 11",
                    "Sports News 12"
            };
        } else if (id == R.id.nav_share) {
            Toast.makeText(this,"Share", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_send) {
            Toast.makeText(this,"Send!", Toast.LENGTH_SHORT).show();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, newsItems);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
