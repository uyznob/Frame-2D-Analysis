package com.j97.app.ui.input;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.j97.app.R;
import com.j97.app.data.local.AppDatabase;
import com.j97.app.data.local.MaterialModel;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

class MaterialAdapter extends ListAdapter<MaterialModel, MaterialAdapter.VH> {
  private final NumberFormat numberFormat = new DecimalFormat("#.##");

  protected MaterialAdapter() {
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

  public class VH extends RecyclerView.ViewHolder {

    private final TextView textViewID;
    private final TextView textViewType;
    private final TextView textViewE;
    private final TextView textViewA;
    private final TextView textViewI;

    public VH(@NonNull View itemView) {
      super(itemView);
      textViewID = itemView.findViewById(R.id.textViewID);
      textViewType = itemView.findViewById(R.id.textViewType);
      textViewE = itemView.findViewById(R.id.textViewE);
      textViewA = itemView.findViewById(R.id.textViewA);
      textViewI = itemView.findViewById(R.id.textViewI);
    }

    public void bind(MaterialModel item, int index) {
      textViewID.setText(String.valueOf(index + 1));
      textViewType.setText(item.getType());

      textViewE.setText(numberFormat.format(item.getE()));
      textViewA.setText(numberFormat.format(item.getA()));
      textViewI.setText(numberFormat.format(item.getI()));
    }
  }
}

public class ViewMaterialsActivity extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_materials);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    RecyclerView recyclerView = findViewById(R.id.recycler_view);
    View fab = findViewById(R.id.fab);

    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent toDefineActivity = new Intent(ViewMaterialsActivity.this, MaterialDefineActivity.class);
        startActivity(toDefineActivity);
      }
    });
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

    final MaterialAdapter adapter = new MaterialAdapter();
    recyclerView.setAdapter(adapter);

    AppDatabase
        .getDatabase(this).materialDao()
        .getAllMaterialModels()
        .observe(this, new Observer<List<MaterialModel>>() {
          @Override
          public void onChanged(List<MaterialModel> materialModels) {
            adapter.submitList(materialModels);
          }
        });


  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
