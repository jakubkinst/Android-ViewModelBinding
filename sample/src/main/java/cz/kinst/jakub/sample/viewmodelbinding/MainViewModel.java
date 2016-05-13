package cz.kinst.jakub.sample.viewmodelbinding;

import android.databinding.ObservableField;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cz.kinst.jakub.itemviewmodelbinding.ItemViewModel;
import cz.kinst.jakub.itemviewmodelbinding.RecyclerViewConfig;
import cz.kinst.jakub.sample.viewmodelbinding.databinding.ActivityMainBinding;
import cz.kinst.jakub.viewmodelbinding.ViewModel;


/**
 * Created by jakubkinst on 10/11/15.
 */
public class MainViewModel extends ViewModel<ActivityMainBinding> implements ItemViewModel.ItemHandler<ItemEntity> {

	private static final int VIEW_TYPE_ITEM = 1;
	private static final int VIEW_TYPE_ITEM_SECONDARY = 2;
	public ObservableField<String> name = new ObservableField<>();
	private SampleDialogFragment dialog;

	public RecyclerViewConfig itemConfig = RecyclerViewConfig.create()
			.addViewType(VIEW_TYPE_ITEM, R.layout.item)
			.addViewType(VIEW_TYPE_ITEM_SECONDARY, R.layout.item_secondary);


	@Override
	public void onViewModelCreated() {
		super.onViewModelCreated();
		List<ItemViewModel<?>> list = new ArrayList<>();
		list.add(new ItemViewModel<>(VIEW_TYPE_ITEM, new ItemEntity("Title", "Subtitle"), this));
		list.add(new ItemViewModel<>(VIEW_TYPE_ITEM_SECONDARY, new ItemEntity("Title", "Subtitle"), this));
		list.add(new ItemViewModel<>(VIEW_TYPE_ITEM, new ItemEntity("Title", "Subtitle"), this));
		list.add(new ItemViewModel<>(VIEW_TYPE_ITEM_SECONDARY, new ItemEntity("Title", "Subtitle"), this));
		list.add(new ItemViewModel<>(VIEW_TYPE_ITEM, new ItemEntity("Title", "Subtitle"), this));
		itemConfig.setItems(list);
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


	@Override
	public void onItemClicked(View view, ItemEntity item) {

	}
}
