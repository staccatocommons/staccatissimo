package net.sf.staccatocommons.defs;

import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * Very basic definition of a type that can act as a container or computation of
 * other objects.
 * 
 * 
 * @author flbulgarelli
 * 
 * @param <ContainerType>
 * @param <A>
 * @since 1.2
 */
public interface ProtoMonad<ContainerType, A> {

  /**
   * Transforms each element using the given function
   * 
   * @param <B>
   * @param function
   *          the mapper used to transform each element, applying it
   * @return a {@link ProtoMonad} that contains or computes the result of
   *         applying the given function to each element
   */
  <B> ProtoMonad<ContainerType, B> map(Applicable<? super A, ? extends B> function);

  /**
   * Preserves elements that satisfy the given <code>predicate</code>
   * 
   * @param predicate
   * @return a new {@link ProtoMonad} that will retrieve only elements that
   *         evaluate to true
   */
  ProtoMonad<ContainerType, A> filter(Evaluable<? super A> predicate);

  /**
   * Executes the given {@link Executable} for each element in this
   * {@link ProtoMonad}.
   * 
   * @param block
   */
  void forEach(@NonNull Executable<? super A> block);

}
