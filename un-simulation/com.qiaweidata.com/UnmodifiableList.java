package cn.afterturn.easypoi.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author by jueyue on 19-10-8.
 */
public class UnmodifiableList
{
    @Test
    public void test()
    {
        Collection collection = Collections.unmodifiableCollection(new ArrayList<>());
        Assert.assertTrue(collection.getClass().getName().contains("Unmodifiable"));
        List list = Collections.unmodifiableList(new ArrayList<>());
        Assert.assertTrue(list.getClass().getName().contains("Unmodifiable"));
        Set set = Collections.unmodifiableSet(new HashSet<>());
        Assert.assertTrue(set.getClass().getName().contains("Unmodifiable"));
    }
}
