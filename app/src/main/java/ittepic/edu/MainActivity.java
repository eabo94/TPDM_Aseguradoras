package ittepic.edu;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b1,b2;
    EditText tel,nom,dom,fec;
    private AdminSQLiteOpenHelper admin;
    private SQLiteDatabase bd;

    private ContentValues registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        admin = new AdminSQLiteOpenHelper(getApplicationContext(), "Aseguro", null,1);
        bd = admin.getWritableDatabase();
        tel= findViewById(R.id.editText);
        nom=findViewById(R.id.editText2);
        dom=findViewById(R.id.editText3);
        fec=findViewById(R.id.editText4);

        b1=findViewById(R.id.button);
        //b1 = findViewById(R.id.button2);
        b2 = findViewById(R.id.button4);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "Aseguro", null, 1);
                bd = admin.getWritableDatabase();
                registro = new ContentValues();

                if ( tel!= null) {

                    registro.put("TEL", tel.getText().toString());// nombre del campo de la tabla
                    registro.put("NOMBRE",nom.getText().toString());
                    registro.put("DOMICILIO",dom.getText().toString());
                    registro.put("FECHA",fec.getText().toString());



                    bd.insert("PROPIETARIO", null, registro);// la tabla

                    bd.close();
                    Toast.makeText(MainActivity.this, "REGISTRO ALMACENADO", Toast.LENGTH_LONG).show();


                    tel.setText("");
                    nom.setText("");
                    dom.setText("");
                    fec.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "REGISTRO   NO ALMACENADO", Toast.LENGTH_LONG).show();// indica una alerta de que se registro

                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);
            }
        });
    }
}
