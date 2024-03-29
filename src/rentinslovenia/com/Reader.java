package rentinslovenia.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class Reader {
	public Reader()
	{
		
	}
	
	public Reader(String url)
	{
		ReadResponse(url);
	}
	
	public String response="";
	
	public String ReadResponse(String url)
	{
		String answer="";
		HttpClient client = new DefaultHttpClient();
		
		HttpGet httpGet = new HttpGet(
				url);
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
//					builder.append(line);
					answer+=line;
				}
			} else {
				Log.d("REST", "Napaka pri prenosu.");
			}
		} catch (ClientProtocolException e) {
			Log.d("REST",e.getMessage());
		} catch (IOException e) {
			Log.d("REST",e.getMessage());
		}
		return answer;
	}
	
	public void toJson(String url)
	{
		String read = ReadResponse(url);
		//Log.d("REST", read);
		
		try {
			JSONObject json_data = new JSONObject(read);
			JSONArray nameArray = json_data.names();
			JSONArray responses = json_data.toJSONArray(nameArray);
			response=responses.getString(0);
			
		} catch (Exception e) {
			Log.d("REST", "Napaka2: "+e.toString());
		}
	}
	
	public void toJson2(String url)
	{
		String read = ReadResponse(url);
		//Log.d("REST", read);
		
		try {
			response="";
			JSONObject json_data = new JSONObject(read);
			JSONArray nameArray = json_data.names();
			JSONArray responses = json_data.toJSONArray(nameArray);
			for (int i = 0; i < responses.length(); i++) {
				response+=responses.getString(i);
			}
			
		} catch (Exception e) {
			Log.d("REST", "Napaka2: "+e.toString());
		}
	}
}