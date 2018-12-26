package memory.Memoryapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import memory.Memoryapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MemoryFragment extends Fragment {

    /**
     * Default Constructor
     * Required empty public constructor
     */
    public MemoryFragment() { }

    /**
     * Inflates the layout for this fragment.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return this fragment's view.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_memory, container, false);
    }

}
