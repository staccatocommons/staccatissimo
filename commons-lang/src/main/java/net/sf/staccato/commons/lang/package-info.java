/**
 * This package contains the core functor-like interfaces of 
 * staccato-commons-lang - {@link net.sf.staccato.commons.lang.Applicable}, {@link net.sf.staccato.commons.lang.Executable}, 
 * {@link net.sf.staccato.commons.lang.Provider} and variants.  
 * 
 * 
 * There exists three different versions of the Executables, 
 * with <code>exec</code> method arities from 1 to 3 
 * - {@link net.sf.staccato.commons.lang.Executable}, 
 *  {@link net.sf.staccato.commons.lang.Executable2} and
 *  {@link net.sf.staccato.commons.lang.Executable3}. There does not exists an executable with <code>exec</code> method arity of zero,
 *  as it would overlapp with {@link java.lang.Runnable} interface.
 *  So, whenever an hypothetical Executable0 would be necessary, use {@link java.lang.Runnable} instead
 */
package net.sf.staccato.commons.lang;