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


package net.sf.staccatocommons.restrictions.effect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.staccatocommons.restrictions.Restriction;
import net.sf.staccatocommons.restrictions.value.Unmodifiable;

/**
 * Side effect free is a {@link Restriction} that applies to methods that have
 * no side effects. As a consequence, such methods <strong>must not</strong>
 * modify the state of its arguments, if any, nor the <code>this</code>
 * reference, if non-static, nor any attribute in scope.
 * <p>
 * When applied to types, it means that all its methods are
 * {@link SideEffectFree}. As a consequence, statefull classes that are
 * annotated this way <strong>must</strong> be ummodifiable, as mutators are
 * inheritely non-side-effect free. Thus there is no reason no annotate
 * {@link SideEffectFree} classes as {@link Unmodifiable}, as it is implicit.
 * </p>
 * <p>
 * Being {@link SideEffectFree} is necessary but not sufficient for being
 * referential transparent, thus methods annotated this way <strong>may</strong>
 * return different results for the same arguments, if any. Such methods should
 * be annotated with {@link Transparent} instead.
 * </p>
 * 
 * @author flbulgarelli
 * @see <a
 *      href="http://en.wikipedia.org/wiki/Side_effect_(computer_science)">Side
 *      effect</a>
 * @see Transparent
 */
@Restriction
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface SideEffectFree {

}
