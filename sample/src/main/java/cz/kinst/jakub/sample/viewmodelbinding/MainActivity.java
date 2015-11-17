package cz.kinst.jakub.sample.viewmodelbinding;


import cz.kinst.jakub.sample.viewmodelbinding.databinding.ActivityMainBinding;
import cz.kinst.jakub.viewmodelbinding.BaseViewModel;
import cz.kinst.jakub.viewmodelbinding.BaseViewModelActivity;


/**
 * Created by jakubkinst on 10/11/15.
 */
public class MainActivity extends BaseViewModelActivity<ActivityMainBinding, MainViewModel> {

	@Override
	public int getLayoutResource() {
		return R.layout.activity_main;
	}


	@Override
	protected Class<? extends BaseViewModel> getViewModelClass() {
		return MainViewModel.class;
	}


	@Override
	public int getViewModelDataBindingId() {
		return cz.kinst.jakub.sample.viewmodelbinding.BR.viewModel;
	}
}
