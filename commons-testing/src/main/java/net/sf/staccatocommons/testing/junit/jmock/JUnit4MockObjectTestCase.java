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
package net.sf.staccatocommons.testing.junit.jmock;

import org.hamcrest.Description;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.States;
import org.jmock.api.Expectation;
import org.jmock.api.ExpectationErrorTranslator;
import org.jmock.api.Imposteriser;
import org.jmock.api.MockObjectNamingScheme;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.internal.ExpectationBuilder;
import org.junit.After;

/**
 * 
 * @author fbulgarelli
 * 
 */
public abstract class JUnit4MockObjectTestCase {
	private Mockery context = new JUnit4Mockery();

	/**
	 * @see org.jmock.Mockery#setDefaultResultForType(java.lang.Class,
	 *      java.lang.Object)
	 */
	public void setDefaultResultForType(Class<?> type, Object result) {
		context.setDefaultResultForType(type, result);
	}

	/**
	 * @see org.jmock.Mockery#setImposteriser(org.jmock.api.Imposteriser)
	 */
	public void setImposteriser(Imposteriser imposteriser) {
		context.setImposteriser(imposteriser);
	}

	/**
	 * @see org.jmock.Mockery#setNamingScheme(org.jmock.api.MockObjectNamingScheme)
	 */
	public void setNamingScheme(MockObjectNamingScheme namingScheme) {
		context.setNamingScheme(namingScheme);
	}

	/**
	 * @see org.jmock.Mockery#setExpectationErrorTranslator(org.jmock.api.ExpectationErrorTranslator)
	 */
	public void setExpectationErrorTranslator(ExpectationErrorTranslator expectationErrorTranslator) {
		context.setExpectationErrorTranslator(expectationErrorTranslator);
	}

	/**
	 * @see org.jmock.Mockery#sequence(java.lang.String)
	 */
	public Sequence sequence(String name) {
		return context.sequence(name);
	}

	/**
	 * @see org.jmock.Mockery#states(java.lang.String)
	 */
	public States states(String name) {
		return context.states(name);
	}

	/**
	 * @param expectations
	 * @see org.jmock.Mockery#checking(org.jmock.internal.ExpectationBuilder)
	 */
	public void checking(ExpectationBuilder expectations) {
		context.checking(expectations);
	}

	/**
	 * @param expectation
	 * @see org.jmock.Mockery#addExpectation(org.jmock.api.Expectation)
	 */
	public void addExpectation(Expectation expectation) {
		context.addExpectation(expectation);
	}

	/**
	 * @param description
	 * @see org.jmock.Mockery#describeTo(org.hamcrest.Description)
	 */
	public void describeTo(Description description) {
		context.describeTo(description);
	}

	/**
	 * @see org.jmock.Mockery#mock(java.lang.Class)
	 */
	public <T> T mock(Class<T> typeToMock) {
		return context.mock(typeToMock);
	}

	/**
	 * @see org.jmock.Mockery#mock(java.lang.Class, java.lang.String)
	 */
	public <T> T mock(Class<T> typeToMock, String name) {
		return context.mock(typeToMock, name);
	}

	@After
	public void assertIsSatisfied() {
		context.assertIsSatisfied();
	}

}
