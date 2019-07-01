package info.bankaspace.serviceproviders;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by DC on 01-03-2018.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {
    protected abstract CrimeFragment createFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager fm=getSupportFragmentManager();
        android.support.v4.app.Fragment fragment=fm.findFragmentById(R.id.fragment_container);

        if(fragment==null){
            fragment=createFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }
}