package cz.kinst.jakub.viewmodelbinding.base;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cz.kinst.jakub.viewmodelbinding.BR;


/**
 * Created by jakubkinst on 10/11/15.
 */
public abstract class BaseViewModelActivity<T extends ViewDataBinding, S extends BaseViewModel<T>> extends AppCompatActivity implements ViewInterface {
	private final ViewModelHelper<S> mViewModelHelper = new ViewModelHelper<>();

	private T mBinding;


	@Override
	public void onDestroy() {
		mViewModelHelper.onDestroy(this);
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
		return mViewModelHelper.getViewModel();
	}


	public T getBinding() {
		return mBinding;
	}


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBinding = DataBindingUtil.setContentView(this, getLayoutResource());
		mViewModelHelper.onCreate(this, savedInstanceState, getViewModelClass());
		mBinding.setVariable(BR.viewModel, getViewModel());
	}


	protected abstract int getLayoutResource();


	protected abstract Class<? extends BaseViewModel> getViewModelClass();
}
