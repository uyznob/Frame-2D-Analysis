package com.j97.app.ui.input.node;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.j97.app.R;
import com.j97.app.data.local.AppDatabase;
import com.j97.app.data.local.NodeModel;

import java.text.DecimalFormat;
import java.text.NumberFormat;

class NodeAdapter extends ListAdapter<NodeModel, NodeAdapter.VH> {
  private final Listener listener;
  private final NumberFormat numberFormat = new DecimalFormat("#.###");

  protected NodeAdapter(Listener listener) {
    super(new DiffUtil.ItemCallback<NodeModel>() {
      @Override
      public boolean areItemsTheSame(@NonNull NodeModel oldItem, @NonNull NodeModel newItem) {
        return oldItem.getId() == newItem.getId();
      }

      @Override
      public boolean areContentsTheSame(@NonNull NodeModel oldItem, @NonNull NodeModel newItem) {
        return oldItem.equals(newItem);
      }
    });
    this.listener = listener;
  }

  @NonNull
  @Override
  public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.node_layout_item, parent, false);
    return new VH(view);
  }

  @Override
  public void onBindViewHolder(@NonNull VH holder, int position) {
    holder.bind(getItem(position), position);
  }

  public interface Listener {
    void delete(NodeModel nodeModel);

    void edit(NodeModel nodeModel);
  }

  public class VH extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView tvNodeId;
    private final TextView tvCoorX;
    private final TextView tvCoorY;
    private final ImageView imageEdit;
    private final ImageView imageDelete;

    public VH(@NonNull View itemView) {
      super(itemView);
      tvNodeId = itemView.findViewById(R.id.tvNodeId);
      tvCoorX = itemView.findViewById(R.id.tvCoorX);
      tvCoorY = itemView.findViewById(R.id.tvCoorY);
      imageEdit = itemView.findViewById(R.id.imageEdit);
      imageDelete = itemView.findViewById(R.id.imageDelete);

      imageEdit.setOnClickListener(this);
      imageDelete.setOnClickListener(this);
    }

    public void bind(NodeModel item, int index) {
      tvNodeId.setText(String.valueOf(index + 1));

      tvCoorX.setText(numberFormat.format(item.getCoorX()));
      tvCoorY.setText(numberFormat.format(item.getCoorY()));
    }

    @Override
    public void onClick(View v) {
      int adapterPosition = getAdapterPosition();
      if (adapterPosition == RecyclerView.NO_POSITION) {
        return;
      }
      NodeModel item = getItem(adapterPosition);

      if (v.getId() == R.id.imageEdit) {
        listener.edit(item);
      } else if (v.getId() == R.id.imageDelete) {
        listener.delete(item);
      }
    }
  }
}

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
      // TO-DO: Add new custom nodes
    });

    fabDeleteAll.setOnClickListener(v -> {
      // TO-DO: Delete all nodes
    });

    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

      public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy > 0) {
          fabAdd.hide();
          fabDeleteAll.hide();
        } else {
          fabAdd.show();
          fabDeleteAll.hide();
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
    // TO-DO: Edit data
  }
}
