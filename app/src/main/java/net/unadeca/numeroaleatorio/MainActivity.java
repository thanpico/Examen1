package net.unadeca.numeroaleatorio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private  int aleatorio;
    private int intentos=3;
    private int  numeroUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final TextView pantalla = findViewById(R.id.pantalla);
        final EditText numero = findViewById(R.id.numero);

        final Button informacion = findViewById(R.id.info);
        final Button jugar = findViewById(R.id.jugar);
        final Button resert = findViewById(R.id.resert);
        final TextView Intentos = findViewById(R.id.Intentos);
        resert.setEnabled(false);
        Intentos.setText(String.valueOf(intentos));
        //Toast de bienvenida
        Toast.makeText(getApplicationContext(),getResources().getString(R.string.bienvenida), Toast.LENGTH_LONG).show();


        //boton utilizado para dar informacion al usuario como son las breves intrucciones del pequeño juego
        informacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater mInflater = getLayoutInflater();
                View mLayout = mInflater.inflate(R.layout.toast_layout,
                (ViewGroup)findViewById(R.id.toastLayout));


                Toast mToast = new Toast(getApplicationContext());
                mToast.setGravity(Gravity.RELATIVE_LAYOUT_DIRECTION,0,0);//se establece la posicion en la pantalla
                mToast.setView(mLayout);//Se establece la vista del toast
                mToast.setDuration(mToast.LENGTH_LONG);// se establece la duracion del toast
                mToast.show();//se muestra el toast
            }
        });

        aleatorio = (int) (Math.random()*10+1);//metodo para generar numero aleatorio

        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validar(numero))

                numeroUsuario=Integer.parseInt(numero.getText().toString());


                    //utilizamos un if para ver si el numero aleatorio generado es mayor que el numero que ingresó el usuario y así indicarle
                    //que digite un nuemero mayor
                    if (aleatorio > numeroUsuario) {

                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.numeromayor), Toast.LENGTH_LONG).show();
                        numero.setText(null);
                        intentos--;
                        Intentos.setText(String.valueOf(intentos));

                    }
                    //volvemos a utilizar un if pero es esta ocasion para ver si es menor
                    if (aleatorio < numeroUsuario){

                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.numeromenor), Toast.LENGTH_LONG).show();
                        numero.setText(null);
                        intentos--;
                        Intentos.setText(String.valueOf(intentos));

                    }
                    //de no adivinar el numero, los intentos se irán disminuyendo hasta llegar a creo, acá es cuando el usuario pierde
                    if (intentos==0){

                        jugar.setEnabled(false);
                        pantalla.setText(R.string.perdedor);
                        resert.setEnabled(true);
                        numero.setEnabled(false);
                        Intentos.setText(String.valueOf(intentos));
                    }

                    //a la hora que el numero aleatorio sea igual al numero ingresado se termina el juego, mostrando un mensaje en pantalla
                    //que ha ganado
                    if (aleatorio == numeroUsuario){

                        pantalla.setText(R.string.ganador);
                        jugar.setEnabled(false);
                        resert.setEnabled(true);
                        numero.setEnabled(false);
                        Intentos.setText(String.valueOf(intentos));
                    }



                    }
        });



        //utilizo un boton de resert para volver a jugar, así tambien restaurando todos los valores como los intentos, numero aleatorio nuevo
        //tambien se activa nuevamente el boton de jugar
        resert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentos=3;
                aleatorio = (int) (Math.random()*10+1);
                jugar.setEnabled(true);
                resert.setEnabled(false);
                pantalla.setText(R.string.numero);
                numero.setEnabled(true);
                numero.setText(null);
            }
        });

    }

    //metodo para validar si se da el caso que el EditText esté vacío
    private boolean validar(EditText numero){

        boolean send = true;

        if(numero.getText().toString().isEmpty()){

            Toast.makeText(getApplicationContext(), getResources().getString(R.string.errornumero), Toast.LENGTH_LONG).show();
            send =false;
        }

        return send;
    }


}
