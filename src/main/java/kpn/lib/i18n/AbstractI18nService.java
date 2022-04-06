package kpn.lib.i18n;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import kpn.lib.result.Result;

public abstract class AbstractI18nService implements I18nService{

    private final Locale defaultLocale;

    protected AbstractI18nService(Locale defaultLocale) {
        this.defaultLocale = defaultLocale;
    }

    @Override
    public String getTranslation(Result<?> result, Locale locale) {
        Optional<String> maybeTranslation = translate(result, locale);
        return maybeTranslation.orElseGet(() -> createDefaultMessage(result));
    }

    @Override
    public String getTranslation(Result<?> result) {
        return getTranslation(result, defaultLocale);
    }

    private String createDefaultMessage(Result<?> result) {
        List<Object> collect = Arrays.stream(result.getArgs()).collect(Collectors.toList());
        return result.getCode() + " " + collect;
    }

    protected abstract Optional<String> translate(Result<?> result, Locale locale);
}
