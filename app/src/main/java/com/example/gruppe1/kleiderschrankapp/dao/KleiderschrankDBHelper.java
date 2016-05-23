package com.example.gruppe1.kleiderschrankapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

/**
 * Created by Furkan on 17.05.2016.
 */
import com.example.gruppe1.kleiderschrankapp.dao.DatabaseSchema.KleiderschrankEntry;
import com.example.gruppe1.kleiderschrankapp.dao.DatabaseSchema.KlamotteEntry;
import com.example.gruppe1.kleiderschrankapp.dao.DatabaseSchema.KategorieEntry;
import com.example.gruppe1.kleiderschrankapp.model.Kleiderschrank;


public class KleiderschrankDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "kleiderschrankapp.db";

    private static final String SQL_CREATE_TABLE_KLEIDERSCHRANK =
            "CREATE TABLE " + KleiderschrankEntry.TABLE_NAME + " (" +
                    KleiderschrankEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KleiderschrankEntry.COLUMN_NAME_BEZEICHNUNG + " TEXT " +
                    ");";

    public static final String SQL_CREATE_TABLE_KATEGORIE =
            "CREATE TABLE " + KategorieEntry.TABLE_NAME + " (" +
                    KategorieEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KategorieEntry.COLUMN_NAME_BEZEICHNUNG + " TEXT " +
                    ");";

    private static final String SQL_CREATE_TABLE_KLAMOTTE =
            "CREATE TABLE " + KlamotteEntry.TABLE_NAME + " (" +
                    KlamotteEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KlamotteEntry.COLUMN_NAME_KLEIDERSCHRANK_FK + " TEXT," +
                    KlamotteEntry.COLUMN_NAME_KATEGORIE_FK + " TEXT " +
                    ")" +
                    "FOREIGN KEY(" + KlamotteEntry.COLUMN_NAME_KLEIDERSCHRANK_FK + ") REFERENCES " + KleiderschrankEntry.TABLE_NAME + "(" + KleiderschrankEntry._ID + ")" +
                    "FOREIGN KEY(" + KlamotteEntry.COLUMN_NAME_KATEGORIE_FK + ") REFERENCES " + DatabaseSchema.KategorieEntry.TABLE_NAME + "(" + DatabaseSchema.KategorieEntry._ID + ")" +
                    ")";

    private static final String SQL_DROP_TABLE_KLEIDERSCHRANK = "DROP TABLE IF EXISTS " + KleiderschrankEntry.TABLE_NAME + ";";
    private static final String SQL_DROP_TABLE_KATEGORIE = "DROP TABLE IF EXISTS " + KategorieEntry.TABLE_NAME + ";";
    private static final String SQL_DROP_TABLE_KLAMOTTE = "DROP TABLE IF EXISTS " + KlamotteEntry.TABLE_NAME + ";";

    private static KleiderschrankDBHelper instance = null;
    private Context ctx;

    private KleiderschrankDBHelper(Context context) {
        super(context, KleiderschrankEntry.TABLE_NAME, null, DATABASE_VERSION);
        this.ctx = context;
    }

    public KleiderschrankDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, KleiderschrankEntry.TABLE_NAME, factory, DATABASE_VERSION);
        this.ctx = ctx;
    }

    public static KleiderschrankDBHelper getInstance(Context ctx) {
        if (instance == null) {
            return new KleiderschrankDBHelper(ctx.getApplicationContext());
        }
        return instance;
    }

    public Cursor findAllKleiderschrank() {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String sortOrder = KleiderschrankEntry.COLUMN_NAME_BEZEICHNUNG + " ASC";
        qb.setTables(KleiderschrankEntry.TABLE_NAME);
        return qb.query(getReadableDatabase(), null, null, null, null, null, sortOrder);
    }

    public void insertKleiderschrank(Kleiderschrank kleiderschrank) {
        ContentValues values = new ContentValues();

        values.put(KleiderschrankEntry.COLUMN_NAME_BEZEICHNUNG, kleiderschrank.getBezeichnung());

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(KleiderschrankEntry.TABLE_NAME, null, values);
        sqLiteDatabase.close();

    }

    public void deleteKleiderschrank(Kleiderschrank kleiderschrank) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        sqLiteDatabase.execSQL("DELETE FROM " + KleiderschrankEntry.TABLE_NAME + " WHERE " + KleiderschrankEntry._ID + " =\" " + kleiderschrank.getId() + ";");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = SQL_CREATE_TABLE_KLEIDERSCHRANK;
        sqLiteDatabase.execSQL(query);

//        query = SQL_CREATE_TABLE_KLAMOTTE;
//        sqLiteDatabase.execSQL(query);
//
//        query = SQL_CREATE_TABLE_KATEGORIE;
//        sqLiteDatabase.execSQL(query);

    }

    public String databaseToString() {
        String dbString = "";

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String query = "SELECT * FROM " + KleiderschrankEntry.TABLE_NAME + " WHERE 1";

        //Cursor point to a location of your results
        Cursor c = sqLiteDatabase.rawQuery(query, null);

        //Move to the first row in your results
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(KleiderschrankEntry.COLUMN_NAME_BEZEICHNUNG)) != null) {
                dbString += c.getString(c.getColumnIndex(KleiderschrankEntry.COLUMN_NAME_BEZEICHNUNG));
                dbString += "\n";
            }
            c.moveToNext();
        }

        sqLiteDatabase.close();

        return dbString;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DROP_TABLE_KLEIDERSCHRANK);
        sqLiteDatabase.execSQL(SQL_DROP_TABLE_KLAMOTTE);
        sqLiteDatabase.execSQL(SQL_DROP_TABLE_KATEGORIE);
        onCreate(sqLiteDatabase);
    }
}
