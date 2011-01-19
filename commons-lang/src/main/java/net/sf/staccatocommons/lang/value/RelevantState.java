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
package net.sf.staccatocommons.lang.value;

import net.sf.staccatocommons.check.annotation.NonNull;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * An abstract class that is able of identifying the relevant attributes of an
 * object, and uses them to implements value comparisons -
 * {@link Object#equals(Object)} and {@link Comparable#compareTo(Object)} -
 * {@link Object#toString()} and {@link Object#hashCode()} in a consistent way
 * 
 * @author flbulgarelli
 */
public abstract class RelevantState<A> {

	private final int relevantAttributesCount;
	private final ToStringStyle toStringStyle;

	/**
	 * Creates a new {@link RelevantState}
	 * 
	 * @param relevantAttributesCount
	 *          number of significant properties that determine the value of an
	 *          object of type A
	 * @param toStringStyle
	 */
	public RelevantState(int relevantAttributesCount, @NonNull ToStringStyle toStringStyle) {
		super();
		this.relevantAttributesCount = relevantAttributesCount;
		this.toStringStyle = toStringStyle;
	}

	/**
	 * Creates a new {@link RelevantState}, using
	 * {@link ToStringBuilder#getDefaultStyle()} as <code>toStringStyle</code>
	 * 
	 * 
	 * @param relevantAttributesCount
	 *          number of significant properties that determine the value of an
	 *          object of type A
	 */
	public RelevantState(int relevantAttributesCount) {
		this(relevantAttributesCount, ToStringBuilder.getDefaultStyle());
	}

	/**
	 * Answers the hashcode for the given object, based on the attributes
	 * collected by {@link #collectState(Object, StateCollector)}
	 * 
	 * It is safe to use the result as an implementation of
	 * {@link Object#hashCode()} for <code>object</code>
	 * 
	 * @param object
	 * @return a hash code computed using the relevant fields of the given object
	 */
	public int hashCode(@NonNull A object) {
		HashCodeStateBuilder b = new HashCodeStateBuilder();
		collectState(object, b);
		return b.toHashCode();
	}

	/***
	 * Answers a string representation for the given object, based on the
	 * attributes collected by {@link #collectState(Object, StateCollector)}
	 * 
	 * It is safe to use the result as an implementation of
	 * {@link Object#toString()} for <code>object</code>
	 * 
	 * @param object
	 * @return a to string implementation for <code>object</code>, using the style
	 *         provided by {@link #getToStringStyle()}
	 */
	public String toString(@NonNull A object) {
		ToStringCriteria b = new ToStringCriteria(object, toStringStyle);
		collectState(object, b);
		return b.toString();
	}

	/**
	 * Compares the given <code>object</code> with <code>other</code>, based on
	 * the fields collected by {@link #collectState(Object, StateCollector)}.
	 * 
	 * It is safe to use the result as an implementation of
	 * {@link Comparable#compareTo(Object)} for <code>object</code>
	 * 
	 * @param object
	 * @param other
	 * @return 0 if both arguments are the same, or the result of a field by field
	 *         comparison
	 */
	public int compareTo(@NonNull A object, A other) {
		if (object == other)
			return 0;
		CompareStateBuilder b = new CompareStateBuilder(relevantAttributesCount);
		collectStateInTwoPhases(object, other, b);
		return b.toComparison();
	}

	/**
	 * Answers if the given <code>object</code> is equal to <code>other</code>,
	 * based on the fields collected by
	 * {@link #collectState(Object, StateCollector)}
	 * 
	 * It is safe to use the result as an implementation of
	 * {@link Object#equals(Object)} for <code>object</code>
	 * 
	 * @param object
	 * @param other
	 * @return the result of a {@link BasicEquals} test if it is enough to
	 *         determine equality, or a field by field comparison, otherwise
	 * @see BasicEquals
	 */
	public boolean equals(@NonNull A object, Object other) {
		BasicEquals be = BasicEquals.from(object, other);
		if (be.isEqualsDone())
			return be.toEquals();
		EqualsStateBuilder sb = new EqualsStateBuilder(relevantAttributesCount);
		collectStateInTwoPhases(object, other, sb);
		return sb.isEquals();
	}

	private void collectStateInTwoPhases(A object, Object other, TwoPhaseStateBuilder sb) {
		collectState(object, sb);
		sb.setPropertyIndex(0);
		sb.setState(TwoPhaseStateBuilderState.SECOND_RUN);
		collectState((A) other, sb);
	}

	/**
	 * Collects the attributes that conform the relevant state of the given
	 * <code>object</code>, by adding them to the given {@link StateCollector}
	 * 
	 * @param object
	 * @param s
	 *          the {@link StateCollector} to which
	 */
	protected abstract void collectState(@NonNull A object, @NonNull StateCollector s);

	/**
	 * The {@link ToStringStyle} used to create toString representations
	 * 
	 * @return the toStringStyle
	 */
	public ToStringStyle getToStringStyle() {
		return toStringStyle;
	}

	/**
	 * An object for collecting the attributes of an object that are part of its
	 * relevant state
	 * 
	 * @author flbulgarelli
	 */
	public interface StateCollector {

		/**
		 * Adds an attribute to the object's state
		 * 
		 * @param attribute
		 *          an attribute to add to the relevant state
		 * @return this builder
		 */
		public StateCollector add(Object attribute);
	}

	private static interface TwoPhaseStateBuilder extends StateCollector {

		void setState(TwoPhaseStateBuilderState state);

		void setPropertyIndex(int index);

		Object append(Object o1, Object o2);

		Object[] getProperties();

		int getPropertyIndex();

	}

	private static final class CompareStateBuilder extends CompareToBuilder implements
		TwoPhaseStateBuilder {

		private TwoPhaseStateBuilderState state;
		private int propertyIndex;
		private Object[] properties;

		/**
		 * Creates a new {@link RelevantState.EqualsStateBuilder}
		 */
		public CompareStateBuilder(int propsCount) {
			properties = new Object[propsCount];
			state = TwoPhaseStateBuilderState.FIRST_RUN;
			propertyIndex = 0;
		}

		public StateCollector add(Object o) {
			state.with(o, this);
			return this;
		}

		public Object[] getProperties() {
			return properties;
		}

		public int getPropertyIndex() {
			return propertyIndex;
		}

		public void setPropertyIndex(int propertyIndex) {
			this.propertyIndex = propertyIndex;
		}

		public void setState(TwoPhaseStateBuilderState state) {
			this.state = state;
		}

	}

	private static final class ToStringCriteria extends ToStringBuilder implements StateCollector {

		/**
		 * Creates a new {@link ToStringCriteria}
		 * 
		 * @param toStrinStyle
		 */
		public ToStringCriteria(Object object, ToStringStyle toStrinStyle) {
			super(object, toStrinStyle);
		}

		public StateCollector add(Object o) {
			append(o);
			return this;
		}

	}

	private static final class HashCodeStateBuilder extends HashCodeBuilder implements StateCollector {
		public StateCollector add(Object o) {
			append(o);
			return this;
		}
	}

	private static class EqualsStateBuilder extends EqualsBuilder implements TwoPhaseStateBuilder {

		private TwoPhaseStateBuilderState state;
		private int propertyIndex;
		private Object[] properties;

		/**
		 * Creates a new {@link RelevantState.EqualsStateBuilder}
		 */
		public EqualsStateBuilder(int propsCount) {
			properties = new Object[propsCount];
			state = TwoPhaseStateBuilderState.FIRST_RUN;
			propertyIndex = 0;
		}

		public StateCollector add(Object o) {
			state.with(o, this);
			return this;
		}

		public int getPropertyIndex() {
			return propertyIndex;
		}

		public void setPropertyIndex(int propertyIndex) {
			this.propertyIndex = propertyIndex;
		}

		public Object[] getProperties() {
			return properties;
		}

		public void setState(TwoPhaseStateBuilderState state) {
			this.state = state;
		}

	}

	private enum TwoPhaseStateBuilderState {

		FIRST_RUN {
			void with(Object o, TwoPhaseStateBuilder eb) {
				int i = eb.getPropertyIndex();
				eb.getProperties()[i] = o;
				eb.setPropertyIndex(i + 1);
			}
		},
		SECOND_RUN {
			void with(Object o, TwoPhaseStateBuilder eb) {
				int i = eb.getPropertyIndex();
				eb.append(eb.getProperties()[i], o);
				eb.setPropertyIndex(i + 1);
			}
		};
		abstract void with(Object o, TwoPhaseStateBuilder eb);
	}

}