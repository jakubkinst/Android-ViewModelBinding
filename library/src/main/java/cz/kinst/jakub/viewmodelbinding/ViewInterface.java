package cz.kinst.jakub.viewmodelbinding;

import android.app.Activity;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;


/**
 * Created by jakubkinst on 11/11/15.
 */
public interface ViewInterface<T extends ViewDataBinding> {
	Context getContext();
	T getBinding();
	Activity getActivity();
	Bundle getBundle();
	ViewModelBindingConfig getViewModelBindingConfig();
}
