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
package net.sf.staccato.commons.lang.restriction;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * {@link Restriction}s is a meta-annotation for annotation types that express
 * constraints over annotated elements.
 * </p>
 * <p>
 * Restrictions may express preconditions, postconditions or invariants,
 * depending on the specific annotation type and on the annotated element:
 * <ul>
 * <li>Preconditions means that client <strong>must not</strong> violate the
 * restriction. Doing that is a client code bug, and the annotated element and
 * its context may not behave normally. Implementors <strong>should</strong>
 * throw a {@link RuntimeException} or any subclass of it when this occurs</li>
 * <li>Postconditions and invariants means client code <strong>must</strong>
 * assume the constraint the annotated element does not violate the constraint,
 * and should not check it. If annotated element violates this postcondition,
 * there is a bug in the implementor code</li>
 * </ul>
 * </p>
 * <p>
 * Annotations marked as {@link Restriction} obey the following rules
 * <ol>
 * <li>Restrictions <strong>should</strong> be annotation-processor-agnostic and
 * documentation oriented, that is, its primary design goal is documenting the
 * constraint. As a consequence, these annotations should be {@link Documented}.
 * However, it is valid to process them</li>
 * <li>Restriction annotations <strong>must</strong> preserve their meaning in
 * subtypes</li>
 * <li>
 * <ol>
 * <li>Preconditions restrictions inherited by subtypes
 * <strong>may</strong>relax restrictions but <strong> must
 * not</strong>introduce new ones</li>
 * <li>Postconditions and invariants restrictions inherited by subtypes
 * <strong>must not</strong>relax restrictions but <strong>may</strong>introduce
 * new ones</li>
 * </ol>
 * </li>
 * <li>
 * <ol>
 * <li>Restriction annotations <strong>should</strong> be explicit in code
 * directly exposed to client, but <strong>may</strong> be implicit in the rest
 * of the code
 * <li>Postconditions and invariants restrictions present on supertypes and
 * absent on subtypes <strong>must</strong> be assumed to be still observed.</li>
 * <li>Postconditions restrictions present on supertypes and absent on subtypes
 * <strong>must</strong> be assumed to have being removed</li>
 * </ol>
 * <li>Elements not annotated with restrictions in supertypes but that however
 * observe constraints compatible with some {@link Restriction},
 * <strong>may</strong> be annotated with such Restriction</li>
 * </ol>
 * </p>
 * <p>
 * The concrete way this annotation is handled is by no means described here,
 * and it depends exclusively on its static or dynamic processor. It may even
 * not be processed at all, and server exclusively as documentation.
 * </p>
 */
@Documented
@Inherited
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Restriction {

}
