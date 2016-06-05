package anglehack.grabngo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;
/**
 * A simple {@link Fragment} subclass.
 */
public class PendingFragment extends Fragment {


    public PendingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pending, null);
        ExpandableListView elv = (ExpandableListView) v.findViewById(R.id.expandableListView);
        elv.setAdapter(new SavedTabsListAdapter());
        return v;

    }
    public class SavedTabsListAdapter extends BaseExpandableListAdapter {



        // int i=
        private String[] groups = {"On Going 1", "On Going 2"};



        private String[][] children = {
                {"Request Date: 06/06/2016 06:13pm", "Accepted Time:05/06/2016 06:30pm", "Origin: Perak, Jln 6/11", "Destination: 2L USJ"
                        ,"Number of Item:3", "Total Price:RM50.00","Total Weight:30 Kg", "Status:Processing"},
                {"Request Date: 06/06/2016 01:13pm", "Accepted Time:04/04/2016 01:30pm", "Origin: Perak, Jln 6/11", "Destination: 2L USJ"
                        ,"Number of Item:2", "Total Price:RM60.00","Total Weight:40 Kg", "Status:Processing"},
//                {"White", "Brown"},
//                {"Golden", "Silver", "Red"},
//                {"Pink", "Gray", "Yellow"},
                //Database.getInstance().getUsers().get(1).
        };

        @Override
        public int getGroupCount() {
            return groups.length;
        }

        @Override
        public int getChildrenCount(int i) {
            return children[i].length;
        }

        @Override
        public Object getGroup(int i) {
            return groups[i];
        }

        @Override
        public Object getChild(int i, int i1) {
            return children[i][i1];
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(PendingFragment.this.getActivity());
            textView.setText(getGroup(i).toString());
            textView.setBackgroundColor(0xFF303F9F);
            textView.setHeight(250);
            textView.setTextSize(20);
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setTextColor(0xFFFFFFFF);

            return textView;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(PendingFragment.this.getActivity());
            textView.setText(getChild(i, i1).toString());
            textView.setHeight(250);
            textView.setTextSize(20);
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setPadding(100,0,0,0);
            return textView;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }

    }
}
