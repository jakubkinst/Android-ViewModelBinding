package cz.kinst.jakub.sample.viewmodelbinding;


import cz.kinst.jakub.sample.viewmodelbinding.databinding.ActivityMainBinding;
import cz.kinst.jakub.viewmodelbinding.ViewModelActivity;


/**
 * Created by jakubkinst on 10/11/15.
 */
public class MainActivity extends ViewModelActivity<ActivityMainBinding, MainViewModel> {

	@Override
	public int getLayoutResourceId() {
		return R.layout.activity_main;
	}
}
