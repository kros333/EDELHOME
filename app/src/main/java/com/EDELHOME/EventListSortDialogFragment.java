package com.EDELHOME;


import android.content.DialogInterface;
import android.view.View.OnClickListener;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import androidx.fragment.app.DialogFragment;
import androidx.annotation.NonNull;
public class EventListSortDialogFragment extends DialogFragment implements OnClickListener
{
    String[] sort_method = {"По дате (ближайшие)", "По дате (с конца)", "По дате добавления (сначала новые)", "По дате добавления (сначала старые)"};
    String[] filter_method = {"По дате (ближайшие)", "По дате (с конца)", "По дате добавления (сначала новые)", "По дате добавления (сначала старые)"};

    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Title!");
        View v = inflater.inflate(R.layout.event_list_sort_dialog, null);
        Spinner sort = v.findViewById(R.id.sort);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> sort_adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, sort_method);
        // Определяем разметку для использования при выборе элемента
        sort_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        sort.setAdapter(sort_adapter);

        Spinner filter = v.findViewById(R.id.filter);
        ArrayAdapter<String> filter_adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, filter_method);
        filter_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter.setAdapter(sort_adapter);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                // Получаем выбранный объект
                String item = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        };
        sort.setOnItemSelectedListener(itemSelectedListener);
        filter.setOnItemSelectedListener(itemSelectedListener);
        v.findViewById(R.id.ok).setOnClickListener(this);
        v.findViewById(R.id.cancel).setOnClickListener(this);
        return v;
    }

    public void onClick(View v) {
        dismiss();
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }
    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }
}
