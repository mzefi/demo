package com.example.demo.googleactionsobjects;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class GoogleActionsOpenUrl {
    private String url;
    @Builder.Default
    private String hint = "LINK_UNSPECIFIED";
    
}
