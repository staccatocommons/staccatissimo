package net.sf.staccatocommons.check.instrument.mock;

import java.math.BigDecimal;
import java.util.List;

import net.sf.staccatocommons.check.annotation.ForceChecks;
import net.sf.staccatocommons.check.annotation.IgnoreChecks;
import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.check.annotation.NotEmpty;
import net.sf.staccatocommons.check.annotation.NotNegative;
import net.sf.staccatocommons.check.annotation.Positive;
import net.sf.staccatocommons.check.annotation.Size;

/**
 * 
 * @author flbulgarelli
 * 
 */
public class Mock {
	/***/
	public Mock() {
	}

	/***/
	public Mock(@NonNull Object argument, @NonNull Integer argument2) {
	}

	/***/
	@IgnoreChecks
	public Mock(@NonNull Object argument, @NonNull Long argument2) {
	}

	/***/
	@ForceChecks
	public Mock(@NonNull Object argument, @NonNull String argument2) {
	}

	/***/
	@NonNull
	public Object defaultReturnNonNull() {
		return null;
	}

	/***/
	@ForceChecks
	public void forceChecksNonNullMethodArgument(@NonNull Object argument) {
	}

	/***/
	@IgnoreChecks
	public void ignoreChecksNonNullMethodArgument(@NonNull Object argument) {
	}

	/***/
	public void defaultNonNullMethodArgument(@NonNull Object argument) {
	}

	/***/
	public void defaultSizeMethodArgument(@Size(1) List<?> argument) {
	}

	/***/
	public void defaultNotEmptyMethodArgument(@NotEmpty String argument) {
	}

	/***/
	public void defaultPositiveMethodArgument(@Positive BigDecimal argument) {
	}

	/***/
	public void defaultNotNegative(@NotNegative int i) {

	}

}
