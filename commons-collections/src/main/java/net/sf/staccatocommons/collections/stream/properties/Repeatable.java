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
package net.sf.staccatocommons.collections.stream.properties;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.staccatocommons.collections.stream.Stream;

/**
 * {@link Repeatable} annotates methods that return {@link Stream}s that grant
 * to have an <strong>efficient</strong> repeatable iteration order - that is,
 * subsequent acess the the same position returns equal objects - as long as
 * such {@link Stream}s sources are not mutated.
 * 
 * @see Stream Repetable iteration order
 * 
 * @author flbulgarelli
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Repeatable {}
