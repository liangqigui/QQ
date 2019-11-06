package com.example.liangqg.qq.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangqg.qq.R;
import com.example.liangqg.qq.layout.AddressLayout;
import com.example.liangqg.qq.layout.PersonTitle;
import com.example.liangqg.qq.layout.TimeLayout;

public class PersonActivity extends BaseActivity {
private Button editbutton,savebutton,cenceledit;
private ImageView person_head,person_head_edit;
private AddressLayout person_hometwon_city,person_location_city;
private TimeLayout person_birth_time;
private RadioGroup person_sex_radio;
private EditText person_autograph_edit,person_name_edit;
private TextView person_autograph_text,person_name_text,person_account_text,
        person_sex_text,person_birth_text,person_hometwon_text,person_location_text;
private PersonTitle persontitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
          init();
        person_head_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person_head_edit.setImageResource(R.drawable.img001);
            }
        });
        cenceledit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishVisibility();
            }
        });
        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             edit();
            }
        });
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               save();
            }
        });
    }
    private void edit(){
        editVisibility();
        String autograph=person_autograph_text.getText().toString();
        String name=person_name_text.getText().toString();
        String sex=person_sex_text.getText().toString();
        String birth=person_birth_text.getText().toString();
        String  hometwon =person_hometwon_text.getText().toString();
        String location=person_location_text.getText().toString();

        if(!birth.equals("")){
            String[] birthtime=birth.split("-");
            person_birth_time.initSelect(Integer.parseInt(birthtime[0]),
                    Integer.parseInt(birthtime[1]),Integer.parseInt(birthtime[2]));
        }
        if (!hometwon.equals("")){
            String[] hometwons=hometwon.split("-");
            person_hometwon_city.initSelect(hometwons[0],hometwons[1],hometwons[2]);
        }
        if(!location.equals("")){
            String[] locations=location.split("-");
            person_location_city.initSelect(locations[0],locations[1],locations[2]);
        }
        person_autograph_edit.setText(autograph);
        person_name_edit.setText(name);
        person_name_edit.setText(name);
        if(sex.equals("男"))
            person_sex_radio.check(R.id.person_sex_man);
        else
            person_sex_radio.check(R.id.person_sex_woman);
    }
    private void save(){
        finishVisibility();
        String autograph=person_autograph_edit.getText().toString();
        String name=person_name_edit.getText().toString();
        RadioButton rb = (RadioButton)PersonActivity.this.findViewById(person_sex_radio.getCheckedRadioButtonId());
        String sex=rb.getText().toString();
        Integer[] birth=person_birth_time.getBirth();
        String[] homeaddress=person_hometwon_city.getAddress();
        String[] localtionaddress=person_location_city.getAddress();
        person_name_text.setText(name);
        person_sex_text.setText(sex);
        person_birth_text.setText(birth[0]+"-"+birth[1]+"-"+birth[2]);
        person_hometwon_text.setText(homeaddress[0]+"-"+homeaddress[1]+"-"+homeaddress[2]);
        person_location_text.setText(localtionaddress[0]+"-"+localtionaddress[1]+"-"+localtionaddress[2]);
    }
    private void finishVisibility(){
        person_head_edit.setVisibility(View.INVISIBLE);
        person_head.setVisibility(View.VISIBLE);
        cenceledit.setVisibility(View.INVISIBLE);
        person_autograph_edit.setVisibility(View.INVISIBLE);
        person_name_edit.setVisibility(View.INVISIBLE);
        person_sex_radio.setVisibility(View.INVISIBLE);
        person_birth_time.setVisibility(View.INVISIBLE);
        person_hometwon_city.setVisibility(View.INVISIBLE);
        person_location_city.setVisibility(View.INVISIBLE);
        savebutton.setVisibility(View.INVISIBLE);
        editbutton.setVisibility(View.VISIBLE);
        person_autograph_text.setVisibility(View.VISIBLE);
        person_name_text.setVisibility(View.VISIBLE);
        person_sex_text.setVisibility(View.VISIBLE);
        person_birth_text.setVisibility(View.VISIBLE);
        person_hometwon_text.setVisibility(View.VISIBLE);
        person_location_text.setVisibility(View.VISIBLE);
    }
    private void editVisibility(){
        person_head_edit.setVisibility(View.VISIBLE);
        person_head.setVisibility(View.INVISIBLE);
        editbutton.setVisibility(View.INVISIBLE);
        person_autograph_text.setVisibility(View.INVISIBLE);
        person_name_text.setVisibility(View.INVISIBLE);
        person_sex_text.setVisibility(View.INVISIBLE);
        person_birth_text.setVisibility(View.INVISIBLE);
        person_hometwon_text.setVisibility(View.INVISIBLE);
        person_location_text.setVisibility(View.INVISIBLE);
        person_autograph_edit.setVisibility(View.VISIBLE);
        person_name_edit.setVisibility(View.VISIBLE);
        person_sex_radio.setVisibility(View.VISIBLE);
        person_birth_time.setVisibility(View.VISIBLE);
        person_hometwon_city.setVisibility(View.VISIBLE);
        person_location_city.setVisibility(View.VISIBLE);
        savebutton.setVisibility(View.VISIBLE);
        cenceledit.setVisibility(View.VISIBLE);
    }
    private void init(){
        persontitle=(PersonTitle)findViewById(R.id.persontitle);
        cenceledit=(Button)findViewById(R.id.cenceledit);
        editbutton=(Button)findViewById(R.id.editbutton);
        savebutton=(Button)findViewById(R.id.savebutton);
        person_head=(ImageView)findViewById(R.id.person_head);
        person_head_edit=(ImageView) findViewById(R.id.person_head_edit);
        person_hometwon_city=(AddressLayout) findViewById(R.id.person_hometwon_city);
        person_location_city=(AddressLayout) findViewById(R.id.person_location_city);
        person_birth_time=(TimeLayout)findViewById(R.id.person_birth_time);
        person_sex_radio=(RadioGroup)findViewById(R.id.person_sex_radio);
        person_autograph_edit=(EditText)findViewById(R.id.person_autograph_edit);
        person_name_edit=(EditText)findViewById(R.id.person_name_edit);
        person_autograph_text=(TextView)findViewById(R.id.person_autograph_text);
        person_name_text=(TextView)findViewById(R.id.person_name_text);
        person_account_text=(TextView)findViewById(R.id.person_account_text);
        person_sex_text=(TextView)findViewById(R.id.person_sex_text);
        person_birth_text=(TextView)findViewById(R.id.person_birth_text);
        person_hometwon_text=(TextView)findViewById(R.id.person_hometwon_text);
        person_location_text=(TextView)findViewById(R.id.person_location_text);
    }
}
