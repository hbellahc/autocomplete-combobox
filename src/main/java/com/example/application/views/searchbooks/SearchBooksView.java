package com.example.application.views.searchbooks;

import com.example.application.data.entity.SampleBook;
import com.example.application.data.service.SampleBookService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import org.apache.commons.lang3.StringUtils;


@PageTitle("Search Books")
@Route(value = "search-books", layout = MainLayout.class)
@Uses(Icon.class)
public class SearchBooksView extends VerticalLayout {


    public SearchBooksView(SampleBookService bookService) {


        FormLayout layout = new FormLayout();


        ComboBox<SampleBook> comboBox = new ComboBox<>();
        comboBox.setItemLabelGenerator(SampleBook::getName);

        comboBox.setAllowCustomValue(true);

        // by default just for test to display the 4 top books

        comboBox.addCustomValueSetListener(event -> {
            String searchWord = event.getDetail();
            if (StringUtils.isNotBlank(searchWord))
                comboBox.setItems(q -> bookService.search(searchWord, VaadinSpringDataHelpers.toSpringPageRequest(q)).stream());

        });

        layout.add(comboBox);
        setSizeFull();

        add(layout);


    }


}
