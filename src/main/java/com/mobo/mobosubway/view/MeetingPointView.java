package com.mobo.mobosubway.view;

import com.mobo.mobosubway.service.PathService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        HorizontalLayout result = new HorizontalLayout();
        Text meetingPoints = new Text("");
        result.add(new H3("结果为: "), meetingPoints);

        Button cal = new Button("计算");
        cal.addClickListener(click -> {
            List<String> statoinList = stations.stream().map(TextField::getValue).collect(Collectors.toList());
            List<Double> distanceList = distances.stream().map(TextField::getValue).map(Double::parseDouble).collect(Collectors.toList());
            List<String> calresult = pathService.getAvailableMeetingPoint(statoinList, distanceList);
            meetingPoints.setText(StringUtils.join(calresult, ","));
        });

        add(input, addItem, cal, result);
    }



}
