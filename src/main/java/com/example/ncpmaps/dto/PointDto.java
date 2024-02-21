package com.example.ncpmaps.dto;

import com.example.ncpmaps.dto.direction.DirectionRoute;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class PointDto {
    private Double lng;
    private Double lat;

    public String toQueryValue() {
        return String.format("%f,%f", lng, lat);
    }

    public static PointDto stringToPointDto(String s) {
        String[] split = s.split(",");
        PointDto pointDto = new PointDto(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
        return pointDto;
    }

    public static List<PointDto> directionRouteToPointDto(DirectionRoute directionRoute) {
        List<PointDto> path = new ArrayList<>();
        for (int i = 0; i < directionRoute.getPath().size(); i++) {
            Double lat = directionRoute.getPath().get(i).get(1);
            Double lng = directionRoute.getPath().get(i).get(0);
            PointDto dto = new PointDto(lng, lat);
            path.add(dto);
        }
        return path;
    }
}
