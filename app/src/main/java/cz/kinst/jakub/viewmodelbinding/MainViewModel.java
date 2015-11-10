package cz.kinst.jakub.viewmodelbinding;

import android.content.Context;
import android.databinding.Bindable;
import android.location.Location;
import android.location.LocationManager;

import cz.kinst.jakub.viewmodelbinding.base.BaseViewModel;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * Created by jakubkinst on 10/11/15.
 */
public class MainViewModel extends BaseViewModel implements Callback<WeatherData>
{

	private WeatherData mWeatherData;
	private Call<WeatherData> mWeatherCall;


	public MainViewModel(Context context)
	{
		super(context);
	}


	@Override
	public void onViewCreated()
	{
		LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
		Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		mWeatherCall = WeatherApiProvider.get().getWeatherData(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude(), WeatherConfig.WEATHER_APP_ID);
		mWeatherCall.enqueue(this);
	}


	@Override
	public void onViewDestroy()
	{
		mWeatherCall.cancel();
		super.onViewDestroy();
	}


	@Override
	public void onResponse(Response<WeatherData> response, Retrofit retrofit)
	{
		mWeatherData = response.body();
		notifyPropertyChanged(cz.kinst.jakub.viewmodelbinding.BR.weatherData);
	}


	@Bindable
	public WeatherData getWeatherData()
	{
		return mWeatherData;
	}


	@Override
	public void onFailure(Throwable t)
	{

	}
}
