<layout xmlns:android="http://schemas.android.com/apk/res/android"
		xmlns:tools="http://schemas.android.com/tools"
		xmlns:app="http://schemas.android.com/apk/res-auto"
    	tools:context=".${screenClass}">

	<data>

		<variable
			name="viewModel"
			type="${packageName}.${viewModelClass}"/>
	</data>
	<#if useStatefulLayout>
	<cz.kinst.jakub.view.StatefulLayout
				android:layout_width="match_parent"
				android:layout_height="match_parent"
				app:state="@{viewModel.state}">
	</#if>
		<LinearLayout
			android:layout_width="match_parent"
			android:layout_height="match_parent"
			android:orientation="vertical">
		</LinearLayout>
	<#if useStatefulLayout>
	</cz.kinst.jakub.view.StatefulLayout>
	</#if>
</layout>
