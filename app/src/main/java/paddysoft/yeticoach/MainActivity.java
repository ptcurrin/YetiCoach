package paddysoft.yeticoach;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.LoaderManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ResourceCursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import dataprovider.League;
import dataprovider.User;
import dataprovider.YContentObserver;
import dataprovider.YLeagueLoader;
import dataprovider.YMainLoader;
import dataprovider.YetiCoachContract;

import static android.app.DialogFragment.*;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor>,
        AdapterView.OnItemSelectedListener
{


    private TextView tvUserId;
    private TextView tvFirstName;
    private TextView tvLastName;
    private TextView tvWelcomeEmail;
    private TextView tvPhone;
    private TextView tvStreet;
    private TextView tvCity;
    private TextView tvState;
    private TextView tvZip;
    private TextView testing;

    private String cEmail;

    private User user = null;
    private League league = null;
    private static final int USER_LOADER_ID = 1;
    private static final int LEAGUE_LOADER_ID = 2;
    private LoaderManager.LoaderCallbacks<Cursor> yUserCallbacks;
    private LoaderManager.LoaderCallbacks<Cursor> yLeaguesCallbacks;
    private LoaderManager.LoaderCallbacks<Cursor> yPlayersCallbacks;
    private YContentObserver yUserObserver;
    private YContentObserver yLeaguesObserver;
    private Spinner spinnerLeagues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yUserCallbacks = this;
        yLeaguesCallbacks = this;

        testing = (TextView)findViewById(R.id.MainTextViewTest);

        tvUserId = (TextView) findViewById(R.id.tvWelcomeUserId);
        tvFirstName = (TextView) findViewById(R.id.userFirstName);
        tvLastName = (TextView) findViewById(R.id.userLastName);
        tvWelcomeEmail = (TextView) findViewById(R.id.tvWelcomeEmail);
        tvPhone = (TextView) findViewById(R.id.tvWelcomePhone);
        tvStreet = (TextView) findViewById(R.id.userStreet);
        tvCity = (TextView) findViewById(R.id.userCity);
        tvState = (TextView) findViewById(R.id.userState);
        tvZip = (TextView) findViewById(R.id.userZip);

        Intent incomingIntent = getIntent();
        Bundle bundle = incomingIntent.getBundleExtra("LoginDelivery");
        cEmail = bundle.getString("Email");


        yUserObserver = new YContentObserver(new Handler());
        yLeaguesObserver = new YContentObserver(new Handler());
        android.app.LoaderManager lm = getLoaderManager();

//        getContentResolver().registerContentObserver(YetiCoachContract.Users.CONTENT_URI, true, yUserObserver);
//        getContentResolver().registerContentObserver(YetiCoachContract.Leagues.CONTENT_URI, true, yLeaguesObserver);

        lm.initLoader(USER_LOADER_ID, null, yUserCallbacks);
        lm.initLoader(LEAGUE_LOADER_ID, null, yLeaguesCallbacks);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        getContentResolver().registerContentObserver(YetiCoachContract.Users.CONTENT_URI, true, yUserObserver);
//        getContentResolver().registerContentObserver(YetiCoachContract.Leagues.CONTENT_URI, true, yLeaguesObserver);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        getContentResolver().unregisterContentObserver(yUserObserver);
//        getContentResolver().unregisterContentObserver(yLeaguesObserver);
    }

    public void OnMainButtonEnterLeaguePressed(View v){
        testing.setText("Enter Pressed");
        showEnterDialog();
    }

    public void OnMainButtonAddLeaguePressed(View v){
        testing.setText("Add Pressed");
    }

    public void OnMainButtonRemoveLeaguePressed(View v){
        testing.setText("Remove Pressed");
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        if (id == USER_LOADER_ID) {
            return new YMainLoader(getApplicationContext(), cEmail);
        }
        if (id == LEAGUE_LOADER_ID) {
            return new YLeagueLoader(getApplicationContext(), cEmail);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        switch (loader.getId()) {
            case USER_LOADER_ID:

                if (cursor != null && cursor.getCount() == 1) {
                    cursor.moveToFirst();
                    user = new User(cursor.getInt(cursor.getColumnIndex("_id")),
                            cursor.getString(cursor.getColumnIndex("userid")),
                            cursor.getString(cursor.getColumnIndex("firstname")),
                            cursor.getString(cursor.getColumnIndex("lastname")),
                            cursor.getString(cursor.getColumnIndex("email")),
                            cursor.getString(cursor.getColumnIndex("phone")),
                            cursor.getString(cursor.getColumnIndex("street")),
                            cursor.getString(cursor.getColumnIndex("city")),
                            cursor.getString(cursor.getColumnIndex("state")),
                            cursor.getString(cursor.getColumnIndex("zip"))
                    );


                    tvFirstName.setText(user.getFirstName());
                    tvLastName.setText(user.getLastName());
                    tvStreet.setText(user.getStreet());
                    tvCity.setText(user.getCity());
                    tvState.setText(user.getState());
                    tvZip.setText(user.getZip());
                    tvWelcomeEmail.setText(user.getEmail());
                    tvPhone.setText(user.getPhone());
                    tvUserId.setText(user.getUserId());
                }
                break;
            case LEAGUE_LOADER_ID:

                if (cursor != null && cursor.getCount() > 0) {

                    cursor.moveToFirst();

                    spinnerLeagues = (Spinner)findViewById(R.id.spinnerLeagues);
                    //ArrayAdapter<CharSequence> shipsArrayAdapter = ArrayAdapter.createFromResource(
                    ResourceCursorAdapter cursorAdapter = new SimpleCursorAdapter(
                            this,
                            android.R.layout.simple_dropdown_item_1line,
                            cursor,
                            new String[]{"name"},
                            new int[]{ android.R.id.text1 },
                            0
                    );
                    spinnerLeagues.setAdapter(cursorAdapter);

//                    shipsArrayAdapter.setDropDownViewResource(
//                            android.R.layout.simple_spinner_dropdown_item);
//                    spinnerLeagues.setAdapter(shipsArrayAdapter);
                }
                break;

        }
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        switch (loader.getId()) {
            case USER_LOADER_ID:
                tvFirstName.setText("");
                tvLastName.setText("");
                tvStreet.setText("");
                tvCity.setText("");
                tvState.setText("");
                tvZip.setText("");
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public static class ConfirmDialogFragment extends DialogFragment {

        public static ConfirmDialogFragment getConfirmDialogFragment(int title){
            ConfirmDialogFragment cdf = new ConfirmDialogFragment();
            Bundle args = new Bundle();
            args.putInt("title", title);
            cdf.setArguments(args);
            return cdf;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){

            int title = getArguments().getInt("title");

            return new AlertDialog.Builder(getActivity())
                    .setTitle(title)
                    .setPositiveButton(R.string.alert_dialog_ok,
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    ((MainActivity)getActivity()).doPositiveClickEnter();
                                }
                            })

                    .setNegativeButton(R.string.alert_dialog_cancel,
                            new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int whichButton){
                                    ((MainActivity) getActivity()).doNegativeClickEnter();
                                }
                            })
                    .create();
        }
    }

    void showEnterDialog() {

        DialogFragment newFragment = ConfirmDialogFragment.getConfirmDialogFragment(R.string.alert_dialog_confirm_enter_league);
        newFragment.show(getFragmentManager(), "dialog");

    }

    public void doPositiveClickEnter() {

        Intent intent = new Intent(getApplicationContext(), LeagueAdministrationActivity.class);

        try{
            Cursor cLeague =  (Cursor)spinnerLeagues.getItemAtPosition(spinnerLeagues.getSelectedItemPosition());
            League league = League.cursorToLeague(cLeague, spinnerLeagues.getSelectedItemPosition());
            int lid = league.get_id();
            intent.putExtra("league", lid);
        }catch (Exception e){
            Log.v("......................", e.getMessage());
        }

        startActivity(intent);
    }

    public void doNegativeClickEnter() {
        Log.v(".......................", "Canceling(Negative)");
    }
}


