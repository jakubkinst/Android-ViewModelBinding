package cz.kinst.jakub.sample.viewmodelbinding;

import android.databinding.ObservableField;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import cz.kinst.jakub.sample.viewmodelbinding.databinding.ActivityMainBinding;
import cz.kinst.jakub.viewmodelbinding.ViewModel;


/**
 * Created by jakubkinst on 10/11/15.
 */
public class MainViewModel extends ViewModel<ActivityMainBinding> {

	public ObservableField<String> name = new ObservableField<>();
	private SampleDialogFragment dialog;


	@Override
	public void onViewModelCreated() {
		super.onViewModelCreated();
		// Do API calls etc.
	}


	@Override
	public void onViewAttached(boolean firstAttachment) {
		super.onViewAttached(firstAttachment);
		// manipulate with the view
	}


	@Override
	public void onViewDetached(boolean finalDetachment) {
		super.onViewDetached(finalDetachment);

		// run safely on ui thread next time the view is attached (if this was called directly, getContext() would return null at this time)
		runOnUiThread(() ->
				Toast.makeText(getContext(), "onViewDetached()", Toast.LENGTH_SHORT).show()
		);
	}


	@Override
	public void onViewModelDestroyed() {
		super.onViewModelDestroyed();
		// Cancel API calls
	}


	public void greet() {
		name.set(getBinding().nameEditText.getText().toString());
	}


	public void showDialog() {
		dialog = SampleDialogFragment.newInstance();
		dialog.setListener(() -> {
			if(hasViewAttached())
				Toast.makeText(getContext(), "Button in dialog clicked", Toast.LENGTH_SHORT).show();
		});
		dialog.show(((AppCompatActivity) getActivity()).getSupportFragmentManager(), "sample");
	}

}
