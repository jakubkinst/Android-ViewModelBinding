package cz.kinst.jakub.sample.viewmodelbinding;


import cz.kinst.jakub.sample.viewmodelbinding.databinding.ActivityMainBinding;
import cz.kinst.jakub.viewmodelbinding.ViewModelActivity;
import cz.kinst.jakub.viewmodelbinding.ViewModelBindingConfig;


/**
 * Created by jakubkinst on 10/11/15.
 */
public class MainActivity extends ViewModelActivity<ActivityMainBinding, MainViewModel> {

	@Override
	public ViewModelBindingConfig getViewModelBindingConfig() {
		return new ViewModelBindingConfig(R.layout.activity_main, MainViewModel.class, cz.kinst.jakub.sample.viewmodelbinding.BR.viewModel);
	}
}
