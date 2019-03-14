package ittepic.edu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper( Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table PROPIETARIO ( TEL integer primary key ,NOMBRE text,DOMICILIO text,FECHA text)");
        db.execSQL(" create table SEGURO( IDSEG integer primary key autoincrement,DESCRIPCION text,FECHA text,TIPO text,TEL INTEGER NOT NULL CONSTRAINT fk_id_dep REFERENCES PROPIETARIO(TEL) ON DELETE CASCADE ON UPDATE CASCADE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists PROPIETARIO" );
        db.execSQL(" create table PROPIETARIO (TEL integer primary key ,NOMBRE text,DOMICILIO text,FECHA text)");
        db.execSQL("drop table if exists SEGURO" );
        db.execSQL(" create table SEGURO( IDSEG integer primary key autoincrement,DESCRIPCION text,FECHA text,TIPO text,TEL INTEGER NOT NULL CONSTRAINT fk_id_dep REFERENCES departamento(iddep) ON DELETE CASCADE ON UPDATE CASCADE)");
    }
}
