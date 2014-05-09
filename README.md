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
     It is used to facilitate storing the file location in the databse.
     
   -> private final String WORKING_DIRECTORY = "pdf";
   
   - You must have Apache Tomcat 6 or 7 running within your eclipse.
   
   -Changes to DBConnector.java to reflect your Mysql instance
        this.database = "iLearn";
        this.username = "root";
        this.password = "root";
        
        this.username is the username you use to access your instance
        
        this.password is the password you use to access your instance
        Defaults are usually root and root, but you may choose your own.
        
        
      If your Mysql service is running off localhost:3306 then no other changes are required.
      
      otherwise change the following line:
      
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
					+ database + "?user=" + username + "&password=" + password);
					
	 and replace localhost:3306 with your mysql server address and port in the form of:
	 address:port. 
	 
	 
	 - Make sure you have the JDBC connector and other libraries used within the application
	   in the Tomcat\lib directory; otherwise you will see Class.forname exceptions.
		
		
   
   
