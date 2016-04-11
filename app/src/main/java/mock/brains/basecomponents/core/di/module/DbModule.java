package mock.brains.basecomponents.core.di.module;

import android.content.Context;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import mock.brains.basecomponents.BuildConfig;
import mock.brains.basecomponents.core.briteDb.DbManager;
import mock.brains.basecomponents.core.briteDb.DbOpenHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.schedulers.Schedulers;
import timber.log.Timber;

@Module
public class DbModule {

    private String dbName;
    private int dbVer;

    public DbModule(String dbName, int dbVer) {
        this.dbName = dbName;
        this.dbVer = dbVer;
    }

    @Provides
    @Singleton
    BriteDatabase provideDataBase(SqlBrite sqlBrite, DbOpenHelper helper) {
        BriteDatabase briteDatabase = sqlBrite.wrapDatabaseHelper(helper, Schedulers.io());
        if (BuildConfig.DEBUG) {
            briteDatabase.setLoggingEnabled(true);
        }
        return briteDatabase;
    }

    @Provides
    @Singleton
    SqlBrite provideSqlBrite() {
        return SqlBrite.create(message -> Timber.d(message));
    }

    @Provides
    @Singleton
    DbOpenHelper provideOpenHelper(Context context) {
        return new DbOpenHelper(context, dbName, dbVer);
    }

    @Provides
    @Singleton
    DbManager provideDbManager(BriteDatabase briteDatabase) {
        return new DbManager(briteDatabase);
    }
}
