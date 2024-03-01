package com.mobo.mobosubway.view;

import com.mobo.mobosubway.service.PathService;
import com.mobo.mobosubway.vo.PathInfoVO;
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

@Route("subway")
@PageTitle("mobo-subway")
public class PathView extends VerticalLayout {

    @Autowired
    private PathService pathService;

    public PathView() {
        TextField start = new TextField();
        TextField end = new TextField();
        HorizontalLayout path = new HorizontalLayout();
        path.add(start, end);
        Button pathCalculate = new Button("calculate path");

        VerticalLayout result = new VerticalLayout();
        TextField transferNum = new TextField();
        TextField distance = new TextField();
        Text stations = new Text("");
        result.add(
                new H3("换乘数: "),
                transferNum,
                new H3("总距离 :"),
                distance,
                new H3("途径站点详情:"),
                stations
        );

        pathCalculate.addClickListener(click -> {
            PathInfoVO pathResult = pathService.getPath(start.getValue(), end.getValue());
            System.out.println(pathResult);
            stations.setText(StringUtils.join(pathResult.getDetail(), ","));
            transferNum.setValue(String.valueOf(pathResult.getTransferNum()));
            distance.setValue(String.valueOf(pathResult.getDistance()));
        });

        add(
                new Text("hello world"),
                path,
                pathCalculate,
                result
        );
    }
}
