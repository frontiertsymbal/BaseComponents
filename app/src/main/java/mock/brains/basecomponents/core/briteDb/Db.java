package mock.brains.basecomponents.core.briteDb;

import android.database.Cursor;

public class Db {

    /**
     * Base columns
     */
    public static final String COL_ID = "_id";
    public static final String COUNT_ROWS = "COUNT_ROWS";

    /**
     * Query parts
     */
    public static final String CREATE_TABLE = "CREATE TABLE ";
    public static final String DROP_TABLE_IF_EXIST = "DROP TABLE IF EXISTS ";
    public static final String SELECT = "SELECT ";
    public static final String FROM = " FROM ";
    public static final String WHERE = " WHERE ";
    public static final String SELECT_ALL_FROM = SELECT + " * " + FROM;
    public static final String SELECT_COUNT_ALL_AS_COUNT_ROWS_FROM = SELECT + "COUNT(*) AS " + COUNT_ROWS + FROM;
    public static final String DELETE_FROM = "DELETE" + FROM;

    public static final String EQUALS = " = ";
    public static final String EQUALS_ARG = " = ?";
    public static final String NOT_EQUALS = " <> ";
    public static final String NOT_EQUALS_ARG = " <> ?";

    public static final String LIMIT = " LIMIT ";
    public static final String DISTINCT = " DISTINCT ";
    public static final String ORDER_BY = " ORDER BY ";
    public static final String ASC = " ASC ";
    public static final String DESC = " DESC ";
    public static final String AND = " AND ";
    public static final String COMMA = ", ";
    public static final String OPEN_BRACKET = "(";
    public static final String CLOSE_BRACKET = ")";

    /**
     * Data types
     */
    public static final String INTEGER = " INTEGER";
    public static final String TEXT = " TEXT";
    public static final String REAL = " REAL";
    public static final String NUMERIC = " NUMERIC";
    public static final String NONE = " NONE";

    public static final String NOT_NULL = " NOT NULL";

    public static final String INTEGER_NOT_NULL = INTEGER + NOT_NULL;
    public static final String TEXT_NOT_NULL = TEXT + NOT_NULL;
    public static final String REAL_NOT_NULL = REAL + NOT_NULL;
    public static final String NUMERIC_NOT_NULL = NUMERIC + NOT_NULL;
    public static final String NONE_NOT_NULL = NONE + NOT_NULL;

    public static final String INT_NOT_NULL_PK_AUTOINCREMENT = INTEGER_NOT_NULL + " PRIMARY KEY AUTOINCREMENT";

    /**
     * Db mapper utils
     */
    public static final int BOOLEAN_FALSE = 0;
    public static final int BOOLEAN_TRUE = 1;

    public static String getString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndexOrThrow(columnName));
    }

    public static boolean getBoolean(Cursor cursor, String columnName) {
        return getInt(cursor, columnName) == BOOLEAN_TRUE;
    }

    public static long getLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndexOrThrow(columnName));
    }

    public static int getInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndexOrThrow(columnName));
    }

    public static double getDouble(Cursor cursor, String columnName) {
        return cursor.getDouble(cursor.getColumnIndexOrThrow(columnName));
    }
}
