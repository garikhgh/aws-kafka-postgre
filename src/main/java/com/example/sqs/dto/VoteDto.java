package com.example.sqs.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VoteDto {

    @JsonProperty("candidateName")
    private String candidate;

    @JsonProperty("votes")
    private int votes;
}
