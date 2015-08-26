package edu.ncc.nccmobile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class JSONHelper {

	private JSONObject json;

	// puts the .json file into a buffer and stores this as a JSON object (the 'mother' object
	// containing all the arrays, basically)
	public JSONHelper(Context context, String fileName) throws IOException, JSONException {
		InputStream stream;
		String jsonBuffer;
		int size;
		byte[] buffer;

		stream = context.getAssets().open(fileName);
		size = stream.available();
		buffer = new byte[size];
		stream.read(buffer);
		stream.close();
		jsonBuffer = new String(buffer, "UTF-8");
		json = new JSONObject(jsonBuffer);
	}

	public JSONObject getJSON() {
		return json;
	}

	// Returns a specific array within the JSON file
	public JSONArray getJSONArray(String arrayName) throws JSONException {
		return json.getJSONArray(arrayName);
	}

	// A helper method that gets only the names within a specified array -- used for populating the
	// ListView in InfoListActivity
	public ArrayList<String> getNameList(String arrayName) throws JSONException {
		JSONArray array = json.getJSONArray(arrayName);
		ArrayList<String> nameList = new ArrayList<String>();

		for(int i = 0; i < array.length(); i++) {
			JSONObject temp = array.getJSONObject(i);
			nameList.add(temp.getString("name"));
		}

		return nameList;
	}

	// A helper method to get a specific JSON object within a JSON array
	public JSONObject getJSONObject(String arrayName, int objectIndex) throws JSONException {
		JSONArray array = json.getJSONArray(arrayName);
		JSONObject object = array.getJSONObject(objectIndex);
		return object;
	}
}
