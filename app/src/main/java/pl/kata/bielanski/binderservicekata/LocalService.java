package pl.kata.bielanski.binderservicekata;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Random;

public class LocalService extends Service {
	private final LocalBinder mBinder = new LocalBinder();
	private final Random mGenerator = new Random();
	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	public class LocalBinder extends Binder {
		LocalService getService(){
			return LocalService.this;
		}
	}

	public int getRundomNumber(){
		return mGenerator.nextInt(100);
	}
}
