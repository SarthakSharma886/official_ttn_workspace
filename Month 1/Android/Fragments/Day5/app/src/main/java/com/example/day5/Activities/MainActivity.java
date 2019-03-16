package com.example.day5.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.day5.Fragments.Fragment_A;
import com.example.day5.Fragments.Fragment_B;
import com.example.day5.Fragments.Fragment_Dialog;
import com.example.day5.Fragments.Fragment_Preference;
import com.example.day5.Interfaces.ICommunicator;
import com.example.day5.POJO.Models;
import com.example.day5.R;
import com.getbase.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements ICommunicator {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment_B fragment_b=new Fragment_B();
    FragmentTransaction fragmentTransaction;
    FloatingActionButton preference_fab,dialog_fab,nextActivity_fab;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preference_fab = findViewById(R.id.prefrence_fragment);
        dialog_fab = findViewById(R.id.dialog_fragment);
        nextActivity_fab = findViewById(R.id.next_activity);

        nextActivity_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        preference_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_Preference preference = new Fragment_Preference();


                if(fragmentManager.getFragments()
                        .get(getSupportFragmentManager().getFragments().size() - 1)!=preference) {
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frag_layout_b, preference);
                    fragmentTransaction.commit();
                }


            }
        });

        dialog_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_Dialog fragment_dialog = new Fragment_Dialog();
                fragment_dialog.show(getSupportFragmentManager(),"Dialogx");
            }
        });



        Fragment_A fragment_a = new Fragment_A();

        fragment_a.setCommunicator(this);

        fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frag_layout_a,fragment_a);
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "on start Activity 1", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "on Resume Activity 1", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "on Pause Activity 1", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "on stop Activity 1", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "on Restart Activity 1", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "on Destroy Activity 1", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void exchange(Models models) {


//        Show Fragment
        if(fragmentManager.getFragments()
                .get(getSupportFragmentManager().getFragments().size() - 1)!=fragment_b){
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frag_layout_b,fragment_b);
            fragmentTransaction.commit();
        }

        fragment_b.setModel(models);
    }

}
