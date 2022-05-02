# FootballAPI

I created different packages to organize the project like we usually do.
The businesslogic contains an interface with a request method to get the information from the API's URL.

In the controller there is a method to convert images from SVG to BufferedImage. I followed this link https://edencoding.com/svg-javafx/#svg-bufferedimage, I just edited the inputstream, and added conditions to check if an image is SVG by checking its last 3 characters (because there is also png images that work properly).

There is an error in this image https://crests.football-data.org/UCL.png 
