package br.anmoraes.calculador_salarial_br.dbHelper;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


import br.anmoraes.calculador_salarial_br.model.Simulacao;


public class ConexaoSQLite extends SQLiteOpenHelper {

    private static final String DB_NAME = "CalculadorSalarialBr.sqlite";
    private static final int DB_VERSION = 1;


    private static final String TABELA_RESULTADO = "resultado";
    private static final String ID = "id";
    private static final String NOME_CADASTRO = "nomeCadastro";
    private static final String SALARIO_BRUTO = "salarioBruto";
    private static final String VALOR_INSS = "valorINSS";
    private static final String VALOR_IRPF = "valorIRPF";
    private static final String PENSAO_ALIMENTICIA = "pensaoAlimenticia";
    private static final String OUTROS_DESCONTOS = "outrosDescontos";
    private static final String BASE_IRPF = "baseIRPF";
    private static final String FGTS_DEPOSITADO = "fgtsDepositado";
    private static final String SALARIO_LIQUIDO = "salarioLiquido";

    private static final String[] COLUNAS =
            {NOME_CADASTRO, SALARIO_BRUTO, VALOR_INSS,
                    VALOR_IRPF, PENSAO_ALIMENTICIA, OUTROS_DESCONTOS,
                    BASE_IRPF, FGTS_DEPOSITADO, SALARIO_LIQUIDO};

    SQLiteDatabase db;

    public ConexaoSQLite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d("ConexaoSQLite", "Constructor");

        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("ConexaoSQLite", "onCreate");

        String sqlSimulacao = "CREATE TABLE IF NOT EXISTS resultado \n" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nomeCadastro TEXT,\n" +
                "salarioBruto REAL,\n" +
                "valorINSS REAL,\n" +
                "valorIRPF REAL,\n" +
                "pensaoAlimenticia REAL,\n" +
                "outrosDescontos REAL,\n" +
                "baseIRPF REAL,\n" +
                "fgtsDepositado REAL,\n" +
                "salarioLiquido REAL\n" +
                ");";

        try {

            db.execSQL(sqlSimulacao);
            this.onUpgrade(db, 1, DB_VERSION);

        } catch (SQLException e) {

            Log.e("DB_LOG", "onCreate:" + e.getLocalizedMessage());

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("ConexaoSQLite", "onUpgrade");

    }

    //INSERT INTO TABLE values
    public void insert(Simulacao simulacao) {

        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NOME_CADASTRO, simulacao.getNomeCadastro());
        values.put(SALARIO_BRUTO, simulacao.getSalariobruto());
        values.put(VALOR_INSS, simulacao.getValorINSS());
        values.put(VALOR_IRPF, simulacao.getValorIRPF());
        values.put(PENSAO_ALIMENTICIA, simulacao.getPensaoAlimenticia());
        values.put(OUTROS_DESCONTOS, simulacao.getOutrosDescontos());
        values.put(BASE_IRPF, simulacao.getBaseIRPF());
        values.put(FGTS_DEPOSITADO, simulacao.getFgtsDespositado());
        values.put(SALARIO_LIQUIDO, simulacao.getSalarioLiquido());

        db.insert(TABELA_RESULTADO, null, values);

    }


    public Simulacao getSimulacao(int id) {
        db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_RESULTADO, // a. tabela
                COLUNAS, // b. colunas
                " id = ?", // c. colunas para comparar
                new String[]{String.valueOf(id)}, // d. parâmetros
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        if (cursor == null) {
            return null;
        } else {
            cursor.moveToFirst();
            Simulacao simulacao = cursorToSimulacao(cursor);
            return simulacao;
        }
    }

    private Simulacao cursorToSimulacao(Cursor cursor) {
        Simulacao simulacao = new Simulacao();


        simulacao.setId(Integer.parseInt(cursor.getString(0)));
        simulacao.setNomeCadastro(cursor.getString(1));
        simulacao.setSalariobruto(cursor.getDouble(2));
        simulacao.setValorINSS(cursor.getDouble(3));
        simulacao.setValorIRPF(cursor.getDouble(4));
        simulacao.setPensaoAlimenticia(cursor.getDouble(5));
        simulacao.setOutrosDescontos(cursor.getDouble(6));
        simulacao.setBaseIRPF(cursor.getDouble(7));
        simulacao.setFgtsDespositado(cursor.getDouble(8));
        simulacao.setSalarioLiquido(cursor.getDouble(9));
        return simulacao;
    }


    public ArrayList<Simulacao> getAllSimulacoes() {
        ArrayList<Simulacao> listaSimulacoes = new ArrayList<Simulacao>();
        String sqlConsulta = "SELECT * FROM " + TABELA_RESULTADO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlConsulta, null);
        if (cursor.moveToFirst()) {
            do {
                Simulacao simulacao = cursorToSimulacao(cursor);
                listaSimulacoes.add(simulacao);
            } while (cursor.moveToNext());
        }
        return listaSimulacoes;
    }


    //UPDATE table SET
    public int update(Simulacao simulacao) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NOME_CADASTRO, simulacao.getNomeCadastro());



        int i = db.update(TABELA_RESULTADO, //tabela
                values, // valores
                ID + " = ?", // colunas para comparar
                new String[]{String.valueOf(simulacao.getId())}); //parâmetros
        db.close();
        return i; // número de linhas modificadas
    }


    //DELETE FROM table WHERE
    public int delete(Simulacao simulacao) {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABELA_RESULTADO, //tabela
                ID + " = ?", // colunas para comparar
                new String[]{String.valueOf(simulacao.getId())});
        db.close();
        return i; // número de linhas excluídas
    }
}






