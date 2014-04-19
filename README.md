ChomSky
=======

###Introduction

####What is it?

ChomSky -- A text processing system.

####How to pronounce?

The name of this project is inspired by Noam Chomsky. But the correct pronunciation  should be Chom--Sky.

####What for?

This project is primarily for the assignment of WAES2108 Natural Language Processing and Generation.

####Is it COOL?

Of course it is.

###Updates

+ GUI updates [20140415]
+ File Input [20140416]
+ Text Formatting [20140416]
+ File Output [20140416]
+ Special treatment on numbers and etc [20140416]
+ First complete version updated [20140419]

###To-do List

+ Consider fine-tuning rules
+ Consider special cases
+ Refine data structure
+ clean code and add comment

###Notes

1. This repo is a Eclipse project developed under MacOS X, Eclipse Kepler and JavaSE-1.6, you can download the whole project and directly import to your Eclipse, shouldn't occur any problems. If it is, check your OS, Java installation and Eclipse release.

2. The code is explained itself, so yes, READ THE DAMN CODE.

3. In current version, raw data processing is not elegant at all (but good enough to work in most cases). In future, this part should be changed completely to support more language phenomenon (e.x. Math equations, Unicode characters) [We consider ASC II characters now]

4. This project use JWNL library for identify the base form of the word. Problem is occurred on initialisation. Several configurations are needed, all on the path of the files. One of them is in `jwnl14-rc2/config/file_properties.xml`, open this file, change the last line to your WordNet-3.0 dictionary path on your machine.

5. Some exceptions are needed. For example, JWNL is too strong on looking for base form, so it will change every letter in lower case. Such as "I"-->"i", "He"-->"he", we need some exception on it. Numbers are always a pain. Be careful. The word list I provided currently is not a complete version, please consider more.

6. `lookupBase` method in `ChomskyText.java` is not a good implementation. It will reinitialise JWNL library every time it performed. So find a way to put it in the constructor or other places (I tried, but failed).

###Contacts

Hu Yuhuang

_No. 42, North, Flatland_

Email: duguyue100@gmail.com