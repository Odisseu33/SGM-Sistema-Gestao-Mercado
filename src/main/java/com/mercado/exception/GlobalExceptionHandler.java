package com.mercado.exception;
import org.springframework.web.bind.annotation.ControllerAdvice; import org.springframework.web.bind.annotation.ExceptionHandler; import org.springframework.ui.Model; import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.ResponseStatus; import org.springframework.http.HttpStatus;
@ControllerAdvice public class GlobalExceptionHandler {
 @ExceptionHandler(BusinessException.class) public String negocio(BusinessException e,RedirectAttributes ra){ra.addFlashAttribute("erro",e.getMessage());return "redirect:/";}
 @ExceptionHandler(Exception.class) @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) public String geral(Exception e,Model model){model.addAttribute("mensagem","Ocorreu um erro ao processar a solicitação.");return "erro";}
}
