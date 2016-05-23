package com.example.gruppe1.kleiderschrankapp.dao;

import android.provider.BaseColumns;

/**
 * Contains database schemas for Contact and Address
 */
public final class DatabaseSchema {

    /**
     * Private constructor for DatabaseSchema to prevent instantiation
     */
    private DatabaseSchema() {

    }

    /**
     * Defines the columns for kleiderschrank table
     */
    public static abstract class KleiderschrankEntry implements BaseColumns {
        public static final String TABLE_NAME = "kleiderschrank";
        public static final String COLUMN_NAME_ID = "ID";
        public static final String COLUMN_NAME_BEZEICHNUNG = "bezeichnung";
    }

    /**
     * Defines the columns for klamotte table
     */
    public static abstract class KlamotteEntry implements BaseColumns {
        public static final String TABLE_NAME = "klamotte";
        public static final String COLUMN_NAME_ID = "ID";
        public static final String COLUMN_NAME_KLEIDERSCHRANK_FK = "kleiderschrank_fk";
        public static final String COLUMN_NAME_KATEGORIE_FK = "kategorie_fk";
    }

    /**
     * Defines the columns for kategorie table
     */
    public static abstract class KategorieEntry implements BaseColumns {
        public static final String COLUMN_NAME_ID = "ID";
        public static final String TABLE_NAME = "kategorie";
        public static final String COLUMN_NAME_BEZEICHNUNG = "bezeichnung";
    }
}
