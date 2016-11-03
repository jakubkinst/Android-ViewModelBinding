package cz.kinst.jakub.sample.viewmodelbinding;


import android.os.Bundle;
import android.support.annotation.Nullable;

import cz.kinst.jakub.sample.viewmodelbinding.databinding.ActivityMainBinding;
import cz.kinst.jakub.viewmodelbinding.ViewModelActivity;


public class MainActivity extends ViewModelActivity<ActivityMainBinding, MainViewModel> {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		setupViewModel(R.layout.activity_main, MainViewModel.class);
		super.onCreate(savedInstanceState);
	}
}
