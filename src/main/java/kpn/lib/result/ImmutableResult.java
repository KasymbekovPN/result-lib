package kpn.lib.result;

import kpn.lib.seed.ImmutableSeed;
import kpn.lib.seed.Seed;

public class ImmutableResult<T> implements Result<T> {

    private final boolean success;
    private final T value;
    private final Seed seed;

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static <T> Builder<T> ok(T value){
        return new Builder<T>().success(true).value(value);
    }

    public static <T> Builder<T> fail(String code){
        return new Builder<T>().success(false).code(code);
    }

    private ImmutableResult(boolean success, T value, Seed seed) {
        this.success = success;
        this.value = value;
        this.seed = seed;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public Seed getSeed() {
        return seed;
    }
    
    public static class Builder<T>{
        private boolean success;
        private T value;
        private ImmutableSeed.Builder seedBuilder = ImmutableSeed.builder();

        public Builder<T> success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder<T> value(T value) {
            this.value = value;
            return this;
        }

        public Builder<T> code(String code) {
            seedBuilder.code(code);
            return this;
        }

        public Builder<T> beginArg(Object arg){
            seedBuilder.beginArg(arg);
            return this;
        }

        public Builder<T> arg(Object arg) {
            seedBuilder.arg(arg);
            return this;
        }

        public ImmutableResult<T> build() {
            return new ImmutableResult<>(success, value, seedBuilder.build());
        }
    }

}
