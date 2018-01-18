import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.DirectoryWalker;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CommandLine extends DirectoryWalker {

    CommandLineParser parser = new BasicParser();
    String[] args = null;
    Options options = new Options();
    int fileCount = 0;
    int directoryCount = 0;
    int uniqueExtentionsCount = 0;
    Map<String,Integer> map = new HashMap<String, Integer>();
    Set keyset = map.keySet();

    public CommandLine(String[] args){
        this.args = args;
    }

    public void parse() {
        options.addOption("a", "total-num-files", false, "The total number of files");
        options.addOption("b", "total-num-dirs", false, "Total number of directory");
        options.addOption("c", "total-unique-exts", false, "Total number of unique file extensions");
        options.addOption("d", "list-exts", false, "List all unique file extensions. Do not list duplicates");
        options.addOption("e","num-ext=",true,"List total number of file for specified extension EXT");
        options.addOption("f","path-to-folder",true,"Path to the documentation folder. This is a required argument");
        org.apache.commons.cli.CommandLine cmd;
        try {
            cmd = parser.parse(options, this.args);
            if (cmd.hasOption("f")){
                walker(new File(cmd.getOptionValue("f")));
            }
            if (cmd.hasOption("a")){
                System.out.println("Total number of files: "+fileCount);
            }
            if (cmd.hasOption("b")){
                System.out.println("Total number of directory: "+directoryCount);
            }
            if (cmd.hasOption("c")){
                System.out.println("Total number of unique file extensions: "+uniqueExtentionsCount);
            }
            if (cmd.hasOption("d")){
                System.out.println("List all unique file extensions. Do not list duplicates: "+keyset);
            }
            if (cmd.hasOption("e")){
                System.out.println("Total number of files for ."+ cmd.getOptionValue("e") +": "+map.get(cmd.getOptionValue("e")));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public List walker(File startDirectory) {
        List results = new ArrayList();
        try {
            walk(startDirectory, results);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    protected boolean handleDirectory(File directory, int depth, Collection results) throws IOException {
        directoryCount++;
        return true;
    }

    @Override
    protected void handleFile(File file, int depth, Collection results) throws IOException {
        fileCount++;
        String extension = FilenameUtils.getExtension(file.getName());
        if(map.containsKey(extension) == true){
            map.put(extension, map.get(extension) + 1);
        }else{
            uniqueExtentionsCount++;
            map.put(extension, 1);
        }
    }
}
