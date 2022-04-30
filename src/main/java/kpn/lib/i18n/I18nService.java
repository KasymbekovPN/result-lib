package kpn.lib.i18n;

import java.util.Locale;

import kpn.lib.seed.Seed;

public interface I18nService {
    String getTranslation(Seed seed, Locale locale);
    String getTranslation(Seed seed);
}
