package com.example.ncpmaps;

import com.example.ncpmaps.client.NcpHttpInterface;
import com.example.ncpmaps.dto.NaviRouteDto;
import com.example.ncpmaps.dto.NaviWithPointsDto;
import com.example.ncpmaps.dto.PointDto;
import com.example.ncpmaps.dto.direction.DirectionNcpResponse;
import com.example.ncpmaps.dto.direction.DirectionRoute;
import com.example.ncpmaps.dto.rgeocoding.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class NcpmapsService {
    private final NcpHttpInterface exchange;

    public NaviRouteDto directions5(NaviWithPointsDto dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("start", dto.getStart().toQueryValue());
        params.put("goal", dto.getGoal().toQueryValue());
        log.info("start & goal : {}, {}", dto.getStart(), dto.getGoal());

        DirectionNcpResponse response = exchange.directions5(params);
        log.info("response:: " + response);

        DirectionRoute route = response.getRoute().get("traoptimal").get(0);
        List<PointDto> path = PointDto.directionRouteToPointDto(route);
        NaviRouteDto result = new NaviRouteDto(path);

        return result;
    }

    public RGeoResponseDto reverseGeocoding(PointDto dto) {
        log.info("dto came : "+dto);

        Map<String, Object> params = new HashMap<>();
        params.put("coords", dto.toQueryValue());
        params.put("output", "json");
        params.put("orders", "roadaddr");
        log.info(params.toString());

        RGeoNcpResponse response = exchange.reverseGeocoding(params);
        log.info("response :  " + response);
        RGeoRegion rGeoResultRegion = response.getResults().get(0).getRegion();
        RGeoLand rGeoResultLand = response.getResults().get(0).getLand();

        StringBuffer sb = new StringBuffer();
        sb.append(rGeoResultRegion.getArea1().getName()).append(" ");
        sb.append(rGeoResultRegion.getArea2().getName()).append(" ");
        sb.append(rGeoResultRegion.getArea3().getName()).append(" ");
        sb.append(rGeoResultLand.getName()).append(" ").append(rGeoResultLand.getNumber1());

        RGeoResponseDto result = new RGeoResponseDto(sb.toString());
        return result;

    }
}