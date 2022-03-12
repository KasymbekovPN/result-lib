package kpn.resultLib.i18n;

import kpn.resultLib.result.ImmutableResult;
import kpn.resultLib.result.Result;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractI18nServiceTest {

    private static final String EXISTED_CODE = "existed.code";
    private static final String NON_EXISTED_CODE = "non.existed.code";
    private static final String TEMPLATE = "message %s !";

    @Test
    void shouldCheckTranslationIfCodeIsAbsent() {
        List<Integer> args = List.of(1, 2, 3);
        String expectedTranslation = NON_EXISTED_CODE + " " + args;

        TestI18nService service = new TestI18nService();
        ImmutableResult.Builder<Void> builder = ImmutableResult.<Void>builder().code(NON_EXISTED_CODE);
        args.forEach(builder::arg);

        String translation = service.getTranslation(builder.build());
        assertThat(expectedTranslation).isEqualTo(translation);
    }

    @Test
    void shouldCheckTranslation() {
        List<Integer> args = List.of(1);
        String expectedTranslation = String.format(TEMPLATE, args.get(0));

        TestI18nService service = new TestI18nService();
        ImmutableResult.Builder<Void> builder = ImmutableResult.<Void>builder().code(EXISTED_CODE);
        args.forEach(builder::arg);

        String translation = service.getTranslation(builder.build());
        assertThat(expectedTranslation).isEqualTo(translation);
    }

    private static class TestI18nService extends AbstractI18nService{

        public TestI18nService() {
            super(Locale.getDefault());
        }

        @Override
        protected Optional<String> translate(Result<?> result, Locale locale) {
            String code = result.getCode();
            if (EXISTED_CODE.equals(code)){
                return Optional.of(String.format(TEMPLATE, result.getArgs()[0]));
            }
            return Optional.empty();
        }
    }
}
