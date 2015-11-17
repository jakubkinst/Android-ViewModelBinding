package cz.kinst.jakub.viewmodelbinding;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;


/**
 * Created by jakubkinst on 11/11/15.
 */
public interface ViewInterface<T> {
	Context getContext();
	T getBinding();
	Activity getActivity();
	int getViewModelDataBindingId();
	@LayoutRes
	int getLayoutResource();
}
