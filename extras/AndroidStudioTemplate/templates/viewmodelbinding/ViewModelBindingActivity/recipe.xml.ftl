<?xml version="1.0"?>
<recipe>

    <#if appCompat && !(hasDependency('cz.kinst.jakub:viewmodelbinding'))>
    	<dependency mavenUrl="cz.kinst.jakub:viewmodelbinding:0.8.3"/>
	</#if>
	<#if useStatefulLayout && !(hasDependency('cz.kinst.jakub:stateful-layout'))>
        <dependency mavenUrl="cz.kinst.jakub:stateful-layout:1.1.1"/>
	</#if>

    <#if screenType == "Activity">
        <merge from="root/src/app_package/AndroidManifest.xml.ftl"
                 to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />
        <merge from="root/src/app_package/strings.xml.ftl"
                 to="${escapeXmlAttribute(resOut)}/values/strings.xml" />
    </#if>

    <instantiate from="root/src/app_package/layout.xml.ftl"
                 to="${escapeXmlAttribute(resOut)}/layout/${layoutName}.xml" />

    <instantiate from="root/src/app_package/Screen.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${screenClass}.java" />

    <instantiate from="root/src/app_package/ViewModel.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${viewModelClass}.java" />

    <open file="${escapeXmlAttribute(srcOut)}/${screenClass}.java" />
    <open file="${escapeXmlAttribute(resOut)}/layout/${layoutName}.xml" />
    <open file="${escapeXmlAttribute(srcOut)}/${viewModelClass}.java" />
</recipe>
