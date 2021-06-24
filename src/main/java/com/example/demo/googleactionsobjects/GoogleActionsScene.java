package com.example.demo.googleactionsobjects;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class GoogleActionsScene {
    private String name;
    private String slotFillingStatus;
    private Map<String, Object> slots;
    private GoogleActionsNextScene next;
}
