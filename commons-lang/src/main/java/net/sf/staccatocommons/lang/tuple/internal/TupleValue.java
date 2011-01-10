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

/**
 * @author flbulgarelli
 * 
 */
public abstract class TupleValue<A> {

	private int significantAttributesCount;

	public TupleValue(int significantAttributesCount) {
		super();
		this.significantAttributesCount = significantAttributesCount;
	}

	public int hashCode(A this_) {
		HashCodeCriteria b = new HashCodeCriteria();
		significant(this_, b);
		return b.toHashCode();
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
		qb.setState(TwoPhaseCriteriaState.FIRST_RUN);
		significant(this_, qb);
		qb.setState(TwoPhaseCriteriaState.SECOND_RUN);
		significant((A) that, qb);
	}

	public static interface TwoPhaseCriteria extends Criteria {

		/**
		 * @param o
		 */
		void setCurrentProp(Object o);

		/**
		 * @param firstRun
		 */
		void setState(TwoPhaseCriteriaState firstRun);

		/**
		 * @return
		 */
		Object getCurrentProp();

		public Object append(Object o1, Object o2);

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
		}

		public void setState(TwoPhaseCriteriaState state) {
			this.state = state;
			i = 0;
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
		}

		public void setState(TwoPhaseCriteriaState state) {
			this.state = state;
			i = 0;
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

	public interface Criteria {
		public Criteria with(Object o);
	}

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
