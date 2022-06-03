package learn;

import learn.exception.ServiceException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;


public abstract class AbstractController {

    protected void validForm(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ObjectError error = bindingResult.getAllErrors().get(0);
            throw new ServiceException(error.getDefaultMessage());
        }
    }
}
