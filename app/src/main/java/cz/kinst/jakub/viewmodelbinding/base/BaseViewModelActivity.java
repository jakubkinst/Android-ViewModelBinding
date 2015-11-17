package cz.kinst.jakub.viewmodelbinding.base;

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
public abstract class BaseViewModelActivity<T extends ViewDataBinding, S extends BaseViewModel<T>> extends AppCompatActivity implements ViewInterface
{
	private final ViewModelHelper<S> mViewModeHelper = new ViewModelHelper<>();

	private S mViewModel;
	private T mBinding;


	@Override
	public void onDestroy() {
		mViewModel.onViewDestroy();
		super.onDestroy();
	}


	@Override
	public Context getContext() {
		return this;
	}


	public S getViewModel() {
		return mViewModel;
	}


	public T getBinding() {
		return mBinding;
	}


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(getIntent() != null) {
			mViewModeHelper.onCreate(savedInstanceState, getViewModelClass(), getIntent().getExtras());
		} else {
			mViewModeHelper.onCreate(savedInstanceState, getViewModelClass(), null);
		}
		mViewModel = mViewModeHelper.getViewModel();
		bindView();
		mBinding = DataBindingUtil.setContentView(this, getLayoutResource());
		mBinding.setVariable(BR.viewModel, mViewModel);
		mViewModel.onViewCreated();
	}


	protected abstract int getLayoutResource();


	protected abstract Class<? extends BaseViewModel> getViewModelClass();
}
