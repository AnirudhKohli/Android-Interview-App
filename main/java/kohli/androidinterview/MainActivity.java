package kohli.androidinterview;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    
    Button bsimple, btough, bseeOtherApps, brateApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Codes to add action bar
        ConstraintLayout front_ll = (ConstraintLayout) findViewById(R.id.front_page_titlebar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.frontpage_title_bar);

        bsimple = (Button) findViewById(R.id.btnsq);
        btough = (Button) findViewById(R.id.btntq);
        bseeOtherApps = (Button) findViewById(R.id.btnseeother);
        brateApps = (Button) findViewById(R.id.btnrate);

        bsimple.setOnClickListener(this);
        btough.setOnClickListener(this);
        bseeOtherApps.setOnClickListener(this);
        brateApps.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnsq:

                Intent i = new Intent(MainActivity.this, Simple_Question.class);
                startActivity(i);

                break;
            case R.id.btntq:

                Intent j = new Intent(MainActivity.this, Tough_Question.class);
                startActivity(j);

                break;
            case R.id.btnseeother:

                break;
            case R.id.btnrate:

//                break;
        }
    }
}