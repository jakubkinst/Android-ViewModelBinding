package cz.kinst.jakub.viewmodelbinding.base;

import android.content.Context;
import android.databinding.BaseObservable;


/**
 * Created by jakubkinst on 10/11/15.
 */
public class BaseViewModel extends BaseObservable
{
	private Context mContext;


	public BaseViewModel(Context context)
	{
		mContext = context;
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


	public Context getContext()
	{
		return mContext;
	}
}
