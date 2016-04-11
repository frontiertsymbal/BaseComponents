package mock.brains.basecomponents.core.briteDb;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.squareup.sqlbrite.BriteDatabase;

import mock.brains.basecomponents.core.model.Address;
import mock.brains.basecomponents.core.model.Company;
import mock.brains.basecomponents.core.model.Geo;
import mock.brains.basecomponents.core.model.User;
import timber.log.Timber;

public class DbManager {

    private final BriteDatabase briteDatabase;

    public DbManager(BriteDatabase briteDatabase) {
        this.briteDatabase = briteDatabase;
    }

    //****************************************************************************************************************************************************************************//

    /**
     * Add user to database
     *
     * @param user user
     */
    public void addUser(@NonNull User user) {
        BriteDatabase.Transaction transaction = briteDatabase.newTransaction();

        long id = briteDatabase.insert(User.TABLE, new User.Builder().build(user));
        user.set_id(id);
        user.getAddress().setUserId(id);
        user.getCompany().setUserId(id);
        addAddress(user.getAddress());
        addCompany(user.getCompany());

        transaction.markSuccessful();
        transaction.end();
    }

    public void addAddress(@NonNull Address address) {
        long id = briteDatabase.insert(Address.TABLE, new Address.Builder().build(address));
        address.set_id(id);
        address.getGeo().setAddressId(id);
        addGeo(address.getGeo());
    }

    public void addGeo(@NonNull Geo geo) {
        long id = briteDatabase.insert(Geo.TABLE, new Geo.Builder().build(geo));
        geo.set_id(id);
    }

    public void addCompany(@NonNull Company company) {
        long id = briteDatabase.insert(Company.TABLE, new Company.Builder().build(company));
        company.set_id(id);
    }

    /**
     * Get user from database
     *
     * @param value      argument for database query
     * @param columnName name of column in database
     * @return returns user from database or null if user is not exist
     */
    public User getUser(Object value, String columnName) {
        User user = null;
        Cursor cursor;
        try {
            cursor = briteDatabase.query(Db.SELECT_ALL_FROM
                            + User.TABLE + Db.WHERE
                            + columnName + Db.EQUALS_ARG,
                    String.valueOf(value));
            if (cursor.moveToFirst()) {
                user = User.MAPPER.call(cursor);
            }
            cursor.close();
        } catch (Exception e) {
            Timber.e(e, "getUser by %s = %s error", columnName, String.valueOf(value));
        }
        if (user != null) {
            user.setAddress(getAddress(user.get_id(), Address.COL_USER_ID));
            user.setCompany(getCompany(user.get_id(), Company.COL_USER_ID));
        }
        return user;
    }

    public Address getAddress(Object value, String columnName) {
        Address address = null;
        Cursor cursor;
        try {
            cursor = briteDatabase.query(Db.SELECT_ALL_FROM
                    + Address.TABLE + Db.WHERE + columnName
                    + Db.EQUALS_ARG, String.valueOf(value));
            if (cursor.moveToFirst()) {
                address = Address.MAPPER.call(cursor);
            }
            cursor.close();
        } catch (Exception e) {
            Timber.e(e, "getAddress by %s = %s error", columnName, String.valueOf(value));
        }
        if (address != null) {
            address.setGeo(getGeo(address.get_id(), Geo.COL_ADDRESS_ID));
        }
        return address;
    }

    public Geo getGeo(Object value, String columnName) {
        Geo geo = null;
        Cursor cursor;
        try {
            cursor = briteDatabase.query(Db.SELECT_ALL_FROM
                    + Geo.TABLE + Db.WHERE + columnName
                    + Db.EQUALS_ARG, String.valueOf(value));
            if (cursor.moveToFirst()) {
                geo = Geo.MAPPER.call(cursor);
            }
            cursor.close();
        } catch (Exception e) {
            Timber.e(e, "getGeo by %s = %s error", columnName, String.valueOf(value));
        }
        return geo;
    }

    public Company getCompany(Object value, String columnName) {
        Company company = null;
        Cursor cursor;
        try {
            cursor = briteDatabase.query(Db.SELECT_ALL_FROM +
                    Company.TABLE + Db.WHERE + columnName
                    + Db.EQUALS_ARG, String.valueOf(value));
            if (cursor.moveToFirst()) {
                company = Company.MAPPER.call(cursor);
            }
            cursor.close();
        } catch (Exception e) {
            Timber.e(e, "getCompany by %s = %s error", columnName, String.valueOf(value));
        }
        return company;
    }

    public int getCountOfUsers() {
        int count = 0;
        Cursor cursor;
        try {
            cursor = briteDatabase.query(Db.SELECT_COUNT_ALL_AS_COUNT_ROWS_FROM +
                    User.TABLE);
            if (cursor.moveToFirst()) {
                count = Db.getInt(cursor, Db.COUNT_ROWS);
            }
            cursor.close();
        } catch (Exception e) {
            Timber.e(e, "getCountOfUsersError");
        }
        return count;
    }

    /**
     * Basic isExist method for check object existing in database
     *
     * @param value      argument for database query
     * @param tableName  table name
     * @param columnName name of column in table
     * @return boolean result of querying
     */
    public boolean isExist(Object value, String tableName, String columnName) {
        int count = 0;
        Cursor cursor;
        try {
            cursor = briteDatabase.query(Db.SELECT_COUNT_ALL_AS_COUNT_ROWS_FROM
                            + tableName + Db.WHERE
                            + columnName + Db.EQUALS_ARG,
                    String.valueOf(value));
            if (cursor.moveToFirst()) {
                count = Db.getInt(cursor, Db.COUNT_ROWS);
            }
            cursor.close();
        } catch (Exception e) {
            Timber.e(e, "isExist in table %s by %s = %s error",
                    tableName, columnName, String.valueOf(value));
        }
        return count != 0;
    }

    public boolean isUserExist(int serverUserId) {
        return isExist(serverUserId, User.TABLE, User.COL_USER_ID);
    }

}
