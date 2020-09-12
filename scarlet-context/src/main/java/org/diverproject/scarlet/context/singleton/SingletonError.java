package org.diverproject.scarlet.context.singleton;

import static org.diverproject.scarlet.util.ScarletUtils.nameOf;

public class SingletonError {

	private SingletonError() { }

	public static SingletonException singletonNotFound(String key, Class<?> className) {
		return new SingletonException(SingletonLanguage.SINGLETON_NOT_FOUND, key, nameOf(className));
	}

	public static SingletonException cannotCastSingleton(Object singletonInstance, Class<?> singletonClass) {
		return new SingletonException(SingletonLanguage.CANNOT_CAST_SINGLETON, nameOf(singletonInstance), nameOf(singletonClass));
	}

	public static SingletonException singletonClassNotAnnotated(Class<?> singletonClass) {
		return new SingletonException(SingletonLanguage.SINGLETON_CLASS_NOT_ANNOTATED, nameOf(singletonClass));
	}
}
