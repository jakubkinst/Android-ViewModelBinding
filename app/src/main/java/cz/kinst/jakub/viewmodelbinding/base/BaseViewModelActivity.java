package cz.kinst.jakub.viewmodelbinding.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cz.kinst.jakub.viewmodelbinding.BR;


/**
 * Created by jakubkinst on 10/11/15.
 */
public abstract class BaseViewModelActivity<T extends ViewDataBinding, S extends BaseViewModel> extends AppCompatActivity
{
	private S mViewModel;
	private T mBinding;


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


	public S getViewModel()
	{
		return mViewModel;
	}


	public T getBinding()
	{
		return mBinding;
	}


	protected abstract int getLayoutResource();


	@Override
	public void onDestroy()
	{
		mViewModel.onViewDestroy();
		super.onDestroy();
	}
}
