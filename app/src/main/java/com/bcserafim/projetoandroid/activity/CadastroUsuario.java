package com.bcserafim.projetoandroid.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.entity.Usuario;
import com.bcserafim.projetoandroid.service.UsuarioService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bcserafim.projetoandroid.activity.MainActivity.BASE_URL;

public class CadastroUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        Button botaoCadastrar = (Button) findViewById(R.id.botaoSalvarUsuario);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {

            final EditText login = findViewById(R.id.textLoginUsuario);
            final EditText senha = findViewById(R.id.textSenhaUsuario);

            @Override
            public void onClick(View view) {
                if( login.getText().toString().trim().equals("")){
                    login.setError("Campo obrigatório");

                }
                if( senha.getText().toString().trim().equals("")){
                    senha.setError("Campo obrigatório");

                }

                String loginUsuario = login.getText().toString();
                String senhaUsuario = senha.getText().toString().trim();
                Usuario usuario = new Usuario();

                if (!loginUsuario.isEmpty() && !senhaUsuario.isEmpty()) {
                    usuario.setLogin(Integer.valueOf(loginUsuario));
                    usuario.setSenha(senhaUsuario);
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();



                    UsuarioService service = retrofit.create(UsuarioService.class);
                    Call<Usuario> call = service.cadastrarUsuario(usuario);
                    call.enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                            Context contexto = getApplicationContext();
                            String texto = "Usuário Criado";
                            int duracao = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(contexto, texto,duracao);
                            toast.show();

                        }

                        @Override
                        public void onFailure(Call<Usuario> call, Throwable t) {

                        }
                    });

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //    getMenuInflater().inflate(R.menu.menu_cadastrar_Usuario,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ((item.getItemId())) {
      /*      case R.id.cadastrarUsuario:
                //Executa ação para o item salvar
                Toast.makeText(CadastroUsuario.this,
                        "Botao salvar pressionado",
                        Toast.LENGTH_SHORT).show();
                break;*/
        }
        return super.onOptionsItemSelected(item);
    }

    public void cadastrarUsuario(View view) {


    }
}