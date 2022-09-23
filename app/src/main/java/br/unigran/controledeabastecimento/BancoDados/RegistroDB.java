package br.unigran.controledeabastecimento.BancoDados;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.unigran.controledeabastecimento.Registro;

public class RegistroDB {
    private DBHelper db;
    private SQLiteDatabase conexao;

    public RegistroDB(DBHelper db) { this.db = db; }

    public void inserir(Registro registro) {
        conexao = db.getWritableDatabase();//abre o bd
        ContentValues valores = new ContentValues();
        valores.put("campoNom", registro.getCampoNom());
        valores.put("campoTel", registro.getCampoTel());
        valores.put("campoDataNasc", registro.getCampoDataNasc());
        conexao.update("Lista", valores, "id=?", new String[]{registro.getId().toString()});
        conexao.close();
    }

    public void atualizar(Registro registro) {
        conexao = db.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("campoNom", registro.getCampoNom());
        valores.put("campoTel", registro.getCampoTel());
        valores.put("campoDataNasc", registro.getCampoDataNasc());
        conexao.update("Lista", valores, "id=?", new String[]{registro.getId().toString()});
        conexao.close();
    }

    public void remover(int id){
        conexao = db.getWritableDatabase();
        conexao.delete("Lista","id=?", new String[]{id + ""});
    }

    public void lista(List dados){
        dados.clear();
        conexao = db.getReadableDatabase();
        String names[] = {"id", "campoNom", "campoTel", "campoDataNasc"};
        Cursor query = conexao.query("Lista", names, null, null, null, null, "campoNom");
        while (query.moveToNext()){
            Registro registro = new Registro();
            registro.setId(Integer.parseInt(
                    query.getString(0)));
            registro.setCampoNom(
                    query.getString(1));
            registro.setCampoTel(
                    query.getString(2));
            registro.setCampoDataNasc(
                    query.getString(3));
            dados.add(registro);
        }
        conexao.close();
    }
}
