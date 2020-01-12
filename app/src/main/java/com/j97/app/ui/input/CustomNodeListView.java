package com.j97.app.ui.input;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.j97.app.R;

public class CustomNodeListView extends ArrayAdapter<String> {

  private String[] nodeTemplateName;
  private String[] nodeTemplateDescription;
  private Integer[] nodeTemplateImageId;
  private Activity context;

  public CustomNodeListView(Activity context, String[] nodeTemplateName, String[] nodeTemplateDescription, Integer[] nodeTemplateImageId) {
    super(context, R.layout.node_layout_item, nodeTemplateName);

    this.context = context;
    this.nodeTemplateName = nodeTemplateName;
    this.nodeTemplateDescription = nodeTemplateDescription;
    this.nodeTemplateImageId = nodeTemplateImageId;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    View r = convertView;
    ViewHolder viewHolder = null;
    if (r == null) {
      LayoutInflater layoutInflater = context.getLayoutInflater();
      r = layoutInflater.inflate(R.layout.node_layout_item, null, true);
      viewHolder = new ViewHolder(r);
      r.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder) r.getTag();

    }
    viewHolder.ivNodeTemplate.setImageResource(nodeTemplateImageId[position]);
    viewHolder.tvName.setText(nodeTemplateName[position]);
    viewHolder.tvDescription.setText(nodeTemplateDescription[position]);

    return r;
  }

  class ViewHolder {
    TextView tvName;
    TextView tvDescription;
    ImageView ivNodeTemplate;

    ViewHolder(View v) {
      tvName = v.findViewById(R.id.tvName);
      tvDescription = v.findViewById(R.id.tvDescription);
      ivNodeTemplate = v.findViewById(R.id.ivNodeTemplate);
    }
  }
}
