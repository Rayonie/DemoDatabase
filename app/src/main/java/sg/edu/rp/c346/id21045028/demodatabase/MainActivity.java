package sg.edu.rp.c346.id21045028.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert;
    Button btnGetTask;
    TextView tvResults;
    ListView lv;
    EditText editDate;
    EditText editTask;
    ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editDate = findViewById(R.id.editDate);
        editTask = findViewById(R.id.editTask);
        btnInsert = findViewById(R.id.btnInsert);
        toggleButton = findViewById(R.id.toggleButton);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBhelper db = new DBhelper(MainActivity.this);

                String date = editDate.getText().toString();
                String tasks = editTask.getText().toString();
                // Insert a task
                Toast.makeText(getApplicationContext(),"Before insert task " + tasks + " " + date,Toast.LENGTH_SHORT).show();
                db.insertTask(tasks, date);
                Toast.makeText(getApplicationContext(),"After insert task " + tasks + " " + date,Toast.LENGTH_SHORT).show();

            }
        });

        btnGetTask = findViewById(R.id.buttonGetTask);
        tvResults = findViewById(R.id.textViewResults);
        lv = findViewById(R.id.lv);
        btnGetTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBhelper db = new DBhelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);

                DBhelper db2 = new DBhelper(MainActivity.this);
                if(toggleButton.isChecked()){
                    ArrayList<Task> al = db2.getTasksASC();
                    db2.close();
                    ArrayAdapter aa = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,al);
                    lv.setAdapter(aa);
                }else{
                    ArrayList<Task> al = db2.getTasksDESC();
                    db2.close();
                    ArrayAdapter aa = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,al);
                    lv.setAdapter(aa);
                }



            }
        });




    }
}