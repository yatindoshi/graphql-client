package com.yatin.graphqlclient.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class GraphqlRequestBody {

    private String query;
    private Map<String,Object> variables;
}
