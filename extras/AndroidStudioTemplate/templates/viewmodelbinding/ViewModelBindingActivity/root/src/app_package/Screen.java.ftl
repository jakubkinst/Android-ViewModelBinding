package ${packageName}<#if scrPackage != "">.${scrPackage}</#if>;

import android.os.Bundle;
import cz.kinst.jakub.viewmodelbinding.ViewModelActivity;
import cz.kinst.jakub.viewmodelbinding.ViewModelBindingConfig;

public class ${screenClass} extends ViewModel${screenType}<${underscoreToCamelCase(layoutName)}Binding, ${viewModelClass}> {

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
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		setupViewModel(R.layout.${layoutName}, ${classToResource(screenClass)?cap_first}ViewModel.class);
		super.onCreate(savedInstanceState);
	}
}
