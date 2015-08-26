package edu.ncc.nccmobile;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CampusMapActivity extends Activity implements ActionBar.OnNavigationListener {

	private SharedPreferences savedLatLng;
	private GoogleMap map;
	private LatLng mycar;

	// Academic buildings  
	private static final LatLng BUILDING_A = new LatLng(40.729966, -73.590835);
	private static final LatLng BUILDING_B = new LatLng(40.730373, -73.590674);
	private static final LatLng BUILDING_C = new LatLng(40.730848, - 73.590556);
	private static final LatLng BUILDING_D = new LatLng(40.731608, -73.590696); 
	private static final LatLng BUILDING_E = new LatLng(40.730938, -73.592445);
	private static final LatLng BUILDING_F = new LatLng(40.730491, -73.592584);
	private static final LatLng BUILDING_G = new LatLng(40.729161, -73.593947);
	private static final LatLng BUILDING_H = new LatLng(40.72993,-73.59878);
	private static final LatLng BUILDING_K = new LatLng(40.732031,-73.593335);
	private static final LatLng LS_BUILDING = new LatLng(40.732324,-73.590803);
	private static final LatLng BUILDING_M = new LatLng(40.729625,-73.596795); // Nassau Hall/Building M
	private static final LatLng BUILDING_N = new LatLng(40.730891,-73.598528);
	private static final LatLng BUILDING_P = new LatLng(40.728958,-73.591533);
	private static final LatLng BUILDING_Q = new LatLng(40.730007,-73.598077);
	private static final LatLng BUILDING_S = new LatLng(40.729267,-73.597702);
	private static final LatLng BUILDING_V = new LatLng(40.731039,-73.597273);
	private static final LatLng BUILDING_W = new LatLng(40.728308,-73.596296);
	private static final LatLng BUILDING_Y = new LatLng(40.732503,-73.59576);
	private static final LatLng MEDIA_BUILDING = new LatLng(40.730907,-73.599086);

	// **NOTE** There is no building I
	// Bus Stops 
	private static final LatLng BUSSTOP_DUNCAN_AVE = new LatLng(40.731726,-73.597332);
	private static final LatLng BUSSTOP_BRADLEY_HALL = new LatLng(40.732243,-73.595814);
	private static final LatLng BUSSTOP_ENDOBLVD = new LatLng(40.732003,-73.592407);
	private static final LatLng BUSSTOP_MILLER = new LatLng(40.73306,-73.591227);
	private static final LatLng BUSSTOP_OVINGTON_LINDENBURG = new LatLng(40.726059,-73.592541);
	private static final LatLng BUSSTOP_UNION = new LatLng(40.728702,-73.595669);

	//Parking Lots
	private static final LatLng PARKW1 = new LatLng(40.729743,-73.599389);
	private static final LatLng PARKW2 = new LatLng(40.730314,-73.596943);
	private static final LatLng PARKW3 = new LatLng(40.72906,-73.596131);
	private static final LatLng PARKW4A = new LatLng(40.727657,-73.59584);
	private static final LatLng PARKW4B = new LatLng(40.727612,-73.593952);
	private static final LatLng PARKW5 = new LatLng(40.727592,-73.592359);

	//East Lots 
	private static final LatLng PARKE1 = new LatLng(40.731804,-73.593054);
	private static final LatLng PARKE2 = new LatLng(40.732283,-73.591828);
	private static final LatLng PARKE3A = new LatLng(40.733163,-73.589462);
	private static final LatLng PARKE3B = new LatLng(40.733206,-73.588762);
	private static final LatLng PARKE3C = new LatLng(40.733104,-73.587517);
	private static final LatLng PARKE4A = new LatLng(40.732342,-73.589537);
	private static final LatLng PARKE4B = new LatLng(40.732277,-73.588727);
	private static final LatLng PARKE4C = new LatLng(40.732082,-73.587866);
	private static final LatLng PARKE5A = new LatLng(40.73032,-73.589663);
	private static final LatLng PARKE5B = new LatLng(40.728824,-73.590264);
	private static final LatLng PARKE5C = new LatLng(40.728558,-73.588582);

	// Dining Spots
	private static final LatLng CCB = new LatLng(40.729613,-73.593491);
	private static final LatLng Treat_Street = new LatLng(40.730478,-73.590886);
	private static final LatLng Treat_Street2 = new LatLng(40.730991,-73.597254);

	//Campus & Student Services 
	private static final LatLng TOWER = new LatLng(40.729775,-73.592396);
	private static final LatLng LIBRARY = new LatLng(40.730295,-73.591656);
	private static final LatLng PUBLIC_SAFETY = new LatLng(40.727702,-73.591906);
	private static final LatLng UNION = new LatLng(40.728747,-73.595248);
	private static final LatLng BOOKSTORE = new LatLng(40.731722,-73.59753);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_layout);

		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

		map.setInfoWindowAdapter(new InfoWindowAdapter() {

			public View getInfoWindow(Marker arg0) {
				return null;
			}

			public View getInfoContents(Marker marker) {
				View view = getLayoutInflater().inflate(R.layout.map_label_layout,null);
				TextView label_text = (TextView)view.findViewById(R.id.label_text);
				TextView label_snippit = (TextView)view.findViewById(R.id.label_snippit);
				label_text.setText(marker.getTitle());
				label_snippit.setText(marker.getSnippet());
				return view; 
			}
		});

		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		map.setMyLocationEnabled(true);

		SpinnerAdapter mapSpinnerAdapter = ArrayAdapter.createFromResource(this,
				R.array.map_options,
				android.R.layout.simple_spinner_dropdown_item);
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		actionBar.setListNavigationCallbacks(mapSpinnerAdapter,  this);

		// Retrieve saved LatLng values. LatLng is set to (0, 0) if no values are saved
		savedLatLng = PreferenceManager.getDefaultSharedPreferences(this); 
		mycar = new LatLng(Double.longBitsToDouble(savedLatLng.getLong("lat", 0)),
				Double.longBitsToDouble(savedLatLng.getLong("lng", 0)));
	}

	@Override
	public void onRestoreInstanceState(Bundle bundle) {
		int spinnerItem = bundle.getInt("spinnerItem");
		getActionBar().setSelectedNavigationItem(spinnerItem);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.map, menu);
		ActionBar actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(true);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem)
	{
		Intent intent = new Intent(this, MainActivity.class);

		if(menuItem.getItemId() == R.id.action_mycar) {
			Location location = map.getMyLocation();
			mycar = new LatLng(location.getLatitude(), location.getLongitude());
			Toast toast = Toast.makeText(getApplicationContext(), "Car location set!", Toast.LENGTH_SHORT);
			toast.show();
		}
		else {
			startActivity(intent);
		}

		return true;
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		switch (itemPosition) {

		// Buildings 
		case 0 :
			map.clear();	

			map.addMarker(new MarkerOptions()
			.position(BUILDING_A)
			.title("Building A\nDepartments:")
			.snippet("Accounting and Business Administration\nLegal Studies\nAdministrative Business Technology")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.blackboard)));

			map.addMarker(new MarkerOptions()
			.position(BUILDING_B)
			.title("Building B\nDepartments:")
			.snippet("Math, Comuter Science & Information Technology\nMarketing & Retailing\nFashion Buying & Merchandise\nFashion & Interior Design")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.blackboard)));

			map.addMarker(new MarkerOptions()
			.position(BUILDING_C)
			.title("Building C\nDepartments:")
			.snippet("Physical Science")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.blackboard)));

			map.addMarker(new MarkerOptions()
			.position(BUILDING_D)
			.title("Building D\nDepartments:")
			.snippet("Engineering, Physics & Technology")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.blackboard)));

			map.addMarker(new MarkerOptions()
			.position(BUILDING_E)
			.title("Building E\nDepartments:")
			.snippet("Health Sciences")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.blackboard)));

			map.addMarker(new MarkerOptions()
			.position(BUILDING_F)
			.title("Building F\nDepartments:")
			.snippet("Biology")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.blackboard)));

			map.addMarker(new MarkerOptions()
			.position(BUILDING_G)
			.title("Building G\nDepartments:")
			.snippet("Criminal Justice\nEconomics & Finance\nHistory, Geography & Political Science\nPsychology\nScoiology\nArt\nTeacher Education")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.blackboard)));

			map.addMarker(new MarkerOptions()
			.position(BUILDING_H)
			.title("Building H\nDepartments:")
			.snippet("Africana Studies\nCommunications\nMusic")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.blackboard)));

			map.addMarker(new MarkerOptions()
			.position(BUILDING_K)
			.title("Building K\nDepartments:")
			.snippet("Hospitality")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.blackboard)));

			map.addMarker(new MarkerOptions()
			.position(LS_BUILDING)
			.title("Life Sciences Building\nDepartments:")
			.snippet("Chemistry\nNursing")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.blackboard)));

			map.addMarker(new MarkerOptions()
			.position(BUILDING_M)
			.title("Nassau Hall/Building M\nDepartments:")
			.snippet("Philosophy\nLanguages")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.blackboard)));

			map.addMarker(new MarkerOptions()
			.position(MEDIA_BUILDING)
			.title("Media/Audio Visual Building")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.blackboard)));

			map.addMarker(new MarkerOptions()
			.position(BUILDING_N)
			.title("North Hall/Building N\nDepartments:")
			.snippet("Reading\nBasic Ed")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.blackboard)));

			map.addMarker(new MarkerOptions()
			.position(BUILDING_P)
			.title("Building P/PHYSICAL EDUCATION COMPLEX\nDepartments:")
			.snippet("Health, Physical Education & Recreation")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.blackboard)));

			map.addMarker(new MarkerOptions()
			.position(BUILDING_Q)
			.title("Building Q\n:Departments:")
			.snippet("Music Labs, & Classrooms")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.blackboard)));

			map.addMarker(new MarkerOptions()
			.position(BUILDING_S)
			.title("South Hall/Building S")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.blackboard)));

			map.addMarker(new MarkerOptions()
			.position(BUILDING_V)
			.title("Building V\nDepartments:")
			.snippet("Studio Recording")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.blackboard)));

			map.addMarker(new MarkerOptions()
			.position(BUILDING_W)
			.title("Building W Theater\nDepartments:")
			.snippet("Theater & Dance")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.blackboard)));

			map.addMarker(new MarkerOptions()
			.position(BUILDING_Y)
			.title("Bradley Hall/Building Y\nDepartments:")
			.snippet("English\nStudent Writing Center\nHonor's Program")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.blackboard)));
			break;

			// Bus Stops 
		case 1:
			map.clear();

			map.addMarker(new MarkerOptions()
			.position(BUSSTOP_DUNCAN_AVE)
			.title("Bookstore/Duncan Ave Bus Stop")
			.snippet("Routes: n6X, n16, n35, n43, n45, n51")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.bus)));


			map.addMarker(new MarkerOptions()
			.position(BUSSTOP_BRADLEY_HALL)
			.title("Bradley Hall/Hazelhurst Ave. Bus Stop")
			.snippet("Routes: n6X, n16, n35, n43, n45, n51")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.bus)));


			map.addMarker(new MarkerOptions()
			.position(BUSSTOP_ENDOBLVD)
			.title("Endo Blvd Bus Stop")
			.snippet("Routes:\nn6X, n16, n35, n43,n45, n51")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.bus)));

			map.addMarker(new MarkerOptions()
			.position(BUSSTOP_MILLER)
			.title("Bus Stop Miller Ave & Endo Blvd")
			.snippet("Routes: n16, n35, n43, n45, n51")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.bus)));

			map.addMarker(new MarkerOptions()
			.position(BUSSTOP_OVINGTON_LINDENBURG)
			.title("Bus Stop Ovington & Lindenburg 1")
			.snippet("Routes: n6X, n16, n43, n45, n51")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.bus)));


			map.addMarker(new MarkerOptions()
			.position(BUSSTOP_UNION)
			.title("Student Union Bus Stop")
			.snippet("Routes: n6X, n16, n43, n45, n51")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.bus)));
			break;

			// Parking Lots 	
		case 2:
			map.clear();

			// West Parking Lots 
			map.addMarker(new MarkerOptions()
			.position(PARKW1)
			.title("Parking West 1")
			.snippet("Student,Vistor, & Faculity Parking")  
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.parking2)));

			map.addMarker(new MarkerOptions()
			.position(PARKW2)
			.title("Parking West 2")
			.snippet("Staff Parking Only")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.parking2)));

			map.addMarker(new MarkerOptions()
			.position(PARKW3)
			.title("Parking West 3")
			.snippet("Staff Parking Only")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.parking2)));

			map.addMarker(new MarkerOptions()
			.position(PARKW4A)
			.title("Parking West 4A")
			.snippet("Student & Visitor Parking")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.parking2)));

			map.addMarker(new MarkerOptions()
			.position(PARKW4B)
			.title("Parking West 4B")
			.snippet("Student & Visitor Parking")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.parking2)));

			map.addMarker(new MarkerOptions()
			.position(PARKW5)
			.title("Parking West 5")
			.snippet("Staff Parking Only")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.parking2)));

			// East Parking Lots 
			map.addMarker(new MarkerOptions()
			.position(PARKE1)
			.title("Parking East 1")
			.snippet("Staff Parking Only")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.parking2)));

			map.addMarker(new MarkerOptions()
			.position(PARKE2)
			.title("Parking East 2")
			.snippet("Staff Parking Only")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.parking2)));

			map.addMarker(new MarkerOptions()
			.position(PARKE3A)
			.title("Parking East 3A")
			.snippet("Student & Visitor Parking")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.parking2)));

			map.addMarker(new MarkerOptions()
			.position(PARKE3B)
			.title("Parking East 3B")
			.snippet("Student & Visitor Parking")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.parking2)));

			map.addMarker(new MarkerOptions()
			.position(PARKE3C)
			.title("Parking East 3C")
			.snippet("Student & Visitor Parking")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.parking2)));

			map.addMarker(new MarkerOptions()
			.position(PARKE4A)
			.title("Parking East 4A")
			.snippet("Student & Visitor Parking")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.parking2)));

			map.addMarker(new MarkerOptions()
			.position(PARKE4B)
			.title("Parking East 4B")
			.snippet("Student & Visitor Parking")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.parking2)));

			map.addMarker(new MarkerOptions()
			.position(PARKE4C)
			.title("Parking East 4C")
			.snippet("Student & Visitor Parking")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.parking2)));

			map.addMarker(new MarkerOptions()
			.position(PARKE5A)
			.title("Parking East 5A")
			.snippet("Student & Visitor Parking")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.parking2)));

			map.addMarker(new MarkerOptions()
			.position(PARKE5B)
			.title("Parking East 5B")
			.snippet("Student & Visitor Parking")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.parking2)));

			map.addMarker(new MarkerOptions()
			.position(PARKE5C)
			.title("Parking East 5C")
			.snippet("Student & Visitor Parking")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.parking2)));
			break;

			//Campus & Student Services 
		case 3:
			map.clear();

			map.addMarker(new MarkerOptions()
			.position(TOWER)
			.title("Tower/Building T\nDepartments:")
			.snippet("Registar\nBursar\nFinancial Aid\nHealth Services\nID Card Office\nAdministrative Offices")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.information)));


			map.addMarker(new MarkerOptions()
			.position(LIBRARY)
			.title("Library")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.information)));


			map.addMarker(new MarkerOptions()
			.position(PUBLIC_SAFETY)
			.title("Public Safety")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.information)));


			map.addMarker( new MarkerOptions()
			.position(CCB)
			.title("CCB\nDepartments:")
			.snippet("Student Life\nStudent Activities Office\nClubs & Organizations\nFood Court")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.information)));

			map.addMarker(new MarkerOptions()
			.position(UNION)
			.title("Building U: Student Union & Academic Advisement\nDepartments:")
			.snippet("Academic Advisement\nPlacement Testing\nStudent Disabilities Services")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.information)));

			map.addMarker(new MarkerOptions()
			.position(BUILDING_N)
			.title("North Hall/Building N\nDepartments:")
			.snippet("Educational Counseling\nCareer Counseling\nJob Placement\nTransfer Counseling")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.information)));


			map.addMarker(new MarkerOptions()
			.position(BOOKSTORE)
			.title("Campus Bookstore")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.information)));
			break;

			// Dining Spots
		case 4:
			map.clear();	

			map.addMarker( new MarkerOptions()
			.position(CCB)
			.title("Top Flight Food Court @ CCB")
			.snippet("M-Th: 7AM-7:30PM\nF:7AM-3:30PM")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.food)));

			map.addMarker(new MarkerOptions()
			.position(Treat_Street)
			.title("Treat Street Food Snack Bar\nHours:")
			.snippet("M-Th: 7AM-9:30PM\nF:7AM-4PM")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.food)));

			map.addMarker(new MarkerOptions()
			.position(Treat_Street2)
			.title("Treat Street 2 Snack Bar\nHours:")
			.snippet("M-Th:7AM-6PM\nF:7AM-3:30PM")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.food)));
			break;

			// Car Location
		case 5:
			if(mycar.latitude == 0 && mycar.longitude == 0) {
				getActionBar().setSelectedNavigationItem(0);
				Toast toast = Toast.makeText(getApplicationContext(), "You haven't set the car location yet!", Toast.LENGTH_SHORT);
				toast.show();
			}
			else {
				map.clear();
				map.addMarker(new MarkerOptions()
				.position(mycar)
				.title("Your car is parked here!")
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));

				CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(mycar, 17);
				map.animateCamera(cameraUpdate);
			}
			break;
		}

		return false;
	}

	// Saves LatLng values into SharedPreferences so car location is retained even when user leaves
	// the activity/app
	@Override
	public void onPause() {
		super.onPause();

		SharedPreferences.Editor editor = savedLatLng.edit();

		editor.putLong("lat", Double.doubleToLongBits(mycar.latitude));
		editor.putLong("lng", Double.doubleToLongBits(mycar.longitude));
		editor.commit();
	}

	@Override
	public void onSaveInstanceState(Bundle bundle) {
		bundle.putInt("spinnerItem", getActionBar().getSelectedNavigationIndex());
	}
}