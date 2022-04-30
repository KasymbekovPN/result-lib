package kpn.lib.result;

import org.junit.jupiter.api.Test;

import kpn.lib.seed.ImmutableSeed;
import kpn.lib.seed.ImmutableSeed.Builder;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class ImmutableResultTest {

    @Test
    public void shouldCheckSuccess_whenNotSet() {
        ImmutableResult<String> result = ImmutableResult.<String>builder().build();
        assertThat(result.isSuccess()).isFalse();
    }

    @Test
    public void shouldCheckSuccess_whenSet() {
        ImmutableResult<String> result = ImmutableResult.<String>builder().success(true).build();
        assertThat(result.isSuccess()).isTrue();
    }

    @Test
    public void shouldCheckValue_whenNotSet() {
        ImmutableResult<String> result = ImmutableResult.<String>builder().build();
        assertThat(result.getValue()).isNull();
    }

    @Test
    public void shouldCheckValue_whenSet() {
        String value = "value";
        ImmutableResult<String> result = ImmutableResult.<String>builder().value(value).build();
        assertThat(value).isEqualTo(result.getValue());
    }

    @Test
    public void shouldCheckSeed_whenNotSet(){
        ImmutableResult<String> result = ImmutableResult.<String>builder().build();
        assertThat(result.getSeed().getCode()).isEmpty();
        assertThat(result.getSeed().getArgs()).isEmpty();
    }

    @Test
    public void shouldCheckSeed_whenSet(){
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
}
