package hall.androidcalendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

public class WeekActivity extends Fragment {

    LocalDate currentDat;
    TextView weekNum;
    TextView yearNum;
    // TextView mon, tue, wed, thu, fri, sat, sun;
    LinearLayout monmon, tuetue, wedwed, thuthu, frifri, satsat, sunsun;
    ArrayList<LinearLayout> layouts;
    ArrayList<String> days;
    ArrayList<TextView> alltexts;
    Button forward, backward;
    int count = 0;
    // Calendar calendar;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        setContentView(getContentViewId());
//        navigationView = (BottomNavigationView) view.findViewById(R.id.bottom_navigation);
//        navigationView.setOnNavigationItemSelectedListener(this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.activity_week, container, false);
        View view = inflater.inflate(R.layout.activity_week, container, false);
        backward = view.findViewById(R.id.backward);
        forward = view.findViewById(R.id.forward);
        yearNum = view.findViewById(R.id.whydoesthisnotwork);
        weekNum = view.findViewById(R.id.num);
        alltexts = new ArrayList<>();
        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 7; i++)
                {
                    if (layouts.get(i).getChildCount() > 0) {
                        for (int j = 1; j < layouts.get(i).getChildCount(); j++) {
                            layouts.get(i).getChildAt(j).setVisibility(View.GONE);
                        }
                    }
                }
                currentDat = currentDat.minusWeeks(1);
                yearNum.setText(String.valueOf(currentDat.getYear()));
                int weekN = currentDat.get(WeekFields.of(Locale.CANADA).weekOfWeekBasedYear());
                weekNum.setText(String.valueOf(weekN));
                for (int i = 1; i < 8; i++)
                {
                    ArrayList<TextView> temp = intenseAlgorithms(currentDat, i);
                    for (int j = 0; j < temp.size(); j++) {
                        alltexts.add(temp.get(j));
                        temp.get(j).setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        layouts.get(i).addView(temp.get(j));
                    }
                }
            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 7; i++)
                {
                    if (layouts.get(i).getChildCount() > 0) {
                        for (int j = 1; j < layouts.get(i).getChildCount(); j++) {
                            layouts.get(i).getChildAt(j).setVisibility(View.GONE);
                        }
                    }
                }
                currentDat = currentDat.plusWeeks(1);
                yearNum.setText(String.valueOf(currentDat.getYear()));
                int weekN = currentDat.get(WeekFields.of(Locale.CANADA).weekOfWeekBasedYear());
                weekNum.setText(String.valueOf(weekN));
                for (int i = 1; i < 8; i++)
                {
                    ArrayList<TextView> temp = intenseAlgorithms(currentDat, i);
                    for (int j = 0; j < temp.size(); j++) {
                        alltexts.add(temp.get(j));
                        temp.get(j).setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        layouts.get(i).addView(temp.get(j));
                    }
                }
            }

        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        currentDat = LocalDate.now();
        days = new ArrayList<>();
        // alltexts = new ArrayList<>();
        days.add("Sunday");
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");

        /*ArrayList<String> days = new ArrayList<>();
        days.add(seven days)
         */

        /*mon = view.findViewById(R.id.mon);
        tue = view.findViewById(R.id.tue);
        wed = view.findViewById(R.id.wed);
        thu = view.findViewById(R.id.thu);
        fri = view.findViewById(R.id.fri);
        sat = view.findViewById(R.id.sat);
        sun = view.findViewById(R.id.sun);*/
        monmon = view.findViewById(R.id.monmon);
        tuetue = view.findViewById(R.id.tuetue);
        wedwed = view.findViewById(R.id.wedwed);
        thuthu = view.findViewById(R.id.thuthu);
        frifri = view.findViewById(R.id.frifri);
        satsat = view.findViewById(R.id.satsat);
        sunsun = view.findViewById(R.id.sunsun);
        layouts = new ArrayList<>();
        layouts.add(sunsun);
        layouts.add(monmon);
        layouts.add(tuetue);
        layouts.add(wedwed);
        layouts.add(thuthu);
        layouts.add(frifri);
        layouts.add(satsat);
        /*for (int i = 0; i < layouts.size(); i++)
        {
            layouts.get(i).removeAllViews();
            TextView t = new TextView(getActivity());
            t.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            t.setText(days.get(i));
            t.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            t.setTypeface(null, Typeface.BOLD);
            layouts.get(i).addView(t);
        }*/
        /*for (int i = 1; i < 8; i++)
        {
            ArrayList<TextView> temp = intenseAlgorithms(currentDat, i);
            for (int j = 0; j < temp.size(); j++) {
                alltexts.add(temp.get(j));
                temp.get(j).setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                layouts.get(i).addView(temp.get(j));
            }
        }*/


        /*RecyclerView recyclerView = view.findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerViewAdapter23 adapter = new RecyclerViewAdapter23(days);
        recyclerView.setAdapter(adapter);*/

    }

    private ArrayList<TextView> intenseAlgorithms(LocalDate ld, int i) {
        ArrayList<TextView> temp = new ArrayList<>();
        LocalDate lld = ld.with(WeekFields.of(Locale.CANADA).dayOfWeek(), i);
        ArrayList<Event> events;
        events = CalendarActivity.currentCalendar.search("any", lld.atStartOfDay());
        for (Event e: events) {
            TextView t;
            t = new TextView(getActivity());
            t.setText(e.getName());
            t.setClickable(true);
            //t.setOnClickListener(getActivity());
            int pos = (int) (Math.random() * ((200001))) + 100001;
            t.setId(pos);
            temp.add(t);
        }
        return temp;
    }


    //@Override
    /*public void onClick(View v) {
        //int num = Integer.parseInt(String.valueOf(weekNum));
        if (v.getId() == R.id.backward)
        {
            currentDat.minusWeeks(1);
        }
        else if (v.getId() == R.id.forward)
        {
            //Log.d("asdasd", "asdasd");
            currentDat.plusWeeks(1);
            System.out.println(0);
        }
        yearNum.setText(currentDat.getYear());
        int weekN = currentDat.get(WeekFields.of(Locale.CANADA).weekOfWeekBasedYear());
        weekNum.setText(weekN);

        /*for (int k = 0; k < alltexts.size(); k++)
        {
            if (v.getId() == alltexts.get(k).getId())
            {
                //Intent intent = new Intent(this, ***.class);
                //startActivity(intent);
            }
        }*/
    //}


//    @Override
//    int getContentViewId() {
//        return R.layout.activity_week;
//    }
//
//    @Override
//    int getNavigationMenuItemId() {
//        return R.id.action_week;
//    }

}
