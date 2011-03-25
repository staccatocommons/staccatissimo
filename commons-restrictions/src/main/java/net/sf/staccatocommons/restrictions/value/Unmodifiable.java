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
package net.sf.staccatocommons.restrictions.value;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.staccatocommons.restrictions.Restriction;

/**
 * {@link Unmodifiable}s are objects that do not expose publicly any method that
 * may modify receiver internal state. However, object's state can still be
 * mutated indirectly, if it holds and/or exposes references to objects that may
 * be mutated
 * <p>
 * {@link Unmodifiable}s can still implement lazy initialization and/or caching,
 * as long as it remains encapsulated and is not exposed publicly
 * </p>
 * 
 * @author flbulgarelli
 * @see Restriction
 */
@Documented
@Inherited
@Restriction
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Unmodifiable {

}
