package studio.visualdust.product.codetomd.resource;

import java.util.Vector;

public class Resource {
    public static String softName = "CodeToMD";
    public static String version = "2018.9";
    public static String author = "Studio.VisualDust";

    public static Vector<String> knownCodeType = new Vector<>();
    public static Vector<String> knownFileType = new Vector<>();

    public Resource(){
        knownFileType.add(".cpp");
        knownCodeType.add("cpp");

        knownFileType.add(".java");
        knownCodeType.add("java");

        knownFileType.add(".html");
        knownCodeType.add("html");

        knownFileType.add(".py");
        knownCodeType.add("python");

        knownFileType.add(".c");
        knownCodeType.add("c");

        knownFileType.add(".kt");
        knownCodeType.add("kotlin");

        knownFileType.add(".php");
        knownCodeType.add("php");

        knownFileType.add(".iml");
        knownCodeType.add(".html");

        knownFileType.add(".md");
        knownCodeType.add("markdown");

        knownFileType.add(".markdown");
        knownCodeType.add("markdown");

        knownFileType.add(".cs");
        knownCodeType.add("csharp");

        knownFileType.add(".groovy");
        knownCodeType.add("groovy");

        knownFileType.add(".pas");
        knownCodeType.add("pascal");

        knownFileType.add(".go");
        knownCodeType.add("go");

        knownFileType.add(".js");
        knownCodeType.add("js");

        knownFileType.add(".bat");
        knownCodeType.add("batch");

        knownFileType.add(".sh");
        knownCodeType.add("bash");

        knownFileType.add(".ps");
        knownCodeType.add("powershell");
    }
}
