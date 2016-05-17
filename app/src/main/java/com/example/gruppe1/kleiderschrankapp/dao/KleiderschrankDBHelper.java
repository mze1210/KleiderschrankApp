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

    public static final int DATABASE_VERSION = 6;
    public static final String DATABASE_NAME = "kleiderschrankapp.db";

    private static final String SQL_CREATE_TABLE_KLEIDERSCHRANK =
            "CREATE TABLE " + KleiderschrankEntry.TABLE_NAME + " (" +
                    KleiderschrankEntry._ID + " INTEGER PRIMARY KEY," +
                    KleiderschrankEntry.COLUMN_NAME_BEZEICHNUNG + " TEXT," +
                    ");";

    public static final String SQL_CREATE_TABLE_KATEGORIE =
            "CREATE TABLE " + KleiderschrankEntry.TABLE_NAME + " (" +
                    DatabaseSchema.KategorieEntry._ID + " INTEGER PRIMARY KEY," +
                    KleiderschrankEntry.COLUMN_NAME_BEZEICHNUNG + " TEXT," +
                    ");";

    private static final String SQL_CREATE_TABLE_KLAMOTTET =
            "CREATE TABLE " + KlamotteEntry.TABLE_NAME + " (" +
                    KlamotteEntry._ID + " INTEGER PRIMARY KEY," +
                    KlamotteEntry.COLUMN_NAME_KLEIDERSCHRANK_FK + " TEXT," +
                    KlamotteEntry.COLUMN_NAME_KATEGORIE_FK + " TEXT," +
                    "FOREIGN KEY(" + KlamotteEntry.COLUMN_NAME_KLEIDERSCHRANK_FK + ") REFERENCES " + KleiderschrankEntry.TABLE_NAME + "(" + KleiderschrankEntry._ID + ")" +
                    "FOREIGN KEY(" + KlamotteEntry.COLUMN_NAME_KATEGORIE_FK + ") REFERENCES " + DatabaseSchema.KategorieEntry.TABLE_NAME + "(" + DatabaseSchema.KategorieEntry._ID + ")" +
                    ")";

    private static final String SQL_DROP_TABLE_KLEIDERSCHRANK = "DROP TABLE IF EXISTS " + KleiderschrankEntry.TABLE_NAME + ";";
    private static final String SQL_DROP_TABLE_KATEGORIE = "DROP TABLE IF EXISTS " + KategorieEntry.TABLE_NAME + ";";
    private static final String SQL_DROP_TABLE_KLAMOTTE= "DROP TABLE IF EXISTS " +KlamotteEntry.TABLE_NAME + ";";

    private static KleiderschrankDBHelper instance = null;
    private Context ctx;

    private KleiderschrankDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx = context;
    }

    public static KleiderschrankDBHelper getInstance(Context ctx) {
        if (instance == null) {
            return new KleiderschrankDBHelper(ctx.getApplicationContext());
        }
        return instance;
    }

    public Cursor findAllKleiderschrank(){
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String sortOrder = KleiderschrankEntry.COLUMN_NAME_BEZEICHNUNG + " ASC";
        qb.setTables(KleiderschrankEntry.TABLE_NAME);
        return qb.query(getReadableDatabase(), null, null, null, null, null, sortOrder);
    }

    public Kleiderschrank insertContact(Kleiderschrank kleiderschrank) {
        ContentValues values = getAddressValues(kleiderschrank);

        long id = getWritableDatabase().insert(KleiderschrankEntry.TABLE_NAME, null, values);
        if (id == -1) {
            return null;
        }
        kleiderschrank.setId(id);
        return kleiderschrank;
    }

    /**
     * Helper method which creates ContentValues for Kleiderschrank
     *
     * @param kleiderschrank Kleiderschrank
     * @return ContentValues including all database columns
     */
    private ContentValues getAddressValues(Kleiderschrank kleiderschrank) {
        ContentValues values = new ContentValues();
        values.put(KleiderschrankEntry.COLUMN_NAME_BEZEICHNUNG, kleiderschrank.getBezeichnung());
        return values;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
