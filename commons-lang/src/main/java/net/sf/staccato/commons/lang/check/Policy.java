package net.sf.staccato.commons.lang.check;

/**
 * A policy encapsulates a sequence of checks
 * 
 * @author fbulgarelli
 * 
 * @param <TargetType>
 *          the type of object that will checked in order to see if it violates
 *          or not this policy
 */
public interface Policy<TargetType> {

	public <ExceptionType extends Throwable> void enforce(TargetType target,
		Check<ExceptionType> check) throws ExceptionType;

}
