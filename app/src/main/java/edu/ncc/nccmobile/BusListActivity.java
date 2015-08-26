package edu.ncc.nccmobile;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class BusListActivity extends Activity implements OnItemClickListener {

	private String[] url;
	private String googleDocs = "https://docs.google.com/gview?embedded=true&url=";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bus_list_layout);

		url = getResources().getStringArray(R.array.url_array);

		GridView gridView = (GridView)findViewById(R.id.gridview);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.bus_array, R.layout.gridview_layout);
		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
		Intent intent = new Intent(this, WebsiteActivity.class);
		
		// Puts which activity this is and the url into bundle
		intent.putExtra("which", "bus");
		intent.putExtra("info", "");
		intent.putExtra("url", googleDocs + url[position]);
		startActivity(intent);		
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
		Intent intent = new Intent(this, MainActivity.class);

		startActivity(intent); 
		return true;
	}

}