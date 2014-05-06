
//Includes added libraries from apache commons
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import edu.ben.cmsc398.iLearn.Model.PackageBuilder;

/**
 * Servlet to handle File upload requests
 * Due to Security Restrictions, paths must be hardcoded since there is no actualy deployment of the application.
 * @author leo
 **/
@WebServlet("/upload")
public class FileUploadHandler extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	//Default directory
	private final String DIRECTORY = "\\Leo\\workspace\\iLearn\\WebContent\\pdf";
	private final String WORKING_DIRECTORY = "pdf";
	//The path to the study material
	private String path="";
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		String message = "Invalid action! Please try uploading a file first.";
		// Redirect The addpdf page.
		try {
			response.sendRedirect("addpdf.jsp?message=" + message);
		} catch (IOException e) {

		}
	}
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// Holds any message to be forwarded
    	String message ="";
    	
    	//Flags to checks if the file is in the correct format
    	// and to check if the filename already exists.
    	boolean isPDF = false;
    	boolean doesExist = true;

  	
    	//check if directory exists, otherwise create it
    	File dir = new File(DIRECTORY);
    	if (!dir.exists())
    	{
    		dir.mkdir();
    	}
    	
 
      
        //Process multipart content as directed by apache common documentation
        if(ServletFileUpload.isMultipartContent(request))
        {
        	//Parse out the file name from the request
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(request);
              
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        
                        // Check if the file has .pdf extension
                        if ( name.toLowerCase().endsWith(".pdf"))
                        {
                        	isPDF = true;                      	
                        	path = WORKING_DIRECTORY + File.separator + name;
                        	File file = new File(DIRECTORY + File.separator + name);
                        	
                        	// If the file does not exist then write and set appropriate flags.
                        	if (!file.exists())
                        	{
                        		doesExist = false;
                        	item.write(file);
                        	}
                        	
                        }   
                    }
                }
           
               //File uploaded successfully
               // Redirect to publishing page with appropriate message.
                if ( isPDF && !doesExist)
                {
                	// Get package from session, set the path and send back to session
                	PackageBuilder pkg = (PackageBuilder) request.getSession().getAttribute("pkg");
                	pkg.setStudyMaterialLink(path);
                	pkg.setStudyMaterial(true);
                	request.getSession().setAttribute("pkg", pkg);
                	message = "File uploaded";  
                	request.setAttribute("message", message);
                	response.sendRedirect("publish.jsp?message=" + message);
                }
                
                // If the files already exist.
                else if ( doesExist)
                {
                	message = "File already exists! Please rename the file or choose a differene one.";
                	response.sendRedirect("addpdf.jsp?message=" + message);
                }
                else
                {
                	message = "File must be in .pdf format! Please try again.";
                	response.sendRedirect("addpdf.jsp?message=" + message);
                }
                
   			   }
            
            // File not uploaded
            // Redirect back to addpdf.jsp with the appropriate error message.
            catch (Exception ex) 
            {
            	message = " File upload error! Please try again!";
               request.setAttribute("message", message);
               RequestDispatcher rd = request.getRequestDispatcher("/addpdf.jsp");
   			   rd.forward(request, response);
            }          
         
        }
        // If the request was not a multipart request
        // Redirect back to addpdf.jsp with appropriate error message.
        else
        {
        	message = "File can't be uploaded! Please try another file.";
            request.setAttribute("message", message);
            RequestDispatcher rd = request.getRequestDispatcher("/addpdf.jsp");
		   rd.forward(request, response);
        }
    
    	
    	
    }
  
}


