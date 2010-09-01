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
package net.sf.staccato.commons.lang.check.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * {@link CheckAnnotation}s express constraints that annotated element must
 * observe.
 * </p>
 * <p>
 * Meaning of them may vary depending on the particular element type being
 * annotated
 * <ul>
 * <li>When present on attributes, it means that that attribute must never be
 * assigned with objects that violate that constraint at time of assignment, and
 * that those attribute will always obey it.</li>
 * <li>When present on method parameters, it means that an object that violates
 * it must never be passed to an annotated parameter. Annotation express here a
 * method precondition. If client code violates it, the method with annotated
 * parameters may not behave right, and result is by default unspecified.
 * Violating this constrain is a client code bug.</li>
 * <li>When present on non void method, it means that the returned object can
 * not violate that constraint. Annotation express here a method postcondition.
 * Client code can safely treat returned values as if they obey the constraint
 * and should not check it. If annotated method violates this postcondition,
 * there is a bug in that annotated code.</li>
 * </ul>
 * </p>
 * <p>
 * The concrete way this annotation is handled is by no means described here,
 * and it depends exclusively on its static or dynamic processor. It may even
 * not be processed at all, and server exclusively as documentation.
 * </p>
 * 
 * <p>
 * <strong>Notice about inheritance and polymorphism:</strong> Removing/adding
 * {@link CheckAnnotation}s applied to methods arguments have an analogous
 * semantics respect of inheritance and polymorphism that augmenting/reducing
 * method visibility in Java. Overriders and implementors of annotated methods
 * should follow these rules:
 * <ol>
 * <li>Must never introduce extra checks</li>
 * <li>May relax parent checks</li>
 * <li>Inherited checks must be explicit - if not present, client code must
 * assume the parent checks have been relaxed</li>
 * </ol>
 * </p>
 */
@Documented
@Inherited
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.ANNOTATION_TYPE)
public @interface CheckAnnotation {

}
