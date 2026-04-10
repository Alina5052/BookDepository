package ru.oganisan.bookdepository;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import ru.oganisan.bookdepository.BookFragment;  // добавленный импорт

public class BookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        // Получаем менеджер фрагментов
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Ищем фрагмент по контейнеру
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        // Если фрагмент еще не добавлен, создаем новый и добавляем
        if (fragment == null) {
            fragment = new BookFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}