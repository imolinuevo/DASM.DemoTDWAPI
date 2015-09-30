package com.example.gestionusuarios;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MostrarUsuarioActivity extends AppCompatActivity {

    TextView tvUserId, tvUserName, tvUserEmail, tvUserCTime, tvUserGroup;
    ToggleButton tbUserIsActive, tbUserIsAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_usuario);

        // Recuperar usuario
        final Usuario usuario = (Usuario) getIntent().getExtras().getSerializable("usuario");
        Log.i("Mostrar Usuario", usuario.toString());

        // Identificar recursos
        tvUserId       = (TextView) findViewById(R.id.tvUserId);
        tvUserName     = (TextView) findViewById(R.id.tvUserName);
        tvUserEmail    = (TextView) findViewById(R.id.tvUserEmail);
        tbUserIsActive = (ToggleButton) findViewById(R.id.tbUserIsActive);
        tbUserIsAdmin  = (ToggleButton) findViewById(R.id.tbUserIsAdmin);
        tvUserCTime    = (TextView) findViewById(R.id.tvUserCTime);
        tvUserGroup    = (TextView) findViewById(R.id.tvUserGroup);

        // Asignar valores
        tvUserId.setText(String.valueOf(usuario.getId()));
        tvUserName.setText(usuario.getUsername());
        tvUserEmail.setText(usuario.getEmail());
        tbUserIsActive.setChecked(usuario.isActive());
        tbUserIsActive.setClickable(false);
        tbUserIsAdmin.setChecked(usuario.isAdmin());
        tbUserIsAdmin.setClickable(false);
        if (usuario.getCreateTime() != null) {
            tvUserCTime.setText(usuario.getCreateTime().getDate());
        }
        if (usuario.getGroupId() != null) {
            tvUserGroup.setText(usuario.getGroupId().getGroupname());
            tvUserGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StringBuilder txtGrupo = new StringBuilder();
                    txtGrupo.append("Id: " + usuario.getGroupId().getId() + "\n");
                    txtGrupo.append("Nombre: " + usuario.getGroupId().getGroupname() + "\n");
                    txtGrupo.append("Descripción: " + usuario.getGroupId().getDescription());
                    Toast.makeText(getApplicationContext(), txtGrupo.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mostrar_usuario, menu);
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
}
