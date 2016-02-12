package cz.kinst.jakub.viewmodelbinding;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;


final class ReflectionUtil {

	private ReflectionUtil() {}

	static Class findViewModelClassDefinition(Class clazz, int viewModelGenericClassPosition) {
		Type superType = clazz.getGenericSuperclass();
		if (superType instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) superType;
			if (parameterizedType.getActualTypeArguments().length > viewModelGenericClassPosition) {
				if (parameterizedType.getActualTypeArguments()[viewModelGenericClassPosition] instanceof Class) {
					Class genericClass = (Class) parameterizedType.getActualTypeArguments()[viewModelGenericClassPosition];
					if (ViewModel.class.isAssignableFrom(genericClass)) {
						return genericClass;
					}
				}
				if (parameterizedType.getActualTypeArguments()[viewModelGenericClassPosition] instanceof TypeVariable) {
					TypeVariable typeVariable = (TypeVariable) parameterizedType.getActualTypeArguments()[viewModelGenericClassPosition];
					for (Type type : typeVariable.getBounds()) {
						if (type instanceof Class){
							Class genericTypeClass = (Class) type;
							if (ViewModel.class.isAssignableFrom(genericTypeClass)) {
								return genericTypeClass;
							}
						}
					}
				}
			}
		}

		Class superClass = clazz.getSuperclass();
		if (superClass != ViewModelActivity.class && superClass != ViewModelFragment.class && superClass != ViewModelDialogFragment.class)
			return findViewModelClassDefinition((Class) superClass, viewModelGenericClassPosition);
		else return null;
	}
}
