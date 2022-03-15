package com.fantappstic.fragment_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class CourseFragment extends Fragment {
    RecyclerView recyclerView;
    View view;
    RecyAdapter recyAdapter;
    ArrayList course;
    ArrayList desc;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_course, container, false);
        course=new ArrayList();
        desc=new ArrayList();
        course.add("Department of Computer Engineering");
        course.add("Department of Information Technology Engineering");
        course.add("Department of Automobile Engineering");
        desc.add("Department of Computer Engineering was established in the year 2009, with the objective of imparting quality education in the field of Computer Engineering.\n" +
                "\n" +
                "The department started with a 4 year graduate program in Computer Engineering with the aim to develop core competence in the field and to prepare the students to carry out development work, as well as take up challenges in industries and research areas. The curriculum was designed keeping industrial and research needs in view, as well as the rapid development in the IT industry.\n" +
                "\n" +
                "The department started its 2 year post graduate program as well as Ph.D program in Computer Engineering in the year 2012 imparting a breadth of advanced knowledge in various areas of Computer Engineering.\n" +
                "\n" +
                "Department has well equipped laboratories with latest high tech hardware as well as software facilities. The department has state of the art infrastructure and computing equipment supported by high speed Ethernet and wireless networks. Faculty members are well qualified and many of them acquainted with industrial experience.");
        desc.add("Department of IT Engineering was established in the year 2009, with the objective of imparting quality education in the field of ITEngineering.\n" +
                "\n" +
                "The department started with a 4 year graduate program in IT Engineering with the aim to develop core competence in the field and to prepare the students to carry out development work, as well as take up challenges in industries and research areas. The curriculum was designed keeping industrial and research needs in view, as well as the rapid development in the IT industry.\n" +
                "\n" +
                "Department has well equipped laboratories with latest high tech hardware as well as software facilities. The department has state of the art infrastructure and computing equipment supported by high speed Ethernet and wireless networks. Faculty members are well qualified and many of them acquainted with industrial experience.");
        desc.add("Department of Mechanical Engineering was established in 2010 to accomplish the necessity of competent Mechanical engineers in the current phase of infrastructure development in the state as well as nation. To realize, the curriculum has been designed with close interaction of faculty members with the experts from the industries.\n" +
                "\n" +
                "To have hands-on experience, laboratories are developed indigenously. The department owns laboratories in the area of Fluid engineering, Thermal engineering, Mechanical Measurement, Manufacturing engineering, Automobile engines, Automobile systems etc.\n" +
                "\n" +
                "In favor of the overall development of students, the department follows holistic approach that incorporates career counseling, industrial tour and professional society related exercises along with academic activities. Faculty members are well qualified and acquainted with industrial practices. Industrial affiliations are established by signing MoUs (Memorandum of Understanding) with some industries in the areas like fabrication, process, automobile and manufacturing.\n" +
                "\n" +
                "The department aims to be comprehensive of all segments of the society by providing educational courses of ITI, B. Tech., M. Tech. and Ph. D.");
        recyclerView=view.findViewById(R.id.myrecy);
        recyAdapter=new RecyAdapter(course,desc,getActivity().getSupportFragmentManager());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyAdapter);
        return view;
    }
}