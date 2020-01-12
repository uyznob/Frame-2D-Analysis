package com.j97.app.ui.input.node;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.j97.app.R;

public class NodeDefineActivity extends AppCompatActivity {
    private static final String TAG = "MATERIAL_LOAD";

    ListView listViewNode;
    String[] nodeTemplateName = {"Custom", "Beam 1", "Beam 2", "Beam 3", "Beam Frame 1", "Beam Frame 2", "Beam Frame 3", "Beam Frame 4",
            "Roof Frame 1", "Roof Frame 2", "Roof Frame 3", "Roof Frame 4", "Roof Frame 5", "Roof Frame 6",
            "Roof Frame 7", "Roof Frame 8", "Roof Frame 9", "Roof Frame 10", "Roof Frame 11", "Roof Frame 12",
            "Column Frame 1", "Column Frame 2", "Column Frame 3", "Column Frame 4", "Column Frame 5", "Column Frame 6", "Column Frame 7", "Column Frame 8",
            "Building Frame 1", "Building Frame 2", "Building Frame 3", "Building Frame 4", "Building Frame 5", "Building Frame 6",
            "Bridge Frame 1", "Bridge Frame 2", "Bridge Frame 3", "Bridge Frame 4", "Bridge Frame 5",
            "Collection 1", "Collection 2", "Collection 3", "Collection 4"};
    String[] nodeTemplateDescription = {"Defined by user", "Concentrated load", "Distributed load", "Arch geometry",
            "4 spans with '/' braces", "4 spans with '\\' braces", "4 spans with '/\\' braces", "4 spans with 'X' braces",
            "1 side with 2 spans", "1 side with 3 spans", "1 side with 4 spans", "2 sides with 4 spans", "2 sides with 6 spans", "2 sides with 8 spans",
            "1 side with 2 spans", "1 side with 3 spans", "1 side with 4 spans", "2 sides with 4 spans", "2 sides with 6 spans", "2 sides with 8 spans",
            "4 levels with '\\' braces", "4 levels with '/' braces", "4 levels with '/\\' braces", "4 levels with 'X' braces", "Pano support", "Traffic sign support", "Lift support 1", "Lift support 2",
            "1 span with straight roof beam", "2 span with straight roof beam", "1 sided triangle roof with 4 spans", "1 sided trapezoid roof with 4 spans", "2 sided triangle roof with 8 spans", "2 sided trapezoid roof with 8 spans",
            "Suspension-like bridge", "Arch geometry", "Upward bridge frame", "Downward bridge frame", "Hanger",
            "Bike", "Airplane section", "Tunnel holder", "Gate"};
    Integer[] nodeTemplateImageId = {R.drawable.customnode, R.drawable.beam_1, R.drawable.beam_2, R.drawable.beam_3, R.drawable.beam_frame_1, R.drawable.beam_frame_2, R.drawable.beam_frame_3, R.drawable.beam_frame_4,
            R.drawable.roof_frame_1, R.drawable.roof_frame_2, R.drawable.roof_frame_3, R.drawable.roof_frame_4, R.drawable.roof_frame_5, R.drawable.roof_frame_6,
            R.drawable.roof_frame_7, R.drawable.roof_frame_8, R.drawable.roof_frame_9, R.drawable.roof_frame_10, R.drawable.roof_frame_11, R.drawable.roof_frame_12,
            R.drawable.column_frame_1, R.drawable.column_frame_2, R.drawable.column_frame_3, R.drawable.column_frame_4, R.drawable.column_frame_5, R.drawable.column_frame_6, R.drawable.column_frame_7, R.drawable.column_frame_8,
            R.drawable.building_frame_1, R.drawable.building_frame_2, R.drawable.building_frame_3, R.drawable.building_frame_4, R.drawable.building_frame_5, R.drawable.building_frame_6,
            R.drawable.bridge_frame_1, R.drawable.bridge_frame_2, R.drawable.bridge_frame_3, R.drawable.bridge_frame_4, R.drawable.bridge_frame_5,
            R.drawable.collection_1, R.drawable.collection_2, R.drawable.collection_3, R.drawable.collection_4};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.node_define_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listViewNode = findViewById(R.id.listViewNode);
        CustomNodeListView customNodeListView = new CustomNodeListView(this, nodeTemplateName, nodeTemplateDescription, nodeTemplateImageId);
        listViewNode.setAdapter(customNodeListView);

        listViewNode.setOnItemClickListener((parent, view, position, id) ->
                {
                    Toast.makeText(NodeDefineActivity.this, nodeTemplateName[position] + " clicked", Toast.LENGTH_SHORT).show();

                    switch (position) {
                        case 0:
                            Intent toCustomNodes = new Intent(view.getContext(), NodeCustomActivity.class);
                            startActivity(toCustomNodes);
                            break;
                    }
                }
        );

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
