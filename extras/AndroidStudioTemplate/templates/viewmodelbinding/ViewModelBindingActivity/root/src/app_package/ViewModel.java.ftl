package ${packageName}.${vmPackage};

import android.databinding.ObservableField;
import android.view.View;

<#if useStatefulLayout>
import cz.kinst.jakub.view.StatefulLayout;
</#if>

import cz.kinst.jakub.viewmodelbinding.ViewModel;


public class ${viewModelClass} extends ViewModel<${underscoreToCamelCase(layoutName)}Binding> {

	<#if useStatefulLayout>
	public final ObservableField<StatefulLayout.State> state = new ObservableField<>(StatefulLayout.State.PROGRESS);
	</#if>

	@Override
	public void onViewModelCreated() {
		super.onViewModelCreated();
		// Do initial setup
		<#if useStatefulLayout>
		state.set(StatefulLayout.State.CONTENT);
		</#if>
	}
}
