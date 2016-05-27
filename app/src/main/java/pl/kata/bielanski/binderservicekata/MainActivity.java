package pl.kata.bielanski.binderservicekata;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static pl.kata.bielanski.binderservicekata.LocalService.*;

public class MainActivity extends AppCompatActivity {

	boolean mBound = false;
	LocalService mService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}


	@Override
	protected void onStart() {
		super.onStart();
		Intent intent = new Intent(this, LocalService.class);
		bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onStop() {
		super.onStop();
		if(mBound)
			unbindService(mConnection);
		mBound=false;
	}

	public void onButtonClick(View v){

	}

	private ServiceConnection mConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
				LocalService.LocalBinder binder = (LocalService.LocalBinder)service;
				mService = binder.getService();
				mBound = true;
			}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			mBound = false;
		}
	};
}
