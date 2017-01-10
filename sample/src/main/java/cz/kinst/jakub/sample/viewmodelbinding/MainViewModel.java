package cz.kinst.jakub.sample.viewmodelbinding;

import android.databinding.ObservableField;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import cz.kinst.jakub.viewmodelbinding.ViewModel;


public class MainViewModel extends ViewModel {

	public ObservableField<String> name = new ObservableField<>();


	@Override
	public void onViewModelCreated() {
		super.onViewModelCreated();
		// Do API calls etc.
	}


	@Override
	public void onViewDetached(boolean finalDetachment) {
		super.onViewDetached(finalDetachment);

		// run safely on ui thread next time the view is attached (if this was called directly, getRootView() would return null at this time and this call would fail)
		showSnackBar("onViewDetached()");
	}


	@Override
	public void onViewModelDestroyed() {
		super.onViewModelDestroyed();
		// Cancel API calls
	}


	public void showDialog() {
		ArgumentDialogFragment dialog = ArgumentDialogFragment.newInstance(name.get());
		dialog.setListener(() -> {
			showSnackBar("Button in dialog clicked");
			dialog.dismiss();
		});
		dialog.show(((AppCompatActivity) getActivity()).getSupportFragmentManager(), "sample");
	}


	private void showSnackBar(String message) {runOnUiThread(() -> Snackbar.make(getRootView(), message, Snackbar.LENGTH_SHORT).show());}

}
