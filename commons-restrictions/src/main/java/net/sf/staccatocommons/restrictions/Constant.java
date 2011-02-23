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
package net.sf.staccatocommons.restrictions;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.staccatocommons.restrictions.effect.SideEffectFree;
import net.sf.staccatocommons.restrictions.effect.Transparent;

/**
 * {@link Restriction} that denotes that the return value of a method is always
 * the same object. <strong>This does not necessary mean that the returned
 * object is a singleton</strong>.
 * <p>
 * Although theoretically a method may be constant and still not
 * {@link Transparent} - because of not being {@link SideEffectFree}, in
 * practice this is a bug source. Thus, {@link Constant} annotated methods
 * <strong>must</strong> be {@link Transparent}, and they do not need to be
 * annotated with that annotation too, as it is implied.
 * </p>
 * 
 * @author flbulgarelli
 */
@Documented
@Restriction
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface Constant {

}
