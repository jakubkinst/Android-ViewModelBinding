package cz.kinst.jakub.viewmodelbinding;

import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Stack;


final class ReflectionUtil {

	private ReflectionUtil() {}


	/**
	 * Finds {@link Class} instance of a {@link ViewModel} provided via generics in {@link ViewInterface} implementation.
	 * This method goes up through the whole class hierarchy until it reaches ViewInterface implementation and then goes
	 * down until it detects an explicit definition of generic for the ViewModel.
	 * @param clazz {@link Class} instance of direct or indirect implementation of {@link ViewInterface}
	 * @param viewModelGenericTypePosition position of declared generic type for ViewModel in ViewInterface
	 * @return {@link Class} instance for declared ViewModel generic type or null if the interface is not implemented
	 */
	static Class findViewModelClassDefinition(Class clazz, int viewModelGenericTypePosition) {

		// Look for ViewInterface implementation and save all visited classes to a stack
		Stack<Class> classesStack = new Stack<>();
		ParameterizedType viewInterfaceParamType = searchForInterfaceImplementation(clazz, classesStack);

		// ViewInterface is not implemented by any class in class hierarchy, return null
		if (viewInterfaceParamType == null) return null;

		// Access generic types of ViewInterface
		Type[] genericTypes = viewInterfaceParamType.getActualTypeArguments();
		if (genericTypes.length > viewModelGenericTypePosition) {
			Type interfaceGenericTypeDeclaration = genericTypes[viewModelGenericTypePosition];
			if (interfaceGenericTypeDeclaration instanceof Class) {
				// Generic type is explicitly named right in ViewInterface implementation class
				return (Class) interfaceGenericTypeDeclaration;
			} else if (interfaceGenericTypeDeclaration instanceof TypeVariable) {
				// Generic type is not explicitly defined (described by bounds like "T extends ViewModel"), it's definition needs to be found
				GenericDeclaration declaration = ((TypeVariable) interfaceGenericTypeDeclaration).getGenericDeclaration();
				// Delete all classes from stack that are above the class where generic is defined including that class
				stripEntriesBefore(classesStack, (Class) declaration);
				// Find index of generic declaration
				int indexOfTypeVariable = indexOf(declaration.getTypeParameters(), (TypeVariable) interfaceGenericTypeDeclaration);
				return findViewModelClassDefinition(classesStack, indexOfTypeVariable);
			}
		}
		// Interface is implemented without declaring generic type
		return null;
	}


	private static Class findViewModelClassDefinition(Stack<Class> classesStack, int argumentIndex) {
		// Try to get generic type definition from the next subclass
		Class clazz = classesStack.pop();
		Type[] supertypes = getSuperTypes(clazz);
		for (Type type : supertypes) {
			if(type instanceof ParameterizedType) {
				Type argument = ((ParameterizedType) type).getActualTypeArguments()[argumentIndex];
				if(argument instanceof Class) {
					// Generic type is explicitly named right in ViewInterface implementation class
					return (Class) argument;
				} else if(argument instanceof TypeVariable) {
					// Generic type is not explicitly defined (described by bounds like "T extends ViewModel")
					Type typeVar = ((TypeVariable) argument).getBounds()[0];
					if(typeVar instanceof Class) {
						return (Class) typeVar;
					} else {
						return findViewModelClassDefinition(classesStack, indexOf(clazz.getTypeParameters(), (TypeVariable) argument));
					}
				}
			}
		}
		return null;
	}


	/**
	 * Looks for ViewInterface declaration in hierarchy of interfaces.
	 * @param clazz Class that declares an implementation of ViewInterface or it's subinterface
	 * @param classes Stack of visited classes, represents class hierarchy
	 * @return Parameterized type of ViewInterface's Class instance
	 */
	private static ParameterizedType getInterfaceClassFromHierarchy(Class clazz, Stack<Class> classes) {
		classes.push(clazz);
		Type[] typedSuperInterfaces = clazz.getGenericInterfaces();
		Class[] superInterfaces = clazz.getInterfaces();
		for (int i = 0; i < superInterfaces.length; i++) {
			if(typedSuperInterfaces[i] instanceof ParameterizedType && ((ParameterizedType) typedSuperInterfaces[i]).getRawType() == ViewInterface.class)
				// It's a parameterized type of ViewInterface, found it
				return (ParameterizedType) typedSuperInterfaces[i];
			else {
				// Still not a class of ViewInterface, go upper
				ParameterizedType type = getInterfaceClassFromHierarchy(superInterfaces[i], classes);
				if (type != null) return type;
			}
		}
		return null;
	}


	private static ParameterizedType searchForInterfaceImplementation(Class clazz, Stack<Class> classes) {
		// Save given class to stack of visited classes
		classes.push(clazz);

		Type[] genericInterfaces = clazz.getGenericInterfaces();
		Class[] interfaces = clazz.getInterfaces();
		ParameterizedType viewInterfaceClass = null;
		for (int i = 0; i < genericInterfaces.length; i++) {
			if (genericInterfaces[i] instanceof ParameterizedType) {
				// This interface contains some generic class definitions
				ParameterizedType paramInterface = (ParameterizedType) genericInterfaces[i];
				if (paramInterface.getRawType() == ViewInterface.class) {
					// This interface is ViewInterface
					viewInterfaceClass = paramInterface;
					break;
				} else if (ViewInterface.class.isAssignableFrom(interfaces[i])) {
					// This interface extends ViewInterface, start searching in it's hierarchy
					viewInterfaceClass = getInterfaceClassFromHierarchy(interfaces[i], classes);
					break;
				}
			}
		}
		if(viewInterfaceClass != null) {
			// Implementation of ViewInterface is found
			return viewInterfaceClass;
		} else {
			// This tier of class hierarchy doesn't declare implementation of ViewInterface, go upper if it's possible
			return clazz != Object.class ? searchForInterfaceImplementation(clazz.getSuperclass(), classes) : null;
		}
	}


	private static void stripEntriesBefore(Stack<Class> stack, Class entry) {
		while(stack.peek() != entry) {
			stack.pop();
		}
		stack.pop();
	}


	/**
	 * Finds an index of the given generic type variable in array of generic type variables declaration
	 * @param typeVariables Array of generic type variables declaration
	 * @param typeVariable Generic type variable to find
	 * @return index of the generic type variable in declaration or -1 if it's not found
	 */
	private static int indexOf(TypeVariable[] typeVariables, TypeVariable typeVariable) {
		for (int i = 0; i < typeVariables.length; i++) {
			if (typeVariable.getName().equals(typeVariables[i].getName())) return i;
		}
		return -1;
	}


	/**
	 * Constructs an array of parameterized superclass and parameterized implemented interfaces of the given class.
	 * @param clazz class to collect parameterized superclass and parameterized implemented interfaces from
	 * @return array of parameterized types
	 */
	private static Type[] getSuperTypes(Class clazz) {
		Type[] types = new Type[clazz.getGenericInterfaces().length + 1];
		types[0] = clazz.getGenericSuperclass();
		for (int i = 1; i < types.length; i++) {
			types[i] = clazz.getGenericInterfaces()[i-1];
		}
		return types;
	}
}
