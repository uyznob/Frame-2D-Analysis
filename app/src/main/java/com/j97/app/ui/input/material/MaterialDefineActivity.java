package com.j97.app.ui.input.material;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.j97.app.R;

public class MaterialDefineActivity extends AppCompatActivity implements View.OnClickListener {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.material_define_activity);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    final View recButton = findViewById(R.id.rectImage);
    final View circButton = findViewById(R.id.circImage);
    final View pipeButton = findViewById(R.id.pipeImage);
    final View tubeButton = findViewById(R.id.tubeImage);
    final View ibeamButton = findViewById(R.id.ibeamImage);
    final View channelButton = findViewById(R.id.channelImage);
    final View doubleangleButton = findViewById(R.id.doubleangleImage);
    final View customButton = findViewById(R.id.customImage);

    recButton.setOnClickListener(this);
    circButton.setOnClickListener(this);
    pipeButton.setOnClickListener(this);
    tubeButton.setOnClickListener(this);
    ibeamButton.setOnClickListener(this);
    channelButton.setOnClickListener(this);
    doubleangleButton.setOnClickListener(this);
    customButton.setOnClickListener(this);
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
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.rectImage:
        Intent rectangleScreenIntent = new Intent(v.getContext(), RectangleActivity.class);
        startActivity(rectangleScreenIntent);
        break;
      case R.id.circImage:
        Intent circleScreenIntent = new Intent(v.getContext(), CircleActivity.class);
        startActivity(circleScreenIntent);
        break;
      case R.id.pipeImage:
        Intent pipeScreenIntent = new Intent(v.getContext(), PipeActivity.class);
        startActivity(pipeScreenIntent);
        break;
      case R.id.tubeImage:
        Intent tubeScreenIntent = new Intent(v.getContext(), TubeActivity.class);
        startActivity(tubeScreenIntent);
        break;
      case R.id.ibeamImage:
        Intent ibeamScreenIntent = new Intent(v.getContext(), IbeamActivity.class);
        startActivity(ibeamScreenIntent);
        break;
      case R.id.channelImage:
        Intent channelScreenIntent = new Intent(v.getContext(), ChannelActivity.class);
        startActivity(channelScreenIntent);
        break;
      case R.id.doubleangleImage:
        Intent doubleangleScreenIntent = new Intent(v.getContext(), DoubleAngleActivity.class);
        startActivity(doubleangleScreenIntent);
        break;
      case R.id.customImage:
        Intent customScreenIntent = new Intent(v.getContext(), SectionCustomActivity.class);
        startActivity(customScreenIntent);
        break;
      default:
        break;
    }
  }

}
