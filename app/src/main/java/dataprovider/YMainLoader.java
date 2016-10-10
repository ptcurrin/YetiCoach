package dataprovider;

import android.content.Context;
import android.content.Loader;
import android.content.AsyncTaskLoader;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;

import utils.PhoneUtils;


/**
 * Created by Patrick on 9/26/2016.
 */

public class YMainLoader extends AsyncTaskLoader<Cursor> {

    private String email = "";
    private Cursor yCursor;
    private YContentObserver yContentObserver;

    /**
     * Stores away the application context associated with context.
     * Since Loaders can be used across multiple activities it's dangerous to
     * store the context directly; always use {@link #getContext()} to retrieve
     * the Loader's Context, don't use the constructor argument directly.
     * The Context returned by {@link #getContext} is safe to use across
     * Activity instances.
     *
     * @param context used to retrieve the application context.
     * @param cEmail
     */
    public YMainLoader(Context context, String cEmail) {
        super(context);
        this.email = cEmail;
        yContentObserver = new YContentObserver(new Handler());
    }

    @Override
    public Cursor loadInBackground() {

        yCursor = getContext().getContentResolver().query(YetiCoachContract.Users.CONTENT_URI,
            YetiCoachContract.Users.PROJECTION_ALL,
            "email=?",
            new String[] { email },
            null);

        if(yCursor != null && yCursor.getCount() > 0){
            return yCursor;
        }

        return null;
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
