package ${packageName};

import android.databinding.ObservableField;
import android.view.View;

import cz.kinst.jakub.viewmodelbinding.BaseViewModel;


public class ${viewModelClass} extends BaseViewModel<${underscoreToCamelCase(layoutName)}Binding> {

	@Override
	public void onViewAttached(boolean firstAttachment) {
		super.onViewAttached(firstAttachment);
		if(firstAttachment) {
			// Do initial setup
		}
	}	
}
