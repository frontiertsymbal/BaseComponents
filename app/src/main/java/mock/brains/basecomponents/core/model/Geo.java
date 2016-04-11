package mock.brains.basecomponents.core.model;

import android.content.ContentValues;
import android.database.Cursor;

import java.io.Serializable;

import mock.brains.basecomponents.core.briteDb.Db;
import rx.functions.Func1;

public class Geo implements Serializable {

    public static final String TABLE = "geo";

    public static final String COL_ADDRESS_ID = "address_id";
    public static final String COL_LAT = "lat";
    public static final String COL_LNG = "lng";

    public static final String CREATE_TABLE = ""
            + Db.CREATE_TABLE + TABLE + Db.OPEN_BRACKET
            + Db.COL_ID + Db.INT_NOT_NULL_PK_AUTOINCREMENT + Db.COMMA
            + COL_ADDRESS_ID + Db.INTEGER_NOT_NULL + Db.COMMA
            + COL_LAT + Db.REAL_NOT_NULL + Db.COMMA
            + COL_LNG + Db.REAL_NOT_NULL
            + Db.CLOSE_BRACKET;

    private long _id;
    private long addressId;
    private double lat;
    private double lng;

    public static final Func1<Cursor, Geo> MAPPER = cursor -> {
        Geo geo = new Geo();
        geo.set_id(Db.getLong(cursor, Db.COL_ID));
        geo.setAddressId(Db.getInt(cursor, COL_ADDRESS_ID));
        geo.setLat(Db.getDouble(cursor, COL_LAT));
        geo.setLng(Db.getDouble(cursor, COL_LNG));
        return geo;
    };

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Geo{" +
                "_id=" + _id +
                ", addressId=" + addressId +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }

    public static final class Builder {
        private final ContentValues values = new ContentValues();

        public Builder id(long _id) {
            values.put(Db.COL_ID, _id);
            return this;
        }

        public Builder addressId(long addressId) {
            values.put(COL_ADDRESS_ID, addressId);
            return this;
        }

        public Builder lat(double lat) {
            values.put(COL_LAT, lat);
            return this;
        }

        public Builder lng(double lng) {
            values.put(COL_LNG, lng);
            return this;
        }

        public ContentValues build() {
            return values;
        }

        public ContentValues build(Geo geo) {
            return new Geo.Builder()
                    .addressId(geo.getAddressId())
                    .lat(geo.getLat())
                    .lng(geo.getLng())
                    .build();
        }
    }
}
