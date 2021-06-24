package com.example.demo.googleactionsobjects;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class GoogleActionsContentImage extends GoogleActionsContent {
    private GoogleActionsImage image;
}
