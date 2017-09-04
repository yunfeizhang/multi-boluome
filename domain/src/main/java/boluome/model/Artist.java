package boluome.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;

/**
 * Created by yunfei on 2017/9/1.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist {

    private String name;

    private String from;

    public boolean isFrom(String from){
        return this.from.equals(from);
    }

    public int getMembers(){
        return new Random().nextInt(10);
    }
}
