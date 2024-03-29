package com.example.ncpmaps;

import com.example.ncpmaps.dto.*;
import com.example.ncpmaps.dto.rgeocoding.RGeoResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("navigate")
@RequiredArgsConstructor
@Slf4j
public class NaviController {
    private final NaviService service;

    // 두 좌표를 받아 이동경로를 반환하는 메서드
    @PostMapping("points")
    public NaviRouteDto withPoints(
            @RequestBody
            NaviWithPointsDto dto
    ) {
        log.info("controller dto : "+dto);
        return service.directions5(dto);
    }

    // 하나의 좌표를 입력받아, 주소를 반환하는 메서드
    @PostMapping("get-address")
    public RGeoResponseDto getAddress(
            @RequestBody
            PointDto point
    ) {
        return service.reverseGeocoding(point);
    }

    // 하나의 좌표와 주소를 입력받아, 좌표에서
    // 주소검색 결과 위치로의 이동경로를 반환하는 메서드
    @PostMapping("start-query")
    public NaviRouteDto withQuery(
            @RequestBody
            NaviWithQueryDto dto
    ) {
        return service.withQuery(dto);
    }

    // 두 IP 주소를 입력받아 이동경로를 반환하는 메서드
    @PostMapping("ips")
    public NaviRouteDto withIpAddresses(
            @RequestBody
            NaviWithIpsDto dto
    ) {
        return service.withIpAddresses(dto);
    }
}
