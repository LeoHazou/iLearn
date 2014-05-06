iLearn
======

Capstone Project

This is my graduation Capstone project. Eventhough it is open and you are free to edit,
the content is considered controlled by Benedectine University in Lisle, Illinois.

NOTES:
-It is recommended you use an IDE like Eclipse.
-For the email function to work, you must be connected to the internet.
-The iLearn.sql in the SQL directory should be imported into your MySQL database.
-For the Study Material PDF functionality, you must go the FileUploadHandler.java servlet
 and setup the directories to point towards the \pdf directory for the project. 
 a pdf directory already exists, you do not need to create one. 
 
   *This is the physical location where the files are located on a typical Eclipse workspace
    that is located on C:\Leo\workspace\iLearn\Wencontent\pdf.
   ->private final String DIRECTORY = "\\Leo\\workspace\\iLearn\\WebContent\\pdf";
   NOTE: \\ is an escape character.

   * If everything is setup right, this line does not need to changes.
     It is used to facilitate storing the file location i nthe databse.
   -> private final String WORKING_DIRECTORY = "pdf";
