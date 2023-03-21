module com.jmp.service.rest {
    requires com.jmp.dto;

    requires spring.web;
    requires swagger.annotations;
    requires spring.hateoas;

    exports com.jmp.rest;
}