package andro.jf.arg;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;

public class AndroReverseGeocodingActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TextView address = (TextView)findViewById(R.id.address);

		ReverseGeocodingTask rgt = new ReverseGeocodingTask(this, address);
		Location ensib = new Location("JFL provider");
		ensib.setLatitude(47.082687);
		ensib.setLongitude(2.415916);
		rgt.execute(new Location[] {ensib});
	}
}