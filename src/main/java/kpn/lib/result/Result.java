package kpn.lib.result;

import kpn.lib.seed.Seed;

public interface Result<T> {
    boolean isSuccess();
    T getValue();
    Seed getSeed();
}
