package com.EDELHOME;


import android.content.Context;
import android.content.DialogInterface;
import android.view.View.OnClickListener;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.annotation.NonNull;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class EventListSortDialogFragment extends DialogFragment implements OnClickListener
{

    FilterDialogInterface filterDialogInterface;
    EventListForOfficeActivity callBackActivity;
    String[] sort_method = {"По возрастанию даты", "По убыванию даты"};
    String[] filter_method = {"Все категории", "Замеры", "Мебель", "Окна", "Входные двери", "Межкомнатные двери", "Кухни"};
    Spinner sort;
    Spinner filter;
    SwitchMaterial archive;
    int f;
    int s;
    boolean a;
    public EventListSortDialogFragment(FilterDialogInterface filterDialogInterface, int f, int s, boolean a)
    {
        this.filterDialogInterface = filterDialogInterface;
        this.f = f;
        this.s = s;
        this.a = a;
    }
    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        callBackActivity = new EventListForOfficeActivity();
        View v = inflater.inflate(R.layout.event_list_sort_dialog, null);

        archive = v.findViewById(R.id.archive);
        archive.setOnCheckedChangeListener(null);
        archive.setChecked(a);
        archive.setOnCheckedChangeListener(this::onCheckedChanged);


        sort = v.findViewById(R.id.sort);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> sort_adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, sort_method);
        // Определяем разметку для использования при выборе элемента
        sort_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        sort.setAdapter(sort_adapter);
        sort.setSelection(s - 1);

        filter = v.findViewById(R.id.filter);
        ArrayAdapter<String> filter_adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, filter_method);
        filter_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter.setAdapter(filter_adapter);
        filter.setSelection(f);
        AdapterView.OnItemSelectedListener s_itemSelectedListener = new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                // Получаем выбранный объект
                String item = (String)parent.getItemAtPosition(position);
//                switch (item)
//                {
////                    case ("Все категории"):
////                        getActivity().
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        };
        AdapterView.OnItemSelectedListener f_itemSelectedListener = new AdapterView.OnItemSelectedListener()
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
        sort.setOnItemSelectedListener(s_itemSelectedListener);
        filter.setOnItemSelectedListener(f_itemSelectedListener);
        v.findViewById(R.id.ok).setOnClickListener(onOk);
        v.findViewById(R.id.reset).setOnClickListener(onReset);
        v.findViewById(R.id.cancel).setOnClickListener(this);
        return v;
    }
    OnClickListener onOk = new OnClickListener() {
        @Override
        public void onClick(View v) {
            f = filter.getSelectedItemPosition();
            s = sort.getSelectedItemPosition() + 1;
//            switch (filter.getSelectedItem().toString())
//                {
//                    case ("Все категории"):
//                        f = 0;
//                        break;
//                    case ("Замеры"):
//                        f = 1;
//                        break;
//                    case ("Мебель"):
//                        f = 2;
//                        break;
//                    case ("Окна"):
//                        f = 3;
//                        break;
//                    case ("Входные двери"):
//                        f = 4;
//                        break;
//                    case ("Межкомнатные двери"):
//                        f = 5;
//                        break;
//                    case ("Кухни"):
//                        f = 6;
//                        break;
//                    default:
//                        f = 0;
//                        break;
//                }
//            switch (filter.getSelectedItem().toString())
//            {
//                case ("По возрастанию даты)"):
//                    s = 1;
//                    break;
//                case ("По убыванию даты"):
//                    s = 2;
//                    break;
//                default:
//                    s = 1;
//                    break;
//            }
            dismiss();
            filterDialogInterface.onSendData(f, s, a);
        }
    };
    OnClickListener onReset = new OnClickListener() {
        @Override
        public void onClick(View v) {
            f = 0;
            s = 1;
            a = false;
            dismiss();
            filterDialogInterface.onSendData(f, s, a);
        }
    };
//    OnClickListener onCancel = new OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//        }
//    };
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
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
        a = isChecked;
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        try {
//            filterDialogInterface = (FilterDialogInterface) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString()
//                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
//        }
//    }
}
