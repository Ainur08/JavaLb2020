package ru.itis.hateoas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.hateoas.services.ConfirmationService;

import javax.servlet.http.HttpServlet;

@RepositoryRestController
public class ConfirmationController extends HttpServlet {
    @Autowired
    private ConfirmationService confirmationService;

    @RequestMapping(value = "users/activate/{token}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> activate(@PathVariable("token") String token) {
        return ResponseEntity.ok(
                EntityModel.of(confirmationService.checkConfirmation(token)));
    }
}
