package br.unigran.controledeabastecimento.BancoDados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) { super(context,
            "BancoLista",
            null, 1);}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Lista(" +
                "id interger primary key autoincrement," +
                "kmAtual varchar(7)," +
                "qntAbastecido varchar(7)," +
                "diaAbastecido varchar(7)," +
                "valorAbastecido varchar(15))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
