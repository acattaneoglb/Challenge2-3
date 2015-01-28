package co.mobilemakers.chooseyourownadventureononeactivity;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;


/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class AlleyFragment extends Fragment {
    int winButton, loseButton;

    Button mButtonLeft, mButtonRight, mButtonContinue;

    public AlleyFragment() {
        // Required empty public constructor
    }

    protected class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int whichButton = 2;
            if (v == mButtonLeft) {
                whichButton = 0;
            }
            else if (v == mButtonRight) {
                whichButton = 1;
            }

            boolean canWin = (whichButton == winButton);
            boolean canLose = (whichButton == loseButton);

            float random = new Random().nextFloat();

            //Intent intent;
            if (canWin && random < 0.2) {
                //intent = new Intent(getActivity(), WinningActivity.class);
                ((MainActivity)getActivity()).winGame();
            }
            else if (canLose && random > 0.2) {
                //intent = new Intent(getActivity(), LosingActivity.class);
                ((MainActivity)getActivity()).loseGame();
            }
            else if (random < 0.5) {
                //intent = new Intent(getActivity(), AlleyActivity.class);
                ((MainActivity)getActivity()).goToAlley();
            }
            else {
                //intent = new Intent(getActivity(), RoomActivity.class);
                ((MainActivity)getActivity()).goToRoom();
            }

            //startActivity(intent);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alley, container, false);

        int randomRed = new Random().nextInt(64) + 192;
        int randomGreen = new Random().nextInt(64) + 192;
        int randomBlue = new Random().nextInt(64) + 192;

        view.findViewById(R.id.background).setBackgroundColor((randomRed * 256 + randomGreen) * 256 + randomBlue
            + 0xFF000000);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        winButton = new Random().nextInt(3);
        loseButton = new Random().nextInt(3);

        prepareButtons();
    }

    private void prepareButtons() {
        ButtonListener listener = new ButtonListener();

        mButtonLeft = (Button)getActivity().findViewById(R.id.button_go_left);
        mButtonRight = (Button)getActivity().findViewById(R.id.button_go_right);
        mButtonContinue = (Button)getActivity().findViewById(R.id.button_continue);
        mButtonLeft.setOnClickListener(listener);
        mButtonRight.setOnClickListener(listener);
        mButtonContinue.setOnClickListener(listener);
    }
}
