/**
 * 
 */
package net.sf.staccato.commons.lang.collection;

import java.util.AbstractList;

public class StringList extends AbstractList<Character> {

	private final String string;

	public StringList(String string) {
		this.string = string;
	}

	@Override
	public int size() {
		return string.length();
	}

	@Override
	public Character get(int index) {
		return string.charAt(index);
	}

	@Override
	public boolean contains(Object o) {
		Character c = (Character) o;
		return string.indexOf(c.charValue()) != -1;
	}
	
	

}