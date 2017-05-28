package com.example.brother.mtit_assignment_final;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

public class MainActivity extends AppCompatActivity {

    private Button btnSearch;
    private EditText txtSearch;
    private String kioskVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSearch = (Button) findViewById(R.id.btnSearch);
        txtSearch = (EditText) findViewById(R.id.txtSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = txtSearch.getText().toString();
                if (value.equals("") || value.isEmpty())
                    Toast.makeText(getApplicationContext(), "Enter a Kiosk name to find location", Toast.LENGTH_SHORT).show();
                else {
                    kioskVal = value;
                    Toast.makeText(getApplicationContext(), "Entered : " + kioskVal, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
