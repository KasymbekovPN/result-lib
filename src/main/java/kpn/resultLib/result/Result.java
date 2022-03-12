package kpn.resultLib.result;

public interface Result<T> {
    boolean isSuccess();
    T getValue();
    String getCode();
    Object[] getArgs();
}
