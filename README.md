#MyMovies
A simple yet sufficient android application to fetch: 
- Popular Movies
- Top Rated Movies
- Upcoming Movies
using api from [using api from [moviedb.org](https://www.themoviedb.org/)]

#Configuring your API key
  The purpose of configuring your API key is for it not to be visible to your users.
#How to configure
 -First create an account in moviesdb.org where you will be given your Apikey.
 - Then create a file called apikey.properties on the root project folder then add this
  line into the file API_KEY=XXXXXXXXXXX where the XXXXXXXXXXX is you actual apiKey.
  -To avoid these keys being seen by other users ,add it to your .gitgnore file
  -For the apikey to be accessed in your project add this lines in app/build.gradle
                 
           def apikeyPropertiesFile = rootProject.file("apikey.properties")
           def apikeyProperties = new Properties()
           apikeyProperties.load(new FileInputStream(apikeyPropertiesFile))
    
   -Still in the app/build.gradle under the section android and default config such that
                 android {
                 
                   defaultConfig {
                 
                     buildConfigField("String", "API_KEY", apikeyProperties['API_KEY']) // ADD THIS LINE
                    
                   }
                 }
    -After all this is done one can access it in their project in the code below
            val apiKey=BuildConfig.API_KEY
            
    