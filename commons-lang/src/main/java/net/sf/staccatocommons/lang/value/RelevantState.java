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

import java.util.Comparator;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.Evaluable2;
import net.sf.staccatocommons.defs.restriction.Transparent;
import net.sf.staccatocommons.lang.value.RelevantState.StateCollector;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * An abstract class that is capable of identifying the relevant attributes of
 * an object and implementing with them value comparisons, hashing and string
 * representation in a consisten way.
 * <p>
 * Normal usage of {@link RelevantState} is declaring an implementor per class
 * of type &lt;A&gt;, having a singleton instance of it, and then delegating
 * {@link Object#equals(Object)}, {@link Object#hashCode()} etc, to it. For
 * example:
 * 
 * <pre>
 * class Customer {
 *  
 *   private String name;
 *   private Date birthday;
 *   private Date joinDate;
 *   
 *   .....
 *   public int hashCode() {
 *    return state.hashCode(this);
 *   }
 * 	
 *   public boolean equals(Object obj) {
 *    return state.equals(this, obj);
 *   }
 *   
 *   public String toString() {
 *    return val.toString(this);
 *   }
 *   .....
 *   
 *   private static RelevantState&lt;&lt;&gt; state = new TupleState&lt;Customer&gt;(3) {
 *    protected void collectState(Customer o, StateCollector c) {
 *     c.add(o.name).add(o.birthday).add(joinDate); 
 * 		} 
 *   };
 * }
 * 
 * </pre>
 * 
 * In order to do that, {@link RelevantState} instances <strong>must</strong> be
 * {@link Transparent}, allowing them to be shared by instances and threads.
 * </p>
 * <p>
 * {@link RelevantState} is completely reflection-free, and is built on top of
 * {@link EqualsBuilder}, {@link ToStringBuilder}, {@link HashCodeBuilder} and
 * {@link CompareToBuilder}.
 * </p>
 * 
 * 
 * @see Object#equals(Object)
 * @see Object#hashCode()
 * @see Object#toString()
 * @see Comparable#compareTo(Object)
 * 
 * @param <A>
 *          the type of object this {@link RelevantState} applies to
 * @author flbulgarelli
 */
@Transparent
public abstract class RelevantState<A> implements Comparator<A>, Evaluable2<A, A> {

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
	 * Creates a new {@link RelevantState}, using {@link NamedTupleToStringStyle}
	 * as <code>toStringStyle</code>
	 * 
	 * 
	 * @param relevantAttributesCount
	 *          number of significant properties that determine the value of an
	 *          object of type A
	 */
	public RelevantState(int relevantAttributesCount) {
		this(relevantAttributesCount, NamedTupleToStringStyle.getInstance());
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

	public int compare(A o1, A o2) {
		return compareTo(o1, o2);
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

	public boolean eval(A arg0, A arg1) {
		if (arg0 == null)
			return arg1 == null;
		return equals(arg0, arg1);
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
		StateCollector add(Object attribute);

		/**
		 * Adds an int attribute to the object's state
		 * 
		 * @param attribute
		 *          an attribute to add to the relevant state
		 * @return this builder
		 */
		StateCollector add(int attribute);

		/**
		 * Adds a long attribute to the object's state
		 * 
		 * @param attribute
		 *          an attribute to add to the relevant state
		 * @return this builder
		 */
		StateCollector add(long attribute);

		/**
		 * Adds a boolean attribute to the object's state
		 * 
		 * @param attribute
		 *          an attribute to add to the relevant state
		 * @return this builder
		 */
		StateCollector add(boolean attribute);
	}

}

interface TwoPhaseStateBuilder extends StateCollector {

	void setState(TwoPhaseStateBuilderState state);

	void setPropertyIndex(int index);

	Object append(Object o1, Object o2);

	Object append(long o1, long o2);

	Object append(int o1, int o2);

	Object append(boolean o1, boolean o2);

	Object[] getProperties();

	int getPropertyIndex();

}

final class CompareStateBuilder extends CompareToBuilder implements TwoPhaseStateBuilder {

	private TwoPhaseStateBuilderState state;
	private int propertyIndex;
	private Object[] properties;

	/**
	 * Creates a new {@link RelevantState.CompareStateBuilder}
	 */
	public CompareStateBuilder(int propsCount) {
		properties = new Object[propsCount];
		state = TwoPhaseStateBuilderState.FIRST_RUN;
		propertyIndex = 0;
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

	public StateCollector add(Object o) {
		state.with(o, this);
		return this;
	}

	public StateCollector add(boolean attribute) {
		state.with(attribute, this);
		return this;
	}

	public StateCollector add(int attribute) {
		state.with(attribute, this);
		return this;
	}

	public StateCollector add(long attribute) {
		state.with(attribute, this);
		return this;
	}

}

final class ToStringCriteria extends ToStringBuilder implements StateCollector {

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

	public StateCollector add(boolean attribute) {
		append(attribute);
		return this;
	}

	public StateCollector add(int attribute) {
		append(attribute);
		return this;
	}

	public StateCollector add(long attribute) {
		append(attribute);
		return this;
	}

}

final class HashCodeStateBuilder extends HashCodeBuilder implements StateCollector {
	public StateCollector add(Object o) {
		append(o);
		return this;
	}

	public StateCollector add(int attribute) {
		append(attribute);
		return this;
	}

	public StateCollector add(boolean attribute) {
		append(attribute);
		return this;
	}

	public StateCollector add(long attribute) {
		append(attribute);
		return this;
	}
}

final class EqualsStateBuilder extends EqualsBuilder implements TwoPhaseStateBuilder {

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

	public StateCollector add(Object o) {
		state.with(o, this);
		return this;
	}

	public StateCollector add(int attribute) {
		state.with(attribute, this);
		return this;
	}

	public StateCollector add(boolean attribute) {
		state.with(attribute, this);
		return this;
	}

	public StateCollector add(long attribute) {
		state.with(attribute, this);
		return this;
	}

}

enum TwoPhaseStateBuilderState {

	FIRST_RUN {
		void with(Object o, TwoPhaseStateBuilder eb) {
			eb.getProperties()[nextIndex(eb)] = o;
		}
	},
	SECOND_RUN {
		void with(Object o, TwoPhaseStateBuilder eb) {
			eb.append(eb.getProperties()[nextIndex(eb)], o);
		}

		void with(boolean o, TwoPhaseStateBuilder eb) {
			eb.append(((Boolean) eb.getProperties()[nextIndex(eb)]).booleanValue(), o);
		}

		void with(int o, TwoPhaseStateBuilder eb) {
			eb.append(((Integer) eb.getProperties()[nextIndex(eb)]).intValue(), o);
		}

		void with(long o, TwoPhaseStateBuilder eb) {
			eb.append(((Long) eb.getProperties()[nextIndex(eb)]).longValue(), o);
		}
	};
	abstract void with(Object o, TwoPhaseStateBuilder eb);

	void with(boolean o, TwoPhaseStateBuilder eb) {
		with((Boolean) o, eb);
	};

	void with(int o, TwoPhaseStateBuilder eb) {
		with((Integer) o, eb);
	}

	void with(long o, TwoPhaseStateBuilder eb) {
		with((Long) o, eb);
	}

	protected int nextIndex(TwoPhaseStateBuilder eb) {
		int i = eb.getPropertyIndex();
		eb.setPropertyIndex(i + 1);
		return i;
	}
}