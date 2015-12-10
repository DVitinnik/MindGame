package game.MindGame;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Switch;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * Created by Maxis on 20-Oct-15.
 */
public class SettingsActivity extends Activity {
    String[] data = {"Ловля точки","Подсчет", "Выбор цвета"};
    RadioGroup radioGroup;
    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;
    RadioButton button1;
    RadioButton button2;
    RadioButton button3;
    String preferencesName;
    public static Context contextOfApplication;


    public static Context getContextOfApplication(){
        return contextOfApplication;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        getActionBar().hide();
        // чтобы приложение постоянно имело портретную ориентацию
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //чтобы приложение было полноэкранным
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        contextOfApplication = getApplicationContext();
        radioGroup=(RadioGroup) findViewById(R.id.radioGroup);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);

        button1 = (RadioButton) radioGroup.getChildAt(0);
        button2 = (RadioButton) radioGroup.getChildAt(2);
        button3 = (RadioButton) radioGroup.getChildAt(5);
        button1.setChecked(true);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter);
        // заголовок
        spinner1.setPrompt("Выберите уровень");
        // выделяем элемент
        spinner1.setSelection(0);
        // устанавливаем обработчик нажатия
        spinner2.setAdapter(adapter);
        spinner2.setPrompt("Выберите уровень");
        spinner2.setSelection(1);

        spinner3.setAdapter(adapter);
        spinner3.setPrompt("Выберите уровень");
        spinner3.setSelection(2);
        spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getBaseContext(), "Выбран уровень " + data[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // показываем позиция нажатого элемента

                if (spinner2.getSelectedItemPosition() == spinner3.getSelectedItemPosition())
                    if (spinner3.getSelectedItemPosition() != 0) {
                        spinner2.setSelection(0);
                    } else {
                        spinner2.setSelection(spinner3.getSelectedItemPosition() + 1);
                    }
                // Toast.makeText(getBaseContext(), "Выбран уровень " + data[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        // третье выпадающее меню
        spinner3.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (spinner2.getSelectedItemPosition() == spinner3.getSelectedItemPosition())
                    if (spinner2.getSelectedItemPosition() != 0) {
                        spinner3.setSelection(0);
                    } else
                        spinner3.setSelection(spinner2.getSelectedItemPosition() + 1);
                // Toast.makeText(getBaseContext(), "Выбран уровень " + data[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

    }
    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences settings;
        settings =PreferenceManager.getDefaultSharedPreferences(contextOfApplication);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        // Запоминаем данные
        editor.putBoolean("Button 1",button1.isChecked());
        editor.putBoolean("Button 2",button2.isChecked());
        editor.putBoolean("Button 3",button3.isChecked());
        editor.putInt("Spinner 1",spinner1.getSelectedItemPosition());
        editor.putInt("Spinner 2",spinner2.getSelectedItemPosition());
        editor.putInt("Spinner 3",spinner3.getSelectedItemPosition());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

       // SharedPreferences settings = getSharedPreferences("answers", 0);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(contextOfApplication);
        boolean b1checked = settings.getBoolean("Button 1",false);
        boolean b2checked = settings.getBoolean("Button 2",false);
        boolean b3checked = settings.getBoolean("Button 3",false);

        if (b1checked)
            button1.toggle();
        if (b2checked)
            button2.toggle();
        if (b3checked)
            button3.toggle();
        int spin1 = settings.getInt("Spinner 1",0);
        int spin2 = settings.getInt("Spinner 2",1);
        int spin3 = settings.getInt("Spinner 3",2);
        spinner1.setSelection(spin1);
        spinner2.setSelection(spin2);
        spinner3.setSelection(spin3);
    }
}


