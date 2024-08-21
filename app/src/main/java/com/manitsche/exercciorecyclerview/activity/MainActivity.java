package com.manitsche.exercciorecyclerview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.manitsche.exercciorecyclerview.R;
import com.manitsche.exercciorecyclerview.adapter.Adapter;
import com.manitsche.exercciorecyclerview.model.Compromisso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Compromisso> listaCompromissos = new ArrayList<>();
    private Adapter adapter;
    private EditText edtTitulo, edtData, edtHorario, edtLocal;
    private Button btnAdicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar os componentes
        recyclerView = findViewById(R.id.recyclerView);
        edtTitulo = findViewById(R.id.editTitulo);
        edtData = findViewById(R.id.editData);
        edtHorario = findViewById(R.id.editHorario);
        edtLocal = findViewById(R.id.editLocal);
        btnAdicionar = findViewById(R.id.buttonAdicionar);

        // Configurar o RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        // Inicializar o Adapter
        adapter = new Adapter(listaCompromissos);
        recyclerView.setAdapter(adapter);

        // Adicionar Compromisso no clique do botão
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarCompromisso();
            }
        });

        // Configurar o evento de clique nos itens da lista
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Compromisso compromisso = listaCompromissos.get(position);
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Local: " + compromisso.getLocal(),
                                        Toast.LENGTH_LONG
                                ).show();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                // Ação para clique longo (opcional)
                            }
                        }
                )
        );
    }

    // Método para adicionar um compromisso à lista
    private void adicionarCompromisso() {
        String titulo = edtTitulo.getText().toString();
        String data = edtData.getText().toString();
        String horario = edtHorario.getText().toString();
        String local = edtLocal.getText().toString();

        if (titulo.isEmpty() || data.isEmpty() || horario.isEmpty() || local.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Criar um novo compromisso
        Compromisso compromisso = new Compromisso(titulo, data, horario, local);
        listaCompromissos.add(compromisso);

        // Notificar o Adapter que o dado mudou
        adapter.notifyDataSetChanged();

        // Limpar os campos
        edtTitulo.setText("");
        edtData.setText("");
        edtHorario.setText("");
        edtLocal.setText("");
    }
}