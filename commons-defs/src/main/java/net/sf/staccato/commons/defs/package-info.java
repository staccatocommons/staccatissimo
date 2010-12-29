/**
 * This package contains function-like interfaces of different arities - number or arguments -, and interfaces 
 * that describe container-like objects
 */
package net.sf.staccato.commons.defs;

/*
 * There exists three different versions of the Executables, with
 * <code>exec</code> method arities from 1 to 3 - {@link
 * net.sf.staccato.commons.lang.Executable}, {@link
 * net.sf.staccato.commons.defs.Executable2} and {@link
 * net.sf.staccato.commons.defs.Executable3}. There does not exists an
 * executable with <code>exec</code> method arity of zero, as it would overlapp
 * with {@link java.lang.Runnable} interface. So, whenever an hypothetical
 * Executable0 would be necessary, use {@link java.lang.Runnable} instead
 */