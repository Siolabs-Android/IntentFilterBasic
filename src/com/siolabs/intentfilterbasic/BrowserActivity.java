package com.siolabs.intentfilterbasic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

public class BrowserActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browser);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
        .permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		Intent intent = getIntent();
		TextView text = (TextView)findViewById(R.id.browserTView);
		
		
		String action = intent.getAction();
		
		if(!action.equals(Intent.ACTION_VIEW)){
			throw new RuntimeException("Should not happen");
		}
		
		Uri data = intent.getData();
		
		URL url;
		
		
		try{
			url = new URL(data.getScheme(),data.getHost(), data.getPath());
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(url.openStream()));
			String line = "";
			
			while((line = rd.readLine())!=null){
				text.append(line);
			}
			
		}catch(Exception e ){
			e.printStackTrace();
		}
	}
}
