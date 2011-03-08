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
package net.sf.staccatocommons.collections.stream;

import static net.sf.staccatocommons.lang.Compare.*;
import static net.sf.staccatocommons.lang.tuple.Tuples.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import net.sf.staccatocommons.check.Ensure;
import net.sf.staccatocommons.check.Validate;
import net.sf.staccatocommons.collections.internal.ToPair;
import net.sf.staccatocommons.collections.iterable.Iterables;
import net.sf.staccatocommons.collections.iterable.internal.IterablesInternal;
import net.sf.staccatocommons.collections.stream.impl.IteratorStream;
import net.sf.staccatocommons.collections.stream.impl.ListStream;
import net.sf.staccatocommons.collections.stream.impl.MemorizedStream;
import net.sf.staccatocommons.collections.stream.impl.PrependStream;
import net.sf.staccatocommons.collections.stream.impl.ThenStream;
import net.sf.staccatocommons.collections.stream.impl.internal.AppendIterableStream;
import net.sf.staccatocommons.collections.stream.impl.internal.AppendStream;
import net.sf.staccatocommons.collections.stream.impl.internal.DeconsThenStream;
import net.sf.staccatocommons.collections.stream.impl.internal.DelayedDeconsThenStream;
import net.sf.staccatocommons.collections.stream.impl.internal.DropStream;
import net.sf.staccatocommons.collections.stream.impl.internal.DropWhileStream;
import net.sf.staccatocommons.collections.stream.impl.internal.FilterStream;
import net.sf.staccatocommons.collections.stream.impl.internal.FlatMapStream;
import net.sf.staccatocommons.collections.stream.impl.internal.GroupByStream;
import net.sf.staccatocommons.collections.stream.impl.internal.MapStream;
import net.sf.staccatocommons.collections.stream.impl.internal.SortedStream;
import net.sf.staccatocommons.collections.stream.impl.internal.TakeStream;
import net.sf.staccatocommons.collections.stream.impl.internal.TakeWhileStream;
import net.sf.staccatocommons.collections.stream.impl.internal.ZipStream;
import net.sf.staccatocommons.collections.stream.impl.internal.delayed.DelayedAppendStream;
import net.sf.staccatocommons.collections.stream.impl.internal.delayed.DelayedPrependStream;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.EmptyAware;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.defs.Evaluable2;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.defs.function.Function2;
import net.sf.staccatocommons.defs.type.NumberType;
import net.sf.staccatocommons.iterators.thriter.Thriter;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.lang.Compare;
import net.sf.staccatocommons.lang.Option;
import net.sf.staccatocommons.lang.function.AbstractFunction;
import net.sf.staccatocommons.lang.function.AbstractFunction2;
import net.sf.staccatocommons.lang.predicate.Equiv;
import net.sf.staccatocommons.lang.tuple.Pair;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.check.NotNegative;
import net.sf.staccatocommons.restrictions.processing.ForceRestrictions;

import org.apache.commons.lang.StringUtils;

/**
 * An abstract implementation of a {@link Stream}. Only it {@link Iterator}
 * method is abstract
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 */
public abstract class AbstractStream<A> implements Stream<A> {

	protected static final Validate<NoSuchElementException> validate = Validate
		.throwing(NoSuchElementException.class);

	@Override
	public int size() {
		int size = 0;
		Thriter<A> iter = this.iterator();
		while (iter.hasNext()) {
			iter.advanceNext();
			size++;
		}
		return size;
	}

	@Override
	public boolean isEmpty() {
		return Iterables.isEmpty(this);
	}

	@Override
	public boolean contains(A element) {
		return IterablesInternal.containsInternal(this, element);
	}

	@Override
	public Stream<A> filter(final Evaluable<? super A> predicate) {
		return new FilterStream<A>(this, predicate);
	}

	@Override
	public Stream<A> takeWhile(final Evaluable<? super A> predicate) {
		return new TakeWhileStream<A>(this, predicate);
	}

	@Override
	public Stream<A> take(@NotNegative final int amountOfElements) {
		return new TakeStream<A>(this, amountOfElements);
	}

	public Stream<A> dropWhile(Evaluable<? super A> predicate) {
		return new DropWhileStream<A>(this, predicate);
	}

	public Stream<A> drop(@NotNegative int amountOfElements) {
		return new DropStream<A>(this, amountOfElements);
	}

	@Override
	public A reduce(Applicable2<? super A, ? super A, ? extends A> function) {
		validate.that(!isEmpty(), "Can not reduce an empty stream");
		return Iterables.reduce(this, function);
	}

	@Override
	public <O> O fold(O initial, Applicable2<? super O, ? super A, ? extends O> function) {
		return Iterables.fold(this, initial, function);
	}

	@Override
	public A any() {
		return Iterables.any(this);
	}

	@Override
	public Option<A> anyOrNone() {
		return Iterables.anyOrNone(this);
	}

	@Override
	public A anyOrNull() {
		return anyOrNone().valueOrNull();
	}

	@Override
	public A anyOrElse(Thunk<A> thunk) {
		return anyOrNone().valueOrElse(thunk);
	}

	@Override
	public A anyOrElse(A value) {
		return anyOrNone().valueOrElse(value);
	}

	@Override
	public A find(Evaluable<? super A> predicate) {
		return Iterables.find(this, predicate);
	}

	@Override
	public Option<A> findOrNone(Evaluable<? super A> predicate) {
		return Iterables.findOrNone(this, predicate);
	}

	@Override
	public A findOrNull(Evaluable<? super A> predicate) {
		return findOrNone(predicate).valueOrNull();
	}

	@Override
	public A findOrElse(Evaluable<? super A> predicate, Thunk<? extends A> thunk) {
		return findOrNone(predicate).valueOrElse(thunk);
	}

	@Override
	public boolean all(Evaluable<? super A> predicate) {
		return Iterables.all(this, predicate);
	}

	@Override
	public boolean any(Evaluable<? super A> predicate) {
		return Iterables.any(this, predicate);
	}

	@Override
	public <B> Stream<B> map(final Function<? super A, ? extends B> function) {
		return new MapStream<A, B>(this, function);
	}

	@Override
	public <B> Stream<B> flatMap(final Function<? super A, ? extends Iterable<? extends B>> function) {
		return new FlatMapStream<A, B>(this, function);
	}

	@Override
	public Stream<A> append(final Iterable<A> other) {
		return new AppendIterableStream<A>(this, other);
	}

	public Stream<A> appendUndefined() {
		return append(Streams.<A> undefined());
	}

	@Override
	public A first() {
		return get(0);
	}

	@Override
	public A second() {
		return get(1);
	}

	@Override
	public A third() {
		return get(2);
	}

	@Override
	public A last() {
		return get(size() - 1);
	}

	@Override
	public A get(int n) {
		Thriterator<A> iter = this.iterator();
		for (int i = 0; i <= n; i++)
			try {
				iter.advanceNext();
			} catch (NoSuchElementException e) {
				throw new IndexOutOfBoundsException("At " + n);
			}
		return iter.current();
	}

	@Override
	public int indexOf(A element) {
		return Iterables.indexOf(this, element);
	}

	@Override
	public final int positionOf(A element) {
		int index = indexOf(element);
		if (index == -1)
			throw new NoSuchElementException(element.toString());
		return index;
	}

	@Override
	public boolean isBefore(A previous, A next) {
		return Iterables.isBefore(this, previous, next);
	}

	@Override
	public Set<A> toSet() {
		return Iterables.toSet(this);
	}

	@Override
	public List<A> toList() {
		return Iterables.toList(this);
	}

	public Stream<A> force() {
		return new ListStream<A>(toList()) {
			public Stream<A> dettach() {
				return this;
			}

			public List<A> toList() {
				return Collections.unmodifiableList(getList());
			}
		};
	}

	public Stream<A> memorize() {
		return new MemorizedStream<A>(this);
	}

	public Stream<A> dettach() {
		return new IteratorStream<A>(iterator());
	}

	@Override
	public A[] toArray(Class<? extends A> clazz) {
		return toArray(clazz, toList());
	}

	protected A[] toArray(Class<? extends A> clazz, Collection<A> readOnlyColView) {
		return readOnlyColView.toArray((A[]) Array.newInstance(clazz, readOnlyColView.size()));
	}

	@Override
	@ForceRestrictions
	public String joinStrings(@NonNull String separator) {
		return StringUtils.join(iterator(), separator);
	}

	@Override
	public Pair<List<A>, List<A>> partition(Evaluable<? super A> predicate) {
		return Iterables.partition(this, predicate);
	}

	@Override
	public final Pair<Stream<A>, Stream<A>> streamPartition(Evaluable<? super A> predicate) {
		Pair<List<A>, List<A>> partition = partition(predicate);
		return _(Streams.from(partition._1()), Streams.from(partition._2()));
	}

	@Override
	public final boolean equivalent(A... elements) {
		return equivalent(Arrays.asList(elements));
	}

	@Override
	public boolean equivalent(Iterable<? extends A> other) {
		return Iterables.equivalent(this, other);
	}

	public boolean equivalentBy(Iterable<? extends A> other, Evaluable2<A, A> equalty) {
		return Iterables.equivalentBy(this, other, equalty);
	}

	public <B extends Comparable<B>> boolean equivalentOn(Iterable<? extends A> iterable,
		Applicable<A, B> function) {
		return Iterables.equivalentBy(this, iterable, Equiv.on(function));
	}

	@Override
	public <B> Stream<B> then(final Applicable<Stream<A>, ? extends Stream<B>> function) {
		return new ThenStream<A, B>(this, function);
	}

	/**
	 * <pre>
	 * intersperse _   []      = []
	 * intersperse _   [x]     = [x]
	 * intersperse sep (x:xs)  = x : sep : intersperse sep xs
	 * </pre>
	 * 
	 */
	public Stream<A> intersperse(final A sep) {
		return delayedThen(new DeconsFunction<A, A>() {
			public Stream<A> delayedApply(Thunk<A> head, Stream<A> tail) {
				if (tail.isEmpty())
					return Cons.from(head);
				return Cons.from(head, Cons.from(sep, tail.intersperse(sep)));
			}
		});
	}

	public Stream<A> append(A element) {
		return new AppendStream<A>(this, element);
	}

	public Stream<A> append(Thunk<A> element) {
		return new DelayedAppendStream<A>(this, element);
	}

	public Stream<A> prepend(A element) {
		return new PrependStream<A>(element, this);
	}

	public Stream<A> prepend(Thunk<A> element) {
		return new DelayedPrependStream<A>(element, this);
	}

	public <B> Stream<B> then(final DeconsApplicable<A, B> function) {
		return new DeconsThenStream<B, A>(this, function);
	}

	public <B> Stream<B> delayedThen(final DeconsApplicable<A, B> function) {
		return new DelayedDeconsThenStream<A, B>(this, function);
	}

	@Override
	public <B> Stream<Pair<A, B>> zip(Iterable<B> iterable) {
		return zip(iterable, ToPair.<A, B> getInstance());
	}

	public Pair<A, Stream<A>> decons() {
		Iterator<A> iter = iterator();
		validate.that(iter.hasNext(), "Empty streams have no head");
		return _(iter.next(), Streams.from(iter));
	}

	public Pair<Thunk<A>, Stream<A>> delayedDecons() {
		Thriterator<A> iter = iterator();
		validate.that(iter.hasNext(), "Empty streams have no head");
		return _(iter.delayedNext(), Streams.from(iter));
	}

	public Stream<A> tail() {
		validate.that(!isEmpty(), "Empty streams have not tail");
		return drop(1);
	}

	public A head() {
		validate.that(!isEmpty(), "Empty streams have not head");
		return first();
	}

	@ForceRestrictions
	public <B, C> Stream<C> zip(@NonNull final Iterable<B> iterable,
		@NonNull final Function2<A, B, C> function) {
		return new ZipStream<C, A, B>(this, iterable, function);
	}

	@Override
	public A sum() {
		return Iterables.sum(this);
	}

	@Override
	public A sum(NumberType<A> numberType) {
		return Iterables.sum(this, numberType);
	}

	@Override
	public A product() {
		return Iterables.product(this);
	}

	@Override
	public A product(NumberType<A> numberType) {
		return Iterables.product(this, numberType);
	}

	public A average() {
		return average(numberType());
	}

	public A average(final NumberType<A> numberType) {
		validate.that(!isEmpty(), "Can not get average on an empty stream");
		class Ref {
			A val = numberType.zero();
		}
		final Ref size = new Ref();
		return numberType.divide(fold(numberType.zero(), new AbstractFunction2<A, A, A>() {
			public A apply(A arg1, A arg2) {
				size.val = numberType.increment(size.val);
				return numberType.add(arg1, arg2);
			}
		}), size.val);
	}

	@Override
	public A maximum() {
		return maximumBy(natural());
	}

	@Override
	public A minimum() {
		return minimumBy(natural());
	}

	@Override
	public A maximumBy(Comparator<? super A> comparator) {
		return reduce(max(comparator));
	}

	@Override
	public A minimumBy(Comparator<? super A> comparator) {
		return reduce(min(comparator));
	}

	@Override
	public <B extends Comparable<B>> A maximumOn(Applicable<? super A, B> function)
		throws NoSuchElementException {
		return maximumBy(Compare.on(function));
	}

	@Override
	public <B extends Comparable<B>> A minimumOn(Applicable<? super A, B> function)
		throws NoSuchElementException {
		return minimumBy(Compare.on(function));
	}

	public Stream<A> sort() {
		return sortBy(natural());
	}

	public Stream<A> sortBy(Comparator<A> comparator) {
		return new SortedStream<A>(this, comparator);
	}

	public <B extends Comparable<B>> Stream<A> sortOn(Applicable<? super A, B> function) {
		return sortBy(Compare.on(function));
	}

	private Comparator<A> natural() {
		return (Comparator<A>) Compare.<Comparable> natural();
	}

	public NumberType<A> numberType() {
		throw new ClassCastException("Source can not be casted to ImplicitNumerType");
	}

	public Stream<A> reverse() {
		if (this.isEmpty())
			return Streams.empty();
		LinkedList<A> reversedList = new LinkedList<A>();
		for (A element : this)
			reversedList.addFirst(element);
		return Streams.from((List<A>) reversedList);
	}

	/***
	 * @param pred
	 * @return a new {@link Stream}. Although the resulting stream itself is lazy,
	 *         its stream elements are not.
	 */
	public Stream<Stream<A>> groupBy(final Evaluable2<A, A> pred) {
		return new GroupByStream<A>(this, pred);
	}

	public <B> Stream<Pair<A, B>> cross(@NonNull Iterable<B> other) {
		return cross(Streams.from(other));
	}

	// this >>= (\x -> other >>= (\y -> return (x,y)))
	@ForceRestrictions
	public <B> Stream<Pair<A, B>> cross(@NonNull final Stream<B> other) {
		return then(new AbstractFunction<Stream<A>, Stream<Pair<A, B>>>() {
			public Stream<Pair<A, B>> apply(Stream<A> stram) {
				return flatMap(new AbstractFunction<A, Stream<Pair<A, B>>>() {
					public Stream<Pair<A, B>> apply(final A x) {
						return other.flatMap(new AbstractFunction<B, Stream<Pair<A, B>>>() {
							public Stream<Pair<A, B>> apply(B y) {
								return Cons.from(_(x, y));
							}
						});
					}
				});
			}
		});
	}

	@ForceRestrictions
	public Stream<Stream<A>> fullCross(@NonNull Stream<Stream<A>> other) {
		Ensure.that().isNotEmpty("other", (EmptyAware) other);
		return fcross(other.prepend(this));
	}

	// fcross [xs,ys] = xs >>= \x -> ys >>= \y -> return [x,y]
	// fcross (xs:xss) = xs >>= \x -> (fcross xss) >>= \ys -> return (x:ys)
	private static <A> Stream<Stream<A>> fcross(Stream<Stream<A>> other) {
		return other.then(new AbstractFunction<Stream<Stream<A>>, Stream<Stream<A>>>() {
			public Stream<Stream<A>> apply(Stream<Stream<A>> _xss) {
				final Stream<Stream<A>> xss = _xss.memorize();
				if (xss.size() == 2)
					return xss.first().flatMap(new AbstractFunction<A, Stream<Stream<A>>>() {
						public Stream<Stream<A>> apply(final A x) {
							return xss.second().flatMap(new AbstractFunction<A, Stream<Stream<A>>>() {
								public Stream<Stream<A>> apply(A y) {
									return Cons.from(Cons.from(x, y));
								}
							});
						}
					});

				return xss.head().flatMap(new AbstractFunction<A, Stream<Stream<A>>>() {
					public Stream<Stream<A>> apply(final A x) {
						return fcross(xss.tail()).flatMap(new AbstractFunction<Stream<A>, Stream<Stream<A>>>() {
							public Stream<Stream<A>> apply(Stream<A> ys) {
								return Cons.from(Cons.from(x, ys));
							}
						});
					}
				});
			}
		});
	}

	public String toString() {
		return "[" + joinStrings(",") + "]";
	}

}
