package sg.edu.rp.c346.id22024852.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spn;
    EditText editText;
    Button add;
    Button delete;
    Button clear;
    ListView lvToDo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spn = findViewById(R.id.ToDospinner);
        editText = findViewById(R.id.editTextTasks);
        add = findViewById(R.id.buttonAdd);
        delete = findViewById(R.id.buttonDelete);
        clear = findViewById(R.id.buttonClear);
        lvToDo = findViewById(R.id.lvTasks);

        ArrayList<String> alTodo =new ArrayList<String>();
        ArrayAdapter aaTodo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,alTodo);
        lvToDo.setAdapter(aaTodo);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        add.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    String input = editText.getText().toString();
                                    if (!input.equalsIgnoreCase("")){
                                    alTodo.add(input);
                                    aaTodo.notifyDataSetChanged();
                                    } else {
                                        Toast.makeText(MainActivity.this, "You have entered nothing", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        clear.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!alTodo.isEmpty()){
                                    alTodo.clear();
                                    aaTodo.notifyDataSetChanged();}
                                else {
                                    Toast.makeText(MainActivity.this, "You have nothing to clear", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        if (!alTodo.isEmpty()){
                            clear.setEnabled(true);
                            add.setEnabled(true);
                            delete.setEnabled(false);

                        } else {
                            clear.setEnabled(true);
                            add.setEnabled(true);
                            delete.setEnabled(false);
                        }
                        break;

                    case 1:
                        editText.setHint("Type the index of the task to be removed");
                        delete.setEnabled(true);
                        clear.setEnabled(true);
                        add.setEnabled(false);

                        delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String input = editText.getText().toString().trim();
                                if (input.isEmpty()) {
                                    Toast.makeText(MainActivity.this, "You don't have anything to delete", Toast.LENGTH_SHORT).show();
                                } else {
                                    int index = Integer.parseInt(input);
                                    if (index >= 0 && index < alTodo.size()) {
                                        alTodo.remove(index);
                                        aaTodo.notifyDataSetChanged();
                                        editText.setText("");
                                    } else {
                                        Toast.makeText(MainActivity.this, "Wrong Index Number", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });

                        clear.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!alTodo.isEmpty()){
                                alTodo.clear();
                                aaTodo.notifyDataSetChanged();}
                                else {
                                    Toast.makeText(MainActivity.this, "You have nothing to clear", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}