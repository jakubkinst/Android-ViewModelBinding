package ${packageName};

<#if screenType == "Fragment">
import android.os.Bundle;
</#if>
import cz.kinst.jakub.viewmodelbinding.BaseViewModelActivity;
import cz.kinst.jakub.viewmodelbinding.ViewModelBindingConfig;

public class ${screenClass} extends BaseViewModel${screenType}<${underscoreToCamelCase(layoutName)}Binding, ${viewModelClass}> {

	<#if screenType == "Fragment">
	public static ${screenClass} newInstance() {
		Bundle bundle = new Bundle();
		// set arguments
		${screenClass} fragment = new ${screenClass}();
		fragment.setArguments(bundle);
		return fragment;
	}
	</#if>


    @Override
	public ViewModelBindingConfig getViewModelBindingConfig() {
		return new ViewModelBindingConfig(R.layout.${layoutName}, ${classToResource(screenClass)?cap_first}ViewModel.class, BR.viewModel);
	}
}
