package com.koreatehc.a_plus_maker.studymode;

public class TTSStudyMode extends StudyModeFactory {
    public static final int NORMAL_MAX_LEVEL = 0;

    public TTSStudyMode(String content) {
        super(content, NORMAL_MAX_LEVEL);
    }


    @Override
    public String getContent(int currentLevel) {
        return getOriginalContent();
    }
}
