package edu.ncc.nccmobile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class InfoActivity extends Activity implements View.OnClickListener {

	private String which;
	private String info;
	private String website;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_layout);

		// retrieves the activity the user was in and the information to be displayed
		which = getIntent().getStringExtra("which");
		info = getIntent().getStringExtra("info");

		String name = new String();
		String location = new String();
		String phone = new String();
		String fax = new String();
		String chairperson = new String();
		String email = new String();
		String extra = new String();
		String extra_info = new String();
		JSONArray extra_infoArray;

		website = new String();

		try {
			// Converts the 'info' string into a JSON object
			JSONObject json = new JSONObject(info);
			
			// Parses the fields in the 'json' JSON object
			name = json.getString("name");
			location = json.getString("location");
			phone = json.getString("phone");
			fax = json.getString("fax");
			
			// Checks if the previous activity was Academic Departments, parses the name of the
			// chairperson if it is; ignores the field if it is not
			if(which.equals("departments"))
				chairperson = json.getString("chairperson");
			email = json.getString("email");
			website = json.getString("website");
			extra = json.getString("extra");
			
			// extra_info is an JSON array within the object. Parses the values in the array
			extra_infoArray = json.getJSONArray("extra_info");
			for(int i = 0; i < extra_infoArray.length(); i++)
				extra_info = extra_info + extra_infoArray.getString(i) + "\n";
		}
		catch(JSONException e) {
			e.printStackTrace();
		}

		TextView textView = (TextView)findViewById(R.id.name);
		textView.setText(name);
		textView = (TextView)findViewById(R.id.info);

		if(which.equals("departments"))
			textView.setText(getResources().getString(R.string.location) + location + "\n" + 
					getResources().getString(R.string.phone) + phone + "\n" + 
					getResources().getString(R.string.fax) + fax + "\n" + 
					getResources().getString(R.string.chairperson) + chairperson + "\n" + 
					getResources().getString(R.string.email) + email);
		else
			textView.setText(getResources().getString(R.string.location) + location + "\n" + 
					getResources().getString(R.string.phone) + phone + "\n" + 
					getResources().getString(R.string.fax) + fax + "\n" + 
					getResources().getString(R.string.email) + email);

		textView = (TextView)findViewById(R.id.website);
		textView.setOnClickListener(this);
		textView = (TextView)findViewById(R.id.extra);
		textView.setText(extra);
		textView = (TextView)findViewById(R.id.extra_info);
		textView.setText(extra_info);
	}

	@Override
	public void onClick(View view) {
		Intent intent = new Intent(this, WebsiteActivity.class);

		// Stores information into the bundle so that the user returns to the correct activity with
		// the correct information displayed. Also stores the desired url to be displayed
		intent.putExtra("which", which);
		intent.putExtra("info", info);
		intent.putExtra("url", "http://www.ncc.edu/" + website);

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
		// Ensures the user returns to the same list
		Intent intent = new Intent(this, InfoListActivity.class);
		intent.putExtra("which", which);

		startActivity(intent); 
		return true;
	}
}
