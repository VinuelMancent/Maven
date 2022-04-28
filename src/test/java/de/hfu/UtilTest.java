package de.hfu;

import static org.junit.Assert.assertTrue;

import org.junit.Before;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class UtilTest 
{
	Util util;
	
	@Before
	public void initUtil() {
		util = new Util();
	}
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testIstErstesHalbjahrSuccess()
    {
       assertTrue(util.istErstesHalbjahr(1));
       assertTrue(util.istErstesHalbjahr(6));
       assertFalse(util.istErstesHalbjahr(7));
       
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testIstErstesHalbjahrException() {
    	util.istErstesHalbjahr(-1);
    	util.istErstesHalbjahr(13);
    }
}
