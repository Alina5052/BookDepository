package ru.oganisan.bookdepository;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;

public class BookFragment extends Fragment {

    private static final String TAG = "BookFragment";
    private static final String ARG_BOOK_ID = "book_id";

    private Book mBook;
    private EditText mTitleField;
    private CheckBox mReadedCheckBox;

    public static BookFragment newInstance(UUID bookId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_BOOK_ID, bookId);
        BookFragment fragment = new BookFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() вызов");

        UUID bookId = (UUID) getArguments().getSerializable(ARG_BOOK_ID);
        mBook = BookLab.get(getActivity()).getBook(bookId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_book, container, false);

        mTitleField = v.findViewById(R.id.book_title);
        mTitleField.setText(mBook.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBook.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Вывод даты (если есть поле)
        TextView dateTextView = v.findViewById(R.id.book_date);
        if (dateTextView != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            dateTextView.setText(sdf.format(mBook.getDate()));
        }

        mReadedCheckBox = v.findViewById(R.id.book_readed);
        mReadedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mBook.setReaded(isChecked);
            }
        });

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() вызван");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() вызван");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() вызван");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() вызван");
        if (mBook.getTitle() != null && !mBook.getTitle().isEmpty()) {
            Log.d(TAG, "Сохраненное название: " + mBook.getTitle());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView() вызван");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() вызван");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach() вызван");
    }
}