package com.stacksurveyor.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table
@Data
@AllArgsConstructor
public class Survey {
    @PrimaryKey
    private UUID id;

    private UUID userId;
    private String title;
    private String description;
}
