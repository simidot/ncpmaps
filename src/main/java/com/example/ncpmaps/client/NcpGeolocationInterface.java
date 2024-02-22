package com.example.ncpmaps.client;

import com.example.ncpmaps.dto.geolocation.GeoLocationNcpResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.util.Map;

public interface NcpGeolocationInterface {

    @GetExchange
    GeoLocationNcpResponse geoLocation(
            @RequestParam Map<String, Object> params
    );
}
