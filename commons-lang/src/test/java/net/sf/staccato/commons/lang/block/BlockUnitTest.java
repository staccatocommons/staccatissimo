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
package net.sf.staccato.commons.lang.block;

import static org.junit.Assert.assertEquals;
import net.sf.staccato.commons.lang.Executable;
import net.sf.staccato.commons.testing.junit.jmock.JUnit4MockObjectTestCase;

import org.apache.commons.lang.mutable.MutableInt;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class BlockUnitTest extends JUnit4MockObjectTestCase {

	Block<MutableInt> block;
	private Executable<MutableInt> executable;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		executable = mock(Executable.class);
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.lang.block.Block#then(net.sf.staccato.commons.lang.Executable)}
	 * .
	 */
	@Test
	public void testThen() {
		block = new Block<MutableInt>() {
			public void exec(MutableInt argument) {
				assertEquals(0, argument.getValue());
				argument.add(1);
			}
		};

		checking(new Expectations() {
			{
				one(executable).exec(new MutableInt(1));
			}
		});

		block.then(executable).exec(new MutableInt(0));
	}

}
