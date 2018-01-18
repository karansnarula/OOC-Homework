import java.io.File;
import java.io.IOException;
import java.util.Set;

public class Main {
    public static void main (String args[]) throws IOException {
        File directory = new File("C:\\Users\\Admin\\Desktop\\OOC\\Homework1\\Question1\\docs");
//        C:\Users\Admin\Desktop\OOC\Homework1\Question1\docs
        FileCount file = new FileCount();
        file.walker(directory);
        System.out.println("Total number of files: "+file.fileCount);
        System.out.println("Total number of directory: "+file.directoryCount);
        System.out.println("Total number of unique file extensions: "+file.uniqueExtentionsCount);
        System.out.println("List of all unique file extensions: "+file.keyset);
        System.out.println("Total number of files for each extension: "+file.entryset);
        System.out.println();
        System.out.println("////////////////////////////////////////////////////////////////////////////////");
        System.out.println();
        new CommandLine(args).parse();
    }
}
