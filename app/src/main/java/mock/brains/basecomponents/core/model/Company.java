package mock.brains.basecomponents.core.model;

import android.content.ContentValues;
import android.database.Cursor;

import java.io.Serializable;

import mock.brains.basecomponents.core.briteDb.Db;
import rx.functions.Func1;

public class Company implements Serializable {

    public static final String TABLE = "company";

    public static final String COL_USER_ID = "user_id";
    public static final String COL_NAME = "company_name";
    public static final String COL_CATCH_PHRASE = "catch_phrase";
    public static final String COL_BS = "bs";

    public static final String CREATE_TABLE = ""
            + Db.CREATE_TABLE + TABLE + Db.OPEN_BRACKET
            + Db.COL_ID + Db.INT_NOT_NULL_PK_AUTOINCREMENT + ", "
            + COL_USER_ID + Db.INTEGER_NOT_NULL + Db.COMMA
            + COL_NAME + Db.TEXT_NOT_NULL + Db.COMMA
            + COL_CATCH_PHRASE + Db.TEXT_NOT_NULL + Db.COMMA
            + COL_BS + Db.TEXT_NOT_NULL
            + Db.CLOSE_BRACKET;

    private long _id;
    private long userId;

    private String name;
    private String catchPhrase;
    private String bs;

    public static final Func1<Cursor, Company> MAPPER = cursor -> {
        Company company = new Company();
        company.set_id(Db.getLong(cursor, Db.COL_ID));
        company.setUserId(Db.getInt(cursor, COL_USER_ID));
        company.setName(Db.getString(cursor, COL_NAME));
        company.setCatchPhrase(Db.getString(cursor, COL_CATCH_PHRASE));
        company.setBs(Db.getString(cursor, COL_BS));
        return company;
    };

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    @Override
    public String toString() {
        return "Company{" +
                "_id=" + _id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", catchPhrase='" + catchPhrase + '\'' +
                ", bs='" + bs + '\'' +
                '}';
    }

    public static final class Builder {
        private final ContentValues values = new ContentValues();

        public Builder id(long _id) {
            values.put(Db.COL_ID, _id);
            return this;
        }

        public Builder userId(long userId) {
            values.put(COL_USER_ID, userId);
            return this;
        }

        public Builder name(String name) {
            values.put(COL_NAME, name);
            return this;
        }

        public Builder catchPhrase(String catchPhrase) {
            values.put(COL_CATCH_PHRASE, catchPhrase);
            return this;
        }

        public Builder bs(String bs) {
            values.put(COL_BS, bs);
            return this;
        }

        public ContentValues build() {
            return values;
        }

        public ContentValues build(Company company) {
            return new Company.Builder()
                    .userId(company.getUserId())
                    .name(company.getName())
                    .catchPhrase(company.getCatchPhrase())
                    .bs(company.getBs())
                    .build();
        }
    }
}
