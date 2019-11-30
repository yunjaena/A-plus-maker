package com.koreatehc.a_plus_maker.studymode;

import java.util.concurrent.ThreadLocalRandom;

public class RandomStudyMode extends StudyModeFactory {
    public static final int NORMAL_MAX_LEVEL = 3;
    public static final int MAX_SECOND_RANDOM_PERCENTAGE = 40;
    public static final int MAX_THIRD_RANDOM_PERCENTAGE = 60;

    public RandomStudyMode(String content) {
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
                for (int i = 0; i < OriginalData.length; i++) {
                    if (OriginalData[i] != '{' && OriginalData[i] != '}') {
                        if (OriginalData[i] == '[') {
                            returnContent += "[";
                            if (ThreadLocalRandom.current().nextInt(100) <= MAX_SECOND_RANDOM_PERCENTAGE)
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
                for (int i = 0; i < OriginalData.length; i++) {
                    if (OriginalData[i] != '{' && OriginalData[i] != '}') {
                        if (OriginalData[i] == '[') {
                            returnContent += "[";
                            if (ThreadLocalRandom.current().nextInt(100) <= MAX_THIRD_RANDOM_PERCENTAGE)
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
        }
        return returnContent;
    }
}
