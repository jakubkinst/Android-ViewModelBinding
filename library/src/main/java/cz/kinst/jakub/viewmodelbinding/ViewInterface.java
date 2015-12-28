package cz.kinst.jakub.viewmodelbinding;

import android.app.Activity;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;


/**
 * An interface representing View in MVVM architecture
 * <p/>
 * This interface should be implemented by Activity/Fragment representing a screen
 *
 * @param <T> Generated Data Binding layout class
 */
public interface ViewInterface<T extends ViewDataBinding> {
	Context getContext();
	T getBinding();
	Activity getActivity();
	Bundle getBundle();
	ViewModelBindingConfig getViewModelBindingConfig();
}
