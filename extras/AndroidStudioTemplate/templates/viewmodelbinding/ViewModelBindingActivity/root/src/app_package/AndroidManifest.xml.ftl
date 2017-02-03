<manifest xmlns:android="http://schemas.android.com/apk/res/android" >

    <application>
            <activity android:name="${relativePackage}.<#if scrPackage != "">${scrPackage}.</#if>${screenClass}"
                <#if isNewProject>
                    android:label="@string/app_name"
                <#else>
                    android:label="@string/title_${classToResource(screenClass)}"
                </#if>
                >
                <#if isLauncher && !(isLibraryProject!false)>
                    <intent-filter>
                        <action android:name="android.intent.action.MAIN" />
                        <category android:name="android.intent.category.LAUNCHER" />
                    </intent-filter>
                </#if>
            </activity>
    </application>
</manifest>
