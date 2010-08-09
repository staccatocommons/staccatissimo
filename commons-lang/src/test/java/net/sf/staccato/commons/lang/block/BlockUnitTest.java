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
import static org.junit.Assert.fail;
import net.sf.staccato.commons.lang.Executable;
import net.sf.staccato.commons.testing.junit.jmock.JUnit4MockObjectTestCase;

import org.apache.commons.lang.mutable.MutableInt;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Ignore;
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

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.lang.block.Block#andCatch(net.sf.staccato.commons.lang.block.Block2)}
	 * .
	 */
	@Ignore
	@Test
	public void testAndCatchBlock2OfQsuperRuntimeExceptionT() {
		block.andCatch(new Block2<RuntimeException, MutableInt>() {
		});

	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.lang.block.Block#andCatch(java.lang.Class, net.sf.staccato.commons.lang.block.Block2)}
	 * .
	 */
	@Ignore
	@Test
	public void testAndCatchClassOfEBlock2OfQsuperET() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.lang.block.Block#andFinally(net.sf.staccato.commons.lang.Executable)}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAndFinally() {
		block = new Block<MutableInt>() {
			public void exec(MutableInt argument) {
				throw new IllegalArgumentException();
			}
		};

		checking(new Expectations() {
			{
				one(executable).exec(new MutableInt(90));
			}
		});

		block.andFinally(executable).exec(new MutableInt(90));
	}

}
