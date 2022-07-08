package com.example.wyw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ModalAdapter  extends ArrayAdapter<Modal> {

    public ModalAdapter(@NonNull Context context, ArrayList<Modal> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View currentItemView = convertView;

        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Modal currentPosition = getItem(position);

        TextView textView1 = currentItemView.findViewById(R.id.address_view);
        textView1.setText(currentPosition.getAddress());

        TextView textView2 = currentItemView.findViewById(R.id.phone_view);
        textView2.setText(currentPosition.getPhone());

        TextView textView3 = currentItemView.findViewById(R.id.requirement_view);
        textView3.setText(currentPosition.getRequirements());

        return currentItemView;
    }
}

