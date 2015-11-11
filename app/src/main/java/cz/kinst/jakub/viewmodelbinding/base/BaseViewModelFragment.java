package cz.kinst.jakub.viewmodelbinding.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cz.kinst.jakub.viewmodelbinding.BR;


/**
 * Created by jakubkinst on 10/11/15.
 */
public abstract class BaseViewModelFragment<T extends ViewDataBinding, S extends BaseViewModel> extends Fragment implements ViewInterface
{
	private S mViewModel;
	private T mBinding;


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		mViewModel = onCreateViewModel();
		mBinding = DataBindingUtil.inflate(inflater, getLayoutResource(), container, false);
		mBinding.setVariable(BR.viewModel, mViewModel);
		return mBinding.getRoot();
	}


	@Override
	public void onDestroy()
	{
		mViewModel.onViewDestroy();
		super.onDestroy();
	}


	@Override
	public void onDetach()
	{
		mViewModel.onViewDetach();
		super.onDetach();
	}


	@Override
	public Context getContext()
	{
		return getActivity();
	}


	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		mViewModel.onViewCreated();
	}


	public S getViewModel()
	{
		return mViewModel;
	}


	public T getBinding()
	{
		return mBinding;
	}


	protected abstract S onCreateViewModel();


	protected abstract int getLayoutResource();
}
