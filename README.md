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

####What does it look like?

![ChomSky](/resource/ChomSky.png)

###Updates

+ GUI updates [20140415]
+ File Input [20140416]
+ Text Formatting [20140416]
+ File Output [20140416]
+ Special treatment on numbers and etc [20140416]
+ First complete version updated [20140419]
+ First refined version [20140420]

###To-do List

+ Consider fine-tuning rules
+ Consider special cases
+ Refine data structure

###Usage

The project is exported as a runnable JAR file, you can either use GUI utilities or Command Line utilities.

1. Use GUI: open JAR file directly or type following command in terminal

   ```
   java -jar chomsky.jar
   ```

2. Use Command Line: type following command in terminal

   ```
   java -jar chomsky.jar -f <input file path> <output file path>
   ```

3. Help

   ```
   java -jar chomsky.jar [-h|-help|--help]
   ```

4. Check version

   ```
   java -jar chomsky.jar --version
   ```

###Levels of Processing

####Preprocessing

1. Identify numbers, special words contains '.', ',', '-', '''. Replace them to special characters. (`processRawText`)

2. Remove punctuation, current consideration:
   ```
   '_' (empty space), ',', '.', '!', '?', '()',
   '[]', '<>', ';', '`'', '"', '-', '\n'
   ```
   (`processRawText`)

3. Recover the special numbers format.

####Processing

1. First level: recover be-verb

2. Second level: remove the verb be

   'be playing' to 'play'

   'be written' to 'be written'

3. Third level: remove useless word, current consideration

   ```
   a, an, the, in, on, of, at, for,
   to, into, this, that
   ```

4. Fourth level: recover verb

   'wrote' to 'write'

   'played' to 'play'

5. Fifth level: recover noun

   'apples' to 'apple'

   'boxes' to 'box'

###Notes

1. This repo is a Eclipse project developed under MacOS X, Eclipse Kepler and JavaSE-1.6, you can download the whole project and directly import to your Eclipse, shouldn't occur any problems. If it is, check your OS, Java installation and Eclipse release.

2. The code is explained itself, so yes, READ THE DAMN CODE.

3. In current version, raw data processing is not elegant at all (but good enough to work in most cases). In future, this part should be changed completely to support more language phenomenon (e.x. Math equations, Unicode characters) [We consider ASC II characters now]

4. This project use JWNL library for identify the base form of the word. Problem is occurred on initialisation. Several configurations are needed, all on the path of the files. One of them is in `jwnl14-rc2/config/file_properties.xml`, open this file, change the last line to your WordNet-3.0 dictionary path on your machine.

5. Some exceptions are needed. For example, JWNL is too strong on looking for base form, so it will change every letter in lower case. Such as "I"-->"i", "He"-->"he", we need some exception on it. Numbers are always a pain. Be careful. The word list I provided currently is not a complete version, please consider more.

6. `lookupBase` method in `ChomskyText.java` is not a good implementation. It will reinitialise JWNL library every time it performed. So find a way to put it in the constructor or other places (I tried, but failed).

7. Some known special cases we can process:
  + word with bar: MH-370
  + ordinary numbers: 1,990; 3.14; 2,450.12
  + license: ECK-2120-1241-12415
  + some URLs

8. Some exceptions we known so far:
  + things: things can refer to person's belongs, so the system did not change it.

###Contacts

Hu Yuhuang

_No. 42, North, Flatland_

Email: duguyue100@gmail.com