package com.j97.app.ui.input;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.j97.app.data.local.MaterialModel;

import java.text.DecimalFormat;
import java.text.NumberFormat;

class MaterialAdapter extends ListAdapter<MaterialModel, MaterialAdapter.VH> {
  private final Listener listener;
  private final NumberFormat numberFormat = new DecimalFormat("#.##");

  protected MaterialAdapter(Listener listener) {
    super(new DiffUtil.ItemCallback<MaterialModel>() {
      @Override
      public boolean areItemsTheSame(@NonNull MaterialModel oldItem, @NonNull MaterialModel newItem) {
        return oldItem.getId() == newItem.getId();
      }

      @Override
      public boolean areContentsTheSame(@NonNull MaterialModel oldItem, @NonNull MaterialModel newItem) {
        return oldItem.equals(newItem);
      }
    });
    this.listener = listener;
  }

  @NonNull
  @Override
  public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.material_layout_item, parent, false);
    return new VH(view);
  }

  @Override
  public void onBindViewHolder(@NonNull VH holder, int position) {
    holder.bind(getItem(position), position);
  }

  public interface Listener {
    void delete(MaterialModel materialModel);

    void edit(MaterialModel materialModel);
  }

  public class VH extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView textViewID;
    private final TextView textViewType;
    private final TextView textViewE;
    private final TextView textViewA;
    private final TextView textViewI;
    private final ImageView imageEdit;
    private final ImageView imageDelete;

    public VH(@NonNull View itemView) {
      super(itemView);
      textViewID = itemView.findViewById(R.id.textViewID);
      textViewType = itemView.findViewById(R.id.textViewType);
      textViewE = itemView.findViewById(R.id.textViewE);
      textViewA = itemView.findViewById(R.id.textViewA);
      textViewI = itemView.findViewById(R.id.textViewI);
      imageEdit = itemView.findViewById(R.id.imageEdit);
      imageDelete = itemView.findViewById(R.id.imageDelete);

      imageEdit.setOnClickListener(this);
      imageDelete.setOnClickListener(this);
    }

    public void bind(MaterialModel item, int index) {
      textViewID.setText(String.valueOf(index + 1));
      textViewType.setText(item.getType());

      textViewE.setText(numberFormat.format(item.getE()));
      textViewA.setText(numberFormat.format(item.getA()));
      textViewI.setText(numberFormat.format(item.getI()));
    }

    @Override
    public void onClick(View v) {
      int adapterPosition = getAdapterPosition();
      if (adapterPosition == RecyclerView.NO_POSITION) {
        return;
      }
      MaterialModel item = getItem(adapterPosition);

      if (v.getId() == R.id.imageEdit) {
        listener.edit(item);
      } else if (v.getId() == R.id.imageDelete) {
        listener.delete(item);
      }
    }
  }
}

public class ViewMaterialsActivity extends AppCompatActivity implements MaterialAdapter.Listener {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_materials);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    RecyclerView recyclerView = findViewById(R.id.recycler_view);
    FloatingActionButton fab = findViewById(R.id.fab);

    fab.setOnClickListener(v -> {
      Intent toDefineActivity = new Intent(ViewMaterialsActivity.this, SectionCustom.class);
      startActivity(toDefineActivity);
    });
    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

      public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy > 0) {
          fab.hide();
        } else {
          fab.show();
        }
      }
    });
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

    final MaterialAdapter adapter = new MaterialAdapter(this);
    recyclerView.setAdapter(adapter);

    AppDatabase
        .getDatabase(this).materialDao()
        .getAllMaterialModels()
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
  public void delete(MaterialModel materialModel) {
    new AlertDialog.Builder(this)
        .setTitle("Delete")
        .setMessage("Delete " + materialModel.getType())
        .setPositiveButton("OK", (dialog, __) -> {
          dialog.dismiss();
          AppDatabase.getDatabase(this)
              .materialDao()
              .delete(materialModel);
        })
        .setNegativeButton("Cancel", (dialog, __) -> dialog.dismiss())
        .show();
  }

  @Override
  public void edit(MaterialModel materialModel) {
    if ("custom".equalsIgnoreCase(materialModel.getType())) {
      Intent intent = new Intent(this, SectionCustom.class);
      intent.putExtra("item", materialModel);
      startActivity(intent);
      return;
    }

    if (materialModel.getType().startsWith("R")) {
      Intent intent = new Intent(this, RectangleActivity.class);
      intent.putExtra("item", materialModel);
      startActivity(intent);
      return;
    }
  }
}
