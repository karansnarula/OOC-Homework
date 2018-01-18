import org.apache.commons.io.DirectoryWalker;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FileCount extends DirectoryWalker {
    int fileCount = 0;
    int directoryCount = 0;
    int uniqueExtentionsCount = 0;
    Map<String,Integer> map = new HashMap<String, Integer>();
    Set keyset = map.keySet();
    Set entryset = map.entrySet();

    protected FileCount() {
        super();
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


