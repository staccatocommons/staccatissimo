package net.sf.staccato.commons.collections;

import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import net.sf.staccato.commons.lang.check.Ensure;

import org.apache.commons.lang.ObjectUtils;

public class Lists {
	private static final String LIST_PARAM = "list";

	/**
	 * 
	 * @param <S>
	 * @param list
	 *          non null
	 * @param element
	 *          nullable, if the collection supports nulls, non nullable otherwise
	 * @param reference
	 *          nullable, if the collection supports nulls, non nullable otherwise
	 */
	public static <S> void addAfter(List<S> list, S element, S reference) {
		Ensure.nonNull(LIST_PARAM, list);
		for (ListIterator<S> iter = list.listIterator(); iter.hasNext();)
			if (ObjectUtils.equals(iter.next(), reference)) {
				iter.add(element);
				return;
			}
		throw new NoSuchElementException(reference.toString());
	}

	public static <S> void addBefore(List<S> list, S element, S reference) {
		Ensure.nonNull(LIST_PARAM, list);
		for (ListIterator<S> iter = list.listIterator(); iter.hasNext();)
			if (iter.next().equals(reference)) {
				iter.previous();
				iter.add(element);
				return;
			}
		throw new NoSuchElementException(reference.toString());
	}

	public static <S> S removeLast(List<S> list) {
		Ensure.nonNull(LIST_PARAM, list);
		return list.remove(list.size() - 1);
	}

	public static <S> S first(List<S> list) {
		Ensure.nonNull(LIST_PARAM, list);
		Ensure.notEmpty(LIST_PARAM, list);
		return list.get(0);
	}

	public static <S> S second(List<S> list) {
		Ensure.nonNull(LIST_PARAM, list);
		Ensure.isTrue(LIST_PARAM, list.size() > 1,//
			"Must hava at least two elements");
		return list.get(1);
	}

	public static <S> S third(List<S> list) {
		Ensure.nonNull(LIST_PARAM, list);
		Ensure.isTrue(LIST_PARAM, list.size() > 2,//
			"Must hava at least three elements");
		return list.get(2);
	}

	public static <S> S last(List<S> list) {
		Ensure.nonNull(LIST_PARAM, list);
		Ensure.notEmpty(LIST_PARAM, list);
		return list.get(list.size() - 1);
	}
}
