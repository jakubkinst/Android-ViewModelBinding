package cz.kinst.jakub.viewmodelbinding;

/**
 * Created by jakubkinst on 10/11/15.
 */
public class WeatherData
{
	public class WeatherMain
	{
		double temp;


		public double getTemp()
		{
			return temp;
		}
	}


	WeatherMain main;


	public WeatherMain getMain()
	{
		return main;
	}
}
