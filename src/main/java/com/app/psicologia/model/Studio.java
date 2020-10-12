package com.app.psicologia.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "studios")
public class Studio {
    @Id
    @Getter @Setter
    private Long id;

//    @Indexed(unique = true)
    @Getter @Setter
    private String studioName;

    @Getter@Setter
    private String studioDescription;

    @Getter@Setter
    private List<QuizTest> studioTests= new ArrayList<QuizTest>();

}
