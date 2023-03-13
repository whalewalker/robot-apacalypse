package com.robotapocalypse.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robotapocalypse.data.dto.ApiResponseDTO;
import com.robotapocalypse.utils.RequestManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RobotServiceImpl implements RobotService {
    private final RequestManager requestManager;

    @Value("${robot.base.url}")
    private String baseUrl;

    @Override
    public ApiResponseDTO getRobots() {
        ResponseEntity<String> response = requestManager.getRequest(baseUrl);
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> robots = new ArrayList<>();

        try {
            JsonNode root = mapper.readTree(response.getBody());
            String category = "category";
            for (JsonNode node : root) {
                Map<String, Object> robot = new HashMap<>();
                robot.put("model", node.get("model").asText());
                robot.put("serialNumber", node.get("serialNumber").asText());
                robot.put("manufacturedDate", node.get("manufacturedDate").asText());
                robot.put(category, node.get(category).asText());

                robots.add(robot);
            }

            // Sort robots by category
            robots.sort((o1, o2) -> {
                String category1 = (String) o1.get(category);
                String category2 = (String) o2.get(category);
                return category1.compareTo(category2);
            });
            return new ApiResponseDTO<>(true, "Successfully fetched robots", robots);
        } catch (IOException e) {
            e.printStackTrace();
            return new ApiResponseDTO<>(false, "Error occurred when fetching robots data");
        }
    }
}
