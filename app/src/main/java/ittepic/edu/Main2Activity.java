package ittepic.edu;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    Button b2,b3;
    EditText descripcion,fecha,tipo;
    Spinner sp;
    ListView lista;
    private AdminSQLiteOpenHelper admin;
    private SQLiteDatabase bd;
    private Cursor fila;
    private ContentValues registro;
    String[] arreglo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        admin = new AdminSQLiteOpenHelper(getApplicationContext(), "Aseguro", null, 1);
        bd = admin.getWritableDatabase();
        descripcion=findViewById(R.id.editText2);
        fecha=  findViewById(R.id.editText3);
        tipo=findViewById(R.id.editText4);
        b2 =  findViewById(R.id.button2);
        b3 =  findViewById(R.id.button3);
        lista= findViewById(R.id.lista);
        sp = findViewById(R.id.spinner);
        fila = bd.rawQuery("SELECT TEL AS _id, NOMBRE FROM PROPIETARIO ORDER BY NOMBRE", null);


        SimpleCursorAdapter adapter2 = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_dropdown_item, fila,
                new String[] {"NOMBRE"}, new int[] {android.R.id.text1}, SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter2);



        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bd = admin.getWritableDatabase();
                registro = new ContentValues();




                registro.put("DESCRIPCION", descripcion.getText().toString());
                registro.put("FECHA",fecha.getText().toString());
                registro.put("TIPO",tipo.getText().toString());
                registro.put("TEL",sp.getSelectedItemId());//nombre del campo

//nombre de la tabla
                bd.insert("SEGURO", null, registro);

                bd.close();
                Toast.makeText(Main2Activity.this, "REGISTRO ALMACENADO", Toast.LENGTH_LONG).show();

                descripcion.setText("");
                tipo.setText("");
                fecha.setText("");


                Toast.makeText(Main2Activity.this, "REGISTRO   NO ALMACENADO", Toast.LENGTH_LONG).show();

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bd!= null)
                {
                    admin = new AdminSQLiteOpenHelper(getApplicationContext(), "Aseguro", null, 1);
                    bd = admin.getWritableDatabase();

                    Cursor fila = bd.rawQuery("SELECT PROPIETARIO.NOMBRE, SEGURO.DESCRIPCION,SEGURO.TIPO FROM PROPIETARIO,SEGURO where SEGURO.TEL= PROPIETARIO.TEL", null);
                    int cantidad = fila.getCount();// cantidad de registro
                    int i=0;
                    arreglo = new String[cantidad];
                    if (fila.moveToFirst()) {
                        do {
                            String linea = "Nombre: "+fila.getString(0) + "  Descripcion: " + fila.getString(1)+ "  Tipo: " + fila.getString(2);
                            arreglo[i] = linea;
                            i++;
                        } while (fila.moveToNext());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Main2Activity.this, android.R.layout.simple_list_item_1, arreglo);
                    lista.setAdapter(adapter);


                }
            }
        });
    }
}
