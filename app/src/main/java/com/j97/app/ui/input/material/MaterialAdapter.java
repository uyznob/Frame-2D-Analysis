package com.j97.app.ui.input.material;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.j97.app.R;
import com.j97.app.data.local.MaterialModel;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MaterialAdapter extends ListAdapter<MaterialModel, MaterialAdapter.VH> {
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
