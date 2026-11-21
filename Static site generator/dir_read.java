import java.util.*;
import java.io.File;
class dir_read
{
    private String sourcedir;
    private ArrayList <String> markdown;

    public dir_read(String source)
    {
        sourcedir=source;
        markdown= new ArrayList<>();
    }
    void traversedir(File dir)
    {
        File[] contents= dir.listFiles();
        if(contents==null)
        {
            System.out.println("No files inside directory");
            return;
        }
        for(File f:contents)
        {
            if(f.isDirectory())
                traversedir(f);
            else if(f.getName().toLowerCase().endsWith(".md"))
                markdown.add(f.getAbsolutePath());
        }
    }

     ArrayList<String> getdir()
    {
        File dir= new File(sourcedir);
        if(!dir.exists() || !dir.isDirectory())
        {
            throw new IllegalArgumentException("Invalid Directory");

        }
        traversedir(dir);
        return markdown;
    }
}