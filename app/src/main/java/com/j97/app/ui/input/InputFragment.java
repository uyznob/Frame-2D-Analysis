package com.j97.app.ui.input;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.j97.app.R;

public class InputFragment extends Fragment implements View.OnClickListener {
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_input, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    final Button buttonDefineMaterial = view.findViewById(R.id.button_define_material);
    final Button buttonViewMaterial = view.findViewById(R.id.button_view_material);


    final Button buttonDefineNode = view.findViewById(R.id.button_define_node);
    final Button buttonViewNode = view.findViewById(R.id.button_define_node);
    final Button buttonDefineElement = view.findViewById(R.id.button_define_element);
    final Button buttonViewElement = view.findViewById(R.id.button_define_element);
    final Button buttonDefineForce = view.findViewById(R.id.button_define_force);
    final Button buttonViewForce = view.findViewById(R.id.button_define_force);
    final Button buttonDefineBoundary = view.findViewById(R.id.button_define_boundary);
    final Button buttonViewBoundary = view.findViewById(R.id.button_define_boundary);

    buttonDefineMaterial.setOnClickListener(this);
    buttonViewMaterial.setOnClickListener(this);
    buttonDefineNode.setOnClickListener(this);
    buttonViewNode.setOnClickListener(this);
    buttonDefineElement.setOnClickListener(this);
    buttonViewElement.setOnClickListener(this);
    buttonDefineForce.setOnClickListener(this);
    buttonViewForce.setOnClickListener(this);
    buttonDefineBoundary.setOnClickListener(this);
    buttonViewBoundary.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.button_define_material:
        Intent materialDefineScreenIntent = new Intent(v.getContext(), MaterialDefine.class);
        startActivity(materialDefineScreenIntent);
        break;
      case R.id.button_view_material:
        Intent toViewMaterials = new Intent(v.getContext(), ViewMaterialsActivity.class);
        startActivity(toViewMaterials);
        break;
      case R.id.button_define_node:
        break;
      case R.id.button_view_node:
        break;
      case R.id.button_define_element:
        break;
      case R.id.button_view_element:
        break;
      case R.id.button_define_force:
        break;
      case R.id.button_view_force:
        break;
      case R.id.button_define_boundary:
        break;
      case R.id.button_view_boundary:
        break;
    }
  }
}
