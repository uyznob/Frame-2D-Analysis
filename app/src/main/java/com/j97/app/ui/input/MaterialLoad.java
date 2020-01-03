package com.j97.app.ui.input;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.j97.app.R;

public class MaterialLoad extends Fragment implements View.OnClickListener {
    private static final String TAG = "MATERIAL_LOAD";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.material_load_layout, container, false);

        ImageButton recButton = view.findViewById(R.id.rectImage);
        ImageButton circButton = view.findViewById(R.id.circImage);
        ImageButton pipeButton = view.findViewById(R.id.pipeImage);
        ImageButton tubeButton = view.findViewById(R.id.tubeImage);
        ImageButton ibeamButton = view.findViewById(R.id.ibeamImage);
        ImageButton channelButton = view.findViewById(R.id.channelImage);
        ImageButton doubleangleButton = view.findViewById(R.id.doubleangleImage);

        recButton.setOnClickListener(this);
        circButton.setOnClickListener(this);
        pipeButton.setOnClickListener(this);
        tubeButton.setOnClickListener(this);
        ibeamButton.setOnClickListener(this);
        channelButton.setOnClickListener(this);
        doubleangleButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rectImage:
                Intent rectangleScreenIntent = new Intent(v.getContext(), Rectangle.class);
                startActivity(rectangleScreenIntent);
                break;
            case R.id.circImage:
                Intent circleScreenIntent = new Intent(v.getContext(), Circle.class);
                startActivity(circleScreenIntent);
                break;
            case R.id.pipeImage:
                Intent pipeScreenIntent = new Intent(v.getContext(), Pipe.class);
                startActivity(pipeScreenIntent);
                break;
            case R.id.tubeImage:
                Intent tubeScreenIntent = new Intent(v.getContext(), Tube.class);
                startActivity(tubeScreenIntent);
                break;
            case R.id.ibeamImage:
                Intent ibeamScreenIntent = new Intent(v.getContext(), Ibeam.class);
                startActivity(ibeamScreenIntent);
                break;
            case R.id.channelImage:
                Intent channelScreenIntent = new Intent(v.getContext(), Channel.class);
                startActivity(channelScreenIntent);
                break;
            case R.id.doubleangleImage:
                Intent doubleangleScreenIntent = new Intent(v.getContext(), Doubleangle.class);
                startActivity(doubleangleScreenIntent);
                break;
            default:
                break;
        }
    }

}
