package cz.kinst.jakub.viewmodelbinding.weather;

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
	private static WeatherApiInterface sInstance;


	public interface WeatherApiInterface
	{
		@GET("/data/2.5/weather")
		Call<WeatherData> getWeatherData(@Query("lat") double latitude, @Query("lon") double longitude, @Query("appid") String appId);
	}


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
