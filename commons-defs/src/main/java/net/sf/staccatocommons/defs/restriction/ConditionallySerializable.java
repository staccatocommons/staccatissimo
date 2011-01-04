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
package net.sf.staccatocommons.defs.restriction;

import java.io.Serializable;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * {@link ConditionallySerializable} annotated classes that implement the
 * {@link Serializable} interface, but that can be properly serilized or
 * deserialized only as long as their attributes attributes are properly
 * serializable too
 * 
 * {@link ConditionallyImmutable} restriction is normally only useful on classes
 * that have as attributes generic types or interfaces that do not make any
 * assumption about serialization
 * 
 * @author flbulgarelli
 * @see Restriction
 */
@Documented
@Inherited
@Restriction
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface ConditionallySerializable {
	/*
	 * XXX move from package? integrate in a more generic Conditionally
	 * annotation? Another example is conditionally comparable
	 */

}
