package dataprovider;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import org.w3c.dom.Text;

import java.net.URI;
import java.util.ArrayList;

import static dataprovider.YetiCoachContract.*;

public class YetiContentProvider extends ContentProvider {

    public YetiContentProvider() {
    }

    SQLiteOpenHelper dbHelper = null;
    private final ThreadLocal<Boolean> mIsInBatchMode = new ThreadLocal<Boolean>();

    @Override
    public boolean onCreate() {

        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        boolean useAuthorityUri = false;
        switch(URI_MATCHER.match(uri)){
            case USER_LOGINS_LIST:
                builder.setTables(DBSchema.TBL_USER_LOGINS);
                if(TextUtils.isEmpty(sortOrder)){
                    sortOrder = YetiCoachContract.UserLogins.SORT_ORDER_DEFAULT;
                }
                break;
            case USER_LOGINS_ID:
                builder.setTables(DBSchema.TBL_USER_LOGINS);
                builder.appendWhere(YetiCoachContract.UserLogins._ID + " = " +
                        uri.getLastPathSegment());
                break;
            case USER_LIST:
                builder.setTables(DBSchema.TBL_USERS);
                if(TextUtils.isEmpty(sortOrder)){
                    sortOrder = YetiCoachContract.Users.SORT_ORDER_DEFAULT;
                }
                break;
            case USER_ID:
                builder.setTables(DBSchema.TBL_USERS);
                builder.appendWhere(YetiCoachContract.Users._ID + " = " +
                        uri.getLastPathSegment());
                break;
            case LEAGUE_LIST:
                builder.setTables(DBSchema.TBL_LEAGUES);
                if(TextUtils.isEmpty(sortOrder))
                    sortOrder = YetiCoachContract.Leagues.SORT_ORDER_DEFAULT;
                break;
            case LEAGUE_ID:
                builder.setTables(DBSchema.TBL_LEAGUES);
                builder.appendWhere(YetiCoachContract.Leagues._ID + " = " +
                        uri.getLastPathSegment());
                break;
            case PLAYER_LIST:
                builder.setTables(DBSchema.TBL_PLAYERS);
                if(TextUtils.isEmpty(sortOrder))
                    sortOrder= YetiCoachContract.Players.SORT_ORDER_DEFAULT;
                break;
            case PLAYER_ID:
                builder.setTables(DBSchema.TBL_PLAYERS);
                builder.appendWhere(YetiCoachContract.Players._ID + " = " +
                        uri.getLastPathSegment());
            case PARENT_LIST:
                builder.setTables(DBSchema.TBL_PLAYERS);
                if(TextUtils.isEmpty(sortOrder))
                    sortOrder= YetiCoachContract.Players.SORT_ORDER_DEFAULT;
                break;
            case PARENT_ID:
                builder.setTables(DBSchema.TBL_PLAYERS);
                builder.appendWhere(YetiCoachContract.Players._ID + " = " +
                        uri.getLastPathSegment());
            case SPORT_LIST:
                builder.setTables(DBSchema.TBL_SPORTS);
                if(TextUtils.isEmpty(sortOrder))
                    sortOrder= YetiCoachContract.Sports.SORT_ORDER_DEFAULT;
                break;
            case SPORT_ID:
                builder.setTables(DBSchema.TBL_SPORTS);
                builder.appendWhere(YetiCoachContract.Sports._ID + " = " +
                        uri.getLastPathSegment());
            case ENROLLMENT_LIST:
                builder.setTables(DBSchema.TBL_ENROLLMENTS);
                if(TextUtils.isEmpty(sortOrder))
                    sortOrder= YetiCoachContract.Enrollments.SORT_ORDER_DEFAULT;
                break;
            case ENROLLMENT_ID:
                builder.setTables(DBSchema.TBL_ENROLLMENTS);
                builder.appendWhere(YetiCoachContract.Enrollments._ID + " = " +
                        uri.getLastPathSegment());
            case TRANSACTION_LIST:
                builder.setTables(DBSchema.TBL_TRANSACTIONS);
                if(TextUtils.isEmpty(sortOrder))
                    sortOrder= YetiCoachContract.Transactions.SORT_ORDER_DEFAULT;
                break;
            case TRANSACTION_ID:
                builder.setTables(DBSchema.TBL_TRANSACTIONS);
                builder.appendWhere(YetiCoachContract.Transactions._ID + " = " +
                        uri.getLastPathSegment());
            case TEAM_LIST:
                builder.setTables(DBSchema.TBL_TEAMS);
                if(TextUtils.isEmpty(sortOrder))
                    sortOrder= YetiCoachContract.Teams.SORT_ORDER_DEFAULT;
                break;
            case TEAM_ID:
                builder.setTables(DBSchema.TBL_TEAMS);
                builder.appendWhere(YetiCoachContract.Teams._ID + " = " +
                        uri.getLastPathSegment());
            default:
                throw new IllegalArgumentException("Unsupported Uri: " + uri);
        }
        Cursor cursor = builder.query(
                db,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
        if(useAuthorityUri){
            cursor.setNotificationUri(
                    getContext().getContentResolver(),
                    YetiCoachContract.CONTENT_URI
            );
        }else{
            cursor.setNotificationUri(
                    getContext().getContentResolver(), uri
            );
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {

        switch(URI_MATCHER.match(uri)){
            case USER_LOGINS_LIST:
                return YetiCoachContract.UserLogins.CONTENT_TYPE;
            case USER_LOGINS_ID:
                return UserLogins.CONTENT_ITEM_TYPE;
            case USER_LIST:
                return  Users.CONTENT_TYPE;
            case USER_ID:
                return  Users.CONTENT_ITEM_TYPE;
            case LEAGUE_LIST:
                return  Leagues.CONTENT_TYPE;
            case LEAGUE_ID:
                return  Leagues.CONTENT_ITEM_TYPE;
            case PLAYER_LIST:
                return Players.CONTENT_TYPE;
            case PLAYER_ID:
                return Players.CONTENT_ITEM_TYPE;
            case PARENT_LIST:
                return Parents.CONTENT_TYPE;
            case PARENT_ID:
                return Parents.CONTENT_ITEM_TYPE;
            case SPORT_LIST:
                return Sports.CONTENT_TYPE;
            case SPORT_ID:
                return Sports.CONTENT_ITEM_TYPE;
            case ENROLLMENT_LIST:
                return Enrollments.CONTENT_TYPE;
            case ENROLLMENT_ID:
                return Enrollments.CONTENT_ITEM_TYPE;
            case TRANSACTION_LIST:
                return Transactions.CONTENT_TYPE;
            case TRANSACTION_ID:
                return Transactions.CONTENT_ITEM_TYPE;
            case TEAM_LIST:
                return Teams.CONTENT_TYPE;
            case TEAM_ID:
                return Teams.CONTENT_ITEM_TYPE;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        if (URI_MATCHER.match(uri) != USER_LOGINS_LIST
                &&
                URI_MATCHER.match(uri) != USER_LOGINS_ID
                &&
                URI_MATCHER.match(uri) != USER_LIST
                &&
                URI_MATCHER.match(uri) != USER_ID
                &&
                URI_MATCHER.match(uri) != LEAGUE_LIST
                &&
                URI_MATCHER.match(uri) != LEAGUE_ID
                &&
                URI_MATCHER.match(uri) != PLAYER_LIST
                &&
                URI_MATCHER.match(uri) != PLAYER_ID
                &&
                URI_MATCHER.match(uri) != PARENT_LIST
                &&
                URI_MATCHER.match(uri) != PARENT_LIST
                &&
                URI_MATCHER.match(uri) != ENROLLMENT_LIST
                &&
                URI_MATCHER.match(uri) != ENROLLMENT_ID
                &&
                URI_MATCHER.match(uri) != SPORT_LIST
                &&
                URI_MATCHER.match(uri) != SPORT_ID
                &&
                URI_MATCHER.match(uri) != TRANSACTION_LIST
                &&
                URI_MATCHER.match(uri) != TRANSACTION_ID
                &&
                URI_MATCHER.match(uri) != TEAM_LIST
                &&
                URI_MATCHER.match(uri) != TEAM_LIST
                ) {
            throw new IllegalArgumentException("Unsupported URI for insertion: " + uri);
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (URI_MATCHER.match(uri) == USER_LOGINS_LIST) {
            long id = db.insert(
                    DBSchema.TBL_USER_LOGINS,
                    null,
                    values
            );
            return getUriForId(id, uri);
        } else if (URI_MATCHER.match(uri) == USER_LIST) {
            long id = db.insert(
                    DBSchema.TBL_USERS,
                    null,
                    values
            );
            return getUriForId(id, uri);
        } else if (URI_MATCHER.match(uri) == LEAGUE_LIST) {
            long id = db.insert(
                    DBSchema.TBL_LEAGUES,
                    null,
                    values
            );
            return getUriForId(id, uri);
        } else if (URI_MATCHER.match(uri) == PLAYER_LIST) {
            long id = db.insert(
                    DBSchema.TBL_PLAYERS,
                    null,
                    values
            );
            return getUriForId(id, uri);
        } else if (URI_MATCHER.match(uri) == PARENT_LIST) {
            long id = db.insert(
                    DBSchema.TBL_PARENTS,
                    null,
                    values
            );
            return getUriForId(id, uri);
        } else if (URI_MATCHER.match(uri) == ENROLLMENT_LIST) {
            long id = db.insert(
                    DBSchema.TBL_ENROLLMENTS,
                    null,
                    values
            );
            return getUriForId(id, uri);
        } else if (URI_MATCHER.match(uri) == SPORT_LIST) {
            long id = db.insert(
                    DBSchema.TBL_SPORTS,
                    null,
                    values
            );
            return getUriForId(id, uri);
        } else if (URI_MATCHER.match(uri) == TRANSACTION_LIST) {
            long id = db.insert(
                    DBSchema.TBL_TRANSACTIONS,
                    null,
                    values
            );
            return getUriForId(id, uri);
        } else if (URI_MATCHER.match(uri) == TEAM_LIST) {
            long id = db.insert(
                    DBSchema.TBL_TEAMS,
                    null,
                    values
            );
            return getUriForId(id, uri);
        }
        // TODO: Investigate insertWithOnConflict
        else {
//            long id = db.insertWithOnConflict(
//                    DBSchema.TBL_USER_LOGINS,
//                    null,
//                    values,
//                    SQLiteDatabase.CONFLICT_REPLACE);
//            return getUriForId(id, uri);
            return null;
        }

    }


    // TODO: make delete and update actions for each object
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    private Uri getUriForId(long id, Uri uri){
        if(id > 0){
            Uri itemUri = ContentUris.withAppendedId(uri, id);
            if(!mIsInBatchMode()){
                getContext().getContentResolver().notifyChange(itemUri, null);
            }
            return itemUri;
        }
        throw new SQLException("Problem while inserting into uri: " + uri);
    }

    private boolean mIsInBatchMode() {
        return mIsInBatchMode.get() != null && mIsInBatchMode.get();
    }

    @Override
    public ContentProviderResult[] applyBatch(
            ArrayList<ContentProviderOperation> operations)
            throws OperationApplicationException {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        mIsInBatchMode.set(true);
        // the next line works because SQLiteDatabase
        // uses a thread local SQLiteSession object for
        // all manipulations
        db.beginTransaction();
        try {
            final ContentProviderResult[] retResult = super.applyBatch(operations);
            db.setTransactionSuccessful();
            getContext().getContentResolver().notifyChange(YetiCoachContract.CONTENT_URI, null);
            return retResult;
        }
        finally {
            mIsInBatchMode.remove();
            db.endTransaction();
        }
    }


}
