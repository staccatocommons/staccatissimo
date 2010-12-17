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
package net.sf.staccato.commons.lang.value;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.staccato.commons.lang.restriction.Restriction;

/**
 * Classes annotated as {@link Transparent} - aka Context Free - are
 * {@link Immutable}s that whose methods depend only on their internal state and
 * by no means on any context, and whose invocation is side-effect free - aside
 * from primitive operation like objects instantiation, invocation stack
 * entering, exception throwing and so on. Thus, method invocation on such
 * objects will produce always the same result.
 * 
 * @author flbulgarelli
 * 
 */
@Documented
@Inherited
@Restriction
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Transparent {

}
