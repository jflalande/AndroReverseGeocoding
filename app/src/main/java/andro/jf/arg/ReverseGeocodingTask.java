package andro.jf.arg;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.widget.TextView;

public class ReverseGeocodingTask extends AsyncTask<Location, Void, String> {
	Context mContext;
	TextView addresseResult;
	protected void onPostExecute(String result) {
		addresseResult.setText(result);
	}

	public ReverseGeocodingTask(Context context, TextView result) {
		super();
		mContext = context;
		this.addresseResult = result;
	}

	@Override
	protected String doInBackground(Location... params) {
		String addressText = null;
		Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
		System.out.println("Geocoder is present ? " + geocoder.isPresent());

		Location loc = params[0];
		List<Address> addresses = null;
		try {
			// Call the synchronous getFromLocation() method 
		  // by passing in the lat/long values.
			addresses = geocoder.getFromLocation(loc.getLatitude(), 
			    loc.getLongitude(), 1);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erreur: " + e.toString());
		}
		System.out.println("Adresses: " + addresses);
		if (addresses != null && addresses.size() > 0) {
			Address address = addresses.get(0);
			// Format the first line of address (if available), 
			// city, and country name.
			addressText = String.format("%s, %s, %s",
					address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : 
					    "",
							address.getLocality(),
							address.getCountryName());
			System.out.println("Adresse: " + addressText);

		}
		return addressText;
	}
}