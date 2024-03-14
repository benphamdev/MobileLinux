package com.example.bai137v2;//package com.example.bai137sqliteimage;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import androidx.annotation.Nullable;
//
//import java.util.List;
//
//public class ItemContract {
////    private static final String SQL_CREATE_ENTRIES =
////            "CREATE TABLE " + ItemEntry.TABLE_NAME + " (" +
//////                    ItemEntry._ID + " INTEGER PRIMARY KEY," +
////                    ItemEntry.COLUMN_NAME + " TEXT," +
////                    ItemEntry.COLUMN_DESC + " TEXT," +
////                    ItemEntry.COLUMN_IMAGE + " BLOB)";
//
//    private static final String SQL_DELETE_ENTRIES =
//            "DROP TABLE IF EXISTS " + ItemEntry.TABLE_NAME;
//    private static final String SQL_CREATE_ENTRIES_IF =
//            "CREATE TABLE IF NOT EXISTS " +
//                    ItemEntry.TABLE_NAME + " (" +
//                    ItemEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                    ItemEntry.COLUMN_NAME + " VARCHAR(100)," + // 100 characters
//                    ItemEntry.COLUMN_DESC + " VARCHAR(250)," + // 250 characters
//                    ItemEntry.COLUMN_IMAGE + " BLOB)";
//    private static final String selectAll = "SELECT * FROM " + ItemEntry.TABLE_NAME;
//
//    private ItemContract() {
//    }
//
//    public static class ItemEntry {
//        public static final String TABLE_NAME = "Item";
//        public static final String _ID = "id";
//        public static final String COLUMN_NAME = "name";
//        public static final String COLUMN_DESC = "description";
//        public static final String COLUMN_IMAGE = "image";
//    }
//
//    public class ImageDbHelper extends SQLiteOpenHelper {
//        // If you change the database schema, you must increment the database version.
//        public static final int DATABASE_VERSION = 1;
//        public static final String DATABASE_NAME = "ImageManage.sqlite";
//        public ImageDbHelper database;
//
//        public ImageDbHelper(
//                @Nullable Context context,
//                @Nullable String name,
//                @Nullable SQLiteDatabase.CursorFactory factory, int version
//        ) {
//            super(context, name, factory, version);
//        }
//
//        @Override
//        public void onCreate(SQLiteDatabase sqLiteDatabase) {
////            sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
//            sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_IF);
//        }
//
//        @Override
//        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//
//        }
//
//        public void queryData(String sql) {
//            SQLiteDatabase database = getWritableDatabase();
//            database.execSQL(sql);
//        }
//
//        public Cursor getData(String sql) {
//            SQLiteDatabase database = getReadableDatabase();
//            return database.rawQuery(sql, null);
//        }
//
//        public void insertData(String name, String description, byte[] image) {
//            SQLiteDatabase database = getWritableDatabase();
//            String sql = "INSERT INTO Item VALUES(null, ?, ?, ?)";
//            database.execSQL(sql, new Object[]{name, description, image});
//        }
//
////        public void initDatabase() {
////            database = new ImageDbHelper(
////                    this,
////                    DATABASE_NAME,
////                    null,
////                    DATABASE_VERSION
////            );
////            database.queryData(SQL_CREATE_ENTRIES_IF);
////        }
//
//        public void showData(List itemIds, ItemAdapter itemAdapter) {
//            // Get data from SQLite
//            Cursor dataItem = database.getData(selectAll);
//            while (dataItem.moveToNext()) {
//                itemIds.add(new ItemObject(
//                        dataItem.getInt(0),
//                        dataItem.getString(1),
//                        dataItem.getString(2),
//                        dataItem.getBlob(3)
//                ));
//            }
//            itemAdapter.notifyDataSetChanged();
//        }
//    }
//}
