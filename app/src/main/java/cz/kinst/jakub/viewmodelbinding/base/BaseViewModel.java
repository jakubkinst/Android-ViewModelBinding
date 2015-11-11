package cz.kinst.jakub.viewmodelbinding.base;

import android.databinding.BaseObservable;


/**
 * Created by jakubkinst on 10/11/15.
 */
public class BaseViewModel<T> extends BaseObservable
{
	private ViewInterface<T> mView;


	public BaseViewModel(ViewInterface viewInterface)
	{
		mView = viewInterface;
	}


	public void onViewDestroy()
	{
	}


	public void onViewDetach()
	{
	}


	public void onViewCreated()
	{
	}


	public ViewInterface<T> getView()
	{
		return mView;
	}
}
