package com.example.ncpmaps.client;

import com.example.ncpmaps.dto.direction.DirectionNcpResponse;
import com.example.ncpmaps.dto.geocoding.GeoNcpResponse;
import com.example.ncpmaps.dto.geolocation.GeoLocationNcpResponse;
import com.example.ncpmaps.dto.rgeocoding.RGeoNcpResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.util.Map;

public interface NcpHttpInterface {
    @GetExchange("/map-direction/v1/driving")
    DirectionNcpResponse directions5(
            @RequestParam Map<String, Object> params
    );

    @GetExchange("/map-reversegeocode/v2/gc")
    RGeoNcpResponse reverseGeocoding(
            @RequestParam Map<String, Object> params
    );

    @GetExchange("/map-geocode/v2/geocode")
    GeoNcpResponse geoCoding(
            @RequestParam Map<String, Object> params
    );


}
