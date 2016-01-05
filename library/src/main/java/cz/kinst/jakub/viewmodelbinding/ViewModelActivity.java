package cz.kinst.jakub.viewmodelbinding;

import android.app.Activity;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


public abstract class ViewModelActivity<T extends ViewDataBinding, S extends ViewModel<T>> extends AppCompatActivity implements ViewInterface {
	private final ViewModelBindingHelper<S, T> mViewModelBindingHelper = new ViewModelBindingHelper<>();


	@Override
	public void onDestroy() {
		mViewModelBindingHelper.onDestroy(this);
		super.onDestroy();
	}


	@Override
	public Context getContext() {
		return getActivity();
	}


	@Override
	public Activity getActivity() {
		return this;
	}


	public S getViewModel() {
		return mViewModelBindingHelper.getViewModel();
	}


	@Override
	public T getBinding() {
		return mViewModelBindingHelper.getBinding();
	}


	@Nullable
	@Override
	public Bundle getBundle() {
		if(getIntent() == null) return null;
		return getIntent().getExtras();
	}


	@Override
	protected void onSaveInstanceState(Bundle outState) {
		mViewModelBindingHelper.onSaveInstanceState(outState);
		super.onSaveInstanceState(outState);
	}


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mViewModelBindingHelper.onCreate(this, savedInstanceState);
	}


	@Override
	protected void onResume() {
		super.onResume();
		mViewModelBindingHelper.onResume();
	}


	@Override
	protected void onPause() {
		super.onPause();
		mViewModelBindingHelper.onPause();
	}
}
