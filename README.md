## ANDIE
ANDIE, 'A Non Destructive Image Editor', is an image editing software where
every edit is reversible, in order to maintain the integrity of the image

ANDIE offers a variety of colour, file, image, and filter actions as well as
language support for several languages.

<img width="703" alt="Screenshot 2023-11-27 at 2 53 18 PM" src="https://github.com/Janadhi14/Andie/assets/100277240/3297dabd-b6ef-4ef6-8f49-3da0b0344114">
<img width="699" alt="Screenshot 2023-11-27 at 2 56 49 PM" src="https://github.com/Janadhi14/Andie/assets/100277240/172898f5-fca8-4b5b-9269-d737bcdaf4c2">


## USER GUIDE

Open ANDIE using the given andie.jar file. Then open any image with ANDIE and explore using the drop-down menus that offer various
image-editing actions.

## FILE - the essential image opening/closing/export operations

* Save 
  * Control S
* Save As
  *  Control A
* Open
  * Control O
* Exit
  * Control E
* Export
  * Control X

## EDIT - go back and forth between actions

* Undo
  * Control Z
* Redo
  * Control Y

## VIEW - adjust your view of the image

* Zoom In
  * Shift +
* Zoom out
  * Shift -
* Zoom Full
  * Shift enter

## LANGUAGE - change ANDIE's language

* English
  * Control 1
* French
  * Control 2
* Chinese
  * Control 3
* Italian
  * Control 4
* German
  * Control 5

## FILTERS - apply various filters to the image

* Invert
* Gaussian Blur
  * Control Shift I
* Sharpen
  * Control Shift S
* Mean blur
  * Control Shift M
* Median blur
  * Control Shift D

## COLOR - adjust the image's colour.

* Brightness/Contrast
  * Shift B
* Greyscale
  * Shift G
* Saturation
  * Shift S

## ROTATION - rotate, crop or flip the image

* Rotate Left
  * Shift L
* Rotate Right
  * Shift R
* Flip vertically
  * Shift V
* Flip horizontally
  * Shift H
* Crop
  * Shift C

## EMBOSS - emboss the image from any given direction

* Top left emboss
  * Shift 1
* Mid left emboss
  * Shift 2
* Top mid emboss
  * Shift 3
* Top right emboss
  * Shift 4
* Mid right emboss
  * Shift 5
* Bottom right emboss
  * Shift 6
* Bottom middle emboss
  * Shift 7
* Bottom left emboss
  * Shift 8

## RESIZE - change the image's size

* Resize
  * Control Shift R

## DRAW - Draw lines/shapes on the image.

* Change color
  * Alt C
* Draw line
  * Alt L
* Draw rectangle
  * Alt R
* Draw oval
  * Alt O
* Fill rectangle
  * Alt Shift R
* Line Thickness
  * Alt T
* Fill oval
  * Alt Shift O
* Scribble
  * Alt S

## MACROS - start and stop recording macros.

* Start macro
  * Control R
* Stop macro
  * Control S
* Import macro
  * Control J

## TOOLBAR - Access essential items through clicking the buttons on the toolbar below the menu.

## BUGS AND FIXES

Brightness/Contrast required the user to manually input two values, which was inconvenient and not intuitive. Therefore, I (Keira) changed it to a slider, which is easier to understand and more user friendly

The ToolBar was in the Image Panel, meaning it covered the image and moved around a lot. I (Keira) fixed this by adding it directly to the JFrame so it would sit comfortably underneath the menu.


Cropping an image meant that the resulting image could not be drawn on or the median filter could not be applied. This is fixed by rotating image.    

After the addition of the new features the language actions the language actions class wouldnt function properly, this was fixed thorugh the additiion of new lines of println code that were used to diagose where the problems were
being encountered and then was fixed through teh addition of new missing resources,  Also unicode was used to generate new 


## WHO DID WHAT
In our team, we did our best to split the work equally for the second deliverables.

* Keira did the invert filter, the ToolBar, Saturation adjustment, and wrote this README.
* Raaid did mouse selection, crop selection, emboss, exception handelling, bugs, fixed javadocs and CD/CI pipeline.
* Dyrel did Draw, Keyboard Shorts, Colour and Line Thickness Choice and reformated this README. I also dabbled in some excepting handling.
* Janadhi(@janadhi14) did Macro, Emboss filter comments with help from Raaid, also carried out the CI/CD pipeline and error handeling with the pipeline. 
* Bernice Extended the filters, dealt with filters with negative results,the Sobel filters and added 2 extra features.One being the frame to automatically be the same size as the image even when you resize. The second being giving the choice to the user to increase the brightness of the image at the same time as applying a filter. 

## PROJECT MANAGEMENT
To communicate and keep track of progress, we created a Messenger groupchat and
a Trello board. This has helped us evenly distribute work and determine where
extra support is needed.

## TESTING


All filters are manually tested (by running ANDIE) at regular intervals to ensure they have not been compromised, as well as JUnit testing with CI pipelines





