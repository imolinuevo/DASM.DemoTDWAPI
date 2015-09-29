package com.example.gestionusuarios;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private GetUsersTask tareaGetUsers = null;

    ListView listView;
    Context context;
    ArrayList<Usuario> usuarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Identificar recursos
        listView = (ListView) findViewById(R.id.listView);
        context = this.getApplicationContext();

        // Inicializar usuarios
        usuarios = new ArrayList<>();

        // Lanzar la tarea de recuperación de usuarios
        tareaGetUsers = new GetUsersTask();
        tareaGetUsers.execute((Void) null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static String getResponseText(InputStream inStream) {
        // very nice trick from
        // http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
        return new Scanner(inStream).useDelimiter("\\A").next();
    }

    public class GetUsersTask extends AsyncTask<Void, Void, Boolean> {

        private int codRespuesta = HttpURLConnection.HTTP_BAD_REQUEST;

        GetUsersTask() {
            usuarios = new ArrayList<>();
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                URL url = new URL(getString(R.string.getUsersURL));
                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                conexion.setDoInput(true);
                conexion.setRequestMethod("GET");

                // recuperamos los usuarios
                InputStream in = new BufferedInputStream(conexion.getInputStream());
                codRespuesta = conexion.getResponseCode();
                Log.d("Info", "POST Response Code: " + String.valueOf(codRespuesta));
                String jsonStr = getResponseText(in);
                Log.d("Response: ", jsonStr);
                JSONArray respuestaJSON = new JSONArray(jsonStr);
                Log.d("Nº elementos: ", String.valueOf(respuestaJSON.length()));

                // parseamos JSON para obtener Usuarios
                for (int i = 0; i < respuestaJSON.length(); i++) {
                    JSONObject objeto = respuestaJSON.getJSONObject(i);
                    Usuario user = new Usuario();
                    user.setId(objeto.getInt(Usuario.TAG_ID));
                    user.setUsername(objeto.getString(Usuario.TAG_USERNAME));
                    user.setEmail(objeto.getString(Usuario.TAG_EMAIL));
                    user.setIsActive(objeto.getBoolean(Usuario.TAG_IS_ACTIVE));
                    user.setIsAdmin(objeto.getBoolean(Usuario.TAG_IS_ADMIN));
                    Grupo grupo = new Grupo();
                    JSONObject group = objeto.getJSONObject(Usuario.TAG_GROUP);
                    grupo.setId(group.getInt(Usuario.TAG_GROUP_ID));
                    grupo.setGroupname(group.getString(Usuario.TAG_GROUPNAME));
                    grupo.setDescription(group.getString(Usuario.TAG_GROUPDESC));
                    user.setGroup(grupo);
                    usuarios.add(user);
                }

            } catch (Exception e) {
                Log.e("ERROR", e.getMessage());
                return false;
            }

            return (codRespuesta == HttpURLConnection.HTTP_OK);
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            // showProgress(false);
        }

        @Override
        protected void onCancelled() {
            // showProgress(false);
        }
    }

}
