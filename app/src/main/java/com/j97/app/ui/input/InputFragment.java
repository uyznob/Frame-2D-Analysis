package com.j97.app.ui.input;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.j97.app.R;
import com.j97.app.data.local.AppDatabase;
import com.j97.app.data.local.MaterialModel;

import java.util.List;

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
    final Button buttonLoadMaterial = view.findViewById(R.id.button_load_material);
    final Button buttonViewMaterial = view.findViewById(R.id.button_view_material);


    final Button buttonDefineNode = view.findViewById(R.id.button_define_node);
    final Button buttonLoadNode = view.findViewById(R.id.button_load_node);
    final Button buttonDefineElement = view.findViewById(R.id.button_define_element);
    final Button buttonLoadElement = view.findViewById(R.id.button_load_element);
    final Button buttonDefineForce = view.findViewById(R.id.button_define_force);
    final Button buttonLoadForce = view.findViewById(R.id.button_load_force);
    final Button buttonDefineBoundary = view.findViewById(R.id.button_define_boundary);
    final Button buttonLoadBoundary = view.findViewById(R.id.button_load_boundary);

    buttonDefineMaterial.setOnClickListener(this);
    buttonLoadMaterial.setOnClickListener(this);
    buttonViewMaterial.setOnClickListener(this);
    buttonDefineNode.setOnClickListener(this);
    buttonLoadNode.setOnClickListener(this);
    buttonDefineElement.setOnClickListener(this);
    buttonLoadElement.setOnClickListener(this);
    buttonDefineForce.setOnClickListener(this);
    buttonLoadForce.setOnClickListener(this);
    buttonDefineBoundary.setOnClickListener(this);
    buttonLoadBoundary.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.button_define_material:
        Intent toMaterialDefine = new Intent(v.getContext(), MaterialDefineActivity.class);
        startActivity(toMaterialDefine);
        break;
      case R.id.button_load_material:
        Intent materialLoadScreenIntent = new Intent(v.getContext(), MaterialLoad.class);
        startActivity(materialLoadScreenIntent);
        break;
      case R.id.button_view_material:
        Intent toViewMaterials = new Intent(v.getContext(), ViewMaterialsActivity.class);
        startActivity(toViewMaterials);
        break;
      case R.id.button_define_node:
        break;
      case R.id.button_load_node:
        break;
      case R.id.button_define_element:
        break;
      case R.id.button_load_element:
        break;
      case R.id.button_define_force:
        break;
      case R.id.button_load_force:
        break;
      case R.id.button_define_boundary:
        break;
      case R.id.button_load_boundary:
        break;
    }
  }
}
