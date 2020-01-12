package com.j97.app.ui.input.node;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.j97.app.R;
import com.j97.app.data.local.AppDatabase;
import com.j97.app.data.local.NodeModel;

public class ViewNodesActivity extends AppCompatActivity implements NodeAdapter.Listener {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_nodes);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    RecyclerView recyclerView = findViewById(R.id.recycler_view);
    FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
    FloatingActionButton fabDeleteAll = findViewById(R.id.fabDeleteAll);

    fabAdd.setOnClickListener(v -> {
      Intent toNodeCustomActivity = new Intent(ViewNodesActivity.this, NodeCustomActivity.class);
      startActivity(toNodeCustomActivity);
    });

    fabDeleteAll.setOnClickListener(v -> {
      new AlertDialog.Builder(this)
              .setTitle("Delete")
              .setMessage("Delete All Nodes")
              .setPositiveButton("OK", (dialog, __) -> {
                dialog.dismiss();
                AppDatabase.getDatabase(this)
                        .nodeDao()
                        .deleteAll();
              })
              .setNegativeButton("Cancel", (dialog, __) -> dialog.dismiss())
              .show();
    });

    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

      public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy > 0) {
          fabAdd.hide();
          fabDeleteAll.hide();
        } else {
          fabAdd.show();
          fabDeleteAll.show();
        }
      }
    });
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

    final NodeAdapter adapter = new NodeAdapter(this);
    recyclerView.setAdapter(adapter);

    AppDatabase
        .getDatabase(this).nodeDao()
        .getAllNodeModels()
        .observe(this, adapter::submitList);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void delete(NodeModel nodeModel) {
    new AlertDialog.Builder(this)
        .setTitle("Delete")
        .setMessage("Delete Node(" + nodeModel.getCoorX() + "," + nodeModel.getCoorY() + ")")
        .setPositiveButton("OK", (dialog, __) -> {
          dialog.dismiss();
          AppDatabase.getDatabase(this)
              .nodeDao()
              .delete(nodeModel);
        })
        .setNegativeButton("Cancel", (dialog, __) -> dialog.dismiss())
        .show();
  }

  @Override
  public void edit(NodeModel nodeModel) {
    //TO-DO
  }
}
