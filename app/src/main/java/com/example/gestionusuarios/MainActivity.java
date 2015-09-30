package com.example.gestionusuarios;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Context context;
    ArrayList<Usuario> usuarios;
    UsuariosAdapter usuariosAdapter;
    ProgressDialog progressDialog;
    GetUsersTask tareaGetUsers = null;
    MainActivity main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Identificar recursos
        listView = (ListView) findViewById(R.id.listView);
        context = this.getApplicationContext();
        main = this;

        // Inicializar usuarios
        usuarios = new ArrayList<>();
        usuariosAdapter = new UsuariosAdapter(context, R.layout.usuario, usuarios);
        listView.setAdapter(usuariosAdapter);

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

                // TO DO generamos los usuarios a partir del objeto JSON
                for (int i = 0; i < respuestaJSON.length(); i++) {
                    try {
                        Usuario usuario = new Usuario(respuestaJSON.getJSONObject(i));
                        usuarios.add(usuario);
                    } catch (Exception e) {
                        Log.e("Error: ", "JSON incorrecto: " + e.getMessage());
                    }
                }

            } catch (Exception e) {
                Log.e("ERROR", e.getMessage());
                return false;
            }

            return (codRespuesta == HttpURLConnection.HTTP_OK);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            usuariosAdapter.clear();

            progressDialog = ProgressDialog.show(main, getString(R.string.msgWait), getString(R.string.msgLoadingUsers), true, true);
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

                @Override
                public void onCancel(DialogInterface dialog) {
                    tareaGetUsers.cancel(true);
                }
            });
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            // showProgress(false);

            for (Usuario u : usuarios) {
                usuariosAdapter.add(u);
            }
            usuariosAdapter.notifyDataSetChanged();
            progressDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            // showProgress(false);
        }
    }

}
