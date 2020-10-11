package com.example.parking.tariffservice.routing;


import com.example.parking.tariffservice.core.ThreadLocalStorage;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class TenantAwareRoutingSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return ThreadLocalStorage.getTenantName();
    }
}
