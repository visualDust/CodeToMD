package studio.visualdust.product.codetomd.structure;

import studio.visualdust.product.codetomd.method.PathWalker;
import studio.visualdust.product.codetomd.resource.Resource;

import java.io.File;
import java.util.Vector;

public class MDStream {
    Vector<LinedFile> linedFiles = new Vector<>();
    String outputStr = null;
    String languageClass = null;

    public MDStream(String languageClass) {
        this.languageClass = languageClass;
    }

    public void addFile(LinedFile linedFile) {
        linedFiles.add(linedFile);
        outputStr = null;
    }

    public void addFile(Vector<LinedFile> linedFiles) {
        for (int i = 0; i < linedFiles.size(); i++) {
            this.linedFiles.add(linedFiles.elementAt(i));
        }
    }

    private void remix() {
        LinedFile now = null;
        outputStr = "";
        for (int i = 0; i < linedFiles.size(); i++) {
            now = linedFiles.elementAt(i);
            outputStr += "# " +
                    now.getName() + "\n" +
                    "#### FilePath = " + now.getPath() + "\n" +
                    "#### WordCount = " + now.getWordCount() + "\n" +
                    "```" + languageClass + "\n" +
                    now.toString() + "\n" +
                    "```" + "\n" + "---" + "\n" + "---" + "\n";
        }
    }

    public void output(File outputFile) {
        if (outputStr == null) remix();
//        outputStr += "Auto by " + Resource.softName + " , " + Resource.author + "\n";
        studio.visualdust.product.codetomd.method.FileWriter.WriteLineUTF8(outputFile, outputStr, true);
    }

//    public static void main(String[] args) {
//        MDStream mdStream = new MDStream("java");
//        PathWalker pathWalker = new PathWalker(new File("C:\\Users\\VisualDust\\Desktop\\IdeaProjects\\CodeToMD"),".java");
//        pathWalker.walk();
//        mdStream.addFile(pathWalker.getLinedFiles());
//        mdStream.output(new File("C:\\Users\\VisualDust\\Desktop\\test.md"));
//    }
}
