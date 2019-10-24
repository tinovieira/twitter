package com.echobox.twitter;

import org.junit.Assert;
import org.junit.Test;

public class TwitterCharacterCounterTest {

    @Test
    public void testForNormalString() {
        String tweet = "Former UNM law professor named FERC chair";
        TwitterCharacterCounter counter = new TwitterCharacterCounter();
        Assert.assertEquals(tweet.length(), counter.getCount(tweet));
    }

    @Test
    public void testForStringWithCombinedCharacter() {
        String tweet = "cafe\u0301";
        TwitterCharacterCounter counter = new TwitterCharacterCounter();
        Assert.assertEquals(4, counter.getCount(tweet));
    }

    @Test
    public void testForStringWithDiacriticalCharacter() {
        String tweet = "caféçà º";
        TwitterCharacterCounter counter = new TwitterCharacterCounter();
        Assert.assertEquals(8, counter.getCount(tweet));
    }

    @Test
    public void testForStringWithUrl() {
        String tweet = "http://bit.ly/1IifCDF";
        TwitterCharacterCounter counter = new TwitterCharacterCounter();
        Assert.assertEquals(23, counter.getCount(tweet));
    }

    @Test
    public void testForStringWithUrlAndHttpsAndUserPass() {
        String tweet = "https://user:pass@bit.ly/1IifCDF";
        TwitterCharacterCounter counter = new TwitterCharacterCounter();
        Assert.assertEquals(23, counter.getCount(tweet));
    }

    @Test
    public void testForStringWithUrlWithoutProtocol() {
        String tweet = "bit.ly/1IifCDF";
        TwitterCharacterCounter counter = new TwitterCharacterCounter();
        Assert.assertEquals(23, counter.getCount(tweet));
    }

    @Test
    public void testForStringWithUnParsableUrls() {
        String tweet = "bit.ly/1IifCDF,bit.ly/1IifCDF";
        TwitterCharacterCounter counter = new TwitterCharacterCounter();
        Assert.assertEquals(29, counter.getCount(tweet));
    }

    @Test
    public void testForStringWithTwoUrls() {
        String tweet = "bit.ly/1IifCDF and http://bit.ly/1IifCDF";
        TwitterCharacterCounter counter = new TwitterCharacterCounter();
        Assert.assertEquals(58, counter.getCount(tweet));
    }

    @Test
    public void testForStringWithEncodedComma() {
        String tweet = "bit.ly/1IifCDF%2C";
        TwitterCharacterCounter counter = new TwitterCharacterCounter();
        Assert.assertEquals(23, counter.getCount(tweet));
    }

    @Test
    public void testForTweet() {
        String tweet = "Former UNM law professor named FERC chair http://bit.ly/1IifCDF";
        TwitterCharacterCounter counter = new TwitterCharacterCounter();
        Assert.assertEquals(65, counter.getCount(tweet));
    }

}
