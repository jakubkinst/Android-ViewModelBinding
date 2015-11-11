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
	private S mViewModel;
	private T mBinding;


	@Override
	public void onDestroy()
	{
		mViewModel.onViewDestroy();
		super.onDestroy();
	}


	@Override
	public Context getContext()
	{
		return this;
	}


	public S getViewModel()
	{
		return mViewModel;
	}


	public T getBinding()
	{
		return mBinding;
	}


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mViewModel = onCreateViewModel();
		mBinding = DataBindingUtil.setContentView(this, getLayoutResource());
		mBinding.setVariable(BR.viewModel, mViewModel);
		mViewModel.onViewCreated();
	}


	protected abstract S onCreateViewModel();


	protected abstract int getLayoutResource();
}
