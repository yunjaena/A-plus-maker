package com.koreatehc.a_plus_maker.studymode;

public abstract class StudyModeFactory {
    private int studyLevel;
    private String content;

    public StudyModeFactory(String content, int maxLevel) {
        this.content = content;
        this.studyLevel = maxLevel;
    }

    public abstract String getContent(int currentLevel);

    public int getStudyLevel() {
        return studyLevel;
    }

    public String getOriginalContent() {
        return content;
    }
}
