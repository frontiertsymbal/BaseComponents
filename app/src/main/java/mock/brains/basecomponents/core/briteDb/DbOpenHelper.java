package mock.brains.basecomponents.core.briteDb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import javax.inject.Singleton;

import mock.brains.basecomponents.core.model.Address;
import mock.brains.basecomponents.core.model.Company;
import mock.brains.basecomponents.core.model.Geo;
import mock.brains.basecomponents.core.model.User;
import timber.log.Timber;

@Singleton
public class DbOpenHelper extends SQLiteOpenHelper {

    public DbOpenHelper(Context context, String dbName, int dbVer) {
        super(context, dbName, null, dbVer);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db, User.CREATE_TABLE);
        createTable(db, Company.CREATE_TABLE);
        createTable(db, Address.CREATE_TABLE);
        createTable(db, Geo.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        destroyDB(db);
        onCreate(db);
    }

    public void destroyDB(SQLiteDatabase db) {
        try {
            db.beginTransaction();
            db.execSQL(Db.DROP_TABLE_IF_EXIST + User.TABLE);
            db.execSQL(Db.DROP_TABLE_IF_EXIST + Company.TABLE);
            db.execSQL(Db.DROP_TABLE_IF_EXIST + Address.TABLE);
            db.execSQL(Db.DROP_TABLE_IF_EXIST + Geo.TABLE);
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
