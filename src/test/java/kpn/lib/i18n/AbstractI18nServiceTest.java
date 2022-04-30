package kpn.lib.i18n;

import org.junit.jupiter.api.Test;

import kpn.lib.seed.ImmutableSeed;
import kpn.lib.seed.ImmutableSeed.Builder;
import kpn.lib.seed.Seed;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class AbstractI18nServiceTest {

    private static final String EXISTED_CODE = "existed.code";
    private static final String NON_EXISTED_CODE = "non.existed.code";
    private static final String TEMPLATE = "message %s !";

    @Test
    void shouldCheckTranslation_whenCodeIsAbsent() {
        List<Integer> args = List.of(1, 2, 3);
        String expectedTranslation = NON_EXISTED_CODE + " " + args;

        Builder builder = ImmutableSeed.builder().code(NON_EXISTED_CODE);
        args.forEach(builder::arg);

        TestI18nService service = new TestI18nService();
        String translation = service.getTranslation(builder.build());
        assertThat(expectedTranslation).isEqualTo(translation);
    }

    @Test
    void shouldCheckTranslation() {
        List<Integer> args = List.of(1);
        String expectedTranslation = String.format(TEMPLATE, args.get(0));

        Builder builder = ImmutableSeed.builder().code(EXISTED_CODE);
        args.forEach(builder::arg);

        TestI18nService service = new TestI18nService();
        String translation = service.getTranslation(builder.build());
        assertThat(expectedTranslation).isEqualTo(translation);
    }

    private static class TestI18nService extends AbstractI18nService{

        public TestI18nService() {
            super(Locale.getDefault());
        }

        @Override
        protected Optional<String> translate(Seed seed, Locale locale) {
            String code = seed.getCode();
            return EXISTED_CODE.equals(code)
                    ? Optional.of(String.format(TEMPLATE, seed.getArgs()[0]))
                    : Optional.empty();
        }
    }
}
