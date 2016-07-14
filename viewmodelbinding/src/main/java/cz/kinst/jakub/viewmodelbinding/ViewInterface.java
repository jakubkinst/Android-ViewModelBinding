package cz.kinst.jakub.viewmodelbinding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;


/**
 * An interface representing View in MVVM architecture
 * <p>
 * This interface should be implemented by Activity/Fragment representing a screen
 *
 * @param <T> Generated Data Binding layout class
 */
public interface ViewInterface<T extends ViewDataBinding, S extends ViewModel> {
	Context getContext();
	T getBinding();
	Activity getActivity();
	Bundle getBundle();
	ViewModelBindingConfig<S> getViewModelBindingConfig();
	void startActivityForResult(Intent intent, int requestCode);
	void startActivityForResult(Intent intent, int requestCode, Bundle bundle);
}
