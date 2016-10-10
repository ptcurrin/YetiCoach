package paddysoft.yeticoach;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.Date;

import dataprovider.DBHelper;
import dataprovider.League;
import dataprovider.YLeagueAdminLoader;

public class LeagueAdministrationActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LEAGUE_ADMIN_LOADER_ID = 10;
    private League cLeague;
    private android.app.LoaderManager lm;
    private LoaderManager.LoaderCallbacks<Cursor> yLeagueAdminCallbacks;
    private int y_id = -1;

    //UI Controls
    private TextView leagueName;
    private TextView leagueSport;
    private TextView leagueAdmin;
    private TextView leagueMinAge;
    private TextView leagueMaxAge;
    private TextView leagueStart;
    private TextView leagueEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_administration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Intent intent = getIntent();
        y_id = intent.getIntExtra("league", 0);

        yLeagueAdminCallbacks = this;
        lm = getLoaderManager();
        lm.initLoader(LEAGUE_ADMIN_LOADER_ID, null, yLeagueAdminCallbacks);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (LEAGUE_ADMIN_LOADER_ID == id) {
            return new YLeagueAdminLoader(this.getApplicationContext(), y_id);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor c) {

        c.moveToFirst();


        int id = c.getInt(c.getColumnIndex(DBHelper.LEAGUES_COL_ID));
        String leagueid = c.getString(c.getColumnIndex(DBHelper.LEAGUES_COL_LEAGUE_ID));
        String name = c.getString(c.getColumnIndex(DBHelper.LEAGUES_COL_NAME));
        String adminId = c.getString(c.getColumnIndex(DBHelper.LEAGUES_COL_USER_ID));
        String sportId = c.getString(c.getColumnIndex(DBHelper.LEAGUES_COL_SPORT_ID));
        int min = c.getInt(c.getColumnIndex(DBHelper.LEAGUES_COL_MIN_AGE));
        int max = c.getInt(c.getColumnIndex(DBHelper.LEAGUES_COL_MAX_AGE));
        Date sd = new Date();
        sd.setTime(c.getLong(c.getColumnIndex(DBHelper.LEAGUES_COL_START_DATE)));

        Date ed = new Date();
        ed.setTime(c.getLong(c.getColumnIndex(DBHelper.LEAGUES_COL_END_DATE)));


        leagueName = (TextView)findViewById(R.id.LeagueAdminLeagueName);
        leagueSport = (TextView)findViewById(R.id.LeagueAdminSport);
        leagueAdmin = (TextView)findViewById(R.id.LeagueAdminUser);
        leagueMinAge = (TextView)findViewById(R.id.LeagueAdminMinAge);
        leagueMaxAge = (TextView)findViewById(R.id.LeagueAdminMaxAge);
        leagueStart = (TextView)findViewById(R.id.LeagueAdminStartDate);
        leagueEnd = (TextView)findViewById(R.id.LeagueAdminEndDate);

        leagueName.setText(name);
        leagueSport.setText(sportId);
        leagueAdmin.setText(adminId);
        leagueMinAge.setText(min + "");
        leagueMaxAge.setText(max + "");
        leagueStart.setText(sd.toString());
        leagueEnd.setText(ed.toString());

        getLoaderManager().destroyLoader(LEAGUE_ADMIN_LOADER_ID);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        leagueName.setText("Reset");

    }
}

