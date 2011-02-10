package net.sf.staccatocommons.check.instrument.mock;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import net.sf.staccatocommons.restrictions.check.MaxSize;
import net.sf.staccatocommons.restrictions.check.MinSize;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.check.NotEmpty;
import net.sf.staccatocommons.restrictions.check.NotNegative;
import net.sf.staccatocommons.restrictions.check.NotZero;
import net.sf.staccatocommons.restrictions.check.Positive;
import net.sf.staccatocommons.restrictions.check.Size;
import net.sf.staccatocommons.restrictions.processing.ForceRestrictions;
import net.sf.staccatocommons.restrictions.processing.IgnoreRestrictions;

/**
 * 
 * @author flbulgarelli
 * 
 */
public class Mock {
	/***/
	public Mock() {}

	/***/
	public Mock(@NonNull Object argument, @NonNull Integer argument2) {}

	/***/
	@IgnoreRestrictions
	public Mock(@NonNull Object argument, @NonNull Long argument2) {}

	/***/
	@ForceRestrictions
	public Mock(@NonNull Object argument, @NonNull String argument2) {}

	/***/
	@NonNull
	public Object defaultReturnNonNull() {
		return null;
	}

	/***/
	@ForceRestrictions
	public void forceChecksNonNullMethodArgument(@NonNull Object argument) {}

	/***/
	@IgnoreRestrictions
	public void ignoreChecksNonNullMethodArgument(@NonNull Object argument) {}

	/***/
	public void defaultNonNullMethodArgument(@NonNull Object argument) {}

	/***/
	public void defaultSizeMethodArgument(@Size(1) List<?> argument) {}

	/***/
	public void defaultNotEmptyMethodArgument(@NotEmpty String argument) {}

	/***/
	public void defaultPositiveMethodArgument(@Positive BigDecimal argument) {}

	/***/
	public void defaultNotNegative(@NotNegative int i) {

	}

	/***/
	public void defaulMinSize(@MinSize(4) List<Integer> asList) {

	}

	/***/
	public void defaulMaxSize(@MaxSize(2) Collection<?> col) {

	}

	/***/
	public void defaulNotZero(@NotZero long l) {

	}

}
