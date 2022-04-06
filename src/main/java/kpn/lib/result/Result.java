package kpn.lib.result;

public interface Result<T> {
    boolean isSuccess();
    T getValue();
    String getCode();
    Object[] getArgs();
}
