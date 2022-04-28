package kpn.lib.seed;

import java.util.ArrayDeque;
import java.util.Deque;

public class ImmutableSeed implements Seed {
    private final String code;
    private final Object[] args;

    public static Builder builder(){
        return new Builder();
    }

    private ImmutableSeed(String code, Object[] args) {
        this.code = code;
        this.args = args;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public Object[] getArgs() {
        return args;
    }

    public static class Builder{
        private String code;
        private final Deque<Object> args = new ArrayDeque<>();

        public Builder code(String code){
            this.code = code;
            return this;
        }

        public Builder beginArg(Object arg){
            this.args.addFirst(checkAndGetArg(arg));
            return this;
        }

        public Builder arg(Object arg) {
            this.args.addLast(checkAndGetArg(arg));
            return this;
        }

        public ImmutableSeed build(){
            return new ImmutableSeed(code, args.toArray());
        }

        private Object checkAndGetArg(Object arg) {
            return arg != null ? arg : "";
        }
    }
}
