package com.malpo.northeast.ui;

import com.malpo.northeast.R;
import com.malpo.northeast.ui.main.MainActivity;
import com.metova.slim.Slim;
import com.trello.rxlifecycle.ActivityEvent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import butterknife.ButterKnife;
import rx.subjects.BehaviorSubject;

public class BaseActivity extends AppCompatActivity {

    private final BehaviorSubject<ActivityEvent> mLifecycleSubject = BehaviorSubject.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLifecycleSubject.onNext(ActivityEvent.CREATE);
        View layout = Slim.createLayout(this, this);
        if (layout != null) {
            setContentView(layout);
            ButterKnife.bind(this);
        }

        Slim.injectExtras(getIntent().getExtras(), this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLifecycleSubject.onNext(ActivityEvent.STOP);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLifecycleSubject.onNext(ActivityEvent.DESTROY);
        //added this on develop

    }

    @Override
    protected void onPause() {
        super.onPause();
        mLifecycleSubject.onNext(ActivityEvent.PAUSE);
        //added this on develop

    }

    @Override
    protected void onResume() {
        super.onResume();
        mLifecycleSubject.onNext(ActivityEvent.RESUME);
        //added this on develop
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLifecycleSubject.onNext(ActivityEvent.START);
        //added this on develop

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(!(this instanceof MainActivity))
            overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return super.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
