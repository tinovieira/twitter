package com.echobox.twitter;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwitterCharacterCounter {

    private static final String QUALIFIED_URL_REGEX =
            "((https?)://)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&/=]*)";


    // it will changed
    private static final int SHORT_URL_LENGTH = 23;

    public int getCount(String stringToCount) {
        final List<String> urls = findQualifiedUrls(stringToCount);

        for (String url : urls) {
            stringToCount = stringToCount.replace(url, "");
        }

        final String normalized = Normalizer.normalize(stringToCount, Normalizer.Form.NFC);

        return urls.size() * SHORT_URL_LENGTH + normalized.length();
    }

    private List<String> findQualifiedUrls(String stringToCount) {
        final List<String> urls = new ArrayList<>();
        Pattern pattern = Pattern.compile(QUALIFIED_URL_REGEX);
        Matcher matcher = pattern.matcher(stringToCount);

        while (matcher.find()) {
            urls.add(matcher.group());
        }
        return urls;
    }

}
