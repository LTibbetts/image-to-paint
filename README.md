# Image2Paint

Allows for the user to transform an image into one that resembles a painting.

## Build / Install

Requires Gradle.

In a terminal:

1. Clone the repository
2. cd to the directory containing the build.gradle file
3. gradle installDist
4. cd build/install/image2paint/bin/
5. Run either image2paint.bat or ./image2paint dependent on your OS.

## Usage

Currently file selection is hard-coded. In order to change the picture that you are editing then you must change the source image in src/image2paint/Application.java

Once the application has been built/run with the desired file the image should show on the left side of the window. Alterations to the right side can be made by clicking or dragging on the left image.

Options for the controls of the brush stroke are found on the right side of the screen.

## Example

![Source Image](src/resources/results.png?raw=true)
