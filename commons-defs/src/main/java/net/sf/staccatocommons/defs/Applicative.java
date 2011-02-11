/*
 Copyright (c) 2011, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.defs;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@link Applicable} is an annotation for abstract types that have
 * code-block/command/strategy/closure/lambda semantics, with an only abstract
 * method that implements such code, called applicative method.
 * 
 * <p>
 * In order to remain as general purposes as possible, such types does not
 * impose any {@link Restriction} over the type itself nor its applicative
 * method. There are however some recommendations for implementing it, that will
 * maximize the reusability of concrete classes.
 * 
 * <ul>
 * <li>Implementors <strong>should</strong> be {@link Transparent}, or at least
 * {@link SideEffectFree}. An obvious case where this is not generally possible
 * is for {@link Applicable} that return {@link Void}, which will be applied for
 * its side effects only</li>
 * <li>Implementors <strong>should</strong> be {@link Immutable}, regardless
 * they are {@link SideEffectFree} or not</li>
 * <li>Public available implementors<strong>should</strong> be
 * {@link Serializable}</li>
 * </ul>
 * <p>
 * 
 * @author flbulgarelli
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Applicative {

}
