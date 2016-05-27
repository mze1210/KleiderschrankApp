package com.example.gruppe1.kleiderschrankapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created by Furkan on 17.05.2016.
 */
import com.example.gruppe1.kleiderschrankapp.activities.KleiderschrankAnlegenActivity;
import com.example.gruppe1.kleiderschrankapp.dao.DatabaseSchema.KleiderschrankEntry;
import com.example.gruppe1.kleiderschrankapp.dao.DatabaseSchema.KlamotteEntry;
import com.example.gruppe1.kleiderschrankapp.dao.DatabaseSchema.KategorieEntry;
import com.example.gruppe1.kleiderschrankapp.model.Kategorie;
import com.example.gruppe1.kleiderschrankapp.model.Klamotte;
import com.example.gruppe1.kleiderschrankapp.model.Kleiderschrank;
import com.example.gruppe1.kleiderschrankapp.util.Constants;


public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "kleiderschrankapp.db";

    private static final String SQL_CREATE_TABLE_KLEIDERSCHRANK =
            "CREATE TABLE " + KleiderschrankEntry.TABLE_NAME + " (" +
                    KleiderschrankEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KleiderschrankEntry.COLUMN_NAME_BEZEICHNUNG + " TEXT " +
                    ");";

    public static final String SQL_CREATE_TABLE_KATEGORIE =
            "CREATE TABLE " + KategorieEntry.TABLE_NAME + " (" +
                    KategorieEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KategorieEntry.COLUMN_NAME_BEZEICHNUNG + " TEXT " +
                    ");";

    private static final String SQL_CREATE_TABLE_KLAMOTTE =
            "CREATE TABLE " + KlamotteEntry.TABLE_NAME + " (" +
                    KlamotteEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KlamotteEntry.COLUMN_NAME_KLEIDERSCHRANK_FK + " TEXT, " +
                    KlamotteEntry.COLUMN_NAME_KATEGORIE_FK + " TEXT, " +
                    KlamotteEntry.COLUMN_NAME_IMAGE_PATH + " TEXT, " +
                    "" +
                    " FOREIGN KEY(" + KlamotteEntry.COLUMN_NAME_KLEIDERSCHRANK_FK + ") REFERENCES " + KleiderschrankEntry.TABLE_NAME + "(" + KleiderschrankEntry._ID + ")," +
                    " FOREIGN KEY(" + KlamotteEntry.COLUMN_NAME_KATEGORIE_FK + ") REFERENCES " + DatabaseSchema.KategorieEntry.TABLE_NAME + "(" + DatabaseSchema.KategorieEntry._ID + ")" +
                    ")";

    private static final String SQL_DROP_TABLE_KLEIDERSCHRANK = "DROP TABLE IF EXISTS " + KleiderschrankEntry.TABLE_NAME + ";";
    private static final String SQL_DROP_TABLE_KATEGORIE = "DROP TABLE IF EXISTS " + KategorieEntry.TABLE_NAME + ";";
    private static final String SQL_DROP_TABLE_KLAMOTTE = "DROP TABLE IF EXISTS " + KlamotteEntry.TABLE_NAME + ";";

    private static DBHelper instance = null;
    private Context ctx;

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx = context;
    }

    public static DBHelper getInstance(Context ctx) {
        if (instance == null) {
            return new DBHelper(ctx.getApplicationContext());
        }
        return instance;
    }

    public Cursor findAllKleiderschrank() {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String sortOrder = KleiderschrankEntry.COLUMN_NAME_ID + " ASC";
        qb.setTables(KleiderschrankEntry.TABLE_NAME);
        return qb.query(getReadableDatabase(), null, null, null, null, null, sortOrder);
    }

    public Kleiderschrank findKleiderschrankById(long id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(KleiderschrankEntry.TABLE_NAME, null, "id = " + id, null, null, null, null);

        Kleiderschrank kleiderschrank = new Kleiderschrank();
        kleiderschrank.setId(id);
        kleiderschrank.setBezeichnung(cursor.getString(cursor.getColumnIndex(KleiderschrankEntry.COLUMN_NAME_BEZEICHNUNG)));

        return kleiderschrank;
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

        sqLiteDatabase.execSQL("DELETE FROM " + KleiderschrankEntry.TABLE_NAME + " WHERE " + KleiderschrankEntry.COLUMN_NAME_ID + " =\" " + kleiderschrank.getId() + ";");
    }

    public Cursor findAllKlamotte() {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String sortOrder = KlamotteEntry.COLUMN_NAME_ID + " ASC";
        qb.setTables(KlamotteEntry.TABLE_NAME);

        return qb.query(getReadableDatabase(), null, null, null, null, null, sortOrder);
    }


    public Cursor findAllKlamotteWithKleiderschrankKategorie() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT klamotte._id, klamotte.image, kleiderschrank.bezeichnung as " + Constants.KLEIDERSCHRANK_BEZEICHNUNG +
                ", kategorie.bezeichnung as " + Constants.KATEGORIE_BEZEICHNUNG +
                " FROM " +
                " klamotte " +
                " JOIN kleiderschrank on kleiderschrank._id = klamotte.kleiderschrank_fk" +
                " JOIN kategorie on kategorie._id = klamotte.kategorie_fk";


        return sqLiteDatabase.rawQuery(query, null);
    }

    public void insertKlamotte(Klamotte klamotte) {
        ContentValues values = new ContentValues();

        values.put(KlamotteEntry.COLUMN_NAME_KATEGORIE_FK, klamotte.getKategorie().getId());
        values.put(KlamotteEntry.COLUMN_NAME_KLEIDERSCHRANK_FK, klamotte.getKleiderschrank().getId());

        Uri image = klamotte.getImage();
        if (image == null) {
            klamotte.setImage(Uri.parse(""));
        }
        values.put(KlamotteEntry.COLUMN_NAME_IMAGE_PATH, klamotte.getImage().getPath());

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(KlamotteEntry.TABLE_NAME, null, values);
        sqLiteDatabase.close();
    }

    public void deleteKlamotte(Klamotte klamotte) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        sqLiteDatabase.execSQL("DELETE FROM " + KlamotteEntry.TABLE_NAME + " WHERE " + KleiderschrankEntry.COLUMN_NAME_ID + " =\" " + klamotte.getId() + ";");
    }

    public Cursor findAllKategorie() {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String sortOrder = KategorieEntry.COLUMN_NAME_ID + " ASC";
        qb.setTables(KategorieEntry.TABLE_NAME);

        return qb.query(getReadableDatabase(), null, null, null, null, null, sortOrder);
    }

    public void insertKategorie(Kategorie kategorie) {
        ContentValues values = new ContentValues();

        values.put(KategorieEntry.COLUMN_NAME_BEZEICHNUNG, kategorie.getBezeichnung());

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(KategorieEntry.TABLE_NAME, null, values);
        sqLiteDatabase.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = SQL_CREATE_TABLE_KLEIDERSCHRANK;
        sqLiteDatabase.execSQL(query);

        query = SQL_CREATE_TABLE_KLAMOTTE;
        sqLiteDatabase.execSQL(query);

        query = SQL_CREATE_TABLE_KATEGORIE;
        sqLiteDatabase.execSQL(query);

        query = "INSERT INTO " + KategorieEntry.TABLE_NAME + " values(null, 'Hose');";
        sqLiteDatabase.execSQL(query);

        query = "INSERT INTO " + KategorieEntry.TABLE_NAME + " values (null, 'Jacke');";
        sqLiteDatabase.execSQL(query);

        query = "INSERT INTO " + KategorieEntry.TABLE_NAME + " values (null, 'Hemd');";
        sqLiteDatabase.execSQL(query);

    }

    public String databaseToString() {
        String dbString = "";

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String query = "SELECT * FROM " + KlamotteEntry.TABLE_NAME + " WHERE 1";

        //Cursor point to a location of your results
        Cursor c = sqLiteDatabase.rawQuery(query, null);

        //Move to the first row in your results
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(KlamotteEntry.COLUMN_NAME_ID)) != null) {
                dbString += c.getString(c.getColumnIndex(KlamotteEntry.COLUMN_NAME_ID));
                dbString += c.getString(c.getColumnIndex(KlamotteEntry.COLUMN_NAME_KLEIDERSCHRANK_FK));
                dbString += c.getString(c.getColumnIndex(KlamotteEntry.COLUMN_NAME_KATEGORIE_FK));
                dbString += c.getString(c.getColumnIndex(KlamotteEntry.COLUMN_NAME_IMAGE_PATH));


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
