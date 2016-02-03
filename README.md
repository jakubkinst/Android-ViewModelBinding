# Android ViewModelBinding
[![Build Status](https://travis-ci.org/jakubkinst/Android-ViewModelBinding.svg?branch=master)](https://travis-ci.org/jakubkinst/Android-ViewModelBinding)
## Intro
A lightweight library aiming to speed up Android app development by leveraging the new [Android Data Binding](http://developer.android.com/tools/data-binding/guide.html) together with the [Model-View-ViewModel](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) design pattern.

## Installation
### Gradle/Maven (jcenter)
    compile 'cz.kinst.jakub:viewmodelbinding:0.8'
    
## Usage

### Lifecycle
![Lifecycle Diagram](extras/diagram.png){:style="float: right"}
Lifecycle diagram

### Activity
`MainActivity.java`

```java
public class MainActivity extends ViewModelActivity<ActivityMainBinding, MainViewModel> {

	@Override
	public ViewModelBindingConfig getViewModelBindingConfig() {
		return new ViewModelBindingConfig(R.layout.activity_main, MainViewModel.class);
	}
}
```
    
`activity_main.xml`

```xml
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
```

    
### ViewModel
`MainViewModel.java`

```java
public class MainViewModel extends ViewModel<ActivityMainBinding> {

	public ObservableField<String> name = new ObservableField<>();

	@Override
	public void onViewModelCreated() {
		super.onViewModelCreated();
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

	@Override
	public void onViewModelDestroyed() {
		super.onViewModelDestroyed();
		// Cancel API calls
	}
}
```

### Real usage
For a more complex example of using this approach, see [Weather 2.0](https://github.com/jakubkinst/Weather-2.0) project.

### Android Studio New Screen Template
To deploy new screens even faster, use the included [Android Studio Template](/extras/AndroidStudioTemplate) (`revision 2`)

![Android Studio Template](/extras/AndroidStudioTemplate/screen.png)
#### Usage
1. Copy the template folder to Android Studio templates folder (`/Applications/Android Studio.app/Contents/plugins/android/lib/templates/` on Mac) OR run the following command to download and install the template automatically

		curl -o viewmodelbinding.zip -Lk https://github.com/jakubkinst/Android-ViewModelBinding/archive/master.zip && unzip viewmodelbinding.zip && cp -af Android-ViewModelBinding-master/extras/AndroidStudioTemplate/templates/. "/Applications/Android Studio.app/Contents/plugins/android/lib/templates/" && rm -r Android-ViewModelBinding-master && rm viewmodelbinding.zip
2. Restart Android Studio
3. Use `File>New>ViewModelBinding>ViewModelBinding Screen` action to add a new screen

## Changelog

#### v0.8 (Jan 19, 2016)
- ViewModelConfig can be created without `BR.viewModel` as long as the name ov the binding variable is `viewModel`
- Added `onViewModelCreated()` callback in ViewModel
- *BREAKING* Renamed ~~`onModelRemoved()`~~ to `onViewModelDestroyed()` callback in ViewModel
- Added `getResources()` convenience method to ViewModel

## Authors
- Jakub Kinst (jakub@kinst.cz)
- Stepan Sanda (stepan.sanda@gmail.com)

The library was inspired by a great [AndroidViewModel](https://github.com/inloop/AndroidViewModel) library by Inloop

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
