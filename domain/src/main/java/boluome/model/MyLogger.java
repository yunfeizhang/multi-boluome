package boluome.model;

import java.util.function.Supplier;

/**
 * Created by yunfei on 2017/9/2.
 */
public class MyLogger {
    public void debug(Supplier<String> msg){
        if(isDebugEnabled()){
            debug(msg.get());
        }
    }

    private void debug(String s) {
        System.out.print(s);
    }


    public boolean isDebugEnabled() {
        return true;
    }
}
