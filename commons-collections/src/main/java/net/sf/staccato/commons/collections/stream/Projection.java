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
package net.sf.staccato.commons.collections.stream;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@link Projection} annotates {@link Stream} methods that return without
 * performing any processing on its element, but instead, returning a new
 * {@link Stream} that will perform such operations when needed.
 * 
 * Sending such messages has a small, constant, memory penalty, as they create
 * an intermediate object that would be otherwise unnecessary if performed in a
 * non-lazy manner, but are potentially much more efficient, as they do not
 * perform any processing on elements that is not necessary.
 * 
 * TODO: add examples
 * 
 * @author flbulgarelli
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Projection {

}
