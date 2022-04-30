package kpn.lib.i18n;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import kpn.lib.seed.Seed;

public abstract class AbstractI18nService implements I18nService{

    private final Locale defaultLocale;

    protected AbstractI18nService(Locale defaultLocale) {
        this.defaultLocale = defaultLocale;
    }

    @Override
    public String getTranslation(Seed seed, Locale locale) {
        Optional<String> maybeTranslation = translate(seed, locale);
        return maybeTranslation.orElseGet(() -> createDefaultMessage(seed));
    }

    @Override
    public String getTranslation(Seed seed) {
        return getTranslation(seed, defaultLocale);
    }

    private String createDefaultMessage(Seed seed) {
        List<Object> collect = Arrays.stream(seed.getArgs()).collect(Collectors.toList());
        return seed.getCode() + " " + collect;
    }

    protected abstract Optional<String> translate(Seed seed, Locale locale);
}
