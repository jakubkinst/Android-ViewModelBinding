package cz.kinst.jakub.sample.viewmodelbinding.weather;

/**
 * Created by jakubkinst on 10/11/15.
 */
public class WeatherData {
	WeatherMain main;


	public WeatherMain getMain() {
		return main;
	}


	public class WeatherMain {
		double temp;


		public double getTemp() {
			return temp;
		}
	}
}
