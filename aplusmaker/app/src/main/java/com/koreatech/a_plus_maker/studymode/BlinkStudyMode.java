package com.koreatech.a_plus_maker.studymode;

public class BlinkStudyMode extends StudyModeFactory {
    public static final int NORMAL_MAX_LEVEL = 0;

    public BlinkStudyMode(String content) {
        super(content, NORMAL_MAX_LEVEL);
    }


    @Override
    public String getContent(int currentLevel) {
        return getOriginalContent();
    }
}
