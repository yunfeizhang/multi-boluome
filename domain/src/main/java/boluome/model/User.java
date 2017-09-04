package boluome.model;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 */
@Data
@AllArgsConstructor
@ApiModel
public class User {
    private String id;
    private String name;
    private int age;
}
