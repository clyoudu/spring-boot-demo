package github.clyoudu.spring.boot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Set;

/**
 * @author leichen
 * @date 2020/4/18 6:12 下午
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RedisTemplateTest {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    ValueOperations<String, Object> valueOperations;

    @Autowired
    HashOperations<String, String, Object> hashOperations;

    @Autowired
    ListOperations<String, Object> listOperations;

    @Autowired
    SetOperations<String, Object> setOperations;

    @Autowired
    ZSetOperations<String, Object> zSetOperations;

    @Before
    public void before () {
        redisTemplate.delete(Arrays.asList("foo", "bar"));
    }

    @Test
    public void testString () {
        valueOperations.set("foo", "bar");
        Assert.assertEquals("bar", valueOperations.get("foo"));
        redisTemplate.delete("foo");

        Assert.assertEquals(1L, valueOperations.increment("foo", 1).longValue());
        Assert.assertEquals(4L, valueOperations.increment("foo", 3).longValue());
    }

    @Test
    public void testList () {
        listOperations.rightPushAll("foo", "a", "b", "c");
        Assert.assertEquals("c", listOperations.rightPop("foo"));
        Assert.assertEquals("a", listOperations.leftPop("foo"));
        Assert.assertEquals(1L, listOperations.size("foo").longValue());
    }

    @Test
    public void testHash () {
        hashOperations.put("foo", "age", 26);
        hashOperations.put("foo", "weight", 60);
        hashOperations.put("foo", "height", 180);
        hashOperations.put("foo", "name", "ashe");
        hashOperations.put("foo", "view", 0);
        Assert.assertEquals(26, hashOperations.get("foo", "age"));
        Assert.assertEquals("ashe", hashOperations.get("foo", "name"));

        hashOperations.increment("foo", "view", 100);
        Assert.assertEquals(100, hashOperations.get("foo", "view"));
    }

    @Test
    public void testSet () {
        Assert.assertEquals(3 ,setOperations.add("foo", "a", "b", "c").longValue());
        Assert.assertEquals(2 ,setOperations.add("foo", "a", "d", "e").longValue());
        Assert.assertEquals(3 ,setOperations.add("bar", "a1", "b", "c1").longValue());
        Assert.assertEquals(2 ,setOperations.add("bar", "a1", "d1", "e").longValue());

        Set<Object> difference = setOperations.difference("foo", "bar");
        Assert.assertFalse(difference.contains("b"));
        Assert.assertFalse(difference.contains("e"));
        Assert.assertEquals(3, difference.size());

        Set<Object> union = setOperations.union("foo", "bar");
        Assert.assertEquals(8, union.size());

        Set<Object> intersect = setOperations.intersect("foo", "bar");
        Assert.assertEquals(2, intersect.size());
    }

    @Test
    public void testZset () {
        zSetOperations.add("foo", "uzi", 1000);
        zSetOperations.add("foo", "clearlove", 900);
        zSetOperations.add("foo", "theshy", 800);
        zSetOperations.add("foo", "rookie", 700);

        Assert.assertEquals(1, zSetOperations.rank("foo", "theshy").longValue());
        Assert.assertEquals(2, zSetOperations.reverseRank("foo", "theshy").longValue());
        Assert.assertEquals(2, zSetOperations.range("foo", 0, 1).size());
        Assert.assertEquals(950, zSetOperations.incrementScore("foo", "rookie", 250).intValue());
        Assert.assertEquals(950, zSetOperations.score("foo", "rookie").intValue());
        Assert.assertEquals(1, zSetOperations.rangeByScoreWithScores("foo", 700, 850).size());
    }

}
