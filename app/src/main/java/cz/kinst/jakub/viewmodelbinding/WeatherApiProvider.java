package cz.kinst.jakub.viewmodelbinding;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;


/**
 * Created by jakubkinst on 10/11/15.
 */
public class WeatherApiProvider
{
	interface WeatherApiInterface
	{
		@GET("/data/2.5/weather")
		Call<WeatherData> getWeatherData(@Query("lat") double latitude, @Query("lon") double longitude, @Query("appid") String appId);
	}


	private static WeatherApiInterface sInstance;


	public static WeatherApiInterface get()
	{
		if(sInstance == null)
		{
			sInstance = new Retrofit.Builder()
					.baseUrl("http://api.openweathermap.org")
					.addConverterFactory(GsonConverterFactory.create())
					.build()
					.create(WeatherApiInterface.class);
		}
		return sInstance;
	}
}
