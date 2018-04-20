
package cloudmeclient;

    
import Cloudme.CloudmeException;
import Cloudme.CloudmeFile;
import Cloudme.CloudmeFolder;
import Cloudme.CloudmeFolderNode;
import Cloudme.CloudmeUser;
import static java.lang.System.exit;
import com.sun.xml.internal.stream.Entity;
import java.util.ArrayList;
import java.util.Scanner;


public class CloudMeClient {

    
    public static void printAllFiles(CloudmeFolderNode node) throws CloudmeException{ 
 for(CloudmeFile f : node.getFolder().getFiles()){ 
 System.out.println(f.getMetadata().getName()); 
 } 
 for(CloudmeFolderNode n : node.getChildren()){ 
 printAllFiles(n); 
 } 
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws CloudmeException {
                        Scanner sc=new Scanner(System.in);
                                  int ch;
            int a;
      
        
            do
            {
        
        try {
            
            CloudmeUser user = new CloudmeUser("gayatri96", "Gayatri@1");
            
           
           
            
            System.out.println("1.Create folder\n 2.Upload file\n 3.Create and edit file\n 4.Download file\n 5.Search file \n 6. Display files\n 7.Exit");
            
            System.out.println("Enter choice:");
            ch=sc.nextInt();     
                
            switch (ch){
                    
                    case 1:
            CloudmeFolder folder=user.getFolderManager().newFolder("/NewCloud1222");            
            user.killUser();
            break;
                    case 2:
              user.getFileManager().uploadFile("C:\\Ganesh.png", "/Documents/CloudMe/");          
                        break;
                    case 3:
                        CloudmeFile  file1=user.getFileManager().createDocument("/myfileFi.txt"); 
                        file1.setData("This is cloud assignment..".getBytes());
            file1.saveFile();
            file1.readFile();
                        break;
                        
                    case 4:
                        CloudmeFile file2=user.getFileManager().createDocument("/newFile122.txt");
                       file2.downloadFile("D:\\newfile122.txt");
                        break;
                        
                        case 5:
             ArrayList<CloudmeFile> files = user.getFileManager().FileSearch("/", "png", true); 
             files.stream().forEach((f) -> {
                 System.out.println(f.getMetadata().getName());
                 
               
             
            }); 
                        break;
                            
                        case 6:
                                CloudmeFolderNode node=user.getFolderManager().getFolderTree();
            printAllFiles(node);
            break;
                        case 7:
                            exit(0);
                                break;
                            default:
                                System.out.println("");
                                break;
            }
            
            } catch (Exception e) {
            System.out.println(e);
        }
                System.out.println("Do you want to continue?(1)");
                a=sc.nextInt();
    } while(a==1);
           
    }

}
