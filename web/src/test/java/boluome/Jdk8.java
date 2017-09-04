package boluome;

import boluome.model.Artist;
import boluome.model.Artist2;
import boluome.model.MyLogger;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.apache.log4j.NDC.get;

/**
 * Created by yunfei on 2017/9/1.
 */
@Slf4j
public class Jdk8 {

    @Test
    public void lamda(){
        new Thread(()->log.debug("lamda:".concat(Thread.currentThread().getId()+""))).run();
    }

    @Test
    public void stream(){
        log.debug("stream:artists");
        List<Artist> artists = Arrays.asList(new Artist("张三","广东"),
                new Artist("李四","北京"),new Artist("大圣","湖南"));

        long count = 0;
        for(Artist artist : artists){
            if(artist.isFrom("北京")){
                count ++;
            }
        }
        log.debug(" 来自 北京 有 " + count  + "人");

        log.debug("--------------------------> iterator 过滤 计数");
        Iterator<Artist> iterator = artists.iterator();
        while (iterator.hasNext()){
            Artist artist = iterator.next();
            log.debug(artist.getName().concat(" 来自 ").concat(artist.getFrom()));
        }

        log.debug("--------------------------> filter 过滤 计数");
        count = artists.stream()
                .filter(artist -> artist.isFrom("北京")).count();

        log.debug(" 来自 北京 有 " + count  + "人");

        log.debug("--------------------------> filter 过滤 生成集合");
        List<Artist> filter2 = artists.stream().filter(artist -> !artist.isFrom("北京")).collect(Collectors.toList());
        filter2.forEach(artist -> log.debug(artist.getName().concat(" 来自 ").concat(artist.getFrom())));

        log.debug("--------------------------> filter 过滤 生成集合");
        artists.stream().filter(artist -> {
            log.debug(artist.getName());
            return artist.isFrom("北京");
        });
        log.debug("--------------------------> filter 过滤 生成集合");
        artists.stream().filter(artist -> {
            log.debug(artist.getName());
            return artist.isFrom("北京");
        }).count();

        List<Artist> artists2 = Stream.of(new Artist("张三","广东"),
                new Artist("李四","北京"),new Artist("大圣","湖南")).collect(Collectors.toList());
        artists2.forEach(artist -> log.debug(artist.getName().concat(">>>>>").concat(artist.getFrom())));
    }

    @Test
    public void map(){
        List<String> collected = new ArrayList<>();
        for (String string:Stream.of("a","b","hello").collect(Collectors.toList())){
            String up = string.toUpperCase();
            collected.add(up);
            log.debug(">>"+up);
        }

        log.debug("------------------------map");
        List<String> upList = Stream.of("a","b","hello").map(string->string.toUpperCase()).collect(Collectors.toList());
        upList.forEach(string->log.debug("==="+string));


        String[] strings = "1,2,3,4,5,6".split(",");
        List<String> list = new ArrayList<>();

    }

    @Test
    public void flatMap(){
        List<Integer> together = Stream.of(Arrays.asList(1,2),Arrays.asList(3,4)).flatMap(list->list.stream()).collect(Collectors.toList());
        together.forEach(value->log.debug("-->{}",value));
    }

    @Test
    public void minOrMax(){
        List<Artist> artists = Arrays.asList(new Artist("张三","广东"),
                new Artist("李四","北京"),new Artist("大圣","湖南"));
        Artist min = artists.stream().min(Comparator.comparing(v->v.getName().hashCode())).get();
        log.debug("minInfo:{}",min);

        Artist max = artists.stream().max(Comparator.comparing(v->v.getName().hashCode())).get();
        log.debug("maxInfo:{}",max);

    }

    @Test
    public void reduce(){
        int count = Stream.of(1,2,3)
                .reduce(0,(acc,element)->acc + element);
        log.debug("========{}",count);

        BinaryOperator<Integer> accumulator = (acc,ele)->acc+ele;
        int c = accumulator.apply(0,accumulator.apply(1,accumulator.apply(2,3)));
        log.debug("----------{}",c);

    }

    @Test
    public void addUp(){
        //求和
        log.debug("addUp：={}",Stream.of(1,2,3,4,5,6,7,8,9,10).reduce((x,y)->x+y).get());
        log.debug("addUp：={}",Stream.of(1,2,3,4,5,6,7,8,9,10).reduce(0,(x,y)->x+y));
    }

    @Test
    public void forEach(){
        //遍历艺术家列表，输出艺术家信息字符串列表
        List<String> artistsList = Stream.of(new Artist("张艺术家","中国"),new Artist("孙艺术家","美国"),new Artist("猪艺术家","英国"))
                .map(artist -> artist.getName().concat("->").concat(artist.getName())).collect(Collectors.toList());
        log.debug("forEach: {}",artistsList);
    }

    @Test
    public void reduce2(){
        log.debug("-- {}", Stream.of(new Artist2("张艺术家",10),new Artist2("孙艺术家",12),new Artist2("猪艺术家",18))
                .map(artist2 -> artist2.getMembers())
                .reduce(0,(x,y)->x+y));

        log.debug("-- {}", Stream.of(new Artist2("张艺术家",10),new Artist2("孙艺术家",12),new Artist2("猪艺术家",18))
                .map(artist2 -> artist2.getMembers())
                .reduce((x,y)->x+y).get());

    }

    @Test
    public void forEach3(){
        Stream<Artist2> stream = Stream.of(new Artist2("张艺术家",10),new Artist2("孙艺术家",12),new Artist2("猪艺术家",18));
        //log.debug("-- {}", stream.collect(Collectors.toList()));
        stream.forEach(artist2 -> artist2.setMembers(artist2.getMembers()+1));
        log.debug("-- {}", stream.collect(Collectors.toList()));
    }

    @Test
    public void testGroupingBy(){
        Stream<Artist2> stream3 = Stream.of(new Artist2("张艺术家",10),new Artist2("张艺术家",12),new Artist2("猪艺术家",18));
        Map<String, Long> numEmployeesByCity;
        numEmployeesByCity = stream3.collect(groupingBy(Artist2::getName,counting()));
        log.debug("-- {}", numEmployeesByCity);
    }

    @Test
    public void debug(){
        MyLogger logger = new MyLogger();
        logger.debug(()-> "\"ssssssss\""+get());

        log.debug("{}",new int[]{1});
    }

}
