package edu.ncc.nccmobile;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class InfoListActivity extends ListActivity {

	private JSONHelper json;
	private String which;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_layout);

		// Retrieves which list to display
		which = getIntent().getStringExtra("which");

		TextView textView = (TextView)findViewById(R.id.list_header);
		if(which.equals("departments"))
			textView.setText("Academic Departments");
		else if(which.equals("campus_services"))
			textView.setText("Campus Services");
		else
			textView.setText("Administration");

		ArrayList<String> nameList = new ArrayList<String>();

		try {
			// Retrieves and stores the .json file in its entirety
			json = new JSONHelper(this, "departments.json");
			
			// Retrieves the JSON array within the .json file specified by 'which' list
			nameList = json.getNameList(which);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(JSONException e) {
			e.printStackTrace();
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position, long id) {
		super.onListItemClick(listView, view, position, id);
		Intent intent = new Intent(this, InfoActivity.class);

		String info = new String();

		try {
			// Retrieves the JSON object at position 'position' in the JSON array
			info = json.getJSONObject(which, position).toString();
		}
		catch(JSONException e) {
			e.printStackTrace();
		}

		// Stores the 'which' list (so the user returns to the correct list) and the JSON object
		// 'info'
		intent.putExtra("which", which);
		intent.putExtra("info", info);

		this.startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		ActionBar actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(true);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem)
	{       
		startActivity(new Intent(this, MainActivity.class)); 
		return true;
	}
}
