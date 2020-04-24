package RateSC.RateSCAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PopulateDataRestaurants {
	public static void main(String[] args) {
		try {
			String restaurantsURL= "\"https://api.yelp.com/v3/businesses/\"\n" + 
					"				+ \"search?term=\" + Name + \"&latitude=\" + lati + \"&longitude=\" + longi;";
			URL test= new URL(restaurantsURL);
			HttpURLConnection connection = (HttpURLConnection)test.openConnection();
			connection.setRequestProperty("Authorization", 
					"Bearer tlbml4wE2IacC9dPBb17iBBQ1z5mPAd45hvvQ43tyfKIFH14j817wnnujxbIE0HFxqoZzbpuWaGv3ITzoLaw_hlcBPzgUDKNTbrFwT9r6DLWN5yUKlXTSGmmcvp6XnYx");
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String jsonString= in.readLine();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
