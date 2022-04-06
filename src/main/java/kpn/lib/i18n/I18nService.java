package kpn.lib.i18n;

import java.util.Locale;

import kpn.lib.result.Result;

public interface I18nService {
    String getTranslation(Result<?> result, Locale locale);
    String getTranslation(Result<?> result);
}
