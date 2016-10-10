package dataprovider;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.os.Handler;

/**
 * Created by Patrick on 10/10/2016.
 */

public class YLeagueAdminLoader extends AsyncTaskLoader<Cursor> {

    private int y_id;
    private Cursor yCursor;
    private YContentObserver yContentObserver;

    public YLeagueAdminLoader(Context context, int id){
        super(context);
        yContentObserver = new YContentObserver(new Handler());
        y_id = id;
    }


    @Override
    public Cursor loadInBackground() {

        yCursor = getContext().getContentResolver().query(YetiCoachContract.Leagues.CONTENT_URI,
                YetiCoachContract.Leagues.PROJECTION_ALL,
                "_id=?",
                new String[] { (y_id + "") },
                null);

        return yCursor;
    }

    @Override
    public void deliverResult(Cursor cursor){
        if(isReset()){
            releaseResources(cursor);
            return;
        }

        Cursor oldCursor = yCursor;
        yCursor = cursor;

        if(isStarted()){
            super.deliverResult(cursor);
        }

        if(oldCursor != null && oldCursor != cursor){
            releaseResources(oldCursor);
        }
    }

    public void releaseResources(Cursor cursor){
        cursor.close();
    }

    protected void onStartLoading(){
        if(yCursor != null){
            deliverResult(yCursor);
        }

        // TODO: Register a ContentObserver
        if(yContentObserver == null){
            yContentObserver = new YContentObserver(new Handler());
        }

        if(takeContentChanged() || yCursor == null){
            // When the observer detects a change, it should call onContentChanged()
            // on the Loader, which will cause the next call to takeContentChanged()
            // to return true. If this is ever the case (or if the current data is
            // null), we force a new load.
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading(){
        // The Loader is in a stopped state, so we should attempt to cancel the
        // current load (if there is one).
        cancelLoad();

        // Note that we leave the observer as is. Loaders in a stopped state
        // should still monitor the data source for changes so that the Loader
        // will know to force a new load if it is ever started again.
    }

    protected void onReset(){
        onStopLoading();

        if(yCursor != null){
            releaseResources(yCursor);
            yCursor = null;
        }

//        if(yObserver != null){
//            yObserver = null;
//        }
    }

    public void onCancelled(Cursor cursor){
        super.onCanceled(cursor);

        releaseResources(cursor);
    }
}
