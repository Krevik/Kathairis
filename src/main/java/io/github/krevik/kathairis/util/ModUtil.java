package io.github.krevik.kathairis.util;

import javax.annotation.Nonnull;

/**
 * @author Cadiboo
 */
public final class ModUtil {

	/**
	 * Suppresses IDE warnings and suggestions about blocks being null
	 *
	 * @return null
	 */
	@Nonnull
	public static <T extends Object> T _null() {
		return null;
	}

}
