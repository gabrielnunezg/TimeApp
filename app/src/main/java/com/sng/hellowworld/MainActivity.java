package com.sng.hellowworld;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDate(View view) {
        TextView helloDate=(TextView)findViewById(R.id.hello_label);
        helloDate.setText(currentDate + System.getProperty("line.separator") + currentTime);
    }
}