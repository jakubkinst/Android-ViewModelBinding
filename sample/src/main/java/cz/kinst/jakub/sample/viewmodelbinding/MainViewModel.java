package cz.kinst.jakub.sample.viewmodelbinding;

import android.databinding.ObservableField;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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


	public void onClickGreetButton(View v) {
		name.set(getBinding().nameEditText.getText().toString());
	}


	public void onClickedShowDialogFragmentButton(View v) {
		dialog = SampleDialogFragment.newInstance();
		dialog.setListener(new SampleDialogViewModel.SampleDialogListener() {
			@Override
			public void onButtonClicked() {
				if(hasViewAttached())
					Toast.makeText(getContext(), "Button in dialog clicked", Toast.LENGTH_SHORT).show();
			}
		});
		dialog.show(((AppCompatActivity) getActivity()).getSupportFragmentManager(), "sample");
	}


	@Override
	public void onViewModelDestroyed() {
		super.onViewModelDestroyed();
		// Cancel API calls
	}


}
