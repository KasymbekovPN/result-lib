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
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((seed == null) ? 0 : seed.hashCode());
        result = prime * result + (success ? 1231 : 1237);
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ImmutableResult<T> other = (ImmutableResult<T>) obj;
        if (seed == null) {
            if (other.seed != null)
                return false;
        } else if (!seed.equals(other.seed))
            return false;
        if (success != other.success)
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
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
