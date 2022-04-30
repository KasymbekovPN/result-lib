package kpn.lib.seed;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import kpn.lib.seed.ImmutableSeed.Builder;

class ImmutableSeedTest {

    @Test
    void shouldCheckCode_whenNotSet(){
        ImmutableSeed seed = ImmutableSeed.builder().build();
        assertThat(seed.getCode()).isEmpty();
    }

    @Test
    void shouldCheckCode(){
        String code = "some.code";
        ImmutableSeed seed = ImmutableSeed.builder().code(code).build();
        assertThat(seed.getCode()).isEqualTo(code);
    }

    @Test
    void shouldCheckArgs_whenNotSet() {
        ImmutableSeed seed = ImmutableSeed.builder().build();
        assertThat(seed.getArgs()).isEmpty();
    }

    @Test
    void shouldCheckArgs_beginSet() {
        List<Integer> args = new ArrayList<>(List.of(1,2,3));
        
        Builder builder = ImmutableSeed.builder();
        args.stream().forEach(builder::beginArg);
        ImmutableSeed seed = builder.build();

        Collections.reverse(args);
        assertThat(args.toArray()).isEqualTo(seed.getArgs());
    }

    @Test
    void shouldCheckArgs() {
        List<Integer> args = List.of(1,2,3);
        Builder builder = ImmutableSeed.builder();
        args.stream().forEach(builder::arg);
        ImmutableSeed seed = builder.build();

        assertThat(args.toArray()).isEqualTo(seed.getArgs());
    }

    @Test
    void shouldCheckEquality(){
        String code = "code";
        String arg = "arg";
        ImmutableSeed seed0 = ImmutableSeed.builder().code(code).arg(arg).build();
        ImmutableSeed seed1 = ImmutableSeed.builder().code(code).arg(arg).build();
        assertThat(seed0).isEqualTo(seed1);
    }

    @Test
    void shouldCheckHashCalculation(){
        String code = "code";
        String arg = "arg";
        ImmutableSeed seed0 = ImmutableSeed.builder().code(code).arg(arg).build();
        ImmutableSeed seed1 = ImmutableSeed.builder().code(code).arg(arg).build();
        assertThat(seed0.hashCode()).isEqualTo(seed1.hashCode());
    }
}
