package unknowndomain.engine.I18n;

public final class I18n{
	
	public final static String translation(String key) {
		return LocaleManager.localeManager.translation(key);
	}
}
