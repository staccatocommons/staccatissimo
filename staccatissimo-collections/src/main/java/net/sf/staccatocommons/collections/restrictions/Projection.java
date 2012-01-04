/**
 *  Copyright (c) 2010-2012, The StaccatoCommons Team
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation; version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 */

package net.sf.staccatocommons.collections.restrictions;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.staccatocommons.restrictions.Restriction;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * {@link Projection} restriction signals that the annotated method will not
 * impact the operation on the receptor object, but instead return a new object
 * that represents the result of just operation.
 * 
 * Projection methods <strong>should</strong> return objects that share the same
 * interface of the receptor objection.
 * 
 * Projections <strong>may</strong> be lazy or eager- that is, perform the
 * actual operation on demand or not.
 * 
 * {@link Projection} methods <strong>must</strong> be non null, so there is no
 * need to annotate them as {@link NonNull}, as it is implied.
 * 
 * 
 * @author flbulgarelli
 */
@Restriction
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Projection {}
