package memory.Memoryapp.Object;

public class PersonalDiary extends Diary {


    public PersonalDiary() {
        super();
    }

    public PersonalDiary(String name, String image, String uid) {
       super(name,image,uid);
    }

    public void setAll(PersonalDiary p){
       setAll((Diary)p);
    }
}
