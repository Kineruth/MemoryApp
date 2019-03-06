package memory.Memoryapp.Holder;

import memory.Memoryapp.Object.PersonalDiary;

public class PersonalDiaryDataHolder {
    private PersonalDiary personalDiary = null;
    private static final PersonalDiaryDataHolder data = new PersonalDiaryDataHolder();

    private PersonalDiaryDataHolder(){
        personalDiary = new PersonalDiary();
    }

    public static PersonalDiaryDataHolder getPersonalDiaryDataHolder(){
        return data;
    }

    public PersonalDiary getPersonalDiary(){
        return personalDiary;
    }

    public void clearPersonalDiary(){
        personalDiary.setAll(new PersonalDiary());
    }
}
