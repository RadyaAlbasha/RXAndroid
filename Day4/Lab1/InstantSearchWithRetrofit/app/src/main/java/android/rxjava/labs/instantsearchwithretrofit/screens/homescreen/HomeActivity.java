package android.rxjava.labs.instantsearchwithretrofit.screens.homescreen;

import android.content.Intent;
import android.rxjava.labs.instantsearchwithretrofit.R;
import android.rxjava.labs.instantsearchwithretrofit.screens.mainscreen.MainActivity;
import android.rxjava.labs.instantsearchwithretrofit.screens.remotescreen.RemoteActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void searchLocaly(View view) {
        Intent intent = new Intent(HomeActivity.this , MainActivity.class);
        startActivity(intent);
    }

    public void searchRemotly(View view) {
        Intent intent = new Intent(HomeActivity.this , RemoteActivity.class);
        startActivity(intent);
    }
}
