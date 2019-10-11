package com.koreatehc.a_plus_maker.studymode;

public class NormalStudyMode extends StudyModeFactory {
    public static final int NORMAL_MAX_LEVEL = 3;

    public NormalStudyMode(String content) {
        super(content, NORMAL_MAX_LEVEL);
    }


    @Override
    public String getContent(int currentLevel) {
        String returnConetent = "";
        switch (currentLevel) {
            case 1:
                returnConetent = getOriginalContent();
                break;
            case 2:
                returnConetent = getOriginalContent().replace("a","_");
                break;
            case 3:
                returnConetent = getOriginalContent().replace("a","_").replace("e","_");
                break;
        }
        return returnConetent;
    }
}
