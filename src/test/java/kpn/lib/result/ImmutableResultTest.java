package kpn.lib.result;

import org.junit.jupiter.api.Test;

import kpn.lib.result.ImmutableResult.Builder;

import java.util.Arrays;
import java.util.List;

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

    @Test
    void shouldCheckWhenEqual(){
        boolean success = true;
        Integer value = 123;
        String code = "code";
        List<Integer> args = List.of(1, 2, 3);

        ImmutableResult<Integer> r0 = createResult(success, value, code, args);
        ImmutableResult<Integer> r1 = createResult(success, value, code, args);
        assertThat(r0).isEqualTo(r1);
    }

    @Test
    void shouldCheckWhenNotEqual_secondIsNull(){
        boolean success = true;
        Integer value = 123;
        String code = "code";
        List<Integer> args = List.of(1, 2, 3);

        ImmutableResult<Integer> r0 = createResult(success, value, code, args);
        assertThat(r0.equals(null)).isFalse();
    }

    @Test
    void shouldCheckWhenNotEqual_differentTypes(){
        boolean success = true;
        Integer value = 123;
        String code = "code";
        List<Integer> args = List.of(1, 2, 3);

        ImmutableResult<Integer> r0 = createResult(success, value, code, args);
        assertThat(r0.equals("")).isFalse();
    }

    @Test
    void shouldCheckWhenNotEqual_differentArgs(){
        boolean success = true;
        Integer value = 123;
        String code = "code";
        List<Integer> args0 = List.of(1, 2, 3);
        List<Integer> args1 = List.of(1, 2, 3, 4);

        ImmutableResult<Integer> r0 = createResult(success, value, code, args0);
        ImmutableResult<Integer> r1 = createResult(success, value, code, args1);
        assertThat(r0.equals(r1)).isFalse();
    }

    @Test
    void shouldCheckWhenNotEqual_differentCodes(){
        boolean success = true;
        Integer value = 123;
        String code0 = "code0";
        String code1 = "code1";
        List<Integer> args = List.of(1, 2, 3);

        ImmutableResult<Integer> r0 = createResult(success, value, code0, args);
        ImmutableResult<Integer> r1 = createResult(success, value, code1, args);
        assertThat(r0.equals(r1)).isFalse();
    }

    @Test
    void shouldCheckWhenNotEqual_differentSuccesses(){
        boolean success0 = true;
        boolean success1 = false;
        Integer value = 123;
        String code = "code";
        List<Integer> args = List.of(1, 2, 3);

        ImmutableResult<Integer> r0 = createResult(success0, value, code, args);
        ImmutableResult<Integer> r1 = createResult(success1, value, code, args);
        assertThat(r0.equals(r1)).isFalse();
    }

    @Test
    void shouldCheckWhenNotEqual_differentValues(){
        boolean success = true;
        Integer value0 = 123;
        Integer value1 = 1234;
        String code = "code";
        List<Integer> args = List.of(1, 2, 3);

        ImmutableResult<Integer> r0 = createResult(success, value0, code, args);
        ImmutableResult<Integer> r1 = createResult(success, value1, code, args);
        assertThat(r0.equals(r1)).isFalse();
    }

    @Test
    void shouldCheckArgAddition(){
        List<Object> expectedArgs = List.of("", 1, 2, "", "",  3, "",  4);
        ImmutableResult<Void> result = ImmutableResult.<Void>builder()
            .arg(null)
            .arg(3)
            .arg(null)
            .arg(4)
            .beginArg(null)
            .beginArg(2)
            .beginArg(1)
            .beginArg(null)
            .build();
        assertThat(expectedArgs.toArray()).isEqualTo(result.getArgs());
    }

    private ImmutableResult<Integer> createResult(boolean success, Integer value, String code, List<Integer> args) {
        Builder<Integer> builder = ImmutableResult.<Integer>builder()
            .success(success)
            .value(value)
            .code(code);
        args.stream().forEach(builder::arg);

        return builder.build();
    }
}
