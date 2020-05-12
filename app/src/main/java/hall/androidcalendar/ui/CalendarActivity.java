package hall.androidcalendar.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.parse.ParseUser;

import java.time.LocalDateTime;

import hall.androidcalendar.Calendar;
import hall.androidcalendar.ui.events.EventActivity;
import hall.androidcalendar.EventManager;
import hall.androidcalendar.R;
import hall.androidcalendar.UserManager;
import hall.androidcalendar.ui.month.MonthViewActivity;

public class CalendarActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener,
        NavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigation;
    Fragment currentFragment;
    public static Calendar currentCalendar= new Calendar(1);
    UserManager userManager;
    RelativeLayout rl;
    static EventManager eventManager;
    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = findViewById(R.id.toolbar);
      //  rl = findViewById(R.id.rl_duration_selector);
        setSupportActionBar(toolbar);
        this.userManager = new UserManager(this);
        eventManager = new EventManager();
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new MonthViewActivity()).commit();
        navigationView.setCheckedItem(R.id.nav_month);

        TextView tv = new TextView(this);
        RelativeLayout.LayoutParams par = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        par.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        par.rightMargin = 10;
     //   rl.addView(tv, par);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_logout){
            ParseUser.logOut();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void createEvent(View view){
//        Intent intent = new Intent(this, EventActivity.class);
//        startActivity(intent);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new EventActivity()).commit();
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.add_cal:
//                createCalendar();
//                return true;
//            case R.id.cal_switch:
//
//                return true;
//            default:
//                return false;
//        }
        return true;
    }

    public void createCalendar(){
        this.userManager.createCalendar();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_month:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MonthViewActivity()).commit();
                break;
            case R.id.nav_week:
                break;
            case R.id.nav_day:
                break;

        }
        return true;
    }


}

