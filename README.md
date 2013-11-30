QuartzUI
========

Java command line application to compile UI XML files into a pebble header file for initialization.

Purpose
=======

Initializing and setting basic properties for Pebble UI elements can be cumbersome and annoying, especially in C. QuartzUI offers a way for lazy Pebble developers to generate code for their UI from a more readable XML file, allowing for more readable code and easier maintainence.

Usage
=====

1. Create a 'ui.xml' in the root folder of your project. (See 'examples/ui.xml' for help.)
2. Write your ui.xml.
    Current the only tags that are availible are 'window' and 'text_layer'. You must use a window as the root element of the file, which should be pushed to the screen in your 'init()' function. All elements MUST have an 'id' attribute.
3. Include 'ui.h'.
    ```C
    #include <ui.h>
    ```
    QuartzUI will "compile" your ui.xml into 'src/ui.h', which will initialize all of the defined UI elements. All elements can be referenced by the id given to them by the 'id' attribute.
4. Call 'ui_load()' in your 'init()' function and 'ui_unload()' in your 'deinit()' function.
