package com.vacuum.app.dostor;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.login.LoginManager;
import com.facebook.stetho.Stetho;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.twitter.sdk.android.Twitter;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;
import com.vacuum.app.dostor.Fragmnts.GridFragment;
import com.vacuum.app.dostor.NavigationDrawer.AboutUsActivity;
import com.vacuum.app.dostor.NavigationDrawer.CustomTypefaceSpan;
import com.vacuum.app.dostor.NavigationDrawer.HomeFragment;
import com.vacuum.app.dostor.NavigationDrawer.FavoriteFragment;
import com.vacuum.app.dostor.NavigationDrawer.PrivacyPolicyActivity;
import com.vacuum.app.dostor.NavigationDrawer.SettingsFragment;
import com.vacuum.app.dostor.NavigationDrawer.TermsConditions;
import com.vacuum.app.dostor.Notify.NotifyFragment;
import com.vacuum.app.dostor.Search.SearchFragment;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.vacuum.app.dostor.SplashScreen.MY_PREFS_NAME;


public class MainActivity extends AppCompatActivity {

    Context mContext;
    public Toolbar mToolbar;
    public ImageView notify_layout;
    public SearchView editsearch;
    public RelativeLayout red_badge;
    SearchFragment searchFragment;
    //=======================================================
    public NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtWebsite;
    ActionBarDrawerToggle actionBarDrawerToggle;
    View parentLayout;
    SharedPreferences.Editor editor;
    GoogleApiClient mGoogleApiClient2;
    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_browse = "browse";
    private static final String TAG_favourite = "favourite";
    private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_SETTINGS = "settings";
    public static String CURRENT_TAG = TAG_HOME;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this.getApplicationContext();
        parentLayout = findViewById(android.R.id.content);
        mToolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        editsearch =  findViewById(R.id.search_view);
        notify_layout =  findViewById(R.id.image);
        red_badge =  findViewById(R.id.red_badge);
        drawer =  findViewById(R.id.drawer_layout);
        navigationView =  findViewById(R.id.nav_view);
        // Navigation view header
        navHeader = navigationView.getHeaderView(0);
        txtName =  navHeader.findViewById(R.id.name);
        txtWebsite =  navHeader.findViewById(R.id.website);
        imgNavHeaderBg =  navHeader.findViewById(R.id.img_header_bg);
        imgProfile =  navHeader.findViewById(R.id.img_profile);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/airbnb.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());

        Realm.init(this);
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build());

        notify_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "Notify", Toast.LENGTH_LONG).show();
                //red_badge.setVisibility(View.GONE);
                NotifyFragment notifyFragment = new NotifyFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, notifyFragment,TAG_NOTIFICATIONS)
                        .commitAllowingStateLoss();
                navItemIndex = 3;
                CURRENT_TAG = TAG_NOTIFICATIONS;
                selectNavMenu();
                setToolbarTitle();
            }
        });
        editsearch.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFragment = new SearchFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, searchFragment,TAG_NOTIFICATIONS)
                        .commitAllowingStateLoss();
                navItemIndex = 3;
                CURRENT_TAG = TAG_NOTIFICATIONS;
            }
        });
        //================================================

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);


        Snackbar.make(parentLayout, "Rate our app", Snackbar.LENGTH_LONG)
                        .setAction("Rate", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Rate_us();
                            }
                        }).show();

        loadNavHeader();
        setUpNavigationView();
        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
            actionBarDrawerToggle.syncState();
        }
        //===================================================

        mGoogleApiClient2 = new GoogleApiClient.Builder(mContext)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();
        mGoogleApiClient2.connect();

        //==================================================
        Menu m = navigationView.getMenu();
        for (int i=0;i<m.size();i++) {
            MenuItem mi = m.getItem(i);

            //for aapplying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu!=null && subMenu.size() >0 ) {
                for (int j=0; j <subMenu.size();j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }
            applyFontToMenuItem(mi);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (navItemIndex == 2) {
            getMenuInflater().inflate(R.menu.favourite, menu);
        }else if (navItemIndex == 3) {
            getMenuInflater().inflate(R.menu.notifications, menu);
        }else {
            getMenuInflater().inflate(R.menu.menu_menu, menu);
        }

        //============================================================
        for(int i=0;i<menu.size();i++)
        {
            MenuItem mi = menu.getItem(i);
            applyFontToMenuItem(mi);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        int id = item.getItemId();

        if (id == R.id.log_out) {
            log_out();
        }
        if (id == R.id.action_mark_all_read) {
            Toast.makeText(getApplicationContext(), "All notifications marked as read!", Toast.LENGTH_LONG).show();
        }
        if (id == R.id.action_clear_notifications) {
            Toast.makeText(getApplicationContext(), "Clear all notifications!", Toast.LENGTH_LONG).show();
        }

        if (id == R.id.about) {
            startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
            drawer.closeDrawers();
        }
        if (id == R.id.settings) {
            navItemIndex = 4;
            CURRENT_TAG = TAG_SETTINGS;
            loadHomeFragment();
        }
        if (id == R.id.Notification) {
            navItemIndex = 3;
            CURRENT_TAG = TAG_NOTIFICATIONS;
            loadHomeFragment();
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        if (!editsearch.isIconified()) {
            editsearch.onActionViewCollapsed();
            loadHomeFragment(); }
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return; }
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return ;
            }
        }
        if (CURRENT_TAG == TAG_HOME) {
            super.onBackPressed();
        }
    }
    //====================================================================================
    //====================================================================================
    //====================================================================================
    private void loadNavHeader() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String name = prefs.getString("name", "User Name");//"No name defined" is the default value.
        String email = prefs.getString("email", "email@gmail.com");
        String imageurl = prefs.getString("imageurl", "defaultStringIfNothingFound");
        String cover = prefs.getString("cover", "defaultStringIfNothingFound");
        txtName.setText(name);
        txtWebsite.setText(email);

        RequestOptions options = RequestOptions
                .circleCropTransform()
                .placeholder(R.drawable.thin128);
        RequestOptions options2 = new RequestOptions()
                .placeholder(R.drawable.nav);
        Glide.with(this).load(imageurl)
                    .thumbnail(0.5f)
                    .apply(options)
                    .into(imgProfile);
        Glide.with(this)
                //.load(urlNavHeaderBg)
                .load(cover)
                .apply(options2)
                .into(imgNavHeaderBg);
        navigationView.getMenu().getItem(3).setActionView(R.layout.menu_dot);
        navigationView.setItemIconTintList(null); }
    private void loadHomeFragment() {
        selectNavMenu();
        setToolbarTitle();
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            return;
        }
        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
                // update the favourite content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.container, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
        //Closing drawer on item click
        drawer.closeDrawers();
        // refresh toolbar menu
        invalidateOptionsMenu();
    }
    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home
                GridFragment homeFragment = new GridFragment();
                return homeFragment;
            case 1:
                // photos
                SearchFragment browseFragment = new SearchFragment();
                return browseFragment;
            case 2:
                // movies fragment
                FavoriteFragment favoriteFragment = new FavoriteFragment();
                return favoriteFragment;
            case 3:
                // notifications fragment
                NotifyFragment notificationsFragment = new NotifyFragment();
                return notificationsFragment;

            case 4:
                // settings fragment
                SettingsFragment settingsFragment = new SettingsFragment();
                return settingsFragment;
            default:
                return new HomeFragment();
        }
    }
    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }
    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }
    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the favourite content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_browse:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_browse;
                        break;
                    case R.id.nav_favourite:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_favourite;
                        break;
                    case R.id.nav_notifications:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_NOTIFICATIONS;
                        break;
                    case R.id.nav_settings:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_SETTINGS;
                        break;
                    case R.id.nav_about_us:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.Terms_Conditions:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, TermsConditions.class));
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_privacy_policy:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, PrivacyPolicyActivity.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        drawer.closeDrawers();
                        return true;
                    case R.id.log_out:
                        // launch new intent instead of loading fragment
                        log_out();
                        drawer.closeDrawers();
                        return true;
                    case R.id.gopremium:
                        Toast.makeText(mContext, "Go Premium", Toast.LENGTH_SHORT).show();
                        drawer.closeDrawers();
                        return true;
                    case R.id.rateus:
                        Rate_us();
                        drawer.closeDrawers();
                        return true;
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, mToolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //calling sync state is necessary or else your hamburger icon wont show up
    }
    private void log_out() {
        LoginManager.getInstance().logOut();
                editor = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.clear().commit();
                //Toast.makeText(mContext,"Clear cach",Toast.LENGTH_SHORT).show();


                Auth.GoogleSignInApi.signOut(mGoogleApiClient2).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                // ...
                                Toast.makeText(mContext,"Logged Out",Toast.LENGTH_SHORT).show();
                            }
                        });

                CookieSyncManager.createInstance(mContext);
                CookieManager cookieManager = CookieManager.getInstance();
                cookieManager.removeSessionCookie();
                Twitter.getSessionManager().clearActiveSession();
                Twitter.logOut();
        startActivity(new Intent(MainActivity.this, SplashScreen.class));
    }
    private void applyFontToMenuItem(MenuItem mi) {

        if(mi.getItemId() != R.id.gopremium){
            Typeface font = Typeface.createFromAsset(getAssets(), "fonts/airbnb.ttf");
            SpannableString mNewTitle = new SpannableString(mi.getTitle());
            mNewTitle.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            mi.setTitle(mNewTitle);
        }else{
            SpannableString s = new SpannableString(mi.getTitle());
            s.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.premium)), 0, s.length(), 0);
            mi.setTitle(s);
        }

    }
    private void Rate_us() {
        Uri uri = Uri.parse("market://details?id=" + mContext.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + mContext.getPackageName()))); }
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
