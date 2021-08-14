package kohli.androidinterview;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.w3c.dom.Text;

import java.util.Locale;

public class Simple_Question extends AppCompatActivity implements View.OnClickListener {

     TextView tvquestion, tvanswer, tvtotallength_yy, tvpresentindex_xx;

    ImageButton bleft, bshow, bright;

    String[] simple_question, simple_answers;

    int index;

    private static final String default_answer = "Press A Button for the Answer";

//  Variables and object of text to speech.
    TextToSpeech ttsobject;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);

        // Codes to add action bar
        LinearLayout question_ll = (LinearLayout) findViewById(R.id.questions_page_titlebar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.questions_title_bar);

        Button bspeak = (Button) findViewById(R.id.bspeak);
        Button bstop_mute = (Button) findViewById(R.id.bstop_mute);
        TextView tv_category = (TextView) findViewById(R.id.tv_questions_titlebar);
        tv_category.setText("Simple Questions");


        // Initialization of TextView
        tvquestion = (TextView) findViewById(R.id.tvquestion);
        tvanswer = (TextView) findViewById(R.id.tvanswer);
        tvpresentindex_xx = (TextView) findViewById(R.id.tvxx);
        tvtotallength_yy = (TextView) findViewById(R.id.tvyy);

        // Initialization of Buttons
        bleft = (ImageButton) findViewById(R.id.bleft);
        bshow = (ImageButton) findViewById(R.id.bshowanswer);
        bright = (ImageButton) findViewById(R.id.bright);

        //Importing the string array from Values folder
        simple_question = getResources().getStringArray(R.array.simple_ques);
        simple_answers = getResources().getStringArray(R.array.simple_ans);

        // Onclick Listener for 3 Buttons and also Speak, Mute Buttons
        bleft.setOnClickListener(this);
        bshow.setOnClickListener(this);
        bright.setOnClickListener(this);
        bspeak.setOnClickListener(this);
        bstop_mute.setOnClickListener(this);


        //Setting value to our variables and 4 TextViews
        index = 0;
        tvquestion.setText(simple_question[index]);
        tvanswer.setText(default_answer);
        tvpresentindex_xx.setText(String.valueOf(index+1));
        tvtotallength_yy.setText(String.valueOf("/"+simple_question.length));

//      TTS object and listener initialization
        ttsobject = new TextToSpeech(Simple_Question.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if(status == TextToSpeech.SUCCESS){

                    result = ttsobject.setLanguage(Locale.US);

                }else{

                    Toast.makeText(getApplicationContext(), "Feature not supported in your device", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bleft:
                tvanswer.setText(default_answer);
                index--;

                if(index == -1){
                    index = simple_question.length-1;
                }
                tvquestion.setText(simple_question[index]);

                tvpresentindex_xx.setText(String.valueOf(index+1));
                break;

            case R.id.bshowanswer:

                tvanswer.setText(simple_answers[index]);
                break;

            case R.id.bright:
                tvanswer.setText(default_answer);
                index++;

                if(index == simple_question.length){
                    index = 0;
                }
                tvquestion.setText(simple_question[index]);

                tvpresentindex_xx.setText(String.valueOf(index+1));
                break;

            case  R.id.bspeak:

                if(result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){

                    Toast.makeText(getApplicationContext(), "Feature not supported in your device", Toast.LENGTH_SHORT).show();

                }else{

                    if(tvanswer.getText().toString().equals(default_answer)){

                    }else
                    ttsobject.speak( simple_answers[index], TextToSpeech.QUEUE_FLUSH,null);

                }

                break;

            case R.id.bstop_mute:

                if(ttsobject != null){
                    ttsobject.stop();
                }

                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(ttsobject != null){
            ttsobject.stop();
            ttsobject.shutdown();
        }

    }
}
