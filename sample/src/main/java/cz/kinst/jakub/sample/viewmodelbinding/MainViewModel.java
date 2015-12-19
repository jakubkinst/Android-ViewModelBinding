package cz.kinst.jakub.sample.viewmodelbinding;

import android.databinding.ObservableField;
import android.view.View;

import cz.kinst.jakub.sample.viewmodelbinding.databinding.ActivityMainBinding;
import cz.kinst.jakub.viewmodelbinding.BaseViewModel;


/**
 * Created by jakubkinst on 10/11/15.
 */
public class MainViewModel extends BaseViewModel<ActivityMainBinding> {

	public ObservableField<String> name = new ObservableField<>();


	@Override
	public void onViewAttached(boolean firstAttachment) {
		super.onViewAttached(firstAttachment);
		if(firstAttachment) {
			// Do API calls
		}
	}


	public void onClickGreetButton(View v) {
		name.set(getBinding().nameEditText.getText().toString());
	}


	@Override
	public void onModelRemoved() {
		super.onModelRemoved();
		// Cancel API calls
	}
}
