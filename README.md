# Talent Matrix Podcast Challenge
## Setup
You will need Java 11 (or newer) and the maven build system installed on your machine.

For OSX:
* Java 11: `brew install openjdk@11`
* Maven: `brew install maven`

The Linux equivalents should be similar, but might vary based on your distribution's package manager. If you have other
Java versions installed, you may need to set the `JAVA_HOME` environment variable to a Java 11 JDK.

Once you've cloned the repository, run `mvn compile exec:java -q`. This should print "Ready to begin!" and then a success
message to the command line, which indicates that the packages have been installed properly and you're ready to go!

We recommend that you use a Java IDE to complete this challenge; if you don't have one, check out the free version of
[IntelliJ](https://www.jetbrains.com/idea/download/). You are welcome to complete it with a text editor, but importing
additional classes will be required, so depending on your editor setup you might have to add import statements manually.

## Task
Your task is based on some real data processing that we do for analytics on our podcasts.

Make sure to read the full instructions before continuing: a few of the data formats are a little tricky.

### Steps
Open the `src/main/java/com/washpost/talentmatrix/Analytics.java` file. This is where you'll be making changes.

1. Read the server log data. The logs are in a TSV (tab-separated values) format, and stored at `./data/logs.tsv`.
2. Filter the logs to only include production podcast downloads
   - URL path will start with /washpost-production/ (URL path is the 8th field in the TSV)
   - GET requests only (request method is the 6th field)
   - Status code will be 200 or 206 (status is the 9th field)
3. Extract the following data from each entry in the filtered list:
   - Request IP address (5th field)
   - User agent (11th field)
   - Podcast episode ID (a subset of URL path, 8th field. See below for the URL format.)
4. Using the podcast epsiode ID, fetch the episode title from the WaPo Audio API.
5. Create an AnalyticsMessage object with the fields you collected and push it to the provided list
   - ipAddress
   - userAgent
   - episodeId
   - title

### Notes
The podcast URL paths are in the format `/washpost-production/<SERIES ID>/<PUB DATE>/<EPISODE ID>/<other file data...>`

The URL for the WaPo Audio API you will need is provided in `Analytics.java`. You just need to call the base route with
the episodeId at the end to retrieve the information about that episode. For instance, if the episode id were "00012",
you would make a GET request to `{BASE_URL}/00012`.

Hint: at each step, make sure that the code you've written to parse the data provided is returning the thing you expect.

## Developing and Testing
If you want to run and test from the command line, run `mvn compile exec:java` to run the main method; use this to
check your work while you're developing. When you're ready to test against the result set, run `mvn test`.

If you prefer to run the code from your IDE, you can run the `Analytics.java` file while developing, and the
`AnalyticsTest.java` for full testing.

## Libraries
We recommend using the built-in `HttpClient` for HTTP requests. [Here is a simple
example](https://openjdk.java.net/groups/net/httpclient/recipes.html) you can refer to.

We recommend using the [Jackson Databind library](https://www.baeldung.com/jackson-object-mapper-tutorial) to parse JSON
requests. Jackson is already provided as part of the package's POM file, so you can just import it directly.

## Credit
Thanks to the Washington Post Media Team for writing original version of this challenge, which has been adapted here as
a maven build.

