package com.example.sqs.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VoteDto implements Serializable {

    @JsonProperty("candidateName")
    private String candidateName;

    @JsonProperty("votes")
    private int votes;
}
