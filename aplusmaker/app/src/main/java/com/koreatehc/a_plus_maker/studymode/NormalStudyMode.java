package com.koreatehc.a_plus_maker.studymode;

public class NormalStudyMode extends StudyModeFactory {
    public static final int NORMAL_MAX_LEVEL = 3;

    public NormalStudyMode(String content) {
        super(content, NORMAL_MAX_LEVEL);
    }


    @Override
    public String getContent(int currentLevel) {
        String returnContent = "";
        char[] OriginalData = getOriginalContent().toCharArray();
        boolean isInBracket = false;

        switch (currentLevel) {
            case 1:
                returnContent = getOriginalContent().replace("{", "");
                returnContent = returnContent.replace("}", "");
                returnContent = returnContent.replace("[", "");
                returnContent = returnContent.replace("]", "");
                break;
            case 2:
                for(int i = 0; i < OriginalData.length; i++) {
                    if(OriginalData[i] != '{' && OriginalData[i] != '}') {
                        if (OriginalData[i] == '[') {
                            returnContent += "[";
                            isInBracket = true;
                        } else if (OriginalData[i] == ']') {
                            returnContent += "]";
                            isInBracket = false;
                        } else if (isInBracket == true) {
                            returnContent += " ";
                        } else returnContent += Character.toString(OriginalData[i]);
                    }
                }
                break;
            case 3:
                boolean isSlash = false;

                for(int i = 0; i < OriginalData.length; i++) {
                    if(OriginalData[i] != '[' && OriginalData[i] != ']') {
                        if (OriginalData[i] == '{') {
                            isInBracket = true;
                        } else if (OriginalData[i] == '}') {
                            isInBracket = false;
                            returnContent += "\n- \n\n";
                        } else if (isInBracket) {
                            returnContent += Character.toString(OriginalData[i]);
                        } else continue;
                    }
                }
                break;
        }
        return returnContent;
    }
}
