package com.mobo.mobosubway.view;

import com.mobo.mobosubway.service.PathService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route(value = "meetingPoint", layout = MainView.class)
@PageTitle("汇合点规划")
public class MeetingPointView  extends VerticalLayout {

    @Autowired
    private PathService pathService;

    public MeetingPointView() {
        List<TextField> stations = new ArrayList<>();
        List<TextField> distances = new ArrayList<>();

        VerticalLayout input = new VerticalLayout();

        for (int i = 0; i < 2; i++) {
            HorizontalLayout layout = new HorizontalLayout();
            TextField station = new TextField();
            stations.add(station);
            TextField distance = new TextField();
            distances.add(distance);
            layout.add(station);
            layout.add(distance);
            input.add(layout);
        }
        Button addItem = new Button("add item");
        addItem.addClickListener(click -> {
            HorizontalLayout layout = new HorizontalLayout();
            TextField station = new TextField();
            stations.add(station);
            TextField distance = new TextField();
            distances.add(distance);
            layout.add(station);
            layout.add(distance);
            input.add(layout);
        });

        add(input, addItem);
    }



}
