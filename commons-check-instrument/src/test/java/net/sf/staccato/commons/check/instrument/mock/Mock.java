package net.sf.staccato.commons.check.instrument.mock;

import java.math.BigDecimal;

import net.sf.staccato.commons.check.annotation.ForceChecks;
import net.sf.staccato.commons.check.annotation.IgnoreChecks;
import net.sf.staccato.commons.check.annotation.NonNull;
import net.sf.staccato.commons.check.annotation.NotEmpty;
import net.sf.staccato.commons.check.annotation.Positive;
import net.sf.staccato.commons.check.annotation.Size;

public class Mock {

	public Mock() {
	}

	public Mock(@NonNull Object argument, @NonNull Integer argument2) {
	}

	@IgnoreChecks
	public Mock(@NonNull Object argument, @NonNull Long argument2) {
	}

	@ForceChecks
	public Mock(@NonNull Object argument, @NonNull String argument2) {
	}

	@NonNull
	public Object defaultReturnNonNull() {
		return null;
	}

	@ForceChecks
	public void forceChecksNonNullMethodArgument(@NonNull Object argument) {
	}

	@IgnoreChecks
	public void ignoreChecksNonNullMethodArgument(@NonNull Object argument) {
	}

	public void defaultNonNullMethodArgument(@NonNull Object argument) {
	}

	public void defaultSizeMethodArgument(@Size(1) Object[] argument) {
	}

	public void defaultNotEmptyMethodArgument(@NotEmpty String argument) {
	}

	public void defaultPositiveMethodArgument(@Positive BigDecimal argument) {
	}

}
