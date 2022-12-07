package ru.gb.market.core.aspects;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.gb.market.core.dto.statistics.ControllerStatisticDTO;
import ru.gb.market.core.services.StatisticService;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class MethodTimeAspect {//Аспект для подсчета времени работы метода
    private final StatisticService statisticService;

    @Around(value = "execution(* ru.gb.market.core.controllers..*(..))")
    public Object calculateTime(ProceedingJoinPoint pjp) throws Throwable {
        boolean flagAdd = false;
        Long start = System.currentTimeMillis();
        Object object = pjp.proceed();
        Long end = System.currentTimeMillis();
        String currentControllerName = pjp.getSignature().getDeclaringType().getSimpleName();
        String currentMethodName = pjp.getSignature().getName();
            for (ControllerStatisticDTO item : statisticService.getList()) {
                if (item.getNameController().equals(currentControllerName)) {
                    item.addMethod(currentMethodName, end - start);
                    flagAdd = true;
                    break;
                }
            }
        if(!flagAdd){
            statisticService.getList().add(new ControllerStatisticDTO(currentControllerName, currentMethodName, end - start));
        }
        for (ControllerStatisticDTO item : statisticService.getList()) {
            log.info(String.valueOf(item));
        }
        System.out.println();
        return object;
    }
}
