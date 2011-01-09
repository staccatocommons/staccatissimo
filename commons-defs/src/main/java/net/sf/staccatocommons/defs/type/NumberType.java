/*
 Copyright (c) 2010, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General License for more details.
 */
package net.sf.staccatocommons.defs.type;

import java.util.Comparator;

import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.restriction.Immutable;
import net.sf.staccatocommons.defs.restriction.SideEffectFree;

/**
 * A Strategy for dealing with {@link Number}s is a polymorphic way.
 * 
 * All its methods <strong>should</strong> be side efect-free, which implies
 * that arguments their <strong>should</strong> not be mutated, and that return
 * values correspond to new numbers.
 * 
 * Although all public JDK {@link Number} classes are immutable, there is no
 * restriction for numbers for being mutable, like for example Apache Commons
 * Lang {@link org.apache.commons.lang.mutable.MutableInt} or
 * {@link org.apache.commons.lang.mutable.MutableDouble}.
 * 
 * In those cases, it is valid implement side-effect-full {@link NumberType}s
 * that may mutate its single or first argument, but such implementation
 * <strong>must not</strong> be passed as arguments of methods or constructor
 * that does not clearly state that accept non side-effect-free
 * {@link NumberType}.
 * 
 * In both cases, implementors {@link NumberType}s <strong>must not</strong>
 * mutate argument second arguments of methods that take two parameters
 * 
 * {@link NumberType} <strong>must</strong> be
 * {@link net.sf.staccatocommons.defs.restriction.Immutable}
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 *          The type of number. This type <strong>should</strong> be a subclass
 *          of type {@link Number}. However, this type bound is not included in
 *          the {@link NumberType} interface by design reasons an to allow
 *          maximum flexibility
 */
@Immutable
public interface NumberType<A> extends Comparator<A> {

	/**
	 * Adds two numbers, and returns the result.
	 * 
	 * @param n0
	 *          first addition operand
	 * @param n1
	 *          second addition operand
	 * @return <code>n0 + n1</code>
	 */
	A add(A n0, A n1);

	/**
	 * Subtracts two numbers, and returns the result.
	 * 
	 * For any {@link Number}s x and y, a side-effect-free {@link NumberType}s nt
	 * <strong>must</strong> grant that
	 * <code>nt.subtract(x, y) == nt.add(x, nt.negate(y))</code>
	 * 
	 * @param n0
	 *          first subtraction operand
	 * @param n1
	 *          second subtraction operand
	 * @return <code>n0 - n1</code>
	 */
	A subtract(A n0, A n1);

	/**
	 * Multiplies two numbers, and returns the result
	 * 
	 * @param n0
	 *          first multiplication operand
	 * @param n1
	 *          second multiplication operand
	 * @return <code>n0 * n1</code>
	 */
	A multiply(A n0, A n1);

	/**
	 * Divides two numbers, and returns the result.
	 * 
	 * @param n0
	 *          first division operand
	 * @param n1
	 *          second division operand
	 * @throws ArithmeticException
	 *           if second argument is zero and the numeric type A does not
	 *           support zero division
	 * @return <code>n0 / n1</code>
	 */
	A divide(A n0, A n1) throws ArithmeticException;

	/**
	 * Negates a given number
	 * 
	 * @param n
	 *          the number to negate
	 * @return <code>-n</code>
	 */
	A negate(A n);

	/**
	 * Decrements the given number.
	 * 
	 * For any {@link Number} x, a side-effect-free {@link NumberType}s nt it
	 * <strong>must</strong> grant that
	 * <code>nt.decrement(x) == nt.subtract(x, nt.one())</code>
	 * 
	 * @param n
	 *          the number to decrement
	 * @return <code>n - 1</code>
	 */
	A decrement(A n);

	/**
	 * Increments a given number
	 * 
	 * For any {@link Number} x, a side-effect-free {@link NumberType}s nt
	 * <strong>must</strong> grant that
	 * <code>nt.increment(x) == nt.add(x, nt.one())</code>
	 * 
	 * @param n
	 *          the number increment
	 * @return <code>n + 1</code>
	 */
	A increment(A n);

	/**
	 * Answers if the given number is greater than zero
	 * 
	 * * For any {@link Number} x, a {@link NumberType}s nt <strong>must</strong>
	 * grant that <code>nt.isZero(x) == (nt.compare(x, zero()) > 0)</code>
	 * 
	 * @param n
	 * @return if the number is positive
	 */
	@SideEffectFree
	boolean isPositive(A n);

	/**
	 * Answers if the given number is less than zero
	 * 
	 * For any {@link Number} x, a {@link NumberType}s nt <strong>must</strong>
	 * grant that <code>nt.isZero(x) == (nt.compare(x, zero()) < 0)</code>
	 * 
	 * @param n
	 * @return if the number is negative
	 */
	@SideEffectFree
	boolean isNegative(A n);

	/**
	 * Answers if the given number is zero.
	 * 
	 * For any {@link Number} x, a {@link NumberType}s nt <strong>must</strong>
	 * grant that <code>nt.isZero(x) == (nt.compare(x, zero()) == 0)</code>
	 * 
	 * @param n
	 * @return if the number is zero
	 */
	@SideEffectFree
	boolean isZero(A n);

	/**
	 * Answers the representation of 0 for this {@link NumberType}
	 * 
	 * @return the addition identity element
	 */
	@SideEffectFree
	A zero();

	/**
	 * Answers the representation of 1 for this {@link NumberType}
	 * 
	 * @return the multiplication identity element
	 */
	@SideEffectFree
	A one();

	/**
	 * Answers a 2-arguments function that perform addition as specified by
	 * {@link #add(Object, Object)}
	 * 
	 * @return a function that adds its two arguments using this
	 *         {@link NumberType}
	 */
	@SideEffectFree
	Applicable2<A, A, A> add();

	/**
	 * Answers a 2-arguments function that perform multiplication as specified by
	 * {@link #multiply(Object, Object)}
	 * 
	 * @return a function that multiplies its two arguments using this
	 *         {@link NumberType}
	 */
	@SideEffectFree
	Applicable2<A, A, A> multiply();

}
