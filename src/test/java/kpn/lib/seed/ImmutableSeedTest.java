package kpn.lib.seed;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import kpn.lib.seed.ImmutableSeed.Builder;

public class ImmutableSeedTest {

    @Test
    public void shouldCheckCode_whenNotSet(){
        ImmutableSeed seed = ImmutableSeed.builder().build();
        assertThat(seed.getCode()).isEmpty();
    }

    @Test
    public void shouldCheckCode(){
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
}
