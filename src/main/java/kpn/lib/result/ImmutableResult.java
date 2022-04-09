package kpn.lib.result;

import java.util.ArrayDeque;
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
            this.args.addFirst(arg);
            return this;
        }

        public Builder<T> arg(Object arg) {
            this.args.addLast(arg);
            return this;
        }
    }
}
