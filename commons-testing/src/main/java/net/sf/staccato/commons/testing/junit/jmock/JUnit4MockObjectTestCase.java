/*
 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccato.commons.testing.junit.jmock;

import java.util.Collection;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.States;
import org.jmock.api.Expectation;
import org.jmock.api.ExpectationErrorTranslator;
import org.jmock.api.Imposteriser;
import org.jmock.api.MockObjectNamingScheme;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.internal.ExpectationBuilder;

/**
 * 
 * @author fbulgarelli
 * 
 */
public abstract class JUnit4MockObjectTestCase {
	private Mockery context = new JUnit4Mockery();

	public <T> T mock(Class<T> typeToMock, String name) {
		return context.mock(typeToMock, name);
	}

	public <T> T mock(Class<T> typeToMock) {
		return context.mock(typeToMock);
	}

	public Sequence sequence(String name) {
		return context.sequence(name);
	}

	public void setDefaultResultForType(Class<?> type, Object result) {
		context.setDefaultResultForType(type, result);
	}

	public void setExpectationErrorTranslator(
		ExpectationErrorTranslator expectationErrorTranslator) {
		context.setExpectationErrorTranslator(expectationErrorTranslator);
	}

	public void setImposteriser(Imposteriser imposteriser) {
		context.setImposteriser(imposteriser);
	}

	public void setNamingScheme(MockObjectNamingScheme namingScheme) {
		context.setNamingScheme(namingScheme);
	}

	public States states(String name) {
		return context.states(name);
	}

	public void addExpectation(Expectation expectation) {
		context.addExpectation(expectation);
	}

	public void assertIsSatisfied() {
		context.assertIsSatisfied();
	}

	public void checking(ExpectationBuilder expectations) {
		context.checking(expectations);
	}

	public void describeTo(Description description) {
		context.describeTo(description);
	}

}
