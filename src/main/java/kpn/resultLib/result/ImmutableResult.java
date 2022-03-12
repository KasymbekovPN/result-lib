package kpn.resultLib.result;

import java.util.ArrayList;
import java.util.List;

public class ImmutableResult<T> implements Result<T> {

    private final boolean success;
    private final T value;
    private final String code;
    private final Object[] args;

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    private ImmutableResult(boolean success, T value, String code, Object[] args) {
        this.success = success;
        this.value = value;
        this.code = code;
        this.args = args;
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
    public String getCode() {
        return code;
    }

    @Override
    public Object[] getArgs() {
        return args;
    }

    public static class Builder<T>{
        private boolean success;
        private T value;
        private String code;
        private final List<Object> args = new ArrayList<>();

        public ImmutableResult<T> build() {
            return new ImmutableResult<>(success, value, code, args.toArray());
        }

        public Builder<T> success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder<T> value(T value) {
            this.value = value;
            return this;
        }

        public Builder<T> code(String code) {
            this.code = code;
            return this;
        }

        public Builder<T> arg(Object arg) {
            args.add(arg);
            return this;
        }
    }
}
