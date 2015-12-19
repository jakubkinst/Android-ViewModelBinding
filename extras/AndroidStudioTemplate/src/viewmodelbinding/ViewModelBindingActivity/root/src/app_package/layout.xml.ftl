<layout xmlns:android="http://schemas.android.com/apk/res/android"
		xmlns:tools="http://schemas.android.com/tools"
    	tools:context=".${screenClass}">

	<data>

		<variable
			name="viewModel"
			type="${packageName}.${viewModelClass}"/>
	</data>

	<LinearLayout
		android:layout_width="match_parent"
		android:layout_height="match_parent"
		android:orientation="vertical">
	</LinearLayout>
</layout>
