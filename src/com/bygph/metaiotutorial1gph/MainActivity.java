package com.bygph.metaiotutorial1gph;

import java.io.IOException;

//import com.metaio.Example.MainActivity.WebViewHandler;
import com.metaio.sdk.MetaioDebug;
import com.metaio.tools.io.AssetsManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebSettings;

public class MainActivity extends Activity {

	AssetsExtracter mTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mTask = new AssetsExtracter();
		mTask.execute(0);
		
		Intent intent = new Intent(getApplicationContext(), ARActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	private class AssetsExtracter extends AsyncTask<Integer, Integer, Boolean>
	{
		@Override
		protected Boolean doInBackground(Integer... params) 
		{
			try 
			{
				AssetsManager.extractAllAssets(getApplicationContext(), true);
			} 
			catch (IOException e) 
			{
				MetaioDebug.printStackTrace(Log.ERROR, e);
				return false;
			}
			return true;
		}
	}
	
	
}
