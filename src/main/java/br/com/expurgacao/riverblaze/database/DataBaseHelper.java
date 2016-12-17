package br.com.expurgacao.riverblaze.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import br.com.expurgacao.riverblaze.database.entity.CheckPoint;
import br.com.expurgacao.riverblaze.database.entity.Enemy;
import br.com.expurgacao.riverblaze.database.entity.Level;
import br.com.expurgacao.riverblaze.database.entity.Player;

/**
 * Created by NysioFontes on 18/01/2016.
 */
public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String databaseName = "riverblaze.db";
    private static final int databaseVersion = 13;

    public DataBaseHelper(Context context) {
        super(context, databaseName, null, databaseVersion);
        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sd, ConnectionSource cs) {
        try {
            TableUtils.clearTable(cs, Player.class);
            TableUtils.clearTable(cs, Enemy.class);
            TableUtils.clearTable(cs, Level.class);
            TableUtils.clearTable(cs, CheckPoint.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sd, ConnectionSource cs, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(cs, Player.class, true);
            TableUtils.dropTable(cs, Enemy.class, true);
            TableUtils.dropTable(cs, Level.class, true);
            TableUtils.dropTable(cs, CheckPoint.class, true);
            onCreate(sd, cs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close(){
        super.close();
    }

}
