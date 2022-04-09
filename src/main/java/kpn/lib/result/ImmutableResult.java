package kpn.lib.result;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class ImmutableResult<T> implements Result<T> {

    private final boolean success;
    private final T value;
    private final String code;
    private final Object[] args;

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static <T> Builder<T> ok(T value){
        return new Builder<T>().success(true).value(value);
    }

    public static <T> Builder<T> fail(String code){
        return new Builder<T>().success(false).code(code);
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
        private final Deque<Object> args = new ArrayDeque<>();

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

        public Builder<T> beginArg(Object arg){
            this.args.addFirst(checkAndGetArg(arg));
            return this;
        }

        public Builder<T> arg(Object arg) {
            this.args.addLast(checkAndGetArg(arg));
            return this;
        }

        private Object checkAndGetArg(Object arg) {
            return arg != null ? arg : "";
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(args);
        result = prime * result + ((code == null) ? 0 : code.hashCode());
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
        if (!Arrays.deepEquals(args, other.args))
            return false;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
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
}
