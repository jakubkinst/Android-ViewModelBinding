package ${packageName}<#if vmPackage != "">.${vmPackage}</#if>;

import android.databinding.ObservableField;
import android.view.View;

<#if useStatefulLayout>
import cz.kinst.jakub.view.SimpleStatefulLayout;
</#if>

import cz.kinst.jakub.viewmodelbinding.ViewModel;


public class ${viewModelClass} extends ViewModel {

	<#if useStatefulLayout>
	public final ObservableField<String> state = new ObservableField<>(SimpleStatefulLayout.State.PROGRESS);
	</#if>

	@Override
	public void onViewModelCreated() {
		super.onViewModelCreated();
		// Do initial setup
		<#if useStatefulLayout>
		state.set(SimpleStatefulLayout.State.CONTENT);
		</#if>
	}
}
