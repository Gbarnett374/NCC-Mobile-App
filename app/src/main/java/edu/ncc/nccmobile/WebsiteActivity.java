package edu.ncc.nccmobile;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class WebsiteActivity extends Activity {

	private String which;
	private String info;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview_layout);
		
		// Retrieves 'which' and 'info' to ensure the user returns to the expected view
		which = getIntent().getStringExtra("which");
		info = getIntent().getStringExtra("info");
		String url = getIntent().getStringExtra("url");

		WebView webView = (WebView)findViewById(R.id.web_view);
		webView.getSettings().setUseWideViewPort(true);
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setSupportZoom(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setInitialScale(0);
		webView.loadUrl(url);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		ActionBar actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(true);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem)
	{
		Intent intent;

		// Ensures the user returns to the expected view when clicking the 'back' button
		if(which.equals("bus"))
			intent = new Intent(this, BusListActivity.class);
		else {
			intent = new Intent(this, InfoActivity.class);
			intent.putExtra("which", which);
			intent.putExtra("info", info);
		}

		this.startActivity(intent);
		return true;
	}

}
