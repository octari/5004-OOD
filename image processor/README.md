# Image Processor
YinrouNi ChihaoSun


# How to use program
**The model.Model**

There is an interface called model.Model as the interface of the model. The methods in it can be divided 
into 2 parts, to generate images and to process an model. Each part will be implemented with the 
help class model.ImageGenerator and model.ImageProcessor. The model will generate the 3D array of an model
 which may be generated or processed.

**Part1 Generate Images**

Generating an model can be completed in the class model.ImageGenerator. There are static methods to 
generate kinds of images in 3D array.

It can generate checker board pattern with the given size of a single square. The generated checker 
board will be in 8X8. 

It can generate vertical or horizontal 7-colored rainbows stripes. It requires users to specify size
and vertical or horizontal stripes. The stripes will be vertical in default. Each size of the stripe
should be the same. If the rainbow model can't be divided equally, fit the size of last stripe. 

It can also generate 3 countries flags: France, Greece and Switzerland. Because the size ratio of 
the country flags is fixed, and it shouldn't be fitted. When generating a flag, the user should 
specify an integer ratio of the model size and the actual flag size.
 
 
 
**Part2 Process Images**

Processing an model can be completed in class model.ImageProcessor. It supports filtering (i.e. blur and 
sharpen) and color transformation(i.e. greyscale and sepia tone) operations. Since all the 
operations are based on the matrix in spite of different algorithm, the methods will take advantage 
of class model.HelpMatrixImpl. 

As for dither and mosaic, they can also be completed based on matrix. In the dithering, 
the color of a pixel will affect the pixels around. The algorithm can be done by a matrix. 
In mosaic, the assigment of every pixel to different seeds can form a matrix. Using the assigment
matrix can mosaic an model. Some private methods are added to support the two.

To use the processor, users need to provide the model file to be read in the controller, and pass 
the 3D array , height and width to construct an object of model.ImageProcessor. And then calling the 
corresponding method to process the model and get the result. 


**The controller.Controller**

Since the program can by both batch script and graphical user interaction. The controller can be 
divided in one for non-interaction named "controller.ScriptController" and one for interaction named 
"controller.InteractiveController".


**The controller.ScriptController**

The controller of the program take in the input file and parse then executes the commands using the 
model.

**Part1 controller.ScriptController Construction**

To construct a controller, it requires a model(i.e. the model.Model) and the input script passed as 
args[0] in main method. The commands in the input file are load, generate, process and save. 
The input file is assumed to be in the default directory (where /src is).

**Part2 Command Syntax**

In the input, all the commands should be all about load, generate, process and save. When there is 
anything else, an IllegalStateException should be thrown for that.

Command load: Starting with "load", and the path of the source model follows. It should be "load 
resource.png". When the path can't be identified, an IllegalStateException should be thrown. 
If there are still something following the path, ignore it.

Command generate: Staring with "generate", and the path of the generated model follows. It means the
 model to be generated will be in the that path. It should be "generate target.png".
 
Command generation: The generation command should be the next line of the command generate. 
It offers the type and range of the model to be generated. An model will be generated executing this 
command. Different types model requires different types of arguments. If the arguments are invalid 
for the type generation, an IllegalStateException should be thrown. If there are anything else more 
than the required arguments, ignore them.

For checkerboard generation, it should be "checkerboard (int)size".
For rainbow generation, it should be "rainbow (int)height (int)width vertical/horizontal". If the 
user does not specify it should be vertical or horizontal, it will be vertical in default.
For flag generation, it should be "flag country (int)ratio".

**And after the generation command, there is no save command needed. Since the generated model has 
already been saved in the path in the generate command. If a save command follows, 
it will save a copy of the generated model. The generated file will be the source for the following 
process in default. It have to call load / generate & generation command when the source has to be 
changed.**

Command process: The model can support process like blur, sharpen, greyscale, sepia, dither and 
mosaic. The command of process should be the same, and anything else is invalid. For mosaic,
it requires one more integer followed as the number of seeds. 

**The command process calls model to produce the processed 3D array. Before process command, there 
must be a load or generation command to offer the source file. Otherwise, nothing happeds.And it 
needs a save command to save it as an model in the given path in the save command. Otherwise, the 
processed result will disappear. If the model need to be processed successively by multiple methods,
simply call the commands successively, and save it at the end.**

Command save: Staring with "save", and the path of the source model follows. It should be 
"save dest.png". 

**After execution of save command, the dest file will the the source for the following process in 
default. It have to call load / (generate & generation) command when the source has to be changed. 
If it called before a load/generate, nothing will happen.**



When a save command follows a load/generate command, it means the one saved is the copy of
the source file.

**The controller.InteractiveController**


When constructing an controller.InteractiveController, a model and a view should be given and the view will be 
set in the construction.

The controller.InteractiveController serves as a listener to the actions the user takes in the view by 
graphical user interaction. According to the action, the controller will call the model to complete 
the functionality and the view to show result.

When the user chooses to use batch script and enters the commands in the view. The 
controller.InteractiveController will execute the commands non-interactively with the help of the 
controller.ScriptController. The syntax is the same as the one in the controller.ScriptController mentioned above.


**The view.View**

The view.View is the part of the program that interfaces with the user. It builds up a graphical user 
interface.

It will display the image that is being processed and processed on teh screen. All the features are 
exposed in the menu. And also user can choose execute the batch script instead of in an interactive
way. 




# Completion and Citation

In this program, we have completed generating images (checker board, rainbow stripes and 3 flags), 
and processing a model by blurring, sharpening, greyscale, sepia tone, dither and mosaic. We 
completed the view and controller, adding the redo and undo tho the model.

**Design Change**

In Assignment 9
We changed the model to make it more Object-Oriented. The model should work on a model in 3D array.
And reading and writing model should be done in controller instead of the model. Because the source 
and dest sre offered by the user, controller should be the one to take the input and pass to the 
model. The controller takes the input of user, controls the execution using the model.

In Assignment 10
We added an controller.InteractiveController and a view.View. As for the controller.Controller added in the last assignment, we
renamed it as controller.ScriptController. And it takes the text itself as an argument in the constructor 
rather than the file path, making it usable in the controller.InteractiveController when user choose to execute
batch script in the view. We added redo and undo functionality in the model.

The picture of a lion is cited from:
 https://paintgifts.com/products/colorful-lion-vinci-paint-by-number-kit

The picture of the manhattan is cited from the course page.

