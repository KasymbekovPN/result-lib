package kpn.resultLib.result;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ImmutableResultTest {

    @Test
    void shouldCheckSuccessWhenItNotSet() {
        ImmutableResult<String> result = ImmutableResult.<String>builder().build();
        assertThat(result.isSuccess()).isFalse();
    }

    @Test
    void shouldCheckSuccessWhenItSet() {
        ImmutableResult<String> result = ImmutableResult.<String>builder().success(true).build();
        assertThat(result.isSuccess()).isTrue();
    }

    @Test
    void shouldCheckValueWhenNotSet() {
        ImmutableResult<String> result = ImmutableResult.<String>builder().build();
        assertThat(result.getValue()).isNull();
    }

    @Test
    void shouldCheckValueWhenSet() {
        String value = "value";
        ImmutableResult<String> result = ImmutableResult.<String>builder().value(value).build();
        assertThat(value).isEqualTo(result.getValue());
    }

    @Test
    void shouldCheckCodeWhenNoSet() {
        ImmutableResult<String> result = ImmutableResult.<String>builder().build();
        assertThat(result.getCode()).isNull();
    }

    @Test
    void shouldCheckCodeWhenSet() {
        String code = "code";
        ImmutableResult<String> result = ImmutableResult.<String>builder().code(code).build();
        assertThat(code).isEqualTo(result.getCode());
    }

    @Test
    void shouldCheckArgsWhenNotSet() {
        ImmutableResult<String> result = ImmutableResult.<String>builder().build();
        assertThat(result.getArgs()).isEmpty();
    }

    @Test
    void shouldCheckArgsWhenSet() {
        ImmutableResult.Builder<String> builder = ImmutableResult.<String>builder();
        Object[] expectedArgs = {1, 2, 3};
        Arrays.stream(expectedArgs).forEach(builder::arg);
        ImmutableResult<String> result = builder.build();

        assertThat(expectedArgs).isEqualTo(result.getArgs());
    }
}
