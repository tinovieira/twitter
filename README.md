# twitter
# Twitter Char Count #

## instructions ##

to run the tests:

```mvn test```

So looking at the Twitter Docs, character count is a bit more complicated
than a simple `'some tweet'.length`.
Twitter uses the Unicode character set with UTF-8 encoding.
It counts the codepoints of a NFC normalised string.
NFC stands for Normalisation form C. However even then its not that simple.

Twitter will shorten urls to some form of `t.co`.
There is a JSON file that you can `GET` (`https://api.twitter.com/1.1/help/configuration.json`)
which contains a property `"short_url_length"` which will give you the currently
configured length of `t.co` url shorted string.

### The rules regarding twitter URL shortening: ###

- Doesn't matter how long or short, it will be the configured length (23 currently)
- fully qualified http/s urls with or without a protocol will be shortened
- trailing `.` or `,` are ignored unless encoded
- two urls connected by a `.` or `,` are ignored
- urls with login info will be ignored

I do not have a twitter oath access token so I cannot fetch the configuration details to get the `"short_url_length"`, therefore for this exercise I will use 23 as default.

This regex: `((https?)://)?[-a-zA-Z0-9@:%._+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b([-a-zA-Z0-9()@:%_+.~#?&/=]*)` will be used
## incomplete ##
- the regex must by fine tuned
- the test `testForStringWithUnParsableUrls` is failing because the url are unparsable but the regex are parsing with two urls
Links:

- [twitter configuration docs](https://developer.twitter.com/en/docs/developer-utilities/configuration/api-reference/get-help-configuration)
- [twitter t.co links](https://developer.twitter.com/en/docs/basics/tco.html)
- [twitter text parser doc](https://developer.twitter.com/en/docs/developer-utilities/twitter-text.html)