package mock.brains.basecomponents.core.briteDb;

import com.squareup.sqlbrite.BriteDatabase;

import timber.log.Timber;

public class DbManager {

    private final BriteDatabase briteDatabase;

    public DbManager(BriteDatabase briteDatabase) {
        this.briteDatabase = briteDatabase;
    }

    //****************************************************************************************************************************************************************************//

    // TODO AlexTsymbal: implement methods for work with DB

    //Sample method
    public void helloDb() {
        Timber.e("BriteDB enabled = %1$s", String.valueOf(briteDatabase != null));
    }

}
