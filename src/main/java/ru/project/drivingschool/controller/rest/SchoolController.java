package ru.project.drivingschool.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.service.SchoolService;
import ru.project.drivingschool.to.SchoolTo;

@RestController
@RequestMapping("/schools")
public class SchoolController extends AbstractController<School, SchoolTo> {

    private SchoolService service;

    public SchoolController(SchoolService service) {
        super(service);
        this.service = service;
    }

    @Override
    SchoolTo createTo(School s) {
        return new SchoolTo(s);
    }
}
