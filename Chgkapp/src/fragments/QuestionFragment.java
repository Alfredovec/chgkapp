package fragments;

/**
 * Created by Sergey on 15.01.2015.
 */

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import models.entities.Question;
import ru.chgkapp.R;

/**
 * A fragment representing the front of the card.
 */
public class QuestionFragment extends Fragment {

    private Question question;

    public QuestionFragment()
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        question = (Question) getArguments().getSerializable("question");
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.question, null);
        TextView twQuestion = (TextView) view.findViewById(R.id.textViewQuestion);
        TextView materialTitle = (TextView) view.findViewById(R.id.materialTitle);
        TextView materialText = (TextView) view.findViewById(R.id.materialText);
        ImageView materialImage = (ImageView) view.findViewById(R.id.materialImage);

        twQuestion.setText(question.getQuestion());
        materialImage.setVisibility(View.GONE);
        materialText.setVisibility(View.GONE);
        materialTitle.setVisibility(View.GONE);

//        if(question.getPictureQuestion() != null)
        if (question.getPictureQuestion() != null)
        {
            materialTitle.setVisibility(View.VISIBLE);
            materialImage.setVisibility(View.VISIBLE);

            WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int height = size.y;

            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inDither = true;
            opt.inPreferredConfig = Bitmap.Config.ARGB_8888;
            //byte[] imageByteArray = question.getPictureQuestion();
            byte[] imageByteArray = question.getPictureQuestion();
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length, opt);
            materialImage.setImageBitmap(bitmap);

            int hei = bitmap.getHeight();
            int wid = bitmap.getWidth();

            float den = getResources().getDisplayMetrics().density;
            float heiPx = hei * 150 * den / 72;
            float widPx = wid * 150 * den / 72;

            int heightPx = (int) heiPx;
            int widthPx = (int) widPx;

            materialImage.getLayoutParams().height = (int) heiPx;
            if (widthPx > width)
            {
                materialImage.getLayoutParams().width = width;
                float scale = (float) widthPx / (float) width;
                materialImage.getLayoutParams().height = (int) (heightPx / scale);
            }
            else
            {
                materialImage.getLayoutParams().width = widthPx;
                materialImage.getLayoutParams().height = heightPx;
            }
        }

        if (question.getMaterial() != null && question.getMaterial().length() > 0)
        {
            materialTitle.setVisibility(View.VISIBLE);
            materialText.setVisibility(View.VISIBLE);

            materialText.setText(question.getMaterial());
        }

        return view;
    }
}