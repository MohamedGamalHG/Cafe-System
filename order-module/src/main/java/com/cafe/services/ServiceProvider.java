package com.cafe.services;

import java.util.Optional;

public interface ServiceProvider {
    Optional<?> fetchData(int[] ids);
}
