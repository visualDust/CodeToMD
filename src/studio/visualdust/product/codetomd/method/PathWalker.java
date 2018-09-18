package studio.visualdust.product.codetomd.method;

import studio.visualdust.product.codetomd.structure.LinedFile;

import java.io.File;
import java.util.Vector;

public class PathWalker {
    Vector<LinedFile> linedFiles = new Vector<>();
    File dictionary;
    String type;

    public PathWalker(File dictionary, String type) {
        this.dictionary = dictionary;
        this.type = type;
    }

    public void walk(File file) {
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory())
                walk(files[i]);
            else if (files[i].getName().endsWith(type))
                linedFiles.add(new LinedFile(files[i]));
        }
    }

    public void walk() {
        walk(dictionary);
    }

    public Vector<LinedFile> getLinedFiles() {
        if (linedFiles.size() == 0) walk(dictionary);
        return linedFiles;
    }
}
