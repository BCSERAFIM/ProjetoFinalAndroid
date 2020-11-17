package com.bcserafim.projetoandroid.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.entity.Produto;
import com.bcserafim.projetoandroid.helper.ProdutoCallback;
import com.bcserafim.projetoandroid.helper.ProdutoFacade;
import com.google.android.material.textfield.TextInputEditText;

public class CadastroProduto extends AppCompatActivity {

    private TextInputEditText editCadastroProduto;
    private Produto produtoAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);
        editCadastroProduto = findViewById(R.id.editCadastroProduto);

        //Recuperar produto caso seja edição

        produtoAtual = (Produto) getIntent().getSerializableExtra("produtoSelecionado");

        //Configurar produto na caixa de texto
        if (produtoAtual != null) {
            editCadastroProduto.setText(produtoAtual.getDescricao());
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastrar_produto, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ((item.getItemId())) {
            case R.id.cadastrarProduto:
                //Executa ação para o item salvar
                if (editCadastroProduto.getText().toString().trim().equals("")) {
                    editCadastroProduto.setError("Campo obrigatório");

                }

                if (produtoAtual != null) {//edição
                    String descricaoProduto = editCadastroProduto.getText().toString();
                    if (!descricaoProduto.isEmpty()) {
                        Produto produto = new Produto();
                        produto.setDescricao(descricaoProduto);
                        produto.setId(produtoAtual.getId());
                        ProdutoFacade.alterar(produto, new ProdutoCallback() {
                            //atualizar no banco de dados

                            @Override
                            public void onSuccess(Produto produto) {
                                Toast.makeText(CadastroProduto.this,
                                        "Sucesso ao alterar produto: ",
                                        Toast.LENGTH_LONG).show();
                                finish();
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                Toast.makeText(CadastroProduto.this,
                                        "Erro ao alterar produto: " +
                                                t.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        });

                    }

                } else {//salvar

                    String descricaoProduto = editCadastroProduto.getText().toString();
                    if (!descricaoProduto.isEmpty()) {
                        Produto produto = new Produto();
                        produto.setDescricao(descricaoProduto);
                        ProdutoFacade.cadastrar(produto, new ProdutoCallback() {
                            @Override
                            public void onSuccess(Produto produto) {
                                Toast.makeText(CadastroProduto.this,
                                        "Sucesso ao cadastrar produto: ",
                                        Toast.LENGTH_LONG).show();
                                finish();


                            }

                            @Override
                            public void onFailure(Throwable t) {
                                Toast.makeText(CadastroProduto.this,
                                        "Erro ao cadastrar produto: " +
                                                t.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        });


                    }


                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}