# Custom Functions TIBCO BusinessWorks 5
<p>
<img src =https://img.shields.io/static/v1?label=licence&message=MIT&color=green />
<img src =https://img.shields.io/static/v1?label=status&message=Under%20Contruction&color=yellow />
</p>
  
Custom functions are user-defined functions that allow you to extend the functionality of TIBCO BusinessWorks. These functions can be created in Java and can be used in any BusinessWorks process to perform a specific task or to manipulate data.</p>

Here are some key characteristics of TIBCO BusinessWorks custom functions:

- Reusability: Custom functions can be reused across multiple projects, reducing the need to create the same functionality multiple times.
- Extensibility: Custom functions can be extended to meet specific business requirements by adding new functionality.
- Configurability: Custom functions can be configured to accept input parameters and to produce output parameters that can be used in other parts of the process.
- Scalability: Custom functions can be used in high-volume environments to handle large amounts of data.
- Maintenance: Custom functions can be maintained independently of the main BusinessWorks application, making it easier to fix bugs or add new features.

Overall, TIBCO BusinessWorks custom functions provide a flexible and powerful way to extend the capabilities of the platform and to create custom integration solutions that meet specific business needs.


### Topics

- [Functions](#functions)
  - [Custom Date Time Functions](#custom-date-time-functions)
  - [Custom String Functions](#custom-string-functions)
- [Install](#install)
- [Uninstall](#uninstall)
- [MIT Licence](#mit-licence)

## Functions
This section provides an overview of the available custom functions and explains how to use them in your integration projects. 

We will present the purpose and functionality of each custom function, and provide examples of how they can be used in a BusinessWorks process.

By the end of this section, you should have a clear understanding of the available custom functions and how to use them effectively to meet your integration needs.

- ### Custom Date Time Functions
  Custom-built functions for manipulating dates and times.
  
  - #### extractDayOfYear() 
    **Description:** Retrieves the day number within a year for a given date.  
    **Template:** extractDayOfYear(<< date >>)  
    **Return Type:** integer  
    
      ##### Example:

        Input: ns:extractDayOfYear("2023-12-31")
        Return: 365

  - #### extractWeekOfYear() 
    **Description:** Retrieves the week number within a year for a given date.  
    **Template:** extractWeekOfYear(<< date >>)  
    **Return Type:** integer  
    
      ##### Example:

        Input: dt:extractWeekOfYear("2023-12-31")
        Return: 53

  - #### epochToHumanReadable()
  
    **Description:** Convert from Epoch to Human Readable Date.  
    **Template:** epochToHumanReadable(<< timestamp >>)   
    **Return Type:** string  
    
    ##### Example:  
        
        Input: ns:epochToHumanReadable("1679332277")
        Return: 2023-03-20T14:11:17.017-03:0

  - #### humanReadableToEpoch() 
    **Description:** Convert a readable date format to an Epoch timestamp.  
    **Template:** humanReadableToEpoch(<< datetime >>)  
    **Return Type:** string  
    
      ##### Examples:

        Input: ns:humanReadableToEpoch("2023-03-20T14:11:17.017-03:00")  
        Return: 1679332277  

- ### Custom String Functions
  Custom-built functions for manipulating strings.
    
    - #### countLines() 
      **Description:** Obtain the line count for a given string.  
      **Template:** countLines(<< string-text >>)  
      **Return Type:** integer  
    
      ##### Example:

          Input: ns:countLines("first line. 
                                second line.")
          Return: 2

    - #### countWords() 
      **Description:** Obtain the words count for a given string.  
      **Template:** countWords(<< string-text >>)    
      **Return Type:** integer  
    
      ##### Example:

          Input: ns:countWords("word1 word2 word3 word4")
          Return: 4
   
   - #### maskFormatter() 
      **Description:** Takes a string and formats it based on a given mask.  
      **Template:** maskFormatter(<< string-text >>, << mask >>)  
      **Return Type:** string  
    
      ##### Example:

          Input: ns:maskFormatter("999999999", "+55 11 #####-####")
          Return: +55 11 99999-9999

    - #### normalizeText() 
      **Description:** Cleans a string by removing all non-alphanumeric characters and keeping only letters and numbers.  
      **Template:** normalizeText(<< string-text >>)  
      **Return Type:** string  
    
      ##### Example:

          Input: ns:normalizeText("a|b-c*d%e$f&g")
          Return: abcdefg

    - #### removeAccents() 
      **Description:** Convert accented characters to non-accented characters in a string.  
      **Template:** removeAccents(<< string-text >>)  
      **Return Type:** string  
    
      ##### Example:

          Input: ns:removeAccents("áàâãç")
          Return: aaac

    - #### replace() 
      **Description:**  Returns a string produced from the input string by replacing any substrings that match a given regular expression with a supplied replacement string.  
      **Template:** replace(<< input >>, << pattern >>, << replacement >>)      
      **Return Type:** string  
    
      ##### Example:

          Input: ns:replace("abc 456", "456", "def")
          Return: abc def
          
      ##### Advanced Example:

          Input: ns:replace("useraccount@gmail.com", "(?<=.)[^@\n](?=[^@\n]*?[^@\n]@)|(?:(?<=@.)|(?!^)\G(?=[^@\n]*$))[^.](?!\.)", "*")
          Return: u*********t@g***l.com


    - #### reverseString() 
      **Description:** Takes a string as an input and returns a new string with the characters in the original string reversed.  
      **Template:** reverseString(<< string-text >>)    
      **Return Type:** string  
    
      ##### Example:

          Input: ns:reverseString("all you need is love")
          Return: evol si deen uoy lla

    - #### stringJoin() 
      **Description:**  Returns a string constructed by concatenating all the strings in a sequence (each values separated by space), using the second argument as a separator.  
      **Template:** stringJoin(<< values >>, << separator >>)      
      **Return Type:** string  
    
      ##### Example:

          Input: ns:stringJoin("value1 value2 value3", " | ")
          Return: value1 | value2 | value3

## Install

  🚧 Under Construction
  
## Uninstall

  🚧 Under Construction

## MIT Licence

Copyright (c) 2023 jgimenes

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), 
to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
