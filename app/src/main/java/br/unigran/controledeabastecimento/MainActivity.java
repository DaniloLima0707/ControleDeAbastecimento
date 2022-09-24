package br.unigran.controledeabastecimento;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.unigran.controledeabastecimento.BancoDados.DBHelper;
import br.unigran.controledeabastecimento.BancoDados.RegistroDB;

public class MainActivity extends AppCompatActivity {
    EditText kmAtual;//nome
    EditText qntAbastecida;//telefone
    EditText diaAbastecimento;//data
    EditText valorAbastecido;//novo
    Button btnSalvar;
    ListView listaDados;
    List<Registro> dados;
    DBHelper db;
    RegistroDB registroDB;
    Integer atualiza;
    Integer confirma = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);
        registroDB = new RegistroDB(db);

        kmAtual = findViewById(R.id.kmAtual);
        qntAbastecida = findViewById(R.id.qntAbastecida);
        diaAbastecimento = findViewById(R.id.diaAbastecimento);
        valorAbastecido = findViewById(R.id.valorAbastecido);
        btnSalvar = findViewById(R.id.btnSalvar);
        listaDados = findViewById(R.id.listaDados);
        dados = new ArrayList<>(); //Aloca a lista
        //Vincula adapter
        ArrayAdapter adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, dados);
        listaDados.setAdapter(adapter);
        registroDB = new RegistroDB(db);
        registroDB.lista(dados);//Lista Inicial
        acoes();

    }
    private void acoes(){
        confirma = null;
        listaDados.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        AlertDialog.Builder mensagem = new AlertDialog.Builder(view.getContext());
                        mensagem.setTitle("Opções");
                        mensagem.setMessage("Escolha uma opção para realizar");
                        mensagem.setPositiveButton("Remover", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int id) {

                                new AlertDialog.Builder(view.getContext())
                                        .setMessage("Deseja Remover")
                                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int k) {
                                                registroDB.lista(dados);
                                                ((ArrayAdapter) listaDados.getAdapter()).notifyDataSetChanged();
                                                String msg1 = "Contato removido";
                                                Toast.makeText(getApplicationContext(), msg1, Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .setNegativeButton("Cancelar", null)
                                        .create().show();
                            }
                        });
                        mensagem.setNegativeButton("Editar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                atualiza = dados.get(i).getId();
                                kmAtual.setText(dados.get(i).getKMatual());
                                qntAbastecida.setText(dados.get(i).getQNTAbastecida().toString());
                                diaAbastecimento.setText(dados.get(i).getDIAAbastecido().toString());
                                valorAbastecido.setText(dados.get(i).getVALORAbastecido().toString());

                                registroDB.atualizar(dados.get(i));
                                registroDB.lista(dados);

                                confirma = 1;
                            }
                        });
                        mensagem.setNeutralButton("Cancelar", null);
                        mensagem.show();
                        return false;

                    }
                });
    }
    public boolean verificar(){
        String s1 = kmAtual.getText().toString();
        String s2 = qntAbastecida.getText().toString();
        String s3 = diaAbastecimento.getText().toString();
        String s4 = valorAbastecido.getText().toString();
        if ((s1.equals(null) || s2.equals(null) || s3.equals(null) || s4.equals(null))
                || (s1.equals("") || s2.equals("") || s3.equals("") || s4.equals(""))){
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    public void salvar(View view){
        if ( verificar()){
            Registro registro = new Registro();
            if (atualiza != null){
                registro.setId(atualiza);

                registroDB.lista(dados);
                Toast.makeText(this, "Contato Salvo", Toast.LENGTH_SHORT).show();
            }
            registro.setKMatual(kmAtual.getText().toString());
            registro.setQNTAbastecida(qntAbastecida.getText().toString());
            registro.setDIAAbastecido(diaAbastecimento.getText().toString());
            registro.setVALORAbastecido(valorAbastecido.getText().toString());

            if (atualiza != null)
                registroDB.atualizar(registro);
            else {
                registroDB.inserir(registro);
                registroDB.lista(dados);
                Toast.makeText(this, "Contato Salvo", Toast.LENGTH_SHORT).show();
            }
            registroDB.lista(dados);
            listaDados.invalidateViews();
            limpar();
            atualiza = null;
            confirma = null;
        }
    }

    private void limpar(){
        kmAtual.setText("");
        qntAbastecida.setText("");
        diaAbastecimento.setText("");
        valorAbastecido.setText("");
    }

    @Override
    public void onBackPressed(){
        if (confirma != null){
            super.onBackPressed();
            limpar();
            String msgCancelar = "Edição Cancelada";
            Toast.makeText(getApplicationContext(), msgCancelar, Toast.LENGTH_SHORT).show();
            confirma = null;
        } else {
            super.onBackPressed();
            limpar();
            String msgSair = "Saindo do Sistema";
            Toast.makeText(getApplicationContext(), msgSair, Toast.LENGTH_SHORT).show();
        }
    }
}