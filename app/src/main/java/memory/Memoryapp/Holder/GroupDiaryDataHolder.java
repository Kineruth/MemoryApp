package memory.Memoryapp.Holder;

import memory.Memoryapp.Object.GroupDiary;

public class GroupDiaryDataHolder {

    private GroupDiary groupDiary = null;
    private static final GroupDiaryDataHolder data = new GroupDiaryDataHolder();

    private GroupDiaryDataHolder(){
        groupDiary = new GroupDiary();
    }

    public static GroupDiaryDataHolder getGroupDataHolder(){
        return data;
    }

    public GroupDiary getGroupDiary(){
        return groupDiary;
    }

    public void clearGroupDiary(){
        groupDiary.setAll(new GroupDiary());
    }
}
