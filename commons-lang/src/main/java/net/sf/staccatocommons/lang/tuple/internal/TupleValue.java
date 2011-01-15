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
package net.sf.staccatocommons.lang.tuple.internal;

import net.sf.staccatocommons.lang.value.BasicEquals;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author flbulgarelli
 * 
 */
public abstract class TupleValue<A> {

	private int significantAttributesCount;

	/**
	 * Creates a new {@link TupleValue}
	 * 
	 * @param the
	 *          number of significant properties that determine the value of an
	 *          object of type A
	 */
	public TupleValue(int significantAttributesCount) {
		super();
		this.significantAttributesCount = significantAttributesCount;
	}

	public int hashCode(A this_) {
		HashCodeCriteria b = new HashCodeCriteria();
		significant(this_, b);
		return b.toHashCode();
	}

	public String toString(A this_) {
		ToStringCriteria b = new ToStringCriteria(this_);
		significant(this_, b);
		return b.toString();
	}

	public int compareTo(A this_, A that) {
		if (this_ == that)
			return 0;
		CompareCriteria b = new CompareCriteria(significantAttributesCount);
		twoPhaseSignificant(this_, that, b);
		return b.toComparison();
	}

	public boolean equals(A this_, Object that) {
		BasicEquals be = BasicEquals.from(this_, that);
		if (be.isEqualsDone())
			return be.toEquals();
		EqValueBuilder qb = new EqValueBuilder(significantAttributesCount);
		twoPhaseSignificant(this_, that, qb);
		return qb.isEquals();
	}

	private void twoPhaseSignificant(A this_, Object that, TwoPhaseCriteria qb) {
		significant(this_, qb);
		qb.nextRun();
		significant((A) that, qb);
	}

	public interface Criteria {
		public Criteria with(Object o);
	}

	private static interface TwoPhaseCriteria extends Criteria {

		void setCurrentProp(Object o);

		void nextRun();

		Object getCurrentProp();

		Object append(Object o1, Object o2);

	}

	private static class CompareCriteria extends CompareToBuilder implements TwoPhaseCriteria {

		private TwoPhaseCriteriaState state;
		private int i;
		private Object[] props;

		/**
		 * Creates a new {@link TupleValue.EqValueBuilder}
		 */
		public CompareCriteria(int propsCount) {
			props = new Object[propsCount];
			state = TwoPhaseCriteriaState.FIRST_RUN;
			i = 0;
		}

		public void nextRun() {
			i = 0;
			state = TwoPhaseCriteriaState.SECOND_RUN;
		}

		public Criteria with(Object o) {
			state.with(o, this);
			return this;
		}

		public void setCurrentProp(Object o) {
			props[i++] = o;
		}

		public Object getCurrentProp() {
			return props[i++];
		}

	}

	private class ToStringCriteria extends ToStringBuilder implements Criteria {

		/**
		 * Creates a new {@link ToStringCriteria}
		 */
		public ToStringCriteria(Object object) {
			super(object, TupleToStringStyle.getInstance());
		}

		public Criteria with(Object o) {
			append(o);
			return this;
		}

	}

	private class HashCodeCriteria extends HashCodeBuilder implements Criteria {

		public Criteria with(Object o) {
			append(o);
			return this;
		}
	}

	private static class EqValueBuilder extends EqualsBuilder implements TwoPhaseCriteria {

		private TwoPhaseCriteriaState state;
		private int i;
		private Object[] props;

		/**
		 * Creates a new {@link TupleValue.EqValueBuilder}
		 */
		public EqValueBuilder(int propsCount) {
			props = new Object[propsCount];
			state = TwoPhaseCriteriaState.FIRST_RUN;
			i = 0;
		}

		public void nextRun() {
			i = 0;
			state = TwoPhaseCriteriaState.SECOND_RUN;
		}

		public Criteria with(Object o) {
			state.with(o, this);
			return this;
		}

		public void setCurrentProp(Object o) {
			props[i++] = o;
		}

		public Object getCurrentProp() {
			return props[i++];
		}
	}

	protected abstract void significant(A o, Criteria b);

	private enum TwoPhaseCriteriaState {

		FIRST_RUN {
			void with(Object o, TwoPhaseCriteria eb) {
				eb.setCurrentProp(o);
			}
		},
		SECOND_RUN {
			void with(Object o, TwoPhaseCriteria eb) {
				eb.append(eb.getCurrentProp(), o);
			}
		};
		abstract void with(Object o, TwoPhaseCriteria eb);
	}

}
