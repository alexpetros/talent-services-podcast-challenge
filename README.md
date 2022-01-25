# Talent Matrix Java Screen
## Setup
You will need Java 11+ installed on your machine, as well as the maven build system which usually installs with it. If you don't have one, we recommend openjdk, which you can install on OSX with `brew insall openjdk@11`. The Linux equivalent should be similar, but might vary based on your distribution's package manager.

Once you've cloned the repository, run `mvn -q compile exec:java`. This should print "Ready to begin!" to the command line, which indicates that the packages have been installed properly and you're ready to go!

## Task
Your task is based on some real data processing that we do for analytics on our podcasts.

The required steps are:
- Get the server log data
   - Normally, we will fetch from S3, but for the purposes of this exercise the data you need will be in a file at ./data/logs.tsv
   - The logs are formatted in TSV (tab-separated values) format
- Filter to only production podcast downloads
   - URL path will start with /washpost-production/ (URL path is the 8th field in the TSV)
   - GET requests only (request method is the 6th field)
   - Status code will be 200 OK or 206 Partial Content (status is the 9th field)
- Extract the following data from each log entry:
   - Request IP address (5th field)
   - User agent (11th field)
   - Podcast episode ID (from URL path, 8th field)
       - URL paths are in the format `/washpost-production/<SERIES ID>/<PUB DATE>/<EPISODE ID>/<other file data...>`
- Push an AnalyticsMessage object with the fields you collected to the Analytics API (the AnalyticsMessage class is provided)
   - ipAddress
   - userAgent
   - episodeId
- Fetch the following data for each episode from the WaPo Audio API (field name in parentheses)
   - Episode title (title)
- Add the following fields extracted from the Audio API to the analytics object you are sending
   - title

The URL for the WaPo Audio API you will need is provided in `Analytics.java`. The Analytics API is mocked with a method called postAnalytics() that will simulate sending data to an analytics server. It accepts one parameter, an AnalyticsMessage object.

When you have successfully completed all steps, you will get an "All tests passed" message when you run the code.

## Running in CoderPad
The packages in CoderPad are somewhat limited. You should be able to use anything in the Java standard library. CoderPad has also provided the json-simple package for JSON parsing. This library is unfortunately not very well documented, but you can review [this tutorial](https://www.tutorialspoint.com/json_simple/json_simple_quick_guide.htm) to get a sense of how to use it (go to the "JSON.simple - Using JSONValue" section).

We recommend using the built-in `HttpClient` for HTTP requests. [Here is a simple example](https://openjdk.java.net/groups/net/httpclient/recipes.html) you can refer to.
