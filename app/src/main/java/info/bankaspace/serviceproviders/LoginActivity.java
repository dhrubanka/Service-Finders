package info.bankaspace.serviceproviders;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        FragmentManager fm=getSupportFragmentManager();
        android.support.v4.app.Fragment fragment=fm.findFragmentById(R.id.fragment_container);

        if(fragment==null){
            fragment=createFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }
}
