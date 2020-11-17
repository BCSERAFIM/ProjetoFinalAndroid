package com.bcserafim.projetoandroid.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.entity.Produto;
import com.bcserafim.projetoandroid.entity.Usuario;
import com.bcserafim.projetoandroid.helper.UsuarioCallback;
import com.bcserafim.projetoandroid.helper.UsuarioFacade;
import com.google.android.material.textfield.TextInputEditText;

public class CadastroUsuario extends AppCompatActivity {
    private TextInputEditText login, senha;
    private Usuario usuarioAtual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        login = findViewById(R.id.editTextLogin);
        senha = findViewById(R.id.editTextSenha);


        usuarioAtual = (Usuario) getIntent().getSerializableExtra("usuarioSelecionado");

        //Configurar produto na caixa de texto
        if (usuarioAtual != null) {
            login.setFocusable(false);
            login.setText(String.valueOf(usuarioAtual.getLogin()));
            senha.setText(usuarioAtual.getSenha());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastrar_usuario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final EditText login = findViewById(R.id.editTextLogin);
        final EditText senha = findViewById(R.id.editTextSenha);



        switch ((item.getItemId())) {
            case R.id.cadastrarProduto:
                if (login.getText().toString().trim().equals("") || senha.getText().toString().trim().equals("")) {
                    if (login.getText().toString().trim().equals("")) {
                        login.setError("Campo obrigatório");

                    }
                    if (senha.getText().toString().trim().equals("")) {
                        senha.setError("Campo obrigatório");

                    }
                } else {
                    String loginUsuario = login.getText().toString();
                    String senhaUsuario = senha.getText().toString().trim();
                    Usuario usuario = new Usuario();
                   /*se o usuario for diferente de null eu edito senão eu cadastro um novo*/
                    if (usuarioAtual != null) {


                        usuario.setLogin(Integer.valueOf(loginUsuario));
                        usuario.setSenha(senhaUsuario);
                        UsuarioFacade.alterar(usuario, new UsuarioCallback() {
                            @Override
                            public void onSuccess(Usuario usuario) {
                                Toast.makeText(CadastroUsuario.this,
                                        "Sucesso ao alterar usuario:",
                                        Toast.LENGTH_LONG).show();
                                finish();

                            }

                            @Override
                            public void onFailure(Throwable t) {
                                Toast.makeText(CadastroUsuario.this,
                                        "Erro ao cadastrar usuario: " +
                                                t.getMessage(),
                                        Toast.LENGTH_LONG).show();

                            }
                        });

                    } else {

                        if (!loginUsuario.isEmpty() && !senhaUsuario.isEmpty()) {
                            usuario.setLogin(Integer.valueOf(loginUsuario));
                            usuario.setSenha(senhaUsuario);
                            UsuarioFacade.cadastrar(usuario, new UsuarioCallback() {
                                @Override
                                public void onSuccess(Usuario usuario) {
                                    Toast.makeText(CadastroUsuario.this,
                                            "Sucesso ao cadastrar usuario:",
                                            Toast.LENGTH_LONG).show();
                                    finish();

                                }

                                @Override
                                public void onFailure(Throwable t) {
                                    Toast.makeText(CadastroUsuario.this,
                                            "Erro ao cadastrar usuario: " +
                                                    t.getMessage(),
                                            Toast.LENGTH_LONG).show();

                                }
                            });


                        }
                    }
                }


        }
        return super.onOptionsItemSelected(item);
    }


    public void cadastrarUsuario(View view) {


    }
}