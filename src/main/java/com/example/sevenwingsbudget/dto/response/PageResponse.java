package com.example.sevenwingsbudget.dto.response;

import java.util.List;

public record PageResponse<T>(List<T> data, int page, int size, long totalElements) {
}
