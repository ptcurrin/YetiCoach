package paddysoft.yeticoach;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import dataprovider.League;
import dataprovider.YLeagueLoader;

public class LeagueAdministrationActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LEAGUE_LOADER_ID = 0;
    TextView testing;
    private int lid = -1;
    private League league;

    private LoaderManager.LoaderCallbacks<Cursor> yLeaguesCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_administration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        lid = intent.getIntExtra("league", 0);

        android.app.LoaderManager lm = getLoaderManager();
        yLeaguesCallbacks = this;
        lm.initLoader(LEAGUE_LOADER_ID, null, yLeaguesCallbacks);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        if (id == LEAGUE_LOADER_ID) {
            return new YLeagueLoader(getApplicationContext(), lid);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch(loader.getId()){
            case LEAGUE_LOADER_ID:

                league = League.cursorToLeague(data);


                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
