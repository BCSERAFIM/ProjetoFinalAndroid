package com.bcserafim.projetoandroid.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.entity.Cliente;
import com.bcserafim.projetoandroid.helper.callback.ClienteCallback;
import com.bcserafim.projetoandroid.helper.facade.ClienteFacade;
import com.google.android.material.textfield.TextInputEditText;

public class CadastroClienteActivity extends AppCompatActivity {


    private TextInputEditText editTextCpf, editTextNome, ediTextSobrenome;
    private Cliente clienteAtual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);
        editTextCpf = findViewById(R.id.editTextLogin);
        editTextNome = findViewById(R.id.editTextSenha);
        ediTextSobrenome = findViewById(R.id.editTextSobrenome);

        //Recuperar cliente caso seja edição

        clienteAtual = (Cliente) getIntent().getSerializableExtra("clienteSelecionado");

        //Configurar cliente na caixa de texto
        if (clienteAtual != null) {
            editTextCpf.setFocusable(false);
            editTextCpf.setText(clienteAtual.getCpf());
            editTextNome.setText(clienteAtual.getNome());
            ediTextSobrenome.setText(clienteAtual.getSobreNome());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_cadastrar_cliente, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ((item.getItemId())) {
            case R.id.cadastrarCliente:
                //Executa ação para o item salvar
                if (clienteAtual != null) {//edição
                    String cpfCliente = editTextCpf.getText().toString();
                    String nomeCliente = editTextNome.getText().toString();
                    String sobrenomeCliente = ediTextSobrenome.getText().toString();

                    if (!cpfCliente.isEmpty() && !nomeCliente.isEmpty() && !sobrenomeCliente.isEmpty()) {

                        Cliente cliente = new Cliente();
                        cliente.setCpf(clienteAtual.getCpf());
                        cliente.setNome(nomeCliente);
                        cliente.setSobreNome(sobrenomeCliente);
                        cliente.setId(clienteAtual.getId());
                        ClienteFacade.alterar(cliente, new ClienteCallback() {
                            @Override
                            public void onSuccess(Cliente cliente) {
                                Toast.makeText(CadastroClienteActivity.this,
                                        "Sucesso ao alterar cliente: ",
                                        Toast.LENGTH_LONG).show();
                                finish();
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                Toast.makeText(CadastroClienteActivity.this,
                                        "Erro ao alterar cliente: " +
                                                t.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        });

                    }else {
                        if(!cpfCliente.isEmpty()){
                            editTextCpf.setText(clienteAtual.getCpf());
                        }if(nomeCliente.isEmpty()){
                            editTextNome.setError("Campo obrigatório");
                        }if(sobrenomeCliente.isEmpty()){
                            ediTextSobrenome.setError("Campo obrigatório");
                        }
                    }

                } else {//salvar
                    String cpfCliente = editTextCpf.getText().toString();
                    String nomeCliente = editTextNome.getText().toString();
                    String sobrenomeCliente = ediTextSobrenome.getText().toString();

                    if (!cpfCliente.isEmpty() && !nomeCliente.isEmpty() && !sobrenomeCliente.isEmpty()) {
                        Cliente cliente = new Cliente();
                        cliente.setCpf(cpfCliente);
                        cliente.setNome(nomeCliente);
                        cliente.setSobreNome(sobrenomeCliente);
                        ClienteFacade.cadastrar(cliente, new ClienteCallback() {
                            @Override
                            public void onSuccess(Cliente cliente) {
                                Toast.makeText(CadastroClienteActivity.this,
                                        "Sucesso ao cadastrar cliente: ",
                                        Toast.LENGTH_LONG).show();
                                finish();
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                Toast.makeText(CadastroClienteActivity.this,
                                        "Erro ao cadastrar cliente: " +
                                                t.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        });

                    } else {
                        if(cpfCliente.isEmpty()){
                            editTextCpf.setError("Campo obrigatório");
                        }if(nomeCliente.isEmpty()){
                            editTextNome.setError("Campo obrigatório");
                        }if(sobrenomeCliente.isEmpty()){
                            ediTextSobrenome.setError("Campo obrigatório");
                        }
                    }

                }

                break;
        }

        return super.onOptionsItemSelected(item);
    }


}