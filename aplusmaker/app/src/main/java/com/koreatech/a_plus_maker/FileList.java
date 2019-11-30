package com.koreatech.a_plus_maker;

import java.util.ArrayList;

public class FileList {
    private ArrayList<String> titleList;
    private ArrayList<String> contentList;

    public FileList() {
        titleList = new ArrayList<>();
        contentList = new ArrayList<>();
    }

    public ArrayList<String> getTitleList() {
        return titleList;
    }

    public String getTitle(int index) {
        return titleList.get(index);
    }

    public String getContent(int index) {
        return contentList.get(index);
    }

    public void addFile(String title, String content) {
        titleList.add(title);
        contentList.add(content);
    }

    public boolean isFileSaved(String title) {
        return titleList.contains(title);
    }

    public void deleteFile(String title) {
        int saveIndex = 0;

        for (int i = 0; i < titleList.size(); i++) {
            if (titleList.equals(title)) {
                saveIndex = i;
                break;
            }
        }
        titleList.remove(saveIndex);
        contentList.remove(saveIndex);
    }

    public void deleteFile(int index) {
        titleList.remove(index);
        contentList.remove(index);
    }
}

