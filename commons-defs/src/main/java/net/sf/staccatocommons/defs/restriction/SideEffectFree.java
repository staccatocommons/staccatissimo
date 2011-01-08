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
package net.sf.staccatocommons.defs.restriction;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A {@link Restriction} that applies to methods that <strong>must not</strong>
 * have side effects. Methods with such restriction are said to be <i>pure</i>
 * 
 * <p>
 * In particular such methods <strong>must not</strong> modify the state of any
 * of its arguments nor the <code>this</code> reference.
 * </p>
 * 
 * @author flbulgarelli
 * @see <a
 *      href="http://en.wikipedia.org/wiki/Side_effect_(computer_science)">Side effect</a>
 */
@Restriction
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface SideEffectFree {

}
