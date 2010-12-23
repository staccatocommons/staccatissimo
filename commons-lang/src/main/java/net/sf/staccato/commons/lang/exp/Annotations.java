package net.sf.staccato.commons.lang.exp;

/*
 Copyright (c) 2010, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */

import java.lang.annotation.Annotation;

import net.sf.staccato.commons.check.annotation.NonNull;
import net.sf.staccato.commons.lang.Option;

/**
 * @author flbulgarelli
 * 
 */
public class Annotations {

	private Annotations() {
	}

	@NonNull
	public static <A extends Annotation> Option<A> getAnnotation(@NonNull Object object,
		@NonNull Class<A> annotationClass) {
		return Option.nullToNone(getAnnotationInternal(object, annotationClass));
	}

	public static <A extends Annotation> boolean isAnnotated(@NonNull Object object,
		@NonNull Class<A> annotationClass) {
		return getAnnotationInternal(object, annotationClass) != null;
	}

	private static <A extends Annotation> A getAnnotationInternal(Object object,
		Class<A> annotationClass) {
		return object.getClass().getAnnotation(annotationClass);
	}

}
