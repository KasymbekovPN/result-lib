package kpn.lib.result;

import org.junit.jupiter.api.Test;

import kpn.lib.seed.ImmutableSeed;
import kpn.lib.seed.ImmutableSeed.Builder;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

class ImmutableResultTest {

    @Test
    void shouldCheckSuccess_whenNotSet() {
        ImmutableResult<String> result = ImmutableResult.<String>builder().build();
        assertThat(result.isSuccess()).isFalse();
    }

    @Test
    void shouldCheckSuccess_whenSet() {
        ImmutableResult<String> result = ImmutableResult.<String>builder().success(true).build();
        assertThat(result.isSuccess()).isTrue();
    }

    @Test
    void shouldCheckValue_whenNotSet() {
        ImmutableResult<String> result = ImmutableResult.<String>builder().build();
        assertThat(result.getValue()).isNull();
    }

    @Test
    void shouldCheckValue_whenSet() {
        String value = "value";
        ImmutableResult<String> result = ImmutableResult.<String>builder().value(value).build();
        assertThat(value).isEqualTo(result.getValue());
    }

    @Test
    void shouldCheckSeed_whenNotSet(){
        ImmutableResult<String> result = ImmutableResult.<String>builder().build();
        assertThat(result.getSeed().getCode()).isEmpty();
        assertThat(result.getSeed().getArgs()).isEmpty();
    }

    @Test
    void shouldCheckSeed_whenSet(){
        String code = "some.code";
        List<Integer> rawArgs = List.of(1, 2, 3);

        Builder seedBuilder = ImmutableSeed.builder().code(code);
        rawArgs.stream().forEach(seedBuilder::arg);
        ImmutableSeed expectedSeed = seedBuilder.build();

        kpn.lib.result.ImmutableResult.Builder<String> resultBuilder = ImmutableResult.<String>builder().code(code);
        rawArgs.stream().forEach(resultBuilder::arg);
        ImmutableResult<String> result = resultBuilder.build();

        assertThat(expectedSeed).isEqualTo(result.getSeed());
    }

    @Test
    void shouldCheckEquality(){
        String value = "value";
        String code = "code";
        String arg = "arg";
        ImmutableResult<String> result0 = ImmutableResult.<String>ok(value).code(code).arg(arg).build();
        ImmutableResult<String> result1 = ImmutableResult.<String>ok(value).code(code).arg(arg).build();
        assertThat(result0).isEqualTo(result1);
    }

    @Test
    void shouldCheckHashCalculation(){
        String value = "value";
        String code = "code";
        String arg = "arg";
        ImmutableResult<String> result0 = ImmutableResult.<String>ok(value).code(code).arg(arg).build();
        ImmutableResult<String> result1 = ImmutableResult.<String>ok(value).code(code).arg(arg).build();
        assertThat(result0.hashCode()).isEqualTo(result1.hashCode());
    }
}
