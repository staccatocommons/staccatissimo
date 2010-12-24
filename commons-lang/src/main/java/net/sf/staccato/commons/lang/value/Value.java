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
import java.util.Date;

import net.sf.staccato.commons.lang.restriction.Restriction;

/**
 * 
 * {@link Value} annotation describes objects whose identity is not important,
 * but that are completely described by its state instead, like {@link String}s,
 * {@link Number}s and {@link Date}s.
 * <p>
 * More formally, classes marked as {@link Value} must grant that it is possible
 * to have two instances such that <code>a != b && a.equals(b)</code>
 * </p>
 * <p>
 * Classes annotated this way grant that all their instances observe the
 * following {@link Value} protocol
 * <ul>
 * <li>Implement equality so that it is not based in identity, but in its
 * internal state instead. Values must override {@link #equals(Object)}</li>
 * <li>Implement hashCode to be consistent with equals, in order to be compliant
 * with general {@link Object#hashCode()} contract</li>
 * <li>{@link Value}s implement {@link Object#toString()} in order to provide a
 * descriptive representation of the object state</li>
 * <li>Are {@link Serializable}</li>
 * </ul>
 * <p>
 * Although {@link Value} objects are usually immutable, this annotation does
 * not make any assumption about it, as it only imposes restrictions regarding
 * identity. Mutability aspect is covered by other annotations, like
 * {@link Immutable}, {@link Unmodifiable} and the rest of the annotations
 * defined in this package
 * </p>
 * 
 * @see Immutable
 * @see Unmodifiable
 * @see Restriction
 * @author flbulgarelli
 */
@Documented
@Inherited
@Restriction
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Value {}
