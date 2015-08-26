package edu.ncc.nccmobile;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.ListActivity;
import android.content.Intent;

public class MainActivity extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.main_array, android.R.layout.simple_list_item_1);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position, long id) {
		super.onListItemClick(listView, view, position, id);
		Intent intent = null;

		if(position < 3) {
			intent = new Intent(this, InfoListActivity.class);
			String[] choice = {"departments", "campus_services", "administration"};
			intent.putExtra("which", choice[position]);
		}

		if(position == 3)
			intent = new Intent(this, DiningActivity.class);

		if(position == 4)
			intent = new Intent(this, BusListActivity.class);
		
		if(position == 5)
			intent = new Intent(this, CampusMapActivity.class);

		this.startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
