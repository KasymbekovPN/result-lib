package kpn.resultLib.i18n;

import kpn.resultLib.result.Result;

import java.util.Locale;

public interface I18nService {
    String getTranslation(Result<?> result, Locale locale);
    String getTranslation(Result<?> result);
}
