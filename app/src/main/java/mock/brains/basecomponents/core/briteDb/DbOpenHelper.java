package mock.brains.basecomponents.core.briteDb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import javax.inject.Singleton;

import timber.log.Timber;

@Singleton
public class DbOpenHelper extends SQLiteOpenHelper {

    public DbOpenHelper(Context context, String dbName, int dbVer) {
        super(context, dbName, null, dbVer);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO AlexTsymbal: add CREATE_TABLE sql strings
        //createTable(db, SomeEntity.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        destroyDB(db);
        onCreate(db);
    }

    public void destroyDB(SQLiteDatabase db) {
        try {
            db.beginTransaction();
            // TODO AlexTsymbal: add TABLEs for destroy DB
            //db.execSQL(Db.DROP_TABLE_IF_EXIST + SomeEntity.TABLE);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Timber.e(e, "destroyDB error");
        }
        db.endTransaction();
    }

    private void createTable(SQLiteDatabase db, String sql) {
        try {
            db.execSQL(sql);
        } catch (Exception e) {
            Timber.e(e, "Error create table [ %s ]", sql);
        }
    }
}
