# Android ViewModelBinding

## Intro
A lightweight library aiming to speed up Android app development by leveraging the new [Android Data Binding](http://developer.android.com/tools/data-binding/guide.html) together with the [Model-View-ViewModel](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) design pattern.

## Installation
### Gradle/Maven (jcenter)
    compile 'cz.kinst.jakub:viewmodelbinding:0.3.1'
    
## Usage

### Activity
`MainActivity.java`

    public class MainActivity extends BaseViewModelActivity<ActivityMainBinding, MainViewModel> {
    
    	@Override
    	public int getLayoutResource() {
    		return R.layout.activity_main;
    	}
    
    
    	@Override
    	protected Class<? extends BaseViewModel> getViewModelClass() {
    		return MainViewModel.class;
    	}
    
    
    	@Override
    	public int getViewModelDataBindingId() {
    		return BR.viewModel;
    	}
    }
    
`activity_main.xml`

    <layout xmlns:android="http://schemas.android.com/apk/res/android"
    		xmlns:tools="http://schemas.android.com/tools">
    
    	<data>
    
    		<variable
    			name="viewModel"
    			type="cz.kinst.jakub.sample.viewmodelbinding.MainViewModel"/>
    	</data>
    
    	<LinearLayout
    		android:layout_width="match_parent"
    		android:layout_height="match_parent"
    		android:orientation="vertical">
    
    		<EditText
    			android:id="@+id/name_edit_text"
    			android:layout_width="match_parent"
    			android:layout_height="wrap_content"
    			android:hint="@string/hint_enter_your_name"/>
    
    		<Button
    			android:layout_width="match_parent"
    			android:layout_height="wrap_content"
    			android:onClick="@{viewModel.onClickGreetButton}"
    			android:text="Greet"/>
    
    		<TextView
    			android:layout_width="match_parent"
    			android:layout_height="wrap_content"
    			android:gravity="center"
    			android:text="@{viewModel.name != null &amp;&amp; !viewModel.name.empty ? @string/hello(viewModel.name) : ``}"
    			tools:text="@string/hello"/>
    	</LinearLayout>
    </layout>

    
### ViewModel
`MainViewModel.java`

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
    		name.set(getView().getBinding().nameEditText.getText().toString());
    	}
    
    
    	@Override
    	public void onModelRemoved() {
    		super.onModelRemoved();
    		// Cancel API calls
    	}
    }

### Real usage
For a more complex example of using this approach, see [Weather 2.0](https://github.com/jakubkinst/Weather-2.0) project.

## Authors
- Jakub Kinst (jakub@kinst.cz)
- Stepan Sanda (stepan.sanda@gmail.com)

## License
    Copyright 2015 Jakub Kinst & Stepan Sanda
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
