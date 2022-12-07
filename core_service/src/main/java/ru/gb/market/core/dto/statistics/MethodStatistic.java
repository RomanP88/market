package ru.gb.market.core.dto.statistics;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MethodStatistic {
    private String nameMethod;
    private List<Long> times;
    private Long totalTime;

    public MethodStatistic(String nameMethod, Long time) {
        this.nameMethod = nameMethod;
        this.times = new ArrayList<>();
        this.times.add(time);
        totalTime = time;
    }
    public void addTime(Long time) {
        times.add(time);
        totalTime+=time;
    }
}
