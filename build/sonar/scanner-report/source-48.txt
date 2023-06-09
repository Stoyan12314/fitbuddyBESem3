package org.example.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.Request;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetRequestsResponse {
    List<Request> request;
}
